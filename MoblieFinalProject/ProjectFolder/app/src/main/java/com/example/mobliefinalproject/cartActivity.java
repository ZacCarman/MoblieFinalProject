package com.example.mobliefinalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class cartActivity extends AppCompatActivity {
    Button itemButton, cartButton, signInButton, signUpButton;
    RecyclerView recCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Intent myIntent = getIntent();
        Bundle bundle = myIntent.getExtras();
//        if (bundle != null) {
//            for (String key : bundle.keySet()) {
//                Log.e("AGRH", key + " : " + (bundle.get(key) != null ? bundle.get(key) : "NULL"));
//            }
//        }


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

        recCart = (RecyclerView) findViewById(R.id.recCart);
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


        //recCart.setAdapter(adapter);
        recCart.setLayoutManager(new LinearLayoutManager(this));

    }

    private void processData(XmlResourceParser parser, Bundle bundle) throws XmlPullParserException, IOException {

        while(parser.next() != XmlResourceParser.END_DOCUMENT){

            if(parser.getEventType() != XmlResourceParser.START_TAG) {
                continue;
            }
                String name = parser.getName();

                if (name.equals("id")) {
                    //if(parser.getAttributeValue(null, bundle.get(key)))
                    Log.e("AGRH", "HERE: " + parser.getAttributeValue(null, "id"));
                    for(String key : bundle.keySet()){
//                        if(key == parser.getAttributeValue(null, "id")){
//
//                        }
                    }


                }
            }

        for (String key : bundle.keySet()) {
            //Log.e("AGRH", key + " : " + (bundle.get(key) != null ? bundle.get(key) : "NULL"));
            Log.e("AGRH", key);
        }


        }
    }
