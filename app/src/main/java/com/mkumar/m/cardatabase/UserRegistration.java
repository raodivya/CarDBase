package com.mkumar.m.cardatabase;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class UserRegistration extends AppCompatActivity {

    EditText username,phone,password;
    RadioButton male,female;
    Button submit;
    DatabaseConnect databaseConnect;

    String uname,phoneno,gender,passwd;

    TextView signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        username = findViewById(R.id.uname);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);

        male = findViewById(R.id.male);
        female = findViewById(R.id.female);

        male.isChecked();

        submit = findViewById(R.id.submit);

        databaseConnect = new DatabaseConnect(this);


        signin = findViewById(R.id.signin);
        signin.setClickable(true);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uname = username.getText().toString();
                phoneno = phone.getText().toString();
                passwd = password.getText().toString();

                if (male.isChecked()) {
                    gender = "Male";
                }
                else {
                    gender = "Female";
                }

                Cursor cursor = databaseConnect.displaySingleByUname(uname);

                if (cursor.moveToNext()) {
                    Toast.makeText(getApplicationContext(),"Username not available",Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean result = databaseConnect.insert(uname,phoneno,gender,passwd);
                    if (result) {
                        Toast.makeText(getApplicationContext(),"Data inserted successfully",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"User registration failed.",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });




    }
}
