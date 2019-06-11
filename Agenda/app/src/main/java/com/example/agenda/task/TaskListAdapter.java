package com.example.agenda.task;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agenda.R;
import com.example.agenda.task.ListTask;
import com.example.agenda.task.Task;

import java.util.ArrayList;

public class TaskListAdapter extends BaseAdapter {
    private  ArrayList<Task> tasks;
    private Context context;
    private static LayoutInflater inflater = null;

    public TaskListAdapter(ListTask listTask, ArrayList<Task> tasks){
        this.tasks = tasks;
        context = listTask;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return tasks.size();
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
        View v = inflater.inflate(R.layout.layout_task_item, null);
        TextView tvDescription = v.findViewById(R.id.tvDescription);
        TextView tvDicipline =  v.findViewById(R.id.tvDicipline);
        TextView tvDate = v.findViewById(R.id.tvDate);

        tvDescription.setText(this.tasks.get(position).getDescription());
        tvDicipline.setText(this.tasks.get(position).getDicipline());
        tvDate.setText("Data de Entrega: "+this.tasks.get(position).getDate());

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "You Clicked " + tasks.get(position), Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }

}


