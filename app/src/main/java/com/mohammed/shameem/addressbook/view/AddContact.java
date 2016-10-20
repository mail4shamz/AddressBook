package com.mohammed.shameem.addressbook.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.mohammed.shameem.addressbook.R;
import com.mohammed.shameem.addressbook.constants.Constants.ContactDetails;
import com.mohammed.shameem.addressbook.controller.DBTools;
import com.mohammed.shameem.addressbook.utils_classes.UtilValidate;
import java.util.HashMap;
import java.util.Map;

public class AddContact extends AppCompatActivity implements View.OnClickListener {
    private Toast customToast;
    private LinearLayout profilePicLinearLayout;
    private ImageView profilePicImageView;
    private EditText etFirstName;
    private EditText etLastName;
    private EditText etPhoneNumber;
    private EditText etEmailAddress;
    private Button buttonAddContact;
    private DBTools dbTools;
    private Map<String, String> dataStringHashMap;
    private static String Flash = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initializeViews();


    }

    private void initializeViews() {
        customToast = new Toast(this);
        profilePicLinearLayout = (LinearLayout) findViewById(R.id.profilePicLinearLayout);
        profilePicImageView = (ImageView) findViewById(R.id.profilePicImageView);
        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);
        etEmailAddress = (EditText) findViewById(R.id.etEmailAddress);
        buttonAddContact = (Button) findViewById(R.id.buttonAddContact);
        dataStringHashMap=new HashMap<>();
        buttonAddContact.setOnClickListener(this);
        dbTools = new DBTools(AddContact.this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonAddContact) {
            if (validateInputData()) {
                dataStringHashMap.put(ContactDetails.FIRST_NAME, etFirstName.getText().toString().trim());
                dataStringHashMap.put(ContactDetails.LAST_NAME, etLastName.getText().toString().trim());
                dataStringHashMap.put(ContactDetails.PHONE_NUMBER, etPhoneNumber.getText().toString().trim());
                dataStringHashMap.put(ContactDetails.EMAIL_ADDRESS, etEmailAddress.getText().toString().trim());
                dataStringHashMap.put(ContactDetails.FLASH_SWITCH, Flash);
                dbTools.insertContact(dataStringHashMap);
                startActivity(new Intent(AddContact.this,MainActivity.class));
            }
        }
    }

    private boolean validateInputData() {
        if (etFirstName.getText().toString().isEmpty()) {
            customToast = Toast.makeText(AddContact.this, R.string.toast_enter_first_name, Toast.LENGTH_SHORT);
            customToast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP, 100, 460);
            customToast.show();
            return false;
        }
        if (etLastName.getText().toString().isEmpty()) {
            customToast = Toast.makeText(AddContact.this, "Enter last name", Toast.LENGTH_SHORT);
            customToast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP, 100, 560);
            customToast.show();
            return false;
        }
        if (etPhoneNumber.getText().toString().isEmpty()) {
            customToast = Toast.makeText(AddContact.this, "Enter phone number", Toast.LENGTH_SHORT);
            customToast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP, 100, 660);
            customToast.show();
            return false;
        }
        if (etEmailAddress.getText().toString().isEmpty()) {
            customToast = Toast.makeText(AddContact.this, "Enter email address", Toast.LENGTH_SHORT);
            customToast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP, 100, 760);
            customToast.show();
            return false;
        }
        else {

            if (!UtilValidate.isValidEmail(etEmailAddress.getText().toString())) {
                customToast = Toast.makeText(AddContact.this, "Enter valid email address", Toast.LENGTH_SHORT);
                customToast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP, 100, 760);
                customToast.show();
                return false;
            }
        }
        return true;
    }

}
