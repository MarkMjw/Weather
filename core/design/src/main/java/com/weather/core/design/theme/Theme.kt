package com.weather.core.design.theme

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Build
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

//Use in WeatherTheme
private val darkColorScheme = darkColorScheme(
    primary = Blue,
    surface = Gray800,
    background = Gray900,
    onPrimary = White,
    onBackground = White,
    onSurface = White
)

private val lightColorScheme = lightColorScheme(
    primary = Blue,
    surface = Gray200,
    background = Gray100,
    onPrimary = White,
    onBackground = Black,
    onSurface = Black,
)

@Composable
fun WeatherTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
//    val dynamicColor = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val colorScheme = when {
//        dynamicColor && darkTheme -> dynamicDarkColorScheme(LocalContext.current)
//        dynamicColor && !darkTheme -> dynamicLightColorScheme(LocalContext.current)
        darkTheme -> darkColorScheme
        else -> lightColorScheme
    }
    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}

@Preview(showBackground = true, showSystemUi = false, uiMode = UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = false, uiMode = UI_MODE_NIGHT_NO)
@Composable
private fun ThemeTest() {
    WeatherTheme {
        Surface() {
            Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
                Column(
                    Modifier.padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Surface(
                        border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.onSurface),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(text = "This is a surface")
                    }
                    Text(text = "this is a text")
                    Spacer(modifier = Modifier.height(16.dp))
                    TextButton(
                        onClick = {},
                        border = BorderStroke(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.onSurface
                        ),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
                    ) {
                        Text(text = "this is text button")
                    }
                    Button(
                        onClick = {},
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
                    ) {
                        Text(text = "this is a button")
                    }
                }
            }

        }
    }
}

