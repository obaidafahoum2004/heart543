package com.example.heartreader;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView Useremail = (TextView) findViewById(R.id.Useremail);
        TextView Password = (TextView) findViewById(R.id.password);

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);
        // admin & admin
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Useremail.getText().toString().equals("admin") && Password.getText().toString().equals("admin")) {
                    // correct
                    Toast.makeText(MainActivity.this, "LogIn Successful", Toast.LENGTH_SHORT).show();
                } else {
                    // incorrect
                    Toast.makeText(MainActivity.this, "LogIn Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }}


