package com.example.fyphomefitness.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fyphomefitness.interfaces.NavDrawInterface;
import com.example.fyphomefitness.R;

public class customWorkoutFragment extends Fragment {

    //Navigation Off/On
    private NavDrawInterface navDrawInterface;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        navDrawInterface = (NavDrawInterface) context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.customworkouts, container, false);
        navDrawInterface.lockDrawer();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        navDrawInterface.unlockDrawer();
    }

    }
