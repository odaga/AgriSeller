package com.ugtechie.agriseller.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.elyeproj.loaderviewlibrary.LoaderTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.ugtechie.agriseller.R;

import Models.Seller;
import api.SellerService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SettingActivity extends AppCompatActivity {
    private static final String TAG = "SettingActivity";

    private Toolbar mActionBarToolbar;
    private LinearLayout logoutButton;
    private ImageView profileImage;
    private LoaderTextView nameField, emailField, phoneField;

    private String UserId = FirebaseAuth.getInstance().getCurrentUser().getTenantId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //Setting up toolbar
        mActionBarToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("My Profile");
        mActionBarToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, HomeActivity.class));
            }
        });

        //Setting up widgets
        logoutButton = findViewById(R.id.log_out_button);
        profileImage = findViewById(R.id.circle_image_account_profile_image);
        nameField = findViewById(R.id.profile_name_field);
        emailField = findViewById(R.id.profile_email_field);
        phoneField = findViewById(R.id.profile_phone_field);
        profileImage.setImageResource(R.drawable.ic_launcher_background);

        //SETTING UP RETROFIT
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://amis-1.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SellerService sellerService = retrofit.create(SellerService.class);
       // Call<Seller> call = sellerService.getSellerProfile("5ff30f957412dc23442d1787");
        Call<Seller> call = sellerService.getSellerProfile(FirebaseAuth.getInstance().getCurrentUser().getUid());

        call.enqueue(new Callback<Seller>() {
            @Override
            public void onResponse(Call<Seller> call, Response<Seller> response) {
                if (!response.isSuccessful())
                    Toast.makeText(SettingActivity.this, "operation failed with code: " + response.code(), Toast.LENGTH_SHORT).show();
                else {
                    nameField.setText(response.body().getFirstName() + " " + response.body().getLastName());
                    emailField.setText(response.body().getEmail());
                    phoneField.setText(response.body().getPhoneNumber());
                }
            }

            @Override
            public void onFailure(Call<Seller> call, Throwable t) {
                Toast.makeText(SettingActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    FirebaseAuth.getInstance().signOut();
                    Intent intent = new Intent(SettingActivity.this, Login.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}