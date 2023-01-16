package com.example.practica16_danielcuarental.SQL


import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.practica16_danielcuarental.Articulos.Articulo
import com.example.practica16_danielcuarental.Articulos.ArticulosContract
import com.example.practica16_danielcuarental.Proveedores.ProveedoresContract
import com.example.practica16_danielcuarental.Provincias.ProvinciasContract
import android.database.Cursor as Cursor1

open class SqliteHelper (context: Context?) :
    SQLiteOpenHelper(context, NAME, null, VERSION) {

        companion object {
            private const val NAME = "Almacen.db"
            private const val VERSION = 3
        }

    @SuppressLint("SQLiteString")
    override fun onCreate(sqLiteDatabase: SQLiteDatabase?) {

        sqLiteDatabase?.execSQL(
            "CREATE TABLE " +
                    ProvinciasContract.TABLE_NAME+ " ( "
                    + ProvinciasContract.CODIGOPROVINCIA + " INTEGER PRIMARY KEY, "
                    + ProvinciasContract.NOMBREPROVINCIA + " TEXT NULL);"
        )

        sqLiteDatabase?.execSQL(
            "INSERT INTO " + ProvinciasContract.TABLE_NAME + " (codigoProvincia, nombreProvincia) VALUES" +
                    "(1, 'Valladolid')," +
                    "(2, 'Madrid')," +
                    "(3, 'Barcelona')"
        )


        sqLiteDatabase?.execSQL(
            "CREATE TABLE " +
                    ProveedoresContract.TABLE_NAME+ " ( "
                    + ProveedoresContract.CODIGOPROVEEDOR + " TEXT PRIMARY KEY, "
                    + ProveedoresContract.NOMBREPROVEEDOR + " TEXT NOT NULL, "
                    + ProveedoresContract.DIRECCION + " TEXT NOT NULL, "
                    + ProveedoresContract.TELEFONO + " INTEGER NOT NULL,"
                    + ProveedoresContract.PROVINCIA + " INTEGER NOT NULL,"
                    + "FOREIGN KEY ("+ ProveedoresContract.PROVINCIA + ") " +
                    " REFERENCES " + ProvinciasContract.TABLE_NAME + "(" + ProvinciasContract.CODIGOPROVINCIA
                    + "));"
        )

        sqLiteDatabase?.execSQL(
            "INSERT INTO " + ProveedoresContract.TABLE_NAME + " (codigoProveedor, nombreProveedor, direccion, telefono, provincia) VALUES" +
                    "(1, 'Jose', 'Mojados', 640250241, 1)," +
                    "(2, 'Pablo', 'Vallecas', 635910716, 2)," +
                    "(3, 'Paula', 'Argentona', 640250241, 3)"
        )

        sqLiteDatabase?.execSQL(
            "CREATE TABLE " +
                    ArticulosContract.TABLE_NAME+ " ( "
                    + ArticulosContract.CODIGOARTICULO + " TEXT PRIMARY KEY, "
                    + ArticulosContract.NOMBREARTICULO + " TEXT NOT NULL, "
                    + ArticulosContract.PVP + " DOUBLE NOT NULL, "
                    + ArticulosContract.IVA + " INTEGER NOT NULL,"
                    + ArticulosContract.PROVEEDOR + " TEXT NOT NULL,"
                    + "FOREIGN KEY ("+ ArticulosContract.PROVEEDOR + ") " +
                    " REFERENCES " + ProveedoresContract.TABLE_NAME + "(" + ProveedoresContract.CODIGOPROVEEDOR
                    +"));"

        )


    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }


    fun insertar(articulo: Articulo):Long {
        val db = writableDatabase
        val values = ContentValues()
        values.put(ArticulosContract.CODIGOARTICULO, articulo.codigoArticulo)
        values.put(ArticulosContract.NOMBREARTICULO, articulo.nombreArticulo)
        values.put(ArticulosContract.PVP, articulo.pvp)
        values.put(ArticulosContract.IVA, articulo.iva)
        values.put(ArticulosContract.PROVEEDOR, articulo.proveedor)

        return db.insert(ArticulosContract.TABLE_NAME, null, values)
    }

    fun NombreProveedores (): android.database.Cursor {
        val db = writableDatabase
        var ArrayProveedores : android.database.Cursor =
            db.rawQuery("SELECT nombreProveedor FROM " + ProveedoresContract.TABLE_NAME + "", null)
        return ArrayProveedores
    }

    fun Proveedores (): android.database.Cursor {
        val db = writableDatabase
        var ArrayProveedores : android.database.Cursor =
            db.rawQuery("SELECT * FROM " + ProveedoresContract.TABLE_NAME + "", null)
        return ArrayProveedores
    }

    fun leerArticulos (): android.database.Cursor {
        val db = writableDatabase
        var nombresArticulos : android.database.Cursor =
            db.rawQuery("SELECT nombreArticulo FROM " + ArticulosContract.TABLE_NAME + "", null)
        return nombresArticulos
    }



    fun encontrarArticulo (nombre : String): android.database.Cursor {
        val db = writableDatabase
        var nombresArticulos : android.database.Cursor =
            db.rawQuery("SELECT * FROM " + ArticulosContract.TABLE_NAME + " WHERE nombreArticulo= '" + nombre + "'", null)
        return nombresArticulos
    }

    fun consultaMulti (nombre: String): android.database.Cursor {
        val db = writableDatabase
        var consulta : android.database.Cursor =
            db.rawQuery("SELECT * FROM " + ArticulosContract.TABLE_NAME + " WHERE nombreArticulo= '" + nombre + "'",  null)
        return consulta
    }
}
