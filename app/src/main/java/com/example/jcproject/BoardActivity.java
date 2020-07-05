package com.example.jcproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.jcproject.Adapter.BoardAdapter;
import com.example.jcproject.bean.BoardBean;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BoardActivity extends AppCompatActivity {

    private ListView lstContents;

    private FirebaseDatabase mDatabase;
    private Button btn_write;

    private List<BoardBean> mBoardList = new ArrayList<>();
    private BoardAdapter mBoardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        mDatabase = FirebaseDatabase.getInstance();

        lstContents = findViewById(R.id.lstContents);

        check();

        findViewById(R.id.btn_write).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BoardActivity.this, BWriteActivity.class);
                startActivity(i);
            }
        });
    }

    void check(){
        mDatabase.getReference().child("notice").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //실시간으로 서버가 변경된 내용이 있을 경우 호출된다.

                //기존 리스트는 날려버린다.
                mBoardList.clear();

                //리스트를 서버로부터 온 데이터로 새로 만든다.
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    BoardBean bean = snapshot.getValue(BoardBean.class);
                    mBoardList.add(bean);
                }

                mBoardAdapter = new BoardAdapter(BoardActivity.this, mBoardList);
                lstContents.setAdapter(mBoardAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    protected void onResume() {
        super.onResume();

        //내용 다 받아오기
        DatabaseReference clean = mDatabase.getReference().child("notice");

        if(clean!=null){
            clean.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //실시간으로 서버가 변경된 내용이 있을 경우 호출된다.

                    //기존 리스트는 날려버린다.
                    if(mBoardList!=null) {
                        mBoardList.clear();
                    }

                    //리스트를 서버로부터 온 데이터로 새로 만든다.
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            BoardBean bean = snapshot.getValue(BoardBean.class);
                            mBoardList.add(bean);
                    }

                    //어댑터 적용
                    mBoardAdapter = new BoardAdapter(BoardActivity.this, mBoardList);
                    lstContents.setAdapter(mBoardAdapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

}
