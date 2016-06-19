package com.mohammed.shameem.addressbook.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.mohammed.shameem.addressbook.holder.SingleAddressDetailHolder;

import java.util.ArrayList;

/**
 * Created by shameem on 6/19/2016.
 */
public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.AddressListViewHolder> {
    public AddressListAdapter(Context context, ArrayList<SingleAddressDetailHolder> singleAddressDetailHolders) {
        this.context = context;
        this.singleAddressDetailHolders = singleAddressDetailHolders;
    }

    Context context;
ArrayList<SingleAddressDetailHolder> singleAddressDetailHolders;
    @Override
    public AddressListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
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
