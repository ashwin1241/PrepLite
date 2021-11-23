package com.PrepLite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.PrepLite.response.ResponseLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private TextView reg_link;
    private TextView login_button;
    private EditText email,password;
    private TextView login;
    private ImageView login_bypass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.login_button);
        email = findViewById(R.id.remail);
        password= findViewById(R.id.rpassword);
        reg_link = findViewById(R.id.register_link);
        reg_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               userLogin();
            }
        });
        login_bypass = findViewById(R.id.login_bypass);
        login_bypass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });

    }

    private void userLogin() {

        String userEmail=email.getText().toString().trim();
        String userPassword=password.getText().toString().trim();

        if(userEmail.isEmpty()){
            email.requestFocus();
            email.setError("Enter your email");
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
            email.requestFocus();
            email.setError("Enter valid email");
            return;
        }

        if(userPassword.length()<6){
            password.requestFocus();
            password.setError("Enter your valid password");
            return;
        }

        Call<ResponseLogin> call= Client.getRetrofitInstance().create(ApiCalls.class).login(userEmail,userPassword);

        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {

                ResponseLogin loginResponse=response.body();

                if(response.isSuccessful()){

                    if(loginResponse.getError().equals("200")){


                        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();


                    }

                }

            }



            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {

                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}