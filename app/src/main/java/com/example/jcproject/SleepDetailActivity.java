package com.example.jcproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.jcproject.bean.SleepBean;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class SleepDetailActivity extends AppCompatActivity {

    private TextView txtId1, txtStart1, txtToday1, txtName1, txtNum1, txtPlace1;
    private FirebaseDatabase mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseStorage mStorage;
    private Intent intent;
    public static SleepBean sleepBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_detail);

        mDatabase = FirebaseDatabase.getInstance();
        intent = getIntent();
        sleepBean = (SleepBean) intent.getSerializableExtra(SleepBean.class.getName());


        txtId1 = findViewById(R.id.txtId1);
        txtName1 = findViewById(R.id.txtName1);
        txtNum1 = findViewById(R.id.txtNum1);
        txtStart1 = findViewById(R.id.txtStart1);
        txtPlace1 = findViewById(R.id.txtPlace1);
        txtToday1 = findViewById(R.id.txtToday1);




        txtId1.setText(sleepBean.hak);
        txtName1.setText(sleepBean.name);
        txtNum1.setText(sleepBean.num);
        txtStart1.setText(sleepBean.start);
        txtToday1.setText(sleepBean.today);
        txtPlace1.setText(sleepBean.place);







    }
}