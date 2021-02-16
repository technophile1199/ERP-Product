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
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapter_sh extends RecyclerView.Adapter<RecyclerAdapter_sh.RecyclerViewHolder> {
    private ArrayList<Dataprovider_sh> arrayList=new ArrayList<Dataprovider_sh>();
    Dialog myDialog;
    public RecyclerAdapter_sh(ArrayList<Dataprovider_sh> arrayList)
    {
        this.arrayList=arrayList;
    }
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sales_his_row_layout, viewGroup, false);
        final RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        myDialog=new Dialog(viewGroup.getContext());
        myDialog.setContentView(R.layout.sales_his_popup);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        recyclerViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView sale_code=(TextView)myDialog.findViewById(R.id.sale_code);
                TextView sale_name=(TextView)myDialog.findViewById(R.id.sale_name);
                TextView sale_qty=(TextView)myDialog.findViewById(R.id.sale_qty);
                TextView sale_total=(TextView)myDialog.findViewById(R.id.sale_total);
                TextView sale_date=(TextView)myDialog.findViewById(R.id.sale_date);
                Button close=(Button)myDialog.findViewById(R.id.close);
                sale_code.setText(arrayList.get(recyclerViewHolder.getAdapterPosition()).getSale_code());
               sale_name.setText(arrayList.get(recyclerViewHolder.getAdapterPosition()).getSale_name());
                sale_qty.setText(arrayList.get(recyclerViewHolder.getAdapterPosition()).getSale_qty());
                sale_total.setText(arrayList.get(recyclerViewHolder.getAdapterPosition()).getSale_total());
                sale_date.setText(arrayList.get(recyclerViewHolder.getAdapterPosition()).getSale_date());
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
        Dataprovider_sh dataprovider_sh=arrayList.get(i);
        viewHolder.sale_code.setText(dataprovider_sh.getSale_code());
        viewHolder.sale_name.setText(dataprovider_sh.getSale_name());
        viewHolder.sale_qty.setText(dataprovider_sh.getSale_qty());
        viewHolder.sale_total.setText(dataprovider_sh.getSale_total());
        viewHolder.sale_date.setText(dataprovider_sh.getSale_date());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
        TextView sale_code,sale_name,sale_qty,sale_total, sale_date;
        LinearLayout linearLayout;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            sale_code = (TextView) itemView.findViewById(R.id.sale_code);
            sale_name = (TextView) itemView.findViewById(R.id.sale_name);
            sale_qty = (TextView) itemView.findViewById(R.id.sale_qty);
            sale_total = (TextView) itemView.findViewById(R.id.sale_total);
            sale_date = (TextView) itemView.findViewById(R.id.sale_date);
            linearLayout=(LinearLayout) itemView.findViewById(R.id.sale_his);
        }
    }
    public void setFilter (ArrayList<Dataprovider_sh> newList)
    {
        arrayList=new ArrayList<>();
        arrayList.addAll(newList);
        notifyDataSetChanged();
    }
}
