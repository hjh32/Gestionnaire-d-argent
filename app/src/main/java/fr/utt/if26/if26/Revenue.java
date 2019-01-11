package fr.utt.if26.if26;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Revenue extends AppCompatActivity {
    private ThingBD bd = ThingBD.getInstance(this, ThingBD.DATABASE_NAME, null, 1);
    private String monthStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revenue);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        monthStr = bundle.getString("month");
        int year = Integer.valueOf(monthStr.split("/")[1]);
        int month = Integer.valueOf(monthStr.split("/")[0]);
        TextView revenue_month = findViewById(R.id.revenue_month);
        revenue_month.setText("Revenue du " + monthStr);
        EditText revenueTxt = findViewById(R.id.revenue1);
        revenueTxt.setText(String.valueOf(bd.getRevenue(year, month)));
        Button button = (Button) findViewById(R.id.button_modifier1);
        button.setOnClickListener(listener1);
    }

    private View.OnClickListener listener1 = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            int year = Integer.valueOf(monthStr.split("/")[1]);
            int month = Integer.valueOf(monthStr.split("/")[0]);
            double revenue = bd.getRevenue(year, month);
            EditText revenueTxt = findViewById(R.id.revenue1);
            double newRev = Double.parseDouble(revenueTxt.getText().toString());
            if(revenue - 0.00001 < 0 && revenue + 0.00001 > 0) {
                Thing t = new Thing(0, year, month, 0, "revenue", "", newRev);
                bd.addThing(t);
            } else {
                bd.updateRevenue(year, month, newRev);
            }
            Intent intent = new Intent();
            intent.setClass(Revenue.this, HomepageActivity.class);
            intent.putExtra("month", monthStr);
            startActivity(intent);
        }
    };
}
