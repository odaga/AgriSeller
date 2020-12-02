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
    private FirebaseFirestore db;
    String InventoryDocumentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_detail);

        db = FirebaseFirestore.getInstance();
        CollectionReference InventoryRef = db.collection("Submitted Products");

        textViewName = findViewById(R.id.detail_inventory_name);
        textViewPrice = findViewById(R.id.detail_inventory_price);
        inventoryDetailImage = findViewById(R.id.image_view_inventory_detail);
        textViewApprovalStatus = findViewById(R.id.inventory_detail_approval_status);
        textViewDescription = findViewById(R.id.inventory_detail_description);

         InventoryDocumentId  = getIntent().getExtras().getString("Inventory_item_id");

        //Querying FireStore collection for single document with provided Id
        Task<DocumentSnapshot> query = InventoryRef.document(InventoryDocumentId).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        //Convert the farm document snapshot to a Farm Object for use in
                        // the activity
                        ProductModel inventory = documentSnapshot.toObject(ProductModel.class);

                        //Update UI with data from the document
                        Picasso.get().load(inventory.getProductImage()).into(inventoryDetailImage);
                        textViewName.setText(inventory.getName());
                        textViewPrice.setText("UGX"+inventory.getPrice());
                        textViewDescription.setText(inventory.getPrice());
                        /*
                        if (inventory.getProductApprovalStatus() == false) {
                            textViewApprovalStatus.setText("Not Approved");
                           // textViewApprovalStatus.setBackgroundColor(Color.RED);
                            textViewApprovalStatus.setTextColor(Color.parseColor("#c9300e"));
                        }
                        else {
                            textViewApprovalStatus.setText("Approved");
                            textViewApprovalStatus.setTextColor(Color.parseColor("#2ac210"));
                        }
                        */

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(InventoryDetailActivity.this, "Could not detail inventory details", Toast.LENGTH_SHORT).show();
                    }
                });
    }


}