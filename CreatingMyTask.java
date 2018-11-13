package com.example.a1.timon.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.a1.timon.R;
import com.example.a1.timon.tasks_recycler.Task;

import java.io.IOException;
import java.net.URI;
import java.util.Calendar;
import java.util.Date;

import static android.app.Activity.RESULT_OK;


public class CreatingMyTask extends Fragment implements View.OnClickListener{

    EditText creatingMyTaskTitleEditText;
    EditText priceTaskEditView;
    EditText descriptionMyTaskEditView;
    TextView addImage;
    ImageView addImageImageView;
    Button chooseWayOfPaying;
    Spinner spinner;

    Task myTask;

    Uri selectedImage;

    Intent photoPickerIntent;

    String[] data = {"Образование", "Финансы", "Уборка", "Кухня", "ИТ"};

    static final int GALLERY_REQUEST = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_creating_my_task, container, false);

        myTask = new Task();

        creatingMyTaskTitleEditText = rootView.findViewById(R.id.creatingMyTaskTitleEditText);
        descriptionMyTaskEditView = rootView.findViewById(R.id.descriptionMyTaskEditView);
        priceTaskEditView = rootView.findViewById(R.id.priceTaskTextView);
        addImage = rootView.findViewById(R.id.creatingMyTaskImageTextView);
        addImageImageView = rootView.findViewById(R.id.creatingMyTaskImageView);
        chooseWayOfPaying = rootView.findViewById(R.id.chooseWayOfPayingButton);


        addImageImageView.setOnClickListener(this);
        addImage.setOnClickListener(this);
        chooseWayOfPaying.setOnClickListener(this);


        // адаптер
        ArrayAdapter<String> LTRadapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, data);
        LTRadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);


        spinner = rootView.findViewById(R.id.spinner);
        spinner.setAdapter(LTRadapter);
        spinner.setPrompt("Categorogy");
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i("work", String.valueOf(id) + " " + String.valueOf(position) + " ");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i("work","onNothingSelected");
            }
        });

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.creatingMyTaskImageView:
                photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/");
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST);

                Log.i("addImage","work work");
                break;
            case R.id.creatingMyTaskImageTextView:
                photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/");
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
                Log.i("addImage","work work");
                break;
            case R.id.chooseWayOfPayingButton:
                ChooseTheWayOfPaying fragmentChooseTheWayOfPaying = new ChooseTheWayOfPaying();

                /*---------------------------------------------------------Editing MyTask---------------------------------------------------------------------*/

                Date currentTime = Calendar.getInstance().getTime();
                Log.i("qweeeerty",currentTime.toString());
                myTask.setImageViewUri((Uri)selectedImage);
                myTask.setPrice(priceTaskEditView.getText().toString());
                myTask.setTime(currentTime.toString());
                myTask.setCategory("Образавание");
                myTask.setTitle(creatingMyTaskTitleEditText.getText().toString());
                myTask.setDescription(descriptionMyTaskEditView.getText().toString());

                /*---------------------------------------------------Data Sending To ChooseWayOfPaying----------------------------------------------------------------*/
                Bundle bundle = new Bundle();
                bundle.putParcelable("MyTask", myTask);
                fragmentChooseTheWayOfPaying.setArguments(bundle);

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,fragmentChooseTheWayOfPaying,null).addToBackStack(null).commit();

                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode) {
            case GALLERY_REQUEST:
                if(resultCode == RESULT_OK){
                    selectedImage = (Uri)data.getData();
                    addImageImageView.setImageURI(selectedImage);
                }
        }

    }
}
