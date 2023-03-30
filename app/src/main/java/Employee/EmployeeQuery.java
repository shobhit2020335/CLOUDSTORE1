package Employee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.telephony.ims.ImsMmTelManager;
import android.util.Log;
import android.view.View;

import com.example.dbmsproject.R;
import com.example.dbmsproject.databinding.ActivityEmployeeQueryBinding;

import java.io.Serializable;
import java.util.ArrayList;

public class EmployeeQuery extends AppCompatActivity {
    ActivityEmployeeQueryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEmployeeQueryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        String nam1 = intent.getStringExtra("name1");
        String phone1 = intent.getStringExtra("phone1");
        String count1 = intent.getStringExtra("count");


//        ArrayList<Parcelable> q2list = getIntent().getParcelableArrayListExtra("query2list");
//        ArrayList<olap2model> q2l = new ArrayList<>();
//        for (Parcelable parcelable : q2list) {
//            q2l.add((olap2model) parcelable);
//        }

//        Bundle args = intent.getBundleExtra("BUNDLE");
//        ArrayList<olap2model> q2l = (ArrayList<olap2model>) args.getSerializable("query2list");
        binding.query1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployeeQuery.this, EmployeeHome.class);
                intent.putExtra("name1", nam1);
                intent.putExtra("phone1", phone1);
                intent.putExtra("count", count1);
                startActivity(intent);

            }
        });

        binding.query2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                olap2 o = new olap2(EmployeeQuery.this);
                o.execute();
//                Bundle args = new Bundle();
//                args.putSerializable("query2list", (Serializable) q2l);
//                intent.putExtra("BUNDLE", args);
//                intent.putExtra("query2list", q2l);
//                startActivity(intent);
            }
        });

        binding.query3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("olap3","clicked");
                olap3 o3=new olap3(EmployeeQuery.this);
                o3.execute();
//                Intent intent1=new Intent(EmployeeQuery.this,query3output.class);
//                startActivity(intent1);
            }
        });

        binding.query4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                olap4 o4=new olap4(EmployeeQuery.this);
                o4.execute();
            }
        });
    }
}