package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=(EditText)findViewById(R.id.editText1);
        e2=(EditText)findViewById(R.id.editText2);
        b1=(Button)findViewById(R.id.button2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                if (s1.equals("Vipul") && s2.equals("Vipul@1997")) {
                    Toast.makeText(getApplicationContext(), "Login successfull", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                    startActivity(intent);


                } else {
                    Toast.makeText(getApplicationContext(), "Login fail", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
