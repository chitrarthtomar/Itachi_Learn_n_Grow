package com.example.hp15p077tx.courses.UI;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.hp15p077tx.courses.R;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    File f = new File("log.txt");
    private DrawerLayout dl;
    SharedPreferences sharedPreferences;
    private ActionBarDrawerToggle actionToggle;
    Toolbar toolbaar;
    TextView tw;

    FragmentTransaction fragmentTransaction;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //navigation bar code
        toolbaar = (Toolbar)findViewById(R.id.act_nav);
        tw=(TextView)findViewById(R.id.Heading);
        tw.setText(" ");
        setSupportActionBar(toolbaar);
        dl = (DrawerLayout)findViewById(R.id.draw);
        actionToggle = new ActionBarDrawerToggle(this,dl,R.string.open,R.string.close);
        //TextView tx = (TextView)findViewById(R.id.textView);
        //tx.setText("Dear" + sharedPreferences.getString("User","User")+"Learn and Grow");
        dl.addDrawerListener(actionToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //navigation bar code ends

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,new HomeFragment());
        fragmentTransaction.commit();
        tw.setText("Home");
        setSupportActionBar(toolbaar);
        navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int size = navigationView.getMenu().size();
                for (int i = 0; i < size; i++) {
                    navigationView.getMenu().getItem(i).setChecked(false);
                }
                String name = sharedPreferences.getString("Flag", "default value");
                boolean a=name.toString().equals("True");
                if(a)
                    navigationView.getMenu().getItem(size-1).setTitle("Logout");
                else
                    navigationView.getMenu().getItem(size-1).setTitle("Login/Register");
                switch (item.getItemId())
                {
                    case R.id.nav_home:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container,new HomeFragment());
                        fragmentTransaction.commit();
                        tw.setText("Home");
                        item.setChecked(true);
                        dl.closeDrawers();
                        break;
                    case R.id.nav_courses:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container,new TopicFragment());
                        fragmentTransaction.commit();
                        tw.setText("Courses");
                        item.setChecked(true);
                        dl.closeDrawers();
                        break;
                    case R.id.nav_search:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container,new SearchFragment());
                        fragmentTransaction.commit();
                        tw.setText("Search your Course");
                        item.setChecked(true);
                        dl.closeDrawers();
                        break;
                    case R.id.nav_settings:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container,new AboutFragment());
                        fragmentTransaction.commit();
                        tw.setText("About");
                        item.setChecked(true);
                        dl.closeDrawers();
                        break;
                    case R.id.nav_login:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container,new LoginFragment());
                        fragmentTransaction.commit();
                        if(a)
                            tw.setText("Logout");
                        else
                            tw.setText("Login/Register");
                        item.setChecked(true);
                        dl.closeDrawers();
                        break;

                }

                return true;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionToggle.onOptionsItemSelected(item))
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionToggle.syncState();
    }
}
