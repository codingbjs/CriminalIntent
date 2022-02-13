package com.codingbjs.criminalintent.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.codingbjs.criminalintent.activity.CrimeActivity;
import com.codingbjs.criminalintent.crime.Crime;
import com.codingbjs.criminalintent.crime.CrimeLab;
import com.codingbjs.criminalintent.databinding.FragmentCrimeBinding;

import java.util.Objects;
import java.util.UUID;

public class CrimeFragment extends Fragment {

    private Crime crime;

    FragmentCrimeBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = requireActivity().getIntent();
        if(intent != null) {
            UUID crimeId = (UUID) intent.getSerializableExtra(CrimeActivity.EXTRA_CRIME_ID);
            crime = CrimeLab.getInstance(getActivity()).getCrime(crimeId);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCrimeBinding.inflate(inflater, container, false);

        binding.crimeTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                crime.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.crimeTitle.setText(crime.getTitle());
        binding.crimeDate.setText(crime.getDate());
        binding.crimeDate.setEnabled(false);
        binding.crimeSolved.setChecked(crime.isSolved());

        binding.crimeSolved.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                crime.setSolved(b);
            }
        });

        return binding.getRoot();
    }
}
