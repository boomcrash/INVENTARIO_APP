package com.example.inventarioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button ingreso;
    EditText cedulaa,passa;
    final String CONSTANTE="0911497584";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ingreso=findViewById(R.id.ingresar);
        cedulaa=findViewById(R.id.cedula);
        passa=findViewById(R.id.pass);

        ingreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ced=cedulaa.getText().toString().trim();
                String pas=passa.getText().toString().trim();
                System.out.println(cedulaa.getText().toString().trim());
                System.out.println(passa.getText().toString().trim());
                if(ced.toString().trim().equalsIgnoreCase(CONSTANTE)&&pas.toString().trim().equalsIgnoreCase(CONSTANTE)){
                    Intent nuevo =new Intent(MainActivity.this,OptionActivity.class);
                    startActivity(nuevo);
                }
                System.out.println("hola");
            }
        });
    }
}