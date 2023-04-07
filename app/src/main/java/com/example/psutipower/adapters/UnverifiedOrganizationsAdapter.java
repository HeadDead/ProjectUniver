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
import com.example.psutipower.responses.UnverifiedOrganizationsResponse;

import java.util.ArrayList;

public class UnverifiedOrganizationsAdapter extends ArrayAdapter<UnverifiedOrganizationsResponse> {
    private Context context;
    private ArrayList<UnverifiedOrganizationsResponse> unverifiedOrganizationsResponse;
    TextView unverifiedOrganizationName;

    public UnverifiedOrganizationsAdapter(Context context, ArrayList<UnverifiedOrganizationsResponse> unverifiedOrganizationsResponse) {
        super(context, R.layout.unverified_organizations_item, unverifiedOrganizationsResponse);
        this.context = context;
        this.unverifiedOrganizationsResponse = unverifiedOrganizationsResponse;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.unverified_organizations_item, parent, false);

        unverifiedOrganizationName = (TextView) view.findViewById(R.id.unverified_organization_name);
        unverifiedOrganizationName.setText(this.unverifiedOrganizationsResponse.get(position).getShortName());

        return view;
    }
}
