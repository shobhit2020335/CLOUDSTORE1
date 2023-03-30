package customer;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import Product.ProductModel;

public class dbManager extends AsyncTask<String, Void, String> {
    private CustomerHome fragment;
    String link;
    Context context;
    ArrayList<ProductModel> productModelArrayList = new ArrayList<>();

    public dbManager() {
    }

    public dbManager(String url, CustomerHome fragment, Context context1) {
        this.fragment = fragment;
        link = url;
        context = context1;

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
            productModelArrayList.clear();
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("PName");
                String id = jsonObject.getString("PID");
                String price = jsonObject.getString("PPrice");
                String category = jsonObject.getString("PCategory");
                String quantity = jsonObject.getString("PQuantity");
                ProductModel productModel = new ProductModel(Integer.parseInt(id), name, Integer.parseInt(price), category);
                productModelArrayList.add(productModel);
            }
//            Log.d("pname", productModelArrayList.get(0).getName());
//            Log.d("pname", productModelArrayList.get(0).getCategory());
            if(productModelArrayList.size()>0) {
                fragment.updateData(productModelArrayList);
            }

        } catch (Exception e) {

        }
    }
}