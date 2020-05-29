package com.Piggy.PiggyBox.Activitys;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.Piggy.PiggyBox.R;

import java.util.Map;
import java.util.Set;

public class DialogActivity extends Dialog{

    Button button_cancel,button_reset;
    private ResetButtonListener resetbuttonListener;

    public DialogActivity(Context context, ResetButtonListener resetButtonListener) {
        super(context);
        this.resetbuttonListener = resetButtonListener;
        requestWindowFeature(Window.FEATURE_NO_TITLE);   //다이얼로그의 타이틀바를 없애주는 옵션입니다.
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));  //다이얼로그의 배경을 투명으로 만듭니다.
        setContentView(R.layout.activity_dialog);     //다이얼로그에서 사용할 레이아웃입니다.
    }
    public interface ResetButtonListener{

        void resetButton(boolean data);
    }
    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        button_cancel = (Button) findViewById(R.id.btn);
        button_reset = (Button)findViewById(R.id.btn1);

        resetbuttonListener.resetButton(false);
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(DialogActivity.this,HomeActivity.class);
                resetbuttonListener.resetButton(true);
                dismiss();
            //    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }
        });

    }
}
