package com.chiachen.portfolio.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.chiachen.portfolio.R;

public class DrawerExampleActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigation_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_example);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigation_view = (NavigationView) findViewById(R.id.navigation_view);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigation_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawer(GravityCompat.START);
                switch (item.getItemId()){
                    case R.id.nav_camera:{
                        Toast.makeText(DrawerExampleActivity.this, "Camera", Toast.LENGTH_SHORT).show();
                        return true;
                    }

                    case R.id.nav_gallery:{
                        Toast.makeText(DrawerExampleActivity.this, "Gallery", Toast.LENGTH_SHORT).show();
                        return true;
                    }

                    case R.id.nav_slideshow:{
                        Toast.makeText(DrawerExampleActivity.this, "Slideshow", Toast.LENGTH_SHORT).show();
                        return true;
                    }

                    case R.id.nav_tools:{
                        Toast.makeText(DrawerExampleActivity.this, "Tools", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    case R.id.nav_share:{
                        Toast.makeText(DrawerExampleActivity.this, "Share", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    case R.id.nav_send:{
                        Toast.makeText(DrawerExampleActivity.this, "Send", Toast.LENGTH_SHORT).show();
                        return true;
                    }

                }

                return false;
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (navigation_view.isShown()){
            drawerLayout.closeDrawer(GravityCompat.START);
            return;
        }
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case  R.id.action_help:{
                Toast.makeText(this, "使用說明", Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.action_about:{
                Toast.makeText(this, "關於", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
