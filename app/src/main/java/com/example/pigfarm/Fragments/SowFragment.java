package com.example.pigfarm.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pigfarm.R;
import com.example.pigfarm.createData;
import com.example.pigfarm.firebasemodel;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;



public class SowFragment extends Fragment {


    public SowFragment() {
        // Required empty public constructor
    }

    //****************************
    FirebaseUser firebaseUser;
    FirebaseFirestore firebaseFirestore;
    RecyclerView mSowRec;


    // myadapter adapter;
    FirestoreRecyclerAdapter nadapter;
    //****************************


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sow, container, false);

        FloatingActionButton mplus= view.findViewById(R.id.sowplus);

        //************************************************************************

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();

        mSowRec=view.findViewById(R.id.sowRec);



        //query
        Query query = firebaseFirestore.collection("Records").document(firebaseUser.getUid()).collection("Sow");
        //recyleroption
        FirestoreRecyclerOptions<firebasemodel> alldata = new FirestoreRecyclerOptions.Builder<firebasemodel>()
                .setQuery(query, firebasemodel.class)
                .build();


        nadapter= new FirestoreRecyclerAdapter<firebasemodel, myviewholder2>(alldata) {
            @NonNull
            @Override
            public myviewholder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row,parent,false);
                return new myviewholder2(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull myviewholder2 holder, int position, @NonNull firebasemodel model) {

                holder.rTag.setText(model.getTag());
                holder.rDob.setText(model.getDob());
                holder.rOrigin.setText(model.getOrigin());
                holder.rPlace.setText(model.getPlace());
                holder.rAge.setText(model.getAge());

            }
        };


        mSowRec.setHasFixedSize(true);
        mSowRec.setLayoutManager(new LinearLayoutManager(getContext()));
        mSowRec.setAdapter(nadapter);

        RecyclerView.ItemDecoration divider=new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        mSowRec.addItemDecoration(divider);


        //************************************************************************




        mplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), createData.class);
                intent.putExtra("Category","Sow");
                startActivity(intent);
            }
        });

        return view;
    }

    private class myviewholder2 extends RecyclerView.ViewHolder {

        private TextView rTag,rDob,rOrigin,rPlace,rAge;

        public myviewholder2(@NonNull View itemView) {
            super(itemView);
            rTag=itemView.findViewById(R.id.rowTag);
            rDob=itemView.findViewById(R.id.rowDob);
            rOrigin=itemView.findViewById(R.id.rowOrigin);
            rPlace=itemView.findViewById(R.id.rowPlace);
            rAge=itemView.findViewById(R.id.rowAge);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        nadapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        nadapter.stopListening();
    }
}