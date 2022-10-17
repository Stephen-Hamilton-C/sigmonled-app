package app.shamilton.common

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

//https://developer.android.com/reference/kotlin/androidx/compose/material/package-summary#MaterialTheme(androidx.compose.material.Colors,androidx.compose.material.Typography,androidx.compose.material.Shapes,kotlin.Function0)
@Composable
fun App() {
    val lightColors = lightColors(
        primary = Color(0xFF1EB980)
    )

    val darkColors = darkColors(
        primary = Color(0xFF66ffc7)
    )

    val colors = if (isSystemInDarkTheme()) darkColors else lightColors

    val typography = Typography(
        h1 = TextStyle(
            fontWeight = FontWeight.W100,
            fontSize = 96.sp
        ),
        button = TextStyle(
            fontWeight = FontWeight.W600,
            fontSize = 14.sp
        )
    )



    var text by remember { mutableStateOf("Hello, World!") }
    val platformName = getPlatformName()

    MaterialTheme(colors, typography) {
        Column() {
            Button(onClick = {
                text = "Hello, $platformName"
            }) {
                Text(text)
            }
            Text("Hello")
        }
    }
}
