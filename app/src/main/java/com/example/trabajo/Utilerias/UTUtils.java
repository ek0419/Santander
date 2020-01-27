package com.example.trabajo.Utilerias;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.airbnb.lottie.LottieAnimationView;
import com.example.trabajo.R;


public class UTUtils {

    private static final String TAG = UTUtils.class.getSimpleName();

    //lotie
    private static androidx.appcompat.app.AlertDialog.Builder dialog;
    private static androidx.appcompat.app.AlertDialog alertDialogTransparent;
    private static LottieAnimationView animationView;


    public static void mostrarToas(Context context, String mensaje, boolean largaDuracion) {
        if (largaDuracion)
            Toast.makeText(context, mensaje, Toast.LENGTH_LONG).show();
        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
    }

    public static void lanzarFragment(FragmentManager manager, int container, Fragment fragment, Boolean addToBackStack) {
        if (addToBackStack) {
            manager.beginTransaction().setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right).add(container, fragment).addToBackStack(null).commit();
            return;
        }
        manager.beginTransaction().setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right).add(container, fragment).commit();
    }

    public static void remplazarFragment(FragmentManager manager, int container, Fragment fragment, boolean addToBackStack) {
        if (addToBackStack) {
            manager.beginTransaction().setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right).replace(container, fragment).addToBackStack(null).commit();
            return;
        }
        manager.beginTransaction().setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right).replace(container, fragment).addToBackStack(null).commit();

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

    private void lanzarActivity(Context context, Class<?> clsy, boolean finish) {
        Intent intent = new Intent(context, clsy);
        context.startActivity(intent);
        if (finish) {

        }
    }

    public static void setDialogWindowSize(Dialog dialog, int widthPercent, int heightPercent) {
        float newHeight;
        float newWidth;
        if (dialog == null)
            return;
        if (widthPercent > 0 && widthPercent <= 100 && heightPercent > 0 && heightPercent <= 100) {
            try {
                Window window = dialog.getWindow();
                Point size = new Point();
                Display display;
                if (window != null) {
                    display = window.getWindowManager().getDefaultDisplay();
                    display.getSize(size);
                    newWidth = size.x * ((float) widthPercent / 100);
                    newHeight = size.y * ((float) heightPercent / 100);
                    window.setLayout((int) newWidth, (int) newHeight);
                    window.setGravity(Gravity.CENTER);
                }
            } catch (Exception e) {
                Log.e(TAG, "Ocurrió un error al modificar el tamaño de la ventana del dialogo");

            }

        }

    }

}
