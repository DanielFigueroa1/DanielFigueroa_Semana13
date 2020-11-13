package com.example.danielfigueroa_semana13;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class ContactosAdaptador extends BaseAdapter {

    private String userId;
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
                    String id = contactos.getId();

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("contactos").child(userId).child(id);
                    reference.setValue(null);
                }
        );

        botonEliminarContacto.setOnClickListener(
                (v)->{
                    String numeroContacto = contactos.getNumeroContacto();

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("contactos").child(userId).child(numeroContacto);


                }
        );

        return renglonView;
    }
}
