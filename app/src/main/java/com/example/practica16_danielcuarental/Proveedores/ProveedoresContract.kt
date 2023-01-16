package com.example.practica16_danielcuarental.Proveedores

import android.provider.BaseColumns

object ProveedoresContract: BaseColumns {
    const val TABLE_NAME = "Proveedores"
    const val CODIGOPROVEEDOR = "codigoProveedor"
    const val NOMBREPROVEEDOR = "nombreProveedor"
    const val DIRECCION = "direccion"
    const val TELEFONO = "telefono"
    const val PROVINCIA = "provincia"
}