package Employee;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;

import com.example.dbmsproject.ApiLink;

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

public class olap3 extends AsyncTask<String, Void, String> {
    static String lin= ApiLink.link;

    public static final String link = lin+"OLAP3.php";
    Context context;
//    private OnDataFetchedListener listener;

    ArrayList<String> oidlist = new ArrayList<>();
    ArrayList<String> pnamelist = new ArrayList<>();
    ArrayList<String> pidlist = new ArrayList<>();
    ArrayList<String> oquantitylist = new ArrayList<>();

//    public ArrayList<olap1model> getModelArrayList() {
//        return modelArrayList;
//    }

    public olap3() {
    }

    public olap3(Context context1) {
        context = context1;
    }


    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            Log.d("established","connectedsss");
            StringBuffer buffer = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                buffer.append(line + "\n");
            }
            br.close();
            Log.d("established","connectedsss1");
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
            Log.d("established","connectedsss2");
            JSONArray j=new JSONArray(s);

//            productModelArorayList.clear();
            oidlist.clear();
            pnamelist.clear();
            pidlist.clear();
            oquantitylist.clear();
            Log.d("established",String.valueOf(j.length()));
            Log.d("established","connectedsss4");
            for (int i = 0; i < j.length(); i++) {
               JSONObject jsonObject = j.getJSONObject(i);
                String oid = jsonObject.getString("OID");
                String pname = jsonObject.getString("PName");
                String pid = jsonObject.getString("PID");
                String oquantity = jsonObject.getString("OQuantity");
                Log.d("established","connectedsss3");
                oidlist.add(oid);
                pnamelist.add(pname);
                pidlist.add(pid);
                oquantitylist.add(oquantity);
            }
//            for (int i=0;i<oidlist.size();i++){
                Log.d("query3ooo",String.valueOf(oidlist.size()));
//            }


//            Log.d("pname", productModelArrayList.get(0).getName());
//            Log.d("pname", productModelArrayList.get(0).getCategory());
//            if (oidlist.size() > 0 && pnamelist.size() > 0 && pidlist.size() > 0 && oquantitylist.size() > 0) {
//                ((EmployeeHome) context).onModelListLoaded(modelArrayList);

//                listener.onDataFetched(modelArrayList);
//                Log.d("KOOO",modelArrayList.get(0).name);
                Intent intent = new Intent(context, query3output.class);
                intent.putStringArrayListExtra("oidlist", oidlist);
                intent.putStringArrayListExtra("pnamelist", pnamelist);
                intent.putStringArrayListExtra("pidlist", pidlist);
                intent.putStringArrayListExtra("oquantitylist", oquantitylist);
                context.startActivity(intent);
//            }

        } catch (Exception e) {
            Log.d("cathhhhhhhh",e.toString());
            Log.d("catchchhhh1",s);


        }
    }


}
