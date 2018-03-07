package local.com.agenda;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import local.com.agenda.model.Contacto;
import local.com.agenda.model.UtilsContactos;

/**
 * Created by user on 28/02/2018.
 */

public class BuscarActivity extends AppCompatActivity {

    ArrayList<Contacto> miagenda;
    TextView mostrarTexto;
    //Contacto c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscar_layout);
        Intent intent = this.getIntent();

        miagenda = (ArrayList<Contacto>)intent.getSerializableExtra("agenda");
    }

    public void buscarSinDb(View v) {

        mostrarTexto = findViewById(R.id.textViewBMostrar);
        String email=((EditText)this.findViewById(R.id.editTextBEmail)).getText().toString();

        Contacto c=null;
        for(Contacto con:miagenda) {
            if(con.getEmail().equals(email)) {
                c=con;
                break;
            }
        }

        mostrarDato(c);
    }

    public void buscarEnDb(View v) {


        String email=((EditText)this.findViewById(R.id.editTextBEmail)).getText().toString();
        UtilsContactos gestion = new UtilsContactos(this);
        Contacto c=null;
        c=gestion.buscarPorEmail(email);
        mostrarDato(c);
        gestion.close();
    }

    public void salir(View v) {
        this.finish();
    }

/*    private void mostrarDato(Contacto c) {

        if(c==null) {
            AlertDialog.Builder cuadro = new AlertDialog.Builder(this);
            cuadro.setMessage("No se ha encontrado el contacto. Desea buscar de nuevo?");

            //En caso negativo salimos de la actividad.
            cuadro.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int wich) {
                    BuscarActivity.this.finish();
                }
            });

            cuadro.setPositiveButton(android.R.string.yes, null);
            cuadro.show();

        } else {
            String datos = "Nombre: " + c.getNombre() +
                    "\n Email: " + c.getEmail() +
                    "\n Edad: " + c.getEdad();
            Toast.makeText( this, datos, Toast.LENGTH_LONG).show();

        }
    }*/

    private void mostrarDato(Contacto c) {

        mostrarTexto = findViewById(R.id.textViewBMostrar);

        if (c == null) {

            mostrarTexto.setText("Contacto no registrado");

        } else {

            String datos = "Nombre: " + c.getNombre() +
                    "\nEmail: " + c.getEmail() +
                    "\nEdad: " + c.getEdad();

            mostrarTexto.setText(datos);
        }
    }

}
