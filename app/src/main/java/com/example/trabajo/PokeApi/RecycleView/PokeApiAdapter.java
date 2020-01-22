package com.example.trabajo.PokeApi.RecycleView;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabajo.PokeApi.Pokemones;
import com.example.trabajo.R;
import com.example.trabajo.databinding.CardViewPokemonesBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

public class PokeApiAdapter extends RecyclerView.Adapter<PokeApiAdapter.PokemonHolder> {

    ArrayList<Pokemones> list;
    private static final String BASEIMAGE = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/";
    private static final String REMPLACE = "https://pokeapi.co/api/v2/pokemon/";
    private Context context;

    public PokeApiAdapter() {
        list = new ArrayList<>();

    }

    public void addLista(ArrayList<Pokemones> list) {
        this.list.addAll(list);
        notifyDataSetChanged();

    }


    @NonNull
    @Override
    public PokemonHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        context = viewGroup.getContext();
        return new PokemonHolder(DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.card_view_pokemones, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonHolder holder, int position) {

        Pokemones model = list.get(position);
        holder.binding.tvNombre.setText(Objects.requireNonNull(model.getNombre()).trim());
        String tem = model.getUrl().replace(REMPLACE, "");
        String numeroPokemon = tem.replace("/", "");
        holder.binding.tvNumero.setText("Nº: " + numeroPokemon);
        Picasso.get().load(BASEIMAGE + numeroPokemon + ".png")
                .error(context.getResources().getDrawable(R.drawable.error_carga_magen))
                .centerCrop()
                .placeholder(context.getDrawable(R.drawable.loading))
                .resize(250, 250).into(holder.binding.ivImagenPokemon);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class PokemonHolder extends RecyclerView.ViewHolder {

        CardViewPokemonesBinding binding;

        public PokemonHolder(@NonNull CardViewPokemonesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
