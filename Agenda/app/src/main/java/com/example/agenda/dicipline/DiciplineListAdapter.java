package com.example.agenda.dicipline;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agenda.R;
import com.example.agenda.task.Task;
import com.example.agenda.task.TaskDAO;

import java.util.ArrayList;

public class DiciplineListAdapter extends BaseAdapter {
    private  ArrayList<Dicipline> diciplines;
    private  ArrayList<Task> tasks;
    private Context context;
    private static LayoutInflater inflater = null;


public DiciplineListAdapter(ListDicipline listDicipline, ArrayList<Dicipline> diciplines, ArrayList<Task> tasks){
    this.diciplines = diciplines;
    this.tasks = tasks;

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
        TextView tvTotalNote = v.findViewById(R.id.tvTotalNote);
        TextView tvFaults= v.findViewById(R.id.tvFaults);
        CardView cardView = v.findViewById(R.id.card_view);

        double sum = 0;
        for(int i=0; i<tasks.size(); i++){

            if(this.diciplines.get(position).getName().equals(tasks.get(i).getDicipline())){
                sum+=tasks.get(i).getNote();

            }
        }

        tvName.setText(this.diciplines.get(position).getName());
        tvTotalNote.setText("Nota atual: "+ sum);
        tvFaults.setText("Número de faltas: "+ this.diciplines.get(position).getFaults());

        if(sum>=this.diciplines.get(position).getGoal()){
            cardView.setCardBackgroundColor(Color.GREEN);
        }
        else{
            cardView.setCardBackgroundColor(Color.RED);

        }
       /* if(this.diciplines.get(position).isProgress()){
            tvStatus.setText("Status: Em Andamento");

        }else{
            tvStatus.setText("Status: Concluida");
        }*/
//        v.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Toast.makeText(context, "You Clicked " + diciplines.get(position), Toast.LENGTH_LONG).show();
//            }
//        });

        return v;
    }

}


