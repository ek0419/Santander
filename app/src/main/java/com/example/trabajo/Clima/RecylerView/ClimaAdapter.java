package com.example.trabajo.Clima.RecylerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabajo.Clima.CrearCiudad;
import com.example.trabajo.R;
import com.example.trabajo.databinding.CarViewClimaBinding;

import java.util.ArrayList;

public class ClimaAdapter extends RecyclerView.Adapter<ClimaAdapter.ClimaHolder> {
    ArrayList<CrearCiudad> list = new ArrayList<>();
    private CrearCiudad ciudad;
    private onItemSelectedClima onIntenselecteInterface;

    private Context context;

    public ClimaAdapter(ArrayList<CrearCiudad> list, onItemSelectedClima onItemSelectedClima) {
        this.list = list;
        this.onIntenselecteInterface = onItemSelectedClima;
    }

    @NonNull
    @Override
    public ClimaHolder onCreateViewHolder(@NonNull ViewGroup group, int viewType) {
        context = group.getContext();
        return new ClimaHolder(DataBindingUtil.inflate(LayoutInflater.from(group.getContext()), R.layout.car_view_clima, group, false), onIntenselecteInterface);
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

    class ClimaHolder extends RecyclerView.ViewHolder {
        CarViewClimaBinding binding;
        onItemSelectedClima onItemSelectedClima;

        public ClimaHolder(@NonNull CarViewClimaBinding binding, onItemSelectedClima onItemSelectedClima) {
            super(binding.getRoot());
            this.binding = binding;
            this.onItemSelectedClima = onItemSelectedClima;

            View.OnClickListener clickListener = view -> {
                onIntenselecteInterface.onItemClick(getAdapterPosition());
            };
        }

    }

    public interface onItemSelectedClima {
        void onItemClick(int position);
    }
}
