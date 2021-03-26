package com.example.fyphomefitness.async;

import android.os.AsyncTask;

import com.example.fyphomefitness.model.Workout;
import com.example.fyphomefitness.persistance.WorkoutDao;

public class UpdateAsyncTask extends AsyncTask<Workout, Void, Void> {

    private WorkoutDao mWorkoutDao;
    public UpdateAsyncTask(WorkoutDao dao) {
        mWorkoutDao = dao;
    }

    @Override
    protected Void doInBackground(Workout... workouts) {
        mWorkoutDao.update(workouts);
        return null;
    }
}

