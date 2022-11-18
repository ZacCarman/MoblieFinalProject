package com.example.mobliefinalproject;

import android.content.Context;
import android.content.Intent;

public class toolBar {
    public static void openItems(Context context)
    {
        Intent intent  = new Intent(context, itemActivity.class);
        context.startActivity(intent);
    }
}
