package com.mkumar.m.cardatabase;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText user,pass;
    Spinner spinner;
    Button login;
    TextView signup;

    DatabaseConnect databaseConnect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        spinner = findViewById(R.id.spinner);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);

        databaseConnect = new DatabaseConnect(this);

        String[] arr = {"Admin","User"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,arr);
        spinner.setAdapter(arrayAdapter);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int choice = spinner.getSelectedItemPosition();

                if(choice == 0) {
                    if(String.valueOf(user.getText()).equals("Admin")) {
                        if(String.valueOf(pass.getText()).equals("1234")) {
                            Intent intent = new Intent(MainActivity.this,AdminHome.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Toast.makeText(MainActivity.this,"Password is Incorrect",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(MainActivity.this,"Incorrect Username",Toast.LENGTH_SHORT).show();
                    }
                }

                if (choice == 1){
                    Cursor cursor = databaseConnect.displaySingleByUnamePassword(String.valueOf(user.getText()),String.valueOf(pass.getText()));
                    if (cursor.moveToNext()) {
                        Intent intent = new Intent(getApplicationContext(),UserView.class);
                        intent.putExtra("username",String.valueOf(user.getText()));
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Incorrect Username/Password",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signup.setClickable(true);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),UserRegistration.class);
                startActivity(intent);
            }
        });


    }
}
