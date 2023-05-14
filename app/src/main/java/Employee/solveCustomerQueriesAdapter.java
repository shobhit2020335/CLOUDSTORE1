package Employee;

import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dbmsproject.ApiLink;
import com.example.dbmsproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import customer.CustomerProfile;
import customer.Query;
import customer.customerHome1;
import customer.myQueriesAdapter;
import customer.myQueriesModel;

public class solveCustomerQueriesAdapter extends RecyclerView.Adapter<solveCustomerQueriesAdapter.viewHolder> {



    private ArrayList<solvecustomerqueryModel> arrayList;
    static String eid=employeeLogin.EID;
    private Context context;


    public solveCustomerQueriesAdapter(ArrayList<solvecustomerqueryModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.solvecustomerqueriesitem, parent, false);
        context = parent.getContext();
        return new  viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        int pos=position;

        holder.cphone.setText("Customer phone no:"+ arrayList.get(pos).getCphone());
        holder.id.setText("Query ID: "+arrayList.get(pos).getQid());
        holder.text.setText("Query: "+arrayList.get(pos).getQtext());
        holder.status.setText("Query Status: "+arrayList.get(pos).getQstatus());
        holder.respondbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                builder.setTitle("Enter Query Response:");
//                Log.d("clicked1",":clicked");
//                final EditText input = new EditText(context);
//                input.setInputType(InputType.TYPE_CLASS_TEXT);
//                builder.setView(input);
//
//                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//
//                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                });
//
//                builder.show();

                RequestQueue queue1 = Volley.newRequestQueue(context);
                String link = ApiLink.link;
                String url = link + "solvequery.php";

                StringRequest postRequest1 = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.length()>0) {
                                    Log.d("RESPONSE", response);
                                    Toast.makeText(context, "Query Responded Successfully", Toast.LENGTH_SHORT).show();


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
                        params.put("eid", eid);
                        params.put("qid", arrayList.get(pos).getQid());
//                        params.put("qresponse",)
                        return params;
                    }
                };
                queue1.add(postRequest1);


                Toast.makeText(context, "query answered", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    static class viewHolder extends RecyclerView.ViewHolder{

        TextView cphone,id,text,status,respondbutton;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            cphone=itemView.findViewById(R.id.solvecphone);
            id = itemView.findViewById(R.id.solveqid);
            text = itemView.findViewById(R.id.solveqtext);
            status = itemView.findViewById(R.id.solveqstatus);
            respondbutton=itemView.findViewById(R.id.solvequery);


        }
    }
}
