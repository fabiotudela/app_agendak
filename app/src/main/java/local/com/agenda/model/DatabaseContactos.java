package local.com.agenda.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import local.com.agenda.constantes.Constantes;

/**
 * Created by user on 05/03/2018.
 */

public class DatabaseContactos extends SQLiteOpenHelper{

    //TODO: Constantes para crear y eliminar tabla de contactos
    // Redactamos la instruccion literal SQL (LENGUAJE PROPIO DE SQL EN COLOR VERDE), apoyandonos en las constanes que hemos definido anteriormente.
    private static final String CREATE_TABLE_CONTACTOS =  //Creamos la tabla
            "CREATE TABLE " + Constantes.CONTACTOS_TABLA +  //Nombre de la tabla
                    " (" + Constantes.CAMPO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +  // ( Nombre del Campo y tipo..etc...)
                    Constantes.CAMPO_NOMBRE + " TEXT NOT NULL, " +
                    Constantes.CAMPO_EMAIL + " TEXT NOT NULL, " +
                    Constantes.CAMPO_EDAD + " INTEGER NOT NULL)";

    private static final String DELETE_TABLE_CONTACTOS =
            "DROP TABLE IF EXIST " + Constantes.CONTACTOS_TABLA;


    // TODO: Constructor de nuestra clase. Para crear una referencia a nuestra db
    public DatabaseContactos(Context context) {
        super(context, Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
    }

    // TODO: El metodo onCreate se llama al crear la base de datos.
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Invocamos al metodo createTable
        createTables(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //Eliminamos la tabla...
        deleteTables(db);
        //...y la volvemos a crear.
        createTables(db);
    }


    // TODO: Usamos un metodo para crear nuestra tabla.
    // Con el parametro "db" que es de tipo SQL que hemos creado arriba de tipo String.
    private void createTables(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CONTACTOS);

    }

    private void deleteTables(SQLiteDatabase db) {
        db.execSQL(DELETE_TABLE_CONTACTOS);

    }


}
