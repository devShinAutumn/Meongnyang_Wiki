package com.example.meongnyang_wiki.controller;



import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/login")
public class LoginController {


        @GetMapping("/google")
        public void googleLogin(HttpServletResponse response) throws IOException {
            response.sendRedirect("/oauth2/authorization/google");
        }

        @GetMapping("/naver")
        public void naverLogin(HttpServletResponse response) throws IOException {
            System.out.println("네이버 로그인 엔드포인트 진입!");
            response.sendRedirect("/oauth2/authorization/naver");
        }



}
