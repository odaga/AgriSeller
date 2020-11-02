package com.ugtechie.agriseller.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.ugtechie.agriseller.R;

import Models.ProductModel;
import adapters.ProductInventoryAdapter;

public class InventoryActivity extends AppCompatActivity {
    private static final String TAG = "InventoryActivity";

    private RecyclerView inventoryRecyclerview;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference SubmittedProductsRef = db.collection("Submitted Products");
    private ProductInventoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        setUpRecyclerView();
    }

    private void setUpRecyclerView() {

        Query query = SubmittedProductsRef.whereEqualTo("productOwnerId", FirebaseAuth.getInstance().getCurrentUser().getUid());

        FirestoreRecyclerOptions<ProductModel> options = new FirestoreRecyclerOptions.Builder<ProductModel>()
                .setQuery(query, ProductModel.class)
                .build();
        adapter = new ProductInventoryAdapter(options);

        inventoryRecyclerview = findViewById(R.id.inventory_recyclerview);
        inventoryRecyclerview.setHasFixedSize(true);
        inventoryRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        inventoryRecyclerview.setAdapter(adapter);

        adapter.setOnItemClickListener(new ProductInventoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {

                ProductModel product = documentSnapshot.toObject(ProductModel.class);

                Intent intent  = new Intent(getApplicationContext(), InventoryDetailActivity.class);
               intent.putExtra("Inventory_item_id", documentSnapshot.getId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.startListening();
    }
}