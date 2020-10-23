package com.example.libyaproject.Activitys;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.libyaproject.Models.UserModel;
import com.example.libyaproject.PagerAdapter;
import com.example.libyaproject.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tablayout;
    ViewPager viewpager;
    PagerAdapter pageradapter;
    UserModel user;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Intent changepass = new Intent(this, ChangePasswordActivity.class);
                changepass.putExtra("user",user);
                startActivity(changepass);
                return true;

            case R.id.item2:
                Intent changephone = new Intent(this, ChangePhoneNumberActivity.class);
                changephone.putExtra("user",user);
                startActivity(changephone);
                return true;

            case R.id.item3:
                startActivity(new Intent(this,LoginActivity.class));
                this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tablayout = findViewById(R.id.tablayout);
        viewpager = findViewById(R.id.viewpager);
        pageradapter = new PagerAdapter(getSupportFragmentManager(), tablayout.getTabCount());
        viewpager.setAdapter(pageradapter);


        user = (UserModel) getIntent().getSerializableExtra("user");

        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                tablayout.setScrollPosition(i,v,true);
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

}
