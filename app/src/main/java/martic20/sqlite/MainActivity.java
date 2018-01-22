package martic20.sqlite;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView

    protected BBDD ddbb;
    protected SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent g = new Intent(view.getContext(),VinoView.class);
                g.putExtra("idVino",-1);
                ((Activity)view.getContext()).startActivity(g);
                //((Activity)view.getContext()).finish();
            }
        });
    }

    protected void loadData(String query) {
        ddbb = new BBDD(this, "vinos", null);
        db = ddbb.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        ArrayList<ModelVino> vinosArray = new ArrayList<ModelVino>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String nm = cursor.getString(cursor.getColumnIndex(ModelVino.VINOS_NOM));
                String cl = cursor.getString(cursor.getColumnIndex(ModelVino.VINOS_COLLITA));
                String og = cursor.getString(cursor.getColumnIndex(ModelVino.VINOS_ORIGEN));
                String tp = cursor.getString(cursor.getColumnIndex(ModelVino.VINOS_TIPO));
                int id = cursor.getInt(cursor.getColumnIndex(ModelVino.VINOS_ID));
                vinosArray.add(new ModelVino(id, nm, og, tp, cl));
                cursor.moveToNext();
            }
        }
        cursor.close();

        AdapterVino adapter = new AdapterVino(this, vinosArray);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onRestart();
        loadData("select * from " + ModelVino.TABLE_VINOS);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.search) {
            showSearchDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    protected void showSearchDialog() {

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        View promptView = layoutInflater.inflate(R.layout.dialog_seach, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setView(promptView);

        final EditText input = (EditText) promptView.findViewById(R.id.edittext);
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("Buscar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String text = input.getText().toString();
                        loadData("select * from " + ModelVino.TABLE_VINOS +" where " +
                                ModelVino.VINOS_NOM+" like '%"+text+"%' or "+
                                ModelVino.VINOS_TIPO+" like '%"+text+"%' or "+
                                ModelVino.VINOS_COLLITA+" like '%"+text+"%' or "+
                                ModelVino.VINOS_ORIGEN+" like '%"+text+"%'"
                        );
                    }
                })
                .setNegativeButton("Canelar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

}
