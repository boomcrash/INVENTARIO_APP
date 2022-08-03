package com.example.inventarioapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterConsulta extends RecyclerView.Adapter<AdapterConsulta.ViewHolder> {
    static String name,description,price;
    ArrayList<consultas> arrayList;
    Context context;
    ListenerC listener;
    View v;
    ArrayList<consultas> arrayList2=new ArrayList<>();
    public AdapterConsulta(Context context, ArrayList<consultas> arrayList, ListenerC listener) {
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
        v= LayoutInflater.from(context).inflate(R.layout.rv_layout2,parent,false);
         return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(arrayList!=null && arrayList.size()>0){
            //holder.checkBox.setText(arrayList.get(position).getNombre()+"       $"+arrayList.get(position).getPrecio());
            holder.uno.setText(String.valueOf(arrayList.get(position).getApellidos())+" "+String.valueOf(arrayList.get(position).getNombres()));
            holder.dos.setText(String.valueOf(arrayList.get(position).getTratamientos()));
            holder.tres.setText("$"+String.valueOf(arrayList.get(position).getPrecioFinal()));

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
