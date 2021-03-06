package com.example.pigfarm;

import androidx.annotation.NonNull;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class createData extends AppCompatActivity {
    EditText mcreateTag,mcreateOrigin,mcreateBirthPlace;
    TextView mcreateDob;
    FloatingActionButton mcreatedatafab;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore firestore;
    Calendar calendar;
    DatePickerDialog.OnDateSetListener listener;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_data);

        mcreateTag=findViewById(R.id.createTag);
        mcreateDob=findViewById(R.id.createDob);
        mcreateOrigin=findViewById(R.id.createOrigin);
        mcreateBirthPlace=findViewById(R.id.createBirthPlace);
        mcreatedatafab=findViewById(R.id.createdatafab);

        firebaseAuth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();

        Intent intent=getIntent();
        final String category=intent.getStringExtra("Category");



        calendar=Calendar.getInstance();

        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);



        mcreateDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(createData.this, android.R.style.Theme_Holo_Dialog_MinWidth,listener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });


        listener =new DatePickerDialog.OnDateSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                String date=dayOfMonth+"-"+month+"-"+year;
                LocalDate today=LocalDate.now();
                if(today.getYear()<year||today.getMonthValue()<month||today.getDayOfMonth()<dayOfMonth)
                    Toast.makeText(getApplicationContext(),"Enter vaild date.",Toast.LENGTH_SHORT).show();
                else
                mcreateDob.setText(date);
            }
        };




        mcreatedatafab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Tag=mcreateTag.getText().toString().trim();
                final String Dob=mcreateDob.getText().toString().trim();
                final String Place=mcreateBirthPlace.getText().toString().trim();
                final String Origin=mcreateOrigin.getText().toString().trim();
                final String id= UUID.randomUUID().toString();


                if (Tag.isEmpty()||Dob.isEmpty()||Place.isEmpty()||Origin.isEmpty())
                    Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                else {
                    DocumentReference documentReference = firestore.collection("Records").document(firebaseUser.getUid()).collection(category).document(id);
                    Map<String,Object> record = new HashMap<>();
                    record.put("id", id);
                    record.put("Tag", Tag);
                    record.put("Dob", Dob);
                    record.put("Origin", Origin);
                    record.put("Place", Place);
                //    record.put("Age","Set on display");

                    documentReference.set(record).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getApplicationContext(), "Record added!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(createData.this, pigRecord.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Failed to record data", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }


}