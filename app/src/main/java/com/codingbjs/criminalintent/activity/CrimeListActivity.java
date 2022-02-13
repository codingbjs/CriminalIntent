package com.codingbjs.criminalintent.activity;

import androidx.fragment.app.Fragment;

import com.codingbjs.criminalintent.fragment.CrimeListFragment;

public class CrimeListActivity extends SingleFragmentActivity{
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
