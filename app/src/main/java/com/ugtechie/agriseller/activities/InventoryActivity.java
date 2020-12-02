package com.ugtechie.agriseller.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.ugtechie.agriseller.R;

import java.util.List;

import Models.ProductModel;
import adapters.InventoryAdapter;
import adapters.ProductInventoryAdapter;
import api.ProductService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InventoryActivity extends AppCompatActivity {
    private static final String TAG = "InventoryActivity";

    private ProgressBar inventoryProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        //Setting up toolbar
        Toolbar mActionBarToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("My Inventory");
        inventoryProgressBar = findViewById(R.id.inventory_progressbar);

        getMyInventory();

    }

    private void getMyInventory() {
        //SHOW PROGRESSBAR BEFORE INVENTORY LOADS
        inventoryProgressBar.setVisibility(View.VISIBLE);
        //SETTING UP RETROFIT
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://lit-earth-63598.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ProductService productService = retrofit.create(ProductService.class);
        Call<List<ProductModel>> call = productService.getMyInventory(FirebaseAuth.getInstance().getCurrentUser().getUid());


        call.enqueue(new Callback<List<ProductModel>>() {
            @Override
            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(InventoryActivity.this, "Could not get inventory. Error code: " +response.code(), Toast.LENGTH_SHORT).show();
                } else {
                    List<ProductModel> inventoryList = response.body();
                    buildRecyclerView(inventoryList);
                }
            }
            @Override
            public void onFailure(Call<List<ProductModel>> call, Throwable t) {
                inventoryProgressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(InventoryActivity.this, "Request failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void buildRecyclerView(List<ProductModel> inventoryList) {
        //Setting up widgets
        RecyclerView inventoryRecyclerview = findViewById(R.id.inventory_recyclerview);
        inventoryRecyclerview.setHasFixedSize(true);
        InventoryAdapter adapter = new InventoryAdapter(inventoryList);
        inventoryRecyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        inventoryRecyclerview.setAdapter(adapter);
        inventoryProgressBar.setVisibility(View.INVISIBLE);
        

    }


}