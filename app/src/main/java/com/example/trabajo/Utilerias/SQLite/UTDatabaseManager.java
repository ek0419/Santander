package com.example.trabajo.Utilerias.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.trabajo.Utilerias.General.UsuarioModel;

public class UTDatabaseManager {

    private static final String NOMBRE_DB = "dbManager";
    private static final int VERSION_DB = 1;
    private static final String TAG = UTDatabaseManager.class.getSimpleName();

    public SQLiteDatabase connect(Context context) {
        UTDatabase conet = new UTDatabase(context, NOMBRE_DB, null, VERSION_DB);
        return conet.getWritableDatabase();
    }

    public int insertUsuario(Context context, UsuarioModel user) {
        int result = 0;
        String query = "INSERT INTO usuarios (id,nombre,aMaterno,aPaterno,fechaNacimiento) VALUES ('" + user.getId() + "','" + user.getNombre() + "','" + user.getAPaterno() + "','" + user.getAMaterno() + "','" + user.getFechaNacimiento() + "')";

        SQLiteDatabase db = this.connect(context);
        try {
            db.execSQL(query);
            result = 1;
        } catch (Exception e) {
            Log.e(TAG, "Error en base de datos :" + e.getMessage());
            return result;
        }
        return result;
    }

    public int leerUsuario(Context context) {
        String result = "";
        String query = "SELECT id,nombre, aPaterno, aMaterno, fechaNacimiento FROM usuarios";

        SQLiteDatabase db = this.connect(context);
        try {
            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    result += cursor.getString(0) +
                            cursor.getString(1) +
                            cursor.getString(2) +
                            cursor.getString(3) +
                            cursor.getString(4);

                } while (cursor.moveToNext());
            }

            if (result.isEmpty())
                return 0;
            else
                return 1;

        } catch (Exception e) {
            Log.e(TAG, "Error al leer usuario :" + e.getMessage());
            return 0;
        }
    }
}
