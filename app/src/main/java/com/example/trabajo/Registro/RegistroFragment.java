package com.example.trabajo.Registro;


import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.trabajo.Planetas.catalogo.CatalogoPlanetasFragment;
import com.example.trabajo.R;
import com.example.trabajo.Utilerias.BaseFragment;
import com.example.trabajo.Utilerias.General.UsuarioModel;
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


public class RegistroFragment extends BaseFragment implements RegistroInterface.View {

    FragmentRegistroBinding binding;
    private PickerDialogFragment pickerDialogFragment;
    private DatePickerDialog.OnDateSetListener date;
    private Calendar calendar;
    private RegistroInterface.Presenter presenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegistroBinding.inflate(inflater, container, false);
        // cargarPais();
        binding.btnEnviarDatos.setOnClickListener(v -> creaUsuario());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new RegistroPresenter(this, getActivity());
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

    private void creaUsuario() {
        presenter.guardarUsuario(new UsuarioModel(0, binding.tiNombre.getText().toString(), binding.tiApellidoPa.getText().toString(), binding.tiApellidoMa.getText().toString(), binding.tiFechaNa.getText().toString()));
    }


    @Override
    public void mostrarProgress(boolean mostrar) {
        if (mostrar) {
            UTUtils.mostrarProgressDialog("", "", getActivity());
        } else {
            UTUtils.esconderProgressDialog();
        }
    }

    @Override
    public void cambiarVista() {
        UTUtils.lanzarFragment(getFragmentManager(), R.id.flContainer, new CatalogoPlanetasFragment(), false);
    }

    @Override
    public void mostarMensaje(String mensaje) {
        UTUtils.mostrarToas(getActivity(), mensaje, false);
    }
}
