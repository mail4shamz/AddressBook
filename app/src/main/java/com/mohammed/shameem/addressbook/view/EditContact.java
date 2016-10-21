package com.mohammed.shameem.addressbook.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mohammed.shameem.addressbook.R;
import com.mohammed.shameem.addressbook.constants.Constants;
import com.mohammed.shameem.addressbook.controller.DBTools;

import java.util.HashMap;

public class EditContact extends AppCompatActivity implements View.OnClickListener {
    Intent theIntent;
    String contactId;
    private LinearLayout profilePicLinearLayout;
    private ImageView profilePicImageView;
    private EditText etFirstName, etLastName, etPhoneNumber, etEmail;
    private Button btEditContacts, btDeleteContact;
    private DBTools dbTools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViews();


    }

    private void initViews() {
        profilePicLinearLayout = (LinearLayout) findViewById(R.id.profilePicLinearLayout);
        profilePicImageView = (ImageView) findViewById(R.id.profilePicImageView);

        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);
        etEmail = (EditText) findViewById(R.id.etEmail);

        btEditContacts = (Button) findViewById(R.id.btEditContacts);
        btDeleteContact = (Button) findViewById(R.id.btDeleteContact);
        btEditContacts.setOnClickListener(this);
        btDeleteContact.setOnClickListener(this);
        dbTools = new DBTools(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent theIntent = getIntent();

        String contactId = theIntent.getStringExtra(Constants.KeysUsed.CONTACT_ID_KEY);

        HashMap<String, String> contactList = dbTools.getContactInformation(contactId);

        if (contactList.size() != 0) {
            etFirstName.setText(contactList.get(Constants.ContactDetails.FIRST_NAME));
            etLastName.setText(contactList.get(Constants.ContactDetails.LAST_NAME));
            etPhoneNumber.setText(contactList.get(Constants.ContactDetails.PHONE_NUMBER));
            etEmail.setText(contactList.get(Constants.ContactDetails.EMAIL_ADDRESS));

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btEditContacts:
                HashMap<String, String> contactList = new HashMap<>();
                Intent theIntent = getIntent();
                String contactId = theIntent.getStringExtra(Constants.KeysUsed.CONTACT_ID_KEY);
                contactList.put(Constants.KeysUsed.CONTACT_ID_KEY, contactId);
                contactList.put(Constants.ContactDetails.FIRST_NAME, etFirstName.getText().toString());
                contactList.put(Constants.ContactDetails.FIRST_NAME, etLastName.getText().toString());
                contactList.put(Constants.ContactDetails.LAST_NAME, etPhoneNumber.getText().toString());
                contactList.put(Constants.ContactDetails.EMAIL_ADDRESS, etEmail.getText().toString());
                dbTools.updateContact(contactList);
                theIntent = new Intent(EditContact.this, MainActivity.class);
                theIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                EditContact.this.startActivity(theIntent);


                break;
            case R.id.btDeleteContact:
                theIntent = getIntent();
                contactId = theIntent.getStringExtra(Constants.KeysUsed.CONTACT_ID_KEY);
                dbTools.deleteContact(contactId);
                theIntent = new Intent(EditContact.this, MainActivity.class);
                theIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                EditContact.this.startActivity(theIntent);
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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


}
