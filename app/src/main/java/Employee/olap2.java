package Employee;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;

import com.example.dbmsproject.ApiLink;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import Product.ProductModel;

public class olap2 extends AsyncTask<String, Void, String> {
    static String lin= ApiLink.link;

    public static final String link = lin+"OLAP2.php";
    Context context;
//    private OnDataFetchedListener listener;

    ArrayList<olap2model> modelArrayList = new ArrayList<>();

    public ArrayList<olap2model> getModelArrayList() {
        return modelArrayList;
    }

    public olap2() {
    }

    public olap2(Context context1) {
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
//            productModelArorayList.clear();
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                String pid = jsonObject.getString("PID");
                String pname = jsonObject.getString("PName");
                String sales = jsonObject.getString("TotalSales");
                String quant = jsonObject.getString("TotalQuantity");
                olap2model olapmodel = new olap2model(pid, pname, sales, quant);
                modelArrayList.add(olapmodel);

            }

//            Log.d("pname", productModelArrayList.get(0).getName());
//            Log.d("pname", productModelArrayList.get(0).getCategory());
            if (modelArrayList.size() >= 5) {
//                ((EmployeeHome) context).onModelListLoaded(modelArrayList);

//                listener.onDataFetched(modelArrayList);
                Log.d("KOOO111", modelArrayList.get(0).pid);
                Intent intent = new Intent(context, query2output.class);

                intent.putExtra("pid1", modelArrayList.get(0).getPid());
                intent.putExtra("pid2", modelArrayList.get(1).getPid());
                intent.putExtra("pid3", modelArrayList.get(2).getPid());
                intent.putExtra("pid4", modelArrayList.get(3).getPid());
                intent.putExtra("pid5", modelArrayList.get(4).getPid());

                intent.putExtra("pname1", modelArrayList.get(0).getPname());
                intent.putExtra("pname2", modelArrayList.get(1).getPname());
                intent.putExtra("pname3", modelArrayList.get(2).getPname());
                intent.putExtra("pname4", modelArrayList.get(3).getPname());
                intent.putExtra("pname5", modelArrayList.get(4).getPname());

                intent.putExtra("sale1",modelArrayList.get(0).getSales());
                intent.putExtra("sale2",modelArrayList.get(1).getSales());
                intent.putExtra("sale3",modelArrayList.get(2).getSales());
                intent.putExtra("sale4",modelArrayList.get(3).getSales());
                intent.putExtra("sale5",modelArrayList.get(4).getSales());


                intent.putExtra("quant1",modelArrayList.get(0).getQuantity());
                intent.putExtra("quant2",modelArrayList.get(1).getQuantity());
                intent.putExtra("quant3",modelArrayList.get(2).getQuantity());
                intent.putExtra("quant4",modelArrayList.get(3).getQuantity());
                intent.putExtra("quant5",modelArrayList.get(4).getQuantity());

//               intent.putExtra("pid5",modelArrayList.get(4).getPid());
//
//                startActivity(intent);
//                intent.putExtra("query2list", modelArrayList);
                context.startActivity(intent);
            }

        } catch (Exception e) {


        }
    }


}
