package Employee;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;

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

public class olap1 extends AsyncTask<String, Void, String> {
    public static final String link = "http://192.168.44.208/api/OLAP1.php";
    Context context;
//    private OnDataFetchedListener listener;

    ArrayList<olap1model> modelArrayList = new ArrayList<>();

    public ArrayList<olap1model> getModelArrayList() {
        return modelArrayList;
    }

    public olap1() {
    }

    public olap1(Context context1) {
        context=context1;
    }


    public interface OnDataFetchedListener {
        void onDataFetched(ArrayList<olap1model> data);
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
//            productModelArorayList.clear();
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("CName");
                String phone = jsonObject.getString("CPhone");
                String count = jsonObject.getString("TotalOrders");
                olap1model olap1model = new olap1model(name, phone, count);
                modelArrayList.add(olap1model);

            }

//            Log.d("pname", productModelArrayList.get(0).getName());
//            Log.d("pname", productModelArrayList.get(0).getCategory());
            if (modelArrayList.size() > 0) {
//                ((EmployeeHome) context).onModelListLoaded(modelArrayList);

//                listener.onDataFetched(modelArrayList);
                Log.d("KOOO",modelArrayList.get(0).name);
                Intent intent=new Intent(context,EmployeeQuery.class);
                intent.putExtra("name1",modelArrayList.get(0).getName());
                intent.putExtra("phone1",modelArrayList.get(0).getPhone());
                intent.putExtra("count",modelArrayList.get(0).getCount());
                context.startActivity(intent);
            }

        } catch (Exception e) {


        }
    }


}
