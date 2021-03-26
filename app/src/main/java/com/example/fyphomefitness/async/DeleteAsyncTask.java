package com.example.fyphomefitness.async;

import android.os.AsyncTask;

import com.example.fyphomefitness.model.Workout;
import com.example.fyphomefitness.persistance.WorkoutDao;

public class DeleteAsyncTask extends AsyncTask<Workout, Void, Void> {

    private WorkoutDao mWorkoutDao;
    public DeleteAsyncTask(WorkoutDao dao) {
        mWorkoutDao = dao;

    }

    @Override
    protected Void doInBackground(Workout... workouts) {
        mWorkoutDao.delete(workouts);
        return null;
    }
}
