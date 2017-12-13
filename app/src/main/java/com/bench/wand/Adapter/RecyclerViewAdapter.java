package com.bench.wand.Adapter;

import android.content.Context;
import android.database.Observable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.bench.wand.POJO.Event_Model;
import com.bench.wand.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {




    Context context;
    ViewHolder viewHolder;
    int lastPosition = -1;

    ArrayList<Event_Model> getDataAdapter;

    public RecyclerViewAdapter(Context context, ArrayList<Event_Model> arrayList){

        super();
        this.context = context;
        this.getDataAdapter =arrayList;
        final int REQUEST_PHONE_CALL = 1;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listdata, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {


        Event_Model category = getDataAdapter.get(position);

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
        Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.slide_left_slow);
        holder.itemView.setAnimation(animAnticipateOvershoot);


    }


    @Override
    public int getItemCount() {

        return getDataAdapter.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView txtQuantity,txtSupplyID,txtLable,txtComment,txtHasInventoryContent,txtVendorPartNumber,txtLCDescription,txtVendorName;


        public ViewHolder(View itemView) {

            super(itemView);

            txtSupplyID = (TextView) itemView.findViewById(R.id.txtSupplyID);
            txtQuantity = (TextView) itemView.findViewById(R.id.txtQuantity);
            txtLable = (TextView) itemView.findViewById(R.id.txtLable);
            txtComment = (TextView) itemView.findViewById(R.id.txtComment);
            txtHasInventoryContent = (TextView) itemView.findViewById(R.id.txtHasInventoryContent);
            txtVendorPartNumber = (TextView) itemView.findViewById(R.id.txtVendorPartNumber);
            txtLCDescription = (TextView) itemView.findViewById(R.id.txtLCDescription);
            txtVendorName = (TextView) itemView.findViewById(R.id.txtVendorName);

        }


    }

}
