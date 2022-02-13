package com.codingbjs.criminalintent.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codingbjs.criminalintent.crime.Crime;
import com.codingbjs.criminalintent.crime.CrimeLab;
import com.codingbjs.criminalintent.databinding.FragmentCrimeListBinding;
import com.codingbjs.criminalintent.databinding.ListItemCrimeBinding;

import java.util.List;

public class CrimeListFragment extends Fragment {

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

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.getInstance(getActivity());
        crimeAdapter = new CrimeAdapter(crimeLab.getCrimeList());
        binding.crimeRecyclerView.setAdapter(crimeAdapter);
    }

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ListItemCrimeBinding binding;
        private Crime crime;

        public CrimeHolder(@NonNull ListItemCrimeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.getRoot().setOnClickListener(this);
        }

        public void bindCrime(Crime crime){
            this.crime = crime;
            binding.listItemCrimeTitleTextView.setText(crime.getTitle());
            binding.listItemCrimeDateTextView.setText(crime.getDate());
            binding.listItemCrimeSolvedCheckBox.setChecked(crime.isSolved());
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(), crime.getTitle() + " 선택됨", Toast.LENGTH_SHORT).show();
        }
    }


    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {

        private List<Crime> crimeList;

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
