package com.mohammed.shameem.addressbook.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
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
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AddContact extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "AddContactClass";
    private TextInputLayout etFirstNameLayout, etLastNameLayout, etPhoneNumberLayout, etEmailAddressLayout;
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
    private static final int GALARY_REQUEST_CODE = 18;
    private static final int GALARY_REQUEST_KITKAT_CODE = 19;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initializeViews();
        CheckFocus();


    }

    private void CheckFocus() {
        etFirstName.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(getClass().getSimpleName(), "onTouch");
                if (MotionEvent.ACTION_UP == event.getAction()) {
                    etFirstNameLayout.setCounterEnabled(true);
                    etFirstNameLayout.setCounterMaxLength(13);
                    v.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if (!hasFocus) {
                                etFirstNameLayout.setCounterEnabled(false);
                            } else {
                                etFirstNameLayout.setCounterEnabled(true);

                            }

                        }
                    });
                }

                return false;
            }
        });


        etLastName.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEvent.ACTION_UP == event.getAction()) {
                    Log.d(getClass().getSimpleName(), "onTouch");
                    etLastNameLayout.setCounterEnabled(true);
                    etLastNameLayout.setCounterMaxLength(13);
                    v.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if (!hasFocus) {
                                etLastNameLayout.setCounterEnabled(false);
                            } else {
                                etLastNameLayout.setCounterEnabled(true);

                            }
                        }
                    });
                }

                return false;
            }
        });
        etPhoneNumber.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEvent.ACTION_UP == event.getAction()) {
                    Log.d(getClass().getSimpleName(), "onTouch");
                    etPhoneNumberLayout.setCounterEnabled(true);
                    etPhoneNumberLayout.setCounterMaxLength(13);
                    v.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if (!hasFocus) {
                                etPhoneNumberLayout.setCounterEnabled(false);
                            } else {
                                etPhoneNumberLayout.setCounterEnabled(true);

                            }
                        }
                    });
                }

                return false;
            }
        });

        etEmailAddress.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(getClass().getSimpleName(), "onTouch");
                if (MotionEvent.ACTION_UP == event.getAction()) {
                    etEmailAddressLayout.setCounterEnabled(true);
                    etEmailAddressLayout.setCounterMaxLength(13);
                    v.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if (!hasFocus) {
                                etEmailAddressLayout.setCounterEnabled(false);
                            } else {
                                etEmailAddressLayout.setCounterEnabled(true);

                            }
                        }
                    });
                }
                return false;
            }
        });
    }


    private void initializeViews() {
        customToast = new Toast(this);
        profilePicLinearLayout = (LinearLayout) findViewById(R.id.profilePicLinearLayout);
        profilePicImageView = (ImageView) findViewById(R.id.profilePicImageView);
        profilePicLinearLayout.setOnClickListener(this);

        etFirstNameLayout = (TextInputLayout) findViewById(R.id.etFirstNameLayout);
        etLastNameLayout = (TextInputLayout) findViewById(R.id.etLastNameLayout);
        etPhoneNumberLayout = (TextInputLayout) findViewById(R.id.etPhoneNumberLayout);
        etEmailAddressLayout = (TextInputLayout) findViewById(R.id.etEmailAddressLayout);

        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);
        etEmailAddress = (EditText) findViewById(R.id.etEmailAddress);
        buttonAddContact = (Button) findViewById(R.id.buttonAddContact);
        dataStringHashMap = new HashMap<>();
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
                startActivity(new Intent(AddContact.this, MainActivity.class));
            }
        }
        if (v.getId() == R.id.profilePicLinearLayout) {

            if (Build.VERSION.SDK_INT < 19) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/jpeg");
                startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.select_picture)), GALARY_REQUEST_CODE);
            } else {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/jpeg");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, getResources().getString(R.string.select_picture)), GALARY_REQUEST_KITKAT_CODE);
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (data.getData() != null) {
                Uri uri = data.getData();
                if (uri != null) {
                    if (requestCode == GALARY_REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
                        uri = data.getData();
                        Picasso.with(getApplicationContext()).load(uri).fit().into(profilePicImageView);
                    } else if (requestCode == GALARY_REQUEST_KITKAT_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
                        uri = data.getData();
                        Picasso.with(getApplicationContext()).load(uri).fit().into(profilePicImageView);
                    }
                } else {
                    Toast.makeText(this, "No Image to set", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "No Image to set", Toast.LENGTH_SHORT).show();

            }
        } catch (Exception e) {
            Log.e(TAG, "Error Message " + e.getMessage());
        }


    }

    private boolean validateInputData() {
        if (etFirstName.getText().toString().isEmpty()) {
            customToast = Toast.makeText(AddContact.this, "Enter first name", Toast.LENGTH_SHORT);
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
        } else {

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
