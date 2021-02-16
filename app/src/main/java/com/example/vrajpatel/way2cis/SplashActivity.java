package com.example.vrajpatel.way2cis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread time = new Thread() {
            public void run()
            {
                try {
                    sleep(3500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                    Animatoo.animateSlideLeft(SplashActivity.this);
                    finish();
                }
            }
        };
        time.start();
    }
}
