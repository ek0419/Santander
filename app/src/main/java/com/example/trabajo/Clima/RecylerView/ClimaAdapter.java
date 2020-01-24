package com.example.trabajo.Clima.RecylerView;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabajo.Clima.ClimaWSInterface;
import com.example.trabajo.Clima.CrearCiudad;
import com.example.trabajo.R;
import com.example.trabajo.Utilerias.UTUtils;
import com.example.trabajo.databinding.CarViewClimaBinding;

import java.util.ArrayList;

public class ClimaAdapter extends RecyclerView.Adapter<ClimaAdapter.ClimaHolder> implements ClimaInterface {
    ArrayList<CrearCiudad> list = new ArrayList<>();
    private CrearCiudad ciudad;

    private ClimaInterface interfaz;
    private Context context;

    public ClimaAdapter(ArrayList<CrearCiudad> list, ClimaInterface interfaz) {
        this.list = list;
        this.interfaz = interfaz;
    }

    @NonNull
    @Override
    public ClimaHolder onCreateViewHolder(@NonNull ViewGroup group, int viewType) {
        context = group.getContext();
        return new ClimaHolder(DataBindingUtil.inflate(LayoutInflater.from(group.getContext()), R.layout.car_view_clima, group, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ClimaHolder holder, int position) {
        ciudad = list.get(position);
        holder.binding.tvNombre.setText(ciudad.getNombre());
        holder.binding.tvNumero.setText(ciudad.getId().toString());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v, int position) {
        interfaz.onClick(v, position);
        UTUtils.mostrarToas(context, "CLIC", false);
    }

    class ClimaHolder extends RecyclerView.ViewHolder {
        CarViewClimaBinding binding;

        public ClimaHolder(@NonNull CarViewClimaBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
