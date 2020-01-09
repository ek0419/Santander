package com.example.trabajo.MenuComida;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.trabajo.InitDaggerII.App;
import com.example.trabajo.databinding.FragmentMenuComidaBinding;

import javax.inject.Inject;

public class MenuComidaFragment extends Fragment implements MenuComidaFragmentInterface.View {

    @Inject
    public MenuComidaFragmentInterface.Presenter presenter;
    private FragmentMenuComidaBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMenuComidaBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        ((App) context.getApplicationContext()).getComponet().inject(this);
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(this);
    }
}
