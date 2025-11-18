import React, { useState } from "react";
import "../styles/MapMain.css";
import MapDetail from "../pages/MapDetail";
import { Map, MapMarker } from "react-kakao-maps-sdk";

const dummyPlaces = [
    {
        id: 1,
        category: "카테고리명",
        name: "업체명1",
        rating: 4.5,
        address: "경기도 용인시 기흥구 구갈동 72번지",
        hours: "운영시간 : 17:00 ~ 22:00",
        lat: 37.4979,
        lng: 127.0276,
    },
    {
        id: 2,
        category: "카테고리명",
        name: "업체명2",
        rating: 4.5,
        address: "경기도 용인시 기흥구 구갈동 72번지",
        hours: "운영시간 : 17:00 ~ 22:00",
        lat: 37.4985,
        lng: 127.0290,
    },
    {
        id: 2,
        category: "카테고리명",
        name: "업체명3",
        rating: 4.5,
        address: "경기도 용인시 기흥구 구갈동 72번지",
        hours: "운영시간 : 17:00 ~ 22:00",
        lat: 37.4989,
        lng: 127.0299,
    },
];

export default function MapMain() {
    const [selectedPlace, setSelectedPlace] = useState(null);

    return (
        <div className="main-page">
            {/* 왼쪽 리스트 영역 */}
            <div className="main-left">
                <div className="main-search-bar">
                    <span className="search-icon">🔍</span>
                    <input className="search-input" placeholder="검색어를 입력해주세요." />
                </div>

                <div className="main-list">
                    {dummyPlaces.map((p) => (
                        <div
                            key={p.id}
                            className="place-card"
                            onClick={() => setSelectedPlace(p)} // 클릭하면 레이어카드 표시
                        >
                            <div className="place-thumbnail" />
                            <div className="place-info">
                                <div className="place-category">{p.category}</div>
                                <div className="place-title-row">
                                    <span className="place-name">{p.name}</span>
                                    <span className="place-rating">★ {p.rating}</span>
                                </div>
                                <div className="place-address">{p.address}</div>
                                <div className="place-hours">{p.hours}</div>
                            </div>
                        </div>
                    ))}
                </div>
            </div>

            {/* 오른쪽 지도 영역 */}
            <div className="main-map">
                <Map
                    center={{ lat: 37.4979, lng: 127.0276 }}
                    style={{ width: "100%", height: "100vh" }}
                    level={3}
                >
                    {dummyPlaces.map((p) => (
                        <MapMarker key={p.id} position={{ lat: p.lat, lng: p.lng }}>
                            <div>{p.name}</div>
                        </MapMarker>
                    ))}
                </Map>

                {/* 레이어 카드: selectedPlace가 있으면 지도 위 중앙에 표시 */}
                {selectedPlace && (
                    <div className="map-detail-overlay">
                        <MapDetail
                            place={selectedPlace}
                            onClose={() => setSelectedPlace(null)}
                        />
                    </div>
                )}
            </div>
        </div>
    );
}
