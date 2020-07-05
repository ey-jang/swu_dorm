package com.example.jcproject.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jcproject.R;
import com.example.jcproject.bean.ChatBean;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.net.URL;
import java.util.List;

public class ChatAdapter extends BaseAdapter {

    private Context mContext;
    private List<ChatBean> mList;


    public ChatAdapter(Context context, List<ChatBean> list) {
        mContext = context;
        mList = list;

    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.view_chat, null);

        final ChatBean bean = mList.get(position);


        TextView txtNum = convertView.findViewById(R.id.txtNum);
        TextView txtDate = convertView.findViewById(R.id.txtDate);
        TextView txtContents = convertView.findViewById(R.id.txtContents);



        txtNum.setText(bean.num);
        txtDate.setText(bean.time);
        txtContents.setText(bean.contents);


        return convertView;
    }
}