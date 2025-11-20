package com.example.meongnyang_wiki.security.oauth;

import com.example.meongnyang_wiki.domain.user.UserMaster;
import com.example.meongnyang_wiki.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest)
            throws OAuth2AuthenticationException {


        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate =
                new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);


        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        String providerId;
        String name;
        String email;
        String picture = null;

        switch (registrationId) {
            case "google":

                providerId = (String) attributes.get("sub");
                name      = (String) attributes.get("name");
                email     = (String) attributes.get("email");
                picture   = (String) attributes.get("picture");
                break;

            case "naver":
                // { resultcode, message, response: { id, email, name, profile_image, ... } }
                @SuppressWarnings("unchecked")
                Map<String, Object> naverResp =
                        (Map<String, Object>) attributes.get("response");

                if (naverResp == null) {
                    throw new OAuth2AuthenticationException("네이버 응답 형식이 예상과 다릅니다.");
                }

                providerId = (String) naverResp.get("id");
                name      = (String) naverResp.get("name");
                email     = (String) naverResp.get("email");
                picture   = (String) naverResp.get("profile_image");
                attributes = naverResp;
                break;

            case "kakao":
                // { id, kakao_account: { email, profile: { nickname, profile_image_url, ... } } }
                providerId = String.valueOf(attributes.get("id"));

                @SuppressWarnings("unchecked")
                Map<String, Object> kakaoAccount =
                        (Map<String, Object>) attributes.get("kakao_account");

                Map<String, Object> kakaoProfile = null;
                if (kakaoAccount != null) {
                    email = (String) kakaoAccount.get("email");

                    kakaoProfile = (Map<String, Object>) kakaoAccount.get("profile");
                } else {
                    email = null;
                }

                if (kakaoProfile != null) {
                    name    = (String) kakaoProfile.getOrDefault("nickname", null);
                    picture = (String) kakaoProfile.getOrDefault("profile_image_url", null);
                } else {
                    name = null;
                }
                break;

            default:
                throw new OAuth2AuthenticationException("지원하지 않는 소셜 로그인입니다: " + registrationId);
        }

        UserMaster user = saveOrUpdate(email, name, picture, registrationId, providerId);

        Collection<GrantedAuthority> authorities =
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        
        Map<String, Object> extendedAttributes = new HashMap<>(attributes);
        extendedAttributes.put("userId", user.getUserId());
        extendedAttributes.put("userNm", user.getUserNm());


        String nameAttributeKey;
        if ("google".equals(registrationId)) {
            nameAttributeKey = "sub";
        } else {
            nameAttributeKey = "id";
        }

        return new DefaultOAuth2User(
                authorities,
                extendedAttributes,
                nameAttributeKey
        );
    }


    private UserMaster saveOrUpdate(String email,
                                    String name,
                                    String picture,   // 지금은 안 쓰지만 시그니처만 남겨둠
                                    String provider,
                                    String providerId) {


        UserMaster user = userRepository.selectByEmail(email);
        LocalDateTime now = LocalDateTime.now();

        if (user == null) {
            // 신규 유저
            user = new UserMaster();
            user.setProvider(provider);
            user.setProviderId(providerId);
            user.setEmail(email);
            user.setUserNm(name);
            // user.setMblNo(...);
            user.setLastLogin(now);
            user.setRegDt(now);

            userRepository.insertUser(user);
        } else {

            user.setProvider(provider);
            user.setProviderId(providerId);
            user.setUserNm(name);
            user.setLastLogin(now);
            user.setUdtDt(now);

            userRepository.updateUser(user);
        }

        return user;
    }
}