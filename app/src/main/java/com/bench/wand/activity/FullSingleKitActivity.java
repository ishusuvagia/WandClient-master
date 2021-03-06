package com.bench.wand.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bench.wand.R;

/**
 * Created by Unni Mana on 11/24/2017.
 */

public class FullSingleKitActivity extends AppCompatActivity {


    private static final String TAG = "LOGIN";

    private Button btnTube;

    private EditText editEmpID;

    private Button txtNextWork,txtPartsKit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_single_kit);

        txtNextWork=(Button) findViewById(R.id.txtNextWork);
        txtPartsKit=(Button) findViewById(R.id.txtPartsKit);


        txtNextWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),PartsPullActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);

            }
        });

        txtPartsKit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),QuanityActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);

            }
        });


    }
}
