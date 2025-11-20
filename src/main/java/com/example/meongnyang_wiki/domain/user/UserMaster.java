package com.example.meongnyang_wiki.domain.user;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserMaster {

    private Integer userId;      // user_id

    private String provider;     // 로그인제공자

    private String providerId;   // 소셜로그인 키값

    private String mblNo;        // 휴대폰번호 (mbl_no) ← ★ 이름 정확히 mblNo

    private String email;        // email

    private String userNm;       // 유저명 (user_nm)  ← ★ DB 컬럼 맞춰서

    private LocalDateTime lastLogin; // last_login

    private LocalDateTime regDt;     // reg_dt

    private Integer regUser;         // reg_user

    private LocalDateTime udtDt;     // udt_dt

    private Integer udtUser;         // udt_user
}