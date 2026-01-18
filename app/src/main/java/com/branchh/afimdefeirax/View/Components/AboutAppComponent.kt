import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LinkText(text: String,url: String) {
    val context = LocalContext.current

    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(
            color = Color(0xFF0000EE),
            textDecoration = TextDecoration.Underline
        )
        ) {
            append(text)
        }
    }

    ClickableText(
        text = annotatedString,
        onClick = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(intent)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 8.dp),
        style = androidx.compose.ui.text.TextStyle(
            textAlign = TextAlign.Justify,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    )
}