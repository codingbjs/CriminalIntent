package com.codingbjs.criminalintent.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import com.codingbjs.criminalintent.database.CrimeDbSchema.CrimeTable;

public class CrimeBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "crimeBase.db";


    public CrimeBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuilder createTable = new StringBuilder();
        createTable.append("create table ")
                   .append(CrimeTable.NAME)
                   .append("(")
                   .append("_id integer primary key autoincrement, ")
                   .append(CrimeTable.Cols.UUID).append(", ")
                   .append(CrimeTable.Cols.TITLE).append(", ")
                   .append(CrimeTable.Cols.DATE).append(", ")
                   .append(CrimeTable.Cols.SOLVED).append(")");
        sqLiteDatabase.execSQL(createTable.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
