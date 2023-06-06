package com.example.heartreader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViweHolder> {

    Context context;

    ArrayList<User> list;

    public MyAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViweHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.useritem,parent,false);
        return new MyViweHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViweHolder holder, int position) {

        User user = list.get(position);
        holder.Name.setText(user.getName());
        holder.Nickname.setText(user.getNickname());
        holder.Phone.setText(user.getPhone());
        holder.Email.setText(user.getEmail());
        holder.Password.setText(user.getPassword());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViweHolder extends RecyclerView.ViewHolder{

        TextView Name, Nickname, Phone, Email, Password;
        ImageView userPhoto;

        public MyViweHolder(@NonNull View itemView) {
            super(itemView);

            Name=itemView.findViewById(R.id.tvName);
            Nickname=itemView.findViewById(R.id.tvNickName);
            Phone=itemView.findViewById(R.id.tvPhone);
            Email=itemView.findViewById(R.id.tvEmail);
            Password=itemView.findViewById(R.id.tvPassword);
            userPhoto=itemView.findViewById(R.id.userphoto);


        }
    }
}
