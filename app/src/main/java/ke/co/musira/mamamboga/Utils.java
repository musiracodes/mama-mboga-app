package ke.co.musira.mamamboga;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;


import ke.co.musira.mamamboga.Models.GroceryItem;


public class Utils {
    private static final String TAG = "Utils";

    public static final String DATABASE_NAME = "fake_database";

    private static int ID = 0;

    public Utils() {
    }

    public static int getId() {
        ID++;
        return ID;
    }

    public void initDatabase(Context context) {
        Log.d(TAG, "initDatabase: started");
        SharedPreferences sharedPreferences = context.getSharedPreferences(DATABASE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        GroceryItem item = new GroceryItem("cheese","tastes good", "",
                "food", 15, 2.8);
        Gson gson = new Gson();
        String jsonFile = gson.toJson(item);
        editor.putString("cheese", jsonFile);
        editor.apply();

    }
}
