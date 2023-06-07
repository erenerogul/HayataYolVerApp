package com.gaziuni.hayatayolverapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserCallAdapter extends RecyclerView.Adapter<MyViewHolder> {
     Context context;
     ArrayList<UserCall> userCallList;

    public UserCallAdapter(Context context, ArrayList<UserCall> userCallList) {
        this.context = context;
        this.userCallList = userCallList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        UserCall userCall = userCallList.get(position);
        Double clat = 39.78625678132127,clong =30.488130495131692 ;
        //Ambulans Konumu Güncel konumu almak üzere ayarlanmıştır ancak emülatörde çalıştığımız senaryolar üzerinden proje test edilmiş ve çalıştığı görülmüştür.
        DecimalFormat df = new DecimalFormat("0.0");
        String formationDistance = df.format(haversineDistance(userCall.getUlatitude(),userCall.getUlongitude(),clat,clong))+"Km";
        holder.recName.setText(userCall.getNamesurname());
        holder.recPhone.setText(userCall.getPhone());
        holder.recdistance.setText(formationDistance);
        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("NameSurname",userCallList.get(holder.getAdapterPosition()).getNamesurname());
                intent.putExtra("Phone",userCallList.get(holder.getAdapterPosition()).getPhone());
                intent.putExtra("Ulongitude",userCallList.get(holder.getAdapterPosition()).getUlongitude());
                intent.putExtra("Ulatitude",userCallList.get(holder.getAdapterPosition()).getUlatitude());
                intent.putExtra("Address",userCallList.get(holder.getAdapterPosition()).getAdress());
                intent.putExtra("Distance",formationDistance);
                HashMap<String,Double> hashMap2 = new HashMap<>();
                hashMap2.put("driverLatitude",clat);
                hashMap2.put("driverLongitude",clong);
                context.startActivity(intent);
            }
        });
    }
    static Double haversineDistance(Double ulat,Double ulong,Double clat,Double clong){
        double dLat = Math.toRadians(clat - ulat);
        double dLon = Math.toRadians(clong - ulong);

        ulat = Math.toRadians(ulat);
        clat = Math.toRadians(clat);

        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(ulat) *
                        Math.cos(clat);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        return rad * c;
    }

    @Override
    public int getItemCount() {
        return userCallList.size();
    }
}
class MyViewHolder extends RecyclerView.ViewHolder{
    TextView recName,recPhone,recdistance;
    CardView recCard;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        recName = itemView.findViewById(R.id.recName);
        recPhone = itemView.findViewById(R.id.recPhone);
        recdistance = itemView.findViewById(R.id.recDistance);
        recCard = itemView.findViewById(R.id.recCard);
    }

}