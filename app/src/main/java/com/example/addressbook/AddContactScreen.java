package com.example.addressbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddContactScreen extends AppCompatActivity {

    private TextView cancel;
    private EditText firstName,lastName,age,email,mobileNo;
    private Button save;
    ContactInfo contactInfo;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact_screen);

        cancel = (TextView)findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(AddContactScreen.this,MainActivity.class);
                startActivity(intent);
            }
        });

        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        age = (EditText) findViewById(R.id.age);
        email = (EditText) findViewById(R.id.email);
        mobileNo = (EditText) findViewById(R.id.mobileNo);
        save = (Button) findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    contactInfo = new ContactInfo(-1, firstName.getText().toString(), lastName.getText().toString(), Integer.parseInt(age.getText().toString()), email.getText().toString(),Integer.parseInt(mobileNo.getText().toString()));
                    Toast.makeText(AddContactScreen.this, contactInfo.toString(), Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    Toast.makeText(AddContactScreen.this, "ERROR!!!!!", Toast.LENGTH_LONG).show();
                }

                DatabaseDAO databaseDAO = new DatabaseDAO(AddContactScreen.this);
                boolean success = databaseDAO.addEmployee(contactInfo);
                Toast.makeText(AddContactScreen.this,"Success: " + success, Toast.LENGTH_LONG).show();
                //System.out.println(contactInfo);
                intent = new Intent(AddContactScreen.this,MainActivity.class);
                startActivity(intent);
            }
        });







    }
}
