package com.example.mobliefinalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.location.LocationRequestCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.sql.Array;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class itemActivity extends AppCompatActivity {
    Button itemButton, cartButton, signInButton, signUpButton, addToCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        setValues();

        itemButton = findViewById(R.id.itemButton);
        cartButton = findViewById(R.id.cartButton);
        signInButton = findViewById(R.id.signInButton);
        signUpButton = findViewById(R.id.signUpButton);
        addToCart = findViewById(R.id.addToCart);


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
//                Log.d("CHECK", cartList.get(0).toString());

                Intent intent  = new Intent(getApplicationContext(), cartActivity.class);
                for(int x = 0; x < pass_IDs.size(); x++)
                {
                    intent.putExtra(pass_IDs.get(x),  pass_values.get(x));
                }
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

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (NumberPicker id:
                        list_ID)
                {
                    Integer value = id.getValue();

                    if(value > 0)
                    {
//                        Toast.makeText(itemActivity.this, value, Toast.LENGTH_SHORT).show();
                        Log.d("VALUE",value.toString());
                        Integer pickerID = id.getId();
                        String passID = null;
                        for (String ID:
                             id_Name) {
                            if(ID.contains(pickerID.toString()))
                            {
                                passID = ID;
                            }
                        }
                        pass_IDs.add(passID);
                        pass_values.add(value);
                        Toast.makeText(itemActivity.this, "Items Added To Cart", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
    }
    List<NumberPicker> list_ID = new ArrayList<>();
    List<String> id_Name = new ArrayList<>();
    List<String> pass_IDs =new ArrayList<>();
    List<Integer> pass_values =new ArrayList<>();
    private void setValues() {
        LinearLayout linearLayout = new LinearLayout(this);
        try {
            InputStream is = getAssets().open("item_list.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);

            Element element=doc.getDocumentElement();
            element.normalize();

            NodeList nList = doc.getElementsByTagName("fruit");

            for (int i = 0; i < nList.getLength(); i++) {

                {
                    Node node = nList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element2 = (Element) node;
                        linearLayout = printValues(getValue("name", element2), getValue("id", element2), Integer.parseInt(getValue("quantity", element2)), Double.parseDouble(getValue("price", element2)), linearLayout);
                    }
                }
            }
            nList = doc.getElementsByTagName("vegetables");

            for (int i = 0; i < nList.getLength(); i++) {

                {
                    Node node = nList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element2 = (Element) node;
                        linearLayout = printValues(getValue("name", element2), getValue("id", element2), Integer.parseInt(getValue("quantity", element2)), Double.parseDouble(getValue("price", element2)), linearLayout);
                    }
                }
            }
            nList = doc.getElementsByTagName("bakery");

            for (int i = 0; i < nList.getLength(); i++) {

                {
                    Node node = nList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element2 = (Element) node;
                        linearLayout = printValues(getValue("name", element2), getValue("id", element2), Integer.parseInt(getValue("quantity", element2)), Double.parseDouble(getValue("price", element2)), linearLayout);
                    }
                }
            }
        } catch (Exception e) {
            Log.d("TEST",e.toString());
        }
        ScrollView scroll = new ScrollView(this);

        scroll.setLayoutParams(new ScrollView.LayoutParams(ScrollView.LayoutParams.FILL_PARENT,
                ScrollView.LayoutParams.FILL_PARENT));
        LinearLayout linearLayout1  = findViewById(R.id.itemLayout);
        scroll.addView(linearLayout);

        linearLayout1.addView(scroll);
    }
    private static String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }
    private  List<Pair<String,Integer>> cartList;

    private LinearLayout printValues(String name,String id, int quantity, double price, LinearLayout layout )
    {

        LinearLayout linearLayout = layout;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.requestLayout();
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        params.setMargins(0,0,0,0);
        ImageView itemImage = new ImageView(this);
        TextView itemName = new TextView(this);
        TextView itemID = new TextView(this);
        NumberPicker itemQuantity = new NumberPicker(this);
        TextView itemPrice = new TextView(this);




        String uri = "@drawable/"+name;
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);
        itemImage.setLayoutParams(params);
        itemImage.setImageDrawable(res);
        itemImage.setMaxWidth(10);
        itemImage.getLayoutParams().height = 200;
        itemImage.requestLayout();

        itemName.setLayoutParams(params);
        itemName.setText(name);
        itemName.setTextColor(Color.BLACK);
        itemName.setTextAlignment(ViewGroup.TEXT_ALIGNMENT_CENTER);
        itemImage.requestLayout();
        
        itemID.setLayoutParams(params);
        itemID.setText(id);
        itemID.setTextColor(Color.GRAY);
        itemID.setTextAlignment(ViewGroup.TEXT_ALIGNMENT_CENTER);
        itemID.setTextSize(10);
        itemImage.requestLayout();


        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        itemPrice.setLayoutParams(params);
        itemPrice.setTextColor(Color.BLACK);
        String tempString = "PRICE "+ formatter.format(price);
        SpannableString spanString = new SpannableString(tempString);
        spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, spanString.length(), 0);
        itemPrice.setText(spanString);
        itemPrice.setTextAlignment(ViewGroup.TEXT_ALIGNMENT_CENTER);
        itemImage.requestLayout();

        itemQuantity.setLayoutParams(params);
        itemQuantity.setMaxValue(quantity);
        itemQuantity.setMinValue(0);
        itemQuantity.setId(Integer.parseInt(id.substring(1)));
        id_Name.add(id);

        Button btn1 = new Button(this);
        btn1.setText("Add Item To Cart ");


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartList.add(new Pair<String, Integer>(id,itemQuantity.getValue()));
            }
        });
        list_ID.add(itemQuantity);
        linearLayout.addView(itemImage);
        linearLayout.addView(itemName);
        linearLayout.addView(itemPrice);
        linearLayout.addView(itemQuantity);
//        linearLayout.addView(btn1);
        return linearLayout;
    }
}