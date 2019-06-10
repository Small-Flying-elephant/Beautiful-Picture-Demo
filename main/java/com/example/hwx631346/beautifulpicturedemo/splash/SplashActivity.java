package com.example.hwx631346.beautifulpicturedemo.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.hwx631346.beautifulpicturedemo.GuideActivity;
import com.example.hwx631346.beautifulpicturedemo.MainActivity;
import com.example.hwx631346.beautifulpicturedemo.R;
import com.example.hwx631346.beautifulpicturedemo.sp.BeautifulPicturePreferenceManager;


public class SplashActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "SplashActivity";
    private TextView bt;

    private CountDownTimer countDownTimer = new CountDownTimer(3200, 1000) {
        @Override
        public void onTick(long l) {
            bt.setText("跳过广告" + l / 1000 + "s");
        }

        @Override
        public void onFinish() {
            bt.setText("跳过广告" + 0 + "s");
            toMainActivity();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        bt = findViewById(R.id.bt);
        startClock();
        bt.setOnClickListener(this);
    }

    private void startClock() {
        bt.setVisibility(View.VISIBLE);
        countDownTimer.start();
    }

    private void toMainActivity() {
        boolean isFirest = BeautifulPicturePreferenceManager.getisFirestEntry(getApplicationContext());
        Log.d("linsheng", "toMainActivity: " + isFirest);
        if (isFirest) {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else{
            Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
            startActivity(intent);
            finish();
            BeautifulPicturePreferenceManager.setisFirestEntry(getApplicationContext(),true);
        }
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.bt:
                toMainActivity();
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
