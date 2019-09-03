package com.mkumar.m.cardatabase;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Edit extends AppCompatActivity {

    TextView car,type,reg;
    EditText owner,ins,pol;

    String cardb,ownerdb,typedb,regdb,insdb,poldb;

    Button update;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        dbHelper = new DBHelper(this);


        car = findViewById(R.id.car);
        owner = findViewById(R.id.owner);
        type = findViewById(R.id.type);
        reg = findViewById(R.id.reg);
        ins = findViewById(R.id.insurance);
        pol = findViewById(R.id.pollution);

        update = findViewById(R.id.update);

        final String carinput = getIntent().getExtras().getString("car");
        Cursor cursor = dbHelper.displaySingleByCar(carinput);
        if (cursor.moveToNext()) {
            cardb = cursor.getString(1);
            ownerdb = cursor.getString(2);
            typedb = cursor.getString(3);
            regdb = cursor.getString(4);
            insdb = cursor.getString(5);
            poldb = cursor.getString(6);
        }

        car.setText(cardb);
        owner.setText(ownerdb);
        type.setText(typedb);
        reg.setText(regdb);
        ins.setText(insdb);
        pol.setText(poldb);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ownerupdate = owner.getText().toString();
                String insupdate = ins.getText().toString();
                String poldata = pol.getText().toString();

                boolean result = dbHelper.update(cardb,ownerupdate,typedb,regdb,insupdate,poldata);

                if (result) {
                    Toast.makeText(getApplicationContext(),"Data Updated Successfully",Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Error updating data",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
