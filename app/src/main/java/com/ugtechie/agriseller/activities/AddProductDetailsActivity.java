package com.ugtechie.agriseller.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;
import com.ugtechie.agriseller.R;

import Models.ProductModel;

public class AddProductDetailsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "AddProductDetailsActivity";

    private EditText editTextProductName, editTextProductDescription, editTextProductPrice;
    private EditText editTextProductCategory;
    private Button buttonSubmitProductData;
    private ImageView imageView;
    private Spinner categorySpinner;
    private String productImageUrl;

    String spinnerCategorySelected;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_details);

       //FirebaseFirestore  db = FirebaseFirestore.getInstance();

        //Initializing the toolbar
        Toolbar mActionBarToolbar = findViewById(R.id.toolbar);
        mActionBarToolbar.setTitle("Product Details");

        imageView = findViewById(R.id.uploaded_image_preview);
        editTextProductName = findViewById(R.id.edit_text_add_product_name);
        editTextProductDescription = findViewById(R.id.edit_text_add_product_description);
        editTextProductPrice = findViewById(R.id.edit_text_add_product_price);
        buttonSubmitProductData = findViewById(R.id.button_submit_product_data);
        categorySpinner = (Spinner)findViewById(R.id.product_category_spinner);

        // Creating an ArrayAdapter using the String array and a default Spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.product_categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);
        categorySpinner.setOnItemSelectedListener(this);


        productImageUrl  = getIntent().getExtras().getString("Uploaded_product_Image_url");
        Picasso.get().load(productImageUrl).into(imageView);

        buttonSubmitProductData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String productName = editTextProductName.getText().toString();
                String productDescription = editTextProductDescription.getText().toString();
                String productPrice = editTextProductPrice.getText().toString();
               // String productCategory = editTextProductCategory.getText().toString();

                if (productName.isEmpty())
                    editTextProductName.setError("Product Name is required");
                else if (productDescription.isEmpty())
                    editTextProductDescription.setError("Product Description is required");
                else if (productPrice.isEmpty())
                    editTextProductPrice.setError("Product price is required");
                else {
                    //SubmitProduct(productName, productDescription, productPrice, spinnerCategorySelected);
                }
            }
        });
    }

    /*
    private void SubmitProduct(String productName, String productDescription, String productPrice, String productCategory) {
        //Initialise the progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Submitting product Information...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        //CollectionReference submittedProductRef = db.collection("Submitted Products");

        ProductModel newProduct = new ProductModel(
                productName,
                productDescription,
               // productCategory,
                spinnerCategorySelected,
                productImageUrl,
                productPrice,
                FirebaseAuth.getInstance().getCurrentUser().getUid(),
                //false,
                ""
        );

        submittedProductRef.add(newProduct)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        progressDialog.dismiss();
                        Toast.makeText(AddProductDetailsActivity.this, "product Added Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddProductDetailsActivity.this, HomeActivity.class));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(AddProductDetailsActivity.this, "Product Not added", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    */

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinnerCategorySelected = parent.getItemAtPosition(position).toString();
       // Toast.makeText(this, spinnerCategorySelected, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}