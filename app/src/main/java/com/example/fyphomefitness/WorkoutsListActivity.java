package com.example.fyphomefitness;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workout_room_persistance.adapter.WorkoutsRecyclerAdapter;
import com.example.workout_room_persistance.model.Workout;
import com.example.workout_room_persistance.persistance.WorkoutRepository;
import com.example.workout_room_persistance.util.VerticalSpacingItemDecorator;

import java.util.ArrayList;

//import com.example.workout_room_persistance.model.WorkoutWithExercises;

public class WorkoutsListActivity extends AppCompatActivity implements
        WorkoutsRecyclerAdapter.OnWorkoutListener,
        View.OnClickListener
{

    public final static String TAG = "WorkoutsListActivity";

    // Ui components
    private RecyclerView mRecyclerView;

    // Variables
    private ArrayList<Workout> mWorkouts= new ArrayList();
    private WorkoutsRecyclerAdapter mWorkoutsRecyclerAdapter;

    private WorkoutRepository mWorkoutRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_workout_list);

        mWorkoutRepository = new WorkoutRepository(this);

        mRecyclerView = findViewById(R.id.recycler_view);
        findViewById(R.id.fab).setOnClickListener(this);
        initRecyclerView();
        retrieveWorkouts();

        setSupportActionBar(findViewById(R.id.workout_list_toolbar));
        setTitle("Workouts");

    }
    private void initRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
        mRecyclerView.addItemDecoration(itemDecorator);
        new ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(mRecyclerView);
        mWorkoutsRecyclerAdapter = new WorkoutsRecyclerAdapter(mWorkouts, this);
        mRecyclerView.setAdapter(mWorkoutsRecyclerAdapter);

    }
    private void retrieveWorkouts(){
        mWorkoutRepository.retrieveWorkoutTask().observe(this, workouts -> {
            if(mWorkouts.size()>0){
                mWorkouts.clear();
            }
            if(workouts!=null){
                mWorkouts.addAll(workouts);
            }
            mWorkoutsRecyclerAdapter.notifyDataSetChanged();
        });
    }

    private void deleteWorkout(Workout workout){
        mWorkouts.remove(workout);
        mWorkoutRepository.deleteWorkout(workout);
        mWorkoutsRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ExerciseListActivity.class);
        startActivity(intent);
    }

    @Override
    public void onWorkoutClicked(int position) {
        Log.d(TAG, "Note " + position + " has been clicked");
        Intent intent = new Intent(this, ExerciseListActivity.class);
        intent.putExtra("selected_workout", mWorkouts.get(position));
        startActivity(intent);

    }

    private ItemTouchHelper.SimpleCallback itemTouchHelperCallBack = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT){

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            deleteWorkout(mWorkouts.get(viewHolder.getAdapterPosition()));
        }
    };
}