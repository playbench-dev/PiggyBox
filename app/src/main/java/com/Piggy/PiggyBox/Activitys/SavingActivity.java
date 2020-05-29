package com.Piggy.PiggyBox.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
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
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SavingActivity extends AppCompatActivity {
    Button buttonSaving;
    EditText editTextMoney;
    private AdView mAdView;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    DecimalFormat mf = new DecimalFormat("###,###");
    ImageView imgCancel;
    int saved;
    String date, amount;
    ArrayList<String> arrayList, arrayList1, arrayList2;
    String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving);

        MobileAds.initialize(this, getString(R.string.admob_app_id));
        mAdView = findViewById(R.id.adView2);
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


        arrayList = new ArrayList<>();
        arrayList1 = new ArrayList<>();
        arrayList2 = new ArrayList<>();
        if (LoadData("pref_dayL") != null) {
            arrayList = LoadData("pref_dayL");
            arrayList1 = LoadData("pref_moneyL");
            arrayList2 = LoadData("pref_savingL");
        }

        imgCancel = (ImageView) findViewById(R.id.cancel);
        buttonSaving = (Button) findViewById(R.id.saving_btn);
        editTextMoney = (EditText) findViewById(R.id.edittext_money);
        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        editor = pref.edit();
        final int money_2 = pref.getInt("compare", 0);
        saved = pref.getInt("pref_money", 0);
        amount = getString(R.string.amont);
        editTextMoney.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s.toString()) && !s.toString().equals(result)) {     // StackOverflow를 막기위해,
                    result = mf.format(Double.parseDouble(s.toString().replaceAll(",", "")));   // 에딧텍스트의 값을 변환하여, result에 저장.
                    editTextMoney.setText(result);    // 결과 텍스트 셋팅.
                    editTextMoney.setSelection(result.length());     // 커서를 제일 끝으로 보냄.
                }
            }
        });

        editTextMoney.setImeOptions(EditorInfo.IME_ACTION_DONE);

        buttonSaving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SavingActivity.this, HomeActivity.class);
                String s = Replace(editTextMoney.getText().toString());

                if (editTextMoney.getText().toString().length() <= 0) {
                    Toast toast = Toast.makeText(SavingActivity.this, amount, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, -250);
                    toast.show();

                } else {
                    int total = saved + Integer.parseInt(s);

                    editor.putInt("pref_money", saved + Integer.parseInt(s));
                    editor.putInt("pref_saved", Integer.parseInt(s));

                    arrayList.add(date);
                    arrayList1.add("+" + mf.format(Integer.parseInt(s)));
                    arrayList2.add("" + mf.format(total));

                    SaveData("pref_dayL", arrayList);
                    SaveData("pref_moneyL", arrayList1);
                    SaveData("pref_savingL", arrayList2);
                    editor.putInt("compare", money_2);
                    editor.apply();
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            }
        });

        editTextMoney.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    Intent intent = new Intent(SavingActivity.this, HomeActivity.class);
                    String s = Replace(editTextMoney.getText().toString());
                    if (editTextMoney.getText().toString().length() <= 0) {
                        Toast toast = Toast.makeText(SavingActivity.this, amount, Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, -250);
                        toast.show();
                    } else {
                        int total = saved + Integer.parseInt(s);

                        editor.putInt("pref_money", saved + Integer.parseInt(s));
                        editor.putInt("pref_saved", Integer.parseInt(s));

                        arrayList.add(date);
                        arrayList1.add("+" + mf.format(Integer.parseInt(s)));
                        arrayList2.add("" + mf.format(total));

                        SaveData("pref_dayL", arrayList);
                        SaveData("pref_moneyL", arrayList1);
                        SaveData("pref_savingL", arrayList2);
                        editor.putInt("compare", money_2);
                        editor.apply();
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                }
                return true;
            }
        });

        SimpleDateFormat format1 = new SimpleDateFormat("MM.dd.yyyy");
        long time = System.currentTimeMillis();
        Intent getIntent = getIntent();
        getIntent.getStringExtra("date");
        Log.i("eeee",""+ getIntent.getStringExtra("date"));
        if(getIntent.getStringExtra("date") == null) {
            date = pref.getString("pref_day", format1.format(time));
        }else {
            date = pref.getString("pref_day",getIntent.getStringExtra("date"));
        }
        imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void SaveData(String key, ArrayList<String> value) {
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(value);
        editor.putString(key, json);
        editor.apply();
    }

    private ArrayList<String> LoadData(String key) {
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = pref.getString(key, "");
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        ArrayList<String> arrayList = gson.fromJson(json, type);
        return arrayList;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public String Replace(String data) {
        return data.replaceAll("\\,", "");
    }
}
