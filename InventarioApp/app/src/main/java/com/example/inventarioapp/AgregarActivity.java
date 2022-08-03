package com.example.inventarioapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AgregarActivity extends AppCompatActivity {
    Button añadir;
    EditText nombre1,descripcion1,precio1;
    String nom,des,pre;

    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        añadir=findViewById(R.id.añadir);
        nombre1=findViewById(R.id.nombre);
        descripcion1=findViewById(R.id.descripcion);
        precio1=findViewById(R.id.precio);
        db=FirebaseFirestore.getInstance();
        añadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nom=nombre1.getText().toString().trim();
                des=descripcion1.getText().toString().trim();
                pre=precio1.getText().toString().trim();
                if(!nom.isEmpty()&&!des.isEmpty()&&!pre.isEmpty()){
                    insertarDatos(nom,des,Double.parseDouble(pre));
                    nombre1.setText("");
                    descripcion1.setText("");
                    precio1.setText("");
                    Toast.makeText(AgregarActivity.this, "INGRESADOS CON EXITO !", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
    public void insertarDatos(String nombre,String descripcion,double precio){
        Map<String,Object> user=new HashMap<>();
        user.put("nombre",nombre);
        user.put("descripcion",descripcion);
        user.put("precio",precio);
        db.collection("servicios")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("servicios","Se ha añadido el servicio exitosamente");
                    }

                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("servicios","Error al intentar agregar el servicio");
            }
        });
    }
}