package martic20.sqlite;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class VinoView extends AppCompatActivity {

    protected int position = -1;
    protected EditText nom;
    protected EditText collita;
    protected EditText origen;
    protected EditText tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vino_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Editar vino");

        position = (getIntent().getIntExtra("idVino", -1) + 1);


        nom = (EditText) findViewById(R.id.txNom);
        collita = (EditText) findViewById(R.id.txCollita);
        origen = (EditText) findViewById(R.id.txOrigen);
        tipo = (EditText) findViewById(R.id.txTipo);

        if (position == -1) {
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //guardar datos update
                    BBDD ddbb = new BBDD(view.getContext(), "vinos", null);
                    SQLiteDatabase db = ddbb.getWritableDatabase();
                    Cursor c = db.rawQuery(ModelVino.insert( nom.getText().toString()
                            , tipo.getText().toString(), origen.getText().toString(), collita.getText().toString()), null);
                    c.moveToFirst();
                    c.close();

                    //Intent g = new Intent(view.getContext(), MainActivity.class);
                    //((Activity)view.getContext()).startActivity(g);
                    ((Activity) view.getContext()).finish();
                }
            });
        } else {
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //guardar datos update
                    BBDD ddbb = new BBDD(view.getContext(), "vinos", null);
                    SQLiteDatabase db = ddbb.getWritableDatabase();
                    Cursor c = db.rawQuery(ModelVino.updateById(position, nom.getText().toString()
                            , tipo.getText().toString(), origen.getText().toString(), collita.getText().toString()), null);
                    c.moveToFirst();
                    c.close();

                    Intent g = new Intent(view.getContext(), MainActivity.class);
                    //((Activity)view.getContext()).startActivity(g);
                    ((Activity) view.getContext()).finish();
                }
            });

            BBDD ddbb = new BBDD(this, "vinos", null);
            SQLiteDatabase db = ddbb.getWritableDatabase();

            Cursor cursor = db.rawQuery("select * from " + ModelVino.TABLE_VINOS + " where " + ModelVino.VINOS_ID + " = " + position, null);

            if (cursor.moveToFirst()) {
                if (!cursor.isAfterLast()) {
                    nom = (EditText) findViewById(R.id.txNom);
                    collita = (EditText) findViewById(R.id.txCollita);
                    origen = (EditText) findViewById(R.id.txOrigen);
                    tipo = (EditText) findViewById(R.id.txTipo);

                    nom.setText(cursor.getString(cursor.getColumnIndex(ModelVino.VINOS_NOM)));
                    collita.setText(cursor.getString(cursor.getColumnIndex(ModelVino.VINOS_COLLITA)));
                    origen.setText(cursor.getString(cursor.getColumnIndex(ModelVino.VINOS_ORIGEN)));
                    tipo.setText(cursor.getString(cursor.getColumnIndex(ModelVino.VINOS_TIPO)));

                }
            } else {
                Toast.makeText(this, "No se ha encontrado ningún vino", Toast.LENGTH_SHORT);
            }
            cursor.close();
        }
    }

}
