package com.example.fyphomefitness.async;

import android.os.AsyncTask;

import com.example.fyphomefitness.model.Workout;
import com.example.fyphomefitness.persistance.WorkoutDao;

public class InsertAsyncTask extends AsyncTask<Workout, Void, Void> {

    private WorkoutDao mWorkoutDao;
    public InsertAsyncTask(WorkoutDao dao) {
        mWorkoutDao = dao;

    }

    @Override
    protected Void doInBackground(Workout... workouts) {
        mWorkoutDao.insertWorkouts(workouts);
        return null;
    }
}
