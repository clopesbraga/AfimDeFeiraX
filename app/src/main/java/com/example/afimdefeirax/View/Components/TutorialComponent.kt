package com.example.afimdefeirax.View.Components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.canopas.lib.showcase.IntroShowcase
import com.canopas.lib.showcase.component.ShowcaseStyle

@Composable
fun TutorialShowCaseComponent(
    targetIndex: Int,
    tutorialStyle: ShowcaseStyle = ShowcaseStyle.Default.copy(
        backgroundColor = Color(0xFF009688),
        backgroundAlpha = 0.98f,
        targetCircleColor = Color.White
    ),
    title: String,
    description: String,
    showintro: Boolean,
    onTutorialCompleted: Boolean,
    content: @Composable () -> Unit

) {

    IntroShowcase(
        showIntroShowCase = showintro,
        dismissOnClickOutside = true,
        onShowCaseCompleted = { true },
    ) {

        Box(
            modifier = Modifier
                .padding(16.dp)
                .introShowCaseTarget(
                index = targetIndex,
                style = tutorialStyle,
                content = {
                    Column {
                        Text(
                            text = title,
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = description,
                            color = Color.White,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            ),
        ){
            content()
        }

    }

}