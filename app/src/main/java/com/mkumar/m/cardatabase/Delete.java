package com.mkumar.m.cardatabase;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Delete extends AppCompatActivity {

    TextView car,owner,type,reg,ins,pol;
    Button yes,no;

    DBHelper dbHelper;

    String carinput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        dbHelper = new DBHelper(this);


        car = findViewById(R.id.car);
        owner = findViewById(R.id.owner);
        type = findViewById(R.id.type);
        reg = findViewById(R.id.reg);
        ins = findViewById(R.id.insurance);
        pol = findViewById(R.id.pollution);

        yes = findViewById(R.id.yes_button);
        no = findViewById(R.id.no_button);

        carinput = getIntent().getExtras().getString("car");
        Cursor cursor = dbHelper.displaySingleByCar(carinput);
        if (cursor.moveToNext()) {
            car.setText(cursor.getString(1));
            owner.setText(cursor.getString(2));
            type.setText(cursor.getString(3));
            reg.setText(cursor.getString(4));
            ins.setText(cursor.getString(5));
            pol.setText(cursor.getString(6));
        }
        else {
            Toast.makeText(getApplicationContext(),"No Record Found",Toast.LENGTH_SHORT).show();

        }

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.deletebycar(carinput);
                Toast.makeText(getApplicationContext(),"Data deleted successfully",Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
