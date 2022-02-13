package com.codingbjs.criminalintent;

import androidx.fragment.app.Fragment;

import com.codingbjs.criminalintent.fragment.CrimeFragment;

public class CrimeActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeFragment();
    }

}