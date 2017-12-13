/*
package com.bench.wand.Activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import app.teacherapp.FCM.Config;
import app.teacherapp.FCM.NotificationUtils;
import app.teacherapp.R;
import app.teacherapp.Utils.AppController;
import app.teacherapp.Utils.Constant;


public class Login_Activity extends Fragment
{

    Button Login;
    EditText UserName, Password;
    TextView ForgotPassword;
    ImageView Close;

    private String strusername = "", strpassword = "";
    private ProgressDialog pDialog;
    private JSONObject success;

    SharedPreferences sp, pref;
    String Prefrence = "Prefrence";

    private BroadcastReceiver mRegistrationBroadcastReceiver;
    String regId;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View convertView = inflater.inflate(R.layout.activity_login, container, false);

        sp = getActivity().getSharedPreferences(Prefrence, Context.MODE_PRIVATE);
        pref = getActivity().getSharedPreferences("contact", Context.MODE_PRIVATE);

        UserName = (EditText) convertView.findViewById(R.id.input_email);
        Password = (EditText) convertView.findViewById(R.id.input_password);
        ForgotPassword = (TextView) convertView.findViewById(R.id.btn_forgotpassword);

        Close = (ImageView) convertView.findViewById(R.id.btn_close);
        Login = (Button) convertView.findViewById(R.id.btn_signin);




        ForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.dialog_forgotpassword);
                dialog.setTitle("Forgot Password");
                dialog.show();

                Button Dissmiss = (Button) dialog.findViewById(R.id.dissmiss);

                Dissmiss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();

                    }
                });
            }
        });

        Login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                strusername = UserName.getText().toString();
                strpassword = Password.getText().toString();
                if (strusername.toString().equals("") || strpassword.toString().equals(""))
                {
                    Toast.makeText(getActivity(), "Please Enter all fields", Toast.LENGTH_LONG).show();
                }
                else
                {
                    new PostDataTOServer().execute();
                }
            }
        });



        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                    displayFirebaseRegId();

                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received

                    String message = intent.getStringExtra("message");

                    Toast.makeText(getActivity(), "Push notification: " + message, Toast.LENGTH_LONG).show();


                }
            }
        };

        displayFirebaseRegId();

        return convertView;
    }
    private void displayFirebaseRegId() {
        SharedPreferences pref = getActivity().getSharedPreferences(Config.SHARED_PREF, 0);
        regId = pref.getString("regId", null);

        Log.e(AppController.TAG, "Firebase reg id: " + regId);

        if (!TextUtils.isEmpty(regId))
            System.out.println("the reg id :" + regId);
        else
            System.out.println("the reg id not get:" + regId);
    }
    private class PostDataTOServer extends AsyncTask<Void, Void, Void>
    {
        protected void onPreExecute()
        {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params)
        {
            String serverUrl = Constant.MAIN_URL+"teacher_register.php";
            System.out.println("the url is :" + serverUrl);

            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(serverUrl);

            try {
                JSONObject jo = new JSONObject();

                jo.put("teacher_id", "" + strusername);
                jo.put("password", "" + strpassword);
                jo.put("teacher_token", "" + regId);


                Log.d("teacher token",regId);

               // jo.put("school_id", "" + sp.getString(Constant.School_id,""));




                System.out.println("the send data is :" + jo);

                StringEntity se = new StringEntity(jo.toString());
                se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                post.setEntity(se);
                HttpResponse response = client.execute(post);
                HttpEntity entity = response.getEntity();
                String responseText = EntityUtils.toString(entity);
                System.out.println("the response is :" + responseText);
                success = new JSONObject(responseText);

            }
            catch (Exception e)
            {
                System.out.println("Exception inProfileActivity : " + e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)
        {
            super.onPostExecute(result);
            if (pDialog.isShowing())
                pDialog.dismiss();

            try
            {
                if (success.getString("success").equals("1"))
                {

                    JSONObject j = success.getJSONObject("post");
                    System.out.println("my login JSONObject : " + j);

                   // for (int i = 0; i < jsonArray.length(); i++)
                    {
                     //   JSONObject j = jsonArray.getJSONObject(i);

                        String id = j.getString("tid");
                        sp.edit().putString(Constant.Id, id).commit();

                        String Teacher_ID = j.getString("teacher_id");
                        sp.edit().putString(Constant.teacherId, Teacher_ID).commit();

                        String StudentName = j.getString("teacher name");
                        sp.edit().putString(Constant.TeacherName, StudentName).commit();

                        String Image = j.getString("profile");
                        sp.edit().putString(Constant.Image, Image).commit();

                        String Class = j.getString("class name");
                        sp.edit().putString(Constant.Class, Class).commit();

                        String Section = j.getString("section name");
                        sp.edit().putString(Constant.Section, Section).commit();

                        String ClassId = j.getString("class id");
                        sp.edit().putString(Constant.Class_id, ClassId).commit();

                        String SectionId = j.getString("section id");
                        sp.edit().putString(Constant.Section_id, SectionId).commit();

                        String PassWord = j.getString("password");
                        sp.edit().putString(Constant.Password, PassWord).commit();

                        String Email = j.getString("email");
                        sp.edit().putString(Constant.Email, Email).commit();

                        String Phone = j.getString("phone");
                        sp.edit().putString(Constant.Phone, Phone).commit();
*/
/*
                        String Dob = j.getString("subject id");
                        sp.edit().putString(Constant.Subject_id, Dob).commit();*//*


                        String Gender = j.getString("subject name");
                        sp.edit().putString(Constant.Subject_name, Gender).commit();

                        String ParentName = j.getString("designation");
                        sp.edit().putString(Constant.Designation, ParentName).commit();


                        String School_id = j.getString("school_id");
                        sp.edit().putString(Constant.s_id, School_id).commit();



                        String School_Logo = j.getString("school_picture");
                        sp.edit().putString(Constant.school_logo, School_Logo).commit();

                       */
/* String TeamName = j.getString("team name");
                        sp.edit().putString(Constant.TaemName, TeamName).commit();
                        *//*


                        sp.edit().putString(Constant.yes, "yes").commit();

                        Intent i = new Intent(getActivity(), MainActivity.class);
                        startActivity(i);
                        getActivity().finish();
                        getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);


                    }
                }
                else if (success.getString("success").equals("0"))
                {
                    Toast.makeText(getActivity(), "Incorrect UserName and Password", Toast.LENGTH_SHORT).show();
                }
                else
                {

                }
            }
            catch (Exception e)
            {
                System.out.println("the exx" + e);
            }

        }


    }

    @Override
    public void onResume() {
        super.onResume();

        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));

        // clear the notification area when the app is opened
        NotificationUtils.clearNotifications(getActivity());
    }

    @Override
    public void onPause() {
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }
    boolean doubleBackToExitPressedOnce = false;

   */
/* @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            finish();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                finish();
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
    *//*
*/
/*@Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(Login_Activity.this,MainActivity.class);
        startActivity(intent);
        this.finish();
    }*//*


}
*/
