package com.ugtechie.agriseller.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;


import com.ugtechie.agriseller.R;



public class InventoryActivity extends AppCompatActivity {
    private static final String TAG = "InventoryActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        //Initializing the toolbar
       // Toolbar mActionBarToolbar = findViewById(R.id.toolbar);
       // mActionBarToolbar.setTitle("My Inventory");

        //Initializing the toolbar
        Toolbar mActionBarToolbar = findViewById(R.id.toolbar);
        mActionBarToolbar.setTitle("My Inventory");

    }


}