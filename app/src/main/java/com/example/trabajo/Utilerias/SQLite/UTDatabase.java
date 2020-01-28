package com.example.trabajo.Utilerias.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UTDatabase extends SQLiteOpenHelper {

    String TBL_USR = ("CREATE TABLE usuarios (id INTEGER, nombre TEXT, aPaterno TEXT, aMaterno TEXT, fechaNacimiento TEXT)");
    public UTDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TBL_USR);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
