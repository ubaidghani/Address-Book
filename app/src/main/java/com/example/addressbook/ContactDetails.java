package com.example.addressbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ContactDetails extends AppCompatActivity {
    private TextView back,edit,fname,lname,age,email,mobile;
    int id,agee,mobileNo;
    String firstname,lastname,Email;
    private Button delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        back = (TextView) findViewById(R.id.back);
        edit = (TextView) findViewById(R.id.edit);
        fname = (TextView) findViewById(R.id.firstName);
        lname = (TextView) findViewById(R.id.lastName);
        age = (TextView) findViewById(R.id.age);
        email = (TextView) findViewById(R.id.email);
        mobile = (TextView) findViewById(R.id.mobile);
        delete = (Button)findViewById(R.id.delete);

        int uid = getIntent().getExtras().getInt("id");
        uid = uid +1;
        DatabaseDAO databaseDAO = new DatabaseDAO(ContactDetails.this);
        ContactInfo contactInfo = databaseDAO.getContactInfo(uid);

        //ContactInfo contactInfo = new ContactInfo(id,firstname,lastname,agee,Email,mobileNo);
        id = contactInfo.getId();
        firstname = contactInfo.getFirstName();
        lastname = contactInfo.getLastName();
        agee = contactInfo.getAge();
        Email = contactInfo.getEmail();
        mobileNo = contactInfo.getMobileNo();
        fname.setText("First Name : " + firstname);
        lname.setText("Last Name : "+lastname);
        age.setText("Age : "  + String.valueOf(agee));
        email.setText("Email : "+Email);
        mobile.setText("Mobile No : " + String.valueOf(mobileNo));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactDetails.this,MainActivity.class);
                startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseDAO databaseDAO = new DatabaseDAO(ContactDetails.this);
                databaseDAO.deleteEmployee(id);
                Toast.makeText(ContactDetails.this,"Deleted Contact Successfully" ,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ContactDetails.this,MainActivity.class);
                startActivity(intent);
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactDetails.this,UpdateContact.class);
                intent.putExtra("fname" , firstname);
                intent.putExtra("lname",lastname);
                intent.putExtra("age",agee);
                intent.putExtra("Email",Email);
                intent.putExtra("mobile", mobileNo);
                intent.putExtra("id",id);
                startActivity(intent);

            }
        });






    }
}
