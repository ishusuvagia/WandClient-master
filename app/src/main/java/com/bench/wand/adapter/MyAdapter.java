package com.bench.wand.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bench.wand.pojo.Event_Model;
import com.bench.wand.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
//    private List<String> values;
    ArrayList<Event_Model> categoryArrayGrid;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View layout;
        TextView txtQuantity,txtSupplyID,txtLable,txtComment,txtHasInventoryContent,txtVendorPartNumber,txtLCDescription,txtVendorName;


        public ViewHolder(View v) {
            super(v);
            layout = v;
             txtSupplyID = (TextView) v.findViewById(R.id.txtSupplyID);
             txtQuantity = (TextView) v.findViewById(R.id.txtQuantity);
             txtLable = (TextView) v.findViewById(R.id.txtLable);
             txtComment = (TextView) v.findViewById(R.id.txtComment);
             txtHasInventoryContent = (TextView) v.findViewById(R.id.txtHasInventoryContent);
             txtVendorPartNumber = (TextView) v.findViewById(R.id.txtVendorPartNumber);
             txtLCDescription = (TextView) v.findViewById(R.id.txtLCDescription);
             txtVendorName = (TextView) v.findViewById(R.id.txtVendorName);

        }
    }



    // Provide a suitable constructor (depends on the kind of dataset)

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.listdata, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Event_Model category = categoryArrayGrid.get(position);

        holder.txtSupplyID.setText("supplyID  :  "+category.getSupplyID());
        holder.txtQuantity.setText("Quantity  :  "+category.getQuantity()+"  ");
        holder.txtLable.setText("Lable  :  "+category.getLable());
        holder.txtComment.setText("Comment  :  "+category.getComment());
        holder.txtHasInventoryContent.setText("InventoryContent  :  "+category.getHasInventoryContent());

        if (category.getVendorPartNumber().equals("null"))
        {
            holder.txtVendorPartNumber.setText("VendorPartNumber  :  " );

        }
        else
        {
            holder.txtVendorPartNumber.setText("VendorPartNumber  :  "+category.getVendorPartNumber());

        }
        if (category.getVendorName().equals("null"))
        {
            holder.txtVendorName.setText("VendorName  :  " );

        }
        else
        {
            holder.txtVendorName.setText("VendorName  :  "+category.getVendorName());

        }
        if (category.getlCDescription().equals("null"))
        {
            holder.txtLCDescription.setText("LCDescription  :  " );

        }
        else
        {
            holder.txtLCDescription.setText("LCDescription  :  "+category.getlCDescription());

        }

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    // Return the size of your dataset (invoked by the layout manager)

}