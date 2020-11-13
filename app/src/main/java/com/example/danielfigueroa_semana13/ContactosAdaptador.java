package com.example.danielfigueroa_semana13;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class ContactosAdaptador extends BaseAdapter {

    //data
    private ArrayList<Contactos> contactosA;

    public ContactosAdaptador(){

        contactosA = new ArrayList<>();
    }

    public void addContacto (Contactos contactos){
        contactosA.add(contactos);
        notifyDataSetChanged();
    }

    public void clear() {
        contactosA.clear();
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {

        return contactosA.size();
    }

    @Override
    public Object getItem(int position) {
        return contactosA.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    //dotar de UI a un Arraylist
    @Override
    public View getView(int position, View renglon, ViewGroup lista) {

        LayoutInflater inflater = LayoutInflater.from(lista.getContext());
        View renglonView = inflater.inflate(R.layout.row, null);

        Contactos contactos = contactosA.get(position);



        TextView nombreContactosLista = renglonView.findViewById(R.id.nombreContactosLista);
        TextView numeroContactosLista = renglonView.findViewById(R.id.numeroContactosLista);
        Button botonEliminarContacto = renglonView.findViewById(R.id.botonEliminarContacto);
        Button botonLlamarContacto = renglonView.findViewById(R.id.botonLlamarContacto);

        nombreContactosLista.setText(contactos.getNombreContacto());
        numeroContactosLista.setText(contactos.getNumeroContacto());

        botonEliminarContacto.setOnClickListener(
                (v)->{

                    String id = contactosA.get(position).getId();
                    Log.e("TAG", contactosA.get(position).getId());
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("contactos").child(contactosA.get(position).getIdUsuario()).child(id);

                    reference.setValue(null);
                }
        );

        botonLlamarContacto.setOnClickListener(
                (v)->{

                    ActivityCompat.requestPermissions((Activity) renglon.getContext(), new String[]{Manifest.permission.CALL_PHONE}, 1);
                    String telefono = "tel:" + contactosA.get(position).getNumeroContacto();
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(telefono));
                    renglon.getContext().startActivity(intent);
                }
        );

        return renglonView;
    }
}
