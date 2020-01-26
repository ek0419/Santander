package com.example.trabajo.Planetas.catalogo;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabajo.R;
import com.example.trabajo.databinding.CarViewPlanetasItemBinding;

import java.util.ArrayList;

public class PlanetasAdapter extends RecyclerView.Adapter<PlanetasAdapter.PlanetasHolder> {

    private ArrayList<ItemPlaneta> list;
    private ItemPlaneta planeta;
    private OnItemSelectedInterface interfaz;


    public PlanetasAdapter(ArrayList<ItemPlaneta> list,OnItemSelectedInterface interfaz) {
        this.list = list;
        this.interfaz = interfaz;

    }

    @NonNull
    @Override
    public PlanetasHolder onCreateViewHolder(@NonNull ViewGroup grupo, int viewType) {
        return new PlanetasHolder(DataBindingUtil.inflate(LayoutInflater.from(grupo.getContext()), R.layout.car_view_planetas_item, grupo, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PlanetasHolder holder, int position) {

        planeta = list.get(position);
        holder.binding.tvNombrePlaneta.setText(planeta.getNombre());
        holder.binding.tvDiametro.setText(planeta.getDiametro());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class PlanetasHolder extends RecyclerView.ViewHolder {
        CarViewPlanetasItemBinding binding;

        public PlanetasHolder(@NonNull CarViewPlanetasItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            View.OnClickListener clickListener = view -> {
                interfaz.onItemClick(getAdapterPosition());
            };

            binding.CardView.setOnClickListener(clickListener);
        }
    }

    public interface OnItemSelectedInterface {
        void onItemClick(int position);
    }

}
