package com.example.denny_sem.defect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startSaveActivity(View view) {
        Intent intent = new Intent(getBaseContext(), SaveActivity.class);
        startActivity(intent);
    }

    public void startShowActivity(View view) {
        Intent intent = new Intent(getBaseContext(), ShowActivity.class);
        startActivity(intent);
    }
}
