package com.ugtechie.agriseller.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.ugtechie.agriseller.R;

import java.util.List;

import Models.ProductModel;
import adapters.InventoryAdapter;
import api.ProductService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrdersActivity extends AppCompatActivity {
    private static final String TAG = "OrdersActivity";

    private ProgressBar ordersProgressBar;
    private RecyclerView ordersRecyclerview;
    private TextView textViewNoOrdersYet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);


        //Setting up toolbar
        Toolbar mActionBarToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("My Orders");

        //setting up widgets
        ordersProgressBar = findViewById(R.id.orders_progressbar);
        ordersRecyclerview  = findViewById(R.id.orders_recyclerview);
        textViewNoOrdersYet = findViewById(R.id.no_orders_yet_text);



        getMyOrders();
    }

    private void getMyOrders() {
        //SHOW PROGRESSBAR BEFORE INVENTORY LOADS
        ordersProgressBar.setVisibility(View.VISIBLE);
        //SETTING UP RETROFIT
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://lit-earth-63598.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ProductService productService = retrofit.create(ProductService.class);
        Call<List<ProductModel>> call = productService.getMyOrders(FirebaseAuth.getInstance().getCurrentUser().getUid());


        call.enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
                if (!response.isSuccessful()) {
                    ordersProgressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(OrdersActivity.this, "Could not get inventory. Error code: " +response.code(), Toast.LENGTH_SHORT).show();
                } else {
                    List<ProductModel> inventoryList = response.body();
                    buildRecyclerView(inventoryList);
                }
            }
            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {
                ordersProgressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(OrdersActivity.this, "Request failed", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void buildRecyclerView(List<ProductModel> inventoryList) {
        //Setting up widgets
        RecyclerView ordersRecyclerview  = findViewById(R.id.orders_recyclerview);
        ordersRecyclerview.setHasFixedSize(true);
        InventoryAdapter adapter = new InventoryAdapter(inventoryList);
        ordersRecyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        ordersRecyclerview.setAdapter(adapter);
        ordersProgressBar.setVisibility(View.INVISIBLE);


    }

}