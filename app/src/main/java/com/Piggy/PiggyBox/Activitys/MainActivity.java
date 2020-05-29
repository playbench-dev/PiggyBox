package com.Piggy.PiggyBox.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.Piggy.PiggyBox.R;

public class MainActivity extends AppCompatActivity {
    Button buttonStart;
    SharedPreferences pref1;
    TextView textViewTv;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref1 = getSharedPreferences("pref1", Activity.MODE_PRIVATE);
        name = pref1.getString("pref_nick", "");
        buttonStart = (Button) findViewById(R.id.start);
        textViewTv = (TextView) findViewById(R.id.link);
        textViewTv.setText(Html.fromHtml("<u>" + "Terms \u0026 Conditions" + "</u>")); // 밑줄

        textViewTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, YkActivity.class);
                startActivity(intent);

            }
        });
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.length() <= 0) {
                    Intent intent = new Intent(MainActivity.this, NicknameActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
