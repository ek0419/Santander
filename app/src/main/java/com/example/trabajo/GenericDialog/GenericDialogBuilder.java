package com.example.trabajo.GenericDialog;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.trabajo.R;

public class GenericDialogBuilder implements GenericDialogInterface {

    protected AlertDialog dialog;

    GenericDialogBuilder(AlertDialog.Builder builder) {
        dialog = builder.create();
    }

    public void show() {
        DisplayMetrics metrics = dialog.getContext().getResources().getDisplayMetrics();
        int width = metrics.widthPixels;

        //  dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout((6 * width) / 7, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        dialog.show();
    }

    @Override
    public void dismis() {
        dialog.dismiss();
    }

    public static class Builder {
        private GenericDialogBuilder genericDialogBuilder;
        private AlertDialog.Builder dialogBuilder;
        private Context context;
        private View view;

        public Builder(Activity activity) {
            dialogBuilder = new AlertDialog.Builder(activity, R.style.MyAlertDialogTheme);
            view = activity.getLayoutInflater().inflate(R.layout.generic_dialog, null, false);
            dialogBuilder.setView(view);
            this.setTitulo(null)
                    .setMensaje(null)
                    .setNegativeButton(null)
                    .setPositiveButton(null)
                    .setCancelable(false);

        }

        public Builder setTitulo(String titulo) {
            TextView textView = view.findViewById(R.id.tvGenericDialogTitle);
            if (titulo == null) {
                textView.setVisibility(View.GONE);
            } else {
                textView.setVisibility(View.VISIBLE);
                textView.setText(titulo);
            }
            return this;
        }

        public Builder setMensaje(String mensaje) {
            TextView textView = view.findViewById(R.id.tvGenericDialogDescripcion);
            if (mensaje == null) {
                textView.setVisibility(View.GONE);
            } else {
                textView.setVisibility(View.VISIBLE);
                textView.setText(mensaje);
            }
            return this;
        }

        public Builder setPositiveButton(String titleButton) {

            return setPositiveButton(titleButton,GenericDialogInterface::dismis);
        }

        private Builder setPositiveButton(String titleButton, GenericDialogInterface.OncliclckListener interfas) {
            Button button = view.findViewById(R.id.btnGenericDialogBotonPositivo);
            prepararBoton(button, titleButton, interfas);
            return this;
        }


        public Builder setNegativeButton(String titleButton) {
            return setNegativeButton(titleButton, GenericDialogInterface::dismis);
        }

        public Builder setNegativeButton(String titleButton, GenericDialogInterface.OncliclckListener interefas) {
            Button button = view.findViewById(R.id.btnGenericDialogBotonNegativo);
            prepararBoton(button, titleButton, interefas);
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            dialogBuilder.setCancelable(cancelable);
            return this;
        }

        public GenericDialogBuilder create() {
            genericDialogBuilder = new GenericDialogBuilder(dialogBuilder);
            return genericDialogBuilder;
        }

        private void prepararBoton(Button boton, String texto, GenericDialogInterface.OncliclckListener listener) {
            if (texto == null) {
                boton.setVisibility(View.GONE);
            } else {
                boton.setVisibility(View.VISIBLE);
                boton.setText(texto);
                boton.setOnClickListener(v -> {
                    if (listener != null) {
                        listener.onClik(genericDialogBuilder);
                    }
                });
            }
        }


        private void dismis() {
            if (genericDialogBuilder.dialog != null) {
                genericDialogBuilder.dialog.dismiss();
            }
        }
    }
}
