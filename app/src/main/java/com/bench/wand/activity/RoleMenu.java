package com.bench.wand.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bench.wand.R;

/**
 * Created by Unni Mana on 11/24/2017.
 */

public class RoleMenu extends AppCompatActivity {


    private static final String TAG = "LOGIN";

    private Button btnTube;

    private EditText editEmpID;

    private TextView txtOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rolemenu);

        btnTube=(Button) findViewById(R.id.btnTube);

        btnTube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),FullSingleKitActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);

            }
        });


    }
}
