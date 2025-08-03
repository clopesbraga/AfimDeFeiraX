package com.branchh.afimdefeirax.View.Screens

import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CheckboxDefaults.colors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.branchh.afimdefeirax.R
import com.branchh.afimdefeirax.Utils.FirebaseAnalytics.FirebaseAnalyticsImpl
import com.branchh.afimdefeirax.Utils.Monitoring
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutAppScreen(navController: NavHostController, showBottomBar: (Boolean)->Unit) {

    val firebaseanalytics: FirebaseAnalyticsImpl = koinInject()

    firebaseanalytics.firebaselogEvent(Monitoring.AboutApp.ABOUTAPP_START)
    Scaffold(

        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(R.string.sobre_o_app),
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }
                },
                navigationIcon = {
                    IconButton(

                        onClick = {
                            navController.navigate("map")
                            showBottomBar(true)
                        }) {
                        Icon(Icons.Filled.ArrowBackIosNew, "Back", tint = Color.White)
                    }
                    colors(Color.White)
                },
                colors = topAppBarColors(
                    containerColor = Color(0xFF009688)
                ),

                )
        }

    ) { innerpading ->

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerpading)) {

            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.linearGradient(
                            listOf(
                                Color(0xFF009688),
                                Color(0xFF3E2723),
                                Color(0xFF00BFA5),
                            ),
                        )
                    )
                    .height(250.dp)
            ) {
                this

                Image(
                    painter = painterResource(id = R.mipmap.ic_app_logo_foreground),
                    contentDescription = stringResource(id = R.string.app_name),
                    modifier = Modifier
                        .size(750.dp)
                        .clip(CircleShape)
                        .align(Alignment.Center)
                )


            }

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                ) {

                    Text(
                        text = stringResource(id = R.string.app_name),
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        textAlign = TextAlign.Justify,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    firebaseanalytics.firebaselogEvent(Monitoring.AboutApp.SHOW_APP_VERSION)
                    AppDescription()

                }


            }

        }


    }

}
@Composable
fun AppDescription() {

    Column {


        Row { FormatTitle(R.string.text_1_title) }
        Row { FormatDescription(R.string.text_1) }

        Row { FormatTitle(R.string.text_2_title) }
        Row { FormatDescription(R.string.text_2) }

        Row { FormatTitle(R.string.text_3_title) }
        Row { Text(text=getAppVersion(LocalContext.current)) }

    }

}

@Composable
fun FormatDescription(description: Int) {

    Text(
        text = stringResource(id = description),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        textAlign = TextAlign.Justify,
        fontSize = 18.sp,
        fontWeight = FontWeight.Light

    )

}

@Composable
fun FormatTitle(subtitle: Int) {

    Text(
        text = stringResource(id = subtitle),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        textAlign = TextAlign.Justify,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold

    )

}



fun getAppVersion(context: Context): String {
    return try {
        val packageManager = context.packageManager
        val packageInfo = packageManager.getPackageInfo(context.packageName, 0)
        packageInfo.versionName
    } catch (error: PackageManager.NameNotFoundException) {
        Log.e(Monitoring.AboutApp.ABOUTAPP_VERSION_ERROR,error.message.toString())
    }.toString()
}