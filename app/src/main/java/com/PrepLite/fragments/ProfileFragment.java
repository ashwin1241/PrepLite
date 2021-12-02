package com.PrepLite.fragments;

import static android.app.Activity.RESULT_OK;

import static com.PrepLite.prefs.SharedPrefsConstants.ID;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.PrepLite.ApiCalls;
import com.PrepLite.Client;
import com.PrepLite.activities.HomeActivity;
import com.PrepLite.activities.LoginActivity;
import com.PrepLite.activities.MainActivity;
import com.PrepLite.activities.ProfileEditActivity;
import com.PrepLite.activities.ProfileSettingsActivity;
import com.PrepLite.R;
import com.PrepLite.prefs.SharedPrefs;
import com.PrepLite.models.ServerResponse;
import com.PrepLite.models.User;
import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {
    private ImageView settings;
    private View frag_view;
    private TextView logout;
    private TextView edit_profile;
    private ImageView camera;
    private String profileImagePath;
    private ImageView profileImage;
    private Uri profileImageUri;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        frag_view = inflater.inflate(R.layout.fragment_profile,container,false);
        settings = frag_view.findViewById(R.id.profile_settings);

        getProfile();

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), ProfileSettingsActivity.class);
                startActivity(intent);
            }
        });
        logout = frag_view.findViewById(R.id.logout_button_profile);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setTitle("Logout")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        logout_action();
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
        edit_profile = frag_view.findViewById(R.id.edit_profile);
        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), ProfileEditActivity.class);
                startActivity(intent);
            }
        });
        profileImage = frag_view.findViewById(R.id.profile_pic);
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectGalleryImage();
            }
        });
        camera = frag_view.findViewById(R.id.camera_profile);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                captureProfileImage();
            }
        });
        return frag_view;
    }

    private void getProfile() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("user_id", SharedPrefs.getIntParams(requireContext(), ID));
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
        SharedPrefs.setIntParams(requireContext(), ID, -1);
        Intent intent = new Intent(requireContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        getActivity().finish();
    }

    private void selectGalleryImage()
    {
        if(ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(requireActivity(),new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},101);
            selectGalleryImage();
        }
        else
        {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent,"Pick image"),101);
        }
    }

    private void captureProfileImage()
    {
        if(ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(requireActivity(),new String[]{Manifest.permission.CAMERA},100);
            captureProfileImage();
        }
        else
        {
            try
            {
                String filename = Long.toString(System.currentTimeMillis());
                File storageDirectory = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                File imgFile = File.createTempFile(filename,".jpg",storageDirectory);
                Uri imgUri = FileProvider.getUriForFile(requireContext(),"com.PrepLite.fileprovider",imgFile);
                this.profileImageUri = imgUri;
                profileImagePath = imgFile.getAbsolutePath();
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imgUri);
                startActivityForResult(intent,100);
            }
            catch (Exception e)
            {
                Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==100&&resultCode==RESULT_OK)
        {
            if(profileImagePath!=null)
            {
                try
                {
                    cameracorrection(profileImagePath,MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(),profileImageUri));
                    Glide.with(requireContext()).load(profileImageUri).placeholder(R.drawable.ic_baseline_hourglass_top_24).into(profileImage);
                    profileImage.setBackgroundColor(Color.parseColor("#EFFBF7"));

                    //fetch path for profile image from here t upload to db or use profilePic
                }
                catch (Exception e)
                {
                    Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(requireContext(), "Could not capture image properly!", Toast.LENGTH_SHORT).show();
            }
        }
        if(requestCode==101&&resultCode==RESULT_OK)
        {
            profileImageUri = data.getData();
            Bitmap profilePic = null;
            try
            {
                profilePic = correctedBitmap(profileImageUri);
                Glide.with(requireContext()).load(profileImageUri).placeholder(R.drawable.ic_baseline_hourglass_top_24).into(profileImage);
                profileImage.setBackgroundColor(Color.parseColor("#EFFBF7"));
            }
            catch (Exception e)
            {
                Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void cameracorrection(String path, Bitmap bitmap)
    {
        try
        {
            Matrix matrix = new Matrix();
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,ExifInterface.ORIENTATION_UNDEFINED);
            switch (orientation)
            {
                case ExifInterface.ORIENTATION_ROTATE_90: matrix.postRotate(90);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180: matrix.postRotate(180);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270: matrix.postRotate(270);
                    break;
            }
            Bitmap bitmap1 = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
            File file1 = new File(path);
            FileOutputStream outputStream = new FileOutputStream(file1);
            bitmap1.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
            outputStream.flush();
            outputStream.close();
        }
        catch (Exception e)
        {
            Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private Bitmap correctedBitmap(Uri uri)
    {
        try
        {
            Matrix matrix = new Matrix();
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), uri);
            int orientation;
            Cursor cursor = requireContext().getContentResolver().query(uri, new String[] { MediaStore.Images.ImageColumns.ORIENTATION }, null, null, null);
            if (cursor.getCount() != 1) {
                orientation = -1;
            }
            cursor.moveToFirst();
            orientation = cursor.getInt(0);
            switch (orientation)
            {
                case 90: matrix.postRotate(90);
                    break;
                case 180: matrix.postRotate(180);
                    break;
                case 270: matrix.postRotate(270);
                    break;
            }
            Bitmap bitmap1 = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
            return bitmap1;
        }
        catch (Exception e)
        {
            Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return  null;
    }

}
