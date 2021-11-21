package com.PrepLite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.PrepLite.response.ResponseRegister;

import java.time.chrono.ChronoLocalDateTime;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private TextView lgn_link;
    EditText email,name,password,year,alumni;
    TextView register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.remail);
        name = findViewById(R.id.rname);
        password = findViewById(R.id.rpassword);
        year = findViewById(R.id.ryear);
        alumni = findViewById(R.id.ralumni);

        register = findViewById(R.id.register_button);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        lgn_link = findViewById(R.id.login_link);
        lgn_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);


            }
        });




    }
    private void registerUser() {

        String userName=name.getText().toString().trim();
        String userEmail=email.getText().toString().trim();
        String userPassword=password.getText().toString().trim();
        String userYear=year.getText().toString().trim();
        String userAlumni=alumni.getText().toString().trim();



        if(userEmail.isEmpty()){
            email.requestFocus();
            email.setError("Please enter your email");
            return;
        }
        if(userName.isEmpty()){
            name.requestFocus();
            name.setError("Enter your name");
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
            email.requestFocus();
            email.setError("Enter valid email");
            return;
        }
        if(userYear.isEmpty()){
            year.requestFocus();
            year.setError("Enter your year");
            return;
        }
        if(userAlumni.isEmpty()){
            alumni.requestFocus();
            alumni.setError("Are you an alumni ?");
            return;
        }
        if(userPassword.isEmpty()){
            password.requestFocus();
            password.setError("Enter your password");
            return;
        }
        if(userPassword.length()<6){
            password.requestFocus();
            password.setError("Enter atleast 6 characters");
            return;
        }

        HashMap<String, String> map = new HashMap<>();
        map.put("name", userName);
        map.put("email", userEmail);
        map.put("password", userPassword);
        map.put("yearOfStudy", userYear);
        map.put("alumni", userAlumni);


        Call<ResponseRegister> call= Client
                .getRetrofitInstance()
                .create(ApiCalls.class)
                .register(map);

        call.enqueue(new Callback<ResponseRegister>() {
            @Override
            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {

                ResponseRegister registerResponse=response.body();
                if(registerResponse != null){

                    Toast.makeText(RegisterActivity.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();

                }
                //assert registerResponse != null;
                Toast.makeText(RegisterActivity.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<ResponseRegister> call, Throwable t) {

                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
