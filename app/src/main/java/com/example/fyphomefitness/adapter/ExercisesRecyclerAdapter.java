package com.example.fyphomefitness.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fyphomefitness.R;
import com.example.fyphomefitness.model.Exercise;
import com.example.fyphomefitness.persistance.WorkoutRepository;

import java.util.ArrayList;

public class ExercisesRecyclerAdapter extends RecyclerView.Adapter<ExercisesRecyclerAdapter.ViewHolder>{

    private static final String TAG = "ExercisesRecyclerAdapter";

    private ArrayList <Exercise> mExercises;

    private OnExerciseListener mOnExerciseListener;

    private WorkoutRepository mWorkoutRepository;

    public ExercisesRecyclerAdapter(ArrayList<Exercise> exercise, OnExerciseListener onExerciseListener) {
        this.mExercises = exercise;
        this.mOnExerciseListener = onExerciseListener;

    }

    // Needed Override methods for RecyclerView Adapter
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_exercise_list_item, parent, false);
        return new ViewHolder(view, mOnExerciseListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.title.setText(mExercises.get(i).getTitle());
        holder.repetitions.setText(mExercises.get(i).getRepetitions());
        holder.check.setOnClickListener(v -> {
            holder.disableEditMode();
//            updateExercise(i);
            mExercises.get(i).setTitle(holder.title.getText().toString());
            mExercises.get(i).setRepetitions(holder.repetitions.getText().toString());
            Log.d(TAG, "disabled edit mode "+ holder.repetitions.getText().toString()+ " " +holder.title.getText().toString());
            notifyDataSetChanged();
//            mWorkoutRepository.updateExercise(mExercises.get(i));
        });
        holder.itemView.setOnClickListener(v -> holder.enableEditMode());
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mExercises.size();
    }

    private void updateExercise(int position){mWorkoutRepository.updateExercise(mExercises.get(position));}

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView check;
        TextView title, repetitions;
        EditText editTextRepetitions, editTextTitle;
        OnExerciseListener mOnExerciseListener;

        public ViewHolder(@NonNull View itemView, OnExerciseListener onExerciseListener) {
            super(itemView);

            //Connect to (note list item) Layout
            check = itemView.findViewById(R.id.image_view_check);
            title = itemView.findViewById(R.id.text_view_title);
            repetitions= itemView.findViewById(R.id.text_view_repetitions);
            editTextRepetitions= itemView.findViewById(R.id.edit_text_repetitions);
            editTextTitle = itemView.findViewById(R.id.edit_text_title);
            this.mOnExerciseListener = onExerciseListener;
        }

        public void enableEditMode(){
            repetitions.setVisibility(View.GONE);
            title.setVisibility(View.GONE);
            editTextRepetitions.setVisibility(View.VISIBLE);
            editTextTitle.setVisibility(View.VISIBLE);
            editTextRepetitions.setCursorVisible(true);
            editTextTitle.requestFocus();
//            mFocusedItem = getLayoutPosition();

//            mMode = EDIT_MODE_ENABLED;
        }

        public void disableEditMode() {
            title.setText(editTextTitle.getText().toString());
            repetitions.setText(editTextRepetitions.getText().toString());
            if(editTextRepetitions.getText().toString().equals("")){
                repetitions.setText("0");
            }
            if(editTextTitle.getText().toString().equals("")){
                title.setText("New Exercise");
            }


            title.setVisibility(View.VISIBLE);
            repetitions.setVisibility(View.VISIBLE);
            editTextRepetitions.setVisibility(View.GONE);
            editTextTitle.setVisibility(View.GONE);
            editTextRepetitions.setCursorVisible(false);
            editTextRepetitions.clearFocus();
//            notifyItemChanged(getLayoutPosition());
//            mMode = EDIT_MODE_DISABLED;
        }
    }

    //On click listener
    public interface OnExerciseListener {
        int onExerciseClicked(int position);
    }

}
