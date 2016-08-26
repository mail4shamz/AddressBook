package com.mohammed.shameem.addressbook.view;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mohammed.shameem.addressbook.adapter.AddressListAdapter;
import com.mohammed.shameem.addressbook.controller.DBTools;
import com.mohammed.shameem.addressbook.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SearchView.OnQueryTextListener {
    private Toolbar toolbar;
    private Intent mainActivityIntent;
    private TextView tvContactsId;
    private DBTools dbToolsObject;
    private String searchQuery;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private AddressListAdapter addressListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        mainActivityIntent = getIntent();
        if (Intent.ACTION_SEARCH.equalsIgnoreCase(mainActivityIntent.getAction())) {
            searchQuery=mainActivityIntent.getStringExtra(SearchManager.QUERY);
            Toast.makeText(MainActivity.this, "The searched Name is "+searchQuery, Toast.LENGTH_LONG).show();
        }


    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        dbToolsObject = new DBTools(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        addressListAdapter = new AddressListAdapter(MainActivity.this);
        mRecyclerView.setAdapter(addressListAdapter);
    }

    /**
     * Called when a view has  clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                startActivity(new Intent(MainActivity.this, AddContact.class));
                break;
        }
//Just checking
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        /*final MenuItem item = menu.findItem(R.id.menu_search);*/
        SearchView searchView= (SearchView) menu.findItem(R.id.menu_search).getActionView();
        SearchManager searchManager= (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
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
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
