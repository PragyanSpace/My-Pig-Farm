package com.example.pigfarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.pigfarm.Fragments.BoarFragment;
import com.example.pigfarm.Fragments.FattenersFragment;
import com.example.pigfarm.Fragments.GiltFragment;
import com.example.pigfarm.Fragments.PigletFragment;
import com.example.pigfarm.Fragments.SowFragment;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;


public class pigRecord extends AppCompatActivity {

    Button Sow;
    Button Boar;
    Button Piglet;
    Button Gilt;
    Button Fatteners;
    LinearLayout linearLayout;

    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pig_record);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Records");

        firebaseAuth=FirebaseAuth.getInstance();


        Sow=findViewById(R.id.Sow);
        Fatteners=findViewById(R.id.Fatterners);
        Gilt=findViewById(R.id.Gilt);
        Piglet=findViewById(R.id.Piglet);
        Boar=findViewById(R.id.Boar);
        linearLayout=findViewById(R.id.linearLayout);

        /*........................................*/
        SowFragment sowFragment=new SowFragment();
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.linearLayout,sowFragment);
        transaction.commit();



        Sow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SowFragment sowFragment=new SowFragment();
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.linearLayout,sowFragment);
                transaction.commit();
            }
        });
        Boar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BoarFragment sowFragment=new BoarFragment();
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.linearLayout,sowFragment);
                transaction.commit();
            }
        });
        Piglet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PigletFragment sowFragment=new PigletFragment();
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.linearLayout,sowFragment);
                transaction.commit();
            }
        });
        Gilt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GiltFragment sowFragment=new GiltFragment();
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.linearLayout,sowFragment);
                transaction.commit();
            }
        });
        Fatteners.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FattenersFragment sowFragment=new FattenersFragment();
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.linearLayout,sowFragment);
                transaction.commit();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.logout:
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(pigRecord.this,MainActivity.class));
        }


        return super.onOptionsItemSelected(item);
    }
}