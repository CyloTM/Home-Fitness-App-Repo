package com.example.fyphomefitness;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

public class profileFragment extends Fragment implements UserNameDialog.userNameDialogListener, WeightCustomDialog.weightDialogListener, HeightCustomDialog.heightDialogListener {

    private TextView mTxtVwUserName;
    private TextView mTxtVwUserHeight;
    private TextView mTxtVwUserWeight;
    private TextView mTxtVwBmiTotal;
    private TextView mTxtVwWorkoutsComplete;

    //Save
    public static final String Shared_Pref = "sharedPrefs";
    public static final String mSPUserNameText = "text";
    public static final String mSPUserWeightText = "text2";
    public static final String mSPUserHeightText = "text3";
    public static final String mSPBmiText ="text4";
    public static final String mSPWorkOutsCompleted ="text5";

    //Load
    private String mUserName;
    private String mUserWeight;
    private String mUserHeight;
    private String mBmiTotal;
    private int mWorkoutsCompleted;

    //Navigation Off/On
    private NavDrawInterface navDrawInterface;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        navDrawInterface = (NavDrawInterface) context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile, container, false);

        navDrawInterface.lockDrawer();

        mTxtVwUserName = view.findViewById(R.id.nameText);
        Button openNameBtn = view.findViewById(R.id.nameDialogBtn);
        Button saveBtn = view.findViewById(R.id.saveBtn);
        Button calculateBmiBtn = view.findViewById(R.id.calcBmiButton);
        mTxtVwUserHeight = view.findViewById(R.id.heightTxt);
        mTxtVwUserWeight = view.findViewById(R.id.weightTxt);
        mTxtVwBmiTotal = view.findViewById(R.id.bmiText);
        mTxtVwWorkoutsComplete = view.findViewById(R.id.text_view_workouts_completed);

        openNameBtn.setOnClickListener(v -> openNameDialog());
        saveBtn.setOnClickListener(v -> saveData());
        mTxtVwUserWeight.setOnClickListener(v -> openWeightDialog());
        mTxtVwUserHeight.setOnClickListener(v -> openHeightDialog());

        calculateBmiBtn.setOnClickListener(v -> calculateBMI());

        loadData();
        updateData();

        return view;
    }

    public void calculateBMI(){
        SharedPreferences sharedPreferences = getActivity().getPreferences(MODE_PRIVATE);
        mUserWeight = sharedPreferences.getString(mSPUserWeightText, "");
        mUserHeight = sharedPreferences.getString(mSPUserHeightText, "");
        float hInt = Float.parseFloat(mUserHeight);
        float wInt = Float.parseFloat(mUserWeight);
        if (!mSPUserWeightText.contentEquals("") & !mSPUserHeightText.contentEquals("")){
            hInt = hInt/100;
            hInt = hInt * hInt;
            float bmi =   wInt/hInt ;
            Float bmiRounded = Math.round(bmi * 10f)/10f;
            mTxtVwBmiTotal.setText(bmiRounded.toString());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        navDrawInterface.unlockDrawer();
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getActivity().getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(mSPUserNameText, mTxtVwUserName.getText().toString());
        editor.putString(mSPUserWeightText, mTxtVwUserWeight.getText().toString());
        editor.putString(mSPUserHeightText, mTxtVwUserHeight.getText().toString());
        editor.putString(mSPBmiText, mTxtVwBmiTotal.getText().toString());
        editor.putString(mSPWorkOutsCompleted, mTxtVwWorkoutsComplete.getText().toString());
        editor.apply();

        Toast.makeText(getActivity(), "Data Saved", Toast.LENGTH_SHORT).show();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getActivity().getPreferences(MODE_PRIVATE);
        mUserName = sharedPreferences.getString(mSPUserNameText, "");
        mUserWeight = sharedPreferences.getString(mSPUserWeightText, "");
        mUserHeight = sharedPreferences.getString(mSPUserHeightText, "");
        mBmiTotal = sharedPreferences.getString(mSPBmiText,"");
        mWorkoutsCompleted = sharedPreferences.getInt(mSPWorkOutsCompleted,0);
    }

    public void updateData() {
        mTxtVwUserName.setText(mUserName);
        mTxtVwUserHeight.setText(mUserHeight);
        mTxtVwUserWeight.setText(mUserWeight);
        mTxtVwBmiTotal.setText(mBmiTotal);
        mTxtVwWorkoutsComplete.setText(String.valueOf(mWorkoutsCompleted));
    }

    public void openNameDialog() {
        UserNameDialog userNameDialog = new UserNameDialog();
        userNameDialog.setTargetFragment(profileFragment.this, 1);
        userNameDialog.show(getParentFragmentManager(), "userNameDialog");
    }

    public void openHeightDialog() {
        HeightCustomDialog heightDialog = new HeightCustomDialog();
        heightDialog.setTargetFragment(profileFragment.this, 0);
        heightDialog.show(getParentFragmentManager(), "HeightDialog");
    }

    public void openWeightDialog() {
        WeightCustomDialog weightDialog = new WeightCustomDialog();
        weightDialog.setTargetFragment(profileFragment.this, 1);
        weightDialog.show(getParentFragmentManager(), "WeightDialog");
    }

    @Override
    public void applyUserNameText(String username) {
        mTxtVwUserName.setText(username);
    }

    @Override
    public void applyHeightText(String height) {
        mTxtVwUserHeight.setText(height);
    }

    @Override
    public void applyWeightText(String weight) {
        mTxtVwUserWeight.setText(weight);
    }
}
