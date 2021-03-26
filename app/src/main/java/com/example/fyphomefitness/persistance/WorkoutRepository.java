package com.example.fyphomefitness.persistance;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.fyphomefitness.async.DeleteAsyncTask;
import com.example.fyphomefitness.async.ExerciseDeleteAsyncTask;
import com.example.fyphomefitness.async.ExerciseInsertAsyncTask;
import com.example.fyphomefitness.async.ExerciseUpdateAsyncTask;
import com.example.fyphomefitness.async.InsertAsyncTask;
import com.example.fyphomefitness.async.UpdateAsyncTask;
import com.example.fyphomefitness.model.Exercise;
import com.example.fyphomefitness.model.Workout;

import java.util.List;

public class WorkoutRepository {

    private com.example.fyphomefitness.persistance.WorkoutDatabase mWorkoutDatabase;

    public WorkoutRepository(Context context) {
        mWorkoutDatabase = com.example.fyphomefitness.persistance.WorkoutDatabase.getInstance(context);
    }

    public void insertWorkoutTask(Workout workout){
        new InsertAsyncTask(mWorkoutDatabase.getWorkoutDao()).execute(workout);

    }

    public void updateWorkout(Workout workout){
        new UpdateAsyncTask(mWorkoutDatabase.getWorkoutDao()).execute(workout);

    }

    public LiveData<List<Workout>> retrieveWorkoutTask(){
        return mWorkoutDatabase.getWorkoutDao().getAllData();
    }

    public void deleteWorkout(Workout workout){
        new DeleteAsyncTask(mWorkoutDatabase.getWorkoutDao()).execute(workout);

    }

    public void insertExerciseTask(Exercise exercise){
        new ExerciseInsertAsyncTask(mWorkoutDatabase.getWorkoutDao()).execute(exercise);

    }

    public void updateExercise(Exercise exercise){
        new ExerciseUpdateAsyncTask(mWorkoutDatabase.getWorkoutDao()).execute(exercise);
    }

    public void deleteExercise(Exercise exercise){
        new ExerciseDeleteAsyncTask(mWorkoutDatabase.getWorkoutDao()).execute(exercise);

    }

    public LiveData<List<Exercise>> retrieveExerciseTask(){
        return mWorkoutDatabase.getWorkoutDao().getAll();
    }
}
