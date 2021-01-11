package com.ugtechie.agriseller.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;
import com.ugtechie.agriseller.R;

import Models.ProductModel;

public class InventoryDetailActivity extends AppCompatActivity {
    private static final String TAG = "InventoryDetailActivity";

    private TextView textViewPrice, textViewName, textViewApprovalStatus, textViewDescription;
    private ImageView inventoryDetailImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_detail);

        textViewName = findViewById(R.id.detail_inventory_name);
        textViewPrice = findViewById(R.id.detail_inventory_price);
        inventoryDetailImage = findViewById(R.id.image_view_inventory_detail);
        textViewApprovalStatus = findViewById(R.id.inventory_detail_approval_status);
        //textViewDescription = findViewById(R.id.inventory_detail_description);


    }


}