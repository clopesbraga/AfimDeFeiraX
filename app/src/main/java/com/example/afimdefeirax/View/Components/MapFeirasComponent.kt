package com.example.afimdefeirax.View.Components


 import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.afimdefeirax.R
import com.example.afimdefeirax.R.mipmap.ic_bandeira_saopaulo
import com.example.afimdefeirax.State.MapFeirasUIState
import com.example.afimdefeirax.Utils.CarouselItem
import com.example.afimdefeirax.Utils.Flags
import com.example.afimdefeirax.ViewModel.MapaFeirasViewModel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ListOfNeighborHoodComponent(
    filteredCarouselItems: List<CarouselItem>,
    selectedCity: String?,
    viewModel: MapaFeirasViewModel
) {

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsDraggedAsState()

    var selectedItem by remember { mutableStateOf<CarouselItem?>(null) }


    val backgroundColor by animateColorAsState(
        targetValue = if (isPressed) Color.LightGray else Color.Transparent,
        animationSpec = tween(durationMillis = 100),
        label = "backgroundColorAnimation"
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(filteredCarouselItems.size) { index ->
            val item = filteredCarouselItems[index]
            val isSelected = item == selectedItem
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(backgroundColor)
                    .border(
                        width = 1.dp,
                        color = if (isSelected) Color.Green else Color.White,
                        shape = MaterialTheme.shapes.medium
                    )
                    .clickable(
                        interactionSource = interactionSource,
                        indication =null,
                    ) {
                        selectedItem = item
                        selectedCity?.let { city ->
                            viewModel.geoLocalization(city,item.bairros)
                        }
                    }
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {
                Text(
                    filteredCarouselItems[index].bairros,
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(8.dp)
                )
                Image(
                    modifier = Modifier
                        .height(105.dp)
                        .clip(CircleShape),
                    painter = painterResource(id = filteredCarouselItems[index].imageResId),
                    contentDescription = stringResource(filteredCarouselItems[index].contentDescriptionResId),
                    contentScale = ContentScale.Crop
                )

            }
        }
    }
}

@Composable
fun SearchNeighborHoodComponent(
    searchQuery: String,
    neighborhoodsToShow: List<String>,
    cityImages: Int,
    selectedCity: String?,
    viewModel: MapaFeirasViewModel
) {
    val filteredCarouselItems = neighborhoodsToShow.filter {
        it.contains(searchQuery, ignoreCase = true)
    }.map { bairro ->
        CarouselItem(
            bairros = bairro,
            imageResId = cityImages,
            contentDescriptionResId = R.string.app_name
        )
    }
    if (filteredCarouselItems.isEmpty()) {
        Text(
            text = "Nenhum bairro encontrado",
            color = Color.White,
            modifier = Modifier.padding(16.dp)
        )
    } else {
        ListOfNeighborHoodComponent(
            filteredCarouselItems,
            selectedCity,
            viewModel
        )
    }
}

@Composable
 fun CitiesMenuComponent(
    cities: Array<String>,
    index: Int,
    isSelected: Boolean,
    viewModel: MapaFeirasViewModel,
) {
    Text(
        text = cities[index],
        color = if (isSelected) Color(0xFF009688) else Color.White,

        fontSize = MaterialTheme.typography.titleMedium.fontSize,
        modifier = Modifier
            .height(40.dp)
            .background(
                if (isSelected) Color.White else Color.Transparent,
                shape = MaterialTheme.shapes.medium
            )
            .clickable {
                viewModel.onCityChange(cities[index])
                viewModel.onNeighborhoodSelected(
                    viewModel.neighborhoodsMap[cities[index]]?.toList() ?: emptyList()
                )
                viewModel.onCityImageChange(Flags[cities[index]] ?:ic_bandeira_saopaulo)
            }
            .padding(horizontal = 16.dp, vertical = 0.dp)
    )
}