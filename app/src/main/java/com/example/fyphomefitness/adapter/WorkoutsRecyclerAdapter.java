package com.example.fyphomefitness.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workout_room_persistance.R;
import com.example.workout_room_persistance.model.Workout;

import java.util.ArrayList;

public class WorkoutsRecyclerAdapter extends RecyclerView.Adapter<WorkoutsRecyclerAdapter.ViewHolder> {

    private static final String TAG = "WorkoutRecyclerAdapter";

    private ArrayList <Workout> mWorkouts;

    private OnWorkoutListener mOnWorkoutListener;

    public WorkoutsRecyclerAdapter(ArrayList<Workout> workouts, OnWorkoutListener onWorkoutListener) {
        this.mWorkouts = workouts;
        this.mOnWorkoutListener = onWorkoutListener;

    }

    // Needed Override methods for RecyclerView Adapter
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_workout_list_item, parent, false);

        return new ViewHolder(view, mOnWorkoutListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.title.setText(mWorkouts.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return mWorkouts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        OnWorkoutListener mOnWorkoutListener;

        public ViewHolder(@NonNull View itemView, OnWorkoutListener onWorkoutListener) {
            super(itemView);
            //Connect to (note list item) Layout
            title = itemView.findViewById(R.id.text_view_title);
            this.mOnWorkoutListener = onWorkoutListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnWorkoutListener.onWorkoutClicked(getAdapterPosition());
        }
    }

    //On click listener
    public interface OnWorkoutListener {
        void onWorkoutClicked(int position);
    }
}
