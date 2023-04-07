package com.example.psutipower.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.psutipower.R;
import com.example.psutipower.responses.ApplicationsFromUsersResponse;

import java.util.ArrayList;

public class ApplicationsFromUsersAdapter extends ArrayAdapter<ApplicationsFromUsersResponse> {
    private Context context;
    private ArrayList<ApplicationsFromUsersResponse> applicationsFromUsersResponse;

    TextView nameOfServiceTextView;
    TextView usernameTextView;
    TextView phoneNumberTextView;

    public ApplicationsFromUsersAdapter(Context context, ArrayList<ApplicationsFromUsersResponse> applicationsFromUsersResponse) {
        super(context, R.layout.applications_from_users_item, applicationsFromUsersResponse);
        this.context = context;
        this.applicationsFromUsersResponse = applicationsFromUsersResponse;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.applications_from_users_item, parent, false);

        nameOfServiceTextView = (TextView) view.findViewById(R.id.name_of_the_service);
        usernameTextView = (TextView) view.findViewById(R.id.username);
        phoneNumberTextView = (TextView) view.findViewById(R.id.phone_number);

        nameOfServiceTextView.setText(this.applicationsFromUsersResponse.get(position).getFirstName());
        usernameTextView.setText(this.applicationsFromUsersResponse.get(position).getUsername());
        phoneNumberTextView.setText(this.applicationsFromUsersResponse.get(position).getPhoneNumber());

        return view;
    }
}
