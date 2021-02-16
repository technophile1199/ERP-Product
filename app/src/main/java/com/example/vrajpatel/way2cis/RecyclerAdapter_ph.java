package com.example.vrajpatel.way2cis;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapter_ph extends RecyclerView.Adapter<RecyclerAdapter_ph.RecyclerViewHolder> {
    private ArrayList<Dataprovider_ph> arrayList=new ArrayList<Dataprovider_ph>();
    Dialog myDialog;
    public RecyclerAdapter_ph(ArrayList<Dataprovider_ph> arrayList)
    {
        this.arrayList=arrayList;
    }
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.purchase_his_row_layout, viewGroup, false);
        final RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
       myDialog=new Dialog(viewGroup.getContext());
        myDialog.setContentView(R.layout.purchase_his_popup);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        recyclerViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView pur_code=(TextView)myDialog.findViewById(R.id.pur_code);
                TextView pur_name=(TextView)myDialog.findViewById(R.id.pur_name);
                TextView pur_qty=(TextView)myDialog.findViewById(R.id.pur_qty);
                TextView pur_total=(TextView)myDialog.findViewById(R.id.pur_total);
                TextView pur_date=(TextView)myDialog.findViewById(R.id.pur_date);
                Button close=(Button)myDialog.findViewById(R.id.close);
                pur_code.setText(arrayList.get(recyclerViewHolder.getAdapterPosition()).getPur_code());
                pur_name.setText(arrayList.get(recyclerViewHolder.getAdapterPosition()).getPur_name());
                pur_qty.setText(arrayList.get(recyclerViewHolder.getAdapterPosition()).getPur_qty());
                pur_total.setText(arrayList.get(recyclerViewHolder.getAdapterPosition()).getPur_total());
                pur_date.setText(arrayList.get(recyclerViewHolder.getAdapterPosition()).getPur_date());
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myDialog.dismiss();
                    }
                });
                myDialog.show();
            }
        });
        return recyclerViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder viewHolder, int i) {
        Dataprovider_ph dataprovider_ph=arrayList.get(i);
        viewHolder.pur_code.setText(dataprovider_ph.getPur_code());
        viewHolder.pur_name.setText(dataprovider_ph.getPur_name());
        viewHolder.pur_qty.setText(dataprovider_ph.getPur_qty());
        viewHolder.pur_total.setText(dataprovider_ph.getPur_total());
        viewHolder.pur_date.setText(dataprovider_ph.getPur_date());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
        TextView pur_code,pur_name,pur_qty,pur_total, pur_date;
        LinearLayout linearLayout;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            pur_code = (TextView) itemView.findViewById(R.id.pur_code);
            pur_name = (TextView) itemView.findViewById(R.id.pur_name);
            pur_qty = (TextView) itemView.findViewById(R.id.pur_qty);
            pur_total = (TextView) itemView.findViewById(R.id.pur_total);
            pur_date = (TextView) itemView.findViewById(R.id.pur_date);
            linearLayout=(LinearLayout) itemView.findViewById(R.id.pur_his1);
        }
    }
    public void setFilter (ArrayList<Dataprovider_ph> newList)
    {
        arrayList=new ArrayList<>();
        arrayList.addAll(newList);
        notifyDataSetChanged();
    }
}