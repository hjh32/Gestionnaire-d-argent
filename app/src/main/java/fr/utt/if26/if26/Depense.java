package fr.utt.if26.if26;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class Depense extends AppCompatActivity {
    private ThingBD bd = ThingBD.getInstance(this, ThingBD.DATABASE_NAME, null, 1);
    private String monthStr = "";
    private List<Thing> things = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depense);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        monthStr = bundle.getString("month");
        int year = Integer.valueOf(monthStr.split("/")[1]);
        int month = Integer.valueOf(monthStr.split("/")[0]);

        Button button1=(Button) findViewById(R.id.depense_back);
        button1.setOnClickListener(listener1);
        Button button2=(Button) findViewById(R.id.button_nouveau);
        button2.setOnClickListener(listener2);
        TextView depenseTv = findViewById(R.id.Depenses1);
        depenseTv.setText("Dépenses du " + monthStr);

        ListView liste1 = findViewById(R.id.liste);
        things = bd.getAllThings(year, month);
        final AdaptateurThing adaptateur = new AdaptateurThing(this, R.layout.thing, things);
        liste1.setAdapter(adaptateur);

        SwipeDismissListViewTouchListener touchListener = new SwipeDismissListViewTouchListener(
                liste1,
                new SwipeDismissListViewTouchListener.DismissCallbacks() {

                    @Override
                    public boolean canDismiss(int position) {
                        return true;
                    }

                    @Override
                    public void onDismiss(ListView listView,
                                          int[] reverseSortedPositions) {
                        for (int pos : reverseSortedPositions) {
                            bd.delThing(things.get(pos).getId());
                            things.remove(pos);
                        }
                        adaptateur.notifyDataSetChanged();
                    }
                });

        liste1.setOnTouchListener(touchListener);
    }

    private View.OnClickListener listener1 = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            Intent intent = new Intent();
            intent.setClass(Depense.this, HomepageActivity.class);
            intent.putExtra("month", monthStr);
            startActivity(intent);
        }
    };

    private View.OnClickListener listener2 = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            Intent intent = new Intent();
            intent.setClass(Depense.this, Nouveau.class);
            intent.putExtra("month", monthStr);
            startActivity(intent);
        }
    };
}
