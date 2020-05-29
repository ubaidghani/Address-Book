package com.example.addressbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateContact extends AppCompatActivity {
    private EditText first,last,age,email,phone;
    private Button update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);


        String firstname = getIntent().getExtras().getString("fname");
        String lastname = getIntent().getExtras().getString("lname");
        int agee = getIntent().getExtras().getInt("age");
        String Email = getIntent().getExtras().getString("Email");
        int mobileNo = getIntent().getExtras().getInt("mobile");
        final int id = getIntent().getExtras().getInt("id");


        first = (EditText) findViewById(R.id.first);
        last = (EditText) findViewById(R.id.last);
        age = (EditText) findViewById(R.id.age);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        update = (Button) findViewById(R.id.update);

        first.setText(firstname);
        last.setText(lastname);
        age.setText(String.valueOf(agee));
        email.setText(Email);
        phone.setText(String.valueOf(mobileNo));

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int umar = Integer.parseInt(age.getText().toString());
                int mobilenumber = Integer.parseInt(phone.getText().toString());
                ContactInfo contact = new ContactInfo();
                contact.setId(id);
                contact.setFirstName(first.getText().toString());
                contact.setLastName(last.getText().toString());
                contact.setAge(umar);
                contact.setEmail(email.getText().toString());
                contact.setMobileNo(mobilenumber);

                DatabaseDAO db = new DatabaseDAO(getApplicationContext());
                db.updateContact(contact,id);
                finish();

                Intent intent = new Intent(UpdateContact.this,MainActivity.class);
                startActivity(intent);
                Toast.makeText(UpdateContact.this, "Sucessfull", Toast.LENGTH_LONG).show();
            }
        });


    }
}
