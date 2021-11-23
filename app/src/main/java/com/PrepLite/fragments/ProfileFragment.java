package com.PrepLite.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.PrepLite.Profile_Settings;
import com.PrepLite.R;

public class ProfileFragment extends Fragment {
    private ImageView settings;
    private View frag_view;
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
        return frag_view;
    }
}
