package com.example.inventarioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OptionActivity extends AppCompatActivity {
    Button agregando,venta,registrar,consulta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        agregando=findViewById(R.id.agregar);
        venta=findViewById(R.id.vender);
        registrar=findViewById(R.id.registro);
        consulta=findViewById(R.id.consulta);
        agregando.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent nuevo =new Intent(OptionActivity.this,AgregarActivity.class);
                    startActivity(nuevo);
            }
        });
        venta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nuevo =new Intent(OptionActivity.this,VentaActivity.class);
                startActivity(nuevo);
            }
        });
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nuevo =new Intent(OptionActivity.this,CarritoActivity.class);
                startActivity(nuevo);
            }
        });
        consulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nuevo =new Intent(OptionActivity.this,ConsultaActivity.class);
                startActivity(nuevo);
            }
        });
    }
}