package com.example.vrajpatel.way2cis;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.SearchView;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Fragment_purchase_his extends Fragment {
    private RequestQueue requestQueue;
    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;
    RecyclerView recyclerView;
    RecyclerAdapter_ph adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Dataprovider_ph> arrayList = new ArrayList<Dataprovider_ph>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((HomeActivity)getActivity()).setActionBarTitle("Purchase History");
        View view=inflater.inflate(R.layout.fragment_purchase_his,container,false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        requestQueue= Volley.newRequestQueue(getActivity());
        String url="https://flexile-collision.000webhostapp.com/connect/purchase2.php";
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try
                {
                    JSONArray jsonArray = response.getJSONArray("sales_hist");
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject sales_hist = jsonArray.getJSONObject(i);
                        String pur_code=sales_hist.getString("supplier_code");
                        String pur_name=sales_hist.getString("supplier_name");
                        String pur_qty=sales_hist.getString("qty");
                        String pur_total=sales_hist.getString("net_total");
                        String pur_date=sales_hist.getString("month_year");
                        arrayList.add(new Dataprovider_ph(pur_code,pur_name,pur_qty,pur_total,pur_date));
                    }
                    adapter=new RecyclerAdapter_ph(arrayList);
                    recyclerView.setAdapter(adapter);
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);
        return view;
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem=menu.findItem(R.id.action_search);
        SearchManager searchManager =(SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        if(searchItem != null)
        {
            searchView=(SearchView) searchItem.getActionView();
        }
        if(searchView != null)
        {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
            searchView.setQueryHint("Enter Date...");
            int searchEditId = android.support.v7.appcompat.R.id.search_src_text;
            EditText et = (EditText) searchView.findViewById(searchEditId);
            et.setTextColor(Color.WHITE);
            et.setHintTextColor(getResources().getColor(R.color.gray));
            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    Log.i("onQueryTextChange", s);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    Log.i("onQueryTextChange", s);
                    s= s.toLowerCase();
                    ArrayList<Dataprovider_ph> newList= new ArrayList<>();
                    for(Dataprovider_ph dataprovider_ph : arrayList)
                    {
                        String date=dataprovider_ph.getPur_date().toLowerCase();
                        if(date.contains(s))
                        {
                            newList.add(dataprovider_ph);
                        }
                    }
                    adapter.setFilter(newList);
                    return true;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                // Not implemented here
                return false;
            default:
                break;
        }
        searchView.setOnQueryTextListener(queryTextListener);
        return super.onOptionsItemSelected(item);
    }
}
