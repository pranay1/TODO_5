package com.example.pranay.todo_4;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    static  int postion;
    EditText dateedittext;
    EditText timeedittext;
    RadioGroup radio_grp;
    RadioButton radio_button;
    TextView infotextview;
    Button settaskbutton;
    static int DATE_ID=0;
    static int TIME_ID=1;
    int year_x;
    int month_x;
    int day_x;
    int hour_x;
    int min_x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent i1 = getIntent();
        String act_name=i1.getStringExtra("activity_name");

        Toast.makeText(this, act_name+"  Open", Toast.LENGTH_SHORT).show();


        dateedittext = (EditText) findViewById(R.id.Dateedittext);
        timeedittext = (EditText) findViewById(R.id.timeedittext);
        infotextview = (TextView)findViewById(R.id.infotextview);
        radio_grp = (RadioGroup) findViewById(R.id.radiogroup);
        settaskbutton =(Button)findViewById(R.id.button);

        Calendar cal = Calendar.getInstance();
        day_x=cal.get(Calendar.DAY_OF_MONTH);
        month_x=cal.get(Calendar.MONTH);
        year_x=cal.get(Calendar.YEAR);





        dateedittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_ID);
            }
        });


        timeedittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(TIME_ID);
            }
        });




        settaskbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = radio_grp.getCheckedRadioButtonId();
                radio_button= (RadioButton)findViewById(id);
                Toast.makeText(Main2Activity.this, radio_button.getText().toString(), Toast.LENGTH_SHORT).show();
                Intent a = new Intent(Main2Activity.this,MainActivity.class);
                startActivity(a);
            }
        });






    }


    @Override
    protected Dialog onCreateDialog(int id) {

        if(id==DATE_ID){

            return new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    year_x=year;
                    month_x=month+1;
                    day_x=dayOfMonth;
                    dateedittext.setText("Date :"+String.valueOf(day_x)+"/"+String.valueOf(month_x)+"/"+String.valueOf(year_x));

                }
            }, year_x, month_x, day_x);

        }

        if(id==TIME_ID){

            return new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                    hour_x=hourOfDay;
                    min_x=minute;
                    timeedittext.setText("Time :   "+String.valueOf(hour_x)+":"+String.valueOf(min_x));

                }
            }, hour_x, min_x, false);

        }




        else{

            return null;
        }


    }




}
