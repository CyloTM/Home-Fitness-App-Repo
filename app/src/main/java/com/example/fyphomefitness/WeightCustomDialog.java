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

public class WeightCustomDialog extends AppCompatDialogFragment {

    private EditText editWeight;
    public weightDialogListener dialogListener;

    public static final String wText = "text2";

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            dialogListener = (weightDialogListener) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "implement the ting");
        }
    }

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.weightdialog, null);
        v.bringToFront();
        editWeight = v.findViewById(R.id.edituserweight);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v).setTitle("Weight")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String weight = editWeight.getText().toString();
                        if (!editWeight.equals("")) {
                            dialogListener.applyWeightText(weight);
                            saveData();
                        }

                    }
                });

        return builder.create();
    }

    public interface weightDialogListener {
        void applyWeightText(String weight);
    }

    public void saveData(){
        SharedPreferences sharedPreferences = getActivity().getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(wText, editWeight.getText().toString());
        editor.apply();

        Toast.makeText(getActivity(),"Weight Saved",Toast.LENGTH_SHORT).show();
        return;
    }
}
