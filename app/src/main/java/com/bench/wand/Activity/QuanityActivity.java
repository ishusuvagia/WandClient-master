package com.bench.wand.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bench.wand.Adapter.Event_Adapter;
import com.bench.wand.POJO.Event_Model;
import com.bench.wand.R;
import com.bench.wand.Utils.Constant;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Unni Mana on 11/24/2017.
 */

public class QuanityActivity extends AppCompatActivity {


    private static final String TAG = "LOGIN";

    private Button btnTube;

    private EditText editProtocol,editVisit,editQuantity;

    private TextView txtOK;
    private JSONObject success;
    Event_Model category;

    String txtProtocol,txtVisitName,txtQuantity;

    SharedPreferences sp, pref;
    String Prefrence = "Prefrence";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quanity_activity);
        sp = getSharedPreferences(Prefrence, Context.MODE_PRIVATE);
        pref = getSharedPreferences("contact", Context.MODE_PRIVATE);
        txtOK=(TextView) findViewById(R.id.txtOK);

        editProtocol=(EditText) findViewById(R.id.editProtocol);
        editVisit=(EditText) findViewById(R.id.editVisit);
        editQuantity=(EditText) findViewById(R.id.editQuantity);

        /*editProtocol.setText("AGS67E-14-1");
        editVisit.setText("Kit%20C:%20ADA");
        editQuantity.setText("1");*/


        txtOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("clicked","ohk");






                if(editProtocol.getText().toString().equals("")  || editVisit.getText().toString().equals("") || editQuantity.getText().toString().equals(""))
               {

                   Toast.makeText(QuanityActivity.this, "Fill All Details", Toast.LENGTH_SHORT).show();



               }
               else
                {
                    txtProtocol=editProtocol.getText().toString().replaceAll(" ", "%20");;
                    txtQuantity=editQuantity.getText().toString().replaceAll(" ", "%20");;
                    txtVisitName=editVisit.getText().toString().replaceAll(" ", "%20");;


                    System.out.println("Protocol"+txtProtocol);
                    System.out.println("Quantity"+txtQuantity);
                    System.out.println("VisitName"+txtVisitName);

                    sp.edit().putString(Constant.txtProtocol, txtProtocol).commit();

                    sp.edit().putString(Constant.txtQuantity, txtQuantity).commit();

                    sp.edit().putString(Constant.txtVisitName, txtVisitName).commit();

                    Intent intentt=new Intent(getApplicationContext(),PartsPullActivity.class);
                  /* intentt.putExtra("txtProtocol",txtProtocol);
                   intentt.putExtra("txtQuantity",txtQuantity);
                   intentt.putExtra("txtVisitName",txtVisitName);
*/
                    startActivity(intentt);
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);


//                   new PostDataTOServer().execute();

                }
            }
        });


    }
    private class PostDataTOServer extends AsyncTask<Void, Void, Void> {


        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            String serverUrl = "http://47.49.196.130:3838/SpecificKit?Sedona=7894317&Userid=ktester&WandID=2&ProtocolName="+txtProtocol+"&VisitName="+txtVisitName+"&Quantity="+txtQuantity+"&Guid=b439e077-6648-4d1e-a058-a011da5a8754";

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



            try {


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





//                            arrayList.add(category);

                        }
                       /* Event_Adapter event_adapter = new Event_Adapter(getApplicationContext(), arrayList);
                        listdata.setAdapter(event_adapter);
                        event_adapter.notifyDataSetChanged();*/





                    } catch (JSONException e) {
                        System.out.println("the title exe is :" + e);
                        e.printStackTrace();
                    }


                }
            } catch (Exception e) {
                System.out.println("the title exe is :" + e);
                e.printStackTrace();

            }
        }
    }

}
