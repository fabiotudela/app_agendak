package local.com.agenda.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import local.com.agenda.constantes.Constantes;

/**
 * Created by user on 05/03/2018.
 */

//Se crea esta clase para poder utilizar la anteriormente creada: DatabaseContactos.



public class UtilsContactos {
    //Atributos
    private SQLiteDatabase db = null;
    //Decalramos e instanciamos nuestra calse anteriormente creada.
    private DatabaseContactos dbhelper = null;
    //Contexto
    Context context;

    // TODO: Constructor de nuestra clase para instanciar la clase database contactos y usar los metodos para escribir en nuestra base de datos
    public UtilsContactos(Context contexto){
        this.context=contexto;
        //crea una instancia del helper
        dbhelper=new DatabaseContactos(context);
        //crea un objeto SQLiteDatabase para operar
        //contra la base de datos
        db=dbhelper.getWritableDatabase();
    }

    //TODO: Cerramos la base de datos.
    public void close(){
        dbhelper.close();
    }

    //TODO: Usamos un objeto de tipo ContentValues para guardar las keys de cada campo de nuestrio contacto e instertarlo en la tabla de base de datos.
    public long insertarContacto(Contacto c ){
        ContentValues initialValues=new ContentValues();
        initialValues.put("nombre", c.getNombre());
        initialValues.put("email", c.getEmail());
        initialValues.put("edad", c.getEdad());

        // hacemos referencia a la tabla mediante nuestra constante.
        //Inserta el contacto en la base de datos.
        return db.insert(Constantes.CONTACTOS_TABLA, null, initialValues);
    }
    public Cursor recuperarContactos(){

        //Todo el return es un Cursor. Objeto que tiene data
        return db.query(Constantes.CONTACTOS_TABLA,
                new String[]{Constantes.CAMPO_ID,
                        Constantes.CAMPO_NOMBRE,
                        Constantes.CAMPO_EMAIL,
                        Constantes.CAMPO_EDAD},
                null, null, null,null,null);
    }
    public Contacto buscarPorEmail(String email){
        Contacto con=null;

        //Creamos un cursor para guardar una quier de la seleccion y un criterio para el campo email =?
        Cursor c=db.query(Constantes.CONTACTOS_TABLA,
                new String[]{Constantes.CAMPO_ID,
                        Constantes.CAMPO_NOMBRE,
                        Constantes.CAMPO_EMAIL,
                        Constantes.CAMPO_EDAD},
                Constantes.CAMPO_EMAIL + "=?",
                new String[]{email}, null,null,null);
        //Cone este IF sólo se sucede en TRUE si la "query" lanzada a la base de datos consigue una coinciedencia en el mail.
        //De esa manera creara un contacto recuperando los datos del contacto encontrado.
        if(c.moveToNext()){
            con=new Contacto(c.getString(1),c.getString(2),c.getInt(3));
        }
        return con;
    }

}

