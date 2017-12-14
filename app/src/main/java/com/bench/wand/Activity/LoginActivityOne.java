package com.bench.wand.Activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bench.wand.R;
import com.bench.wand.Utils.Constant;

/**
 * Created by Unni Mana on 11/24/2017.
 */

public class LoginActivityOne extends AppCompatActivity {

    SharedPreferences sp, pref;
    String Prefrence = "Prefrence";

    EditText editEmpID;
    private Button txtScan;
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_one);


        sp = getSharedPreferences(Prefrence, Context.MODE_PRIVATE);
        pref = getSharedPreferences("contact", Context.MODE_PRIVATE);
//        editEmpID = (EditText) findViewById(R.id.editEmpID);

        txtScan=(Button)findViewById(R.id.txtScan);

        txtScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),CheckPinActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });



    }

}
