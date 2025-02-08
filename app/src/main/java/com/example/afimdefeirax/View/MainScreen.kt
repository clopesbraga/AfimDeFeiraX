package com.example.afimdefeirax.View

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCard
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.afimdefeirax.SharedPreferences.LoginSharedImpl
import com.example.afimdefeirax.Utils.Monitoring
import com.example.afimdefeirax.View.Components.MenuBar
import com.example.afimdefeirax.View.Screens.LoginScreen
import com.example.afimdefeirax.View.Screens.MapFeirasScreen
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics


private val menuOptionsBar = listOf(
    MenuBar(name = "Feiras", icons = Icons.Filled.LocationOn),
    MenuBar(name = "Compras", icons = Icons.Filled.AddCard),
    MenuBar(name = "Historico", icons = Icons.Filled.DateRange),
)

private var startScreen: String = ""
private lateinit var mSharedLogin: LoginSharedImpl
private const val ID = "id"


class MainScreen : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val showBottomBar = remember { mutableStateOf(true) }
            mSharedLogin = LoginSharedImpl(application.applicationContext)
            val firebase: FirebaseAnalytics = Firebase.analytics

            firebase.logEvent(Monitoring.Main.MAIN_SCREEN, null)

            Scaffold(
                bottomBar = {
                    if (showBottomBar.value) {
                        MenuBottomBar(navController)
                    }
                }
            ) { innerpading ->

                if (verifyAcess(mSharedLogin)) startScreen = "map" else startScreen = "login"

                NavHost(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerpading),
                    navController = navController,
                    startDestination = startScreen
                )
                {
                    composable(route = "login") {
                        LoginScreen(
                            navController,
                            showBottomBar = ({ showBottomBar.value = it })
                        )
                    }
                    composable(route = "map") {
                        MapFeirasScreen(
                            navController,
                            showBottomBar = ({ showBottomBar.value = it })
                        )
                    }
//                    composable(route = "hist") { HistoricoScreen(navController, showBottomBar) }
                }


            }
        }
    }


}

@Composable
fun MenuBottomBar(navController: NavHostController) {
    var selectedItem = menuOptionsBar.first()

    BottomAppBar(
        containerColor = Color(0xFF009688),
    ) {

        val actions = @Composable {
            menuOptionsBar.forEach { item ->
                val text = item.name
                val icon = item.icons
                NavigationBarItem(
                    colors = NavigationBarItemDefaults.colors(Color(0xFF009688)),
                    alwaysShowLabel = false,
                    selected = selectedItem == item,
                    onClick = {

                        val route = when (text) {
                            "Feiras" -> "map"
                            "Compras" -> "comp"
                            "Historico" -> "hist"
                            else -> {
                                "map"
                            }
                        }
                        navController.navigate(route, navOptions = navOptions {
                            launchSingleTop = true
                            popUpTo(navController.graph.startDestinationId)
                        })
                    },
                    icon = { Icon(imageVector = icon, contentDescription = null) },
                    label = { Text(text = text, color = Color.White) },

                    )

            }

        }
        actions()
    }
}

private fun verifyAcess(mSharedLogin: LoginSharedImpl): Boolean {
    return mSharedLogin.getString(ID).isNotEmpty()
}