package com.example.afimdefeirax.Model

import com.example.afimdefeirax.R

data class ItemsModel(
    val categoria: String,
    val name: String,
    val imageResId: Int
)

val frutasList = listOf(
    ItemsModel("Frutas", "Goiaba", R.mipmap.ic_fruta_goiaba_item),
    ItemsModel("Frutas", "Manga", R.mipmap.ic_fruta_manga_item),
    ItemsModel("Frutas", "Abacaxi", R.mipmap.ic_abacaxi),
    ItemsModel("Frutas", "Uva", R.mipmap.ic_uva),
    ItemsModel("Frutas", "Laranja", R.mipmap.ic_laranja),
    ItemsModel("Frutas", "Jaca", R.mipmap.ic_jaca),
)

val CarnesList = listOf(
    ItemsModel("Carnes", "Acem", R.mipmap.ic_acem),
    ItemsModel("Carnes", "Agulha", R.mipmap.ic_agulha),
    ItemsModel("Carnes", "Alcatra", R.mipmap.ic_alcatra),
    ItemsModel("Carnes", "Braço", R.mipmap.ic_braco),
    ItemsModel("Carnes", "Cochão", R.mipmap.ic_cochao),
    ItemsModel("Carnes", "Contra-File", R.mipmap.ic_contra),
    ItemsModel("Carnes", "Cupim", R.mipmap.ic_cupim),
    ItemsModel("Carnes", "Dianteiro", R.mipmap.ic_dianteiro),
    ItemsModel("Carnes", "Lagarto", R.mipmap.ic_lagarto),
    ItemsModel("Carnes", "Linguiça", R.mipmap.ic_linguica),
    ItemsModel("Carnes", "Maminha", R.mipmap.ic_maminha),
    ItemsModel("Carnes", "Mignon", R.mipmap.ic_mignon),
    ItemsModel("Carnes", "Musculo", R.mipmap.ic_musculo),
    ItemsModel("Carnes", "Patinho", R.mipmap.ic_patinho),
    ItemsModel("Carnes", "Picanha", R.mipmap.ic_picanha),
)

val peixesList = listOf(
    ItemsModel("Peixes", "Camarao", R.mipmap.ic_camarao),
    ItemsModel("Peixes", "Caranguejo", R.mipmap.ic_caranguejo),
    ItemsModel("Peixes", "Dourado", R.mipmap.ic_dourado),
    ItemsModel("Peixes", "Namorado", R.mipmap.ic_namorado),
    ItemsModel("Peixes", "Merlusa", R.mipmap.ic_merlusa),
    ItemsModel("Peixes", "Pescada", R.mipmap.ic_pescada),
    ItemsModel("Peixes", "Pescada-Branca", R.mipmap.ic_pescada_branca),
    ItemsModel("Peixes", "Porquinho", R.mipmap.ic_porquinho),
    ItemsModel("Peixes", "Salmao", R.mipmap.ic_salmao),
    ItemsModel("Peixes", "Tilapia", R.mipmap.ic_tilapia),
    ItemsModel("Peixes", "Truta", R.mipmap.ic_truta),


    )

val LegumesList = listOf(
    ItemsModel("Legumes", "Alho", R.mipmap.ic_alho),
    ItemsModel("Legumes", "Abobora", R.mipmap.ic_abobora),
    ItemsModel("Legumes", "Abobrinha", R.mipmap.ic_abobrinha),
    ItemsModel("Legumes", "Batata", R.mipmap.ic_batata),
    ItemsModel("Legumes", "Batata-doce", R.mipmap.ic_batata_doce),
    ItemsModel("Legumes", "Berinjela", R.mipmap.ic_berinjela),
    ItemsModel("Legumes", "Cebola", R.mipmap.ic_cebola),
    ItemsModel("Legumes", "Cenoura", R.mipmap.ic_cenoura),
    ItemsModel("Legumes", "Milho", R.mipmap.ic_milho),
    ItemsModel("Legumes", "Inhame", R.mipmap.ic_inhame),
    ItemsModel("Legumes", "Jilo", R.mipmap.ic_jilo),
    ItemsModel("Legumes", "Mandioca", R.mipmap.ic_mandioca),
    ItemsModel("Legumes", "Mandinhoquinha", R.mipmap.ic_mandioquinha),
    ItemsModel("Legumes", "Pimentao", R.mipmap.ic_pimentao),
    ItemsModel("Legumes", "Tomate", R.mipmap.ic_tomate),
)

val verdurasList = listOf(
    ItemsModel("Verduras", "Alface", R.mipmap.ic_alface),
    ItemsModel("Verduras", "Brocolis", R.mipmap.ic_brocolis),
    ItemsModel("Verduras", "Broto", R.mipmap.ic_broto),
    ItemsModel("Verduras", "Couve", R.mipmap.ic_couve),
    ItemsModel("Verduras", "Chicoria", R.mipmap.ic_chicoria),
    ItemsModel("Verduras", "Coentro", R.mipmap.ic_coentro),
    ItemsModel("Verduras", "Couve-flor", R.mipmap.ic_couveflor),
    ItemsModel("Verduras", "Espinafre", R.mipmap.ic_espinafre),
    ItemsModel("Verduras", "Hortelaa", R.mipmap.ic_hortelaa),
    ItemsModel("Verduras", "Louros", R.mipmap.ic_louros),
    ItemsModel("Verduras", "Manjericao", R.mipmap.ic_manjericao),
    ItemsModel("Verduras", "Maxixe", R.mipmap.ic_maxixe),
    ItemsModel("Verduras", "Pepino", R.mipmap.ic_pepino),
    ItemsModel("Verduras", "Rucula", R.mipmap.ic_rucula),
    ItemsModel("Verduras", "Salsa", R.mipmap.ic_salsa),
    ItemsModel("Verduras", "Salsinha", R.mipmap.ic_salsinha),
    ItemsModel("Verduras", "Tomilho", R.mipmap.ic_tomilho),

    )

val utensilhosList = listOf(
    ItemsModel("Utensilhos", "Brinquedo", R.mipmap.ic_brinquedo),
    ItemsModel("Utensilhos", "Carrinho", R.mipmap.ic_carrinho),
    ItemsModel("Utensilhos", "Esponja", R.mipmap.ic_esponja),
    ItemsModel("Utensilhos", "Pano-de-Prato", R.mipmap.ic_pano_prato),
    ItemsModel("Utensilhos", "Roda-Carrinho", R.mipmap.ic_roda_carrinho),
    ItemsModel("Utensilhos", "Vassoura", R.mipmap.ic_vassoura),
    ItemsModel("Utensilhos", "Utensilios", R.mipmap.ic_utensilios),

    )

val lanchesList = listOf(
    ItemsModel("Lanches", "Caldo-Cana", R.mipmap.ic_caldo_cana),
    ItemsModel("Lanches", "Coxinha", R.mipmap.ic_coxinha),
    ItemsModel("Lanches", "Espetos", R.mipmap.ic_espetos),
    ItemsModel("Lanches", "Kibe", R.mipmap.ic_kibe),
    ItemsModel("Lanches", "Refrigerante", R.mipmap.ic_refri),
    ItemsModel("Lanches", "Pastel", R.mipmap.ic_pastel),
)


val temperosList = listOf(
    ItemsModel("Temperos", "Acafrao", R.mipmap.ic_acafrao),
    ItemsModel("Temperos", "Canela", R.mipmap.ic_canela),
    ItemsModel("Temperos", "Colorau", R.mipmap.ic_colorau),
    ItemsModel("Temperos", "Louros", R.mipmap.ic_louros),
    ItemsModel("Temperos", "Moscada", R.mipmap.ic_noz_moscada),
    ItemsModel("Temperos", "Oregano", R.mipmap.ic_oregano),
    ItemsModel("Temperos", "Pimenta-do-reino", R.mipmap.ic_pimenta_reino),
    ItemsModel("Temperos", "Paprika", R.mipmap.ic_paprika),


    )

val produtosList = listOf(
    frutasList,
    LegumesList,
    verdurasList,
    CarnesList,
    peixesList,
    temperosList,
    lanchesList,
    utensilhosList,
)