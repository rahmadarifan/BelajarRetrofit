package com.android.rahmadarifanhr.instadakwah;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.rahmadarifanhr.instadakwah.API.InstaDakwahService;
import com.android.rahmadarifanhr.instadakwah.Bean.Login;
import com.android.rahmadarifanhr.instadakwah.Bean.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    EditText password, userName, hasil;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        hasil = (EditText) findViewById(R.id.hasil);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    public void login() {
        String s1 = userName.getText().toString();
        String s2 = password.getText().toString();
        if (s1.equals("")) {
            userName.setError("Masukkan Username!");
        }
        if (s2.equals("")) {
            password.setError("Masukkan Password!");
        }
        Retrofit retro = new Retrofit.Builder()
                .baseUrl("http://192.168.42.202")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final InstaDakwahService instaDakwahService = retro.create(InstaDakwahService.class);

        Call<Login> requestLogin = instaDakwahService.login(s1, s2);
        requestLogin.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Response<Login> response) {
                Login log = response.body();
                if (log == null) {
                    hasil.setText("Gagal Request Login");
                } else {
                    String token = "Bearer " + log.getAccessToken();
                    Call<List<User>> user = instaDakwahService.getUser(token);
                    user.enqueue(new Callback<List<User>>() {
                        @Override
                        public void onResponse(Response<List<User>> response1) {
                            hasil.setText("Nama user : " + response1.body().get(0).getName() + "\nUrl photo : " + response1.body().get(0).getPhoto_url());
                        }

                        @Override
                        public void onFailure(Throwable t) {
                        }
                    });
                }
            }

            @Override
            public void onFailure(Throwable t) {
            }
        });


    }
}
