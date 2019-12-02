package com.ejemplos.myapplication;

import android.content.Context;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class Holder  extends RecyclerView.ViewHolder {

    private  TextView txtNombre, txtApellido;
    ImageView estrella;
    View v;
    Context context;
    public Holder(View v, Context context) {
        super(v);
        this.v=v;
        this.context=context;
        txtNombre = (TextView) v.findViewById(R.id.textView);
        txtApellido=(TextView)v.findViewById(R.id.textView2);
        estrella=(ImageView) v.findViewById(R.id.estrella);
    }

    public void bind(Usuarios entity, int pos){
        if(entity.seleccionado)v.setBackgroundColor( context.getResources().getColor(R.color.oscuro, null));
        else v.setBackgroundColor(ContextCompat.getColor(context,R.color.claro ));
        if(entity.getNombre().contains("3")) estrella.setVisibility(View.VISIBLE);
        else estrella.setVisibility(View.INVISIBLE);
        txtNombre.setText(entity.getNombre());
        txtApellido.setText(entity.getApellidos());
    }

    public TextView getTextNombre() {
        return txtNombre;
    }
    public TextView getTextApellido() {
        return txtApellido;
    }
    @Override
    public String toString() {
        return "ViewHolder{" + txtNombre.getText() + "}";
    }
}