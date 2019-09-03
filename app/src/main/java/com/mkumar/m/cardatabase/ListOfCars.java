package com.mkumar.m.cardatabase;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListOfCars extends AppCompatActivity {

    ListView lv;
    DBHelper db1=new DBHelper(this);
    ArrayList<String> A=new ArrayList<String>();
    String B="";
    String car="0";
    ArrayAdapter<String> ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_cars);

        lv=(ListView)findViewById(R.id.carlist);
        Cursor res=db1.displayAll();
        if(res.moveToNext()) {
            A.add("Car No.      Owner       Registration");
            A.add(res.getString(1)+"   "+res.getString(2)+"   "+res.getString(4));
            while(res.moveToNext())
            {
                A.add(res.getString(1)+"   "+res.getString(2)+"   "+res.getString(4));
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
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)menuInfo;
        B=A.get(info.position);
        car=B.substring(0,4);
        //id=B.indexOf(0);
        menu.setHeaderTitle("Select a menu");
        menu.add(0,v.getId(),0,"View");
        menu.add(0,v.getId(),0,"Edit");
        menu.add(0,v.getId(),0,"Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getTitle()=="View")
        {
            Intent i1=new Intent(getApplicationContext(),AdminView.class);
            i1.putExtra("car",car);
            startActivity(i1);
        }
        else if(item.getTitle()=="Edit")
        {
            Intent i1=new Intent(getApplicationContext(),Edit.class);
            i1.putExtra("car",car);
            startActivity(i1);
            finish();
        }
        else if(item.getTitle()=="Delete")
        {
            Intent i1=new Intent(getApplicationContext(),Delete.class);
            i1.putExtra("car",car);
            startActivity(i1);
            finish();
        }
        return super.onContextItemSelected(item);
    }
}
