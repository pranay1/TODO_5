package com.example.pranay.todo_4;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by pranay on 7/7/2017.
 */

public class TO_DO_Adapter extends ArrayAdapter<TO_DO_ITEMS> {

    Context context;
    ArrayList<TO_DO_ITEMS> todolist;
    int resource;


    public TO_DO_Adapter(@NonNull Context context, @LayoutRes int resource, ArrayList<TO_DO_ITEMS> todolist) {
        super(context, resource,todolist);
        this.context=context;
        this.todolist=todolist;
        this.resource=resource;

    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        if(convertView==null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.individual_item_layout, null);
        }

        TextView name = (TextView)convertView.findViewById(R.id.nametextview);
        TextView number = (TextView)convertView.findViewById(R.id.numbertextview);
// new code added from here
        TextView datetextview = (TextView)convertView.findViewById(R.id.datetextview);
TextView timetextview = (TextView)convertView.findViewById(R.id.timetextview);
   Button prioritybutton = (Button)convertView.findViewById(R.id.prioritybutton);


        TO_DO_ITEMS todo = todolist.get(position);


        name.setText(todo.activity_name);
       number.setText(String.valueOf(todo.id)+").");
datetextview.setText("DATE  :  "+todo.date);
        timetextview.setText("TIME  :  "+todo.time);
String prior= todo.priority;
       // Toast.makeText(context, todo.priority, Toast.LENGTH_SHORT).show();
prioritybutton.setText(prior);
        prioritybutton.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.PaleTurquoise));
if(prior.compareTo("HIGH")==0) {
   // Toast.makeText(context, "inside", Toast.LENGTH_SHORT).show();
    //prioritybutton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
convertView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.Tomato));
}

else if (prior.compareTo("MEDIUM")==0){
    convertView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.Yellow));
}
// priority must be low
else{
convertView.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.PaleGreen));

}





    return convertView;

    }
}
