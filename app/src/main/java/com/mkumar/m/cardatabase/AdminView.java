package com.mkumar.m.cardatabase;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdminView extends AppCompatActivity {

    TextView car,type,reg;
    TextView owner,ins,pol;

    String cardb,ownerdb,typedb,regdb,insdb,poldb;

    Button back;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view);

        dbHelper = new DBHelper(this);


        car = findViewById(R.id.car);
        owner = findViewById(R.id.owner);
        type = findViewById(R.id.type);
        reg = findViewById(R.id.reg);
        ins = findViewById(R.id.insurance);
        pol = findViewById(R.id.pollution);

        back = findViewById(R.id.back_button);

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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
