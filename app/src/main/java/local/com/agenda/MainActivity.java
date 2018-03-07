package local.com.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import local.com.agenda.model.Contacto;

public class MainActivity extends AppCompatActivity {

    //En el comienzo de la definicion de la clase declaramos nuestras variables que tomaran los valores de los objetos en la vista
    //No nos hace falta declarar los botones ya que el OnClick lo implementaremos desde el xml en la creación del boton.
    //Button btnAnadir;
    //Button btnBuscar;
    //Button btnVerTodos;

    ArrayList<Contacto> agenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creamos la agenda: de tipo arraylist de la clase contacto antes creada.
        agenda = new ArrayList<Contacto>();

    }


    public void anadir (View v) {
        Intent intent = new Intent(this, AnadirActivity.class);
        intent.putExtra("agenda", agenda);
        Log.d("TAG", "Concatco creado a pasar " + agenda);
        this.startActivityForResult(intent, 2);
        //Aquí podemos seleccionar el requesCode que consideremos para luego en el onResult Activity chequearlo.

    }

    public void anadirParaDb (View v) {
        Intent intent = new Intent(this, AnadirActivity.class);
         this.startActivity(intent);
        //Aquí podemos seleccionar el requesCode que consideremos para luego en el onResult Activity chequearlo.

    }

    public void buscar (View v) {

        Intent intent = new Intent(this, BuscarActivity.class);
        intent.putExtra("agenda", agenda);
        startActivity(intent);
    }

    public void verTodos (View v) {

        Intent intent = new Intent(this, VerTodosActivity.class);
        intent.putExtra("agenda", agenda);
        startActivity(intent);
    }

/*    @Override
    public void onActivityResult(int cod, int result, Intent data) {
        //Chequeamos que nos referimos al resultado enviado de la activity AnadirActivity. cod 2.definido en la llamada.
        if (cod == 2) {
            // Make sure the request was successful
            if (result == 0) {
                agenda = (ArrayList<Contacto>)data.getSerializableExtra("miagenda");
            }
        }
    }*/
}
