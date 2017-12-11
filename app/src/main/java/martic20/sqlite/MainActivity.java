package martic20.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BBDD bbdd = new BBDD(this, "vinos", null);
        SQLiteDatabase bd = bbdd.getWritableDatabase();


        ArrayList<ModelVino> arrayOfUsers = new ArrayList<ModelVino>();
// Create the adapter to convert the array to views
        AdapterVino adapter = new AdapterVino(this, arrayOfUsers);
// Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        ModelVino newUser = new ModelVino("Nathan", "San Diego");
        adapter.add(newUser);
        ModelVino newUser1 = new ModelVino("vinoy", "San yrthrth");
        adapter.add(newUser1);
        ModelVino newUser2 = new ModelVino("Nathan", "Sahrthn rhrth");
        adapter.add(newUser2);


    }
}
