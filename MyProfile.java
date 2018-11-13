package com.example.a1.timon.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a1.timon.R;

public class MyProfile extends Fragment {

    EditText sfullName;
    EditText sbirthday;
    String fullName;
    String birthday;
    Button button;

    SendDataToSavedMyProfile sendDataToSavedMyProfile;


    public interface SendDataToSavedMyProfile{
        public void sendProfileData(String fullName, String birthday,boolean statusOfProfile);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        sendDataToSavedMyProfile = (SendDataToSavedMyProfile)context;
    }

    private OnFragmentInteractionListener mListener;

    public MyProfile() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my_profile, container, false);

        sfullName = rootView.findViewById(R.id.fullName);
        sbirthday = rootView.findViewById(R.id.birthday);
        button = rootView.findViewById(R.id.sendData);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullName = sfullName.getText().toString();
                birthday = sbirthday.getText().toString();
                sendDataToSavedMyProfile.sendProfileData(fullName, birthday,true);
            }
        });

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
