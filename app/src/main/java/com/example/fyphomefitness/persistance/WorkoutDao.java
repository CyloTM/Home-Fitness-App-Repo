package com.example.fyphomefitness.persistance;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fyphomefitness.model.Exercise;
import com.example.fyphomefitness.model.Workout;

import java.util.List;

@Dao
public interface WorkoutDao {

    @Insert
    long[] insertWorkouts(Workout... workout);

    @Query("SELECT * FROM workouts")
    LiveData<List<Workout>> getAllData();

    @Delete
    int delete(Workout... workouts);

    @Update
    int update(Workout... workouts);

    @Update
    int update(Exercise... exercise);

    @Insert
    long[] insertExercise(Exercise... exercise);

    @Delete
    int delete(Exercise... exercise);

    @Query("SELECT * FROM exercises")
    LiveData<List<Exercise>> getAll();
}
