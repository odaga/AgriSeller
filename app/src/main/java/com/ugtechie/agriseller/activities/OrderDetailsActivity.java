package com.ugtechie.agriseller.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.elyeproj.loaderviewlibrary.LoaderImageView;
import com.elyeproj.loaderviewlibrary.LoaderTextView;
import com.ugtechie.agriseller.R;

public class OrderDetailsActivity extends AppCompatActivity {
    private static final String TAG = "OrderDetailsActivity";

    private LoaderImageView image;
    private LoaderTextView name, price, quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        image = findViewById(R.id.order_image_view);
        name = findViewById(R.id.order_name);
        price = findViewById(R.id.detail_inventory_price);
        quantity = findViewById(R.id.order_detail_quantity);
    }
}