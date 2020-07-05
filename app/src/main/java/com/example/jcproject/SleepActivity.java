package com.example.jcproject;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.jcproject.Adapter.SleepAdapter;
import com.example.jcproject.bean.SleepBean;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SleepActivity extends AppCompatActivity {

    private ListView lstContents;
    private FirebaseDatabase mDatabase;

    private List<SleepBean> mSleepList = new ArrayList<>();
    private SleepAdapter mSleepAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);

        mDatabase = FirebaseDatabase.getInstance();

        lstContents = findViewById(R.id.lstContents);
        check();

    }   //oncreate

    void check() {
        mDatabase.getReference().child("sleep").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mSleepList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    SleepBean bean = snapshot.getValue(SleepBean.class);
                    mSleepList.add(bean);
                }

                mSleepAdapter = new SleepAdapter(SleepActivity.this, mSleepList);
                lstContents.setAdapter(mSleepAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

   /* protected void onResume() {
        super.onResume();

        final DatabaseReference sleep = mDatabase.getReference().child("sleep");

        if (sleep != null) {
            sleep.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (mSleepList != null) {
                        mSleepList.clear();
                    }

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                            SleepBean bean = snapshot1.getValue(SleepBean.class);
                            mSleepList.add(bean);
                        }
                    }

                    mSleepAdapter = new SleepAdapter(SleepActivity.this, mSleepList);
                    lstContents.setAdapter(mSleepAdapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    } */
}
