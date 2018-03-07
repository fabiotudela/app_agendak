package local.com.agenda;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import local.com.agenda.model.Contacto;
import local.com.agenda.model.UtilsContactos;
import local.com.agenda.utils.Utilidades;


/**
 * Created by user on 28/02/2018.
 */

public class AnadirActivity extends Activity {

    //En el comienzo de la definicion de la clase declaramos nuestras variables que tomaran los valores de los objetos-controles en la vista

    //No nos hacen falta estas variables de transferencia. podemos usar directamente la asignacion
    EditText editTextNombre;
    EditText editTextEmail;
    EditText editTextEdad;
    Utilidades u = new Utilidades();
    //Resources rs;

    //Button btnGuardar;

    ArrayList<Contacto> miagenda;
    int edad;

    //Creamos el metodo onCreate para asociarlo al layout correspondiente.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //En esta linea declaramos su layout.
        setContentView(R.layout.anadir_layout);
        //Intent intent = this.getIntent();

        //miagenda = (ArrayList<Contacto>)intent.getSerializableExtra("agenda");

    }

    public void guardar(View v) {

            String nombre = ((EditText) this.findViewById(R.id.editTextNombre)).getText().toString();
            String email = ((EditText) this.findViewById(R.id.editTextEmail)).getText().toString();
            String auxedad = ((EditText) this.findViewById(R.id.editTextEdad)).getText().toString();

            //IMPORTANTE!!! Cuando usamos editText vacíos el resultado para el código NO ES NULL!!, ES ("")

            if ((nombre.equals("")) || (email.equals("")) || (auxedad.equals(""))) {

                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_LONG).show();


            } else {

            //edad = Integer.parseInt(((EditText)this.findViewById(R.id.editTextEdad)).getText().toString());
            edad = Integer.parseInt(auxedad);
            Contacto c = new Contacto(nombre, email, edad);

            Log.d("TAG", "El contcato es: " + c);

            //Lo añadimos a la coleccion
            miagenda.add(c);

            //pasamos la colección como resultado a la actividad principal
            //para que se mantengan los contactos añadidos

            Intent intent = new Intent();
            intent.putExtra("miagenda", miagenda);
            this.setResult(0, intent);
            Toast.makeText(this, "Contacto almacenado", Toast.LENGTH_LONG).show();
            this.finish();

            }

    }

    public void insertarEnDb(View v) {

        String nombre = ((EditText) this.findViewById(R.id.editTextNombre)).getText().toString();
        String email = ((EditText) this.findViewById(R.id.editTextEmail)).getText().toString();
        String auxedad = ((EditText) this.findViewById(R.id.editTextEdad)).getText().toString();

        //IMPORTANTE!!! Cuando usamos editText vacíos el resultado para el código NO ES NULL!!, ES ("")

/*
        if ((TextUtils.isEmpty(editTextNombre.getText())) || (TextUtils.isEmpty(editTextEmail.getText())) || (TextUtils.isEmpty(editTextEdad.getText()))) {
            // para poder ejecutar el metodo tnemos que instanciar  el objeto Utilidades u arriba del todo.
            // tambien instanciamos el objeto Resources rs.
            u.mostarMensaje(this, rs.getString(R.string.campos_vacios));

        } else {*/

        if ((nombre.equals("")) || (email.equals("")) || (auxedad.equals(""))) {

            //Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_LONG).show();
            u.mostrarMensaje(this, R.string.campos_vacios);

        } else {

            //edad = Integer.parseInt(((EditText)this.findViewById(R.id.editTextEdad)).getText().toString());
            edad = Integer.parseInt(auxedad);
            Contacto c = new Contacto(nombre, email, edad);

            //Log.d("TAG", "El contcato es: " + c);

            //Lo añadimos a la coleccion
            UtilsContactos gestion = new UtilsContactos(this);
            gestion.insertarContacto(c);
            //cerrramos la base de datos
            gestion.close();
            Toast.makeText(this, "Contacto almacenado", Toast.LENGTH_LONG).show();
            this.finish();

            //pasamos la colección como resultado a la actividad principal
            //para que se mantengan los contactos añadidos

/*            Intent intent = new Intent();
            intent.putExtra("miagenda", miagenda);
            this.setResult(0, intent);
            Toast.makeText(this, "Contacto almacenado", Toast.LENGTH_LONG).show();
            this.finish();*/

        }

    }

}

