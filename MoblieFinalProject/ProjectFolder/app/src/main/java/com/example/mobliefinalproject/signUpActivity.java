package com.example.mobliefinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signUpActivity extends AppCompatActivity {
    Button itemButton, cartButton, signInButton, signUpButton, addToCart;
    EditText username, password, retype;
    Button SignupButton;
    UPDatabase DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

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

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        retype=findViewById(R.id.repassword);
        SignupButton=findViewById(R.id.RegisterButton);
        DB=new UPDatabase(this);
        SignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                String repass=retype.getText().toString();

                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass))
                Toast.makeText(signUpActivity.this, "All fields required", Toast.LENGTH_LONG).show();
                else{
                    if(pass.equals(repass)) {
                        Boolean checkuser=DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert=DB.insertdata(user, pass);
                                    if(insert==true){
                                        Toast.makeText(signUpActivity.this, "Registered Sucessfully", Toast.LENGTH_LONG).show();
                                        Intent intent=new Intent(getApplicationContext(),itemActivity.class);
                                        startActivity(intent);
                                    } else{
                                        Toast.makeText(signUpActivity.this, "Registration failed", Toast.LENGTH_LONG).show();}
                        }else{
                            Toast.makeText(signUpActivity.this, "User already exists", Toast.LENGTH_LONG).show();

                        }
                    } else{
                        Toast.makeText(signUpActivity.this, "Passwords do not match", Toast.LENGTH_LONG).show();

                    }
                }

            }
        });

    }
}