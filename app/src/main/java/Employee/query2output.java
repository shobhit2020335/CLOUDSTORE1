package Employee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import com.example.dbmsproject.R;
import com.example.dbmsproject.databinding.ActivityQuery2outputBinding;

import java.util.ArrayList;

public class query2output extends AppCompatActivity {

    ActivityQuery2outputBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuery2outputBinding.inflate(getLayoutInflater());
        binding = ActivityQuery2outputBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();

//        ArrayList<Parcelable> q2list = getIntent().getParcelableArrayListExtra("query2list");
//        ArrayList<olap2model> q2l = new ArrayList<>();
//        for (Parcelable parcelable : q2list) {
//            q2l.add((olap2model) parcelable);
//        }

        String id1 = intent.getStringExtra("pid1");
        String id2 = intent.getStringExtra("pid2");
        String id3 = intent.getStringExtra("pid3");
        String id4 = intent.getStringExtra("pid4");
        String id5 = intent.getStringExtra("pid5");
//
        String pname1 = intent.getStringExtra("pname1");
        String pname2 = intent.getStringExtra("pname2");
        String pname3 = intent.getStringExtra("pname3");
        String pname4 = intent.getStringExtra("pname4");
        String pname5 = intent.getStringExtra("pname5");

        String sale1 = intent.getStringExtra("sale1");
        String sale2 = intent.getStringExtra("sale2");
        String sale3 = intent.getStringExtra("sale3");
        String sale4 = intent.getStringExtra("sale4");
        String sale5 = intent.getStringExtra("sale5");

        String quan1 = intent.getStringExtra("quant1");
        String quan2 = intent.getStringExtra("quant2");
        String quan3 = intent.getStringExtra("quant3");
        String quan4 = intent.getStringExtra("quant4");
        String quan5 = intent.getStringExtra("quant5");
//
        binding.pid1.setText(id1);
        binding.pid2.setText(id2);
        binding.pid3.setText(id3);
        binding.pid4.setText(id4);
        binding.pid5.setText(id5);

        Log.d("pppiii",id1);
        Log.d("pppiii",id2);
        Log.d("pppiii",id3);
        binding.pname1.setText(pname1);
        binding.pname2.setText(pname2);
        binding.pname3.setText(pname3);
        binding.pname4.setText(pname4);
        binding.pname5.setText(pname5);

        binding.sale1.setText(sale1);
        binding.sale2.setText(sale2);
        binding.sale3.setText(sale3);
        binding.sale4.setText(sale4);
        binding.sale5.setText(sale5);


        binding.quantity1.setText(quan1);
        binding.quantity2.setText(quan2);
        binding.quantity3.setText(quan3);
        binding.quantity4.setText(quan4);
        binding.quantity5.setText(quan5);
    }
}