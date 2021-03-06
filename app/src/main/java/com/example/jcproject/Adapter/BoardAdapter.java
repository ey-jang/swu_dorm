package com.example.jcproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jcproject.BoardDetailActivity;
import com.example.jcproject.CleanDetailActivity;
import com.example.jcproject.R;
import com.example.jcproject.bean.BoardBean;
import com.example.jcproject.bean.CleanBean;

import java.util.List;

public class BoardAdapter extends BaseAdapter {

    private Context mContext;
    private List<BoardBean> mList;

    public BoardAdapter(Context context, List<BoardBean> list){
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
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.view_notice, null);

        View layTopRow = convertView.findViewById(R.id.layTopRow);
        TextView txtTitle = convertView.findViewById(R.id.txtTitle);

        if (position == 0 ) {
            layTopRow.setVisibility(View.VISIBLE);
        } else {
            layTopRow.setVisibility(View.GONE);
        }

        final BoardBean bean = mList.get(position);

        txtTitle.setText(bean.title);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, BoardDetailActivity.class);
                i.putExtra(BoardBean.class.getName(), bean);

                mContext.startActivity(i);
            }
        });

        return convertView;
    }
}
