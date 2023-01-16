package com.example.practica16_danielcuarental

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.practica16_danielcuarental.Articulos.Articulo
import com.example.practica16_danielcuarental.Articulos.ArticulosContract
import com.example.practica16_danielcuarental.Proveedores.Proveedor
import com.example.practica16_danielcuarental.Proveedores.ProveedoresContract
import com.example.practica16_danielcuarental.SQL.SqliteHelper

class MostrarActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    private lateinit var listView: ListView
    private lateinit var articulo: Articulo
    private lateinit var proveedor1: Proveedor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar)

        listView = findViewById(R.id.listView)

        val helper = SqliteHelper(this)
        val nombres = helper.leerArticulos()

        val arrayNombres = ArrayList<String>()
        val cursorNombres = helper.leerArticulos()
        while (cursorNombres.moveToNext()){
            arrayNombres.add(
                cursorNombres.getString(cursorNombres.getColumnIndexOrThrow(ArticulosContract.NOMBREARTICULO))
            )
        }

        val adaptadorArticulo = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, arrayNombres)
        listView.adapter = adaptadorArticulo
        listView.setOnItemClickListener(this)

    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        val helper = SqliteHelper(this)
        val itemSelected = parent?.getItemAtPosition(position)
        val cursor = helper.encontrarArticulo(itemSelected.toString())

        val articulos = ArrayList<Articulo>()



        if(cursor.getCount() >= 1) {
            while (cursor.moveToNext()) {
                articulos.add(
                    Articulo(
                    cursor.getString(cursor.getColumnIndexOrThrow(ArticulosContract.CODIGOARTICULO)),
                    cursor.getString(cursor.getColumnIndexOrThrow(ArticulosContract.NOMBREARTICULO)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(ArticulosContract.PVP)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(ArticulosContract.IVA)),
                    cursor.getString(cursor.getColumnIndexOrThrow(ArticulosContract.PROVEEDOR)),
                ))
            }}




        var intent = Intent(this, DetallesActivity::class.java)
        intent.putExtra("Articulo", articulos.get(0))
        startActivity(intent)
        onResume()



    }




}


