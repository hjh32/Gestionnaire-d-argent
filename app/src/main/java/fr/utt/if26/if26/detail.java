package fr.utt.if26.if26;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class detail extends AppCompatActivity {
    private  thingBD bd = thingBD.getInstance(this, thingBD.DATABASE_NAME, null, 1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String s = bundle.getString("cat");
        String s2 = bundle.getString("depense");
        TextView cat = (TextView)findViewById(R.id.detail_cat);
        cat.setText(s);
        TextView cat2 = (TextView)findViewById(R.id.allmoney);
        cat2.setText(s2);

        Button button1=(Button) findViewById(R.id.button_ajouter);
        button1.setOnClickListener(listener1);
        Button button2=(Button) findViewById(R.id.button_revenue_back);
        button2.setOnClickListener(listener2);

    }
    private View.OnClickListener listener1 = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            EditText time = (EditText)findViewById(R.id.time);
            String time1 = time.getText().toString();
            EditText description = (EditText)findViewById(R.id.description);
            String description1 = description.getText().toString();
            EditText prix = (EditText)findViewById(R.id.prix);
            String prix1 = prix.getText().toString();
            TextView cat = (TextView)findViewById(R.id.detail_cat);
            String cat1 = cat.getText().toString();
            TextView catall = (TextView)findViewById(R.id.allmoney);
            String string5 = catall.getText().toString();
            int i3=Integer.parseInt(string5);
            int i4=Integer.parseInt(prix1);
            int i5 = i3 + i4;
            String s8 = String.valueOf(i5);
            catall.setText(s8);

            thing t = new thing(cat1, time1, description1, prix1);
            bd.addThing(t);

            Intent intent = new Intent();
            intent.setClass(detail.this, Depense.class);
            intent.putExtra("depenses", s8);
            startActivity(intent);
        }
    };
    private View.OnClickListener listener2 = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            TextView cat2 = (TextView)findViewById(R.id.allmoney);
            String string1 = cat2.getText().toString();
            Intent intent = new Intent();
            intent.setClass(detail.this, Depense.class);
            intent.putExtra("depenses", string1);
            startActivity(intent);
        }
    };

}
