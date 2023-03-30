package customer;

import android.content.Context;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dbmsproject.R;

import java.util.ArrayList;

import Orders.OrderStatus;
import Product.ProductCount;

public class Trigger2Adapter extends RecyclerView.Adapter<Trigger2Adapter.ViewHolder>{
    private ArrayList<OrderStatus> orderStatusArrayList = new ArrayList<>();
    Context context1;

    public Trigger2Adapter(Context context,ArrayList<OrderStatus> orderStatusArrayList) {
        this.orderStatusArrayList = orderStatusArrayList;
        context1 = context;
    }
    public void setData(ArrayList<OrderStatus> data) {
        orderStatusArrayList = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Trigger2Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trigger2data, parent, false);
        return new Trigger2Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Trigger2Adapter.ViewHolder holder, int position) {
        OrderStatus OrderS = orderStatusArrayList.get(position);
        holder.OID.setText(OrderS.getOID());
        holder.OCost.setText(OrderS.getOCost());
        holder.ODate.setText(OrderS.getODate());
        holder.OStatus.setText(OrderS.getOStatus());

    }

    @Override
    public int getItemCount() {
        return orderStatusArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView OID;
        public TextView ODate;
        public TextView OStatus;
        public TextView OCost;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            OID = itemView.findViewById(R.id.OID111);
            OStatus = itemView.findViewById(R.id.OStatus111);
            ODate = itemView.findViewById(R.id.ODate111);
            OCost = itemView.findViewById(R.id.OCost111);
        }
    }
}
