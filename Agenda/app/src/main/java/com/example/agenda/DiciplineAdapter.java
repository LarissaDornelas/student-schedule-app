package com.example.agenda;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DiciplineAdapter extends BaseAdapter {

    private ArrayList<Dicipline> list;
    private Context context;


    public DiciplineAdapter(ArrayList<Dicipline> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Dicipline dicipline = list.get(position);
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.activity_dicipline_adapter, null);

        TextView tvName= v.findViewById(R.id.tvName);
        TextView tvSemester = v.findViewById(R.id.tvSemester);
        TextView tvProgress = v.findViewById(R.id.tvProgress);

        tvName.setText(dicipline.getName());
        tvSemester.setText(""+dicipline.getSemester());

        if(dicipline.isProgress()) {
            tvProgress.setText("Andamento");
        }else{
            tvProgress.setText("Concluida");
        }
        return v;
    }
}
