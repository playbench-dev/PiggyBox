package com.Piggy.PiggyBox.Activitys;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.Piggy.PiggyBox.R;
import com.Piggy.PiggyBox.Utils.BackPressCloseHandler;
import com.bumptech.glide.load.engine.Resource;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity  implements View.OnClickListener{
    TextView txtNick, txtSaved_money, txtSaved_money_0,txtHi;
    ImageView imgCal, imgImg;
    private AdView mAdView;
    SharedPreferences pref,pref1;
    SharedPreferences.Editor editor;
    DecimalFormat mf = new DecimalFormat("###,###");
    private BackPressCloseHandler backPressCloseHandler;
//    GlideDrawableImageViewTarget gifImage;
    int money_2;
    Float translationY = 100f;
    private Boolean isFabOpen = false;
    private FloatingActionButton fab, fab1, fab2;
    OvershootInterpolator interpolator = new OvershootInterpolator();
    Typeface typeface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        pref1 = getSharedPreferences("pref1", Activity.MODE_PRIVATE);
        MobileAds.initialize(this, getString(R.string.admob_app_id));
        mAdView = findViewById(R.id.adView1);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {

            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                // 광고가 문제 없이 로드시 출력됩니다.
                Log.d("@@@", "onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                // 광고 로드에 문제가 있을시 출력됩니다.
                Log.d("@@@", "onAdFailedToLoad " + errorCode);
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }
            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });

        imgCal = (ImageView)findViewById(R.id.calender);
        imgImg = (ImageView)findViewById(R.id.img);
        txtNick = (TextView)findViewById(R.id.nickname_view);
        txtSaved_money = (TextView)findViewById(R.id.money_saved);
        txtSaved_money_0 = (TextView)findViewById(R.id.money_saved1);
        txtHi = (TextView)findViewById(R.id.hi);
        txtSaved_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        long time = System.currentTimeMillis();



//



//        gifImage = new GlideDrawableImageViewTarget(imgImg);
        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        pref1 = getSharedPreferences("pref1", Activity.MODE_PRIVATE);
        editor = pref.edit();
        editor.apply();
        String nick_name = pref1.getString("pref_nick","");
        txtNick.setText(nick_name);
        backPressCloseHandler = new BackPressCloseHandler(this);
        int money = pref.getInt("pref_money",0);
        money_2 = pref.getInt("compare",0);
        initFabMenu();
       imgImg.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Glide.with(getApplicationContext()).load(R.drawable.move_1).into(imgImg);
               imgImg.setEnabled(false);
               new Handler().postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       imgImg.setImageResource(R.drawable.pig_1);
                       imgImg.setEnabled(true);
                   }
               },5000);
           }
       });
        if(money != money_2){
            Glide.with(this).load(R.drawable.test1).into(imgImg);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    imgImg.setImageResource(R.drawable.pig_1);
                }
            },5000);
        }

        money_2 =money;

        editor.putInt("compare",money_2);
        editor.apply();

        String money_3 = mf.format(money);
        txtSaved_money.setText(""+money_3);

        txtNick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,NicknameActivity.class);
                startActivity(intent);
            }
        });

        txtSaved_money_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,SavingActivity.class);
                startActivity(intent);
            }
        });
        imgCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,SavingListActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initFabMenu() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);

        fab1.setAlpha(0f);
        fab2.setAlpha(0f);

        fab1.setTranslationY(translationY);
        fab2.setTranslationY(translationY);
        fab1.setEnabled(false);
        fab2.setEnabled(false);
        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
    }

    private void openMenu() {
        isFabOpen = !isFabOpen;
        fab.animate().setInterpolator(interpolator).rotation(45f).setDuration(300).start();
        fab1.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fab2.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fab.setImageResource(R.drawable.ic_add_black_24dp);
        fab.setBackgroundTintList(getColorStateList(R.color.colorWhite));
        fab1.setEnabled(true);
        fab2.setEnabled(true);
    }

    private void closeMenu() {
        isFabOpen = !isFabOpen;
        fab.animate().setInterpolator(interpolator).rotation(0f).setDuration(300).start();
        fab1.animate().translationY(translationY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fab2.animate().translationY(translationY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fab.setImageResource(R.drawable.ic_dehaze_black_24dp);
        fab.setBackgroundTintList(getColorStateList(R.color.colorYellow));
        fab1.setEnabled(false);
        fab2.setEnabled(false);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                if (isFabOpen) {
                    closeMenu();
                } else {
                    openMenu();
                }
                break;
            case R.id.fab1:
                Intent intentSaving = new Intent(HomeActivity.this,SavingActivity.class);
                startActivity(intentSaving);
                break;
            case R.id.fab2:
                Intent intentWithdraw = new Intent(HomeActivity.this,WithdrawActivity.class);

                startActivity(intentWithdraw);

                break;
        }
    }

    @Override
    public void onBackPressed(){
        backPressCloseHandler.onBackPressed();
    }
}

