package com.example.afimdefeirax.Model

import com.example.afimdefeirax.R

data class ItemsModel(
    val name: String,
    val imageResId: Int
)

val frutasList= listOf(
    ItemsModel("Frutas", R.mipmap.ic_fruta_goiaba_item),
    ItemsModel("Frutas", R.mipmap.ic_fruta_manga_item),
    ItemsModel("Frutas", R.mipmap.ic_abacaxi),
    ItemsModel("Frutas", R.mipmap.ic_uva),
    ItemsModel("Frutas", R.mipmap.ic_laranja),
    ItemsModel("Frutas", R.mipmap.ic_kiwi),
    ItemsModel("Frutas", R.mipmap.ic_banana),
    ItemsModel("Frutas", R.mipmap.ic_limao),
    ItemsModel("Frutas", R.mipmap.ic_ameixa),
    ItemsModel("Frutas", R.mipmap.ic_caju_),
    ItemsModel("Frutas", R.mipmap.ic_melancia_foreground),
    ItemsModel("Frutas", R.mipmap.ic_figo),
    ItemsModel("Frutas", R.mipmap.ic_maca),
    ItemsModel("Frutas", R.mipmap.ic_jaca),
)

val CarnesList= listOf(
    ItemsModel("Carnes", R.mipmap.ic_acem),
    ItemsModel("Carnes", R.mipmap.ic_agulha),
    ItemsModel("Carnes", R.mipmap.ic_alcatra),
    ItemsModel("Carnes", R.mipmap.ic_braco),
    ItemsModel("Carnes", R.mipmap.ic_cochao),
    ItemsModel("Carnes", R.mipmap.ic_contra),
    ItemsModel("Carnes", R.mipmap.ic_cupim),
    ItemsModel("Carnes", R.mipmap.ic_dianteiro),
    ItemsModel("Carnes", R.mipmap.ic_lagarto),
    ItemsModel("Carnes", R.mipmap.ic_linguica),
    ItemsModel("Carnes", R.mipmap.ic_maminha),
    ItemsModel("Carnes", R.mipmap.ic_mignon),
    ItemsModel("Carnes", R.mipmap.ic_musculo),
    ItemsModel("Carnes", R.mipmap.ic_patinho),
    ItemsModel("Carnes", R.mipmap.ic_picanha),
)

val peixesList = listOf(
    ItemsModel("Peixes", R.mipmap.ic_camarao),
    ItemsModel("Peixes", R.mipmap.ic_caranguejo),
    ItemsModel("Peixes", R.mipmap.ic_dourado),
    ItemsModel("Peixes", R.mipmap.ic_namorado),
    ItemsModel("Peixes", R.mipmap.ic_merlusa),
    ItemsModel("Peixes", R.mipmap.ic_pescada),
    ItemsModel("Peixes", R.mipmap.ic_pescada_branca),
    ItemsModel("Peixes", R.mipmap.ic_porquinho),
    ItemsModel("Peixes", R.mipmap.ic_salmao),
    ItemsModel("Peixes", R.mipmap.ic_tilapia),
    ItemsModel("Peixes", R.mipmap.ic_truta),


)

val LegumesList= listOf(
    ItemsModel("Legumes", R.mipmap.ic_alho),
    ItemsModel("Legumes", R.mipmap.ic_abobora),
    ItemsModel("Legumes", R.mipmap.ic_abobrinha),
    ItemsModel("Legumes", R.mipmap.ic_batata),
    ItemsModel("Legumes", R.mipmap.ic_batata_doce),
    ItemsModel("Legumes", R.mipmap.ic_berinjela),
    ItemsModel("Legumes", R.mipmap.ic_cebola),
    ItemsModel("Legumes", R.mipmap.ic_cenoura),
    ItemsModel("Legumes", R.mipmap.ic_milho),
    ItemsModel("Legumes", R.mipmap.ic_inhame),
    ItemsModel("Legumes", R.mipmap.ic_jilo),
    ItemsModel("Legumes", R.mipmap.ic_mandioca),
    ItemsModel("Legumes", R.mipmap.ic_mandioquinha),
    ItemsModel("Legumes", R.mipmap.ic_pimentao),
    ItemsModel("Legumes", R.mipmap.ic_tomate),
)

val verdurasList= listOf(
    ItemsModel("Verduras", R.mipmap.ic_alface),
    ItemsModel("Verduras", R.mipmap.ic_brocolis),
    ItemsModel("Verduras", R.mipmap.ic_broto),
    ItemsModel("Verduras", R.mipmap.ic_couve),
    ItemsModel("Verduras", R.mipmap.ic_chicoria),
    ItemsModel("Verduras", R.mipmap.ic_coentro),
    ItemsModel("Verduras", R.mipmap.ic_couveflor),
    ItemsModel("Verduras", R.mipmap.ic_espinafre),
    ItemsModel("Verduras", R.mipmap.ic_hortelaa),
    ItemsModel("Verduras", R.mipmap.ic_louros),
    ItemsModel("Verduras", R.mipmap.ic_manjericao),
    ItemsModel("Verduras", R.mipmap.ic_maxixe),
    ItemsModel("Verduras", R.mipmap.ic_pepino),
    ItemsModel("Verduras", R.mipmap.ic_rucula),
    ItemsModel("Verduras", R.mipmap.ic_salsa),
    ItemsModel("Verduras", R.mipmap.ic_salsinha),
    ItemsModel("Verduras", R.mipmap.ic_tomilho),

)

val utensilhosList= listOf(
    ItemsModel("Utensilhos", R.mipmap.ic_brinquedo),
    ItemsModel("Utensilhos", R.mipmap.ic_carrinho),
    ItemsModel("Utensilhos", R.mipmap.ic_esponja),
    ItemsModel("Utensilhos", R.mipmap.ic_pano_prato),
    ItemsModel("Utensilhos", R.mipmap.ic_roda_carrinho),
    ItemsModel("Utensilhos", R.mipmap.ic_vassoura),
    ItemsModel("Utensilhos", R.mipmap.ic_utensilios),

)

val lanchesList = listOf(
    ItemsModel("Lanches", R.mipmap.ic_caldo_cana),
    ItemsModel("Lanches", R.mipmap.ic_coxinha),
    ItemsModel("Lanches", R.mipmap.ic_espetos),
    ItemsModel("Lanches", R.mipmap.ic_kibe),
    ItemsModel("Lanches", R.mipmap.ic_refri),
    ItemsModel("Lanches", R.mipmap.ic_pastel),
)



val temperosList = listOf(
    ItemsModel("Temperos", R.mipmap.ic_acafrao),
    ItemsModel("Temperos", R.mipmap.ic_canela),
    ItemsModel("Temperos", R.mipmap.ic_colorau),
    ItemsModel("Temperos", R.mipmap.ic_louros),
    ItemsModel("Temperos", R.mipmap.ic_noz_moscada),
    ItemsModel("Temperos", R.mipmap.ic_oregano),
    ItemsModel("Temperos", R.mipmap.ic_pimenta_reino),
    ItemsModel("Temperos", R.mipmap.ic_paprika),



)

val produtosList=listOf(
    frutasList ,
    LegumesList,
    verdurasList,
    CarnesList ,
    peixesList,
    temperosList,
    lanchesList,
    utensilhosList,
)