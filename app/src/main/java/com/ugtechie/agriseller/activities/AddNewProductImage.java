package com.ugtechie.agriseller.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.ugtechie.agriseller.R;

public class AddNewProductImage extends AppCompatActivity {

    private static final String TAG = "AddNewProductImageActivity";
    private static final int PICK_IMAGE_REQUEST = 1;
    public static final String UPLOADED_PRODUCT_IMAGE_DOWNLOAD_URL = "url";

    private ImageView imageViewProductImagePreview;
    private Button buttonChooseProductImage, buttonNext;
    private ProgressBar uploadProgressBar;
    private Uri mImageUri;

    private StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();
    //Helps to stop upload task when there is a upload currently in progress
    private StorageTask mUploadTask;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product_image);


        //Initializing the toolbar
        Toolbar mActionBarToolbar = findViewById(R.id.toolbar);
        mActionBarToolbar.setTitle("Add New Product Image");


        //Setting up widgets
        imageViewProductImagePreview  = findViewById(R.id.image_view_image_upload);
        buttonChooseProductImage = findViewById(R.id.button_choose_product_image);
        buttonNext = findViewById(R.id.button_next);
        uploadProgressBar = findViewById(R.id.image_upload_progress_bar);

        buttonChooseProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check if there is an ongoing upload
                if (mUploadTask != null && mUploadTask.isInProgress()){
                    Toast.makeText(AddNewProductImage.this, "Upload In Progress, please wait", Toast.LENGTH_SHORT).show();
                }
                else {
                    uploadNewProductImage();
                }

            }
        });
    }

    private void uploadNewProductImage() {
        if (mImageUri != null) {
            StorageReference fileReference = mStorageRef.child("Product_images/"+System.currentTimeMillis()
                    + "." + getFileExtension(mImageUri));

            //Change button text and color
            buttonNext.setText("Uploading...");
           buttonNext.setBackgroundColor(Color.GRAY);

            mUploadTask = fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            buttonNext.setText("Done");
                            buttonNext.setBackgroundColor(Color.GREEN);

                            //Get the uploaded product download url
                            Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                            urlTask.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String uploadedProductImageUrl = uri.toString();
                                    Intent intent = new Intent(AddNewProductImage.this, AddProductDetailsActivity.class);
                                    intent.putExtra("Uploaded_product_Image_url",uploadedProductImageUrl);
                                    startActivity(intent);
                                    finish();
                                }
                            });

                            Toast.makeText(AddNewProductImage.this, "Success", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            uploadProgressBar.setProgress((int) progress);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddNewProductImage.this, "Error:" +e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
        else {
            Toast.makeText(this, "No Image was selected", Toast.LENGTH_SHORT).show();
        }
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*"); //Only show images
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();
            Picasso.get().load(mImageUri).into(imageViewProductImagePreview);
        }
    }

    //Method Handling file extension for the image selected for upload
    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(uri));
    }
}