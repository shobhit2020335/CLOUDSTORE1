package Employee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.dbmsproject.R;
import com.example.dbmsproject.databinding.ActivityQuery3outputBinding;
import com.example.dbmsproject.databinding.ActivityQuery4outputBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class query4output extends AppCompatActivity {
    ActivityQuery4outputBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuery4outputBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        ArrayList<String> categorylist = intent.getStringArrayListExtra("categorylist");
        ArrayList<String> revenuelist = intent.getStringArrayListExtra("revenuelist");
        binding.textView57.setText(categorylist.get(0));
        binding.textView56.setText(categorylist.get(1));
        binding.textView55.setText(categorylist.get(2));
        binding.textView54.setText(categorylist.get(3));
        binding.textView53.setText(categorylist.get(4));
        binding.textView52.setText(categorylist.get(5));
        binding.textView51.setText(categorylist.get(6));
        binding.textView50.setText(categorylist.get(7));

        binding.textview77.setText(revenuelist.get(0));
        binding.textview76.setText(revenuelist.get(1));
        binding.textview75.setText(revenuelist.get(2));
        binding.textview74.setText(revenuelist.get(3));
        binding.textview73.setText(revenuelist.get(4));
        binding.textview72.setText(revenuelist.get(5));
        binding.textview71.setText(revenuelist.get(6));
        binding.textview70.setText(revenuelist.get(7));
    }
}