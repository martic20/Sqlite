package martic20.sqlite;

/**
 * Created by Martí on 11/12/2017.
 */


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class BBDD extends SQLiteOpenHelper {

    static final int BBDD_VERSION =1;

    public BBDD(Context context, String nombre, SQLiteDatabase.CursorFactory factory) {

        super(context, nombre, factory, BBDD_VERSION);

    }

    @Override

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usuario(" +
                "dni integer primary key, " +
                "nombre text, ciudad text, " +
                "numero integer)");

    }

    @Override

    public void onUpgrade(SQLiteDatabase db, int version1, int version2) {

        db.execSQL("drop table if exists usuario");

        db.execSQL("create table usuario(dni integer primary key, nombre text, ciudad text, numero integer)");

    }

}