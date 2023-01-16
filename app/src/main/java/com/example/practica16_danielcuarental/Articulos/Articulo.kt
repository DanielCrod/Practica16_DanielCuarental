package com.example.practica16_danielcuarental.Articulos

class Articulo : java.io.Serializable{
    var codigoArticulo : String
    var nombreArticulo : String
    var pvp : Double
    var iva : Int
    var proveedor : String

    constructor() {
        codigoArticulo = ""
        nombreArticulo = ""
        pvp = 0.0
        iva = 0
        proveedor = ""
    }

    constructor(codigoArticulo:String, nombreArticulo:String, pvp: Double, iva:Int, proveedor:String) {
        this.codigoArticulo = codigoArticulo
        this.nombreArticulo = nombreArticulo
        this.pvp = pvp
        this.iva = iva
        this.proveedor = proveedor
    }

    override fun toString(): String {
        return "Articulo(codigoArticulo='$codigoArticulo', nombreArticulo='$nombreArticulo', pvp=$pvp, iva=$iva, proveedor='$proveedor')"
    }


}