package com.example.a1.timon.fragments;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.a1.timon.DBHelper;
import com.example.a1.timon.R;
import com.example.a1.timon.tasks_recycler.Task;
import com.example.a1.timon.tasks_recycler.TasksAdapter;

import java.util.ArrayList;
import java.util.List;


public class Tasks extends Fragment{

    Button addMyTask;
    TasksAdapter  tasksAdapter;
    RecyclerView recyclerView;
    public static ArrayList<Task> tasks = new ArrayList<>();
    Context context;
    DBHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_tasks,container,false);

        recyclerView = rootView.findViewById(R.id.tasksRecycler);
        addMyTask = rootView.findViewById(R.id.addMyTask);

        dbHelper = new DBHelper(Tasks.this.getContext());

        context = getActivity();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        getTasks();



        tasksAdapter = new TasksAdapter((ArrayList)tasks, Tasks.this);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(tasksAdapter);

        addMyTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*--------------------------------------------------------ADD NEW USER'S TASK-------------------------------------------------- */
                CreatingMyTask creatingMyTask = new CreatingMyTask();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,creatingMyTask,null).addToBackStack(null).commit();
            }
        });

        return rootView;
    }

    private void getTasks() {

//        try {
//            Task taskkk = getArguments().getParcelable("MyTaskMain");
//            tasks.add(taskkk);
//        }catch (NullPointerException e){
//            e.printStackTrace();
//        }

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        tasks.clear();

        Cursor cursor = database.query(dbHelper.TABLE_TASKS,null,null,null,null,null,null);
        if(cursor.moveToFirst() && tasks.isEmpty()){


            int idIndex = cursor.getColumnIndex(dbHelper.KEY_ID);
            int nameIndex = cursor.getColumnIndex("title");
            int priceIndex = cursor.getColumnIndex("price");
            int image_uriIndex = cursor.getColumnIndex("image_uri");
            int descriptionIndex = cursor.getColumnIndex("description");
            int categoryIndex = cursor.getColumnIndex("category");
            int timeIndex = cursor.getColumnIndex("time");
            int way_of_payingIndex = cursor.getColumnIndex("way_of_paying");
            Task special_task;
            do{

                special_task = new Task();

                special_task.setId(cursor.getInt(idIndex));
                special_task.setTitle(cursor.getString(nameIndex));
                special_task.setPrice(cursor.getString(priceIndex));
                special_task.setImageViewUri(Uri.parse(cursor.getString(image_uriIndex)));
                special_task.setDescription(cursor.getString(descriptionIndex));
                special_task.setTime(cursor.getString(timeIndex));
                special_task.setWayOfPaying(cursor.getString(way_of_payingIndex));
                special_task.setCategory(cursor.getString(categoryIndex));
                tasks.add(special_task);
                Log.i("qwerty", Integer.toString(special_task.id));

            }while(cursor.moveToNext());

        }

        cursor.close();

    }


}
