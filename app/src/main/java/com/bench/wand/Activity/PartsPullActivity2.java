package com.bench.wand.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bench.wand.R;
import com.bench.wand.Utils.Constant;

import static com.bench.wand.Utils.Constant.EMPPIN;
import static com.bench.wand.Utils.Constant.txtSize;

/**
 * Created by Unni Vemanchery Mana on 12/1/2017.
 */

public class PartsPullActivity2 extends AppCompatActivity {

    Button txtOK;
    EditText editQuantity2;
    SharedPreferences sp, pref;
    String Prefrence = "Prefrence";
    int countSize;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.partscreen);
        sp = getSharedPreferences(Prefrence, Context.MODE_PRIVATE);
        pref = getSharedPreferences("contact", Context.MODE_PRIVATE);
        editQuantity2=(EditText) findViewById(R.id.editQuantity2);

        txtOK=(Button)findViewById(R.id.txtOK);
        txtOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editQuantity2.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Enter Quantity", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    countSize=Integer.parseInt(editQuantity2.getText().toString());



                    Intent intent=new Intent(getApplicationContext(),DisplayQualityTest.class);
                    intent.putExtra("size",countSize);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);

                }

                }

        });




    }

}
