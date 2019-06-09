package com.example.agenda.dicipline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agenda.R;

import java.util.ArrayList;

public class DiciplineListAdapter extends BaseAdapter {
    private  ArrayList<Dicipline> diciplines;
    private Context context;
    private static LayoutInflater inflater = null;

public DiciplineListAdapter(ListDicipline listDicipline, ArrayList<Dicipline> diciplines){
    this.diciplines = diciplines;
    context = listDicipline;
    inflater = ( LayoutInflater )context.
            getSystemService(Context.LAYOUT_INFLATER_SERVICE);
}


    @Override
    public int getCount() {
        return diciplines.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = inflater.inflate(R.layout.layout_dicipline_item, null);
        TextView tvName = v.findViewById(R.id.tvName);
        TextView tvSemester =  v.findViewById(R.id.tvSemester);
        TextView tvStatus = v.findViewById(R.id.tvStatus);

        tvName.setText(this.diciplines.get(position).getName());
        tvSemester.setText(Integer.toString(this.diciplines.get(position).getSemester())+"° semestre");
        tvStatus.setText("Status: Concluida");

        if(this.diciplines.get(position).isProgress()){
            tvStatus.setText("Status: Em Andamento");

        }else{
            tvStatus.setText("Status: Concluida");
        }
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "You Clicked " + diciplines.get(position), Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }

}


