package com.mohammed.shameem.addressbook.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
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
public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.AddressListViewHolder> implements CompoundButton.OnCheckedChangeListener, Filterable {
    private Activity activity;
    private View cardView;
    List<SingleAddressDetailHolder> originalSingleAddressDetailHolders;
    List<SingleAddressDetailHolder> newSingleAddressDetailHolders;
    DBTools dbTools = new DBTools(activity);
    LayoutInflater inflater;

    public AddressListAdapter(Activity activity, ArrayList<SingleAddressDetailHolder> singleAddressDetailHolders) {
        this.activity = activity;
        this.originalSingleAddressDetailHolders = singleAddressDetailHolders;
        this.newSingleAddressDetailHolders = singleAddressDetailHolders;
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
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return originalSingleAddressDetailHolders.size();
    }

    /**
     * <p>Returns a filter that can be used to constrain data with a filtering
     * pattern.</p>
     * <p>
     * <p>This method is usually implemented by {@link Adapter}
     * classes.</p>
     *
     * @return a filter used to constrain data
     */
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                if (constraint == null || constraint.length() == 0) {
                    // No filter implemented we return all the list
                    results.values = originalSingleAddressDetailHolders;
                    results.count = originalSingleAddressDetailHolders.size();
                } else {
                    List<SingleAddressDetailHolder> nRedeemList = new ArrayList<SingleAddressDetailHolder>();

                    for (SingleAddressDetailHolder addressholder : newSingleAddressDetailHolders) {
                        if (addressholder.getFIRST_NAME().toUpperCase().startsWith(constraint.toString().toUpperCase()))
                            nRedeemList.add(addressholder);
                    }

                    results.values = nRedeemList;
                    results.count = nRedeemList.size();
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results.count == 0) {
                    notifyAll();
                } else {
                    newSingleAddressDetailHolders = (List<SingleAddressDetailHolder>) results.values;
                    notifyDataSetChanged();

                }


            }
        };
    }

    public void resetData() {

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
