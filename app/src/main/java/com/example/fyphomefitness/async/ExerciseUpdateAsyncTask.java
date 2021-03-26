package com.example.fyphomefitness.async;

import android.os.AsyncTask;

import com.example.fyphomefitness.model.Exercise;
import com.example.fyphomefitness.persistance.WorkoutDao;

public class ExerciseUpdateAsyncTask extends AsyncTask<Exercise, Void, Void> {

    private WorkoutDao mWorkoutDao;

    public ExerciseUpdateAsyncTask(WorkoutDao dao) {
        mWorkoutDao = dao;
    }

    @Override
    protected Void doInBackground(Exercise... workouts) {
        mWorkoutDao.update(workouts);
        return null;
    }
}

