package com.mkumar.m.cardatabase;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class UserView extends AppCompatActivity {

    EditText input;
    ImageButton submit;
    TextView car,owner,type,reg,ins,pol,username;

    String carinput;

    Button logout;

    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);

        dbHelper = new DBHelper(this);

        username = findViewById(R.id.user);
        username.setText(getIntent().getExtras().getString("username"));

        input = findViewById(R.id.input);

        logout = findViewById(R.id.logout);

        submit = findViewById(R.id.search);

        car = findViewById(R.id.car);
        owner = findViewById(R.id.owner);
        type = findViewById(R.id.type);
        reg = findViewById(R.id.reg);
        ins = findViewById(R.id.insurance);
        pol = findViewById(R.id.pollution);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carinput = String.valueOf(input.getText());
                Cursor cursor = dbHelper.displaySingleByCar(carinput);
                if (cursor.moveToNext()) {
                    car.setText("Car Number:" + cursor.getString(1));
                    owner.setText("Owner:" + cursor.getString(2));
                    type.setText("Type:" + cursor.getString(3));
                    reg.setText("Registration:" + cursor.getString(4));
                    ins.setText("Insurance:" + cursor.getString(5));
                    pol.setText("Pollution:" + cursor.getString(6));
                } else {

                    Toast.makeText(getApplicationContext(), "No record found", Toast.LENGTH_SHORT).show();
                }

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

