package com.mohammed.shameem.addressbook.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mohammed.shameem.addressbook.R;
import com.mohammed.shameem.addressbook.holder.SingleAddressDetailHolder;

import java.util.ArrayList;

/**
 * Created by shameem on 6/19/2016.
 */
public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.AddressListViewHolder> {
    Context context;
    ArrayList<SingleAddressDetailHolder> singleAddressDetailHolders;
    LayoutInflater inflater;

    public AddressListAdapter(Context context, ArrayList<SingleAddressDetailHolder> singleAddressDetailHolders) {
        this.context = context;
        this.singleAddressDetailHolders = singleAddressDetailHolders;
        inflater = LayoutInflater.from(this.context);
    }


    @Override
    public AddressListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cardView = inflater.inflate(R.layout.contact_entries, parent, false);
        AddressListViewHolder addressListViewHolder=new AddressListViewHolder(cardView);
        return addressListViewHolder;
    }

    @Override
    public void onBindViewHolder(AddressListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return singleAddressDetailHolders.size();
    }

    public class AddressListViewHolder extends RecyclerView.ViewHolder {
        public AddressListViewHolder(View itemView) {
            super(itemView);
        }
    }
}
