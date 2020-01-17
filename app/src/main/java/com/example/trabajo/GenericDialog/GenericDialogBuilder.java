package com.example.trabajo.GenericDialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.trabajo.R;

public class GenericDialogBuilder {

    protected AlertDialog dialog;

    GenericDialogBuilder(AlertDialog.Builder builder) {
        dialog = builder.create();
    }

    public void show() {
        DisplayMetrics metrics = dialog.getContext().getResources().getDisplayMetrics();
        int width = metrics.widthPixels;

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout((6 * width)/7, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        dialog.show();
    }

    public static class Builder {
        private GenericDialogBuilder genericDialogBuilder;
        private AlertDialog.Builder builder;
        private Context context;
        private View view;

        public Builder(Activity activity) {
            builder = new AlertDialog.Builder(activity, R.style.MyAlertDialogTheme);
            view = activity.getLayoutInflater().inflate(R.layout.generic_dialog, null, false);
            builder.setView(view);
            this.setTitulo(null)
                    .setMensaje(null)
                    .setNegativeButton(null)
                    .setPositiveButton(null);
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
            Button button = view.findViewById(R.id.btnGenericDialogBotonPositivo);
            if (titleButton == null) {
                button.setVisibility(View.GONE);
            } else {
                button.setVisibility(View.VISIBLE);
                button.setText(titleButton);
            }
            return this;
        }

        public Builder setNegativeButton(String titleButton) {
            Button button = view.findViewById(R.id.btnGenericDialogBotonNegativo);
            if (titleButton == null) {
                button.setVisibility(View.GONE);
            } else {
                button.setVisibility(View.VISIBLE);
                button.setText(titleButton);
            }
            return this;
        }

        public GenericDialogBuilder create()
        {
            genericDialogBuilder = new GenericDialogBuilder(builder);
            return genericDialogBuilder;
        }
    }
}
