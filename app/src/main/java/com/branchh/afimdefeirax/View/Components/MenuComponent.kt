package com.branchh.afimdefeirax.View.Components

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
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


data class MenuBar(val name: String, val icons: ImageVector)


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