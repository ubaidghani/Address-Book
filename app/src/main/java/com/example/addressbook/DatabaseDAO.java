package com.example.addressbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class DatabaseDAO extends SQLiteOpenHelper {

    public DatabaseDAO(@Nullable Context context) {
        super(context, "customer.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createCustomerTable = "CREATE TABLE CUSTOMER_TABLE (ID INTEGER PRIMARY KEY AUTOINCREMENT, CUSTOMER_FIRST_NAME TEXT, CUSTOMER_LAST_NAME TEXT, CUSTOMER_AGE INT, CUSTOMER_EMAIL TEXT, CUSTOMER_MOBILE_NO LONG)";
        sqLiteDatabase.execSQL(createCustomerTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addEmployee(ContactInfo contactInfo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("CUSTOMER_FIRST_NAME", contactInfo.getFirstName());
        cv.put("CUSTOMER_LAST_NAME", contactInfo.getLastName());
        cv.put("CUSTOMER_AGE", contactInfo.getAge());
        cv.put("CUSTOMER_EMAIL", contactInfo.getEmail());
        cv.put("CUSTOMER_MOBILE_NO", contactInfo.getMobileNo());

        long insert = db.insert("CUSTOMER_TABLE",null,cv);
        if(insert==-1){
            return false;
        }else{
            return true;
        }

    }




    public ContactInfo getContactInfo(int uid){

        String query = "SELECT * FROM CUSTOMER_TABLE WHERE ID = " + uid;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        ContactInfo contactInfo = null;
        if(cursor.moveToFirst()){
                int id = cursor.getInt(0);
                String firstName = cursor.getString(1);
                String lastName = cursor.getString(2);
                int age = cursor.getInt(3);
                String email = cursor.getString(4) ;
                int mobileNo = cursor.getInt(5);

                contactInfo = new ContactInfo(id,firstName,lastName,age,email,mobileNo);

            }
        cursor.close();
        db.close();
        return contactInfo;
    }



    public int updateContact(ContactInfo contactInfo,int id) {

        ContentValues values = new ContentValues();
        values.put("CUSTOMER_FIRST_NAME", contactInfo.getFirstName());
        values.put("CUSTOMER_LAST_NAME", contactInfo.getLastName());
        values.put("CUSTOMER_AGE", contactInfo.getAge());
        values.put("CUSTOMER_EMAIL", contactInfo.getEmail());
        values.put("CUSTOMER_MOBILE_NO", contactInfo.getMobileNo());
        String selection =  id + " LIKE ?";
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            return db.update("CUSTOMER_TABLE", values, selection, null);
        }catch (Exception e){
            Log.e("SQLite Exception", e.getMessage());
            return -1;
        }

    }


    public List<ContactInfo> getEmployees(){

        List<ContactInfo> list = new ArrayList<>();
        String query = "SELECT * FROM CUSTOMER_TABLE";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String firstName = cursor.getString(1);
                String lastName = cursor.getString(2);
                int age = cursor.getInt(3);
                String email = cursor.getString(4) ;
                int mobileNo = cursor.getInt(5);

                ContactInfo contactInfo = new ContactInfo(id,firstName,lastName,age,email,mobileNo);
                list.add(contactInfo);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }
    public boolean deleteEmployee(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM CUSTOMER_TABLE WHERE ID = " + id;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            return true;
        }else {
            return false;
        }
    }



}
