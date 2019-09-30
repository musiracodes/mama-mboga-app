package ke.co.musira.mamamboga;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;

import ke.co.musira.mamamboga.Models.GroceryItem;

public class MainFragment extends Fragment {
    private static final String TAG = "MainFragment";

    private RecyclerView newItemsRecView, popularItemsRecView, suggestedItemsRecView;
    private BottomNavigationView bottomNavigationView;

    private GroceryItemAdapter newItemsAdapter, popularItemsAdapter, suggestedItemsAdapter;

    private Utils utils;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        initViews(view);

        initBottomNavigation();

        utils = new Utils();
        utils.initDatabase(getActivity());

        initRecViews();

        return view;
    }

    private void initRecViews() {
        Log.d(TAG, "initRecViews: started");
        newItemsAdapter = new GroceryItemAdapter(getActivity());
        popularItemsAdapter = new GroceryItemAdapter(getActivity());
        suggestedItemsAdapter = new GroceryItemAdapter(getActivity());

        newItemsRecView.setAdapter(newItemsAdapter);
        popularItemsRecView.setAdapter(popularItemsAdapter);
        suggestedItemsRecView.setAdapter(suggestedItemsAdapter);

        newItemsRecView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        popularItemsRecView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        suggestedItemsRecView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));

        ArrayList<GroceryItem> allItems = utils.getAllItems(getActivity());
        if (null != allItems) {
            newItemsAdapter.setItems(allItems);
        }
    }

    private void initBottomNavigation() {
        Log.d(TAG, "initBottomNavigation: started");
        bottomNavigationView.setSelectedItemId(R.id.homeActivity);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.search:
                        //TODO: fix this
                        break;
                    case R.id.homeActivity:

                        break;
                    case R.id.cart:
                        Toast.makeText(getActivity(), "Cart Selected", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    private void initViews(View view) {
        Log.d(TAG, "initViews: started");
        bottomNavigationView = (BottomNavigationView) view.findViewById(R.id.bottomNavigationView);
        newItemsRecView = (RecyclerView) view.findViewById(R.id.newItemsRecView);
        popularItemsRecView = (RecyclerView) view.findViewById(R.id.popularItems);
        suggestedItemsRecView = (RecyclerView) view.findViewById(R.id.suggestedItems);
    }
}
