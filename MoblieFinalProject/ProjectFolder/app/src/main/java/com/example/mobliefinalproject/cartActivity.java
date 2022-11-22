package com.example.mobliefinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class cartActivity extends AppCompatActivity {
    Button itemButton, cartButton, signInButton, signUpButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Intent myIntent = getIntent();
        Bundle bundle = myIntent.getExtras();
        if (bundle != null) {
            for (String key : bundle.keySet()) {
                Log.e("AGRH", key + " : " + (bundle.get(key) != null ? bundle.get(key) : "NULL"));
            }
        }


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

        String [] cart;

    }
}