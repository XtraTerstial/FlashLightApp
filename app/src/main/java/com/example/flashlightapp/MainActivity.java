package com.example.flashlightapp;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flashlightapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding; // Declare binding

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        getSupportActionBar().hide();
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.button.getText().toString().equals(getString(R.string.turnon))) {
                    binding.button.setText(R.string.turnoff);
                    binding.Flashimage.setImageResource(R.drawable.flashlight);
                    changeLightState(true);
                } else {
                    binding.button.setText(R.string.turnon);
                    binding.Flashimage.setImageResource(R.drawable.turnedoff);
                    changeLightState(false);
                }
            }
        });
    }

    private void changeLightState(boolean state) {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            CameraManager cameraManager= (CameraManager) getSystemService(CAMERA_SERVICE);
            String camID=null;

            try {
                camID=cameraManager.getCameraIdList()[0];
                cameraManager.setTorchMode(camID,state);
            } catch (CameraAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.button.setText(R.string.turnon);
    }
}
