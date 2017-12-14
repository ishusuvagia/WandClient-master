package com.bench.wand.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bench.wand.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Unni Vemanchery Mana on 12/1/2017.
 */

public class PartsPullActivity2 extends AppCompatActivity {

    Button txtOK;
    EditText editQuantity2;
    TextView editExpDate;
    SharedPreferences sp, pref;
    String Prefrence = "Prefrence";
    int countSize;
    String date_absent,date_resumption;
    String current_date;
    private int mYear, mMonth=0, mDay=0, mHour, mMinute;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.partscreen);
        sp = getSharedPreferences(Prefrence, Context.MODE_PRIVATE);
        pref = getSharedPreferences("contact", Context.MODE_PRIVATE);
        editQuantity2=(EditText) findViewById(R.id.editQuantity2);
        editExpDate=(TextView) findViewById(R.id.editExpDate);


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
        Date date = Calendar.getInstance().getTime();
        //
        // Display a date in day, month, year format
        //
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyy");
        String today = formatter.format(date);
        System.out.println("Today : " + today);
        editExpDate.setText(""+today);
        editExpDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate = Calendar.getInstance();
                mYear = mcurrentDate.get(Calendar.YEAR);
                mMonth = mcurrentDate.get(Calendar.MONTH);
                mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(PartsPullActivity2.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub


                        editExpDate.setText(selectedday + "-" + ( selectedmonth + 1 ) + "-" + selectedyear);


                    }
                }, mYear, mMonth, mDay);
                mDatePicker.getDatePicker().setCalendarViewShown(false);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();
            }
        });





    }

}
