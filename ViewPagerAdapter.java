package com.example.a1.timon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.a1.timon.fragments.DoingTasks;
import com.example.a1.timon.fragments.MyPostedAndDoingTasks;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        if(position == 0){return "Заказанные мной";}
        else{return "Выполняемые мной";}
    }

    @Override
    public Fragment getItem(int i) {

        DoingTasks doingTasks = new DoingTasks();
        Bundle bundle = new Bundle();
        bundle.getInt("position", i);
        doingTasks.setArguments(bundle);
        Log.i("position", String.valueOf(i));

        return doingTasks;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
