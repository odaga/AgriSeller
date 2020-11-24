package com.ugtechie.agriseller.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ugtechie.agriseller.R;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";

    private CardView cardViewAddNewProduct, cardViewProductInventory, cardViewWallet, cardViewOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cardViewAddNewProduct = findViewById(R.id.card_add_product);
        cardViewProductInventory = findViewById(R.id.Card_farmer_inventory);
        cardViewWallet = findViewById(R.id.card_farmer_wallet);
        cardViewOrders = findViewById(R.id.card_farmer_view_orders);



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

        cardViewWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), WalletActivity.class));
            }
        });

        cardViewOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), OrdersActivity.class));
            }
        });


    }
}