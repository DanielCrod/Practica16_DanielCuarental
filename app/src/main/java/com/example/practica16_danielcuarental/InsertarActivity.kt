package com.example.practica16_danielcuarental

import android.database.Cursor
import android.database.sqlite.SQLiteCursor
import android.database.sqlite.SQLiteCursorDriver
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.practica16_danielcuarental.Articulos.Articulo
import com.example.practica16_danielcuarental.Articulos.ArticulosContract
import com.example.practica16_danielcuarental.Proveedores.Proveedor
import com.example.practica16_danielcuarental.Proveedores.ProveedoresContract
import com.example.practica16_danielcuarental.SQL.SqliteHelper


class InsertarActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var codProducto : EditText
    private lateinit var nombreProducto : EditText
    private lateinit var spinner: Spinner
    private lateinit var PVP : EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var radio1 : RadioButton
    private lateinit var radio2 : RadioButton
    private lateinit var radio3 : RadioButton
    private lateinit var boton : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertar)

        //Declaración de elementos
        codProducto = findViewById(R.id.codProducto)
        nombreProducto = findViewById(R.id.nombreProducto)
        spinner = findViewById(R.id.proveedores)
        PVP = findViewById(R.id.pvp)
        radioGroup = findViewById(R.id.radiogroup)
        radio1 = findViewById(R.id.b4)
        radio2 = findViewById(R.id.b10)
        radio3 = findViewById(R.id.b21)
        boton = findViewById(R.id.btn)

        boton.setOnClickListener(this)
        radioGroup.setOnClickListener(this)
        radio1.setOnClickListener(this)
        radio2.setOnClickListener(this)
        radio3.setOnClickListener(this)
        spinner.onItemSelectedListener

        //Creamos un array recibiendo los nombres de los proveedores para rellenar el spinner
        val arrayNombres = ArrayList<String>()
        val helper = SqliteHelper(this)
        val cursorNombres = helper.NombreProveedores()
        while (cursorNombres.moveToNext()){
            arrayNombres.add(
                    cursorNombres.getString(cursorNombres.getColumnIndexOrThrow(ProveedoresContract.NOMBREPROVEEDOR))
            )
        }


        val arrayProveedor = ArrayList<Proveedor>()
        val cursorProveedor = helper.Proveedores()
        while (cursorProveedor.moveToNext()){
            arrayProveedor.add(
                Proveedor(
                cursorProveedor.getString(cursorProveedor.getColumnIndexOrThrow(ProveedoresContract.CODIGOPROVEEDOR)),
                cursorProveedor.getString(cursorProveedor.getColumnIndexOrThrow(ProveedoresContract.NOMBREPROVEEDOR)),
                cursorProveedor.getString(cursorProveedor.getColumnIndexOrThrow(ProveedoresContract.DIRECCION)),
                cursorProveedor.getInt(cursorProveedor.getColumnIndexOrThrow(ProveedoresContract.TELEFONO)),
                cursorProveedor.getInt(cursorProveedor.getColumnIndexOrThrow(ProveedoresContract.PROVINCIA))
                )
            )
        }

        //Adaptador del spinner
        val adaptadorProveedor = ArrayAdapter(this,
        android.R.layout.simple_list_item_1, arrayNombres)

        spinner.adapter = adaptadorProveedor

    }

    override fun onClick(p0: View?) {
        when(p0?.id) {
            R.id.btn -> {
                //Recibimos los datos de los campos y spinner para a continuación pasarle un artículo a la BBDD
                val articulo = Articulo(codProducto.text.toString(), nombreProducto.text.toString(), PVP.text.toString().toDouble(), radioGroup.checkedRadioButtonId.toString().toInt(), spinner.selectedItem.toString())
                val helper = SqliteHelper(this)
                if(helper.insertar(articulo)!= (-1).toLong()) {
                    Toast.makeText(this, "Insertado", Toast.LENGTH_SHORT).show()
                    finish()
                }

            }
        }
    }


}