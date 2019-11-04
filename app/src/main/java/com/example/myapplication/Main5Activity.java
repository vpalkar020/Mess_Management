package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class Main5Activity extends AppCompatActivity {
    Button del,chk;
    EditText e1,e2;
    DatabaseHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        mydb = new DatabaseHelper(this);
        del=(Button)findViewById(R.id.delmemid);
        e1=(EditText)findViewById(R.id.delNoidid);
        e2=(EditText)findViewById(R.id.delNameid);
        chk=(Button)findViewById(R.id.chkmemid);
        getMob();
        delete_Data();
    }
       public void delete_Data(){
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deletedRows=mydb.del_data(e1.getText().toString());
                if(deletedRows>0) {
                    Toast.makeText(Main5Activity.this, "Deleteded Successfully", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Main5Activity.this, "Cant dalete Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void getMob(){
        chk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res=mydb.getMobile(e1.getText().toString());
                StringBuffer buff= new StringBuffer();
                if(res.getCount()==0){
                    Toast.makeText(Main5Activity.this,"No Data found",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Main5Activity.this, " Data found", Toast.LENGTH_SHORT).show();
                }
                    while(res.moveToNext()){
                        buff.append(res.getString(0));
                    }
                    e2.setText(buff.toString());
                    String phno=e2.getText().toString();
                    Intent send=new Intent(Intent.ACTION_VIEW);
                    send.putExtra("address",phno);
                    send.putExtra("sms_body","Pay the fees : - from Vipul");
                    send.setType("vnd.android-dir/mms-sms");
                    startActivity(send);
                }
        });

    }
}
