package fr.utt.if26.if26;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1=(Button) findViewById(R.id.main_bt1);
        button1.setOnClickListener(listener1);
        Button button2=(Button) findViewById(R.id.main_bt2);
        button2.setOnClickListener(listener2);
        Button button3=(Button) findViewById(R.id.main_bt3);
        button3.setOnClickListener(listener3);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String s1 = bundle.getString("mois");
        String s2 = bundle.getString("revenue");
        String s3 = bundle.getString("depenses");
        if(s1 != null){
            TextView editText1=(TextView) findViewById(R.id.main_mois);
            editText1.setText(s1);
        }
        if(s2 != null){
            TextView editText2=(TextView) findViewById(R.id.main_revenue);
            editText2.setText(s2);
        }
        if(s3 != null){
            TextView editText3=(TextView) findViewById(R.id.main_depenses);
            editText3.setText(s3);
        }
        TextView editText2=(TextView) findViewById(R.id.main_revenue);
        TextView editText3=(TextView) findViewById(R.id.main_depenses);
        int i1 = Integer.parseInt(editText2.getText().toString());
        int i2 = Integer.parseInt(editText3.getText().toString());
        int i3 = i1 - i2;
        TextView editText4=(TextView) findViewById(R.id.main_reste);
        editText4.setText(String.valueOf(i3));

    }
    private View.OnClickListener listener1 = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            TextView editText1=(TextView) findViewById(R.id.main_mois);
            String st4 = editText1.getText().toString();
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, Mois.class);
            intent.putExtra("mois", st4);
            startActivity(intent);
        }
    };
    private View.OnClickListener listener2 = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            TextView editText2=(TextView) findViewById(R.id.main_revenue);
            String st5 = editText2.getText().toString();
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, Revenue.class);
            intent.putExtra("revenue", st5);
            startActivity(intent);
        }
    };
    private View.OnClickListener listener3 = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            TextView editText3=(TextView) findViewById(R.id.main_depenses);
            String st6 = editText3.getText().toString();
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, Depense.class);
            intent.putExtra("depenses", st6);
            startActivity(intent);
        }
    };
}
