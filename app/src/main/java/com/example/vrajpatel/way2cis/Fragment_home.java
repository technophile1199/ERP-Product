package com.example.vrajpatel.way2cis;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment_home extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((HomeActivity)getActivity()).setActionBarTitle("");
        CardView purhase_his,sales_his,total_out,inv_stat,acc_pay;
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        purhase_his=view.findViewById(R.id.purchase_his);
        sales_his=view.findViewById(R.id.sales_his);
        total_out=view.findViewById(R.id.total_out);
        inv_stat=view.findViewById(R.id.inv_stat);
        acc_pay=view.findViewById(R.id.acc_pay);
        purhase_his.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,new Fragment_purchase_his()).addToBackStack(null);
                fr.commit();
            }
        });
        sales_his.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,new Fragment_sales_his()).addToBackStack(null);
                fr.commit();
            }
        });
        total_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,new Fragment_total_outstanding()).addToBackStack(null);
                fr.commit();
            }
        });
        inv_stat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,new Fragment_inventory_stat()).addToBackStack(null);
                fr.commit();
            }
        });
        acc_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,new Fragment_account_pay()).addToBackStack(null);
                fr.commit();
            }
        });
        return view;
    }
}
