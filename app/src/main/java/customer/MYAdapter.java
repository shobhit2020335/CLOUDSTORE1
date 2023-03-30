package customer;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dbmsproject.R;

import java.util.ArrayList;

import Product.ProductModel;

public class MYAdapter extends RecyclerView.Adapter<MYAdapter.ViewHolder> {

    private ArrayList<ProductModel> mData=new ArrayList<>();
    CustomerHome fragment;
    FragmentManager fragmentManager;
    private Context context;



    public MYAdapter(Context context1) {
        context = context1;
    }

    public void setData(ArrayList<ProductModel> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ProductModel productm = mData.get(position);
        holder.name.setText(productm.getName());
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Intent intent = new Intent(activity, DetailActivity.class);
                intent.putExtra("productPrice", String.valueOf(productm.getPrice()));
                intent.putExtra("productCategory", productm.getCategory());
                intent.putExtra("productName", productm.getName());
                intent.putExtra("productID",String.valueOf(productm.getId()));
                activity.startActivity(intent);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.cat_heading);

        }
    }
}
