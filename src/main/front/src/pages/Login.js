// src/pages/Login.js
import React, { useState } from "react";
import "../styles/Login.css";

export default function Login() {
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");

    const handleGoogleLogin = () => {
        // 필요하면 name/email을 쿼리나 state로 같이 넘겨도 됨
        window.location.href = "http://localhost:8080/login/google";
    };

    const handleNaverLogin = () => {
        window.location.href = "http://localhost:8080/login/naver";
    };

    const handleKakaoLogin = () => {
        alert("카카오 로그인은 아직 준비 중입니다 😅");
    };

    const handleClose = () => {
        window.location.href = "/";
    };

    return (
        <div className="login-page">
            <div className="login-modal">
                {/* X 버튼 */}
                <button className="login-close-btn" onClick={handleClose}>
                    ✕
                </button>

                {/* 타이틀 */}
                <h1 className="login-title">LOGIN</h1>
                <p className="login-subtitle">
                    소셜 계정으로 간편하게 로그인할 수 있어요.
                    <br />
                    첫 로그인 시 자동으로 회원가입됩니다.
                </p>

                {/* 이름 / 이메일 인풋 */}
                <div className="login-input-column">
                    <input
                        type="text"
                        className="login-input"
                        placeholder="이름 입력"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                    />
                    <input
                        type="email"
                        className="login-input"
                        placeholder="이메일 입력"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                </div>

                {/* 소셜 로그인 버튼들 */}
                <div className="login-social-column">
                    <button className="social-full naver" onClick={handleNaverLogin}>
                        <span className="social-full-label">네이버로 로그인</span>
                    </button>

                    <button className="social-full google" onClick={handleGoogleLogin}>
                        <span className="social-full-label">Google 계정으로 로그인</span>
                    </button>

                    <button className="social-full kakao" onClick={handleKakaoLogin}>
                        <span className="social-full-label">카카오로 로그인</span>
                    </button>
                </div>

                {/* 하단 안내 문구 */}
                <div className="login-bottom-text">
                    로그인함으로써 이용약관 및 개인정보 처리방침에 동의하게 됩니다.
                </div>
            </div>
        </div>
    );
}