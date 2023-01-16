package com.example.practica16_danielcuarental.Articulos

import android.provider.BaseColumns

object ArticulosContract : BaseColumns {
    const val TABLE_NAME = "Articulos"
    const val CODIGOARTICULO = "codigoArticulo"
    const val NOMBREARTICULO = "nombreArticulo"
    const val PVP = "pvp"
    const val IVA = "iva"
    const val PROVEEDOR = "proveedor"
}