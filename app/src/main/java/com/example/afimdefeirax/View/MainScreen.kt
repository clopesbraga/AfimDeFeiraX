package com.example.afimdefeirax.View

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Airplay
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.afimdefeirax.SharedPreferences.LoginSharedImpl
import com.example.afimdefeirax.State.MainUIState
import com.example.afimdefeirax.Utils.Monitoring
import com.example.afimdefeirax.View.Components.MenuBar
import com.example.afimdefeirax.View.Components.MoreOptionsMenu
import com.example.afimdefeirax.View.Screens.LoginScreen
import com.example.afimdefeirax.View.Screens.MapFeirasScreen
import com.example.afimdefeirax.View.Screens.ProdutosListScreen
import com.example.afimdefeirax.View.Screens.ProdutosScreen
import com.example.afimdefeirax.ViewModel.MainViewModel
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import org.koin.compose.koinInject


private val menuOptionsBar = listOf(
    MenuBar(name = "Feiras", icons = Icons.Filled.LocationOn),
    MenuBar(name = "Compras", icons = Icons.Filled.AddShoppingCart),
    MenuBar(name = "Historico", icons = Icons.Filled.BarChart),
    MenuBar(name = "Detalhes", icons = Icons.Filled.Airplay)
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

            val viewModel: MainViewModel = koinInject()
            val state by viewModel.state.collectAsState()
            val context = LocalContext.current


            val navController = rememberNavController()
            val showBottomBar = remember { mutableStateOf(true) }
            mSharedLogin = LoginSharedImpl(application.applicationContext)
            val firebaseanalytics: FirebaseAnalytics = Firebase.analytics

            firebaseanalytics.logEvent(Monitoring.Main.MAIN_SCREEN_START, null)


            Scaffold(
                bottomBar = {
                    if (showBottomBar.value) {
                        MenuBottomBar(navController,state,viewModel,context)
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
                            showBottomBar = ({ showBottomBar.value = it })
                        )
                    }
                    composable(route = "comp") {
                        ProdutosScreen(
                            navController,
                            showBottomBar = ({ showBottomBar.value = it })
                        )
                    }

                    composable(route = "list") {
                        ProdutosListScreen(
                            navController,
                            showBottomBar = ({ showBottomBar.value = it })
                        )
                    }
                    composable(route = "hist") {
                        com.example.afimdefeirax.View.Screens.HistoricoScreen(
                            showBottomBar = ({ showBottomBar.value = it })
                        )
                    }
                    composable(route = "about") {
                        com.example.afimdefeirax.View.Screens.AboutAppScreen(
                            navController,
                            showBottomBar = ({ showBottomBar.value = it })
                        )
                    }
                    composable(route = "terms") {
                        com.example.afimdefeirax.View.Screens.TermsOfServiceScreen(navController)
                    }
                }
            }
        }
    }

    @Composable
    fun MenuBottomBar(
        navController: NavHostController,
        state: MainUIState,
        viewModel: MainViewModel,
        context: Context
    ) {

        var selectedItem by remember { mutableStateOf(menuOptionsBar[0]) }
        var expanded by remember { mutableStateOf(false) }
        var tela2NavigationCount by remember { mutableStateOf(0) }

        MoreOptionsMenu(
            expanded,
            onDismissRequest = { expanded = false },
            navController
        )

        BottomAppBar(
            containerColor = Color(0xFF009688),
        ) {

            val actions = @Composable {
                menuOptionsBar.forEach { item ->
                    val text = item.name
                    val icon = item.icons
                    NavigationBarItem(
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFF009688),
                            unselectedIconColor = Color.White,
                            selectedTextColor = Color.White,
                            unselectedTextColor = Color.White,
                            indicatorColor = Color.White
                        ),
                        alwaysShowLabel = false,
                        selected = selectedItem == item,
                        onClick = {
                            selectedItem = item
                            val route = when (text) {
                                "Feiras" -> "map"
                                "Compras" -> {
                                    viewModel.onNavigationCount(tela2NavigationCount++)
                                    if(state.navigatecount % state.frequencycount ==0){
                                        viewModel.loadAds(context)
                                    }
                                    "comp"
                                }
                                "Historico" -> "hist"
                                "Detalhes" -> {
                                    expanded = !expanded
                                    return@NavigationBarItem
                                }

                                else -> {
                                    "map"
                                }
                            }
                            navController.navigate(route, navOptions = navOptions {
                                launchSingleTop = true
                                popUpTo(navController.graph.startDestinationId)
                            })
                            if ((route.contains("comp")) && state.navigatecount % state.frequencycount==0) {
                                viewModel.ShowAd(context = context)
                                tela2NavigationCount=0
                            }
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
}