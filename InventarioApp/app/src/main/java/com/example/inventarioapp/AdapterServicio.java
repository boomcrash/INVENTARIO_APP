package com.example.inventarioapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterServicio extends RecyclerView.Adapter<AdapterServicio.ViewHolder> {
    static String name,description,price;
    ArrayList<servicios> arrayList;
    Context context;
    Listener listener;
    View v;
    ArrayList<servicios> arrayList2=new ArrayList<>();
    public AdapterServicio(Context context,ArrayList<servicios> arrayList,Listener listener) {
        this.arrayList=arrayList;
        this.context=context;
        this.listener=listener;
    }

    public View getView(){
        return v;
    }

    @NonNull

    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        v= LayoutInflater.from(context).inflate(R.layout.rv_layout,parent,false);
         return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(arrayList!=null && arrayList.size()>0){
            //holder.checkBox.setText(arrayList.get(position).getNombre()+"       $"+arrayList.get(position).getPrecio());
            holder.uno.setText(String.valueOf(arrayList.get(position).getNombre()));
            holder.dos.setText(String.valueOf(arrayList.get(position).getDescripcion()));
            holder.tres.setText("$"+String.valueOf(arrayList.get(position).getPrecio()));
            String nombre=String.valueOf(arrayList.get(position).getNombre());
            String des=String.valueOf(arrayList.get(position).getDescripcion());
            String precio=String.valueOf(arrayList.get(position).getPrecio());
            holder.uno.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent nuevo =new Intent(context,ServicioActivity.class);
                    nuevo.putExtra("nombre",nombre);
                    nuevo.putExtra("descripcion",des);
                    nuevo.putExtra("precio",precio);
                    Toast.makeText(context, nombre+precio+des, Toast.LENGTH_SHORT).show();;
                    context.startActivity(nuevo);
                }
            });
            /*if(holder.checkBox.isChecked()){
                arrayList2.add(arrayList.get(position));

            }else {
                arrayList2.remove(arrayList.get(position));
            }
            listener.OnQuantityChange(arrayList2);

             */
        }else{
            System.out.println("no hay datos");
        }
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView uno,dos,tres;

        //CheckBox checkBox;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            //checkBox=itemView.findViewById(R.id.checkBox);
            uno=itemView.findViewById(R.id.texto1);
            dos=itemView.findViewById(R.id.texto2);
            tres=itemView.findViewById(R.id.texto3);
        }
    }
}
