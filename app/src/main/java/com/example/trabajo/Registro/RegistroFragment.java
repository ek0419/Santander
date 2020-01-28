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

import com.example.trabajo.Utilerias.General.UsuarioModel;
import com.example.trabajo.Utilerias.SQLite.UTDatabaseManager;
import com.example.trabajo.Utilerias.UTUtils;
import com.example.trabajo.Utilerias.optionPiker.PickerDialogFragment;
import com.example.trabajo.Utilerias.optionPiker.PickerOptionSelected;
import com.example.trabajo.Utilerias.optionPiker.UTCatalogo;
import com.example.trabajo.databinding.FragmentRegistroBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


public class RegistroFragment extends Fragment {

    FragmentRegistroBinding binding;
    private PickerDialogFragment pickerDialogFragment;
    private DatePickerDialog.OnDateSetListener date;
    private Calendar calendar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegistroBinding.inflate(inflater, container, false);

        cargarPais();

        binding.btnEnviarDatos.setOnClickListener(v -> salvarUser());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initCalendar();
    }


    private void initCalendar() {
        calendar = new GregorianCalendar();
        DatePickerDialog.OnDateSetListener date = (viewcalendar, year, monthOfYear, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();

        };
        binding.tiFechaNa.setFocusable(false);
        binding.tiFechaNa.setOnClickListener(view1 -> {
            binding.tiFechaNa.setFocusable(false);
            binding.tiFechaNa.setEnabled(false);
            DatePickerDialog dialog = new DatePickerDialog(getActivity(), date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            dialog.setCancelable(false);
            dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancelar", (dialog1, which) -> {
                binding.tiFechaNa.setEnabled(true);
            });
            dialog.show();
        });
    }

    private void updateLabel() {
        String myFormat = "dd/MM/YYYY";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        binding.tiFechaNa.setText(new SimpleDateFormat(myFormat).format(calendar.getTime()));
        binding.tiFechaNa.setFocusable(false);
        binding.tiFechaNa.clearFocus();
        binding.tiFechaNa.setEnabled(true);
    }

    private void cargarPais() {
        ArrayList<String> arrayList = new ArrayList<>();
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

    private void salvarUser() {
        UTDatabaseManager bd = new UTDatabaseManager();
        UsuarioModel user = new UsuarioModel(0, binding.tiNombre.getText().toString(), binding.tiApellidoPa.getText().toString(), binding.tiApellidoMa.getText().toString(), binding.tiFechaNa.getText().toString());
        if (validaRegistroVacio(user)) {
            int insert = bd.insertUsuario(getActivity(), user);
            if (insert == 1) {
                UTUtils.mostrarToas(getActivity(), "Usuario guardado en db", false);
            } else {
                UTUtils.mostrarToas(getActivity(), "Error al guardar Usuario", false);
            }
        } else {
            UTUtils.mostrarToas(getActivity(), "Favor de llenar todos los campos", false);
        }


    }

    private boolean validaRegistroVacio(UsuarioModel user) {
        if (user.getNombre().trim().isEmpty() ||
                user.getAPaterno().trim().isEmpty()
                || user.getAMaterno().trim().isEmpty()
                || user.getFechaNacimiento().trim().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }


    //SELECT id,nombre, aPaterno, aMaterno, fechaNacimiento FROM usuarios
}
