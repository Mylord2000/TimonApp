package com.example.a1.timon;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.a1.timon.fragments.ChooseTheWayOfPaying;
import com.example.a1.timon.fragments.MyPostedAndDoingTasks;
import com.example.a1.timon.fragments.MyProfile;
import com.example.a1.timon.fragments.Tasks;
import com.example.a1.timon.fragments.TasksData;
import com.example.a1.timon.fragments.savedMyProfile;
import com.example.a1.timon.tasks_recycler.Task;
import com.example.a1.timon.tasks_recycler.TasksAdapter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,MyProfile.SendDataToSavedMyProfile
        ,MyProfile.OnFragmentInteractionListener,ChooseTheWayOfPaying.SendFullTask,TasksAdapter.OnSendData {

    boolean statusOfProfile = false;
    String fullName;
    String birthday;

    MyProfile myProfile = new MyProfile();
    savedMyProfile profile = new savedMyProfile();
    Tasks tasks = new Tasks();

    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Задании");
        toolbar.setBackgroundColor(Color.parseColor("#3FB98E"));

        fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.container,tasks,null).commit();



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.main_menu) {
            setTitle("Задания");
            fm.beginTransaction().replace(R.id.container,tasks,null).commit();
        } else if (id == R.id.my_profile) {

            if(statusOfProfile){
                setTitle("Мой профиль");
                fm.beginTransaction().replace(R.id.container, profile, null).commit();

            }
            else {
                setTitle("Регистрация");
                fm.beginTransaction().replace(R.id.container, myProfile, null).commit();
            }

        } else if (id == R.id.my_tasks) {
            MyPostedAndDoingTasks myPostedAndDoingTasks = new MyPostedAndDoingTasks();
            fm.beginTransaction().replace(R.id.container, myPostedAndDoingTasks, null).commit();

        } else if (id == R.id.history) {

        } else if (id == R.id.property) {

        } else if (id == R.id.feedback) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void sendProfileData(String fullName, String birthday,boolean statusOfProfile) {
        Log.i("sendProfileData","works");
        this.statusOfProfile = true;
        Bundle bundle = new Bundle();
        bundle.putString("fullName", fullName);
        bundle.putString("birthday", birthday);
        profile.setArguments(bundle);
        fm.beginTransaction().replace(R.id.container,profile,null).commit();
        setTitle("Мой профиль");
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void sending(Task myFullTask) {
        Tasks tasks = new Tasks();
        Bundle bundle = new Bundle();
        bundle.putParcelable("MyTaskMain",myFullTask);
        tasks.setArguments(bundle);
        fm.beginTransaction().replace(R.id.container,tasks,null).commit();
    }

    @Override
    public void click(Task taskss) {
        TasksData tasksData = new TasksData();
        Bundle bundle = new Bundle();
        bundle.putParcelable("taskForTasksData", taskss);
        tasksData.setArguments(bundle);
        fm.beginTransaction().replace(R.id.container, tasksData,null).addToBackStack(null).commit();

    }
}
