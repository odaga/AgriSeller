package com.ugtechie.agriseller.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.elyeproj.loaderviewlibrary.LoaderTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ugtechie.agriseller.R;

import Models.Seller;
import api.SellerService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";

    private ImageView settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        CardView cardViewAddNewProduct = findViewById(R.id.card_add_product);
        CardView cardViewProductInventory = findViewById(R.id.Card_farmer_inventory);
        CardView cardViewOrderActivity = findViewById(R.id.card_farmer_view_orders);
        CardView cardViewWalletActivity = findViewById(R.id.card_farmer_wallet);
        LoaderTextView userName = findViewById(R.id.name_user);
        settings = findViewById(R.id.image_view_settings);

        //Get seller profile data
        //SETTING UP RETROFIT
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://amis-1.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SellerService sellerService = retrofit.create(SellerService.class);
        Call<Seller> call = sellerService.getSellerProfile("5ff30f957412dc23442d1787");

        call.enqueue(new Callback<Seller>() {
            @Override
            public void onResponse(Call<Seller> call, Response<Seller> response) {
                if (!response.isSuccessful())
                    Toast.makeText(HomeActivity.this, "operation failed with code: " + response.code(), Toast.LENGTH_SHORT).show();
                else {
                    userName.setText(response.body().getFirstName());
                    //nameField.setText(response.body().getFirstName() + " " + response.body().getLastName());
                    //emailField.setText(response.body().getEmail());
                    //phoneField.setText(response.body().getPhoneNumber());
                }
            }

            @Override
            public void onFailure(Call<Seller> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });

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

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SettingActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Check if user already signed in
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            finish();
            return;
        }
    }
}