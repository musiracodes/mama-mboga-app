package ke.co.musira.mamamboga;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import ke.co.musira.mamamboga.Models.GroceryItem;
import ke.co.musira.mamamboga.Models.Order;

public class SuccessfulPaymentFragment extends Fragment {
    private static final String TAG = "SuccessfulPalymentFragm";

    private Button btnGoBack;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_success_payment, container, false);

        Utils utils = new Utils(getActivity());
        utils.removeCartItems();

        Bundle bundle = getArguments();
        try {
            Order order = bundle.getParcelable("order");
            ArrayList<Integer> itemIds = order.getItems();
            utils.addPopularityPoint(itemIds);
        }catch (NullPointerException e) {
            e.printStackTrace();
        }

            btnGoBack = (Button) view.findViewById(R.id.btnGoBack);

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        return view;
    }
}
