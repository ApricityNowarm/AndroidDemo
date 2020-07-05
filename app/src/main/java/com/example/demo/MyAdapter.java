package com.example.demo;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    List<UserData> allData = new ArrayList<>();

    public void setAllData(List<UserData> allData) {
        this.allData = allData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_layout,parent,false);
        return new MyViewHolder(itemView);
//        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        UserData userData = allData.get(position);
        switch (userData.getSort()) {
            case "饮食":
                holder.imageView.setImageResource(R.drawable.ic_canpan);
                holder.sort.setText("饮食");
                break;
            case "购物":
                holder.imageView.setImageResource(R.drawable.ic_gouwu);
                holder.sort.setText("购物");
                break;
            case "交通":
                holder.imageView.setImageResource(R.drawable.ic_jiaotong);
                holder.sort.setText("交通");
                break;
            case "零食":
                holder.imageView.setImageResource(R.drawable.ic_lingshi);
                holder.sort.setText("零食");
                break;
            case "娱乐":
                holder.imageView.setImageResource(R.drawable.ic_yule);
                holder.sort.setText("娱乐");
                break;
            case "水":
                holder.imageView.setImageResource(R.drawable.ic_shui);
                holder.sort.setText("水");
                break;
            case "电":
                holder.imageView.setImageResource(R.drawable.ic_dian);
                holder.sort.setText("电");
                break;
            case "网络":
                holder.imageView.setImageResource(R.drawable.ic_wangluo);
                holder.sort.setText("网络");
                break;
            case "住房":
                holder.imageView.setImageResource(R.drawable.ic_zhufang);
                holder.sort.setText("住房");
                break;
            case "教育":
                holder.imageView.setImageResource(R.drawable.ic_jiaoyu);
                holder.sort.setText("教育");
                break;
            case "育儿":
                holder.imageView.setImageResource(R.drawable.ic_yuer);
                holder.sort.setText("育儿");
                break;
            case "旅行":
                holder.imageView.setImageResource(R.drawable.ic_lvxing);
                holder.sort.setText("旅行");
                break;
            case "护肤品":
                holder.imageView.setImageResource(R.drawable.ic_hufu);
                holder.sort.setText("护肤品");
                break;
            case "日用品":
                holder.imageView.setImageResource(R.drawable.ic_riyong);
                holder.sort.setText("日用品");
                break;
            case "修车":
                holder.imageView.setImageResource(R.drawable.ic_xiuche);
                holder.sort.setText("修车");
                break;
            case "宠物":
                holder.imageView.setImageResource(R.drawable.ic_chongwu);
                holder.sort.setText("宠物");
                break;
            case "工资":
                holder.imageView.setImageResource(R.drawable.ic_gongzi);
                holder.sort.setText("工资");
                break;
            case "社交":
                holder.imageView.setImageResource(R.drawable.ic_shejiao);
                holder.sort.setText("社交");
                break;
            default:
                holder.imageView.setImageResource(R.drawable.ic_me);
                holder.sort.setText("其他");
                break;
        }
        if(userData.getSort().equals("工资"))
            holder.money.setText('+' + String.valueOf(userData.getData()));
        else
            holder.money.setText('-' + String.valueOf(userData.getData()));
        holder.date.setText(String.valueOf(userData.getDate()));

    }

    @Override
    public int getItemCount() {
        return allData.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView money,date,sort;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            money = itemView.findViewById(R.id.money);
            date = itemView.findViewById(R.id.date);
            sort = itemView.findViewById(R.id.sort);
        }
    }
}
