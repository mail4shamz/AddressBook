package com.mohammed.shameem.addressbook.view;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mohammed.shameem.addressbook.adapter.AddressListAdapter;
import com.mohammed.shameem.addressbook.constants.Constants;
import com.mohammed.shameem.addressbook.controller.DBTools;
import com.mohammed.shameem.addressbook.R;
import com.mohammed.shameem.addressbook.holder.SingleAddressDetailHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SearchView.OnQueryTextListener {
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
        initViews();
        mainActivityIntent = getIntent();
        if (Intent.ACTION_SEARCH.equalsIgnoreCase(mainActivityIntent.getAction())) {
            searchQuery = mainActivityIntent.getStringExtra(SearchManager.QUERY);
            Toast.makeText(MainActivity.this, "The searched Name is " + searchQuery, Toast.LENGTH_LONG).show();
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        AllContactsMapArrayList = dbToolsObject.getAllContacts();
        Log.e("MainActivity", "All contacts " + AllContactsMapArrayList);
        for (int i = 0; i < AllContactsMapArrayList.size(); i++) {
            singleAddressDetailHolders.add(i, new SingleAddressDetailHolder(Integer.parseInt(AllContactsMapArrayList.get(i).get(Constants.ContactDetails.CONTACT_ID)),
                            AllContactsMapArrayList.get(i).get(Constants.ContactDetails.FIRST_NAME),
                            AllContactsMapArrayList.get(i).get(Constants.ContactDetails.LAST_NAME),
                            AllContactsMapArrayList.get(i).get(Constants.ContactDetails.PHONE_NUMBER),
                            AllContactsMapArrayList.get(i).get(Constants.ContactDetails.EMAIL_ADDRESS),
                            AllContactsMapArrayList.get(i).get(Constants.ContactDetails.FLASH_SWITCH)
                    )
            );
        }
        Log.d("MainActivity", singleAddressDetailHolders.size() + " singleAddressDetailHolders");
        /*addressListAdapter = new AddressListAdapter(MainActivity.this, AllContactsMapArrayList,R.layout.contact_entries);
        mRecyclerView.setAdapter(addressListAdapter);*/
    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.contacts_title);
        dbToolsObject = new DBTools(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setOnClickListener(this);
        addressListAdapter = new AddressListAdapter(MainActivity.this, singleAddressDetailHolders, R.layout.contact_entries);
        mRecyclerView.setAdapter(addressListAdapter);
    }


    /**
     * Called when a view has  clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

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
        MainActivity.this.addressListAdapter.getFilter().filter(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        addressListAdapter.getFilter().filter(newText);
        Log.d("Main Activity", "Search Query" + newText);

        /*if (TextUtils.isEmpty(newText)) {
            addressListAdapter.getFilter().filter("");
            addressListAdapter.resetData();
        } else {
            addressListAdapter.getFilter().filter(newText);
        }*/

        /*MainActivity.this.addressListAdapter.getFilter().filter(newText);*/
        return true;
    }
}
