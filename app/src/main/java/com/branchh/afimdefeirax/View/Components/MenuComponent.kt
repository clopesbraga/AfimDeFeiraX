package com.branchh.afimdefeirax.View.Components

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Airplay
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.AddModerator
import androidx.compose.material.icons.outlined.Apps
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.branchh.afimdefeirax.R


data class MenuBar(val route:Int,val name: String, val icons: ImageVector)

@Composable
fun MainMenuBar(): List<MenuBar> {

    return listOf(
        MenuBar(
            route = 1,
            name = stringResource(R.string.menu_feiras),
            icons = Icons.Filled.LocationOn
        ),
        MenuBar(
            route = 2,
            name = stringResource(R.string.menu_produtos),
            icons = Icons.Filled.AddShoppingCart
        ),
        MenuBar(
            route = 3,
            name = stringResource(R.string.menu_historico),
            icons = Icons.Filled.BarChart
        ),
        MenuBar(
            route = 4,
            name = stringResource(R.string.menu_details),
            icons = Icons.Filled.Airplay
        )
    )
}

@Composable
fun getMainMenu(): List<MenuBar> {
    return listOf(
        MenuBar(
            route = 1,
            name = stringResource(R.string.menu_feiras),
            icons = Icons.Filled.LocationOn
        ),
        MenuBar(
            route = 2,
            name = stringResource(R.string.menu_produtos),
            icons = Icons.Filled.AddShoppingCart
        ),
        MenuBar(
            route = 3,
            name = stringResource(R.string.menu_historico),
            icons = Icons.Filled.BarChart
        ),
        MenuBar(
            route = 4,
            name = stringResource(R.string.menu_details),
            icons = Icons.Filled.Airplay
        )
    )
}


@Composable
fun MoreOptionsMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    navController: NavHostController,
) {
    DropdownMenu(
        offset = androidx.compose.ui.unit.DpOffset(-60.dp, -220.dp),
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.8f))
    ) {
        DropdownMenuItem(
            text = { Text(stringResource(R.string.sobre_o_app)) },
            leadingIcon = {
                Icon(Icons.Outlined.Apps, contentDescription = null)
            },
            onClick = {
                navController.navigate("about")
                onDismissRequest()
            }
        )

        DropdownMenuItem(
            text = { Text(stringResource(R.string.info_politicas)) },
            leadingIcon = {
                Icon(Icons.Outlined.AddModerator, contentDescription = null)
            },
            onClick = {
                navController.navigate("terms")
                onDismissRequest()
            }
        )

    }
}