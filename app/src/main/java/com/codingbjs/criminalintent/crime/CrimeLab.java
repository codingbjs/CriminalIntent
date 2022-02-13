package com.codingbjs.criminalintent.crime;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {

    private static CrimeLab crimeLab;
    private List<Crime> crimeList;


    private CrimeLab(Context context) {
        crimeList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setTitle("범죄 #" + i);
            crime.setSolved(i % 2 == 0);
            crimeList.add(crime);
        }
    }

    public static CrimeLab getInstance(Context context) {
        if (crimeLab == null) {
            crimeLab = new CrimeLab(context);
        }
        return crimeLab;
    }

    public List<Crime> getCrimeList() {
        return crimeList;
    }

    public Crime getCrime(UUID uuid) {
        for (Crime crime : crimeList) {
            if (crime.getUuid().equals(uuid)) {
                return crime;
            }
        }
        return null;
    }

}