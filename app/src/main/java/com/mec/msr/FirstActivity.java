package com.mec.msr;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(FirstActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }, 1000);
    }
}