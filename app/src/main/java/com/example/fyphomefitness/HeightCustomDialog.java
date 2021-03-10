package com.example.fyphomefitness;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import static android.content.Context.MODE_PRIVATE;

public class HeightCustomDialog extends AppCompatDialogFragment {

    private EditText editHeight;
    public heightDialogListener dialogListener;

    public static final String hText = "text3";

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            dialogListener = (heightDialogListener) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "implement the ting");
        }
    }

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View v = inflater.inflate(R.layout.heightdialog, null);

        editHeight = v.findViewById(R.id.edituserheight);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setView(v)
                .setTitle("Height")
                .setNegativeButton("Cancel", (dialog, which) -> {})
                .setPositiveButton("Ok", (dialog, which) -> {
                    String height = editHeight.getText().toString();
                    if (!editHeight.equals("")) {
                        dialogListener.applyHeightText(height);
                        saveData();
                    }

                });

        return builder.create();


    }

    public interface heightDialogListener {
        void applyHeightText(String height);
    }

    public void saveData(){
        SharedPreferences sharedPreferences = getActivity().getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(hText, editHeight.getText().toString());
        editor.apply();

        Toast.makeText(getActivity(),"Height  Saved",Toast.LENGTH_SHORT).show();
        return;
    }


}