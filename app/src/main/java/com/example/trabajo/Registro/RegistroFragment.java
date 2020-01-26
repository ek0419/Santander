package com.example.trabajo.Registro;


import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.trabajo.Utilerias.optionPiker.OptionPickerAdapter;
import com.example.trabajo.Utilerias.optionPiker.PickerDialogFragment;
import com.example.trabajo.Utilerias.optionPiker.PickerOptionSelected;
import com.example.trabajo.Utilerias.optionPiker.UTCatalogo;
import com.example.trabajo.databinding.FragmentRegistroBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class RegistroFragment extends Fragment {

    FragmentRegistroBinding binding;
    private PickerDialogFragment pickerDialogFragment;
    private DatePickerDialog.OnDateSetListener date;
    private Calendar calendar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegistroBinding.inflate(inflater, container, false);
        initCalendar();
        cargarPais();
        initUI();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         levantarCalendario();
    }

    private void initUI()
    {
        binding.tiFechaNa.setFocusable(false);
        binding.tiFechaNa.setOnClickListener(view1 -> {
            binding.tiFechaNa.setFocusable(false);
            binding.tiFechaNa.setEnabled(false);
            DatePickerDialog dialog = new DatePickerDialog(getActivity(), date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            dialog.setCancelable(false);
            dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancelar", (dialog1, which) -> binding.tiFechaNa.setEnabled(true));
            dialog.show();
        });
    }

    private void initCalendar()
    {
        calendar = new GregorianCalendar();
            date = (viewcalendar, year, monthOfYear, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();

        };
    }

    private void levantarCalendario()
    {
            date = (viewcalendar, year, monthOfYear, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();

        };
    }

    private void updateLabel() {
        String myFormat = "DD/MMM/YYYY";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        binding.tiFechaNa.setText(sdf.format(calendar.getTime()));
        binding.tiFechaNa.setFocusable(false);
        binding.tiFechaNa.clearFocus();
        binding.tiFechaNa.setEnabled(true);
    }

    private void cargarPais()
    {
        ArrayList<String>arrayList = new ArrayList<>();
        pickerDialogFragment = new PickerDialogFragment(arrayList, "Prueba", new PickerOptionSelected() {
            @Override
            public void onCatalogSelected(UTCatalogo catalogo) {
                super.onCatalogSelected(catalogo);
            }

            @Override
            public void onDismissPickerDialog() {
                super.onDismissPickerDialog();
            }
        });
    }
}
