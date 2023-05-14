package delieveryAgent;

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

import Employee.employeeLogin;
import Employee.solveCustomerQueriesAdapter;
import Employee.solvecustomerqueryModel;

public class undeliveredOrdersAdapter extends RecyclerView.Adapter<undeliveredOrdersAdapter.viewHolder> {

    private ArrayList<undeliveredOrdersModel> arrayList;
    private Context context;

    static String did = agentLogin.DID;


    public undeliveredOrdersAdapter(ArrayList<undeliveredOrdersModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    //    static String eid= employeeLogin.EID;


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.undeliveredordersitem, parent, false);
        context = parent.getContext();
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        int pos = position;
        holder.id.setText("OID: " + arrayList.get(pos).getOid());
        holder.cost.setText("OCOST: " + arrayList.get(pos).getOcost());
        holder.paymode.setText("OPAYMODE: " + arrayList.get(pos).getOpaymode());
        holder.status.setText("OSTATUS: " + arrayList.get(pos).getOstatus());
        holder.cname.setText("CNAME: " + arrayList.get(pos).getCname());
        holder.cphone.setText("CPHONE: " + arrayList.get(pos).getCphone());
        holder.caddress.setText("CADDRESS: " + arrayList.get(pos).getCaddress());
        holder.deliverOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue1 = Volley.newRequestQueue(context);
                String link = ApiLink.link;
                String url = link + "deliverOrder.php";

                StringRequest postRequest1 = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.length() > 0) {
                                    Log.d("RESPONSE", response);
                                    Toast.makeText(context, "Order Delivered Successfully", Toast.LENGTH_SHORT).show();
                                    context.startActivity(new Intent(context,agentUndeliveredOrders.class));


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
                        params.put("did", did);
                        params.put("oid", arrayList.get(pos).getOid());
                        return params;
                    }
                };
                queue1.add(postRequest1);


                Toast.makeText(context, "Order status changed to Deliverd", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    static class viewHolder extends RecyclerView.ViewHolder {

        TextView cost, id, paymode, status, cname, cphone, caddress;
        Button deliverOrder;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            cphone = itemView.findViewById(R.id.dcphone);
            id = itemView.findViewById(R.id.doid);
            cost = itemView.findViewById(R.id.docost);
            paymode = itemView.findViewById(R.id.dopaymode);
            status = itemView.findViewById(R.id.dostatus);
            cname = itemView.findViewById(R.id.dcname);
            caddress = itemView.findViewById(R.id.dcaddress);
            deliverOrder = itemView.findViewById(R.id.delieverorder);


        }
    }
}
