package com.example.administrator.heroreview.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.heroreview.R;
import com.example.administrator.heroreview.Util;
import com.example.administrator.heroreview.model.Login;
import com.example.administrator.heroreview.service.LoginService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.txt_username)
    EditText userName;
    @BindView(R.id.txt_password)
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
    @OnClick(R.id.btn_login)
    void onLoginButtonClick(){
        String usernameString = userName.getText().toString();
        String passwordString = password.getText().toString();

        if(Util.validateValueAccount(usernameString, passwordString, this)){

        }else {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://tigercoding.000webhostapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            LoginService loginService = retrofit.create(LoginService.class);
            Call<Login> call = loginService.getLoginData(usernameString, passwordString);

            call.enqueue(new Callback<Login>() {
                @Override
                public void onResponse(Call<Login> call, Response<Login> response) {
                    Login login = response.body();

                    if(login.getStatusCode() == 1000){
                        Intent intent = new Intent(getBaseContext(), HeroListActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(getBaseContext(), login.getStatusDescription(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Login> call, Throwable t) {
                    Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
