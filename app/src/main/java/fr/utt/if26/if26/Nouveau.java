package fr.utt.if26.if26;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Nouveau extends AppCompatActivity {
    private String monthStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouveau);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        monthStr = bundle.getString("month");

        Button button1=(Button) findViewById(R.id.bt1);
        button1.setOnClickListener(listener1);
        Button button2=(Button) findViewById(R.id.bt2);
        button2.setOnClickListener(listener2);
        Button button3=(Button) findViewById(R.id.bt3);
        button3.setOnClickListener(listener3);
        Button button4=(Button) findViewById(R.id.bt4);
        button4.setOnClickListener(listener4);
        Button button5=(Button) findViewById(R.id.bt5);
        button5.setOnClickListener(listener5);
        Button button6=(Button) findViewById(R.id.bt6);
        button6.setOnClickListener(listener6);
        Button button7=(Button) findViewById(R.id.button_nouveau_back);
        button7.setOnClickListener(listener7);
    }

    private View.OnClickListener listener1 = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            Intent intent = new Intent();
            intent.setClass(Nouveau.this, detail.class);
            intent.putExtra("cat", "Aliment");
            intent.putExtra("month", monthStr);
            startActivity(intent);
        }
    };

    private View.OnClickListener listener2 = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            Intent intent = new Intent();
            intent.setClass(Nouveau.this, detail.class);
            intent.putExtra("cat", "Transport");
            intent.putExtra("month", monthStr);
            startActivity(intent);
        }
    };

    private View.OnClickListener listener3 = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            Intent intent = new Intent();
            intent.setClass(Nouveau.this, detail.class);
            intent.putExtra("cat", "Habitation");
            intent.putExtra("month", monthStr);
            startActivity(intent);
        }
    };
    private View.OnClickListener listener4 = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            Intent intent = new Intent();
            intent.setClass(Nouveau.this, detail.class);
            intent.putExtra("cat", "Divertissement");
            intent.putExtra("month", monthStr);
            startActivity(intent);
        }
    };
    private View.OnClickListener listener5 = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            Intent intent = new Intent();
            intent.setClass(Nouveau.this, detail.class);
            intent.putExtra("cat", "Habillement");
            intent.putExtra("month", monthStr);
            startActivity(intent);
        }
    };
    private View.OnClickListener listener6 = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            Intent intent = new Intent();
            intent.setClass(Nouveau.this, detail.class);
            intent.putExtra("cat", "Autre");
            intent.putExtra("month", monthStr);
            startActivity(intent);
        }
    };
    private View.OnClickListener listener7 = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            Intent intent = new Intent();
            intent.setClass(Nouveau.this, Depense.class);
            intent.putExtra("month", monthStr);
            startActivity(intent);
        }
    };
}
