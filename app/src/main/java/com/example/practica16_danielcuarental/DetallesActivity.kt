package com.example.practica16_danielcuarental

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.ims.ImsMmTelManager
import android.widget.TextView
import com.example.practica16_danielcuarental.Articulos.Articulo
import com.example.practica16_danielcuarental.Proveedores.Proveedor
import com.example.practica16_danielcuarental.Proveedores.ProveedoresContract
import com.example.practica16_danielcuarental.Provincias.ProvinciasContract
import com.example.practica16_danielcuarental.SQL.SqliteHelper
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

    private lateinit var codProveedor: TextView
    private lateinit var nombreProveedor: TextView
    private lateinit var direccionProveedor: TextView
    private lateinit var telefono: TextView
    private lateinit var provincia: TextView
    private lateinit var txtCodProveedor: TextView
    private lateinit var txtNombreProveedor: TextView
    private lateinit var txtDireccion: TextView
    private lateinit var txtTelefono: TextView
    private lateinit var txtProvincia: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles)
        txtCodigo = findViewById(R.id.txtCodigo)
        txtNombre = findViewById(R.id.txtNombre)
        producto = findViewById(R.id.producto)
        txtProducto = findViewById(R.id.txtProducto)
        codigo = findViewById(R.id.codigo)
        nombre = findViewById(R.id.Nombre)
        pvp = findViewById(R.id.PVP)
        txtPVP = findViewById(R.id.txtPVP)
        iva = findViewById(R.id.IVA)
        codProveedor = findViewById(R.id.codProveedor)
        nombreProveedor = findViewById(R.id.nombreProveedor)
        direccionProveedor = findViewById(R.id.direccion)
        telefono = findViewById(R.id.telefono)
        provincia = findViewById(R.id.provincia)
        txtCodProveedor = findViewById(R.id.txtCodigoProveedor)
        txtNombreProveedor = findViewById(R.id.txtNombreProveedor)
        txtDireccion = findViewById(R.id.txtDireccion)
        txtTelefono = findViewById(R.id.txtTelefono)
        txtProvincia = findViewById(R.id.txtProvincia)


       if(intent.hasExtra("Articulo")) {
            val articulo :Articulo = intent.getSerializableExtra("Articulo") as Articulo
            txtProducto.setText(articulo.codigoArticulo)
            txtCodigo.setText(articulo.nombreArticulo)
            txtNombre.setText(articulo.pvp.toString())
            txtPVP.setText(articulo.iva.toString())


        val helper = SqliteHelper(this)
        val cursor = helper.consultaMultiple(articulo.nombreArticulo)

        if(cursor.getCount() >= 1) {
            while (cursor.moveToNext()){
                txtCodProveedor.setText(cursor.getString(cursor.getColumnIndexOrThrow(ProveedoresContract.CODIGOPROVEEDOR)))
                txtNombreProveedor.setText(cursor.getString(cursor.getColumnIndexOrThrow(ProveedoresContract.NOMBREPROVEEDOR)))
                txtDireccion.setText(cursor.getString(cursor.getColumnIndexOrThrow(ProveedoresContract.DIRECCION)))
                txtTelefono.setText(cursor.getString(cursor.getColumnIndexOrThrow(ProveedoresContract.TELEFONO)).toString())
                txtProvincia.setText(cursor.getString(cursor.getColumnIndexOrThrow(ProvinciasContract.NOMBREPROVINCIA)).toString())
            }
        }}
    }
}