package com.example.psutipower.activities;

import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.psutipower.R;
import com.example.psutipower.api.RegistrationApi;
import com.example.psutipower.requests.LoginRequest;
import com.example.psutipower.responses.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    Intent registrationIntent;
    Intent serverIntent;
    EditText editName;
    EditText editPassword;

    SharedPreferences sharedPreferences;
    LoginRequest loginRequest;

    static final String BASE_URL = "http://10.0.2.2:8080/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        editName = findViewById(R.id.name);
        editPassword = findViewById(R.id.password);
        serverIntent = new Intent(this, ChooseOrganizationActivity.class);
        registrationIntent = new Intent(this, RegistrationActivity.class);
    }

    public Retrofit connectRetrofit() {

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void buttonStart(View view) {
        login();
    }

    public void buttonRegistration(View view) {
        startActivity(registrationIntent);
    }

    private void login() {
        if (editName.getText().toString().isEmpty() || editPassword.getText().toString().isEmpty()) {
            Toast.makeText(LoginActivity.this, "Логин или пароль указан неверно", Toast.LENGTH_SHORT).show();
        } else {
            loginRequest = new LoginRequest(editName.getText().toString(), editPassword.getText().toString());

            RegistrationApi registrationApi = connectRetrofit().create(RegistrationApi.class);
            Call<LoginResponse> call = registrationApi.login(loginRequest);

            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.isSuccessful()) {
                        Log.d("ConnectSuccessful", response.toString());
                        sharedPreferences.edit().putString("AuthToken", response.body().getToken()).apply();
                        startActivity(serverIntent);
                    } else {
                        if (editName.hasFocus()) {
                            editName.clearFocus();
                        } else {
                            editPassword.clearFocus();
                        }

                        Toast.makeText(LoginActivity.this, "Данные недействительны", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Log.d("ConnectNotSuccessful", t.toString());
                }
            });
        }
    }
}
