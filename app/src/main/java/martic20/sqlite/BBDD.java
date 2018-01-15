package martic20.sqlite;

/**
 * Created by Martí on 11/12/2017.
 */


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class BBDD extends SQLiteOpenHelper {

    static final int BBDD_VERSION = 1;

    public BBDD(Context context, String nombre, SQLiteDatabase.CursorFactory factory) {

        super(context, nombre, factory, BBDD_VERSION);

    }

    @Override

    public void onCreate(SQLiteDatabase db) {
        rewriteTable(db);
        populate(db);
    }

    public void populate(SQLiteDatabase db) {
        db.execSQL("insert into " + ModelVino.TABLE_VINOS + " values (1,'Umnhu','2006','Marroc','tinto');");
        db.execSQL("insert into " + ModelVino.TABLE_VINOS + " values (2,'Garfel','2014','Priorat','blanco');");
        db.execSQL("insert into " + ModelVino.TABLE_VINOS + " values (3,'Granti','2016','Toscana','tinto');");
        db.execSQL("insert into " + ModelVino.TABLE_VINOS + " values (4,'Innisha','2014','Ascó','tinto');");
        db.execSQL("insert into " + ModelVino.TABLE_VINOS + " values (5,'Imnia','2002','Pallars','blanco');");
        db.execSQL("insert into " + ModelVino.TABLE_VINOS + " values (6,'Luko','2008','Allà','tinto');");

    }

    public void kill(SQLiteDatabase db) {
        db.execSQL("delete from " + ModelVino.TABLE_VINOS);

    }

    public void rewriteTable(SQLiteDatabase db) {
        //db.execSQL("drop table " + ModelVino.TABLE_VINOS);
        db.execSQL("create table " + ModelVino.TABLE_VINOS + "(" +
                ModelVino.VINOS_ID + " integer primary key, " +
                ModelVino.VINOS_NOM + " text, " +
                ModelVino.VINOS_COLLITA + " text, " +
                ModelVino.VINOS_ORIGEN + " text," +
                ModelVino.VINOS_TIPO + " text) ");
    }

    @Override

    public void onUpgrade(SQLiteDatabase db, int version1, int version2) {

    }

}