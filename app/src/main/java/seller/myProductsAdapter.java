package seller;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dbmsproject.ApiLink;
import com.example.dbmsproject.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import delieveryAgent.agentUndeliveredOrders;
import delieveryAgent.myDeliveriesAdapter;

public class myProductsAdapter extends RecyclerView.Adapter<myProductsAdapter.viewHolder>{
    private ArrayList<myProductsModel> arrayList;

    public myProductsAdapter(ArrayList<myProductsModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    private Context context;


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.myproductitems, parent, false);
        context = parent.getContext();
        return new viewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        int pos=position;
        holder.id.setText("Product ID: "+arrayList.get(position).getPid());
        holder.name.setText("Product Name: "+arrayList.get(position).getPname());
        holder.price.setText("Product Price: "+arrayList.get(position).getPprice());
        holder.quantity.setText("Product Quantity Left: "+arrayList.get(position).getPquantity());
        holder.refill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue1 = Volley.newRequestQueue(context);
                String link = ApiLink.link;
                String url = link + "refillStock.php";
                StringRequest postRequest1 = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.length() > 0) {
                                    Log.d("RESPONSE", response);
                                    Toast.makeText(context, "Stock Refilled Successfully", Toast.LENGTH_SHORT).show();
                                    context.startActivity(new Intent(context, myProducts.class));


//                                Toast.makeText(CustomerProfile.this, "Phone number passed", Toast.LENGTH_SHORT).show();
                                }
//                        Log.d("myorderslist", String.valueOf(myOrdersModelArrayList.size()));
//                        Intent intent1 = new Intent(CustomerProfile.this, CustomerOrders.class);
//                        startActivity(intent1);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                Log.d("ERRORRESPONSE", error.toString());

                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("quantity", "100");
                        params.put("pid", arrayList.get(pos).getPid());
                        return params;
                    }
                };
                queue1.add(postRequest1);



            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    static class viewHolder extends RecyclerView.ViewHolder {

        TextView id,name,price,quantity;
        Button refill;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.mypid);
            name = itemView.findViewById(R.id.mypname);
            price = itemView.findViewById(R.id.mypprice);
            quantity= itemView.findViewById(R.id.mypquantity);
            refill=itemView.findViewById(R.id.refilStock);


        }
    }
}
