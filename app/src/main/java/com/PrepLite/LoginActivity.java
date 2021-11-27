package com.PrepLite;

import static com.PrepLite.prefs.SharedPrefsConstants.ID;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.PrepLite.prefs.SharedPrefs;
import com.PrepLite.models.ServerResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private TextView reg_link;
    private TextView login_button;
    private EditText email, password;
    private TextView login;
    private ImageView login_bypass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.login_button);
        email = findViewById(R.id.lemail);
        password = findViewById(R.id.lpassword);
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
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }

    private void userLogin() {

        String userEmail = email.getText().toString().trim();
        String userPassword = password.getText().toString().trim();

        if (userEmail.isEmpty()) {
            email.requestFocus();
            email.setError("Enter your email");
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            email.requestFocus();
            email.setError("Enter valid email");
            return;
        }

        if (userPassword.length() < 6) {
            password.requestFocus();
            password.setError("Enter your valid password");
            return;
        }

        HashMap<String, String> map = new HashMap<>();
        map.put("email", userEmail);
        map.put("password", userPassword);
        Call<ServerResponse> call = Client.getRetrofitInstance().create(ApiCalls.class).login(map);

        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {

                ServerResponse serverResponse = response.body();

                if (!serverResponse.isError()) {
                    SharedPrefs.setIntParams(LoginActivity.this, ID, serverResponse.getResult().getUser().getId());

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, serverResponse.getMessage(), Toast.LENGTH_SHORT).show();

                } else
                    Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();

            }


            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}