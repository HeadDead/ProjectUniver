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
import com.example.psutipower.requests.RegistrationRequest;
import com.example.psutipower.responses.RegistrationResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrationActivity extends AppCompatActivity {
    Intent loginIntent;

    EditText editName;
    EditText editLastName;
    EditText editPatronymic;
    EditText editPhoneNumber;
    EditText editEmail;
    EditText editUsername;
    EditText editPassword;

    RegistrationRequest registrationRequest;

    static final String BASE_URL = "http://10.0.2.2:8080/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loginIntent = new Intent(RegistrationActivity.this, LoginActivity.class);

        editName = findViewById(R.id.name);
        editLastName = findViewById(R.id.lastName);
        editPatronymic = findViewById(R.id.patronymic);
        editPhoneNumber = findViewById(R.id.phoneNumber);
        editEmail = findViewById(R.id.email);
        editUsername = findViewById(R.id.username);
        editPassword = findViewById(R.id.password);
    }

    public Retrofit connectRetrofit() {

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void buttonRegistration(View view) {
        registration();
    }

    private void registration() {
        if (editName.getText().toString().isEmpty() || editLastName.getText().toString().isEmpty() ||
                editUsername.getText().toString().isEmpty() || editPassword.getText().toString().isEmpty() ||
                editPassword.getText().toString().isEmpty() || editPhoneNumber.getText().toString().isEmpty() ||
                editEmail.getText().toString().isEmpty()
        ) {
            Toast.makeText(this, "Данные заполнены некорректно", Toast.LENGTH_SHORT).show();
        } else {
            registrationRequest = new RegistrationRequest(editName.getText().toString(), editLastName.getText().toString(),
                    editPatronymic.getText().toString(), editUsername.getText().toString(),
                    editPassword.getText().toString(), editPhoneNumber.getText().toString(), editEmail.getText().toString(), "user");

            RegistrationApi registrationApi = connectRetrofit().create(RegistrationApi.class);
            Call<RegistrationResponse> call = registrationApi.registration(registrationRequest);

            call.enqueue(new Callback<RegistrationResponse>() {
                @Override
                public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                    if (response.isSuccessful()) {
                        Log.d("ConnectSuccessful", response.toString());
                        startActivity(loginIntent);
                    } else {
                        if (editName.hasFocus()) {
                            editName.clearFocus();
                        } else {
                            editPassword.clearFocus();
                        }

                        Toast.makeText(RegistrationActivity.this, "Ошибка отправки данных", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                    Log.d("ConnectNotSuccessful", t.toString());
                }
            });
        }
    }
}
