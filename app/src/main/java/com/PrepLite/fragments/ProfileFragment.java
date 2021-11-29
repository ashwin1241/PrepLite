package com.PrepLite.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.PrepLite.ApiCalls;
import com.PrepLite.Client;
import com.PrepLite.activities.LoginActivity;
import com.PrepLite.activities.MainActivity;
import com.PrepLite.activities.ProfileSettingsActivity;
import com.PrepLite.R;
import com.PrepLite.prefs.SharedPrefs;
import com.PrepLite.models.ServerResponse;
import com.PrepLite.models.User;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {
    private ImageView settings;
    private View frag_view;
    private TextView logout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        frag_view = inflater.inflate(R.layout.fragment_profile,container,false);
        settings = frag_view.findViewById(R.id.profile_settings);

        getProfile();

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(container.getContext(), ProfileSettingsActivity.class);
                startActivity(intent);
            }
        });
        logout = frag_view.findViewById(R.id.logout_button_profile);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(container.getContext());
                builder.setTitle("Logout")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        logout_action();
                        Intent intent = new Intent(container.getContext(), LoginActivity.class);
                        startActivity(intent);
                        Toast.makeText(container.getContext(), "Logged out successfully!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create().show();
            }
        });
        return frag_view;
    }

    private void getProfile() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("user_id", /*SharedPrefs.getIntParams(requireContext(), ID)*/ 1);
        Call<ServerResponse> call = Client.getRetrofitInstance().create(ApiCalls.class).profile(map);

        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse serverResponse = response.body();

                if (serverResponse != null) {
                    if (!serverResponse.isError()) {
                        User user = serverResponse.getResult().getUser();
                        //Update the UI here
                    }
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

            }
        });
    }

    private void logout_action()
    {
        SharedPrefs.clearPrefsEditor(requireContext());
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        getActivity().finish();
    }
}
