package com.example.mobliefinalproject;

import static java.lang.Math.round;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class checkout extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button itemButton, cartButton, signInButton, signUpButton, checkoutButton, payButton;
    Spinner delSpinner;

    double delivery = 0;
    final double TAX = 1.13;
    double total =0, subtotalVal =0;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private TextView subTotal, completeTotal, deliveryFeeText;
    private Button complete, cancel;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

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

        checkoutButton = findViewById(R.id.payButton);
        delSpinner = findViewById(R.id.deliverySpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.delivery, android.R.layout.simple_spinner_dropdown_item);
        delSpinner.setAdapter(adapter);
        delSpinner.setOnItemSelectedListener(this);

        payButton = findViewById(R.id.payButton);

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewDialog();
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch(parent.getItemAtPosition(position).toString()){
            case "Express (1-2 Business Days) + $10":
                delivery = 10.00;
                break;
            case "Normal (3-5 Business Days) + $5.50":
                delivery = 5.50;
                break;
            case "Same Day + $13.95":
                delivery = 13.95;
                break;
        }

        //parent.getItemAtPosition(position);
        //Toast.makeText(this, (parent.getItemAtPosition(position)).toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void createNewDialog(){
        dialogBuilder = new AlertDialog.Builder(this);
        final View confirmPopup = getLayoutInflater().inflate(R.layout.popup, null);

        subTotal = confirmPopup.findViewById(R.id.subTotal);
        completeTotal = confirmPopup.findViewById(R.id.completeTotal);
        cancel = confirmPopup.findViewById(R.id.cancel);
        complete = confirmPopup.findViewById(R.id.complete);
        deliveryFeeText = confirmPopup.findViewById(R.id.deliveryFee);

        dialogBuilder.setView(confirmPopup);
        dialog = dialogBuilder.create();
        dialog.show();

        String subTotalPass =getIntent().getStringExtra("totalCharge");
        subtotalVal = Double.parseDouble(subTotalPass);

        subTotal.setText("Subtotal: $" + df.format(subtotalVal));
        deliveryFeeText.setText("Delivery: $" + df.format(delivery));
        double finalFee = (subtotalVal + delivery) * TAX;
        completeTotal.setText("Total: $" + df.format(finalFee));

        Log.d("test", subTotalPass);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go back to cart
                dialog.dismiss();
            }
        });

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // complete form
                Toast.makeText(checkout.this, "Order Complete", Toast.LENGTH_LONG).show();
            }
        });
    }
}