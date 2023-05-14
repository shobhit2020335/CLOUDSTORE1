package customer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dbmsproject.R;

import java.util.ArrayList;

import delieveryAgent.myDeliveriesAdapter;

public class cartAdapter extends RecyclerView.Adapter<cartAdapter.viewHolder>{
    private ArrayList<cartModel> arrayList;
    private Context context;

    public cartAdapter(ArrayList<cartModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cartitems, parent, false);
        context = parent.getContext();
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        int pos=position;
        holder.id.setText("PID: "+arrayList.get(position).getPid());
        holder.name.setText("PNAME: "+arrayList.get(position).getPname());
        holder.quantity.setText("PQUANTITY: "+arrayList.get(position).getPquantity());
        holder.cost.setText("COST: "+arrayList.get(position).getPprice());
        holder.deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.remove(pos);
                Toast.makeText(context, "Item Removed from cart successfully. Refresh the Page", Toast.LENGTH_SHORT).show();
//                context.startActivity(new Intent(context,Cart.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    static class viewHolder extends RecyclerView.ViewHolder {

        TextView id,name,quantity,cost;
        Button deleteItem;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.cpid);
            name = itemView.findViewById(R.id.cpname);
            quantity = itemView.findViewById(R.id.cpquantity);
            cost = itemView.findViewById(R.id.cpcost);
            deleteItem=itemView.findViewById(R.id.deleteItem);


        }
    }
}
