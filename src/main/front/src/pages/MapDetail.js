import React from "react";
import "../styles/MapDetail.css";

export default function MapDetail({place, review, onClose}) {
    const defaultPlace = {
        category: "카테고리명",
        name: "업체명",
        rating: 4.5,
        address: "경기도 용인시 기흥구 구갈동 72번지",
        hours: "운영시간 : 17:00 ~ 22:00",
    };

    const defaultReview = {
        title: "리뷰",
        rating: 4.5,
        date: "2023.01.01",
        content:
            "여기 진짜 좋아요 굳굳!\n내용입니다 내용입니다 내용입니다 내용입니다 내용입니다 내용입니다 내용입니다 내용입니다 내용입니다 내용입니다.",
    };

    const p = place || defaultPlace;
    const r = review || defaultReview;

    return (
        <div className="md-wrapper">
            <div className="md-container">
                {onClose && (
                    <button className="md-close-btn" onClick={onClose}>
                        ✕
                    </button>
                )}

                <div className="md-place-card">
                    <div className="md-photos">
                        <div className="md-photo-main"/>
                        <div className="md-photo-sub-grid">
                            <div className="md-photo-sub"/>
                            <div className="md-photo-sub"/>
                            <div className="md-photo-sub"/>
                        </div>
                    </div>

                    <div className="md-place-info">
                        <div className="md-category">{p.category}</div>

                        <div className="md-name-row">
                            <span className="md-name">{p.name}</span>
                            <span className="md-rating">★ {p.rating}</span>
                        </div>

                        <div className="md-address">{p.address}</div>
                        <div className="md-hours">{p.hours}</div>
                    </div>
                </div>

                <div className="md-review-card">
                    <div className="md-review-header">
                        <div>
                            <span className="md-review-title">{r.title}</span>
                            <span className="md-review-dot">•</span>
                        </div>
                        <span className="md-review-date">{r.date}</span>
                    </div>

                    <div className="md-review-rating-row">
                        <span className="md-review-score">{r.rating}</span>
                        <span className="md-review-stars">★★★★★</span>
                    </div>

                    <p className="md-review-content">
                        {r.content.split("\n").map((line, idx) => (
                            <span key={idx}>
                {line}
                                <br/>
              </span>
                        ))}
                    </p>
                </div>
            </div>
        </div>
    );
}