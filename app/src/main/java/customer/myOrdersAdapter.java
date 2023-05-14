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

public class myOrdersAdapter extends RecyclerView.Adapter<myOrdersAdapter.viewHolder> {
    private ArrayList<myOrdersModel> arrayList;
    private Context context;

    public myOrdersAdapter(ArrayList<myOrdersModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.myorderlist, parent, false);
        context = parent.getContext();
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        holder.id.setText(arrayList.get(position).getOid());
        holder.cost.setText(arrayList.get(position).getOcost());
        holder.status.setText(arrayList.get(position).getOstatus());
        holder.date.setText(arrayList.get(position).getOdate());
        holder.quantity.setText(arrayList.get(position).getOquantity());
        holder.pid.setText(arrayList.get(position).getPid());
        holder.pname.setText(arrayList.get(position).getPname());
        holder.paymode.setText(arrayList.get(position).getOpaymode());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();

    }

    static class viewHolder extends RecyclerView.ViewHolder{

        TextView id,cost,status,date,quantity,pid,pname,paymode;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.moid);
            cost = itemView.findViewById(R.id.mocost);
            status = itemView.findViewById(R.id.mostatus);
            date = itemView.findViewById(R.id.modate);
            quantity = itemView.findViewById(R.id.moquantity);
            pid = itemView.findViewById(R.id.mpid);
            pname = itemView.findViewById(R.id.mpname);
            paymode = itemView.findViewById(R.id.mopaymode);

        }
    }
}
