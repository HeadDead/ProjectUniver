package com.example.psutipower.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.psutipower.R;
import com.example.psutipower.api.RegistrationApi;
import com.example.psutipower.requests.AddressDto;
import com.example.psutipower.requests.AddressTypeDto;
import com.example.psutipower.requests.OrganizationRequest;
import com.example.psutipower.responses.OrganizationResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrationOrganizationActivity extends AppCompatActivity {

    Intent organizationLoginIntent;

    EditText editName;
    EditText editLastName;
    EditText editPatronymic;
    EditText editPhoneNumber;
    EditText editEmail;
    EditText editUsername;
    EditText editPassword;
    EditText editOrganizationFullName;
    EditText editShortName;
    EditText editInn;
    EditText editKpp;
    EditText editOgrn;
    EditText editSubjectName;
    EditText editCityName;
    EditText editStreetName;
    EditText editHouseNumber;
    EditText editAddInfo;

    OrganizationRequest organizationRequest;

    static final String BASE_URL = "http://10.0.2.2:8080/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_organization);
    }

    @Override
    protected void onStart() {
        super.onStart();
        organizationLoginIntent = new Intent(RegistrationOrganizationActivity.this, LoginOrganizationActivity.class);

        editName = findViewById(R.id.name);
        editLastName = findViewById(R.id.lastName);
        editPatronymic = findViewById(R.id.patronymic);
        editPhoneNumber = findViewById(R.id.phoneNumber);
        editEmail = findViewById(R.id.email);
        editUsername = findViewById(R.id.username);
        editPassword = findViewById(R.id.password);
        editOrganizationFullName = findViewById(R.id.organizationFullName);
        editShortName = findViewById(R.id.organizationShortName);
        editInn = findViewById(R.id.inn);
        editKpp = findViewById(R.id.kpp);
        editOgrn = findViewById(R.id.ogrn);
        editSubjectName = findViewById(R.id.subjectName);
        editCityName = findViewById(R.id.cityName);
        editStreetName = findViewById(R.id.streetName);
        editHouseNumber = findViewById(R.id.houseNumber);
        editAddInfo = findViewById(R.id.addInfo);

    }

    public void apply(View view) {
        if (editName.getText().toString().isEmpty() || editLastName.getText().toString().isEmpty() ||
                editUsername.getText().toString().isEmpty() || editPassword.getText().toString().isEmpty() ||
                editPassword.getText().toString().isEmpty() || editPhoneNumber.getText().toString().isEmpty() ||
                editEmail.getText().toString().isEmpty() || editOrganizationFullName.getText().toString().isEmpty()
                || editShortName.getText().toString().isEmpty() || editInn.getText().toString().isEmpty()
                || editKpp.getText().toString().isEmpty() || editOgrn.getText().toString().isEmpty()
                || editSubjectName.getText().toString().isEmpty() || editCityName.getText().toString().isEmpty()
                || editStreetName.getText().toString().isEmpty() || editHouseNumber.getText().toString().isEmpty()
        ) {
            Toast.makeText(this, "Данные заполнены некорректно", Toast.LENGTH_SHORT).show();
        } else {
            organizationRequest = new OrganizationRequest(editName.getText().toString(), editLastName.getText().toString(),
                    editPatronymic.getText().toString(), editUsername.getText().toString(),
                    editPassword.getText().toString(), editPhoneNumber.getText().toString(), editEmail.getText().toString(),
                    "organization", editOrganizationFullName.getText().toString(), editShortName.getText().toString(),
                    editInn.getText().toString(), editKpp.getText().toString(), editOgrn.getText().toString(),
                    new AddressDto(editSubjectName.getText().toString(), editCityName.getText().toString(),
                            editStreetName.getText().toString(), editHouseNumber.getText().toString(),
                            editAddInfo.getText().toString(), new AddressTypeDto("", "")));

            RegistrationApi registrationApi = connectRetrofit().create(RegistrationApi.class);
            Call<OrganizationResponse> call = registrationApi.registrationOrganization(organizationRequest);

            call.enqueue(new Callback<OrganizationResponse>() {
                @Override
                public void onResponse(Call<OrganizationResponse> call, Response<OrganizationResponse> response) {
                    if (response.isSuccessful()) {
                        Log.d("ConnectSuccessful", response.toString());
                        Toast.makeText(getApplicationContext(), "Ожидайте подтверждения от администратора", Toast.LENGTH_SHORT).show();
                        startActivity(organizationLoginIntent);
                    } else {
                        if (editName.hasFocus()) {
                            editName.clearFocus();
                        } else {
                            editPassword.clearFocus();
                        }

                        Toast.makeText(RegistrationOrganizationActivity.this, "Ошибка отправки данных", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<OrganizationResponse> call, Throwable t) {
                    Log.d("ConnectNotSuccessful", t.toString());
                }
            });
        }
    }

    public Retrofit connectRetrofit() {

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
