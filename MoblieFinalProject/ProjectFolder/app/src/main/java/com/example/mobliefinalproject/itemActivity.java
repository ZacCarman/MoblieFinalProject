package com.example.mobliefinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class itemActivity extends AppCompatActivity {
    Button itemButton, cartButton, signInButton, signUpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        setvalues();

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
    }
    private void setvalues() {
        try {
            InputStream is = getAssets().open("itemlist.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);

            Element element=doc.getDocumentElement();
            element.normalize();

            NodeList nList = doc.getElementsByTagName("Fruit");

            for (int i = 0; i < nList.getLength(); i++) {

                {
                    Node node = nList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element2 = (Element) node;
                        printValues(getValue("name", element2), getValue("id", element2), Integer.parseInt(getValue("quantity", element2)), Double.parseDouble(getValue("price", element2)));
//                        if(input.equals(category) || input.equals("test") || input.equals("")) {
//                            tv1.setText(tv1.getText() + "\nTitle : " + getValue("title", element2) + "\n");
//                            tv1.setText(tv1.getText() + "Author : " + getValue("author", element2) + "\n");
//                            tv1.setText(tv1.getText() + "Year : " + getValue("year", element2) + "\n");
//                            tv1.setText(tv1.getText() + "Price : $" + getValue("price", element2) + "\n");
//                            tv1.setText(tv1.getText() + "-----------------------");
//                        }
                    }
                }
            }
        } catch (Exception e) {e.printStackTrace();}
    }
    private static String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }

    private void printValues(String name,String id, int quantity, double price )
    {
//        LinearLayout linearLayout = new LinearLayout(this);
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
//        params.weight = 1;
//        TextView cityText = new TextView(this);
//        TextView temperatureText = new TextView(this);
//        TextView weatherText = new TextView(this);
//        LinearLayout linearLayout1  = findViewById(R.id.itemList);
//
//        cityText.setLayoutParams(params);
//        cityText.setText(city);
//        cityText.setTextColor(Color.RED);
//        cityText.setTextAlignment(ViewGroup.TEXT_ALIGNMENT_CENTER);
//
//        temperatureText.setLayoutParams(params);
//        temperatureText.setText(temperature);
//        temperatureText.setTextColor(Color.RED);
//        temperatureText.setTextAlignment(ViewGroup.TEXT_ALIGNMENT_CENTER);
//
//        weatherText.setLayoutParams(params);
//        weatherText.setText(weather);
//        weatherText.setTextColor(Color.RED);
//        weatherText.setTextAlignment(ViewGroup.TEXT_ALIGNMENT_CENTER);
//
//        // linearLayout.setLayoutParams(params);
//        linearLayout.addView(cityText);
//        linearLayout.addView(temperatureText);
//        linearLayout.addView(weatherText);
//        linearLayout1.addView(linearLayout);
    }
}