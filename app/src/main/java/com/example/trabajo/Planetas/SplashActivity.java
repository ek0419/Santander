package com.example.trabajo.Planetas;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.example.trabajo.MainActivity;
import com.example.trabajo.R;
import com.example.trabajo.Utilerias.BaseActivity;
import com.example.trabajo.databinding.ActivityPlanetasBinding;

import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends BaseActivity {

    ActivityPlanetasBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_planetas);

        mostrarLottie();
        mCompositeDisposable.add(Single.fromCallable(() -> true).delay(5, TimeUnit.SECONDS)
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result->
                {
                    mCompositeDisposable.clear();
                    if(result)
                    {
                        lanzarMainActivity();
                    }
                }));

    }


    private void mostrarLottie() {
        binding.laPlaneta.setAnimation(R.raw.planeta);
        binding.laPlaneta.loop(true);
    }

    private void lanzarMainActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
