package com.mohammed.shameem.addressbook.interfaces;


import android.view.View;

public interface ItemClickListner {
    void onClickListener(View view,int position);

    void setItemClickListner(ItemClickListner itemClickListner);
}
