package com.example.vrajpatel.way2cis;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;

public class HomeActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar=findViewById(R.id.toolbar);
        drawer=findViewById(R.id.drawer_layout);
        navigationView=(NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(HomeActivity.this, drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        if(savedInstanceState==null)
        {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Fragment_home()).commit();
        navigationView.setCheckedItem(R.id.home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId())
        {
            case R.id.purchase_his:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Fragment_purchase_his()).addToBackStack(null).commit();
                break;
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Fragment_home()).addToBackStack(null).commit();
                break;
            case  R.id.sales_his:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Fragment_sales_his()).addToBackStack(null).commit();
                break;
            case R.id.total_out:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Fragment_total_outstanding()).addToBackStack(null).commit();
                break;
            case R.id.inv_stat:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment_inventory_stat()).addToBackStack(null).commit();
                break;
            case R.id.acc_pay:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment_account_pay()).addToBackStack(null).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }
    public void setActionBarTitle(String title)
    {
        getSupportActionBar().setTitle(title);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }
}
