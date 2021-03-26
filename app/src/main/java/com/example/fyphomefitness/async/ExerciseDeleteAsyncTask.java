package com.example.fyphomefitness.async;

import android.os.AsyncTask;

import com.example.fyphomefitness.model.Exercise;
import com.example.fyphomefitness.persistance.WorkoutDao;

public class ExerciseDeleteAsyncTask extends AsyncTask<Exercise, Void, Void> {

    private WorkoutDao mWorkoutDao;
    public ExerciseDeleteAsyncTask(WorkoutDao dao) {
        mWorkoutDao = dao;
    }
    @Override
    protected Void doInBackground(Exercise... workouts) {
        mWorkoutDao.delete(workouts);
        return null;
    }
}
