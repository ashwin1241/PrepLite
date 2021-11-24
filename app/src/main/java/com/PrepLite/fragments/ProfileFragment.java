package com.PrepLite.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.PrepLite.LoginActivity;
import com.PrepLite.Profile_Settings;
import com.PrepLite.R;

public class ProfileFragment extends Fragment {
    private ImageView settings;
    private View frag_view;
    private ImageButton logout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        frag_view = inflater.inflate(R.layout.fragment_profile,container,false);
        settings = frag_view.findViewById(R.id.profile_settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(container.getContext(), Profile_Settings.class);
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

    private void logout_action()
    {
        //backend code to actually logout
    }
}
