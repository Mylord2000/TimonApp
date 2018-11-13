package com.example.a1.timon.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.a1.timon.R;


public class savedMyProfile extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_saved_my_profile, container, false);

        ImageView profileImageView = rootView.findViewById(R.id.profileImageView);
        TextView fullNameTextView = rootView.findViewById(R.id.fullNameTextView);
        TextView birthdayTextView = rootView.findViewById(R.id.birthdayTextView);

        Glide.with(savedMyProfile.this).load(R.drawable.ic_launcher_background).apply(RequestOptions.circleCropTransform()).into(profileImageView);
        fullNameTextView.setText(getArguments().getString("fullName"));
        birthdayTextView.setText(getArguments().getString("birthday"));

        return rootView;}


}
