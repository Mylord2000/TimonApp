package com.example.a1.timon.fragments;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a1.timon.DBHelper;
import com.example.a1.timon.R;
import com.example.a1.timon.tasks_recycler.Task;

public class TasksData extends Fragment implements ChanchingPriceDialog.OnInputSeleceted{

    TextView categorogyTasksDataTextView;
    TextView titleTasksDataTextView;
    TextView priceTasksDataTextView;
    TextView wayOfPayingTasksDataTextView;
    TextView descriptionTasksDataTextView;
    Button offerPrice,otklik;
    ImageView imageView;

    Task task;

    DBHelper db;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tasks_data, container, false);

        db = new DBHelper(TasksData.this.getContext());

        categorogyTasksDataTextView = rootView.findViewById(R.id.categorogyTasksDataTextView);
        titleTasksDataTextView = rootView.findViewById(R.id.titleTasksDataTextView);
        priceTasksDataTextView = rootView.findViewById(R.id.priceTasksDataTextView);
        wayOfPayingTasksDataTextView = rootView.findViewById(R.id.wayPayingTextView);
        descriptionTasksDataTextView = rootView.findViewById(R.id.descriptionTasksDataTextView);
        offerPrice = rootView.findViewById(R.id.offerPrice);
        otklik = rootView.findViewById(R.id.otklyk);
        imageView = rootView.findViewById(R.id.imageView);

        task = getArguments().getParcelable("taskForTasksData");

        titleTasksDataTextView.setText(task.getTitle());
        priceTasksDataTextView.setText(task.getPrice());
        wayOfPayingTasksDataTextView.setText(task.getWayOfPaying());
        descriptionTasksDataTextView.setText(task.getDescription());
        categorogyTasksDataTextView.setText(task.category);

        offerPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        return rootView;
    }

    public void openDialog(){

        ChanchingPriceDialog chanchingPriceDialog = new ChanchingPriceDialog();
        chanchingPriceDialog.setOnInputSeleceted(this);
        chanchingPriceDialog.show(getFragmentManager(),"changing price");
    }

    @Override
    public void sendInput(String input) {

        SQLiteDatabase database = db.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("price",input);
        int updCount = database.update(DBHelper.TABLE_TASKS,contentValues, "_id" + "= ?", new String[] {String.valueOf(task.getId())});

        Tasks tasls = new Tasks();

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, tasls,null).commit();
    }
}
