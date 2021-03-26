package com.example.fyphomefitness;

import java.util.ArrayList;

public class Exercises {

    private ArrayList<String> mExerciseName;
    private ArrayList<String> mExerciseRep;

    public Exercises(ArrayList<String> mExerciseName, ArrayList<String> mExerciseRep) {
        this.mExerciseName = mExerciseName;
        this.mExerciseRep = mExerciseRep;
    }

    public ArrayList<String> getExerciseName() {
        return mExerciseName;
    }

    public void setExerciseName(ArrayList<String> mExerciseName) {
        this.mExerciseName = mExerciseName;
    }

    public ArrayList<String> getExerciseRep() {
        return mExerciseRep;
    }

    public void setExerciseRep(ArrayList<String> mExerciseRep) {
        this.mExerciseRep = mExerciseRep;
    }
}
