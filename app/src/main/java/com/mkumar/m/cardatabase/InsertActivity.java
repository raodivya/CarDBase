package com.mkumar.m.cardatabase;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {

    EditText car,owner,type,regno,insurance,pollution;
    Button submit;
    DBHelper dbHelper;

    String carnodata,ownerdata,typedata,regdatedata,insurancedata,pollutiondata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        dbHelper = new DBHelper(this);

        car = findViewById(R.id.carno);
        owner = findViewById(R.id.owner);
        type = findViewById(R.id.Type);
        regno = findViewById(R.id.regno);
        insurance = findViewById(R.id.insurance);
        pollution = findViewById(R.id.pollution);



        submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carnodata = car.getText().toString();
                ownerdata = owner.getText().toString();
                typedata = type.getText().toString();
                regdatedata = regno.getText().toString();
                insurancedata = insurance.getText().toString();
                pollutiondata = pollution.getText().toString();

                Cursor cursor = dbHelper.displaySingleByCar(carnodata);

                if (cursor.moveToNext()) {
                    Toast.makeText(getApplicationContext(),"Car Number already in database",Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean result = dbHelper.insert(carnodata, ownerdata, typedata, regdatedata, insurancedata, pollutiondata);
                    Toast.makeText(InsertActivity.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });


    }

}
