package martic20.sqlite;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Martí on 11/12/2017.
 */


public class AdapterVino extends ArrayAdapter<ModelVino> {


    public AdapterVino(Context context, ArrayList<ModelVino> users) {
        super(context, 0, users);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ModelVino vino = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_vino, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.txNom);
        TextView tvOrigen = (TextView) convertView.findViewById(R.id.txOrigen);
        TextView tvTipo = (TextView) convertView.findViewById(R.id.txTipo);
        TextView tvCollita = (TextView) convertView.findViewById(R.id.txCollita);

        // Populate the data into the template view using the data object

        tvName.setText(vino.nom);
        tvOrigen.setText(vino.origen);
        tvTipo.setText(vino.tipo);
        tvCollita.setText(vino.collita);

        ImageButton delete = (ImageButton) convertView.findViewById(R.id.btDelete);
        delete.setTag(position);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (Integer) v.getTag();
                Toast.makeText(getContext(), "Vino " + position + " borrado!", Toast.LENGTH_SHORT).show();
            }
        });

        LinearLayout vinoLayout = (LinearLayout) convertView.findViewById(R.id.vino);
        vinoLayout.setTag(position);
        vinoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (Integer) v.getTag();
                Intent i = new Intent(getContext(), VinoView.class);
                i.putExtra("idVino", position);
                v.getContext().startActivity(i);
                //((Activity)v.getContext()).finish();
            }
        });
        // Return the completed view to render on screen
        return convertView;
    }

}
