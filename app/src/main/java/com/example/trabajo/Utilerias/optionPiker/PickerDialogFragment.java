package com.example.trabajo.Utilerias.optionPiker;


import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabajo.Utilerias.UTUtils;
import com.example.trabajo.databinding.FragmentPickerDialogBinding;

import java.util.ArrayList;
import java.util.Objects;

public class PickerDialogFragment extends DialogFragment {

    private ArrayList options;
    private FragmentPickerDialogBinding binding;
    private String title;
    private PickerOptionSelected pickerInterface;
    private ArrayList<Integer> idsPictures;
    private ArrayList<TextWatcher> textWatchers;
    private Spanned titleHtml;
    public static final String TAG = PickerDialogFragment.class.getSimpleName();

    public PickerDialogFragment(ArrayList options, String title, PickerOptionSelected pickerInterface) {
        this.options = options;
        this.title = title;
        this.pickerInterface = pickerInterface;
    }

    public PickerDialogFragment(ArrayList options, Spanned titleHtml, PickerOptionSelected pickerInterface) {
        this.options = options;
        this.pickerInterface = pickerInterface;
        idsPictures = null;
        this.titleHtml = titleHtml;
    }

    public PickerDialogFragment(ArrayList options, ArrayList<TextWatcher> textWatchers, ArrayList<Integer> idsPictures, String title, PickerOptionSelected pickerInterface) {
        this.options = options;
        this.title = title;
        this.pickerInterface = pickerInterface;
        this.idsPictures = idsPictures;
        this.textWatchers = textWatchers;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final ConstraintLayout root = new ConstraintLayout(getActivity());
        root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        final Dialog dialog = new Dialog(Objects.requireNonNull(getActivity()));
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(root);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.setOnDismissListener(dialog1 -> pickerInterface.onDismissPickerDialog());
        return dialog;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPickerDialogBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        if (titleHtml != null) {
            binding.tvTitulo.setText(titleHtml);
        } else {
            binding.tvTitulo.setText(title);
        }

        binding.rvOptions.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvOptions.setAdapter(new OptionPickerAdapter(options, new PickerOptionSelected() {
            @Override
            public void onItemSelected(int position, String optionSelected) {
                dismiss();
                pickerInterface.onItemSelected(position, optionSelected);
            }

            @Override
            public void onCatalogSelected(UTCatalogo catalogo) {
                dismiss();
                pickerInterface.onCatalogSelected(catalogo);

            }

        }, idsPictures, textWatchers));
        binding.rvOptions.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), RecyclerView.VERTICAL));

        UTUtils.setDialogWindowSize(getDialog(), 80, 80);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        pickerInterface.onDismissPickerDialog();
    }


}
