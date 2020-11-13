package com.example.danielfigueroa_semana13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseDatabase db;
    private EditText mainNombre;
    private Button mainIngresarBoton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainNombre = findViewById(R.id.mainNombre);
        mainIngresarBoton = findViewById(R.id.mainIngresarBoton);

        db = FirebaseDatabase.getInstance();

        mainIngresarBoton.setOnClickListener(this);
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
                Usuario usuario = new Usuario(
                        id,
                        mainNombre.getText().toString()
                );
                reference.setValue(usuario);//todo esto hace que se registren los usuarios en el firebase

                Intent i = new Intent(this, ContactoActivity.class);
                i.putExtra("user", id);
                startActivity(i);
                        break;
        }

    }
}