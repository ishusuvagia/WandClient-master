package com.bench.wand.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bench.wand.pojo.Event_Model;
import com.bench.wand.R;

import java.util.ArrayList;


/**
 * Created by isquare3 on 29/06/17.
 */

public class Event_Adapter extends BaseAdapter {

    Context mActivity;
    private static LayoutInflater inflater;
    ArrayList<Event_Model> categoryArrayGrid;


    public Event_Adapter(Context activity, ArrayList<Event_Model> arrayList) {
        this.mActivity = activity;
        this.categoryArrayGrid = arrayList;
        inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public int getCount() {
        return categoryArrayGrid.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.listdata, null);

        TextView txtSupplyID = (TextView) convertView.findViewById(R.id.txtSupplyID);
        TextView txtQuantity = (TextView) convertView.findViewById(R.id.txtQuantity);
        TextView txtLable = (TextView) convertView.findViewById(R.id.txtLable);
        TextView txtComment = (TextView) convertView.findViewById(R.id.txtComment);
        TextView txtHasInventoryContent = (TextView) convertView.findViewById(R.id.txtHasInventoryContent);
        TextView txtVendorPartNumber = (TextView) convertView.findViewById(R.id.txtVendorPartNumber);
        TextView txtLCDescription = (TextView) convertView.findViewById(R.id.txtLCDescription);
        TextView txtVendorName = (TextView) convertView.findViewById(R.id.txtVendorName);


        //  TextView EndTime = (TextView) convertView.findViewById(R.id.endtime);

        Event_Model category = categoryArrayGrid.get(position);

        txtSupplyID.setText("supplyID  :  "+category.getSupplyID());
        txtQuantity.setText("Quantity  :  "+category.getQuantity()+"  ");
        txtLable.setText("Lable  :  "+category.getLable());
        txtComment.setText("Comment  :  "+category.getComment());
        txtHasInventoryContent.setText("InventoryContent  :  "+category.getHasInventoryContent());

        if (category.getVendorPartNumber().equals("null"))
        {
            txtVendorPartNumber.setText("VendorPartNumber  :  " );

        }
        else
        {
            txtVendorPartNumber.setText("VendorPartNumber  :  "+category.getVendorPartNumber());

        }
        if (category.getVendorName().equals("null"))
        {
            txtVendorName.setText("VendorName  :  " );

        }
        else
        {
            txtVendorName.setText("VendorName  :  "+category.getVendorName());

        }
        if (category.getlCDescription().equals("null"))
        {
            txtLCDescription.setText("LCDescription  :  " );

        }
        else
        {
            txtLCDescription.setText("LCDescription  :  "+category.getlCDescription());

        }





        return convertView;


    }
}
