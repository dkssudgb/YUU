package com.example.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import android.annotation.SuppressLint;
import com.example.myapplication.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
public class MainActivity extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private BackKeyClickHandler backKeyClickHandler;
    FirebaseAuth mAuth;

    ViewGroup btn1;
    ViewGroup btn2;
    ViewGroup btn3;
    @SuppressLint("MissingInflatedId")

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        backKeyClickHandler = new BackKeyClickHandler(this);
        mAuth = FirebaseAuth.getInstance();

        btn3 = (ViewGroup) findViewById(R.id.frag4_button2);

        btn3.setOnClickListener(new View.OnClickListener() //교재 추천
        {
            @Override
            public void onClick(View v)
            {

                Intent project = new Intent(MainActivity.this,MapsActivity.class);
                startActivity(project);
            }
        });

        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null){

            Toast.makeText(this, user.getEmail()+" 님 환영합니다.", Toast.LENGTH_SHORT).show();

        }else{

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
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
            backKeyClickHandler.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.loginOutBtn) {
            mAuth.signOut();
            finish();
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
