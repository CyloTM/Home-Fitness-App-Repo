package com.example.fyphomefitness.fragments;

import android.app.Dialog;
import android.content.Context;
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

import com.example.fyphomefitness.R;

import static android.content.Context.MODE_PRIVATE;

public class UserNameDialog extends AppCompatDialogFragment {

    private EditText editUserName;
    public userNameDialogListener dialogListener;

    public static final String Text = "text";

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            dialogListener = (userNameDialogListener) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "implement the ting");
        }
    }

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.usernamedialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        editUserName = v.findViewById(R.id.editusername);
        builder.setView(v)
                .setTitle("UserName")
                .setNegativeButton("Cancel", (dialog, which) -> {})
                .setPositiveButton("ok", (dialog, which) -> {
                    String username = editUserName.getText().toString();
                    if (!editUserName.equals("")) {
                        dialogListener.applyUserNameText(username);
                        saveData();
                    }
                });
        return builder.create();
    }

    public interface userNameDialogListener {
        void applyUserNameText(String username);
    }

    public void saveData(){
        SharedPreferences sharedPreferences = getActivity().getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Text, editUserName.getText().toString());
        editor.apply();

        Toast.makeText(getActivity(),"Name Saved",Toast.LENGTH_SHORT).show();
        return;
    }

}
