package com.example.cameraapp.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DirectionsBus
import androidx.compose.material.icons.rounded.LocalCafe
import androidx.compose.material.icons.rounded.MoreHoriz
import androidx.compose.material.icons.rounded.Restaurant
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.cameraapp.presentation.ui.theme.CategoryCafe
import com.example.cameraapp.presentation.ui.theme.CategoryCafeBg
import com.example.cameraapp.presentation.ui.theme.CategoryEtc
import com.example.cameraapp.presentation.ui.theme.CategoryEtcBg
import com.example.cameraapp.presentation.ui.theme.CategoryGrocery
import com.example.cameraapp.presentation.ui.theme.CategoryGroceryBg
import com.example.cameraapp.presentation.ui.theme.CategoryMeal
import com.example.cameraapp.presentation.ui.theme.CategoryMealBg
import com.example.cameraapp.presentation.ui.theme.CategoryTransport
import com.example.cameraapp.presentation.ui.theme.CategoryTransportBg

enum class Category {
    MEAL,        // 식비
    GROCERY,     // 생활/마트
    TRANSPORT,   // 교통
    CAFE_SNACK,  // 카페/간식
    ETC          // 기타
}

val Category?.icon: ImageVector get() = when (this) {
    Category.MEAL -> Icons.Rounded.Restaurant
    Category.GROCERY -> Icons.Rounded.ShoppingCart
    Category.TRANSPORT -> Icons.Rounded.DirectionsBus
    Category.CAFE_SNACK -> Icons.Rounded.LocalCafe
    else -> Icons.Rounded.MoreHoriz
}

val Category?.label: String get() = when (this) {
    Category.MEAL -> "식비"
    Category.GROCERY -> "생활·마트"
    Category.TRANSPORT -> "교통"
    Category.CAFE_SNACK -> "카페·간식"
    else -> "기타"
}

val Category?.iconTint: Color get() = when (this) {
        Category.MEAL -> CategoryMeal
        Category.GROCERY -> CategoryGrocery
        Category.TRANSPORT -> CategoryTransport
        Category.CAFE_SNACK -> CategoryCafe
        else -> CategoryEtc
}

val Category?.chipBg: Color get() = when (this) {
    Category.MEAL -> CategoryMealBg
    Category.GROCERY -> CategoryGroceryBg
    Category.TRANSPORT -> CategoryTransportBg
    Category.CAFE_SNACK -> CategoryCafeBg
    else -> CategoryEtcBg
}