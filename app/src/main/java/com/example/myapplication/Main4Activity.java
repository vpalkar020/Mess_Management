package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class Main4Activity extends AppCompatActivity {
    Button b1,b2;
    DatabaseHelper mydb;
    EditText e1,e2;
    private static final String TAG="Main4Activity";
    private EditText mJoinDate,mCompDate;
    private DatePickerDialog.OnDateSetListener mDateSetListner;
    private DatePickerDialog.OnDateSetListener mDateSetListner1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        mydb = new DatabaseHelper(this);
        b1 = (Button) findViewById(R.id.addbtnid);
        e1 = (EditText) findViewById(R.id.nameid);
        e2 = (EditText) findViewById(R.id.mobileid);
        mJoinDate = (EditText) findViewById(R.id.jdateid);
        mCompDate = (EditText) findViewById(R.id.cdateid);
        b2 = (Button) findViewById(R.id.viewbtnid);
        addData();
        viewData();
        joinDateSelection();
        complitionDateSelection();
    }
            public void joinDateSelection() {
            mJoinDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH);
                    int day = cal.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog dialog = new DatePickerDialog(Main4Activity.this, R.style.Theme_AppCompat_DayNight_Dialog_MinWidth, mDateSetListner, year, month, day);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));
                    dialog.show();
                }
            });

            mDateSetListner = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    month = month + 1;
                    Log.d(TAG, "onDateSet:Date:" + month + "/" + day + "/" + year);
                    String date = month + "/" + day + "/" + year;
                    mJoinDate.setText(date);
                }
            };
        }
        public void complitionDateSelection(){
        mCompDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal=Calendar.getInstance();
                int year= cal.get(Calendar.YEAR);
                int month= cal.get(Calendar.MONTH);
                int day= cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog=new DatePickerDialog(Main4Activity.this,R.style.Theme_AppCompat_DayNight_Dialog_MinWidth,mDateSetListner1,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));
                dialog.show();
            }
        });

        mDateSetListner1=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month=month+1;
                Log.d(TAG,"onDateSet:Date:"+month+"/"+day+"/"+year);
                String date=month+"/"+day+"/"+year;
                mCompDate.setText(date);
            }
        };

    }
    public void addData(){
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result=mydb.insertData(e1.getText().toString(),e2.getText().toString(),mJoinDate.getText().toString(),mCompDate.getText().toString());
                if(result==true){
                    Toast.makeText(Main4Activity.this, "Inserted Successfully", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Main4Activity.this, "can't insert", Toast.LENGTH_SHORT).show();

                }
                e1.setText("");
                e2.setText("");
                mJoinDate.setText("");
            }
        });
    }
    public void viewData(){
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = mydb.viewAllData();
                if (res.getCount() == 0) {
                    //show message
                    showMessage("Error", "No Data Found");
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("ID :" + res.getString(0) + "\n");
                    buffer.append("NAME :" + res.getString(1) + "\n");
                    buffer.append("PHNO :" + res.getString(2) + "\n");
                    buffer.append("JOINING :" + res.getString(3) + "\n\n");
                }
                //show all data
                showMessage("Data",buffer.toString());
            }
        });

    }
    public void showMessage(String title,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
