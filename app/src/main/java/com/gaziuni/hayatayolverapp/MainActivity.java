package com.gaziuni.hayatayolverapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText signinPhone, signinPassword;
    private Button loginbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signinPhone = findViewById(R.id.textInputEditTextTelefon);
        signinPassword = findViewById(R.id.textInputEditTextSifre);
        loginbtn = (Button) findViewById(R.id.buttonlogin);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkUser();
            }
        });

        TextView textView = findViewById(R.id.TextViewUyeOl);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Register_Activity.class);
                startActivity(intent);
            }
        });
    }
    public String phone,pass;
    public void checkUser(){
        phone = signinPhone.getText().toString();
        pass = signinPassword.getText().toString();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("USERS");
        Query checkUserDatabase = reference.orderByChild("phone").equalTo(phone);
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String phoneFromDB = snapshot.child(phone).child("phone").getValue(String.class);
                    String passwordFromDB = snapshot.child(phone).child("password").getValue(String.class);
                    if (passwordFromDB.equals(pass)&&phoneFromDB.equals(phone)) {
                        String nameFromDB = snapshot.child(phone).child("name").getValue(String.class);
                        String emailFromDB = snapshot.child(phone).child("email").getValue(String.class);
                        String typeFromDB = snapshot.child(phone).child("type").getValue(String.class);
                        if(typeFromDB.equals("Kullanıcı")){
                            Intent intent = new Intent(MainActivity.this, UserActivity.class);
                            intent.putExtra("name", nameFromDB);
                            intent.putExtra("email", emailFromDB);
                            intent.putExtra("type", typeFromDB);
                            intent.putExtra("password", passwordFromDB);
                            intent.putExtra("phone", phoneFromDB);
                            startActivity(intent);
                        } else if (typeFromDB.equals("Ambulans Şoförü")) {
                            Intent intent = new Intent(MainActivity.this, AmbulanceActivity.class);
                            intent.putExtra("name", nameFromDB);
                            intent.putExtra("email", emailFromDB);
                            intent.putExtra("type", typeFromDB);
                            intent.putExtra("password", passwordFromDB);
                            intent.putExtra("phone", phoneFromDB);
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Yanlış telefon numarası veya şifre girildi!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Böyle bir kullanıcı veya sürücü bulunmamakta!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

}