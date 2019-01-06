package fr.utt.if26.if26;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Mois extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mois);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String s = bundle.getString("mois");
        TextView cat = (TextView)findViewById(R.id.mois_1);
        cat.setText(s);
        Button button1=(Button) findViewById(R.id.mois_modifier);
        button1.setOnClickListener(listener1);
    }

    private View.OnClickListener listener1 = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            EditText editText1=(EditText) findViewById(R.id.mois_1);
            String string1 = editText1.getText().toString();
            Intent intent = new Intent();
            intent.setClass(Mois.this, MainActivity.class);
            intent.putExtra("mois", string1);
            startActivity(intent);
        }
    };
}
