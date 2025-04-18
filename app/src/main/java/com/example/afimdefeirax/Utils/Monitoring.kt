package com.example.afimdefeirax.Utils


object Monitoring {

    const val FLOATING_BUTTON="_floating_button_"
    const val ITEM="_item_"
    const val STARTS="_starts"

    object Button {
        const val PRESSED = "press"
        const val CLICKED = "clicked"
    }

    object Actions {
        const val SELECTED = "selected"
    }

    object Login {

        const val LOGIN_SCREEN = "login"
        const val LOGIN_SCREEN_START = LOGIN_SCREEN +STARTS
        const val CREATE_USER_SUCESS = LOGIN_SCREEN+"_user_created"
        const val LOGIN_SUCCESS = "login_success"
        const val LOGIN_BUTTON_CLICKED = "login_button_"+Button.CLICKED
        const val LOGIN_FAILED = "login_failed"
        const val LOGIN_ERROR = "login_error"
        const val LOGIN_PROCESS = "login process"
    }

    object Main {
        const val MAIN_SCREEN = "main"
        const val MAIN_SCREEN_START = MAIN_SCREEN +STARTS
    }

    object Map {

        const val MAP_SCREEN = "map"
        const val MAP_SCREEN_START = MAP_SCREEN +STARTS
        const val MAP_MARKER_LOCALIZATION = MAP_SCREEN + "_starts_user_marker"
        const val MAP_MARKER_FEIRAS = MAP_SCREEN + "_starts_feiras_marker"
        const val MAP_FLOATING_BUTTON_PRESSED = MAP_SCREEN +FLOATING_BUTTON+ Button.PRESSED
        const val SHOW_MENU_NEIGHBORS = MAP_SCREEN + "_show_menu"
        const val CITY_SELECTED = MAP_SCREEN + "_city_" + Actions.SELECTED
        const val NEIGHBOR_SELECTED = MAP_SCREEN + "_neighbor_" + Actions.SELECTED
    }

    object Product {
        const val PRODUCT_SCREEN = "product"
        const val PRODUCT_SCREEN_START = PRODUCT_SCREEN +STARTS
        const val PRODUCT_FLOATING_BUTTON_PRESSED =PRODUCT_SCREEN+FLOATING_BUTTON+Button.PRESSED
        const val PRODUCT_LOADING_LIST = PRODUCT_SCREEN + "_loading_list"
        const val PRODUCT_LOADING_LIST_ERROR = PRODUCT_SCREEN + "_loading_list_error"
        const val PRODUCT_REQUEST = PRODUCT_SCREEN + "_send_requestr"
        const val PRODUCT_REQUEST_ERROR = PRODUCT_SCREEN + "_request_error"
        const val PRODUCT_SAVE = PRODUCT_SCREEN + "_save"
        const val PRODUCT_SAVE_ERROR = PRODUCT_SCREEN + "_save_error"
        const val PRODUCT_ITEM_SELECTED = PRODUCT_SCREEN+ITEM+Button.PRESSED
        const val PRODUCT_CREATE_LIST_ERROR = PRODUCT_SCREEN + "_take_error"
        const val PRODUCT_REMOVE = PRODUCT_SCREEN+"_remove"
        const val PRODUCT_REMOVE_ERROR = PRODUCT_SCREEN + "_remove_error"
    }

    object Historic {

        const val HISTORIC_SCREEN = "hist"
        const val HISTORIC_SCREEN_START = HISTORIC_SCREEN +STARTS
        const val STARTS_HISTORIC_LOAD = HISTORIC_SCREEN + "_loading_historic_list"
        const val SAVE_HISTORIC = HISTORIC_SCREEN + "_save_historic_list"
        const val UPDATE_HISTORIC = HISTORIC_SCREEN + "_update_historic_list"
        const val ERROR_SAVE_HISTORIC = HISTORIC_SCREEN + "_error_save_historic_list"
        const val ERROR_UPDATE_HISTORIC = HISTORIC_SCREEN + "_error_update_historic_list"
    }

    object AboutApp{
        const val ABOUTAPP_SCREEN ="aboutApp"
        const val ABOUTAPP_START =ABOUTAPP_SCREEN+STARTS
        const val ABOUTAPP_VERSION_ERROR = ABOUTAPP_SCREEN+"erro_mostrar_versao"
        const val SHOW_APP_VERSION = "mostrar_versao"
    }

    object TermsApp{
        const val  TERMS_OF_APP_SCREEN="terms"
        const val TERMS_START = TERMS_OF_APP_SCREEN+STARTS
    }

}