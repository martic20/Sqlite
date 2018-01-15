package martic20.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView

    protected BBDD ddbb;
    protected SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    protected void onResume() {
        super.onRestart();
        ddbb = new BBDD(this, "vinos", null);
        db = ddbb.getWritableDatabase();

        Cursor  cursor = db.rawQuery("select * from "+ModelVino.TABLE_VINOS,null);
        ArrayList<ModelVino> vinosArray = new ArrayList<ModelVino>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String nm = cursor.getString(cursor.getColumnIndex(ModelVino.VINOS_NOM));
                String cl = cursor.getString(cursor.getColumnIndex(ModelVino.VINOS_COLLITA));
                String og = cursor.getString(cursor.getColumnIndex(ModelVino.VINOS_ORIGEN));
                String tp = cursor.getString(cursor.getColumnIndex(ModelVino.VINOS_TIPO));
                int id = cursor.getInt(cursor.getColumnIndex(ModelVino.VINOS_ID));
                vinosArray.add(new ModelVino(id,nm,og,tp,cl));
                cursor.moveToNext();
            }
        }
        cursor.close();

        AdapterVino adapter = new AdapterVino(this, vinosArray);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
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

        if (id == R.id.mybutton) {
            // do something here
        }
        return super.onOptionsItemSelected(item);
    }

}
