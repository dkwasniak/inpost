package pl.inpost.recruitmenttask.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import pl.inpost.recruitmenttask.R

// Define a custom font family (optional)
val InpostFontFamily = FontFamily(
    Font(R.font.montserrat_regular, FontWeight.Normal),
    Font(R.font.montserrat_bold, FontWeight.Bold),
    Font(R.font.montserrat_black, FontWeight.Black),
    Font(R.font.montserrat_extrabold, FontWeight.ExtraBold),
    Font(R.font.montserrat_light, FontWeight.Light),
    Font(R.font.montserrat_medium, FontWeight.Medium),
    Font(R.font.montserrat_semibold, FontWeight.SemiBold),
)


val Typography.title: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = InpostFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.4.sp
        )
    }

val Typography.subtitle: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = InpostFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 11.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.4.sp
        )
    }

val Typography.status: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = InpostFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.4.sp
        )
    }

val Typography.h7: TextStyle
    @Composable
    get() {
        return  TextStyle(
            fontFamily = InpostFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.4.sp
        )
    }

val Typography.h6: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = InpostFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.4.sp
        )
    }

val Typography.h9: TextStyle
    @Composable
    get() {
        return  TextStyle(
            fontFamily = InpostFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            lineHeight = 26.sp,
            letterSpacing = 0.4.sp
        )
    }

