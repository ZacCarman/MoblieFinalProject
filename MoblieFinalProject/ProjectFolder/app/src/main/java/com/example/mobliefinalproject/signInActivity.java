package com.example.mobliefinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signInActivity extends AppCompatActivity {
    Button itemButton, cartButton, signInButton, signUpButton, addToCart;
    EditText username, password;
    Button SignInButton;
    UPDatabase DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //***********NAV BAR************************
        itemButton = findViewById(R.id.itemButton);
        cartButton = findViewById(R.id.cartButton);
        signInButton = findViewById(R.id.signInButton);
        signUpButton = findViewById(R.id.signUpButton);


        itemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getApplicationContext(), itemActivity.class);
                startActivity(intent);
            }
        });
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getApplicationContext(), cartActivity.class);
                startActivity(intent);
            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getApplicationContext(), signUpActivity.class);
                startActivity(intent);
            }
        });
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getApplicationContext(), signInActivity.class);
                startActivity(intent);
            }
        });

        //******************************END OF NAV BAR*****************************

        //******************************START OF SIGN IN CODE**********************
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        SignInButton=findViewById(R.id.RegisterButton);
        DB=new UPDatabase(this);

        SignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass))
                    Toast.makeText(signInActivity.this, "All fields required",Toast.LENGTH_LONG).show();
                else{
                    Boolean checkusernamepassword=DB.checkUsernamePassword(user, pass);
                    if(checkusernamepassword==true)
                    {
                        Toast.makeText(signInActivity.this,"Login Sucessful", Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(getApplicationContext(), itemActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(signInActivity.this, "Login failed", Toast.LENGTH_LONG).show();
                    }

                }

            }
        });

    }





}