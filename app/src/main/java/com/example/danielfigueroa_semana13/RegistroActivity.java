package com.example.danielfigueroa_semana13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {


    private FirebaseDatabase db;
    private FirebaseAuth auth;
    private EditText registroNombre;
    private EditText registroCorreo;
    private EditText registroContrasena;
    private EditText registroContrasenaOtra;
    private Button registroCancelar;
    private Button registroRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        registroNombre = findViewById(R.id.registroNombre);
        registroCorreo = findViewById(R.id.registroCorreo);
        registroContrasena = findViewById(R.id.registroContrasena);
        registroContrasenaOtra = findViewById(R.id.registroContrasenaOtra);
        registroCancelar = findViewById(R.id.registroCancelar);
        registroRegistro = findViewById(R.id.registroRegistro);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();

        registroCancelar.setOnClickListener(this);
        registroRegistro.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registroRegistro:
                //hacer aqui una consulta (addeventlistenerforsinglevalueevent) a mi firebase y que confirme si aiguen tiene el nombre ya registrad
                String id = db.getReference().child("usuarios").push().getKey();
                DatabaseReference reference = db.getReference().child("usuarios").child(id);
                Usuario usuario = new Usuario(
                        id,
                        registroNombre.getText().toString(),
                        registroCorreo.getText().toString(),
                        registroContrasena.getText().toString(),
                        registroContrasenaOtra.getText().toString()

                );
                reference.setValue(usuario);//todo esto hace que se registren los usuarios en el firebase

                auth.createUserWithEmailAndPassword(registroCorreo.getText().toString(), registroContrasena.getText().toString())
                .addOnCompleteListener(
                        (task)-> {
                        if (task.isSuccessful()) {

                            Intent i = new Intent(this, ContactoActivity.class);
                            i.putExtra("user", id);
                            startActivity(i);

                        } else {
                            Toast.makeText(this,task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
            );
                break;

            case R.id.registroCancelar:
                Intent j = new Intent(this, MainActivity.class);
                startActivity(j);
                break;
        }

    }
}