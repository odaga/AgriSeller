package com.ugtechie.agriseller.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ugtechie.agriseller.R;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        CardView cardViewAddNewProduct = findViewById(R.id.card_add_product);
        CardView cardViewProductInventory = findViewById(R.id.Card_farmer_inventory);
        CardView cardViewOrderActivity = findViewById(R.id.card_farmer_view_orders);
        CardView cardViewWalletActivity = findViewById(R.id.card_farmer_wallet);

        cardViewAddNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, AddNewProductImage.class));
            }
        });

        cardViewProductInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, InventoryActivity.class));
            }
        });

        cardViewOrderActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), OrdersActivity.class));
            }
        });


        cardViewWalletActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), WalletActivity.class));
            }
        });

    }
}