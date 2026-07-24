package com.smoothsm.cameraapp.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.smoothsm.cameraapp.R

// Jua — 디스플레이 / 숫자 / 헤더 (Google Fonts 다운로드)
val JuaFontFamily = FontFamily(Font(R.font.jua))

val PretendardFontFamily =
    FontFamily(
        Font(R.font.pretendard_regular, FontWeight.Normal),
        Font(R.font.pretendard_medium, FontWeight.Medium),
        Font(R.font.pretendard_bold, FontWeight.Bold),
    )

val Typography =
    Typography(
        // 큰 금액 표시 (33sp)
        displayLarge =
            TextStyle(
                fontFamily = JuaFontFamily,
                fontSize = 33.sp,
                lineHeight = 40.sp,
            ),
        // 로고 워드마크 (31sp)
        displayMedium =
            TextStyle(
                fontFamily = JuaFontFamily,
                fontSize = 31.sp,
                lineHeight = 38.sp,
            ),
        // 헤더 타이틀 (21sp)
        headlineMedium =
            TextStyle(
                fontFamily = JuaFontFamily,
                fontSize = 21.sp,
                lineHeight = 28.sp,
            ),
        // 섹션 소제목 (16sp)
        titleMedium =
            TextStyle(
                fontFamily = JuaFontFamily,
                fontSize = 16.sp,
                lineHeight = 22.sp,
            ),
        // 본문 (15sp)
        bodyLarge =
            TextStyle(
                fontFamily = PretendardFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                lineHeight = 22.sp,
            ),
        // 본문 소 (14sp)
        bodyMedium =
            TextStyle(
                fontFamily = PretendardFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 20.sp,
            ),
        // 보조 설명 (13sp)
        bodySmall =
            TextStyle(
                fontFamily = PretendardFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 13.sp,
                lineHeight = 18.sp,
            ),
        // 캡션 / 라벨 (11sp)
        labelSmall =
            TextStyle(
                fontFamily = PretendardFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 11.sp,
                lineHeight = 16.sp,
            ),
        // 달력 날짜별 금액 (9.5sp)
        labelMedium =
            TextStyle(
                fontFamily = JuaFontFamily,
                fontSize = 9.5.sp,
                lineHeight = 13.sp,
            ),
    )
