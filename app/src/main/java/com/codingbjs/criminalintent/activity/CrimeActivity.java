package com.codingbjs.criminalintent.activity;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.codingbjs.criminalintent.fragment.CrimeFragment;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {

    public static final String EXTRA_CRIME_ID = "com.codingbjs.criminalintent.crime_id";

    @Override
    protected Fragment createFragment() {
        return new CrimeFragment();
    }


    public static Intent newIntent(Context context, UUID crimeId){
        Intent intent = new Intent(context, CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }


}