package fr.utt.if26.if26;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ConnectionCodeDB db = ConnectionCodeDB.getInstance(this, ConnectionCodeDB.DATABASE_NAME, null, 1);

        Button bt2 = findViewById(R.id.button_skip);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!db.hasCode()) {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, HomepageActivity.class);
                    startActivity(intent);
                }
            }
        });

        Button bt1 = findViewById(R.id.button_connection);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!db.hasCode()) {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, HomepageActivity.class);
                    startActivity(intent);
                } else {
                    EditText codeTxt = findViewById(R.id.main_connectioncode);
                    String code = codeTxt.getText().toString();
                    String savedCode = db.getCode();
                    StringBuilder sb = new StringBuilder();
                    try {
                        MessageDigest md = MessageDigest.getInstance("MD5");
                        byte[] hashInBytes = md.digest(code.getBytes(StandardCharsets.UTF_8));

                        for (byte b : hashInBytes) {
                            sb.append(String.format("%02x", b));
                        }
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                    if(savedCode.equals(sb.toString())) {
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this, HomepageActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });

        Button bt3 = findViewById(R.id.button_createcode);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!db.hasCode()) {
                    EditText newcodeTxt = findViewById(R.id.main_newcode);
                    String newcode = newcodeTxt.getText().toString();
                    try {
                        db.addCode(newcode);
                        newcodeTxt.setText("");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
