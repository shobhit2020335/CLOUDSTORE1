package Employee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dbmsproject.R;
import com.example.dbmsproject.databinding.ActivityQuery3outputBinding;

import java.util.ArrayList;

public class query3output extends AppCompatActivity {
    ActivityQuery3outputBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuery3outputBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        ArrayList<String> oidlist = intent.getStringArrayListExtra("oidlist");
        ArrayList<String> pnamelist = intent.getStringArrayListExtra("pnamelist");
        ArrayList<String> pidlist = intent.getStringArrayListExtra("pidlist");
        ArrayList<String> oquantitylist = intent.getStringArrayListExtra("oquantitylist");
        for (int i = 0; i < oidlist.size(); i++) {
//            Toast.makeText(this, pnamelist.get(i), Toast.LENGTH_SHORT).show();
            TextView textView = new TextView(this);
            textView.setText(oidlist.get(i) + "     " + pnamelist.get(i) + "     " + pidlist.get(i) + "       " + oquantitylist.get(i));
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(25);
            binding.txtlayout.addView(textView);

        }
    }
}