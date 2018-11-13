package com.example.a1.timon.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a1.timon.DBHelper;
import com.example.a1.timon.R;
import com.example.a1.timon.tasks_recycler.Task;

import static com.example.a1.timon.R.color.colorClassic;
import static com.example.a1.timon.R.id.textView2;


public class ChooseTheWayOfPaying extends Fragment implements View.OnClickListener  {

    TextView fromPersonalAccount;
    TextView fromTerminal;
    TextView fromMobileOperator;
    TextView fromBankAccount;
    Button chooseTheWayOfPayingButton;

    SendFullTask sendFullTask;

    Task myTask;

    DBHelper dbHelper;

    boolean way1,way2,way3,way4;
    String wayOfPayingString;

    public interface SendFullTask{

        public void sending(Task myFullTask);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_choose_the_way_of_paying, container, false);

        chooseTheWayOfPayingButton = rootView.findViewById(R.id.button);
        fromPersonalAccount = rootView.findViewById(textView2);
        fromTerminal = rootView.findViewById(R.id.textView3);
        fromMobileOperator = rootView.findViewById(R.id.textView4);
        fromBankAccount = rootView.findViewById(R.id.textView5);

        dbHelper = new DBHelper(ChooseTheWayOfPaying.this.getContext());

        chooseTheWayOfPayingButton.setOnClickListener(this);
        fromBankAccount.setOnClickListener(this);
        fromMobileOperator.setOnClickListener(this);
        fromTerminal.setOnClickListener(this);
        fromPersonalAccount.setOnClickListener(this);

        myTask = getArguments().getParcelable("MyTask");

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SendFullTask) {
            sendFullTask = (SendFullTask) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragment1DataListener");
        }
    }

    @SuppressLint("ResourceAsColor")
    public void onClick(View view){

        switch(view.getId()){
            case R.id.button:
                Tasks tasks = new Tasks();
                wayOfPayingString = "";
                if(way1 == true)
                    wayOfPayingString = wayOfPayingString + "From personal account";
                if(way2 == true)
                    wayOfPayingString = wayOfPayingString + " From terminal";
                if(way3 == true)
                    wayOfPayingString = wayOfPayingString + " From mobile operator";
                if(way4 == true)
                    wayOfPayingString = wayOfPayingString + " From bank ACCOUNT";

                    ContentValues contentValues = new ContentValues();
                    SQLiteDatabase database = dbHelper.getWritableDatabase();

                contentValues.put("price", myTask.getPrice());
                contentValues.put("title", myTask.getTitle());
                contentValues.put("time", myTask.getTime());
                contentValues.put("category", myTask.getCategory());
                contentValues.put("description", myTask.getDescription());
                contentValues.put("way_of_paying", wayOfPayingString);
                contentValues.put("image_uri", myTask.getImageViewUri().toString());

                /*---------------------------------------------------Data Sending To Tasks  ----------------------------------------------------------------*/

                database.insert(dbHelper.TABLE_TASKS,null,contentValues);



                if (sendFullTask != null){
                    Log.i("----", "work");
                    sendFullTask.sending(myTask);}
                else{Log.i("----", "doesnt work");}


            case R.id.textView2:
                if(!way1){
                    fromPersonalAccount.setTextColor(Color.parseColor("#3FB98E"));
                    way1 = true;
                }
                else{
                    fromPersonalAccount.setTextColor(Color.parseColor("#000000"));
                    way1 = false;
                }break;

            case R.id.textView3:

                if(!way2){
                    fromTerminal.setTextColor(Color.parseColor("#3FB98E"));
                    way2 = true;
                }
                else{
                    fromTerminal.setTextColor(Color.parseColor("#000000"));
                    way2 = false;
                }break;

            case R.id.textView4:
                if(!way3){
                    fromMobileOperator.setTextColor(Color.parseColor("#3FB98E"));
                    way3 = true;
                }
                else{
                    fromMobileOperator.setTextColor(Color.parseColor("#000000"));
                    way3 = false;
                }break;
            case R.id.textView5:
                if(!way4){
                    fromBankAccount.setTextColor(Color.parseColor("#3FB98E"));
                    way4 = true;
                }
                else{
                    fromBankAccount.setTextColor(Color.parseColor("#000000"));
                    way4 = false;
                }break;
            default: Log.i("Error","view isnt been found");
                break;
        }
        Log.i("onClick","From personal" + way1 + " ------from terminal" + way2 + "  ---------from mobile" + way3 + " ----------from bank" + way4);

    }

}
