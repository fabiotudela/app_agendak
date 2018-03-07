package local.com.agenda;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import local.com.agenda.model.Contacto;
import local.com.agenda.model.UtilsContactos;

/**
 * Created by user on 28/02/2018.
 */

public class VerTodosActivity extends AppCompatActivity {

    TextView mostrarTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_todos_layout);
        //Intent intent = this.getIntent();

        UtilsContactos gestion = new UtilsContactos(this);
        Cursor c = gestion.recuperarContactos();
        mostrarDatos(c);
        gestion.close();

    }


    private void mostrarDatos(Cursor c) {

        mostrarTexto = findViewById(R.id.textViewVTodos);

        if (c == null) {

            mostrarTexto.setText("Contacto no registrado");

        } else {

            String datos = "Nombre: " + c.getString(1) +
                    "\nEmail: " + c.getString(2) +
                    "\nEdad: " + c.getString(3);

            mostrarTexto.setText(datos);
        }
    }

}
