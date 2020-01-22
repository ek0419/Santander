package com.example.trabajo.PokeApi.RecycleView;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabajo.PokeApi.PokeApiModel;
import com.example.trabajo.PokeApi.Result;
import com.example.trabajo.R;
import com.example.trabajo.databinding.CardViewPokemonesBinding;

import java.util.ArrayList;
import java.util.Objects;

public class PokeApiAdapter extends RecyclerView.Adapter<PokeApiAdapter.PokemonHolder> {

    PokeApiModel list;

    public PokeApiAdapter(PokeApiModel list) {
        this.list = list;
    }

    @NonNull
    @Override
    public PokemonHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new PokemonHolder(DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.card_view_pokemones, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonHolder holder, int position) {

        Result model = list.component1().get(position);
        holder.binding.tvNombre.setText(Objects.requireNonNull(model.getName()).trim());
    }

    @Override
    public int getItemCount() {
        return list.component1().size();
    }

    class PokemonHolder extends RecyclerView.ViewHolder {

        CardViewPokemonesBinding binding;

        public PokemonHolder(@NonNull CardViewPokemonesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
