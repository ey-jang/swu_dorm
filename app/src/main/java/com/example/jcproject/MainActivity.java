package com.example.jcproject;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton btnNote, btnMop, btnTool, btnsleep, btnDelivery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNote = findViewById(R.id.btnNote);
        btnMop = findViewById(R.id.btnMop);
        btnTool = findViewById(R.id.btnTool);
        btnsleep = (FloatingActionButton)findViewById(R.id.btnsleep);



        btnNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,BoardActivity.class);
                startActivity(i);
            }
        });

        btnMop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,CleanActivity.class);
                startActivity(i);
            }
        });

        btnTool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ToolActivity.class);
                startActivity(i);
            }
        });

        btnsleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,SleepActivity.class);
                startActivity(i);
            }
        });

    }
}
