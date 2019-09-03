package com.mkumar.m.cardatabase;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListOfUsers extends AppCompatActivity {

    ListView lv;
    DatabaseConnect db1=new DatabaseConnect(this);
    ArrayList<String> A=new ArrayList<String>();
    String B="";
    String id="0";
    ArrayAdapter<String> ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_users);

        lv=(ListView)findViewById(R.id.userlist);
        Cursor res=db1.displayAll();
        if(res.moveToNext()) {
            A.add("Username     Phone       Gender");
            A.add(res.getString(1)+"    "+res.getString(2)+"        "+res.getString(3));
            while(res.moveToNext())
            {
                A.add(res.getString(1)+"    "+res.getString(2)+"        "+res.getString(3));
            }
            ad=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,A);
            lv.setAdapter(ad);
            registerForContextMenu(lv);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"No Record Found",Toast.LENGTH_SHORT).show();
        }
    }
}
