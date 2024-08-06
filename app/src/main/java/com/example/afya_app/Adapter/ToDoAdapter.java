package com.example.afya_app.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.afya_app.AddNewReminder;
import com.example.afya_app.Model.ToDoModel;
import com.example.afya_app.R;
import com.example.afya_app.ReminderActivity;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.MyViewHolder>{

    private List<ToDoModel> todoList;
    private ReminderActivity activity;
    private FirebaseFirestore firestore;

    public ToDoAdapter(ReminderActivity reminderActivity, List<ToDoModel> todoList){
        this.todoList=todoList;
        activity=reminderActivity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(activity).inflate(R.layout.each_reminder,parent,false);
        firestore=FirebaseFirestore.getInstance();
        return new MyViewHolder(view);
    }

    public void deleteReminder(int position){
        ToDoModel toDoModel=todoList.get(position);
        firestore.collection("reminder").document(toDoModel.TaskId).delete();
        todoList.remove(position);
        notifyItemRemoved(position);
    }

    public void editReminder(int position){
        ToDoModel toDoModel=todoList.get(position);
        Bundle bundle=new Bundle();
        bundle.putString("title",toDoModel.getTitle());
        bundle.putString("due",toDoModel.getDue());
        bundle.putString("id",toDoModel.TaskId);

        AddNewReminder addNewReminder=new AddNewReminder();
        addNewReminder.setArguments(bundle);
        addNewReminder.show(activity.getSupportFragmentManager(), addNewReminder.getTag());
    }

    public Context getContext(){
        return activity;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ToDoModel toDoModel=todoList.get(position);
        holder.checkBox.setText(toDoModel.getTitle());

        holder.due_date_tv.setText("Due On: "+toDoModel.getDue());


    }


    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView due_date_tv;
        CheckBox checkBox;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            due_date_tv=itemView.findViewById(R.id.due_date_tv);
            checkBox=itemView.findViewById(R.id.checkbox);
        }
    }
}
