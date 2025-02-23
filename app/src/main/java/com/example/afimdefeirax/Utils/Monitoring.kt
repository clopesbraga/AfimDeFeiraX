package com.example.afimdefeirax.Utils


object Monitoring{

    object Button{
        const val PRESSED = "press"
        const val CLICKED = "clicked"
    }
    object Actions{
        const val SELECTED = "selected"
    }

    object Login{

        const val LOGIN_SCREEN = "login"
        const val LOGIN_CLASS = "LoginViewModel"
        const val LOGIN_SUCCESS = "login_success"
        const val LOGIN_BUTTON_CLICKED= "login_button_clicked"
        const val LOGIN_FAILED = "login_failed"
        const val LOGIN_ERROR = "login_error"
        const val  LOGIN_PROCESS="login process"
   }

    object Main{
        const val MAIN_SCREEN = "main"
    }

    object Map{

        const val MAP_SCREEN="map"
        const val MAP_MARKER_LOCALIZATION = MAP_SCREEN+"/starts-user-marker"
        const val MAP_MARKER_FEIRAS       = MAP_SCREEN+"/starts-feiras-marker"
        const val FLOATING_BUTTON_PRESSED = MAP_SCREEN+"/floating_button"+Button.PRESSED
        const val SHOW_MENU_NEIGHBORS     = MAP_SCREEN+"/mostra menu"
        const val CITY_SELECTED           = MAP_SCREEN+"city"+Actions.SELECTED
        const val NEIGHBOR_SELECTED       = MAP_SCREEN+"neighbor"+Actions.SELECTED
    }




}