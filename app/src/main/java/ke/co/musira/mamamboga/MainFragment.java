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

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import ke.co.musira.mamamboga.Models.GroceryItem;

public class MainFragment extends Fragment {
    private static final String TAG = "MainFragment";

    private BottomNavigationView bottomNavigationView;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        initViews(view);

        initBottomNavigation();

        Utils utils = new Utils();
        utils.initDatabase(getActivity());

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("fake_database", Context.MODE_PRIVATE);
        String returnedValue = sharedPreferences.getString("cheese", "");
        Gson gson = new Gson();
        GroceryItem cheese = gson.fromJson(returnedValue, GroceryItem.class);
        Toast.makeText(getActivity(), cheese.getName(), Toast.LENGTH_SHORT).show();

        return view;
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

    }
}
