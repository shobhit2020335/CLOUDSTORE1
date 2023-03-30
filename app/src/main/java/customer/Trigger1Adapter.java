package customer;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dbmsproject.R;

import java.util.ArrayList;

import Product.ProductCount;
import Product.ProductModel;

public class Trigger1Adapter extends RecyclerView.Adapter<Trigger1Adapter.ViewHolder>{
    private ArrayList<ProductCount> productCountArrayList = new ArrayList<>();
    CustomerHome customerHome;
    Context context1;
    public Trigger1Adapter(Context context,ArrayList<ProductCount> productCountArrayList1) {
        productCountArrayList = productCountArrayList1;
        context1 = context;
    }
    public void setData(ArrayList<ProductCount> data) {
        productCountArrayList = data;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public Trigger1Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.triggerdata, parent, false);
        return new Trigger1Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Trigger1Adapter.ViewHolder holder, int position) {
        ProductCount productm = productCountArrayList.get(position);
        holder.PID.setText(productm.getPID());
        holder.PQuantity.setText(productm.getPQuantity());
        holder.PName.setText(productm.getPName());

    }



    @Override
    public int getItemCount() {
        return productCountArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView PName;
        public TextView PQuantity;
        public TextView PID;

        public ViewHolder(View itemView) {
            super(itemView);
            PID = itemView.findViewById(R.id.PID11);
            PName = itemView.findViewById(R.id.PName11);
            PQuantity = itemView.findViewById(R.id.PQuantity11);

        }
    }
}
