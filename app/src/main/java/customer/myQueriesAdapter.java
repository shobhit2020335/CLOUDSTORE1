package customer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dbmsproject.R;

import java.util.ArrayList;

public class myQueriesAdapter extends RecyclerView.Adapter<myQueriesAdapter.viewHolder>{

    private ArrayList<myQueriesModel> arrayList;
    private Context context;

    public myQueriesAdapter(ArrayList<myQueriesModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.customerqueryitem, parent, false);
        context = parent.getContext();
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        holder.id.setText(arrayList.get(position).getQid());
        holder.text.setText(arrayList.get(position).getQtext());
        holder.status.setText(arrayList.get(position).getQstatus());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    static class viewHolder extends RecyclerView.ViewHolder{

        TextView id,text,status;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.qid);
            text = itemView.findViewById(R.id.qtext);
            status = itemView.findViewById(R.id.qstatus);


        }
    }
}
