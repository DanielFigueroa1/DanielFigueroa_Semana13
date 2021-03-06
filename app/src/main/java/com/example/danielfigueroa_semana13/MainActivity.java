package com.example.danielfigueroa_semana13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseDatabase db;
    private FirebaseAuth auth;
    private EditText mainCorreo;
    private EditText mainContrasena;
    private TextView mainNoCuenta;
    private Button mainIngresarBoton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainCorreo = findViewById(R.id.mainCorreo);
        mainContrasena = findViewById(R.id.mainContrasena);
        mainNoCuenta = findViewById(R.id.mainNoCuenta);
        mainIngresarBoton = findViewById(R.id.mainIngresarBoton);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();

        mainIngresarBoton.setOnClickListener(this);
        mainNoCuenta.setOnClickListener(this);
        /*Intent i = new Intent(this, ContactoActivity.class);
                    startActivity(i);*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mainIngresarBoton:
                //hacer aqui una consulta (addeventlistenerforsinglevalueevent) a mi firebase y que confirme si aiguen tiene el nombre ya registrad
                String id = db.getReference().child("usuarios").push().getKey();
                DatabaseReference reference = db.getReference().child("usuarios").child(id);
                auth.signInWithEmailAndPassword(mainCorreo.getText().toString(), mainContrasena.getText().toString())

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



            case R.id.mainNoCuenta:
                Intent j = new Intent(this, RegistroActivity.class);
                startActivity(j);
                break;
        }

    }
}