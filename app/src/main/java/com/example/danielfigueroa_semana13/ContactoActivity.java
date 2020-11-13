package com.example.danielfigueroa_semana13;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.UUID;

public class ContactoActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseDatabase db;
    private EditText contactoNombre;
    private EditText contactoNumero;
    private Button contactoAgregarBoton;
    private ListView contactosLista;
    /*private ArrayList <Contactos> dataContactos; lista simple
    private ArrayAdapter<Contactos> adapter;*/
    private ContactosAdaptador adapter;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        userId = getIntent().getExtras().getString("user");
        contactoNombre=findViewById(R.id.contactoNombre);
        contactoNumero=findViewById(R.id.contactoNumero);
        contactoAgregarBoton=findViewById(R.id.contactoAgregarBoton);

        adapter = new ContactosAdaptador();

        db = FirebaseDatabase.getInstance();

        contactoAgregarBoton.setOnClickListener(this);//para que el boton agregue numeros

        /*dataContactos = new ArrayList<>(); lista simple
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, dataContactos);*/

        contactosLista=findViewById(R.id.contactosLista);
        contactosLista.setAdapter(adapter);

        loadDatabase();
    }

    @Override
    public void onClick(View v) { //todo esto hace que se registren los contactos en firebase
        switch (v.getId()){
            case R.id.contactoAgregarBoton:
                String id =  db.getReference().child("contactos").child(userId).push().getKey(); //para escribirse como firebase
                DatabaseReference reference = db.getReference().child("contactos").child(userId).child(id);
                Contactos contactos = new Contactos(
                        id,
                        userId,
                        contactoNombre.getText().toString(),
                        contactoNumero.getText().toString()
                );
                reference.setValue(contactos);

        }


    }

    private void loadDatabase() {
        DatabaseReference ref = db.getReference().child("contactos").child(userId);
        ref.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot data) {
                        adapter.clear();
                        for (DataSnapshot child : data.getChildren()){
                            Contactos contactos = child.getValue(Contactos.class);
                            adapter.addContacto(contactos);
                        }

                    }
                    /*public void onDataChange(DataSnapshot data) { lista simple
                        dataContactos.clear();
                        for (DataSnapshot child : data.getChildren()){
                        Contactos contactos = child.getValue(Contactos.class);
                        dataContactos.add(contactos);
                            }
                        adapter.notifyDataSetChanged();
                    }*/

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }
        );
    }

}

