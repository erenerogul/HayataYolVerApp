package com.gaziuni.hayatayolverapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.server.converter.StringToIntConverter;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class UserActivity extends AppCompatActivity {

    FusedLocationProviderClient fusedLocationProviderClient;

    EditText phoneEdittext,nameEditText;
    Button helpbutton;
    Geocoder geocoder;
    public static final int  REQUEST_CODR=100;

    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String phone =  intent.getStringExtra("phone");

        helpbutton = findViewById(R.id.buttonYardim);
        phoneEdittext = findViewById(R.id.phoneEditText);
        nameEditText = findViewById(R.id.nameEditText);
        phoneEdittext.setText(phone);
        nameEditText.setText(name);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        helpbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Help Button
                uploadData();//Konum eklemek için upload data kısmından konum bilgisi ve adres bilgisi girerek yapabilirsiniz.
                //currentLocation();
                //Projede emülatör kullandığımız için GPS sistemi bulunmamaktadır bu yüzden projeye ekleme yaparken manuel olarak ekleme yapmaktayız.
            }
        });
    }
    public void uploadData(){
        String phoneText = phoneEdittext.getText().toString();
        String nameText = nameEditText.getText().toString();
        String addressText = "Eskibağlar, Yılmaz Büyükerşen Blv No:21, 26170 Tepebaşı/Eskişehir";
        Double longitude = 30.510120825714427 ;
        Double latitude = 39.781799425471945;//Buradan Kullanıcı verilerini değiştirerek ekleme işlemlerini gerçekleştirebilirsiniz.

        UserCall userCall = new UserCall(phoneText,nameText,addressText,longitude,latitude);
        FirebaseDatabase.getInstance().getReference("UserCall").child(phoneText).setValue(userCall)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(UserActivity.this,"Konumunuz ambulanslarımıza iletilmiştir!",Toast.LENGTH_LONG).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UserActivity.this,"Konum alınamadı tekrar deneyeyiniz!",Toast.LENGTH_LONG).show();
                    }
                });
    }

     void currentLocation() {
        if(ContextCompat.checkSelfPermission(UserActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if(location != null)
                    {
                        geocoder = new Geocoder(UserActivity.this, Locale.getDefault());
                        List<Address> addressList = null;
                        try {
                            addressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            /*Toast.makeText(UserActivity.this, "Latitude:"+addressList.get(0).getLatitude()+
                                    "Longitude:"+addressList.get(0).getLongitude()+
                                    "City:"+addressList.get(0).getLongitude()+
                                    "Country:"+addressList.get(0).getCountryName()+
                                    "Address:"+addressList.get(0).getAddressLine(0), Toast.LENGTH_SHORT).show();*/

                            String phoneText = phoneEdittext.getText().toString();
                            String nameText = nameEditText.getText().toString();
                            String addressText=addressList.get(0).getAddressLine(0);
                            Double longitude= addressList.get(0).getLongitude();
                            Double latitude = addressList.get(0).getLatitude();

                            UserCall userCall = new UserCall(phoneText,nameText,addressText,longitude,latitude);
                            FirebaseDatabase.getInstance().getReference("UserCall").child(phoneText).setValue(userCall)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                Toast.makeText(UserActivity.this,"Konumunuz ambulanslarımıza iletilmiştir!",Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(UserActivity.this,"Konum alıanamadı tekrar deneyeyiniz!",Toast.LENGTH_LONG).show();
                                        }
                                    });
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            });
        }else {
            AskPerm();
        }
    }

    protected void AskPerm() {
        ActivityCompat.requestPermissions(UserActivity.this,new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION
        },REQUEST_CODR);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODR)
        {
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                currentLocation();
            }else {
                Toast.makeText(this, "İzin gerekli!", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}