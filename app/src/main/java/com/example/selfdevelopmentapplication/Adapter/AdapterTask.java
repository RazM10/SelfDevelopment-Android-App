package com.example.selfdevelopmentapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.selfdevelopmentapplication.Activity.AddTaskActivity;
import com.example.selfdevelopmentapplication.Activity.MainActivity;
import com.example.selfdevelopmentapplication.GlobalItems.StringArrays;
import com.example.selfdevelopmentapplication.Model.Task;
import com.example.selfdevelopmentapplication.R;

import java.util.ArrayList;

public class AdapterTask extends RecyclerView.Adapter<AdapterTask.ViewHolderClass> {
    ArrayList<Task> taskArrayList;
    Context context;

    public AdapterTask() {
    }

    public AdapterTask(ArrayList<Task> taskArrayList, Context context) {
        this.taskArrayList = taskArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rv_task, parent, false);
        return new ViewHolderClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderClass holder, final int position) {
        final Task task = taskArrayList.get(position);
        holder.tv_category.setText(StringArrays.category[task.getCategory()]);

        holder.linearLayout_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, AddTaskActivity.class);

                intent.putExtra("id",task.getId());
                intent.putExtra("category",task.getCategory());
                intent.putExtra("date",task.getDate());
                intent.putExtra("situation",task.getSituation());
                intent.putExtra("character",task.getCharacter());
                intent.putExtra("output",task.getOutput());
                intent.putExtra("priority",task.getPriority());
                intent.putExtra("duration",task.getDuration());
                intent.putExtra("complete",task.getComplete());
                intent.putExtra("description",task.getDescription());
                intent.putExtra("solution",task.getSolution());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskArrayList.size();
    }

    class ViewHolderClass extends RecyclerView.ViewHolder {

        TextView tv_category;
        LinearLayout linearLayout_task;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);

            linearLayout_task = itemView.findViewById(R.id.linearLayout_task);
            tv_category = itemView.findViewById(R.id.tv_category);
        }
    }

    public void replaceArrayList(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
        notifyDataSetChanged();
    }
}

