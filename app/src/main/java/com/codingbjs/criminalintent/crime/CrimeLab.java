package com.codingbjs.criminalintent.crime;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.codingbjs.criminalintent.database.CrimeBaseHelper;
import com.codingbjs.criminalintent.database.CrimeCursorWrapper;
import com.codingbjs.criminalintent.database.CrimeDbSchema;
import com.codingbjs.criminalintent.database.CrimeDbSchema.CrimeTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {

    private static CrimeLab crimeLab;
    private Context context;
    private SQLiteDatabase sqLiteDatabase;



    private CrimeLab(Context context) {
        this.context = context.getApplicationContext();
        sqLiteDatabase = new CrimeBaseHelper(this.context).getWritableDatabase();
    }

    public static CrimeLab getInstance(Context context) {
        if (crimeLab == null) {
            crimeLab = new CrimeLab(context);
        }
        return crimeLab;
    }

    public List<Crime> getCrimeList() {
        List<Crime> crimeList = new ArrayList<>();
        CrimeCursorWrapper cursorWrapper = queryCrimes(null, null);

        try {
            cursorWrapper.moveToFirst();
            while(!cursorWrapper.isAfterLast()){
                crimeList.add(cursorWrapper.getCrime());
                cursorWrapper.moveToNext();
            }
        } finally {
            cursorWrapper.close();
        }

        return crimeList;
    }

    public Crime getCrime(UUID uuid) {
        CrimeCursorWrapper cursorWrapper = queryCrimes(
                CrimeTable.Cols.UUID + " = ?",
                new String[] {uuid.toString()}
        );

        try{
          if(cursorWrapper.getCount() == 0) {
              return null;
          }
          cursorWrapper.moveToFirst();
          return cursorWrapper.getCrime();
        }finally {
            cursorWrapper.close();
        }
    }

    private static ContentValues getContentValues (Crime crime) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CrimeTable.Cols.UUID, crime.getUuid().toString());
        contentValues.put(CrimeTable.Cols.TITLE, crime.getTitle());
        contentValues.put(CrimeTable.Cols.DATE, crime.getDate().toString());
        contentValues.put(CrimeTable.Cols.SOLVED, crime.isSolved() ? 1 : 0);
        return contentValues;
    }

    public void addCrime (Crime crime) {
        ContentValues contentValues = getContentValues(crime);
        sqLiteDatabase.insert(CrimeTable.NAME, null, contentValues);
    }

    public void updateCrime(Crime crime) {
        String uuidString = crime.getUuid().toString();
        ContentValues contentValues = getContentValues(crime);

        sqLiteDatabase.update(CrimeTable.NAME, contentValues,
                CrimeTable.Cols.UUID + " = ?", new String[] {uuidString});
    }

    private CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs){
        Cursor cursor = sqLiteDatabase.query(
                CrimeTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new CrimeCursorWrapper(cursor);
    }

}
