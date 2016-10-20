package com.mohammed.shameem.addressbook.adapter;

import android.app.Activity;
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
import com.mohammed.shameem.addressbook.interfaces.ItemClickListner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shameem on 6/19/2016.
 */
public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.AddressListViewHolder> implements Filterable {

    private Activity activity;
    private View cardView;
    private List<SingleAddressDetailHolder> originalSingleAddressDetailHolders;
    private List<SingleAddressDetailHolder> newSingleAddressDetailHolders;
    private DBTools dbTools = new DBTools(activity);
    private LayoutInflater inflater;
    private Filter AddressBookFilter;

    public AddressListAdapter(Activity activity, ArrayList<SingleAddressDetailHolder> singleAddressDetailHolders) {
        this.activity = activity;
        this.originalSingleAddressDetailHolders = singleAddressDetailHolders;
        this.newSingleAddressDetailHolders = singleAddressDetailHolders;
        inflater = LayoutInflater.from(this.activity);

    }

    @Override
    public long getItemId(int position) {
        return originalSingleAddressDetailHolders == null ? 0 : originalSingleAddressDetailHolders.get(position).hashCode();
    }

    @Override
    public int getItemCount() {
        return originalSingleAddressDetailHolders == null ? 0 : originalSingleAddressDetailHolders.size();
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
        holder.tvContactsId.setText(String.valueOf(originalSingleAddressDetailHolders.get(position).getCONTACT_ID()));
        holder.tvFirstName.setText(String.valueOf(originalSingleAddressDetailHolders.get(position).getFIRST_NAME()));
        holder.tvLastName.setText(String.valueOf(originalSingleAddressDetailHolders.get(position).getLAST_NAME()));
        if (holder.switchFlashOnOff != null) {
            holder.switchFlashOnOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                }
            });
        }


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
        if (AddressBookFilter == null)
            AddressBookFilter = new AddressBookFilterClass(newSingleAddressDetailHolders, this);
        return AddressBookFilter;
        }



    private class AddressBookFilterClass extends Filter {
        private AddressListAdapter addressListAdapter;
        private List<SingleAddressDetailHolder> filteredAddressList;

        public AddressBookFilterClass(List<SingleAddressDetailHolder> filteredAddressList, AddressListAdapter addressListAdapter) {
            this.addressListAdapter = addressListAdapter;
            this.filteredAddressList = filteredAddressList;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                constraint = constraint.toString().toUpperCase();
                ArrayList<SingleAddressDetailHolder> filteredSingleAddressDetailHolders = new ArrayList<>();

                for (int i = 0; i < filteredAddressList.size(); i++) {
                    //Loop through the list for Addresses
                    if (filteredAddressList.get(i).getFIRST_NAME().toUpperCase().contains(constraint)) {
                        filteredSingleAddressDetailHolders.add(filteredAddressList.get(i));
                    }else if (filteredAddressList.get(i).getLAST_NAME().toUpperCase().contains(constraint)){
                        filteredSingleAddressDetailHolders.add(filteredAddressList.get(i));
                    }

                }
                results.count = filteredSingleAddressDetailHolders.size();
                results.values = filteredSingleAddressDetailHolders;

            } else {
                results.count = filteredAddressList.size();
                results.values = filteredAddressList;
            }



            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            // Now we have to inform the adapter about the new list filtered

            addressListAdapter.originalSingleAddressDetailHolders = (List<SingleAddressDetailHolder>) results.values;
            addressListAdapter.notifyDataSetChanged();


        }
    }


    public class AddressListViewHolder extends RecyclerView.ViewHolder {
        ItemClickListner itemClickListner;
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
