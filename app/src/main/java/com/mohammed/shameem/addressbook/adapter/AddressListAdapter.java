package com.mohammed.shameem.addressbook.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.mohammed.shameem.addressbook.R;
import com.mohammed.shameem.addressbook.controller.DBTools;
import com.mohammed.shameem.addressbook.holder.SingleAddressDetailHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shameem on 6/19/2016.
 */
public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.AddressListViewHolder> implements CompoundButton.OnCheckedChangeListener,Filterable {
    private Activity activity;
    private View cardView;
    List<SingleAddressDetailHolder> singleAddressDetailHolders;
    DBTools dbTools = new DBTools(activity);
    LayoutInflater inflater;

    public AddressListAdapter(Activity activity/*, ArrayList<SingleAddressDetailHolder> singleAddressDetailHolders*/) {
        this.activity = activity;
        this.singleAddressDetailHolders = singleAddressDetailHolders;
        inflater = LayoutInflater.from(this.activity);

    }


    @Override
    public AddressListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         cardView = inflater.inflate(R.layout.contact_entries, parent, false);
        AddressListViewHolder addressListViewHolder = new AddressListViewHolder(cardView);
        return addressListViewHolder;
    }

    @Override
    public void onBindViewHolder(AddressListViewHolder holder, int position) {
        // I am converting the Integer type to String Type to give a correct String resource Id
        ArrayList<String> strings = new ArrayList<>();
//        holder.tvContactsId.setText(String.valueOf(singleAddressDetailHolders.get(position).getCONTACT_ID()));
        if (holder.switchFlashOnOff != null) {
            holder.switchFlashOnOff.setOnCheckedChangeListener(this);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            // Set the flash on
         //dbTools.insertContact();
        } else {
            // Set the flash off
        }
    }

    @Override
    public int getItemCount() {
        return 50;
        /*return singleAddressDetailHolders.size();*/
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                Log.e("Address Adapter","Filtered Text "+constraint);
                return null;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

            }
        };
    }

    public class AddressListViewHolder extends RecyclerView.ViewHolder {
        CardView cvContainerView;
        TextView tvContactsId, tvLastName, tvFirstName;
        Switch switchFlashOnOff;

        public AddressListViewHolder(View itemView) {
            super(itemView);
            cvContainerView = (CardView) itemView.findViewById(R.id.cvContainerView);
            tvContactsId = (TextView) itemView.findViewById(R.id.tvContactsId);
            tvLastName = (TextView) itemView.findViewById(R.id.tvLastName);
            tvFirstName = (TextView) itemView.findViewById(R.id.tvFirstName);
            switchFlashOnOff = (Switch) itemView.findViewById(R.id.switchFlashOnOff);

        }
    }
}
