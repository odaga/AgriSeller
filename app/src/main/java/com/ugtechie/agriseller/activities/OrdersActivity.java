package com.ugtechie.agriseller.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ugtechie.agriseller.R;

public class OrdersActivity extends AppCompatActivity {
    private static final String TAG = "OrdersActivity";

    private RecyclerView ordersRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        //Initializing the toolbar
        Toolbar mActionBarToolbar = findViewById(R.id.toolbar);
        mActionBarToolbar.setTitle("My Orders");

        //Setting up widgets
        ordersRecyclerView = findViewById(R.id.orders_recyclerivew);
    }
}