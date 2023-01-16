package com.example.practica16_danielcuarental

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.practica16_danielcuarental.Articulos.Articulo
import org.w3c.dom.Text

class DetallesActivity : AppCompatActivity() {

    private lateinit var producto : TextView
    private lateinit var txtProducto : TextView
    private lateinit var codigo : TextView
    private lateinit var txtCodigo : TextView
    private lateinit var nombre : TextView
    private lateinit var txtNombre : TextView
    private lateinit var pvp : TextView
    private lateinit var txtPVP : TextView
    private lateinit var iva : TextView
    private lateinit var txtIVA : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles)

       /*if(intent.hasExtra("Articulo")) {
            val articulo :Articulo = intent.getSerializableExtra("Articulo") as Articulo
            txtCodigo.setText(articulo.codigoArticulo)
            txtNombre.setText(articulo.nombreArticulo)
            txtPVP.setText(articulo.pvp)
            txtIVA.setText(articulo.proveedor)
        }*/
    }
}