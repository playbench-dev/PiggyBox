package com.Piggy.PiggyBox.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.Piggy.PiggyBox.R;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 1000;
    SharedPreferences pref1;
    String nick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        pref1 = getSharedPreferences("pref1",Activity.MODE_PRIVATE);
        nick = pref1.getString("pref_nick","");
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashActivity.this,MainActivity.class);
                Intent homeIntent = new Intent(SplashActivity.this,HomeActivity.class);
                if(nick.length()<=0){
                    startActivity(mainIntent);
                    finish();
                }
                if(nick.length()>0){
                    startActivity(homeIntent);
                    finish();
                }


            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
