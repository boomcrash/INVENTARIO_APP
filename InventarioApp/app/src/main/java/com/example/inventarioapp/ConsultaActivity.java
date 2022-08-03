package com.example.inventarioapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ConsultaActivity extends AppCompatActivity implements ListenerC{
    RecyclerView recycler_view;
    private FirebaseFirestore db;
    ArrayList<consultas> arrayList;
    AdapterConsulta adapterConsulta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_venta2);

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
        adapterConsulta=new AdapterConsulta(this,getdata(arrayList),this);
        recycler_view.setAdapter(adapterConsulta);





    }

    private ArrayList<consultas> getdata(ArrayList<consultas> arrayList) {

        db.collection("ventas").orderBy("apellidos").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable  QuerySnapshot value, @Nullable  FirebaseFirestoreException error) {
                if(error!=null){

                    System.out.println("error  :"+error.getMessage());
                    return;
                }
                for (DocumentChange dc:value.getDocumentChanges()){
                    if (dc.getType() == DocumentChange.Type.ADDED) {

                        arrayList.add(dc.getDocument().toObject(consultas.class));
                        System.out.println(dc.getDocument().toObject(consultas.class));
                    }
                    adapterConsulta.notifyDataSetChanged();

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
    public void OnQuantityChange(ArrayList<consultas> arrayList) {
        Toast.makeText(this, arrayList.toString(), Toast.LENGTH_SHORT).show();
    }
}