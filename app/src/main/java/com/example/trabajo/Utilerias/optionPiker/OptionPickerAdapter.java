package com.example.trabajo.Utilerias.optionPiker;

import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabajo.databinding.ItemOptionPickerBinding;

import java.util.ArrayList;

public class OptionPickerAdapter extends RecyclerView.Adapter<OptionPickerAdapter.OptionPickerViewHolder> {

    private ArrayList options;
    private PickerOptionSelected pickerInterface;
    private ArrayList<Integer> idsPictures;
    private ArrayList<TextWatcher> textWatchers;

    OptionPickerAdapter(ArrayList options, PickerOptionSelected pickerInterface, ArrayList<Integer> idsPictures, ArrayList<TextWatcher> textWatchers) {
        this.options = options;
        this.pickerInterface = pickerInterface;
        this.idsPictures = idsPictures;
        this.textWatchers = textWatchers;
    }

    @NonNull
    @Override
    public OptionPickerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OptionPickerViewHolder(ItemOptionPickerBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull OptionPickerViewHolder holder, int position) {
        Object option = options.get(position);
        Integer idPicture = idsPictures != null ? idsPictures.get(position) : null;
        String descripcion = option instanceof String ? (String) option : option instanceof UTCatalogo ? ((UTCatalogo) option).getCadena() : "";

        if(textWatchers!=null){
            for (TextWatcher textWatcher : textWatchers){
                holder.binding.tvOption.addTextChangedListener(textWatcher);
            }
        }
        holder.binding.tvOption.setText(descripcion);


        if(idPicture != null){
            holder.binding.ivOption.setVisibility(View.VISIBLE);
            holder.binding.ivOption.setImageResource(idPicture);
        } else{
            holder.binding.ivOption.setVisibility(View.GONE);
        }

        holder.binding.tvOption.setOnClickListener(v -> {

            if (option instanceof UTCatalogo) {
                pickerInterface.onCatalogSelected((UTCatalogo) option);

            } else {
                pickerInterface.onItemSelected(position, (String) option);

            }

        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class OptionPickerViewHolder extends RecyclerView.ViewHolder {
        ItemOptionPickerBinding binding;

        OptionPickerViewHolder(ItemOptionPickerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
