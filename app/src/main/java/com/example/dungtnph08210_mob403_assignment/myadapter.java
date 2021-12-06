package com.example.dungtnph08210_mob403_assignment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


import java.util.List;

public class myadapter extends RecyclerView.Adapter<myadapter.myviewholder>{
    List<food> datalist ;

    public void setmyadapter(List<food> datalist) {
        this.datalist = datalist;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.tvnameitem1.setText(datalist.get(position).getName());
        holder.tvpriceitem1.setText(datalist.get(position).getPrice());
        Glide.with(holder.tvnameitem1.getContext()).load(datalist.get(position).getImage()).into(holder.imgitem1);
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        ImageView imgitem1;
        TextView tvnameitem1,tvpriceitem1;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            imgitem1=itemView.findViewById(R.id.imgimageitem1);
            tvnameitem1=itemView.findViewById(R.id.tvnameitem1);
            tvpriceitem1=itemView.findViewById(R.id.tvpriceitem1);
        }
    }
}
