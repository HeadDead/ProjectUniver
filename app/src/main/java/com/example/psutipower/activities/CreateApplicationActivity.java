package com.example.psutipower.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.psutipower.R;
import com.example.psutipower.api.OrganizationApi;
import com.example.psutipower.api.RegistrationApi;
import com.example.psutipower.requests.CreateApplicationRequest;
import com.example.psutipower.requests.RegistrationRequest;
import com.example.psutipower.requests.ServiceDopDto;
import com.example.psutipower.responses.CreateApplicationResponse;
import com.example.psutipower.responses.RegistrationResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateApplicationActivity extends AppCompatActivity {

    Intent organizationMenuIntent;

    EditText editNameOfService;
    EditText editNumberOfService;
    EditText editDate;
    EditText editPrice;
    EditText editLeadTime;
    EditText editExtraServices;
    EditText editAddInfo;

    SharedPreferences sharedPreferences;

    CreateApplicationRequest createApplicationRequest;
    ServiceDopDto serviceDopDto;

    static final String BASE_URL = "http://10.0.2.2:8080/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_application);
        sharedPreferences = getSharedPreferences("OrgData", MODE_PRIVATE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        organizationMenuIntent = new Intent(CreateApplicationActivity.this, OrganizationMenuActivity.class);

        editNameOfService = findViewById(R.id.name_of_service);
        editNumberOfService = findViewById(R.id.number_of_service);
        editDate = findViewById(R.id.date);
        editPrice = findViewById(R.id.price);
        editLeadTime = findViewById(R.id.lead_time);
        editExtraServices = findViewById(R.id.extra_services);
        editAddInfo = findViewById(R.id.add_info);
    }

    public Retrofit connectRetrofit() {

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void buttonCreateApplication(View view) {
        createApplicationFromOrganization();
    }

    private void createApplicationFromOrganization() {
        if (editNameOfService.getText().toString().isEmpty() || editNumberOfService.getText().toString().isEmpty() ||
                editDate.getText().toString().isEmpty() || editPrice.getText().toString().isEmpty() ||
                editLeadTime.getText().toString().isEmpty()
        ) {
            Toast.makeText(getApplicationContext(), "Заявка заполнена некорректно", Toast.LENGTH_SHORT).show();
        } else {
            serviceDopDto = new ServiceDopDto(editExtraServices.getText().toString(), editNumberOfService.getText().toString(),
                    editPrice.getText().toString(), editLeadTime.getText().toString(), "");

            createApplicationRequest = new CreateApplicationRequest(editNameOfService.getText().toString(),
                    editDate.getText().toString(), editAddInfo.getText().toString(), serviceDopDto);

            OrganizationApi organizationApi = connectRetrofit().create(OrganizationApi.class);
            Call<CreateApplicationResponse> call = organizationApi.createApplication("Bearer " + sharedPreferences.getString("AuthToken", ""), createApplicationRequest);

           call.enqueue(new Callback<CreateApplicationResponse>() {
               @Override
               public void onResponse(Call<CreateApplicationResponse> call, Response<CreateApplicationResponse> response) {
                   if (response.isSuccessful()) {
                       Log.d("ConnectSuccessful", response.toString());
                       Toast.makeText(getApplicationContext(), "Заявка успешно создана", Toast.LENGTH_SHORT).show();
                       startActivity(organizationMenuIntent);
                   } else {
                       Toast.makeText(getApplicationContext(), "Ваша организация не подтверждена", Toast.LENGTH_SHORT).show();
                   }
               }

               @Override
               public void onFailure(Call<CreateApplicationResponse> call, Throwable t) {
                    Log.d("ConnectFailed", t.toString());
               }
           });
        }
    }
}
