package com.example.psutipower.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.psutipower.R;


public class OrganizationMenuActivity extends AppCompatActivity {
    Intent nextActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_menu);
    }

    public void goToCreateApplicationActivity(View view) {
        nextActivity = new Intent(OrganizationMenuActivity.this, CreateApplicationActivity.class);
        startActivity(nextActivity);
    }

    public void goToApplicationsFromUsersActivity(View view) {
        nextActivity = new Intent(OrganizationMenuActivity.this, ApplicationsFromUsersActivity.class);
        startActivity(nextActivity);
    }

}
