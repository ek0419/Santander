package com.example.trabajo.Utilerias;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.disposables.CompositeDisposable;

public class BaseActivity extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();

    protected  CompositeDisposable mCompositeDisposable;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCompositeDisposable.clear();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean accion = false;
        Log.d(TAG, "empezando on key down");
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            for (int i = 0; i < getSupportFragmentManager().getFragments().size(); i++) {
                if (getSupportFragmentManager().getFragments().get(i) instanceof BaseFragment) {
                    BaseFragment fragment = (BaseFragment) getSupportFragmentManager().getFragments().get(i);
                    accion = accion || fragment.onBackPressed();
                }
            }
        }
        Log.d(TAG, " accion = " + accion);
        return accion || super.onKeyDown(keyCode, event);
    }
}
