package com.example.practica16_danielcuarental.Provincias

class Provincia {
    var codigoProvincia : Int
    var nombreProvincia : String

    constructor() {
        codigoProvincia = 0
        nombreProvincia = ""
    }

    constructor(codigoProvincia: Int, nombreProvincia: String) {
        this.codigoProvincia = codigoProvincia
        this.nombreProvincia = nombreProvincia
    }
}