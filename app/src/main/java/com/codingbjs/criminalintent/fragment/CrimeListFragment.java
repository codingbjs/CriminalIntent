package com.codingbjs.criminalintent.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codingbjs.criminalintent.activity.CrimeActivity;
import com.codingbjs.criminalintent.activity.CrimePagerActivity;
import com.codingbjs.criminalintent.crime.Crime;
import com.codingbjs.criminalintent.crime.CrimeLab;
import com.codingbjs.criminalintent.databinding.FragmentCrimeListBinding;
import com.codingbjs.criminalintent.databinding.ListItemCrimeBinding;

import java.util.List;

public class CrimeListFragment extends Fragment {

    private static final int REQUEST_CRIME = 1;

    FragmentCrimeListBinding binding;

    private CrimeAdapter crimeAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCrimeListBinding.inflate(inflater, container, false);

        binding.crimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.getInstance(getActivity());
        if(crimeAdapter == null) {
            crimeAdapter = new CrimeAdapter(crimeLab.getCrimeList());
            binding.crimeRecyclerView.setAdapter(crimeAdapter);
        } else {
            crimeAdapter.notifyItemChanged(0);
        }
    }

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ListItemCrimeBinding binding;
        private Crime crime;
        private int clickPosition = 0;

        public CrimeHolder(@NonNull ListItemCrimeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.getRoot().setOnClickListener(this);
        }

        public void bindCrime(Crime crime){
            this.crime = crime;
            binding.listItemCrimeTitleTextView.setText(crime.getTitle());
            binding.listItemCrimeDateTextView.setText(crime.getDate().toString());
            binding.listItemCrimeSolvedCheckBox.setChecked(crime.isSolved());
        }

        @Override
        public void onClick(View view) {
            clickPosition = getAdapterPosition();
            Toast.makeText(getActivity(), crime.getTitle() + " 선택됨" + clickPosition, Toast.LENGTH_SHORT).show();
            Intent intent = CrimePagerActivity.newIntent(getActivity(), crime.getUuid());
            startActivity(intent);
        }
    }


    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {

        private final List<Crime> crimeList;

        public CrimeAdapter(List<Crime> crimeList) {
            this.crimeList = crimeList;
        }

        @NonNull
        @Override
        public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ListItemCrimeBinding binding = ListItemCrimeBinding.inflate(
                                LayoutInflater.from(getActivity()), parent, false);
            return new CrimeHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull CrimeHolder holder, int position) {
            Crime crime = crimeList.get(position);
            holder.bindCrime(crime);
        }

        @Override
        public int getItemCount() {
            return crimeList.size();
        }
    }
}
