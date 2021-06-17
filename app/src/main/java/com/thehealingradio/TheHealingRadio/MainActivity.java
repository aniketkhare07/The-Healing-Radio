package com.thehealingradio.TheHealingRadio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity{

    private BottomNavigationView bottomNavigationView;
    private NavController navController;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        navController = Navigation.findNavController(this, R.id.frame_layout);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);


//        drawerLayout = findViewById(R.id.drawerLayout);
//        navigationView = findViewById(R.id.navigation_view);
//
//        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.start, R.string.close);
//        drawerLayout.addDrawerListener(toggle);
//
//        toggle.syncState();
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        navigationView.setNavigationItemSelectedListener(this);

    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if(toggle.onOptionsItemSelected(item)){
//            return true;
//        }
//
//        return true;
//    }
//
////    @Override
////    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
////        switch (item.getItemId()){
////            case R.id.host:
////                Toast.makeText(this, "Host", Toast.LENGTH_SHORT).show();
////                break;
////            case R.id.developer:
////                Toast.makeText(this, "Developer", Toast.LENGTH_SHORT).show();
////                break;
////            case R.id.share:
////                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
////                break;
////            case R.id.rateus:
////                Toast.makeText(this, "Rate Us", Toast.LENGTH_SHORT).show();
////                break;
////            case R.id.website:
////                Toast.makeText(this, "website", Toast.LENGTH_SHORT).show();
////                break;
////        }
////
////        return true;
////    }
}