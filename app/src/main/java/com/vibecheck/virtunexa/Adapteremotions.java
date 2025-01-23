package com.vibecheck.virtunexa;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Vector;

public class Adapteremotions extends RecyclerView.Adapter<Adapteremotions.Viewadap> {
    //getting data
    Vector<emotionmix> data;
    Context context;
    private  int sendposition=-1;
    Adapteremotions(Context context,Vector<emotionmix> data){
        this.data=data;
        this.context=context;
    }
    @NonNull
    @Override
    public Viewadap onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.singleemotion,parent,false);
        Viewadap vh=new Viewadap(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewadap holder, int position) {
        holder.img.setImageResource(data.get(position).img);
        holder.emoji.setText(data.get(position).emoji);
        holder.img.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        if(sendposition==position)  //to set the color
        {
            holder.vh.setBackgroundResource(R.drawable.moodbg);
        }
        else {
            holder.vh.setBackgroundResource(android.R.color.transparent);
        }
        holder.vh.setOnClickListener(v -> {
            sendposition=holder.getAdapterPosition();
            notifyDataSetChanged(); //to tell after clicking data has changed in recylce view
        });





    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public int getposition(){
        return sendposition;
    }

    public class Viewadap extends RecyclerView.ViewHolder{
        View vh;
        ImageView img;
        TextView emoji;
        Viewadap(View vh) {
            super(vh);
            this.vh=vh;
            img=vh.findViewById(R.id.image);
            emoji=vh.findViewById(R.id.emotiontext);
        }
    }


}
