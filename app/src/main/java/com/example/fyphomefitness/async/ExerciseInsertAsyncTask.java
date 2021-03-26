package com.example.fyphomefitness.async;

import android.os.AsyncTask;

import com.example.fyphomefitness.model.Exercise;
import com.example.fyphomefitness.persistance.WorkoutDao;

public class ExerciseInsertAsyncTask extends AsyncTask<Exercise, Void, Void> {

    private WorkoutDao mWorkoutDao;


    public ExerciseInsertAsyncTask(WorkoutDao dao) {
        mWorkoutDao = dao;
    }

    @Override
    protected Void doInBackground(Exercise... exercise) {
        mWorkoutDao.insertExercise(exercise);
        return null;
    }
}
