package com.example.win10.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.win10.myapplication.R;
import com.example.win10.myapplication.player;

/**
 * Created by win10 on 3/7/2018.
 */

public class manutd_adapter extends RecyclerView.Adapter<manutd_adapter.MyHolder>
{
    private static final String TAG = manutd_adapter.class.getSimpleName();

    public player[] data;

    public manutd_adapter(player[] data) {
        this.data = data;
    }


    @Override
    public manutd_adapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context=parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        MyHolder myHolder = new MyHolder(layoutInflater.inflate(R.layout.manutd_res,parent,false));

        return myHolder;
    }

    @Override
    public void onBindViewHolder(manutd_adapter.MyHolder holder, int position)
    {
        holder.bind(position);
    }


    @Override
    public int getItemCount()
    {
        return data.length;
    }
    public class MyHolder extends RecyclerView.ViewHolder{

        TextView name;

        ImageView img;

        public MyHolder(View view){
            super(view);
            name  = (TextView)view.findViewById(R.id.text);

            img =(ImageView)view.findViewById(R.id.img);
        }

        void bind(int listIndex){
            name.setText(String.valueOf(data[listIndex].getName()));

            img.setImageResource(data[listIndex].getId());
        }

    }
}
