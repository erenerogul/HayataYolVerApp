package com.gaziuni.hayatayolverapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register_Activity extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;
    String[] item = {"Ambulans Şoförü","Kullanıcı"};
    private TextInputEditText signupEmail, signupPassword,signupName,signupPhone;
    private AutoCompleteTextView signupType;
    private Button signupbtn;

    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        signupEmail = findViewById(R.id.textInputEditTextPhonenumber);
        signupPassword = findViewById(R.id.textInputEditTextSifre);
        signupName = findViewById(R.id.textInputEditTextNameSurname);
        signupPhone = findViewById(R.id.textInputEditTextPhone);
        signupbtn =  findViewById(R.id.buttonkayitol);
        signupType = findViewById(R.id.dropdownlist);
        autoCompleteTextView =findViewById(R.id.dropdownlist);
        adapterItems = new ArrayAdapter<String>(this,R
                .layout.dropdown_item,item);
        autoCompleteTextView.setAdapter(adapterItems);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadData();
            }
        });

    }
    public void uploadData(){
        String email = signupEmail.getText().toString();
        String name = signupName.getText().toString();
        String pass = signupPassword.getText().toString();
        String phone = signupPhone.getText().toString();
        String type = signupType.getText().toString();

        Users helperClass = new Users(phone,pass,name,email,type);
        FirebaseDatabase.getInstance().getReference("USERS").child(phone).setValue(helperClass)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Register_Activity.this,"Kullanıcı kayıt işlemi başarılı!",Toast.LENGTH_LONG).show();
                            signupEmail.setText("");
                            signupName.setText("");
                            signupPassword.setText("");
                            signupPhone.setText("");
                            signupType.setText("Seçiniz...");
                            Intent intent = new Intent(Register_Activity.this,MainActivity.class);
                            startActivity(intent);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Register_Activity.this,"Kullanıcı kayıt işlemi başarısız!!!",Toast.LENGTH_LONG).show();
                    }
                });
    }
}