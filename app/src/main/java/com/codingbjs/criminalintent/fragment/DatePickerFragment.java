package com.codingbjs.criminalintent.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.codingbjs.criminalintent.R;
import com.codingbjs.criminalintent.databinding.DialogDateBinding;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatePickerFragment extends DialogFragment {

    public static final String ARG_DATE = "date";


    DialogDateBinding binding;


    public static DatePickerFragment newInstance (Date date) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);

        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.setArguments(args);
        return datePickerFragment;
    }

    private void sendResult(String requestDate, Date date) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);
        getParentFragmentManager().setFragmentResult(requestDate, args);
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        int year = 2022, month = 1, day = 1;

        if (getArguments() != null) {
            Date date = (Date) getArguments().getSerializable(ARG_DATE);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
        }

        binding = DialogDateBinding.inflate(getLayoutInflater());

        binding.dialogDatePicker.init(year, month, day, null);

        return new AlertDialog.Builder(getActivity())
                .setView(binding.getRoot())
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int year = binding.dialogDatePicker.getYear();
                        int month = binding.dialogDatePicker.getMonth();
                        int day = binding.dialogDatePicker.getDayOfMonth();
                        Date date = new GregorianCalendar(year, month, day).getTime();
                        sendResult(CrimeFragment.REQUEST_DATE, date);
                    }
                })
                .create();
    }
}
