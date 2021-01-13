package com.ugtechie.agriseller.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.ugtechie.agriseller.R;

import java.util.List;

import Models.OrderModel;
import Models.ProductModel;
import adapters.InventoryAdapter;
import adapters.OrderAdapter;
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
        textViewNoOrdersYet.setVisibility(View.INVISIBLE);

        getMyOrders();
    }

    private void getMyOrders() {
        //SHOW PROGRESSBAR BEFORE INVENTORY LOADS
        ordersProgressBar.setVisibility(View.VISIBLE);
        //SETTING UP RETROFIT
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://amis-1.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ProductService productService = retrofit.create(ProductService.class);
        Call<List<OrderModel>> call = productService.getMyOrders(FirebaseAuth.getInstance().getCurrentUser().getUid());


        call.enqueue(new Callback<List<OrderModel>>() {
            @Override
            public void onResponse(Call<List<OrderModel>> call, Response<List<OrderModel>> response) {
                if (!response.isSuccessful()) {
                    ordersProgressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(OrdersActivity.this, "Could not get inventory. Error code: " +response.code(), Toast.LENGTH_SHORT).show();
                } else {
                    List<OrderModel> orderList = response.body();
                    buildRecyclerView(orderList);
                }
            }

            @Override
            public void onFailure(Call<List<OrderModel>> call, Throwable t) {
                ordersProgressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(OrdersActivity.this, "Connection Error", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void buildRecyclerView(List<OrderModel> orderList) {
        //Setting up widgets
        RecyclerView ordersRecyclerview  = findViewById(R.id.orders_recyclerview);
        ordersRecyclerview.setHasFixedSize(true);
        //InventoryAdapter adapter = new InventoryAdapter(inventoryList);
        OrderAdapter adapter = new OrderAdapter(orderList);
        ordersRecyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        ordersRecyclerview.setAdapter(adapter);
        ordersProgressBar.setVisibility(View.INVISIBLE);
        adapter.setOnItemClickListener(new OrderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
               //e orderList.get(position);
                //Toast.makeText(OrdersActivity.this, orderList.get(position).getProductId(), Toast.LENGTH_SHORT).show();
                Intent intent  = new Intent(OrdersActivity.this, OrderDetailsActivity.class);
                intent.putExtra("order_item_id", orderList.get(position).getProductId());
                intent.putExtra("product_order_Name", orderList.get(position).getName());
                intent.putExtra("product_order_price", orderList.get(position).getPrice());
                intent.putExtra("product_order_quantity", orderList.get(position).getQuantity());
                intent.putExtra("product_order_image", orderList.get(position).getProductImage());
                intent.putExtra("product_order_buyer_id", orderList.get(position).getBuyerId());
                Toast.makeText(OrdersActivity.this, orderList.get(position).getProductImage(), Toast.LENGTH_SHORT).show();
                //startActivity(intent);

            }
        });

    }

}