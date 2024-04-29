package com.example.afimdefeirax.Utils

import java.util.Calendar
import java.util.GregorianCalendar

class DeviceCurrentTime {


   private lateinit var  diasemana: String
   private val semana: GregorianCalendar = GregorianCalendar()

    fun trazSemana():String{

        when(semana.get(Calendar.DAY_OF_WEEK)){

            Calendar.MONDAY -> diasemana = "SEG"
            Calendar.TUESDAY -> diasemana = "TER"
            Calendar.WEDNESDAY -> diasemana = "QUA"
            Calendar.THURSDAY -> diasemana = "QUI"
            Calendar.FRIDAY -> diasemana = "SEX"
            Calendar.SATURDAY -> diasemana = "SAB"
            Calendar.SUNDAY -> diasemana = "DOM"
        }

        return diasemana

    }

}