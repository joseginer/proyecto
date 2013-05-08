package library;

import java.util.HashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
   
    // Versiond de la base de datos
    private static final int DATABASE_VERSION = 1;
    // Nombre de la base de datos
    private static final String DATABASE_NAME = "android_api";
    // Nombre de la tabla Login
    private static final String TABLE_LOGIN = "login";
    // Nomber de las columnas de la tabla Login
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_EJERCICIOS = "ejercicios";
    private static final String KEY_CREATED_AT = "created_at";
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    // Creando tablas
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_LOGIN + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_EMAIL + " TEXT UNIQUE,"
                + KEY_EJERCICIOS + " TEXT,"
                + KEY_CREATED_AT + " TEXT" + ")";
        db.execSQL(CREATE_LOGIN_TABLE);
    }
    // Actualizando base de datos
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);
        // Create tables again
        onCreate(db);
    }
    /**
     * Almacenamiento de los datos del usuario en la base de datos
     * */
    public void addUser(String name, String email, String uid, String created_at) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name); // Name
        values.put(KEY_EMAIL, email); // Email
        values.put(KEY_EJERCICIOS, uid); // Email
        values.put(KEY_CREATED_AT, created_at); // Created At
        // Inserting Row
        db.insert(TABLE_LOGIN, null, values);
        db.close(); // Closing database connection
    }
    /**
     * Obtener datos del usuario de la base de datos
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String,String> user = new HashMap<String,String>();
        String selectQuery = "SELECT  * FROM " + TABLE_LOGIN;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Mueve el cursor al principio
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            user.put("name", cursor.getString(1));
            user.put("email", cursor.getString(2));
            user.put("ejercicios", cursor.getString(3));
            user.put("created_at", cursor.getString(4));
        }
        cursor.close();
        db.close();
        // devuelve usuario
        return user;
    }
    /**
     * Obtener el estado de conexión del usuario 
     * devuelve true si hay filas en la tabla
     * */
    public int getRowCount() {
        String countQuery = "SELECT  * FROM " + TABLE_LOGIN;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int rowCount = cursor.getCount();
        db.close();
        cursor.close();
        // devulve el número de filas
        return rowCount;
    }
    /**
     * Vuelve a crar la base de datos
     * Elimina todas las tablas y las vuelve a crear
     * */
    public void resetTables(){
        SQLiteDatabase db = this.getWritableDatabase();
        // Elimina todas las filas
        db.delete(TABLE_LOGIN, null, null);
        db.close();
    }
}
