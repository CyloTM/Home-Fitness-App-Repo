package com.example.fyphomefitness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, NavDrawInterface {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    Toolbar toolbar;
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    Fragment fragment;
    FrameLayout frameLayout;
    ListView listView;
    View view;

    private TextView mTxtVwWorkoutsComplete;
    public static final String mSPWorkOutsCompleted ="text5";
    private int mWorkoutsCompleted;

    @Override
    public void lockDrawer() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    @Override
    public void unlockDrawer() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    String[] workoutsRoutines = {"Upper Body", "Lower body", "Upper Body", "Lower body"};
    int[] workoutsBackgrounds = {R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground};

    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.setScrimColor(getResources().getColor(android.R.color.transparent));
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setNavigationIcon(R.drawable.ic_android_black_24dp);
        frameLayout = findViewById(R.id.frameLayout);
        mTxtVwWorkoutsComplete = findViewById(R.id.text_view_workouts_completed);

        listView = findViewById(R.id.listview);
        view = findViewById(R.id.mainActivity2);
        CustomAdapter customAdapter = new CustomAdapter();


        //Array Adapter
        listView.setAdapter(customAdapter);


        toolbar.setNavigationOnClickListener(v -> {

            drawerLayout.openDrawer(GravityCompat.START);
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                onBackPressed();
            }


            if (fragment != null) {
                count = fragmentManager.getBackStackEntryCount();
                for (int i = 0; i < count; i++) {
                    fragmentManager.popBackStack();
                    drawerLayout.closeDrawer(GravityCompat.START);
                }

                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction().remove(fragment);

                fragmentTransaction.commit();
                frameLayout.setClickable(false);
            }
            toolbar.setTitle(R.string.app_name);
            toolbar.setNavigationIcon(R.drawable.ic_android_black_24dp);
        });

        loadData();
        updateData();

    }

    public void loadData() {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        mWorkoutsCompleted = sharedPreferences.getInt(mSPWorkOutsCompleted,0);
    }

    public void updateData() {
        mTxtVwWorkoutsComplete.setText(String.valueOf(mWorkoutsCompleted));
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else super.onBackPressed();
        count = fragmentManager.getBackStackEntryCount();
        for (int i = 0; i < count; i++) {
            fragmentManager.popBackStack();
        }
        toolbar.setNavigationIcon(R.drawable.ic_android_black_24dp);
        toolbar.setTitle(R.string.app_name);
        frameLayout.setClickable(false);
    }

    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return workoutsBackgrounds.length;
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
            View view1 = getLayoutInflater().inflate(R.layout.rowdata, null);

            TextView name = view1.findViewById((R.id.workouts));
            ImageView image = view1.findViewById((R.id.background));

            name.setText(workoutsRoutines[i]);
            image.setImageResource(workoutsBackgrounds[i]);

            view1.setOnClickListener(v -> {
                Intent intent = new Intent(getApplicationContext(), WorkOutList.class);
                intent.putExtra("name", workoutsRoutines[i]);
                startActivity(intent);
                Toast.makeText(MainActivity.this, workoutsRoutines[i], Toast.LENGTH_SHORT).show();
            });

            return view1;

        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        frameLayout.setClickable(true);
        switch (item.getItemId()) {
            case R.id.nav_profile:
                fragment = new profileFragment();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).addToBackStack(null);
                fragmentTransaction.commit();
                toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24);
                toolbar.setTitle(item.getTitle());

                break;

            case R.id.nav_custom_workouts:
                fragment = new customWorkoutFragment();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction().replace(R.id.frameLayout, new customWorkoutFragment()).addToBackStack(null);
                fragmentTransaction.commit();
                toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24);
                toolbar.setTitle(item.getTitle());

                break;
        }
        item.setChecked(true);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}