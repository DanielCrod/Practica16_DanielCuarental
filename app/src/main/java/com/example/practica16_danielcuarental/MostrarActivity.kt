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
import com.example.practica16_danielcuarental.Proveedores.ProveedoresContract
import com.example.practica16_danielcuarental.SQL.SqliteHelper

class MostrarActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    private lateinit var listView: ListView


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
        val intent1: Intent
        val helper = SqliteHelper(this)

        val selectedItem = parent?.getItemAtPosition(position) as Articulo
        val item = helper.consultaMulti(selectedItem.toString())
        Toast.makeText(this, item.toString(), Toast.LENGTH_SHORT).show()

        /*val articulo = listView.adapter?.getItem(item.position) as Articulo
        Toast.makeText(this, articulo.codigoArticulo, Toast.LENGTH_LONG).show()

        var intent = Intent(this, DetallesActivity::class.java)

        intent.putExtra("Articulo", articulo)

        startActivity(intent)
        onResume()*/
    }


}