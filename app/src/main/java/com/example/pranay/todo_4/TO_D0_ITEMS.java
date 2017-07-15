package com.example.pranay.todo_4;

/**
 * Created by pranay on 7/7/2017.
 */

class  TO_DO_ITEMS {

    String activity_name;
int id;
String date;
    String time;
    String priority;


    public TO_DO_ITEMS(String activity_name, int id,String date, String time, String priority) {
        this.activity_name = activity_name;
this.id=id;
        this.date = date;
        this.time = time;
        this.priority = priority;
    }
}
