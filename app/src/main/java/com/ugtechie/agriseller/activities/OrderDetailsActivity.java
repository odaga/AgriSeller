package com.ugtechie.agriseller.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.elyeproj.loaderviewlibrary.LoaderImageView;
import com.elyeproj.loaderviewlibrary.LoaderTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;
import com.ugtechie.agriseller.R;

import java.util.List;

import Models.ConfirmOrderModel;
import Models.OrderModel;
import api.ProductService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderDetailsActivity extends AppCompatActivity {
    private static final String TAG = "OrderDetailsActivity";

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        LoaderImageView image = findViewById(R.id.order_image_view);
        LoaderTextView name = findViewById(R.id.order_name);
        LoaderTextView price = findViewById(R.id.detail_inventory_price);
        LoaderTextView quantity = findViewById(R.id.order_detail_quantity);
        progressBar = findViewById(R.id.orders_details_progressbar);
        Button confirmOrderButtun = findViewById(R.id.button_confirm_order);
        progressBar.setVisibility(View.INVISIBLE);

        name.setText(getIntent().getStringExtra("product_order_Name"));
        price.setText(getIntent().getStringExtra("product_order_price"));
        quantity.setText(getIntent().getStringExtra("product_order_quantity"));
        Picasso.get().load(getIntent().getStringExtra("product_order_image")).into(image);

        confirmOrderButtun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                confirmOrder();
            }
        });

    }

    private void confirmOrder() {
        ConfirmOrderModel order = new ConfirmOrderModel(
                getIntent().getStringExtra("order_item_id"),
                getIntent().getStringExtra("product_order_buyer_id"),
                Integer.parseInt(getIntent().getStringExtra("product_order_quantity"))
        );
        //SETTING UP RETROFIT
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://amis-1.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ProductService productService = retrofit.create(ProductService.class);
        Call<Void> call = productService.updateStock(order);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(OrderDetailsActivity.this, "Failed to confirm order", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(OrderDetailsActivity.this, OrdersActivity.class));
                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(OrderDetailsActivity.this, "Order confirmed", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(OrderDetailsActivity.this, "Connection error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}