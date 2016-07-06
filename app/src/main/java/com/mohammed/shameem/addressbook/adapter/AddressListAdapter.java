package com.mohammed.shameem.addressbook.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.mohammed.shameem.addressbook.R;
import com.mohammed.shameem.addressbook.controller.DBTools;
import com.mohammed.shameem.addressbook.holder.SingleAddressDetailHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shameem on 6/19/2016.
 */
public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.AddressListViewHolder> implements CompoundButton.OnCheckedChangeListener {
    Context context;
    List<SingleAddressDetailHolder> singleAddressDetailHolders;
    DBTools dbTools = new DBTools(context);
    LayoutInflater inflater;

    public AddressListAdapter(Context context, ArrayList<SingleAddressDetailHolder> singleAddressDetailHolders) {
        this.context = context;
        this.singleAddressDetailHolders = singleAddressDetailHolders;
        inflater = LayoutInflater.from(this.context);

    }


    @Override
    public AddressListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cardView = inflater.inflate(R.layout.contact_entries, parent, false);
        AddressListViewHolder addressListViewHolder = new AddressListViewHolder(cardView);
        return addressListViewHolder;
    }

    @Override
    public void onBindViewHolder(AddressListViewHolder holder, int position) {
        // I am converting the Integer type to String Type to give a correct String resource Id
        ArrayList<String> strings = new ArrayList<>();
        strings.add("Mohammed Shameem");
        strings.add("Mohammed Shameem");
        strings.add("Mohammed Shameem");
        holder.tvContactsId.setText(String.valueOf(singleAddressDetailHolders.get(position).getCONTACT_ID()));
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
        return singleAddressDetailHolders.size();
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
