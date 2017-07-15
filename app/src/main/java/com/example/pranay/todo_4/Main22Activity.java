package com.example.pranay.todo_4;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
import android.provider.Settings;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import static com.example.pranay.todo_4.MainActivity.listadapter;
import static com.example.pranay.todo_4.MainActivity.todolist;

public class Main22Activity extends AppCompatActivity {


CheckBox checkbox;
    EditText nameedittext;
    EditText dateedittext;
    EditText timeedittext;
    RadioGroup radio_grp;
    RadioButton radio_button;
    TextView infotextview;
    Button settaskbutton;
    static int DATE_ID=0;
    static int TIME_ID=1;
    String activity__name;
    String date;
    String time;
    String priority;

    int year_x;
    int month_x;
    int day_x;
    int hour_x;
    int min_x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main22);



        checkbox = (CheckBox)findViewById(R.id.checkbox);
        nameedittext = (EditText) findViewById(R.id.activitynameedittextnew);
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

                int id1 = radio_grp.getCheckedRadioButtonId();
                radio_button = (RadioButton) findViewById(id1);


                if ((nameedittext.getText().toString().isEmpty())||(dateedittext.getText().toString().isEmpty())||(dateedittext.getText().toString().isEmpty())||(!checkbox.isChecked())||(radio_button.getText().toString().isEmpty())) {

                    Toast.makeText(Main22Activity.this, "Field Empty", Toast.LENGTH_SHORT).show();
                } else {


                    priority = radio_button.getText().toString();

                    activity__name = nameedittext.getText().toString();

                    if (checkbox.isChecked()) {
                        Toast.makeText(Main22Activity.this, "checkbox checked", Toast.LENGTH_SHORT).show();
                        Snackbar.make(v, "checkbox checked", BaseTransientBottomBar.LENGTH_SHORT).show();
                        checkbox();
                    }


                    Open_helper helper = new Open_helper(Main22Activity.this);
                    SQLiteDatabase db = helper.getWritableDatabase();
                    ContentValues cv = new ContentValues();
                    cv.put(Open_helper.ACTIVITY_NAME, activity__name);
                    cv.put(Open_helper.ACTIVITY_DATE, date);
                    cv.put(Open_helper.ACTIVITY_TIME, time);
                    cv.put(Open_helper.ACTIVITY_PRIORITY, priority);

                    long res = db.insert(Open_helper.TABLE_NAME, null, cv);
                    if (res == -1) {

                        Toast.makeText(Main22Activity.this, "Error Saving Data", Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(Main22Activity.this, "Data Saved", Toast.LENGTH_SHORT).show();
                    }


                    Intent a1 = new Intent(Main22Activity.this, MainActivity.class);
                    startActivity(a1);

                }

            }
        });






    }

    private void checkbox() {

        AlarmManager am = (AlarmManager) Main22Activity.this.getSystemService(Context.ALARM_SERVICE);

        Intent i =new Intent(Main22Activity.this,AlarmReciever.class);


        // we use pending intent as we want it to open when this alarm is reached due to this we use pendingintent not simple intent
        PendingIntent pendingintent = PendingIntent.getBroadcast(Main22Activity.this,1,i,0);


        //am.set(AlarmManager.RTC, System.currentTimeMillis()+2000 ,pendingintent);
am.setRepeating(AlarmManager.RTC,System.currentTimeMillis(), System.currentTimeMillis()+86400,pendingintent);




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
                   date = String.valueOf(day_x)+"/"+String.valueOf(month_x)+"/"+String.valueOf(year_x);
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
                time =String.valueOf(hour_x)+":"+String.valueOf(min_x);
                }
            }, hour_x, min_x, false);

        }




        else{

            return null;
        }


    }

}
