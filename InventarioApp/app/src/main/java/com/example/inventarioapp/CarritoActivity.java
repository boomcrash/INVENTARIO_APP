package com.example.inventarioapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarritoActivity extends AppCompatActivity {
    ArrayAdapter ADP;
    EditText ape,nom,ced;
    Button venta;
    TextView pri;
    private FirebaseFirestore db;
    ArrayList<String> datos=ServicioActivity.productos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
        ListView lista=findViewById(R.id.list);
        venta=findViewById(R.id.vender);
        ape=findViewById(R.id.apellido);
        nom=findViewById(R.id.nombres);
        ced=findViewById(R.id.cedula);
        pri=findViewById(R.id.pri);

        db=FirebaseFirestore.getInstance();
        int precioa=0;
        String valora[],valorb;
        String tratamientos = "";
        for(int i=0;i<datos.size();i++){
            valora=datos.get(i).split("-");
            valorb=valora[1].substring(1);
            tratamientos+=datos.get(i)+"\n";
            precioa=precioa+(Integer.parseInt(valorb)*Integer.parseInt(valora[3]));
        }
        pri.setText(String.valueOf(precioa));

        ADP=new ArrayAdapter(CarritoActivity.this, android.R.layout.simple_list_item_1,datos);
        lista.setAdapter(ADP);
        venta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(datos.size()>0){
                    if(!ape.getText().toString().isEmpty()&&!nom.getText().toString().isEmpty()&&!ced.getText().toString().isEmpty()){
                        int precio=0;
                        String valor[],valor2;
                        String tratamientos = "";
                        for(int i=0;i<datos.size();i++){
                            valor=datos.get(i).split("-");
                            valor2=valor[1].substring(1);
                            tratamientos+=datos.get(i)+"\n";
                            precio=precio+(Integer.parseInt(valor2)*Integer.parseInt(valor[3]));
                        }

                        Map<String,Object> user=new HashMap<>();
                        user.put("apellidos",ape.getText().toString());
                        user.put("cedula",ced.getText().toString());
                        user.put("nombres",nom.getText().toString());
                        user.put("precioFinal",String.valueOf(precio));
                        user.put("tratamientos",tratamientos);
                        ape.setText("");
                        nom.setText("");
                        ced.setText("");
                        datos.clear();
                        db.collection("ventas")
                                .add(user)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Log.d("ventas","Se ha añadido la venta exitosamente");
                                    }

                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("ventas","Error al intentar agregar la venta");
                            }
                        });
                    }else {
                        Toast.makeText(CarritoActivity.this, "RELLENE LOS DATOS DEL USUARIO PARA PROCEDER !", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(CarritoActivity.this, "AGREGUE PRODUTOS PRIMERO !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}