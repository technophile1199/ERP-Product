package com.example.vrajpatel.way2cis;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class Fragment_inventory_stat extends Fragment {
    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        ((HomeActivity)getActivity()).setActionBarTitle("Inventory Status");
        return inflater.inflate(R.layout.fragment_inventory_stat,container,false);
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
