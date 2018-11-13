package com.example.a1.timon.tasks_recycler;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a1.timon.R;
import com.example.a1.timon.fragments.TasksData;

import java.util.ArrayList;
import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.Task> {

    List<com.example.a1.timon.tasks_recycler.Task> mtasks;
    Fragment fragment;
    Context context;
    OnSendData onSendData;

    public interface OnSendData{

        public void click(com.example.a1.timon.tasks_recycler.Task taskss);

    }

    public TasksAdapter(List<com.example.a1.timon.tasks_recycler.Task> tasks, Fragment fragment) {
        this.mtasks = tasks;
        this.fragment = fragment;
        onSendData = (OnSendData)fragment.getActivity();
    }

    @NonNull
    @Override
    public Task onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        context = viewGroup.getContext();
        int layout = R.layout.tasks;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layout,viewGroup,false);
        Task task = new Task(view, mtasks);
        return task;

    }

    @Override
    public void onBindViewHolder(@NonNull TasksAdapter.Task task, int i) {
        task.bind(i);
    }

    @Override
    public int getItemCount() {
        return mtasks.size();
    }



    public class Task extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView price;
        TextView title;
        TextView time;
        ImageView image;

        public Task(@NonNull View itemView, List<com.example.a1.timon.tasks_recycler.Task> tasks) {
            super(itemView);
            itemView.setOnClickListener(this);
            price = itemView.findViewById(R.id.priceTaskTextView);
            title = itemView.findViewById(R.id.tasksTextView);
            time = itemView.findViewById(R.id.timeTaskTextView);
            image = itemView.findViewById(R.id.tasksImageView);

        }

        public void  bind(int position){

            price.setText(mtasks.get(position).price.toString());
            title.setText(mtasks.get(position).title.toString());
            time.setText(mtasks.get(position).time.toString());

            image.setImageURI(mtasks.get(position).imageViewUri);
//
//            Glide.with(fragment).load(mtasks.get(position).imageViewInt).into(image);



        }

        @Override
        public void onClick(View v) {
            if (onSendData != null){
                Log.i("onSendData", "work in forecast adapter");
                onSendData.click(mtasks.get(getAdapterPosition()));}
            else{Log.i("onSendData", " dont work");}

        }
    }
}
