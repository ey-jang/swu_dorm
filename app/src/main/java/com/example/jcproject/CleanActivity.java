package com.example.jcproject;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.jcproject.Adapter.CleanAdapter;
import com.example.jcproject.bean.CleanBean;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CleanActivity extends AppCompatActivity {

    private Button btnIt, btnEconomy, btnScience, btnEtc;
    private ListView lstContents;
    public String category;

    private FirebaseDatabase mDatabase;

    private List<CleanBean> mCleanList = new ArrayList<>();
    private CleanAdapter mCleanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clean);

        mDatabase = FirebaseDatabase.getInstance();

        //btnWrite = findViewById(R.id.btnWrite);

        btnIt = findViewById(R.id.btnIt);
        btnEconomy = findViewById(R.id.btnEconomy);
        btnScience = findViewById(R.id.btnScience);
        btnEtc = findViewById(R.id.btnEtc);

        lstContents = findViewById(R.id.lstContents);

        btnIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = "1";
                check();
            }
        });

        btnEconomy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = "2";
                check();
            }
        });

        btnScience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = "3";
                check();
            }
        });

        btnEtc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = "4";
                check();
            }
        });

    }


    void check(){
        mDatabase.getReference().child("clean").child(category).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //실시간으로 서버가 변경된 내용이 있을 경우 호출된다.

                //기존 리스트는 날려버린다.
                mCleanList.clear();

                //리스트를 서버로부터 온 데이터로 새로 만든다.
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    CleanBean bean = snapshot.getValue(CleanBean.class);
                    mCleanList.add(bean);
                }

                mCleanAdapter = new CleanAdapter(CleanActivity.this, mCleanList);
                lstContents.setAdapter(mCleanAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
