package com.codingbjs.criminalintent.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.codingbjs.criminalintent.crime.Crime;
import com.codingbjs.criminalintent.crime.CrimeLab;
import com.codingbjs.criminalintent.databinding.ActivityCrimePageBinding;
import com.codingbjs.criminalintent.fragment.CrimeFragment;

import java.util.List;
import java.util.UUID;

public class CrimePagerActivity extends AppCompatActivity {

    private static final String EXTRA_CRIME_ID = "com.codingbjs.criminalintent.crime_id";

    private ActivityCrimePageBinding binding;

    private List<Crime> crimeList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCrimePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        crimeList = CrimeLab.getInstance(this).getCrimeList();

        binding.activityCrimePagerViewPager.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                Crime crime = crimeList.get(position);
                return CrimeFragment.newInstance(crime.getUuid());
            }

            @Override
            public int getItemCount() {
                return crimeList.size();
            }
        });

        for (int i = 0; i < crimeList.size(); i++) {
            if(crimeList.get(i).getUuid().equals(crimeId)){
                binding.activityCrimePagerViewPager.setCurrentItem(i, false);
                break;
            }
        }

    }

    public static Intent newIntent(Context context, UUID crimeId){
        Intent intent = new Intent(context, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

}
