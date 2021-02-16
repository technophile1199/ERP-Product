package com.example.vrajpatel.way2cis;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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

public class Fragment_sales_his extends Fragment {
    private RequestQueue requestQueue;
    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;
    RecyclerView recyclerView;
    RecyclerAdapter_sh adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Dataprovider_sh> arrayList = new ArrayList<Dataprovider_sh>();
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        ((HomeActivity)getActivity()).setActionBarTitle("Sales History");
        View view=inflater.inflate(R.layout.fragment_sales_his,container,false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        requestQueue= Volley.newRequestQueue(getActivity());
        String url="https://flexile-collision.000webhostapp.com/connect/sales2.php";
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try
                {
                    JSONArray jsonArray = response.getJSONArray("sales_hist");
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject sales_hist = jsonArray.getJSONObject(i);
                        String sale_code=sales_hist.getString("cust_code");
                        String sale_name=sales_hist.getString("cust_name");
                        String sale_qty=sales_hist.getString("qty");
                        String sale_total=sales_hist.getString("total");
                        String sale_date=sales_hist.getString("month_year");
                        arrayList.add(new Dataprovider_sh(sale_code,sale_name,sale_qty,sale_total,sale_date));
                    }
                    adapter=new RecyclerAdapter_sh(arrayList);
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
                    ArrayList<Dataprovider_sh> newList= new ArrayList<>();
                    for(Dataprovider_sh dataprovider_sh : arrayList)
                    {
                        String date=dataprovider_sh.getSale_date().toLowerCase();
                        if(date.contains(s))
                        {
                            newList.add(dataprovider_sh);
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
