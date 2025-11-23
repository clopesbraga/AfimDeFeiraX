package com.branchh.afimdefeirax.View.Components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.branchh.afimdefeirax.Model.ItemsModel
import com.branchh.afimdefeirax.R

@Composable
fun getAllProductsList(): List<List<ItemsModel>> {

    return listOf(
        getFruitsList(),
        getVegetablesList(),
        getGreensList(),
        getMeatList(),
        getFishList(),
        getUtilitiesList(),
        getSnacksList(),
        getCondimentsList()
    )
}

@Composable
fun getFruitsList(): List<ItemsModel> {

    return listOf(
        ItemsModel(
            stringResource(R.string.category_fruits),
            stringResource(R.string.fruit_abacaxi),
            R.mipmap.ic_fruta_abacaxi_foreground
        ),
        ItemsModel(
            stringResource(R.string.category_fruits),
            stringResource(R.string.fruit_banana),
            R.mipmap.ic_fruta_banana_foreground
        ),
        ItemsModel(
            stringResource(R.string.category_fruits),
            stringResource(R.string.fruit_caju),
            R.mipmap.ic_fruta_caqui_foreground
        ),
        ItemsModel(
            stringResource(R.string.category_fruits),
            stringResource(R.string.fruit_caqui),
            R.mipmap.ic_fruta_caqui_foreground
        ),
        ItemsModel(
            stringResource(R.string.category_fruits),
            stringResource(R.string.fruit_carambola),
            R.mipmap.ic_fruta_carambola_foreground
        ),
        ItemsModel(
            stringResource(R.string.category_fruits),
            stringResource(R.string.fruit_coco),
            R.mipmap.ic_fruta_coco_foreground
        ),
        ItemsModel(
            stringResource(R.string.category_fruits),
            stringResource(R.string.fruit_figo),
            R.mipmap.ic_figo
        ),
        ItemsModel(
            stringResource(R.string.category_fruits),
            stringResource(R.string.fruit_goiaba),
            R.mipmap.ic_fruta_goiaba_foreground
        ),
        ItemsModel(
            stringResource(R.string.category_fruits),
            stringResource(R.string.fruit_jaca),
            R.mipmap.ic_fruta_jaca_foreground
        ),
        ItemsModel(
            stringResource(R.string.category_fruits),
            stringResource(R.string.fruit_kiwi),
            R.mipmap.ic_fruta_kiwi_foreground
        ),
        ItemsModel(
            stringResource(R.string.category_fruits),
            stringResource(R.string.fruit_jaboticaba),
            R.mipmap.ic_fruta_jaboticaba_foreground
        ),
        ItemsModel(
            stringResource(R.string.category_fruits),
            stringResource(R.string.fruit_laranja),
            R.mipmap.ic_fruta_laranja_foreground
        ),
        ItemsModel(
            stringResource(R.string.category_fruits),
            stringResource(R.string.fruit_limao),
            R.mipmap.ic_fruta_limao_foreground
        ),
        ItemsModel(
            stringResource(R.string.category_fruits),
            stringResource(R.string.fruit_lixia),
            R.mipmap.ic_fruta_lixia_foreground
        ),
        ItemsModel(
            stringResource(R.string.category_fruits),
            stringResource(R.string.fruit_mamao),
            R.mipmap.ic_fruta_mamao_foreground
        ),
        ItemsModel(
            stringResource(R.string.category_fruits),
            stringResource(R.string.fruit_manga),
            R.mipmap.ic_fruta_manga_foreground
        ),
        ItemsModel(
            stringResource(R.string.category_fruits),
            stringResource(R.string.fruit_maracuja),
            R.mipmap.ic_fruta_maracuja_foreground
        ),
        ItemsModel(
            stringResource(R.string.category_fruits),
            stringResource(R.string.fruit_melao),
            R.mipmap.ic_fruta_melao_foreground
        ),
        ItemsModel(
            stringResource(R.string.category_fruits),
            stringResource(R.string.fruit_melancia),
            R.mipmap.ic_fruta_melancia_foreground
        ),
        ItemsModel(
            stringResource(R.string.category_fruits),
            stringResource(R.string.fruit_mexirica),
            R.mipmap.ic_fruta_mexirica_foreground
        ),
        ItemsModel(
            stringResource(R.string.category_fruits),
            stringResource(R.string.fruit_morango),
            R.mipmap.ic_fruta_morango_foreground
        ),
        ItemsModel(
            stringResource(R.string.category_fruits),
            stringResource(R.string.fruit_pera),
            R.mipmap.ic_fruta_pera_foreground
        ),
        ItemsModel(
            stringResource(R.string.category_fruits),
            stringResource(R.string.fruit_uva),
            R.mipmap.ic_fruta_uva_foreground
        ),

        )
}

@Composable
fun getVegetablesList(): List<ItemsModel> {
    return listOf(

        ItemsModel(
            stringResource(R.string.category_vegetables),
            stringResource(R.string.vegetables_alho),
            R.mipmap.ic_alho
        ),
        ItemsModel(
            stringResource(R.string.category_vegetables),
            stringResource(R.string.vegetables_abobora),
            R.mipmap.ic_abobora
        ),
        ItemsModel(
            stringResource(R.string.category_vegetables),
            stringResource(R.string.vegetables_abobrinha),
            R.mipmap.ic_abobrinha
        ),
        ItemsModel(
            stringResource(R.string.category_vegetables),
            stringResource(R.string.vegetables_batata),
            R.mipmap.ic_batata
        ),
        ItemsModel(
            stringResource(R.string.category_vegetables),
            stringResource(R.string.vegetables_batata_doce),
            R.mipmap.ic_batata_doce
        ),
        ItemsModel(
            stringResource(R.string.category_vegetables),
            stringResource(R.string.vegetables_berinjela),
            R.mipmap.ic_berinjela
        ),
        ItemsModel(
            stringResource(R.string.category_vegetables),
            stringResource(R.string.vegetables_cebola),
            R.mipmap.ic_cebola
        ),
        ItemsModel(
            stringResource(R.string.category_vegetables),
            stringResource(R.string.vegetables_cenoura),
            R.mipmap.ic_cenoura
        ),
        ItemsModel(
            stringResource(R.string.category_vegetables),
            stringResource(R.string.vegetables_inhame),
            R.mipmap.ic_inhame
        ),
        ItemsModel(
            stringResource(R.string.category_vegetables),
            stringResource(R.string.vegetables_jilo),
            R.mipmap.ic_jilo
        ),
        ItemsModel(
            stringResource(R.string.category_vegetables),
            stringResource(R.string.vegetables_mandioca),
            R.mipmap.ic_mandioca
        ),
        ItemsModel(
            stringResource(R.string.category_vegetables),
            stringResource(R.string.vegetables_mandinhoquinha),
            R.mipmap.ic_mandioquinha
        ),
        ItemsModel(
            stringResource(R.string.category_vegetables),
            stringResource(R.string.vegetables_pimentao),
            R.mipmap.ic_pimentao
        ),
        ItemsModel(
            stringResource(R.string.category_vegetables),
            stringResource(R.string.vegetables_tomate),
            R.mipmap.ic_tomate
        ),

        )
}

@Composable
fun getGreensList(): List<ItemsModel> {
    return listOf(
        ItemsModel(
            stringResource(id = R.string.category_greens),
            stringResource(id = R.string.greens_acelga),
            R.mipmap.ic_acelga_foreground
        ),
        ItemsModel(
            stringResource(id = R.string.category_greens),
            stringResource(id = R.string.greens_alface),
            R.mipmap.ic_alface
        ),
        ItemsModel(
            stringResource(id = R.string.category_greens),
            stringResource(id = R.string.greens_alegrin),
            R.mipmap.ic_alecrin_foreground
        ),
        ItemsModel(
            stringResource(id = R.string.category_greens),
            stringResource(id = R.string.greens_almeirao),
            R.mipmap.ic_almeirao
        ),
        ItemsModel(
            stringResource(id = R.string.category_greens),
            stringResource(id = R.string.greens_brocolis),
            R.mipmap.ic_brocolis_foreground
        ),
        ItemsModel(
            stringResource(id = R.string.category_greens),
            stringResource(id = R.string.greens_couve),
            R.mipmap.ic_couve_foreground
        ),
        ItemsModel(
            stringResource(id = R.string.category_greens),
            stringResource(id = R.string.greens_chicoria),
            R.mipmap.ic_chicoria_foreground
        ),
        ItemsModel(
            stringResource(id = R.string.category_greens),
            stringResource(id = R.string.greens_coentro),
            R.mipmap.ic_coentro
        ),
        ItemsModel(
            stringResource(id = R.string.category_greens),
            stringResource(id = R.string.greens_couve),
            R.mipmap.ic_couve_flor_foreground
        ),
        ItemsModel(
            stringResource(id = R.string.category_greens),
            stringResource(id = R.string.greens_espinafre),
            R.mipmap.ic_espinafre
        ),
        ItemsModel(
            stringResource(id = R.string.category_greens),
            stringResource(id = R.string.greens_hortela),
            R.mipmap.ic_hortela_foreground
        ),
        ItemsModel(
            stringResource(id = R.string.category_greens),
            stringResource(id = R.string.greens_manjericao),
            R.mipmap.ic_manjericao_foreground
        ),
        ItemsModel(
            stringResource(id = R.string.category_greens),
            stringResource(id = R.string.greens_maxixe),
            R.mipmap.ic_maxixe
        ),
        ItemsModel(
            stringResource(id = R.string.category_greens),
            stringResource(id = R.string.greens_pepino),
            R.mipmap.ic_pepino
        ),
        ItemsModel(
            stringResource(id = R.string.category_greens),
            stringResource(id = R.string.greens_rucula),
            R.mipmap.ic_rucula
        ),
        ItemsModel(
            stringResource(id = R.string.category_greens),
            stringResource(id = R.string.greens_salsao),
            R.mipmap.ic_salsa
        ),
        ItemsModel(
            stringResource(id = R.string.category_greens),
            stringResource(id = R.string.greens_salsinha),
            R.mipmap.ic_salsinha
        ),
        ItemsModel(
            stringResource(id = R.string.category_greens),
            stringResource(id = R.string.greens_tomilho),
            R.mipmap.ic_tomilho
        ),
    )
}

@Composable
fun getMeatList(): List<ItemsModel> {
    return listOf(

        ItemsModel(
            stringResource(R.string.category_meats),
            stringResource(R.string.meats_acem),
            R.mipmap.ic_acem
        ),
        ItemsModel(
            stringResource(R.string.category_meats),
            stringResource(R.string.meats_agulha),
            R.mipmap.ic_agulha
        ),
        ItemsModel(
            stringResource(R.string.category_meats),
            stringResource(R.string.meats_alcatra),
            R.mipmap.ic_alcatra
        ),
        ItemsModel(
            stringResource(R.string.category_meats),
            stringResource(R.string.meats_braco),
            R.mipmap.ic_braco
        ),
        ItemsModel(
            stringResource(R.string.category_meats),
            stringResource(R.string.meats_cochao),
            R.mipmap.ic_cochao
        ),
        ItemsModel(
            stringResource(R.string.category_meats),
            stringResource(R.string.meats_contra),
            R.mipmap.ic_contra
        ),
        ItemsModel(
            stringResource(R.string.category_meats),
            stringResource(R.string.meats_cupim),
            R.mipmap.ic_cupim
        ),
        ItemsModel(
            stringResource(R.string.category_meats),
            stringResource(R.string.meats_dianteiro),
            R.mipmap.ic_dianteiro
        ),
        ItemsModel(
            stringResource(R.string.category_meats),
            stringResource(R.string.meats_lagarto),
            R.mipmap.ic_lagarto
        ),
        ItemsModel(
            stringResource(R.string.category_meats),
            stringResource(R.string.meats_linguica),
            R.mipmap.ic_linguica
        ),
        ItemsModel(
            stringResource(R.string.category_meats),
            stringResource(R.string.meats_maminha),
            R.mipmap.ic_maminha
        ),
        ItemsModel(
            stringResource(R.string.category_meats),
            stringResource(R.string.meats_mignon),
            R.mipmap.ic_mignon
        ),
        ItemsModel(
            stringResource(R.string.category_meats),
            stringResource(R.string.meats_musculo),
            R.mipmap.ic_musculo
        ),
        ItemsModel(
            stringResource(R.string.category_meats),
            stringResource(R.string.meats_patinho),
            R.mipmap.ic_patinho
        ),
        ItemsModel(
            stringResource(R.string.category_meats),
            stringResource(R.string.meats_picanha),
            R.mipmap.ic_picanha
        ),

        )
}

@Composable
fun getFishList(): List<ItemsModel> {
    return listOf(

        ItemsModel(
            stringResource(R.string.category_fish),
            stringResource(R.string.fish_camarao),
            R.mipmap.ic_camarao
        ),
        ItemsModel(
            stringResource(R.string.category_fish),
            stringResource(R.string.fish_caranguejo),
            R.mipmap.ic_caranguejo
        ),
        ItemsModel(
            stringResource(R.string.category_fish),
            stringResource(R.string.fish_dourado),
            R.mipmap.ic_dourado
        ),
        ItemsModel(
            stringResource(R.string.category_fish),
            stringResource(R.string.fish_namorado),
            R.mipmap.ic_namorado
        ),
        ItemsModel(
            stringResource(R.string.category_fish),
            stringResource(R.string.fish_merlusa),
            R.mipmap.ic_merlusa
        ),
        ItemsModel(
            stringResource(R.string.category_fish),
            stringResource(R.string.fish_pescada),
            R.mipmap.ic_pescada
        ),
        ItemsModel(
            stringResource(R.string.category_fish),
            stringResource(R.string.fish_pescada_branca),
            R.mipmap.ic_pescada_branca
        ),
        ItemsModel(
            stringResource(R.string.category_fish),
            stringResource(R.string.fish_porquinho),
            R.mipmap.ic_porquinho
        ),
        ItemsModel(
            stringResource(R.string.category_fish),
            stringResource(R.string.fish_salmao),
            R.mipmap.ic_salmao
        ),
        ItemsModel(
            stringResource(R.string.category_fish),
            stringResource(R.string.fish_tilapia),
            R.mipmap.ic_tilapia
        ),
        ItemsModel(
            stringResource(R.string.category_fish),
            stringResource(R.string.fish_truta),
            R.mipmap.ic_truta
        ),
    )
}

@Composable
fun getUtilitiesList(): List<ItemsModel> {
    return listOf(
        ItemsModel(
            stringResource(R.string.category_utensils),
            stringResource(R.string.utensils_brinquedo),
            R.mipmap.ic_brinquedo
        ),
        ItemsModel(
            stringResource(R.string.category_utensils),
            stringResource(R.string.utensils_carrinho),
            R.mipmap.ic_carrinho
        ),
        ItemsModel(
            stringResource(R.string.category_utensils),
            stringResource(R.string.utensils_esponja),
            R.mipmap.ic_esponja
        ),
        ItemsModel(
            stringResource(R.string.category_utensils),
            stringResource(R.string.utensils_pano_prato),
            R.mipmap.ic_pano_prato
        ),
        ItemsModel(
            stringResource(R.string.category_utensils),
            stringResource(R.string.utensils_roda_carrinho),
            R.mipmap.ic_roda_carrinho
        ),
        ItemsModel(
            stringResource(R.string.category_utensils),
            stringResource(R.string.utensils_vassoura),
            R.mipmap.ic_vassoura
        ),
        ItemsModel(
            stringResource(R.string.category_utensils),
            stringResource(R.string.utensils_panela),
            R.mipmap.ic_panelas_foreground
        ),
    )
}

@Composable
fun getSnacksList(): List<ItemsModel> {
    return listOf(
        ItemsModel(
            stringResource(R.string.category_snacks),
            stringResource(R.string.snacks_agua_coco),
            R.mipmap.ic_agua_coco
        ),
        ItemsModel(
            stringResource(R.string.category_snacks),
            stringResource(R.string.snacks_caldo_cana),
            R.mipmap.ic_caldo_cana
        ),
        ItemsModel(
            stringResource(R.string.category_snacks),
            stringResource(R.string.snacks_coxinha),
            R.mipmap.ic_coxinha
        ),
        ItemsModel(
            stringResource(R.string.category_snacks),
            stringResource(R.string.snacks_espetos),
            R.mipmap.ic_espetos
        ),
        ItemsModel(
            stringResource(R.string.category_snacks),
            stringResource(R.string.snacks_kibe),
            R.mipmap.ic_kibe
        ),
        ItemsModel(
            stringResource(R.string.category_snacks),
            stringResource(R.string.snacks_refrigerante),
            R.mipmap.ic_refri
        ),
        ItemsModel(
            stringResource(R.string.category_snacks),
            stringResource(R.string.snacks_pastel),
            R.mipmap.ic_pastelzinho
        ),
        ItemsModel(
            stringResource(R.string.category_snacks),
            stringResource(R.string.snacks_pastel),
            R.mipmap.ic_pastel
        ),

        )

}

@Composable
fun getCondimentsList(): List<ItemsModel> {
    return listOf(
        ItemsModel(
            stringResource(R.string.category_condiments),
            stringResource(R.string.condiments_acafrao),
            R.mipmap.ic_acafrao
        ),
        ItemsModel(
            stringResource(R.string.category_condiments),
            stringResource(R.string.condiments_canela),
            R.mipmap.ic_canela
        ),
        ItemsModel(
            stringResource(R.string.category_condiments),
            stringResource(R.string.condiments_colorau),
            R.mipmap.ic_colorau
        ),
        ItemsModel(
            stringResource(R.string.category_condiments),
            stringResource(R.string.condiments_louros),
            R.mipmap.ic_louros
        ),
        ItemsModel(
            stringResource(R.string.category_condiments),
            stringResource(R.string.condiments_noz_moscada),
            R.mipmap.ic_noz_moscada
        ),
        ItemsModel(
            stringResource(R.string.category_condiments),
            stringResource(R.string.condiments_oregano),
            R.mipmap.ic_oregano
        ),
        ItemsModel(
            stringResource(R.string.category_condiments),
            stringResource(R.string.condiments_pimenta_reino),
            R.mipmap.ic_pimenta_reino
        ),
        ItemsModel(
            stringResource(R.string.category_condiments),
            stringResource(R.string.condiments_paprika),
            R.mipmap.ic_paprika
        ),
    )
}