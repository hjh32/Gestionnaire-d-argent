package fr.utt.if26.if26;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Revenue extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revenue);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String s = bundle.getString("revenue");
        TextView cat = (TextView)findViewById(R.id.revenue1);
        cat.setText(s);
        Button button=(Button) findViewById(R.id.button_modifier1);
        button.setOnClickListener(listener1);
    }

    private View.OnClickListener listener1 = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            EditText editText1=(EditText) findViewById(R.id.revenue1);
            String string1 = editText1.getText().toString();
            Intent intent = new Intent();
            intent.setClass(Revenue.this, MainActivity.class);
            intent.putExtra("revenue", string1);
            startActivity(intent);
        }
    };
}
