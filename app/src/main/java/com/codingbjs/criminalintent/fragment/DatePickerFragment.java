package com.codingbjs.criminalintent.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.codingbjs.criminalintent.R;
import com.codingbjs.criminalintent.databinding.DialogDateBinding;

public class DatePickerFragment extends DialogFragment {

    DialogDateBinding binding;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding = DialogDateBinding.inflate(getLayoutInflater());
        return new AlertDialog.Builder(getActivity())
                .setView(binding.getRoot())
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }
}
