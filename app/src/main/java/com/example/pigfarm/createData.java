package com.example.pigfarm;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;
import java.util.Map;

public class createData extends AppCompatActivity {
    EditText mcreateTag,mcreateOrigin,mcreateBirthPlace,mcreateDob;
    FloatingActionButton mcreatedatafab;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore firestore;


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




        switch (category){
            case "Sow":
                mcreatedatafab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    final String Tag=mcreateTag.getText().toString().trim();
                    final String Dob=mcreateDob.getText().toString().trim();
                    final String Place=mcreateBirthPlace.getText().toString().trim();
                    final String Origin=mcreateOrigin.getText().toString().trim();
                        if (Tag.isEmpty()||Dob.isEmpty()||Place.isEmpty()||Origin.isEmpty())
                        Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                        else {
                            DocumentReference documentReference = firestore.collection("Records").document(firebaseUser.getUid()).collection("Sow").document();
                            Map<String,Object> record = new HashMap<>();
                            record.put("Tag", Tag);
                            record.put("Dob", Dob);
                            record.put("Origin", Origin);
                            record.put("Place", Place);
                            record.put("Age","test");

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
                break;
            case "Boar":
                mcreatedatafab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String Tag=mcreateTag.getText().toString().trim();
                        final String Dob=mcreateDob.getText().toString().trim();
                        final String Place=mcreateBirthPlace.getText().toString().trim();
                        final String Origin=mcreateOrigin.getText().toString().trim();

                        if (Tag.isEmpty() || Dob.isEmpty() || Origin.isEmpty() || Place.isEmpty())
                            Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                        else {
                            DocumentReference documentReference = firestore.collection("Records").document(firebaseUser.getUid()).collection("Boar").document();
                            Map<String, Object> record = new HashMap<>();
                            record.put("Tag", Tag);
                            record.put("Dob", Dob);
                            record.put("Origin", Origin);
                            record.put("Place", Place);
                            record.put("Age","test");

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
                break;
            case "Gilt":
                mcreatedatafab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String Tag=mcreateTag.getText().toString().trim();
                        final String Dob=mcreateDob.getText().toString().trim();
                        final String Place=mcreateBirthPlace.getText().toString().trim();
                        final String Origin=mcreateOrigin.getText().toString().trim();

                        if (Tag.isEmpty() || Dob.isEmpty() || Origin.isEmpty() || Place.isEmpty())
                            Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                        else {
                            DocumentReference documentReference = firestore.collection("Records").document(firebaseUser.getUid()).collection("Gilt").document();
                            Map<String, Object> record = new HashMap<>();
                            record.put("Tag", Tag);
                            record.put("Dob", Dob);
                            record.put("Origin", Origin);
                            record.put("Place", Place);
                            record.put("Age","test");

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
                break;
            case "Piglet":
                mcreatedatafab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String Tag=mcreateTag.getText().toString().trim();
                        final String Dob=mcreateDob.getText().toString().trim();
                        final String Place=mcreateBirthPlace.getText().toString().trim();
                        final String Origin=mcreateOrigin.getText().toString().trim();

                        if (Tag.isEmpty() || Dob.isEmpty() || Origin.isEmpty() || Place.isEmpty())
                            Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                        else {
                            DocumentReference documentReference = firestore.collection("Records").document(firebaseUser.getUid()).collection("Piglet").document();
                            Map<String, Object> record = new HashMap<>();
                            record.put("Tag", Tag);
                            record.put("Dob", Dob);
                            record.put("Origin", Origin);
                            record.put("Place", Place);
                            record.put("Age","test");

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
                break;
            case "Fatteners":
                mcreatedatafab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String Tag=mcreateTag.getText().toString().trim();
                        final String Dob=mcreateDob.getText().toString().trim();
                        final String Place=mcreateBirthPlace.getText().toString().trim();
                        final String Origin=mcreateOrigin.getText().toString().trim();

                        if (Tag.isEmpty() || Dob.isEmpty() || Origin.isEmpty() || Place.isEmpty())
                            Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                        else {
                            DocumentReference documentReference = firestore.collection("Records").document(firebaseUser.getUid()).collection("Fatteners").document();
                            Map<String, Object> record = new HashMap<>();
                            record.put("Tag", Tag);
                            record.put("Dob", Dob);
                            record.put("Origin", Origin);
                            record.put("Place", Place);
                            record.put("Age","test");

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
                break;

        }


    }
}