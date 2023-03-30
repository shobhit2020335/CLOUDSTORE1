package customer;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import Product.ProductCount;

public class Trigger1Async extends AsyncTask<String, Void, String> {
    String link;
    Trigger1 trigger1;
    Context context;
    private CustomerHome customerHome;
    ArrayList<ProductCount> productCountArrayList = new ArrayList<>();
    public Trigger1Async(String url, Context context1) {
        this.link = url;
        context= context1;
    }
    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer buffer = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                buffer.append(line + "\n");
            }
            br.close();
            return buffer.toString();

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            JSONArray jsonArray = new JSONArray(s);
            JSONObject jsonObject = null;
            productCountArrayList.clear();
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                String PName = jsonObject.getString("PName");
                String PID = jsonObject.getString("PID");
                String PQuantity = jsonObject.getString("PQuantity");
                ProductCount productCount = new ProductCount(PName,PID, PQuantity);
                productCountArrayList.add(productCount);
            }
            if(productCountArrayList.size()>0) {
                Bundle args = new Bundle();
                Intent intent = new Intent(context,Trigger1.class);
                args.putSerializable("ARRAYLIST",(Serializable)productCountArrayList);
                intent.putExtra("BUNDLE",args);
                context.startActivity(intent);
            }

        } catch (Exception e) {
            Log.d("Catch",e.toString());
        }
    }
}
