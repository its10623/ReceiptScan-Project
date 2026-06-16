package com.example.cameraapp.domain.model

enum class SyncStatus{
    PENDING,    // 로컬 저장, 서버 전송 대기
    SYNCED,     // 서버와 동기화 완료
    FAILED      // 전송 시도했으나 실패
}