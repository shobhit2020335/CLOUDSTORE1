package delieveryAgent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dbmsproject.R;

import java.util.ArrayList;

public class myDeliveriesAdapter  extends RecyclerView.Adapter<myDeliveriesAdapter.viewHolder>{
    private ArrayList<String> arrayList;
    private Context context;

    public myDeliveriesAdapter(ArrayList<String> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    static String did = agentLogin.DID;

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.myordersitm, parent, false);
        context = parent.getContext();
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        holder.id.setText("Order ID: "+arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    static class viewHolder extends RecyclerView.ViewHolder {

        TextView  id;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.textView21);


        }
    }
}
