package ru.mercanie.taskweatherapp.presentation.utils

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import ru.mercanie.taskweatherapp.R

@Composable
fun TitleLarge(text: String) {
    Text(text = text, fontFamily = FontFamily(Font(R.font.harryen)), fontSize = 46.sp)
}

@Composable
fun TextMedium(text: String) {
    Text(text = text, fontFamily = FontFamily(Font(R.font.harryen)), fontSize = 26.sp)
}

@Composable
fun TextSmall(text: String) {
    Text(text = text, fontFamily = FontFamily(Font(R.font.harryen)), fontSize = 16.sp)
}