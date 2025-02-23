package com.example.afimdefeirax.Utils


object Monitoring{

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
        const val MAP_MARKER_LOCALIZATION = "map/starts-user-marker"
        const val MAP_MARKER_FEIRAS ="map/starts-feiras-marker"

        const val FLOATING_BUTTON_PRESSED = "map/pressionado-button-localizacao"
        const val SHOW_MENU_NEIGHBORS ="map/mostra menu"


    }



}