package com.example.jcproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jcproject.Adapter.ChatAdapter;
import com.example.jcproject.bean.ChatBean;
import com.example.jcproject.bean.CleanBean;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CleanDetailActivity extends AppCompatActivity {

    TextView txtMember, txtContents, txtTitle;
    ListView lstChat;
    Button uploadChat;
    EditText edtChat;
    public static CleanBean cleanBean;

    private FirebaseDatabase mDatabase;

    private List<ChatBean> mChatList = new ArrayList<>();
    private ChatAdapter mChatAdapter;
    private ChatBean chatBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clean_detail);

        mDatabase = FirebaseDatabase.getInstance();
        Intent intent = getIntent();
        cleanBean = (CleanBean) getIntent().getSerializableExtra(CleanBean.class.getName());

        txtContents=findViewById(R.id.txtContents);
        txtTitle = findViewById(R.id.txtTitle);


        uploadChat = findViewById(R.id.btnUploadChat);

        lstChat = findViewById(R.id.lstChat);
        edtChat = findViewById(R.id.edtChat);


        txtContents.setText(cleanBean.contents);
        txtTitle.setText(cleanBean.title);

        uploadChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload();
            }
        });






        DatabaseReference chat = mDatabase.getReference().child("chat");

        if(chat!=null){
            chat.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //실시간으로 서버가 변경된 내용이 있을 경우 호출된다.

                    //기존 리스트는 날려버린다.
                    if(mChatList!=null) {
                        mChatList.clear();
                    }

                    //리스트를 서버로부터 온 데이터로 새로 만든다.
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String str = snapshot.getKey();
                        if(str.toString().equals(cleanBean.id)) {
                            for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                ChatBean bean = snapshot1.getValue(ChatBean.class);
                                mChatList.add(bean);
                            }
                        }
                    }

                    //어댑터 적용
                    mChatAdapter = new ChatAdapter(CleanDetailActivity.this, mChatList);
                    lstChat.setAdapter(mChatAdapter);

                    setListViewHeightBasedOnChildren(lstChat);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


            });


        }
    }   //end onCreate()

    void upload(){
        //이미지 업로드가 끝나면 호출되는 CallBack 메서드
        //해야될 일 : Uploaded된 이미지 URL과 사용자가 작성한
        //메모의 내용을 RealTime DB에 업로드 시킨다.
        DatabaseReference firebaseRef = mDatabase.getReference();
        String id = firebaseRef.push().getKey();

        //DATABASE 에 저장한다.
        ChatBean mChatBean = new ChatBean();

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Calendar time = Calendar.getInstance();
        String format_time1 = format.format(time.getTime());
        mChatBean.textId = cleanBean.id;
        mChatBean.reId= id;

        mChatBean.time = format_time1;
        mChatBean.contents =  edtChat.getText().toString();



        firebaseRef.child("chat").child(mChatBean.textId).child(mChatBean.reId).setValue(mChatBean);
        Toast.makeText(CleanDetailActivity.this, "서버 글쓰기 성공", Toast.LENGTH_SHORT).show();

        edtChat.setText("");



    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    /*public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }*/

}
