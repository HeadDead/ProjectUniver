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
import com.example.psutipower.responses.OneUnverifiedOrganizationResponse;
import com.example.psutipower.responses.UnverifiedOrganizationsResponse;

import java.util.ArrayList;

public class OneUnverifiedOrganizationAdapter extends ArrayAdapter<OneUnverifiedOrganizationResponse> {
    private Context context;
    private ArrayList<OneUnverifiedOrganizationResponse> oneUnverifiedOrganizationResponses;

    TextView firstNameTextView;
    TextView lastNameTextView;
    TextView patronymicTextView;
    TextView phoneNumberTextView;
    TextView usernameTextView;
    TextView emailTextView;
    TextView organizationFullNameTextView;
    TextView organizationShortNameTextView;
    TextView innTextView;
    TextView kppTextView;
    TextView ogrnTextView;
    TextView subjectNameTextView;
    TextView cityNameTextView;
    TextView streetNameTextView;
    TextView houseNumberTextView;
    TextView addInfoTextView;

    public OneUnverifiedOrganizationAdapter(Context context, ArrayList<OneUnverifiedOrganizationResponse> oneUnverifiedOrganizationResponse) {
        super(context, R.layout.one_unverified_organization_item, oneUnverifiedOrganizationResponse);
        this.context = context;
        this.oneUnverifiedOrganizationResponses = oneUnverifiedOrganizationResponse;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.one_unverified_organization_item, parent, false);

        firstNameTextView = (TextView) view.findViewById(R.id.first_name);
        lastNameTextView = (TextView) view.findViewById(R.id.last_name);
        patronymicTextView = (TextView) view.findViewById(R.id.patronymic);
        phoneNumberTextView = (TextView) view.findViewById(R.id.phone_number);
        usernameTextView = (TextView) view.findViewById(R.id.nickname);
        emailTextView = (TextView) view.findViewById(R.id.email);
        organizationFullNameTextView = (TextView) view.findViewById(R.id.organization_full_name);
        organizationShortNameTextView = (TextView) view.findViewById(R.id.organization_short_name);
        innTextView = (TextView) view.findViewById(R.id.inn);
        kppTextView = (TextView) view.findViewById(R.id.kpp);
        ogrnTextView = (TextView) view.findViewById(R.id.ogrn);
        subjectNameTextView = (TextView) view.findViewById(R.id.subject_name);
        cityNameTextView = (TextView) view.findViewById(R.id.city_name);
        streetNameTextView = (TextView) view.findViewById(R.id.street_name);
        houseNumberTextView = (TextView) view.findViewById(R.id.house_number);
        addInfoTextView = (TextView) view.findViewById(R.id.add_info);

        firstNameTextView.setText(this.oneUnverifiedOrganizationResponses.get(position).getFirstName());
        lastNameTextView.setText(this.oneUnverifiedOrganizationResponses.get(position).getLastName());
        patronymicTextView.setText(this.oneUnverifiedOrganizationResponses.get(position).getPatronymic());
        phoneNumberTextView.setText(this.oneUnverifiedOrganizationResponses.get(position).getPhoneNumber());
        usernameTextView.setText(this.oneUnverifiedOrganizationResponses.get(position).getUsername());
        emailTextView.setText(this.oneUnverifiedOrganizationResponses.get(position).getEmail());
        organizationFullNameTextView.setText(this.oneUnverifiedOrganizationResponses.get(position).getOrganizationFullName());
        organizationShortNameTextView.setText(this.oneUnverifiedOrganizationResponses.get(position).getOrganizationShortName());
        innTextView.setText(this.oneUnverifiedOrganizationResponses.get(position).getInn());
        kppTextView.setText(this.oneUnverifiedOrganizationResponses.get(position).getKpp());
        ogrnTextView.setText(this.oneUnverifiedOrganizationResponses.get(position).getOgrn());
        subjectNameTextView.setText(this.oneUnverifiedOrganizationResponses.get(position).getAddress().get(0).getSubjectName());
        cityNameTextView.setText(this.oneUnverifiedOrganizationResponses.get(position).getAddress().get(0).getCityName());
        streetNameTextView.setText(this.oneUnverifiedOrganizationResponses.get(position).getAddress().get(0).getStreetName());
        houseNumberTextView.setText(this.oneUnverifiedOrganizationResponses.get(position).getAddress().get(0).getHouseNumber());
        addInfoTextView.setText(this.oneUnverifiedOrganizationResponses.get(position).getAddress().get(0).getAddInfo());

        return view;
    }
}
