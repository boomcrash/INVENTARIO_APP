package com.example.inventarioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ServicioActivity extends AppCompatActivity {
    TextView n,d,p,textView;
    static ArrayList<String> productos =new ArrayList<>();

    ImageButton btn,btn2;
    Button btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicio);
        Bundle extras=getIntent().getExtras();
        String name=extras.getString("nombre");
        String description=extras.getString("descripcion");
        String price=extras.getString("precio");
        n=findViewById(R.id.texto1);
        d=findViewById(R.id.texto2);
        p=findViewById(R.id.texto3);
        btn=findViewById(R.id.button);
        btn2=findViewById(R.id.button2);
        btn3=findViewById(R.id.button3);
        textView=findViewById(R.id.textView);
        n.setText(name);
        d.setText("$"+price);
        p.setText(description);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int valor= Integer.parseInt(textView.getText().toString());
                if(valor!=0){
                    textView.setText(String.valueOf(valor-1));
                    p.setText(String.valueOf(Integer.parseInt(price)*(valor-1)));
                }else{
                    textView.setText(String.valueOf(0));
                }

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int valor= Integer.parseInt(textView.getText().toString());
                textView.setText(String.valueOf(valor+1));
                p.setText(String.valueOf(Integer.parseInt(price)*(valor+1)));
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(textView.getText().toString())!=0){
                    productos.add(name+"-"+"$"+price+"-"+description+"-"+textView.getText());
                    Toast.makeText(ServicioActivity.this, "SE AGREGÓ CON EXITO!", Toast.LENGTH_SHORT).show();
                    textView.setText("0");
                }else{
                    Toast.makeText(ServicioActivity.this, "ESCOJA AL MENOS UN PRODUCTO !", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}