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

public class olap4 extends AsyncTask<String, Void, String> {
    public static final String link = "http://192.168.44.208/api/OLAP4.php";
    Context context;
//    private OnDataFetchedListener listener;

    ArrayList<String> categorylist = new ArrayList<>();
    ArrayList<String> revenuelist = new ArrayList<>();
//    ArrayList<String> pidlist = new ArrayList<>();
//    ArrayList<String> oquantitylist = new ArrayList<>();

//    public ArrayList<olap1model> getModelArrayList() {
//        return modelArrayList;
//    }

    public olap4() {

    }

    public olap4(Context context1) {
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
                String category = jsonObject.getString("PCategory");
                String revenue = jsonObject.getString("TotalRevenue");
//                String pid = jsonObject.getString("PID");
//                String oquantity = jsonObject.getString("OQuantity");
//                oidlist.add(oid);
//                pnamelist.add(pname);
//                pidlist.add(pid);
//                oquantitylist.add(oquantity);
                categorylist.add(category);
                revenuelist.add(revenue);


            }

//            Log.d("pname", productModelArrayList.get(0).getName());
//            Log.d("pname", productModelArrayList.get(0).getCategory());
            if (categorylist.size() > 0 && revenuelist.size() > 0 ) {

                Intent intent = new Intent(context, query4output.class);
                intent.putStringArrayListExtra("categorylist",categorylist);
                intent.putStringArrayListExtra("revenuelist", revenuelist);
//                intent.putStringArrayListExtra("pidlist", pidlist);
//                intent.putStringArrayListExtra("oquantitylist", oquantitylist);
                context.startActivity(intent);
            }

        } catch (Exception e) {


        }
    }


}
