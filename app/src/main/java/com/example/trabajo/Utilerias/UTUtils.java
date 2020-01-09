package com.example.trabajo.Utilerias;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.airbnb.lottie.LottieAnimationView;
import com.example.trabajo.R;


public class UTUtils {

    //lotie
    private static androidx.appcompat.app.AlertDialog.Builder dialog;
    private static androidx.appcompat.app.AlertDialog alertDialogTransparent;
    private static LottieAnimationView animationView;


    public static void mostrarToas(Context context, String mensaje, boolean largaDuracion) {
        if (largaDuracion)
            Toast.makeText(context, mensaje, Toast.LENGTH_LONG).show();
        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
    }

    public static void lanzarFragment(FragmentManager manager, int container, Fragment fragment) {
        manager.beginTransaction().add(container, fragment).commit();
    }

    public static void remplazarFragment(FragmentManager manager, int container, Fragment fragment) {
        manager.beginTransaction().replace(container, fragment).commit();
    }

    public static void mostrarProgressDialog(String title, String message, Context activity) {

        if (alertDialogTransparent != null) {
            alertDialogTransparent.dismiss();
        }

        View view = LayoutInflater.from(activity).inflate(R.layout.custom_alert_transparent, null, false);
        animationView = view.findViewById(R.id.myAnimation);
        animationView.setAnimation(R.raw.general_lottie_colors);
        animationView.loop(true);
        dialog = new androidx.appcompat.app.AlertDialog.Builder(activity)
                .setView(view)
                .setCancelable(false);
        alertDialogTransparent = dialog.create();
        alertDialogTransparent.show();
        alertDialogTransparent.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    public static void esconderProgressDialog() {
        if (dialog != null && alertDialogTransparent.isShowing()) {
            alertDialogTransparent.dismiss();
            dialog = null;
        }
    }

}
