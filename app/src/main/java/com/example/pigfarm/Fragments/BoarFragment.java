package com.example.pigfarm.Fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pigfarm.EditDocumentActivity;
import com.example.pigfarm.R;
import com.example.pigfarm.createData;
import com.example.pigfarm.firebasemodel;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;


public class BoarFragment extends Fragment {


    public BoarFragment() {
        // Required empty public constructor
    }

    FirebaseUser firebaseUser;
    FirebaseFirestore firebaseFirestore;
    RecyclerView mboarRec;





    FirestoreRecyclerAdapter nadapter;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().findViewById(R.id.Boar).setBackgroundColor(requireActivity().getColor(R.color.white));
        getActivity().findViewById(R.id.Sow).setBackgroundColor(requireActivity().getColor(R.color.sap));
        getActivity().findViewById(R.id.Gilt).setBackgroundColor(requireActivity().getColor(R.color.sap));
        getActivity().findViewById(R.id.Piglet).setBackgroundColor(requireActivity().getColor(R.color.sap));
        getActivity().findViewById(R.id.Fatterners).setBackgroundColor(requireActivity().getColor(R.color.sap));


        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_boar, container, false);

        FloatingActionButton mplus= view.findViewById(R.id.boarplus);


        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();

        mboarRec=view.findViewById(R.id.boarRec);




        //query
        Query query = firebaseFirestore.collection("Records").document(firebaseUser.getUid()).collection("boar").orderBy("Tag", Query.Direction.ASCENDING);
        //recyleroption
        FirestoreRecyclerOptions<firebasemodel> alldata = new FirestoreRecyclerOptions.Builder<firebasemodel>()
                .setQuery(query, firebasemodel.class)
                .build();


        nadapter= new FirestoreRecyclerAdapter<firebasemodel, myviewholder2>(alldata) {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            protected void onBindViewHolder(@NonNull myviewholder2 holder, int position, @NonNull firebasemodel model) {

                String date = model.getDob()+'-';
                String[] d_m_y=new String[3];
                int point=0,count=0;
                for (int i = 0; i < date.length(); i++) {
                    if(date.charAt(i)=='-'||date.charAt(i)=='/') {
                        d_m_y[count]=date.substring(point, i);
                        point=i+1;
                        count=count+1;
                    }
                }

                int yr = Integer.parseInt(d_m_y[2]);
                int mn = Integer.parseInt(d_m_y[1]);
                int da = Integer.parseInt(d_m_y[0]);



                LocalDate today=LocalDate.now();
                LocalDate birthdate=LocalDate.of(yr,mn,da);


                int month_age=Period.between(birthdate,today).getMonths();
                int year_age_inMonths=Period.between(birthdate,today).getYears()*12;

                String Age=String.valueOf(month_age+year_age_inMonths);

                holder.rTag.setText(model.getTag());
                holder.rDob.setText(model.getDob());
                holder.rOrigin.setText(model.getOrigin());
                holder.rPlace.setText(model.getPlace());
                holder.rAge.setText(Age);

                String id=model.getid();



                holder.linearLayoutForEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showPopupMenu(v);
                    }

                    private void showPopupMenu(View v) {

                        PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
                        popupMenu.setGravity(Gravity.END);
                        popupMenu.inflate(R.menu.menu_for_delete_edit);
                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()) {
                                    case R.id.edit:

                                        Intent intent=new Intent(getActivity(), EditDocumentActivity.class);
                                        intent.putExtra("Tag",model.getTag());
                                        intent.putExtra("Place",model.getPlace());
                                        intent.putExtra("Dob",model.getDob());
                                        intent.putExtra("Origin",model.getOrigin());
                                        intent.putExtra("id",id);
                                        intent.putExtra("Category","boar");
                                        startActivity(intent);

                                        return true;
                                    case R.id.delete:

                                        DocumentReference documentReference=firebaseFirestore.collection("Records").document(firebaseUser.getUid()).collection("boar").document(id);
                                        documentReference.delete();
                                        return true;
                                    default:
                                        return false;
                                }
                            }
                        });
                        popupMenu.show();

                    }
                });


            }
            @NonNull
            @Override
            public myviewholder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row,parent,false);
                return new myviewholder2(view);
            }
        };


        mboarRec.setHasFixedSize(true);
        mboarRec.setLayoutManager(new LinearLayoutManager(getContext()));
        mboarRec.setAdapter(nadapter);


        RecyclerView.ItemDecoration divider=new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        mboarRec.addItemDecoration(divider);


        mplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), createData.class);
                intent.putExtra("Category","boar");
                startActivity(intent);
            }
        });

        return view;
    }

    private class myviewholder2 extends RecyclerView.ViewHolder /*implements View.OnClickListener, PopupMenu.OnMenuItemClickListener */{

        private TextView rTag,rDob,rOrigin,rPlace,rAge;
        private LinearLayout linearLayoutForEdit;

        public myviewholder2(@NonNull View itemView) {
            super(itemView);
            rTag=itemView.findViewById(R.id.rowTag);
            rDob=itemView.findViewById(R.id.rowDob);
            rOrigin=itemView.findViewById(R.id.rowOrigin);
            rPlace=itemView.findViewById(R.id.rowPlace);
            rAge=itemView.findViewById(R.id.rowAge);
            linearLayoutForEdit=itemView.findViewById(R.id.linearLayoutForEdit);


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