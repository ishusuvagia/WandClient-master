package com.bench.wand.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.RippleDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bench.wand.POJO.User;
import com.bench.wand.R;
import com.bench.wand.Utils.Constant;
import com.bench.wand.Utils.SharedPrefManager;
import com.bench.wand.Utils.VolleySingleton;

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

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Unni Mana on 11/24/2017.
 */

public class CheckPinActivity extends AppCompatActivity {


    private static final String TAG = "LOGIN";

    private Button loginBtn;

    private EditText editEmpPIN;

    private TextView  txtError;

    private String strusername = "", strpassword = "";
    private ProgressDialog pDialog;
    private JSONObject success;

    private RippleDrawable rippleDrawable;
    private Button txtOk;


    String pin = "1234";
    Context mthis;


    SharedPreferences sp, pref;
    String Prefrence = "Prefrence";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkpin);
        sp = getSharedPreferences(Prefrence, Context.MODE_PRIVATE);
        pref = getSharedPreferences("contact", Context.MODE_PRIVATE);
        mthis = CheckPinActivity.this;
        txtOk = (Button) findViewById(R.id.txtOK);
        editEmpPIN = (EditText) findViewById(R.id.editEmpPIN);
        txtError = (TextView) findViewById(R.id.txtError);


        txtOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editEmpPIN.getText().toString().equals(pin)) {
                    txtError.setVisibility(View.GONE);

                      String EMPPIN = editEmpPIN.getText().toString();
                        sp.edit().putString(Constant.EMPPIN, EMPPIN).commit();

//                    new  PostAssignmentDataTOServer().execute();

                    Intent intent = new Intent(getApplicationContext(), RoleMenu.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);

                    new PostAssignmentDataTOServer().execute();
                }

                else {
                    txtError.setVisibility(View.VISIBLE);
                    txtError.setText("Please enter valid PIN");
                }

            }
        });


    }







    private class PostAssignmentDataTOServer extends AsyncTask<Void, Void, Void> {

        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            String serverUrl = "http://47.49.196.130:3838/Login?Sedona=7894317&rfid=100&UserPin=1234";
            System.out.println("the url is :" + serverUrl);

            HttpClient client = new DefaultHttpClient();
            HttpGet post = new HttpGet(serverUrl);


            try {
                Date date = Calendar.getInstance().getTime();
                //
                // Display a date in day, month, year format
                //
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String today = formatter.format(date);
                System.out.println("Today : " + today);
//                txtError.setText(success.getString("userid"));

/*
                JSONObject jo = new JSONObject();

                jo.put("date", "" + today);*/


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
            String jsonString = "";
            JSONArray jsonarray = null;
            try {
                jsonarray = new JSONArray(jsonString);

                for (int i = 0; i < jsonarray.length(); i++) {

                    JSONObject jsonobj = null;
                    try {
                        jsonobj = jsonarray.getJSONObject(i);
                        System.out.println("UserName : " + i + " = " + jsonobj.getString("userid"));
                        System.out.println("Roles : " + i + " = " + jsonobj.getString("roles"));
                        System.out.println("Guid : " + i + " = " + jsonobj.getString("guid"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }



        }
    }

}