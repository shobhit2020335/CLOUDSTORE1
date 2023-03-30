//package customer;
//
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.example.dbmsproject.R;
//import com.example.dbmsproject.databinding.FragmentCustomerHomeBinding;
//
//import java.lang.reflect.Array;
//import java.util.ArrayList;
//
//import Product.ProductModel;
//
//public class CustomerHome extends Fragment {
//    MYAdapter adapter;
//    FragmentCustomerHomeBinding binding;
//    ArrayList<ProductModel> list=new ArrayList<>();
//
////    private static final String apiurl = "http://192.168.2.4/api/productdata.php";
//
//    public CustomerHome() {
//        // Required empty public constructor
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        binding = FragmentCustomerHomeBinding.inflate(inflater, container, false);
////        dbManager manager = new dbManager();
////        manager.execute(apiurl);
//        binding.RV1.setLayoutManager(new LinearLayoutManager(getActivity()));
//        adapter = new MYAdapter(getContext());
//        binding.RV1.setAdapter(adapter);
//        return binding.getRoot();
//    }
//
//    public void updateData(ArrayList<ProductModel> productModelArrayList) {
//        if(productModelArrayList.size()>0) {
//            adapter.setData(productModelArrayList);
//        }
//    }
//}

package customer;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dbmsproject.databinding.FragmentCustomerHomeBinding;

import java.util.ArrayList;

import Product.ProductModel;

public class CustomerHome extends Fragment {
    MYAdapter adapter;
    private static final String apiurl1 = "http://192.168.44.208/api/Trigger1.php";
    private static final String apiurl2 = "http://192.168.44.208/api/Trigger2.php";
    FragmentCustomerHomeBinding binding;
    ArrayList<ProductModel> list=new ArrayList<>();


    public CustomerHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCustomerHomeBinding.inflate(inflater, container, false);
        binding.RV1.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MYAdapter(getContext());
        binding.RV1.setAdapter(adapter);
        binding.Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToActivity();
            }
        });
        binding.BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Trigger1Async triggerManager = new Trigger1Async(apiurl1,getContext());
                triggerManager.execute();
            }
        });
        binding.DeliveredOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Trigger2Async trigger2Async = new Trigger2Async(apiurl2,getContext());
                trigger2Async.execute();
            }
        });
        return binding.getRoot();
    }

    public void updateData(ArrayList<ProductModel> productModelArrayList) {
        if(productModelArrayList.size()>0) {
            adapter.setData(productModelArrayList);
        }
    }
    // Inside your Fragment class
    public void goToActivity() {
        Intent intent = new Intent(getActivity(), CustomerProfile.class);
        startActivity(intent);
    }

}