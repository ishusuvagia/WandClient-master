package com.bench.wand.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bench.wand.R;

/**
 * Created by Unni Mana on 11/24/2017.
 */

public class FullSingleKitActivity extends AppCompatActivity {


    private static final String TAG = "LOGIN";

    private Button btnTube;

    private EditText editEmpID;

    private TextView txtNextWork,txtPartsKit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_single_kit);

        txtNextWork=(TextView) findViewById(R.id.txtNextWork);
        txtPartsKit=(TextView) findViewById(R.id.txtPartsKit);


        txtNextWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),PartsPullActivity.class);
                startActivity(intent);
            }
        });

        txtPartsKit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),QuanityActivity.class);
                startActivity(intent);
            }
        });


    }
}
