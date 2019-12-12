package com.example.assignment_8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ErrorActivity extends AppCompatActivity {
    Button Backbtn;
    TextView errorTV = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);
        Backbtn = (Button) findViewById(R.id.Backbtn);
        errorTV = (TextView) findViewById(R.id.error);
        Backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        String error = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null)
            error = extras.getString("error");
        errorTV.append(" " + error);
    }
}