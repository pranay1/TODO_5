package com.example.pranay.todo_4;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listview;
   static ArrayList<TO_DO_ITEMS> todolist;
    static TO_DO_Adapter listadapter;
    static int i = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        listview = (ListView) findViewById(R.id.listview);
        todolist = new ArrayList<>();


 /*       for (int j = 0; j < 10; j++) {

            TO_DO_ITEMS todo = new TO_DO_ITEMS("Activity" + j, j);
            todolist.add(todo);


        }*/

        listadapter = new TO_DO_Adapter(this, R.layout.individual_item_layout, todolist);
        listview.setAdapter(listadapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

        Intent i = new Intent(MainActivity.this,Main22Activity.class);
              startActivity(i);

          /*      AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                AlertDialog dialog;
                builder.setTitle("ADD NEW ACTIVITY");

                final View v = getLayoutInflater().inflate(R.layout.dialogboxlayout, null);
                builder.setView(v);
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        EditText e = (EditText) v.findViewById(R.id.activitynameedittext);
                        if (e.getText().toString().isEmpty()) {

                            Toast.makeText(MainActivity.this, "EMPTY NAME", Toast.LENGTH_SHORT).show();
                        } else {

                            TO_DO_ITEMS todo = new TO_DO_ITEMS(e.getText().toString(), i);
                            todolist.add(todo);
                            listadapter.notifyDataSetChanged();
                            i++;
                            Toast.makeText(MainActivity.this, "New Activity Added", Toast.LENGTH_SHORT).show();
                            Openhelper helper = new Openhelper(MainActivity.this);
                            SQLiteDatabase db = helper.getWritableDatabase();
                            ContentValues cv = new ContentValues();
                            cv.put(Openhelper.ACTIVITY_NAME, e.getText().toString());
                            cv.put(Openhelper.ACTIVITY_NUMBER, i);
                            long a = db.insert(Openhelper.TABLE_NAME, null, cv);
                            if (a == -1) {
                                Toast.makeText(MainActivity.this, "error saving data", Toast.LENGTH_SHORT).show();

                            } else {

                                Toast.makeText(MainActivity.this, "data saved", Toast.LENGTH_SHORT).show();
                            }

                        }


                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();


                    }
                });

                dialog = builder.create();
                dialog.show();*/

            }
        });



        /*listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //   Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                i.putExtra("activity_name", todolist.get(position).activity_name);

                startActivity(i);

            }
        });
*/
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

      /*  Toast.makeText(MainActivity.this, "Activity Completed", Toast.LENGTH_SHORT).show();

        todolist.remove(position);
        MainActivity.listadapter.notifyDataSetChanged();
*/


                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(false);
                AlertDialog dialog;
                builder.setTitle("DELETE");
                builder.setMessage("Do You Want To Delete This Task");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        Open_helper helper = new Open_helper(MainActivity.this);
                        SQLiteDatabase db = helper.getWritableDatabase();

                       String activityname = todolist.get(position).activity_name;
             int activityid = todolist.get(position).id;
                    String date = todolist.get(position).date;
                        String time = todolist.get(position).time;
                        String priority = todolist.get(position).priority;
String activity__id=String.valueOf(activityid);



                      /*  String[] args = new String[]{
                                activityname,
                                String.valueOf(activityid),
                                    date,
                                    time,
                                    priority

                        };*/

                        String args1[] = new String[]{activity__id};


                        Toast.makeText(MainActivity.this, "here", Toast.LENGTH_SHORT).show();
                   //db.delete(Open_helper.TABLE_NAME,Open_helper.ACTIVITY_NAME+" =? AND "+Open_helper.ACTIVITY_ID+" +? "+Open_helper.ACTIVITY_DATE+" +? "+Open_helper.ACTIVITY_TIME+" +? "+Open_helper.ACTIVITY_PRIORITY+" +? ",args);
                        db.delete(Open_helper.TABLE_NAME,Open_helper.ACTIVITY_ID+" =? ",args1);
                        todolist.remove(position);
                        MainActivity.listadapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "Successsfully deleted", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                });


                dialog=builder.create();
                builder.show();


                return true;
            }
        });





        loadtodolist();
//from here



        //till here

    }

    private void loadtodolist() {

        Open_helper helper = new Open_helper(this);
        //    todolist.clear();
        SQLiteDatabase db = helper.getReadableDatabase();
       Cursor cursor = db.query(Open_helper.TABLE_NAME,null,null,null,null,null,null);

        while (cursor.moveToNext()) {

            String name = cursor.getString(cursor.getColumnIndex(Open_helper.ACTIVITY_NAME));
            int id = cursor.getInt(cursor.getColumnIndex(Open_helper.ACTIVITY_ID));
String date = cursor.getString(cursor.getColumnIndex(Open_helper.ACTIVITY_DATE));
          String time = cursor.getString(cursor.getColumnIndex(Open_helper.ACTIVITY_TIME));
          String priority= cursor.getString(cursor.getColumnIndex(Open_helper.ACTIVITY_PRIORITY));

        TO_DO_ITEMS todo = new TO_DO_ITEMS(name,id,date,time,priority);
            todolist.add(todo);


        }


        listadapter.notifyDataSetChanged();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if(id == R.id.aboutUs){
            Intent i = new Intent();
            i.setAction(Intent.ACTION_VIEW);
            Uri uri = Uri.parse("https://www.codingninjas.in");
            i.setData(uri);

            startActivity(i);
        }else if(id == R.id.contactUs){
            Intent i = new Intent();
            i.setAction(Intent.ACTION_CALL);
            Uri uri = Uri.parse("tel:9971627237");
            i.setData(uri);
            startActivity(i);
        }else if(id == R.id.feedback){
            Intent i = new Intent();
            i.setAction(Intent.ACTION_SENDTO);
            Uri uri = Uri.parse("mailto:manisha@codingninjas.in");
            i.putExtra(Intent.EXTRA_SUBJECT,"Feedback");
            i.setData(uri);
            if(i.resolveActivity(getPackageManager()) != null) {
                startActivity(i);
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
