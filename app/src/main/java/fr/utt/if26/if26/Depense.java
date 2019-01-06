package fr.utt.if26.if26;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Depense extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depense);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String s = bundle.getString("depenses");
        if(s != null){
            TextView cat = (TextView)findViewById(R.id.combien);
            cat.setText(s);
        }

        Button button1=(Button) findViewById(R.id.depense_back);
        button1.setOnClickListener(listener1);
        Button button2=(Button) findViewById(R.id.button_nouveau);
        button2.setOnClickListener(listener2);

        ListView liste1 = findViewById(R.id.liste);
        thingBD persistance = thingBD.getInstance(this, thingBD.DATABASE_NAME, null, 1);
        //persistance.initData();
        ArrayList<thing> things = persistance.getAllThings();
        AdaptateurThing adaptateur = new AdaptateurThing(this, R.layout.thing, things);
        liste1.setAdapter(adaptateur);
    }

    private View.OnClickListener listener1 = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            TextView cat = (TextView)findViewById(R.id.combien);
            String string1 = cat.getText().toString();
            Intent intent = new Intent();
            intent.setClass(Depense.this, MainActivity.class);
            intent.putExtra("depenses", string1);
            startActivity(intent);
        }
    };
    private View.OnClickListener listener2 = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            TextView cat = (TextView)findViewById(R.id.combien);
            String string1 = cat.getText().toString();
            Intent intent = new Intent();
            intent.setClass(Depense.this, Nouveau.class);
            intent.putExtra("depenses", string1);
            startActivity(intent);
        }
    };
}
