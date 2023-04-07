package com.example.psutipower.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.psutipower.R;

public class MainActivity extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clientButton(View view) {
        intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void organizationButton(View view) {
        intent = new Intent(this, LoginOrganizationActivity.class);
        startActivity(intent);
    }

    public void adminButton(View view) {
        intent = new Intent(this, LoginAdminActivity.class);
        startActivity(intent);
    }
}