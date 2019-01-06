package fr.utt.if26.if26;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private String monthStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getIntent().getExtras() != null) {
            monthStr = getIntent().getExtras().getString("month");
        }
        if(monthStr.equals("")) {
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
            monthStr = dateFormat.format(date);
        }
        EditText monthTxt = findViewById(R.id.main_month);
        monthTxt.setText(monthStr);
        int year = Integer.valueOf(monthStr.split("/")[1]);
        int month = Integer.valueOf(monthStr.split("/")[0]);
        thingBD bd = thingBD.getInstance(this, thingBD.DATABASE_NAME, null, 1);
        double revenue = bd.getRevenue(year, month);
        TextView revenueTv = findViewById(R.id.main_revenue);
        revenueTv.setText(String.valueOf(revenue));
        ImageView ivrev = findViewById(R.id.main_ivrevenue);
        ivrev.setOnClickListener(listener2);

        ImageView ivdep = findViewById(R.id.main_ivdepenses);
        ivdep.setOnClickListener(listener3);

        double depenses = bd.calcuteDepenses(year, month);
        depenses = Math.floor(depenses * 100) / 100;
        TextView depenseTv = findViewById(R.id.main_depenses);
        depenseTv.setText(String.valueOf(depenses));

        double reste = revenue - depenses;
        reste = Math.floor(reste * 100) / 100;
        TextView resteTv = findViewById(R.id.main_reste);
        resteTv.setText(String.valueOf(reste));

        Button ok = findViewById(R.id.main_ok);
        ok.setOnClickListener(listener1);
    }
    private View.OnClickListener listener1 = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            EditText monthTxt = findViewById(R.id.main_month);
            monthStr = monthTxt.getText().toString();
            int year = Integer.valueOf(monthStr.split("/")[1]);
            int month = Integer.valueOf(monthStr.split("/")[0]);
            thingBD bd = thingBD.getInstance(MainActivity.this, thingBD.DATABASE_NAME, null, 1);
            double revenue = bd.getRevenue(year, month);
            TextView revenueTv = findViewById(R.id.main_revenue);
            revenueTv.setText(String.valueOf(revenue));
            ImageView ivrev = findViewById(R.id.main_ivrevenue);
            ivrev.setOnClickListener(listener2);

            ImageView ivdep = findViewById(R.id.main_ivdepenses);
            ivdep.setOnClickListener(listener3);

            double depenses = bd.calcuteDepenses(year, month);
            depenses = Math.floor(depenses * 100) / 100;
            TextView depenseTv = findViewById(R.id.main_depenses);
            depenseTv.setText(String.valueOf(depenses));

            double reste = revenue - depenses;
            reste = Math.floor(reste * 100) / 100;
            TextView resteTv = findViewById(R.id.main_reste);
            resteTv.setText(String.valueOf(reste));
        }
    };
    private View.OnClickListener listener2 = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            EditText monthTxt = findViewById(R.id.main_month);
            String monthStr = monthTxt.getText().toString();
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, Revenue.class);
            intent.putExtra("month", monthStr);
            startActivity(intent);
        }
    };
    private View.OnClickListener listener3 = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            EditText monthTxt = findViewById(R.id.main_month);
            String monthStr = monthTxt.getText().toString();
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, Depense.class);
            intent.putExtra("month", monthStr);
            startActivity(intent);
        }
    };
}
