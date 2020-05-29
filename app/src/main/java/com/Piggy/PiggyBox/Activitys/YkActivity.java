package com.Piggy.PiggyBox.Activitys;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.Piggy.PiggyBox.R;

public class YkActivity extends AppCompatActivity {
    TextView txtViewYk;
    ImageView back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yk);

        txtViewYk = (TextView)findViewById(R.id.yk);
        back_btn = (ImageView)findViewById(R.id.back_1);

        txtViewYk.setText(R.string.yk);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}
