package com.bench.wand.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bench.wand.R;

/**
 * Created by Unni Vemanchery Mana on 12/1/2017.
 */

public class PartsPullActivity extends AppCompatActivity {
    SharedPreferences sp, pref;
    String Prefrence = "Prefrence";
    Button txtContinue;
    EditText editPartName,editPartNumber,editPartDescription,editBinLocation,editBinNumber,editShelf,editAisle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pulltoparts);
        sp = getSharedPreferences(Prefrence, Context.MODE_PRIVATE);
        pref = getSharedPreferences("contact", Context.MODE_PRIVATE);

        editPartName=(EditText)findViewById(R.id.editPartName);
        editPartNumber=(EditText)findViewById(R.id.editPartNumber);
        editPartDescription=(EditText)findViewById(R.id.editPartDescription);
        editBinLocation=(EditText)findViewById(R.id.editBinLocation);
        editBinNumber=(EditText)findViewById(R.id.editBinNumber);
        editShelf=(EditText)findViewById(R.id.editShelf);
        editAisle=(EditText)findViewById(R.id.editAisle);




        txtContinue=(Button)findViewById(R.id.txtContinue);
        txtContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editPartName.getText().toString().equals("") || editPartNumber.getText().toString().equals("") || editPartDescription.getText().toString().equals("") || editBinLocation.getText().toString().equals("") || editBinNumber.getText().toString().equals("") || editShelf.getText().toString().equals("") || editAisle.getText().toString().equals(""))
                {
                    Toast.makeText(PartsPullActivity.this, "Please Fill All Details", Toast.LENGTH_SHORT).show();

                }

                else
                {
                    Intent intent=new Intent(getApplicationContext(),PartsPullActivity2.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);


                }

            }
        });




    }


}
