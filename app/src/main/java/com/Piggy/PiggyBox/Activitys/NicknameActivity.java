package com.Piggy.PiggyBox.Activitys;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Piggy.PiggyBox.R;
import com.Piggy.PiggyBox.Utils.BackPressCloseHandler;

public class NicknameActivity extends AppCompatActivity {
    Button buttonNext;
    String name,pleaseName;
    EditText edittextEditText;
    SharedPreferences pref1;
    SharedPreferences.Editor editor1;
    private BackPressCloseHandler backPressCloseHandler;
    ImageView imgBack_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nickname);
        imgBack_btn = (ImageView) findViewById(R.id.back_btn_1);
        backPressCloseHandler = new BackPressCloseHandler(this);
        buttonNext = (Button) findViewById(R.id.next_btn);
        edittextEditText = (EditText) findViewById(R.id.input_nick);
        pref1 = getSharedPreferences("pref1", Activity.MODE_PRIVATE);
        editor1 = pref1.edit();
        edittextEditText.setImeOptions(EditorInfo.IME_ACTION_DONE);
        name = pref1.getString("pref_nick","");
        pleaseName = getString(R.string.pleaseNmae);
        edittextEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Intent intent = new Intent(NicknameActivity.this, HomeActivity.class);
                if (edittextEditText.getText().toString().length() <= 0) {
                    Toast toast = Toast.makeText(NicknameActivity.this,pleaseName, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, -250);
                    toast.show();
                } else {
                    editor1.putString("pref_nick", edittextEditText.getText().toString());
                    editor1.apply();
                    startActivity(intent);
                    finish();
                }

                return true;
            }
        });

        imgBack_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(NicknameActivity.this, MainActivity.class);
                Intent intentHome = new Intent(NicknameActivity.this,HomeActivity.class);

                if(name.isEmpty()) {
                    startActivity(intentMain);
                    finish();
                }else {
                    intentHome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intentHome);
                    finish();
                }
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NicknameActivity.this, HomeActivity.class);

                if (edittextEditText.getText().toString().length() <= 0) {
                    Toast toast = Toast.makeText(NicknameActivity.this,pleaseName, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, -250);
                    toast.show();
                } else {
                    editor1.putString("pref_nick", edittextEditText.getText().toString());
                    editor1.apply();
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

}
