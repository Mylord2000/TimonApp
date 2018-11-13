package com.example.a1.timon.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a1.timon.R;
import com.example.a1.timon.tasks_recycler.Task;
import com.example.a1.timon.tasks_recycler.TasksAdapter;

import java.util.ArrayList;

public class DoingTasks extends Fragment {

    public DoingTasks() {
        // Required empty public constructor
    }

    TasksAdapter tasksAdapter;
    RecyclerView recyclerView;
    public static ArrayList<Task> taskss = new ArrayList<>();
    public static ArrayList<Task> tas = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_doing_tasks, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerDoing);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        Task tasss = new Task();
        if(getArguments().getInt("position") == 0){

            tasss.setCategory("Education");
            tasss.setWayOfPaying("bank account");
            tasss.setDescription("SMMMMMMMMMMMMMMAAAAAAAAAARRRRRRRRRTTTTTTTTTTTTT");
            tasss.setTitle("Programmer");
            tasss.setPrice("150 000" + "$");
            }

        taskss.add(tasss);
        tasksAdapter = new TasksAdapter((ArrayList)taskss, DoingTasks.this);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(tasksAdapter);


        return rootView;
    }
}
