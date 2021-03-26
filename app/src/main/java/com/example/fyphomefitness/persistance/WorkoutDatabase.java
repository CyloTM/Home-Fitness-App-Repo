package com.example.fyphomefitness.persistance;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.fyphomefitness.model.Exercise;
import com.example.fyphomefitness.model.Workout;

@Database(entities = {Workout.class, Exercise.class}, version=1)
public abstract class WorkoutDatabase extends RoomDatabase {
   public static final String DATABASE_NAME = "workout_db";

   private static WorkoutDatabase instance;

   static WorkoutDatabase getInstance(final Context context){
       if(instance == null)
           instance = Room.databaseBuilder(
                   context.getApplicationContext(),
                   WorkoutDatabase.class,
                   DATABASE_NAME
           ).build();
       return instance;
    }

    public abstract WorkoutDao getWorkoutDao();

}
