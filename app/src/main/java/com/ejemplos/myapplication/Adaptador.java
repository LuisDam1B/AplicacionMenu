package com.ejemplos.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Adaptador extends SeleccionableAdapter implements View.OnClickListener, View.OnLongClickListener{
    private  ArrayList<Usuarios> datos;
    private View.OnClickListener listener;

    private View.OnLongClickListener listenerLong;
    private Context context;
     Adaptador(ArrayList<Usuarios> datos, Context context) {

        this.datos=datos;
        this.context=context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recyclerlayout, viewGroup, false);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        return new Holder(itemView, context);
    }

    void eliminarItemSeleccionado(List<Integer> items){
        Collections.sort(items);//ordenamos
        Collections.reverse(items);// revertimos

        ArrayList<Usuarios> aux = new ArrayList<Usuarios>(datos);
        for (int i:items) aux.remove(i);
        datos = new ArrayList<>(aux);

        for(int i:items)notifyItemRemoved(i);

    }

    void desactivarSeleccion(){


    }

    void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;  }


    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }
    public void setOnLongClickListener(View.OnLongClickListener listener) {
        this.listenerLong = listener;  }
    @Override
    public boolean onLongClick(View v) {
        if(listener != null)
            listenerLong.onLongClick(v);
        return true;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        Usuarios item = datos.get(i);
        ((Holder)viewHolder).bind(item, i);

    }

    @Override
    public int getItemCount() {  return datos.size(); }



}
