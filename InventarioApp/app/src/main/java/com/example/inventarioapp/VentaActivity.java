package com.example.inventarioapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class VentaActivity extends AppCompatActivity implements Listener{
    RecyclerView recycler_view;
    private FirebaseFirestore db;
    ArrayList<servicios> arrayList;
    AdapterServicio adapterServicio;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_venta);

     /*   progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("obteniendo .. ");
        progressDialog.show();
*/
        recycler_view=(RecyclerView) findViewById(R.id.recycler_view);
        recycler_view.setHasFixedSize(true);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        db=FirebaseFirestore.getInstance();

        arrayList=new ArrayList<>();
        adapterServicio=new AdapterServicio(this,getdata(arrayList),this);
        recycler_view.setAdapter(adapterServicio);





    }

    private ArrayList<servicios> getdata(ArrayList<servicios> arrayList) {

        db.collection("servicios").orderBy("nombre").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable  QuerySnapshot value, @Nullable  FirebaseFirestoreException error) {
                if(error!=null){

                    System.out.println("error  :"+error.getMessage());
                    return;
                }
                for (DocumentChange dc:value.getDocumentChanges()){
                    if (dc.getType() == DocumentChange.Type.ADDED) {

                        arrayList.add(dc.getDocument().toObject(servicios.class));
                        System.out.println(dc.getDocument().toObject(servicios.class));
                    }
                    adapterServicio.notifyDataSetChanged();

                }
            }
        });
        return arrayList;
/*
        db.collection("servicios").orderBy("nombre").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> snapshotList=queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot snapshot:snapshotList){
                            servicios obj=snapshot.toObject(servicios.class);
                            arrayList.add(obj);
                        }
                        adapterServicio.notifyDataSetChanged();

                    }
                });

 */
    }


    @Override
    public void OnQuantityChange(ArrayList<servicios> arrayList) {
        Toast.makeText(this, arrayList.toString(), Toast.LENGTH_SHORT).show();
    }
}