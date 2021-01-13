package com.ugtechie.agriseller.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ugtechie.agriseller.R;

import Models.Seller;
import api.ProductService;
import api.SellerService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = "SignUpActivity";

    private FirebaseAuth mAuth;
    private Button buttonRegister;
    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextPhoneNumber;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private String userId;
    private TextView loginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //initializing Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        //Setting Up widgets
        buttonRegister = findViewById(R.id.button_register);
        editTextFirstName = findViewById(R.id.edit_text_register_first_name);
        editTextLastName = findViewById(R.id.edit_text_register_last_name);
        editTextEmail = findViewById(R.id.edit_text_register_email);
        editTextPhoneNumber = findViewById(R.id.edit_text_register_phone_number);
        editTextPassword = findViewById(R.id.edit_text_register_password);
        loginLink = findViewById(R.id.text_view_login);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Adds a new user to Firebase
                SignUpNewUser();
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, Login.class));
            }
        });
    }

    private void SignUpNewUser() {

        //Extracting strings from the editText
        final String firstName = editTextFirstName.getText().toString().trim();
        final String lastName = editTextLastName.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        final String phoneNumber = editTextPhoneNumber.getText().toString().trim();

        //Validating the user input
        if (firstName.isEmpty()) {
            editTextFirstName.setError("First name is required");
        } else if (lastName.isEmpty()) {
            editTextLastName.setError("Last name is required");
        } else if (phoneNumber.isEmpty()) {
            editTextPhoneNumber.setError("Valid Phone Number is required");
        } else if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
        } else if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
        } else if (password.length() < 8) {
            editTextPassword.setError("Password must be at least 8 characters");

        } else {
            //Initializing the progressDialog
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Registering user...");
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();


            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            //progressDialog.dismiss();
                            if (task.isSuccessful()) {
                                //Sign in success
                                Log.d(TAG, "onComplete: createUSerWith Email successful");
                                FirebaseUser user = task.getResult().getUser();
                                userId = mAuth.getCurrentUser().getUid();

                                //Cresting the seller object to be passed to save profile method
                                Seller newseller = new Seller(
                                        firstName,
                                        lastName,
                                        email,
                                        phoneNumber,
                                        "",
                                        "",//to be added after adding the location widget
                                        userId
                                );
                                //Save seller profile
                                saveSellerProfile(newseller);
                                progressDialog.dismiss();
                                //Go to home activity
                                //startHomeActivity();
                            } else {
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                progressDialog.dismiss();
                                Toast.makeText(SignUpActivity.this, "Creating Account failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }


    private void startHomeActivity() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent intent = new Intent(this, HomeActivity.class);
            //Intent intent = new Intent(this, UploadProfileImageActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    }

    //Save seller profile
    private void saveSellerProfile(Seller newSeller) {
        //0758531384
        //SETTING UP RETROFIT
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://amis-1.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SellerService sellerService = retrofit.create(SellerService.class);
        Call<Seller> call = sellerService.saveSellerProfile(newSeller);

        call.enqueue(new Callback<Seller>() {
            @Override
            public void onResponse(Call<Seller> call, Response<Seller> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(SignUpActivity.this, "Failed with code: " + response.code(), Toast.LENGTH_SHORT).show();
                } else {
                    Log.d(TAG, "onResponse: Profile saved successfully");

                    startHomeActivity();
                }
            }

            @Override
            public void onFailure(Call<Seller> call, Throwable t) {

                Toast.makeText(SignUpActivity.this, "Failed to save profile", Toast.LENGTH_SHORT).show();
            }
        });

    }


}