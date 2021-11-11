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

        String userName=name.getText().toString();
        String userEmail=email.getText().toString();
        String userPassword=password.getText().toString();
        String userYear=year.getText().toString();
        String userAlumni=alumni.getText().toString();



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



        Call<ResponseRegister> call= Client
                .getInstance()
                .getApi()
                .register(userName,userEmail,userPassword,userYear,userAlumni);

        call.enqueue(new Callback<ResponseRegister>() {
            @Override
            public void onResponse(@NonNull Call<ResponseRegister> call, @NonNull Response<ResponseRegister> response) {

                ResponseRegister registerResponse=response.body();
                if(response.isSuccessful()){


                    assert registerResponse != null;
                    Toast.makeText(RegisterActivity.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();

                }
                //assert registerResponse != null;
                Toast.makeText(RegisterActivity.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(@NonNull Call<ResponseRegister> call, @NonNull Throwable t) {

                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
