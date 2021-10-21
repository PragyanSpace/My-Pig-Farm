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
import java.time.Period;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class EditDocumentActivity extends AppCompatActivity {
    EditText mupdateTag,mupdateOrigin,mupdateBirthPlace;
    TextView mupdateDob;
    FloatingActionButton mupdatedatafab;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore firestore;
    Calendar calendar;
    DatePickerDialog.OnDateSetListener listener;

    LocalDate today,birthdate;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_document);

        mupdateTag=findViewById(R.id.updateTag);
        mupdateDob=findViewById(R.id.updateDob);
        mupdateOrigin=findViewById(R.id.updateOrigin);
        mupdateBirthPlace=findViewById(R.id.updateBirthPlace);
        mupdatedatafab=findViewById(R.id.updatedatafab);

        firebaseAuth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();

        Intent intent=getIntent();
        final String Tag=intent.getStringExtra("Tag");
        final String Origin=intent.getStringExtra("Origin");
        final String Place=intent.getStringExtra("Place");
        final String Dob=intent.getStringExtra("Dob");
        final String category=intent.getStringExtra("Category");
        final String id=intent.getStringExtra("id");

        mupdateBirthPlace.setText(Place);
        mupdateTag.setText(Tag);
        mupdateDob.setText(Dob);
        mupdateOrigin.setText(Origin);




        calendar=Calendar.getInstance();

        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);



        mupdateDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(EditDocumentActivity.this, android.R.style.Theme_Holo_Dialog_MinWidth,listener,year,month,day);
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
                mupdateDob.setText(date);
            }
        };

        today= LocalDate.now();
        birthdate=LocalDate.of(year,month+1,day);


        mupdatedatafab.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                final String Tag=mupdateTag.getText().toString().trim();
                final String Dob=mupdateDob.getText().toString().trim();
                final String Place=mupdateBirthPlace.getText().toString().trim();
                final String Origin=mupdateOrigin.getText().toString().trim();

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
                    record.put("Age","Set on display");

                    documentReference.update(record).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getApplicationContext(), "Record added!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(EditDocumentActivity.this, pigRecord.class));
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