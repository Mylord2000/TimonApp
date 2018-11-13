package com.example.a1.timon.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.a1.timon.R;
import com.example.a1.timon.ViewPagerAdapter;
import com.example.a1.timon.tasks_recycler.Task;
import com.example.a1.timon.tasks_recycler.TasksAdapter;

import java.util.ArrayList;
import java.util.List;


public class MyPostedAndDoingTasks extends Fragment {

    Toolbar toolbar;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    TabLayout tabLayout;

    public MyPostedAndDoingTasks() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_my_posted_and_doing_tasks, container, false);

        tabLayout = rootView.findViewById(R.id.tabs);
        toolbar = rootView.findViewById(R.id.toolBar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        viewPager = rootView.findViewById(R.id.pager);
        viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

        return rootView;

    }
}
