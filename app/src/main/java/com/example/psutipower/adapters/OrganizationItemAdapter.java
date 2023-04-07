package com.example.psutipower.adapters;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.psutipower.R;
import com.example.psutipower.responses.AllApplicationsResponse;

import java.util.ArrayList;

public class OrganizationItemAdapter extends ArrayAdapter<AllApplicationsResponse> {

    private Context context;
    private ArrayList<AllApplicationsResponse> allApplicationsResponses;

    TextView idTextView;
    TextView firstNameTextView;
    TextView organizationNameTextView;
    TextView addressTextView;
    TextView priceTextView;

    public OrganizationItemAdapter(Context context, ArrayList<AllApplicationsResponse> allApplicationsResponses) {
        super(context, R.layout.organization_item, allApplicationsResponses);
        this.context = context;
        this.allApplicationsResponses = allApplicationsResponses;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.organization_item, parent, false);

        idTextView = (TextView) view.findViewById(R.id.application_id);
        firstNameTextView = (TextView) view.findViewById(R.id.application_name);
        organizationNameTextView = (TextView) view.findViewById(R.id.organization_name);
        addressTextView = (TextView) view.findViewById(R.id.address);
        priceTextView = (TextView) view.findViewById(R.id.price);

        idTextView.setText(String.valueOf(this.allApplicationsResponses.get(position).getId()));
        firstNameTextView.setText(this.allApplicationsResponses.get(position).getFirstName());
        organizationNameTextView.setText(this.allApplicationsResponses.get(position).getOrganizationName());
        addressTextView.setText(new StringBuilder().append(this.allApplicationsResponses.get(position).getAddress().getCityName())
                .append(", ул. ").append(this.allApplicationsResponses.get(position).getAddress().getStreetName()).append(" д.")
                .append(this.allApplicationsResponses.get(position).getAddress().getHouseNumber()).toString());
        priceTextView.setText(new StringBuilder().append(this.allApplicationsResponses.get(position).getPrice()).append("₽"));

        return view;
    }
}
