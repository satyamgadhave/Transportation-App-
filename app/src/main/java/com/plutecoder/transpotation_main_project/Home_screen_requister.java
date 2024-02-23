package com.plutecoder.transpotation_main_project;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;

public class Home_screen_requister extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen_requister);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        fragmentManager = getSupportFragmentManager();



        // Set the initial fragment
        replaceFragment(new FirstFragment_Provider());

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        replaceFragment( new FirstFragment_Requister());
                        return true;

                    case R.id.order:
                        replaceFragment( new SecondFragment_Requister());
                        return true;

                    case R.id.add:
                        replaceFragment( new ThirdFragment_Requister());
                        return true;

                 //   case R.id.search:
                       // replaceFragment(new ThirdFragment_Requister());
                      //  return true;

                    case R.id.profile:
                        replaceFragment(new ForthFragment_Requister());
                        return true;
                }
                return false;
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}


