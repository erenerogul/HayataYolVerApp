package com.gaziuni.hayatayolverapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    TextView detailName, detailPhone,detailAddress,detailDistance;
    Double ulongitude,ulatitude;
    Button buttonGo;
    GoogleMap gMap;
    FrameLayout map;
    private HashMap<String,String> hashMap;
    private HashMap<String,Double> hashMap2;

    public DetailActivity(HashMap hashMap,HashMap hashMap2) {
        this.hashMap = hashMap;
        this.hashMap2 = hashMap2;
    }
    public DetailActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        detailName = findViewById(R.id.detailName);
        detailPhone = findViewById(R.id.detailPhone);
        detailAddress = findViewById(R.id.detailAddress);
        detailDistance = findViewById(R.id.detailDistance);
        map = findViewById(R.id.map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {
            detailName.setText(bundle.getString("NameSurname"));
            detailPhone.setText(bundle.getString("Phone"));
            detailAddress.setText(bundle.getString("Address"));
            ulongitude = bundle.getDouble("Ulongitude");
            ulatitude = bundle.getDouble("Ulatitude");
            detailDistance.setText(bundle.getString("Distance"));

        }
        buttonGo = findViewById(R.id.buttonGo);
        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userCallDataDelete();
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("google.navigation:q="+ulatitude+","+ulongitude+"&mode=d"));
                intent.setPackage("com.google.android.apps.maps");
                if(intent.resolveActivity(getPackageManager()) !=null)
                {
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.gMap = googleMap;
        LatLng latLng = new LatLng(ulatitude,ulongitude);
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title(detailName+"'s location.");
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,100));
        googleMap.addMarker(markerOptions);
    }


    private void userCallDataDelete(){
        String userPhone = detailPhone.getText().toString();
        FirebaseDatabase.getInstance().getReference("UserCall").child(userPhone).removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(DetailActivity.this,"Konuma doğru yola çıkılmıştır.",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(DetailActivity.this,"Yola çıkma işlemi başarısız!",Toast.LENGTH_SHORT).show();
                    }
                });
    }


}