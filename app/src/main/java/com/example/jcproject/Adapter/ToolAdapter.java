package com.example.jcproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.jcproject.R;
import com.example.jcproject.ToolActivity;
import com.example.jcproject.ToolDetailActivity;
import com.example.jcproject.bean.ToolBean;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;
import java.util.List;
//import static com.example.jcproject.JoinActivity.getUserIdFromUUID;



public class ToolAdapter extends BaseAdapter {

    private Context mContext;
    private List<ToolBean> mList;
    private Spinner spinner;
    private String category;
    private String categoryNum;
    private ToolBean toolBean;
    private FirebaseDatabase mDatabase;
    private FirebaseAuth mAuth;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;




    public ToolAdapter(Context context, List<ToolBean> list){
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.view_tool, null);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();

        View layTopRow = convertView.findViewById(R.id.layTopRow);
        TextView txtTitle = convertView.findViewById(R.id.txtTitle);
        TextView txtState = convertView.findViewById(R.id.txtState);


        if(position == 0) {
            layTopRow.setVisibility(View.VISIBLE);
        } else {
            layTopRow.setVisibility(View.GONE);
        }

        final ToolBean bean = mList.get(position);

        txtTitle.setText(bean.title);
        txtState.setText(bean.category);

        DatabaseReference firebaseRef = mDatabase.getReference();


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, ToolDetailActivity.class);
                i.putExtra(ToolBean.class.getName(), bean);

                mContext.startActivity(i);
            }
        });

        return convertView;
    }


}