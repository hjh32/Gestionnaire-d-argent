package fr.utt.if26.if26;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomepageActivity extends AppCompatActivity {
    private String monthStr = "";
    private MonthYearPickerDialog pickerDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        final ThingBD bd = ThingBD.getInstance(this, ThingBD.DATABASE_NAME, null, 1);

        if (getIntent().getExtras() != null) {
            monthStr = getIntent().getExtras().getString("month");
        }
        if(monthStr.equals("")) {
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
            monthStr = dateFormat.format(date);
        }
        final TextView monthTxt = findViewById(R.id.main_month);
        monthTxt.setText(monthStr);
        monthTxt.setOnClickListener(listener1);
        pickerDialog = new MonthYearPickerDialog();
        pickerDialog.setListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int i2) {
                monthTxt.setText(month + "/" + year);
                monthStr = monthTxt.getText().toString();
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
        });
        int year = Integer.valueOf(monthStr.split("/")[1]);
        int month = Integer.valueOf(monthStr.split("/")[0]);

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

        List<Thing> things = bd.getAllThings(year, month);
        double aliment = 0;
        double habillement = 0;
        double transport = 0;
        double habitation = 0;
        double divertissement = 0;
        double autre = 0;
        for (Thing t :
                things) {
            switch(t.getCat()) {
                case "Aliment" :
                    aliment += t.getPrix();
                    break;
                case "Habillement" :
                    habillement += t.getPrix();
                    break;
                case "Transport" :
                    transport += t.getPrix();
                    break;
                case "Habitation" :
                    habitation += t.getPrix();
                    break;
                case "Divertissement" :
                    divertissement += t.getPrix();
                    break;
                case "Autre" :
                    autre += t.getPrix();
                    break;
                default : break;
            }
        }
        Pie pie = AnyChart.pie();
        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("Aliment", aliment));
        data.add(new ValueDataEntry("Transport", transport));
        data.add(new ValueDataEntry("Habitation", habitation));
        data.add(new ValueDataEntry("Divertissement", divertissement));
        data.add(new ValueDataEntry("Habillement", habillement));
        data.add(new ValueDataEntry("Autre", autre));
        pie.data(data);

        pie.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);
        AnyChartView anyChartView = (AnyChartView) findViewById(R.id.any_chart_view);
        anyChartView.setChart(pie);
    }

    private View.OnClickListener listener1 = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            pickerDialog.show(getSupportFragmentManager(), "MonthYearPickerDialog");
        }
    };
    private View.OnClickListener listener2 = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            TextView monthTxt = findViewById(R.id.main_month);
            String monthStr = monthTxt.getText().toString();
            Intent intent = new Intent();
            intent.setClass(HomepageActivity.this, Revenue.class);
            intent.putExtra("month", monthStr);
            startActivity(intent);
        }
    };
    private View.OnClickListener listener3 = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            TextView monthTxt = findViewById(R.id.main_month);
            String monthStr = monthTxt.getText().toString();
            Intent intent = new Intent();
            intent.setClass(HomepageActivity.this, Depense.class);
            intent.putExtra("month", monthStr);
            startActivity(intent);
        }
    };
}
