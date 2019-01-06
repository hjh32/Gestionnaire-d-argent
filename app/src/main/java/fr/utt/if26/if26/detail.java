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
    private String monthStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String categorie = bundle.getString("cat");
        monthStr = bundle.getString("month");
        TextView cat = (TextView)findViewById(R.id.detail_cat);
        cat.setText(categorie);
        TextView monthTv = findViewById(R.id.detail_month);
        monthTv.setText("/" + monthStr);

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
            int year = Integer.valueOf(monthStr.split("/")[1]);
            int month = Integer.valueOf(monthStr.split("/")[0]);

            thing t = new thing(0, year, month, Integer.parseInt(time1), cat1, description1, Double.parseDouble(prix1));
            bd.addThing(t);

            Intent intent = new Intent();
            intent.setClass(detail.this, Depense.class);
            intent.putExtra("month", monthStr);
            startActivity(intent);
        }
    };

    private View.OnClickListener listener2 = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            Intent intent = new Intent();
            intent.setClass(detail.this, Depense.class);
            intent.putExtra("month", monthStr);
            startActivity(intent);
        }
    };

}
