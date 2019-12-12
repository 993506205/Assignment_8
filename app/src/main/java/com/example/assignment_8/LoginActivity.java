package com.example.assignment_8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class LoginActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    Button Loginbtn;
    DBAdapter dbAdapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbAdapter = new DBAdapter(getApplicationContext());

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        Loginbtn = (Button) findViewById(R.id.Loginbtn);
        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(username, password);
            }
        });
    }
    private void validate(EditText username, EditText password) {
        if(username.getText().length()==0){
            username.setBackgroundColor(Color.rgb(100, 150, 150));
            Toast.makeText(getApplication(), getResources().getString(R.string.no_id_txt), Toast.LENGTH_LONG).show();
        } else {
            if (username.getText().toString().equals("admin") && (password.getText().toString().equals("admin"))) {
                login(username.getText().toString());
            } else {
                //Here we add a new customer data
                dbAdapter.open();
                Cursor cursor = dbAdapter.getPersonByFirstName(username.getText().toString());
                if (cursor.moveToFirst()) {
                    //Here we close database connection and start user page
                    dbAdapter.close();
                    login(username.getText().toString());
                } else {
                    //Here we close database connection and start error page
                    dbAdapter.close();
                    Intent intent = new Intent(getApplication(), ErrorActivity.class);
                    intent.putExtra("error", "User Not Found !");
                    startActivity(intent);
                }
            }
        }
    }
    private void login(String username) {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra("data", username);
        intent.putExtra("time", "" + new Date());
        startActivity(intent);
    }
}

