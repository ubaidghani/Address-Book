package com.example.addressbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private EditText search;
    private ListView listView;
    FloatingActionButton addContact;
    ArrayAdapter arrayAdapter;
    DatabaseDAO databaseDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search = (EditText) findViewById(R.id.search);
        listView = (ListView)findViewById(R.id.contactList);
        addContact = (FloatingActionButton) findViewById(R.id.addContact);

        databaseDAO = new DatabaseDAO(MainActivity.this);

        employeesList();

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                (MainActivity.this).arrayAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddContactScreen.class);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,ContactDetails.class);
                intent.putExtra("id",i);
                startActivity(intent);
            }
        });
    }

    private void employeesList() {
        arrayAdapter = new ArrayAdapter<ContactInfo>(MainActivity.this,android.R.layout.simple_list_item_1,databaseDAO.getEmployees());
        listView.setAdapter(arrayAdapter);
    }
}
