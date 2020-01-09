package com.example.trabajo.FireBase;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.trabajo.Utilerias.UTUtils;
import com.example.trabajo.databinding.FragmentFirebaseBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;


public class FirebaseFragment extends Fragment {

    private FragmentFirebaseBinding binding;
    private FirebaseDatabase database;
    private DatabaseReference refencia;
    private static final String PATH_START = "star";
    private static final String PATH_MESSAGE = "message";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFirebaseBinding.inflate(inflater, container, false);

        inicializarFireBase();
        return binding.getRoot();
    }


    private void inicializarFireBase() {
        database = FirebaseDatabase.getInstance();
        refencia = database.getReference(PATH_START).child(PATH_MESSAGE);
        //  /star/message
        consultarDatoFirebase();
        binding.btnEnviarDatos.setOnClickListener(view -> enviarDatoFireBase());
    }


    private void consultarDatoFirebase() {
        refencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //  binding.tvActivity.setText(Objects.requireNonNull(dataSnapshot.getValue()).toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                UTUtils.mostrarToas(getActivity(), "Error al consultar Firebase", true);
            }
        });

    }

    private void enviarDatoFireBase() {
        refencia.setValue(Objects.requireNonNull(binding.etEnviarCadena.getText()).toString());
        binding.etEnviarCadena.setText("");
    }

}
