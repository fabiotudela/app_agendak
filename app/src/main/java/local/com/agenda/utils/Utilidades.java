package local.com.agenda.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by user on 06/03/2018.
 */

public class Utilidades {

    /**
     * Muestra un toast con un mensaje para el usuario
     * @param context (representa el contexto donde se va a mostra el mensaje)
     * @param mensaje (Mensaje del usuario)
     */
    public void mostrarMensaje (Context context, int mensaje) {

        Toast.makeText(context, mensaje, Toast.LENGTH_LONG).show();


    }
}
