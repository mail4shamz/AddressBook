package com.mohammed.shameem.addressbook.view;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.mohammed.shameem.addressbook.R;
import com.mohammed.shameem.addressbook.adapter.AddressListAdapter;
import com.mohammed.shameem.addressbook.constants.Constants;
import com.mohammed.shameem.addressbook.controller.DBTools;
import com.mohammed.shameem.addressbook.holder.SingleAddressDetailHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private Toolbar toolbar;
    private Intent mainActivityIntent;
    private TextView tvContactsId;
    private DBTools dbToolsObject;
    private String searchQuery;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private AddressListAdapter addressListAdapter;
    private ArrayList<SingleAddressDetailHolder> singleAddressDetailHolders = new ArrayList<>();
    private ArrayList<HashMap<String, String>> AllContactsMapArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }



    @Override
    protected void onStart() {
        super.onStart();
        initViews();
        AllContactsMapArrayList = dbToolsObject.getAllContacts();
        for (int i = 0; i < AllContactsMapArrayList.size(); i++) {
            singleAddressDetailHolders.add(i, new SingleAddressDetailHolder(Integer.parseInt(
                    AllContactsMapArrayList.get(i).get(Constants.ContactDetails.CONTACT_ID)),
                    AllContactsMapArrayList.get(i).get(Constants.ContactDetails.VIEW_POSTION),
                    AllContactsMapArrayList.get(i).get(Constants.ContactDetails.FIRST_NAME),
                    AllContactsMapArrayList.get(i).get(Constants.ContactDetails.LAST_NAME),
                    AllContactsMapArrayList.get(i).get(Constants.ContactDetails.PHONE_NUMBER),
                    AllContactsMapArrayList.get(i).get(Constants.ContactDetails.EMAIL_ADDRESS),
                    AllContactsMapArrayList.get(i).get(Constants.ContactDetails.FLASH_SWITCH),
                    AllContactsMapArrayList.get(i).get(Constants.ContactDetails.PROFILE_IMAGE)));
        }
        Collections.sort(singleAddressDetailHolders, new Comparator<SingleAddressDetailHolder>() {
            @Override
            public int compare(SingleAddressDetailHolder lhs, SingleAddressDetailHolder rhs) {
                return lhs.getFIRST_NAME().equalsIgnoreCase(rhs.getFIRST_NAME())?
                        lhs.getLAST_NAME().compareTo(rhs.getLAST_NAME()):
                        lhs.getFIRST_NAME().compareToIgnoreCase(rhs.getFIRST_NAME());

            }


        });
        if(Looper.getMainLooper() == Looper.myLooper()) {
            // Current Thread is Main Thread.
            Toast.makeText(this, "This is running in main thread", Toast.LENGTH_LONG).show();
        }

    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.contacts_title);
        dbToolsObject = new DBTools(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        addressListAdapter = new AddressListAdapter(MainActivity.this, singleAddressDetailHolders);
        mRecyclerView.setAdapter(addressListAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.menu_addbutton) {
            startActivity(new Intent(MainActivity.this, AddContact.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        addressListAdapter.getFilter().filter(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        addressListAdapter.getFilter().filter(newText);
        return true;
    }

}
