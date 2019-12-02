package com.ejemplos.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Usuarios> datos;
    Adaptador adaptador;
    boolean pulsado;
    ActionModeCallBack actionModeCallBack;
    ActionMode actionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerList);
        datos=new ArrayList<>();
        anadirDatos();
        adaptador=new Adaptador(datos, this);
       // pulsado=false;

        actionModeCallBack = new ActionModeCallBack();

        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int posicion = recyclerView.getChildAdapterPosition(v);
                if (actionMode != null){
                    if (intercambiarSeleccion(posicion)) {
                        datos.get(posicion).seleccionado = true;

                    }else
                        datos.get(posicion).seleccionado= false;
                } else
                    Toast.makeText(MainActivity.this, "Has pulsado" + recyclerView.getChildAdapterPosition(v)
                            , Toast.LENGTH_SHORT).show();
            });


        adaptador.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int posicion = recyclerView.getChildAdapterPosition(v);
                if (actionMode == null)
                    actionMode = startSupportActionMode(actionModeCallBack);

                if (intercambiarSeleccion(posicion)) datos.get(posicion).seleccionado = true;
                else
                    datos.get(posicion).seleccionado= false;

                Toast.makeText(MainActivity.this, "Long", Toast.LENGTH_SHORT).show();


                return false;
            }
        });


        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration( new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(adaptador);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        //recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,1 )); //Segundo argumento, 0 no salta de linea, 1 salta para redimensionar



    }

    private void anadirDatos() {
        for(int i=0; i<20;i++)
        {
            //  if(i%2==0)
            datos.add(new Usuarios("nombre"+i, "apellido1"+i+"   Apellido2"+i));
            //  else if(i%3==0)  datos.add(new Usuarios("nombre afafa afaf adfa"+i, "apellido1/nafafaf"+i+"   Apellido2"+i));
            //   else   datos.add(new Usuarios("nombre"+i,""));

        }
    }

    private  boolean intercambiarSeleccion(int posicion){
        boolean seleccionado=adaptador.toggleSelection(posicion);
        int count = adaptador.getSelectedItemCount();

        if (count== 0)actionMode.finish();
        else{
            actionMode.setTitle(String.valueOf(count));
            actionMode.invalidate();//actualiza el estado de action mode
        }

        return seleccionado;
    }


    class ActionModeCallBack implements ActionMode.Callback{

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {

            mode.getMenuInflater().inflate(R.menu.menu_layout,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            switch (item.getItemId()){

                case R.id.eliminar:
                    adaptador.eliminarItemSeleccionado(adaptador.getSelectedItems());
                    mode.finish();
                    return true;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

            //codigo para des-seleccionar
            adaptador.clearSelection();
            adaptador.desactivarSeleccion();
            actionMode = null;

        }
    }

}
