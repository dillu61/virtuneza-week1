package com.vibecheck.virtunexa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Vector;

public class historyadapter extends RecyclerView.Adapter<historyadapter.vh>{
    private Vector<historygroup> data;
    historyadapter(Vector<historygroup> data)
    {
        this.data=data;
    }

    @NonNull
    @Override
    public vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.groupsetter,parent,false);
        vh VH=new vh(v);
        return VH;
    }

    @Override
    public void onBindViewHolder(@NonNull historyadapter.vh holder, int position) {
        holder.emojiname.setText(data.get(position).emoji);
        holder.img.setImageResource(data.get(position).img);
        holder.time.setText(data.get(position).time);
        holder.date.setText(data.get(position).date);


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class vh extends RecyclerView.ViewHolder {
        View v;
        ImageView img;
        TextView emojiname;
        TextView time;
        TextView date;
        vh(View v)
        {
            super(v);
            this.v=v;
            img=v.findViewById(R.id.emoji);
            emojiname=v.findViewById(R.id.emojiname);
            time=v.findViewById(R.id.time);
            date=v.findViewById(R.id.date);
        }




    }
}
