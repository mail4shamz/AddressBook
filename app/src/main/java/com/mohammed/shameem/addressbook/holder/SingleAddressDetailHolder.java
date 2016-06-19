package com.mohammed.shameem.addressbook.holder;

/**
 * Created by shameem on 6/19/2016.
 */
public class SingleAddressDetailHolder {
    private int CONTACT_ID;
    private String FIRST_NAME;
    private String LAST_NAME;
    private String PHONE_NUMBER;
    private String EMAIL_ADDRESS;

    public SingleAddressDetailHolder(int CONTACT_ID, String FIRST_NAME, String LAST_NAME, String PHONE_NUMBER, String EMAIL_ADDRESS) {
        this.CONTACT_ID = CONTACT_ID;
        this.FIRST_NAME = FIRST_NAME;
        this.LAST_NAME = LAST_NAME;
        this.PHONE_NUMBER = PHONE_NUMBER;
        this.EMAIL_ADDRESS = EMAIL_ADDRESS;
    }

    public int getCONTACT_ID() {
        return CONTACT_ID;
    }

    public void setCONTACT_ID(int CONTACT_ID) {
        this.CONTACT_ID = CONTACT_ID;
    }

    public String getFIRST_NAME() {
        return FIRST_NAME;
    }

    public void setFIRST_NAME(String FIRST_NAME) {
        this.FIRST_NAME = FIRST_NAME;
    }

    public String getLAST_NAME() {
        return LAST_NAME;
    }

    public void setLAST_NAME(String LAST_NAME) {
        this.LAST_NAME = LAST_NAME;
    }

    public String getPHONE_NUMBER() {
        return PHONE_NUMBER;
    }

    public void setPHONE_NUMBER(String PHONE_NUMBER) {
        this.PHONE_NUMBER = PHONE_NUMBER;
    }

    public String getEMAIL_ADDRESS() {
        return EMAIL_ADDRESS;
    }

    public void setEMAIL_ADDRESS(String EMAIL_ADDRESS) {
        this.EMAIL_ADDRESS = EMAIL_ADDRESS;
    }


}
