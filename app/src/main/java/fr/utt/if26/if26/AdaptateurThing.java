package fr.utt.if26.if26;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class AdaptateurThing extends ArrayAdapter<Thing> {

    List<Thing> l;
    Context c;
    int r;

    public AdaptateurThing(Context context, int resource, List<Thing> objects) {
        super(context, resource, objects);
        this.l = objects;
        this.c = context;
        this.r = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) c).getLayoutInflater();
        View v = inflater.inflate(r, parent, false);
        TextView cat = v.findViewById(R.id.thing_categorie);
        TextView date = v.findViewById(R.id.thing_date);
        TextView des = v.findViewById(R.id.thing_description);
        TextView prix = v.findViewById(R.id.thing_prix);
        cat.setText(l.get(position).getCat());
        date.setText(l.get(position).getDay() + "/" + l.get(position).getMonth());
        des.setText(l.get(position).getDescription());
        prix.setText(String.valueOf(l.get(position).getPrix()));
        return v;
    }
}
