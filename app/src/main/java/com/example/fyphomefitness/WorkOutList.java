package com.example.fyphomefitness;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class WorkOutList extends AppCompatActivity implements MyInterface {

    public ListView mListView;
    public Exercise e;


    //fragment
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private Fragment fragment;

    private FrameLayout mFrameLayoutWorkoutPlayer;

    //List view Data
    private ArrayList<String> mExerciseNameArray = new ArrayList<>();
    private ArrayList<String> mExerciseRepsArray = new ArrayList<>();
    private ArrayList<Integer> mExerciseGifArray = new ArrayList<>();

    String[] exerciseAmount = {"1","2","3","4","5","6"};

    public String eName;

    int count;

    private Button mBtnProceed;

    //Save
    public static final String Shared_Pref = "sharedPrefs";

    //Load
    public static final String mSPWorkOutsCompleted = "text5";
    private int mWorkoutsCompleted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        mListView = findViewById(R.id.list_view_workout);
        mBtnProceed = findViewById(R.id.button_proceed);
        Intent intent = getIntent();
        eName= intent.getStringExtra("name");

        workoutArray();
        fragment = new WorkoutPlayerFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("exercises", e.getExerciseName());
        bundle.putStringArrayList("exerciseReps", e.getExerciseRep());
        fragment.setArguments(bundle);
//        intent = new Intent(this, WorkoutPlaylistFragment.class);
//        intent.putExtra("array_list", exercises);

        fragmentManager = getSupportFragmentManager();
        mFrameLayoutWorkoutPlayer = findViewById(R.id.frameLayout2);

        mBtnProceed.setOnClickListener(v -> {
                fragmentTransaction = fragmentManager.beginTransaction().replace(R.id.frameLayout2, fragment).addToBackStack(null);
                fragmentTransaction.commit();
                mFrameLayoutWorkoutPlayer.setClickable(true);
                mBtnProceed.setVisibility(View.INVISIBLE);
        });

        //Array Adapter
        toolbar.setTitle(eName);
        CustomAdapter customAdapter = new CustomAdapter();
        mListView.setAdapter(customAdapter);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        saveData();
        count = fragmentManager.getBackStackEntryCount();
        for (int i = 0; i<count;i++){
            fragmentManager.popBackStack();
        }
        mFrameLayoutWorkoutPlayer.setClickable(false);
        mBtnProceed.setVisibility(View.VISIBLE);
    }

    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mExerciseNameArray.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup parent) {
            View view1 = getLayoutInflater().inflate(R.layout.exercise_rowdata, parent, false);

            TextView name = view1.findViewById((R.id.text_view_exercise_name));
            TextView amount = view1.findViewById((R.id.text_view_exercise_reps));

            name.setText(e.getExerciseName().get(i));
            amount.setText("x" + e.getExerciseRep().get(i));
            return view1;
        }

        //Turn off list view click event
        @Override
        public boolean isEnabled(int position) {
            return false;
        }
    }

    public void workoutArray(){

        if (eName.equals("Upper Body")){

            e = new Exercise(mExerciseNameArray, mExerciseRepsArray);

            mExerciseNameArray.add(0,"Push Ups");
            mExerciseNameArray.add(1,"Lateral Push Ups");
            mExerciseNameArray.add(2,"Shoulder Push Ups");
            mExerciseNameArray.add(3,"Incline Push Ups");
            mExerciseNameArray.add(4,"Knee Push Ups");

            mExerciseRepsArray.add(0,"5");
            mExerciseRepsArray.add(1,"5");
            mExerciseRepsArray.add(2,"5");
            mExerciseRepsArray.add(3,"6");
            mExerciseRepsArray.add(4,"6");


        }
        else if (eName.equals("Lower body")){

            e = new Exercise(mExerciseNameArray, mExerciseRepsArray);

            mExerciseNameArray.add(0,"Legs1");
            mExerciseNameArray.add(1,"Legs2");
            mExerciseNameArray.add(2,"Legs3");
            mExerciseNameArray.add(3,"Legs4");
            mExerciseNameArray.add(4,"Legs5");

            mExerciseRepsArray.add(0,"1");
            mExerciseRepsArray.add(0,"2");
            mExerciseRepsArray.add(0,"3");
            mExerciseRepsArray.add(0,"4");
            mExerciseRepsArray.add(0,"5");
        }

        loadData();
        saveData();
    }


    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(Shared_Pref, MODE_PRIVATE);
        mWorkoutsCompleted = sharedPreferences.getInt(mSPWorkOutsCompleted, 0);
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(Shared_Pref, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(mSPWorkOutsCompleted, mWorkoutsCompleted);
        editor.apply();

        Toast.makeText(WorkOutList.this, "Workout Count Saved", Toast.LENGTH_SHORT).show();
        return;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        e.getExerciseName().clear();
        e.getExerciseRep().clear();
    }

    @Override
    public void closeActivity(){
        mWorkoutsCompleted = mWorkoutsCompleted + 1;
        saveData();
        loadData();
        mBtnProceed.setVisibility(View.VISIBLE);
        finish();
    }

}