package com.example.mobliefinalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.text.DecimalFormat;

public class cartActivity extends AppCompatActivity {
    Button itemButton, cartButton, signInButton, signUpButton, checkoutButton;
    database db = new database(this,null,null,1);
    TextView cartText, totalText;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    double total = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Intent myIntent = getIntent();
        Bundle bundle = myIntent.getExtras();
        cartText = (TextView) findViewById(R.id.cartText);


        //***********NAV BAR************************
        itemButton = findViewById(R.id.itemButton);
        cartButton = findViewById(R.id.cartButton);
        signInButton = findViewById(R.id.signInButton);
        signUpButton = findViewById(R.id.signUpButton);
        checkoutButton = findViewById(R.id.checkoutButton);


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

        XmlResourceParser parser = getResources().getXml(R.xml.item_list);
        if(bundle != null) {
            try {
                try {
                    processData(parser, bundle);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
        }


        ScrollView scroll = new ScrollView(this);



        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), checkout.class);
                intent.putExtra("totalCharge", df.format(total));
                startActivity(intent);
            }
        });


    }

    private void processData(XmlResourceParser parser, Bundle bundle) throws XmlPullParserException, IOException {
        String temp = "";

        int q = 0;
        cartText = findViewById(R.id.cartText);
        totalText = findViewById(R.id.total);

        for (String key : bundle.keySet()) {

            q = (int) bundle.get(key);

            String price = db.checkPrice(key);
            String itemName = db.checkName(key);


            temp += itemName + " " + (bundle.get(key) != null ? bundle.get(key) : "NULL") +"\n";
            total += (q* Double.parseDouble(price));
        }

        cartText.setText(temp);
        totalText.setText("Total: $" + df.format(total));
    }


    } // EOF
