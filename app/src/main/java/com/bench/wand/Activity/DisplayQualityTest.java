package com.bench.wand.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bench.wand.Adapter.Event_Adapter;
import com.bench.wand.Adapter.MyAdapter;
import com.bench.wand.Adapter.RecyclerViewAdapter;
import com.bench.wand.POJO.Event_Model;
import com.bench.wand.R;
import com.bench.wand.Utils.Constant;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Unni Mana on 11/24/2017.
 */

public class DisplayQualityTest extends AppCompatActivity {


    private static final String TAG = "LOGIN";

   ListView listdata;
    private ArrayList<HashMap<String, String>> categoryArrayList;
    private ArrayList<Event_Model> arrayList;
    private JSONObject success;

    SharedPreferences sp, pref;
    String Prefrence = "Prefrence";
    Event_Model category;
    String txtProtocol,txtVisitName,txtQuantity;
    private ProgressDialog pDialog;
    String StrPIN;
    RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    TextView txtError;
    int str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displaytest);

        sp = getSharedPreferences(Prefrence, Context.MODE_PRIVATE);
        pref = getSharedPreferences("contact", Context.MODE_PRIVATE);
        categoryArrayList = new ArrayList<HashMap<String, String>>();
        arrayList = new ArrayList<>();
//        listdata=(ListView) findViewById(R.id.listdata);
        txtError=(TextView)findViewById(R.id.txtError);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        StrPIN=sp.getString(Constant.EMPPIN,"");
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Log.d("pin is",StrPIN);
        Intent intent=getIntent();
        txtProtocol=sp.getString(Constant.txtProtocol,"");
        txtQuantity=sp.getString(Constant.txtQuantity,"");
        txtVisitName=sp.getString(Constant.txtVisitName,"");

        str=intent.getIntExtra("size",0);

        System.out.println("Protocol  2  :"+txtProtocol);
        System.out.println("Quantity  2  :"+txtQuantity);
        System.out.println("VisitName  2  :"+txtVisitName);

        new PostDataTOServer().execute();




    }
    private class PostDataTOServer extends AsyncTask<Void, Void, Void> {


        protected void onPreExecute() {
            super.onPreExecute();

          /*  pDialog = new ProgressDialog(getApplicationContext());
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
*/
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            String serverUrl = "http://47.49.196.130:3838/SpecificKit?Sedona=7894317&Userid=100&WandID=2&ProtocolName="+txtProtocol+"&VisitName="+txtVisitName+"&Quantity="+txtQuantity+"&Guid=b439e077-6648-4d1e-a058-a011da5a8754";

//            String serverUrl = "http://47.49.196.130:3838/SpecificKit?Sedona=7894317&Userid=ktester&WandID=2&ProtocolName=AGS67E-14-1&VisitName=Kit%20C:%20ADA&Quantity=1&Guid=b439e077-6648-4d1e-a058-a011da5a8754";
            System.out.println("the url is :" + serverUrl);

            HttpClient client = new DefaultHttpClient();
            HttpGet post = new HttpGet(serverUrl);

            try {


                JSONObject jo = new JSONObject();

               /* jo.put("ProtocolName", "AGS67E-14-1" );
                jo.put("VisitName", "Kit%20C:%20ADA" );
                jo.put("Quantity", "1");
                jo.put("Guid", "b439e077-6648-4d1e-a058-a011da5a8754");*/


                StringEntity se = new StringEntity(jo.toString());
                se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
//                post.setEntity(se);

                HttpResponse response = client.execute(post);
                HttpEntity entity = response.getEntity();
                String responseText = EntityUtils.toString(entity);

                success = new JSONObject(responseText);
                System.out.println("the response is :" + success);

            } catch (Exception e) {
                System.out.println("Exception inProfileActivity : " + e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        /* if (pDialog.isShowing())
          pDialog.dismiss();*/


            try {

//                if (success.getString("lCName").equals("AGENE141"))
                {
                    try {

                        JSONArray jsonArrayCategory = success.getJSONArray("items");

                        for (int i = 0; i < jsonArrayCategory.length(); i++) {

                            category = new Event_Model();
                            JSONObject catObj = jsonArrayCategory.getJSONObject(i);

                            category.setSupplyID(catObj.getString("supplyID"));
                            category.setLable(catObj.getString("label"));
                            category.setComment(catObj.getString("comment"));
                            category.setQuantity(catObj.getString("quantity"));
                            category.setHasInventoryContent(catObj.getString("hasInventoryContent"));
                            category.setVendorPartNumber(catObj.getString("vendorPartNumber"));
                            category.setlCDescription(catObj.getString("lCDescription"));
                            category.setVendorName(catObj.getString("vendorName"));


                            if (arrayList.size()<str)
                            {
                                Log.e("arraylist size",""+str);
                                arrayList.add(category);

                            }


                        }
                       /* Event_Adapter event_adapter = new Event_Adapter(getApplicationContext(), arrayList);
                        listdata.setAdapter(event_adapter);
                        event_adapter.notifyDataSetChanged();*/
                        RecyclerViewAdapter report_adapter=new RecyclerViewAdapter(getApplicationContext(),arrayList);
                        recyclerView.setAdapter(report_adapter);
                        recyclerView.setHasFixedSize(true);







                    } catch (JSONException e) {
                        System.out.println("the title exe is :" + e);


                        e.printStackTrace();
                    }


                }
            } catch (Exception e) {
                System.out.println("the title exe is :" + e);
                e.printStackTrace();
                Log.d("hello","Data is Not Available");
                txtError.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                txtError.setText("Data Not Available");


            }
        }
    }
}
