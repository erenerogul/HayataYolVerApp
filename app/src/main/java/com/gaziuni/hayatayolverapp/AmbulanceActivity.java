package com.gaziuni.hayatayolverapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class AmbulanceActivity extends AppCompatActivity {
    EditText namesurnameEditText,plateEditText;
    Button buttonMapLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance);

        Intent intent = getIntent();
        String driverName = intent.getStringExtra("name");
        String driverPhone = intent.getStringExtra("phone");

        namesurnameEditText = findViewById(R.id.namesurnameEditText);
        namesurnameEditText.setText(driverName);
        plateEditText = findViewById(R.id.plateEditText);
        buttonMapLogin = findViewById(R.id.buttonMapGiris);
        buttonMapLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (plateEditText.equals(null))
                {
                    Toast.makeText(AmbulanceActivity.this,"Lütfen plaka giriniz!",Toast.LENGTH_LONG).show();
                }
                else {
                    Intent anotherIntent = new Intent(AmbulanceActivity.this, LocationActivity.class);
                    HashMap<String,String> hashMap = new HashMap<>();
                    hashMap.put("driverName",driverName);
                    hashMap.put("driverPhone",driverPhone);
                    hashMap.put("driverPlate",plateEditText.toString());
                    startActivity(anotherIntent);
                }
            }
        });



    }
}