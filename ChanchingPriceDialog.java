package com.example.a1.timon.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.a1.timon.DBHelper;
import com.example.a1.timon.R;
import com.example.a1.timon.tasks_recycler.Task;

public class ChanchingPriceDialog extends android.support.v4.app.DialogFragment {

        public interface OnInputSeleceted{

            void sendInput(String input);

        }

    public OnInputSeleceted onInputSeleceted;



    EditText chanchingPriceEditText;
    Button cancel, change;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.layout_dialog, container, false);



        chanchingPriceEditText = rootView.findViewById(R.id.cpet);
        cancel = rootView.findViewById(R.id.cancel);
        change = rootView.findViewById(R.id.change);



        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = chanchingPriceEditText.getText().toString();

                if(!input.equals("")){
                    onInputSeleceted.sendInput(input);
                }

                getDialog().dismiss();
            }
        });
        return rootView;
    }

    public void setOnInputSeleceted(Fragment fragment) {
        try {

            onInputSeleceted =(OnInputSeleceted)fragment;

        }catch(Exception e){
            Log.i("ERROrR","ERROR" + e);}

    }
}

