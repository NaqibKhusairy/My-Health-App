package com.example.myhealthapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class dbHandler extends SQLiteOpenHelper{
    //declare Const
    private static final String DBNAME="MyHealthDB";
    private static final int DBVERSION = 1;
    private static final String TABNAME="RegisterTable";
    private static final String COLID = "Id";
    private static final String COLNAME = "name";
    private static final String COLDOB = "dob";
    private static final String COLWEIGHT = "weight";
    private static final String COLHEIGHT = "height";
    private static final String COLGENDER = "jantina";
    private static final String COLMAIL = "email";
    private static final String COLPASS= "password";

    private static final String TABNAME2 = "BPRecord";
    private static final String COLID2 = "Id";
    private static final String COLDATE2 = "date";
    private static final String COLTIME2 = "time";
    private static final String COLSYSTOLIC2 = "systolic";
    private static final String COLDIASTOLIC2 = "diastolic";
    private static final String COLRESULT2 = "result";



    public dbHandler(@Nullable Context context) {
        super(context, DBNAME, null, DBVERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //create table
        String qry="CREATE TABLE "+TABNAME+" ("
                +COLID2+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +COLNAME+" TEXT (200) NOT NULL, "
                +COLDOB+" TEXT (200) NOT NULL, "
                +COLWEIGHT+" REAL NOT NULL, "
                +COLHEIGHT+" REAL NOT NULL, "
                +COLGENDER+" TEXT (10) NOT NULL, "
                +COLMAIL+" TEXT (100) NOT NULL, "
                +COLPASS+" TEXT (12) NOT NULL)";

        //create table 2
        String qry2="CREATE TABLE "+TABNAME2+" ("
                +COLID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +COLDATE2+" TEXT (200) NOT NULL,"
                +COLTIME2+" TEXT (200) NOT NULL, "
                +COLSYSTOLIC2+" TEXT (200) NOT NULL, "
                +COLDIASTOLIC2+" TEXT (200) NOT NULL, "
                +COLRESULT2+" TEXT (200) NOT NULL) ";

        //execute query
        db.execSQL(qry);
        db.execSQL(qry2);

    }

    public boolean newRegistration(String nama,String dob,double weight,double height,String jantina,String email, String password)
    {
        //make your db writable
        SQLiteDatabase db=this.getWritableDatabase();
        //crete obj for content value
        ContentValues val=new ContentValues();
        val.put(COLNAME,nama);
        val.put(COLDOB,dob);
        val.put(COLWEIGHT,weight);
        val.put(COLHEIGHT,height);
        val.put(COLGENDER,jantina);
        val.put(COLMAIL,email);
        val.put(COLPASS,password);
        //insert data - execute


        long stat=db.insert(TABNAME,null,val);
        if(stat==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public boolean bpData(String date,String time,String systolic,String diastolic,String result){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues val=new ContentValues();
        val.put(COLDATE2,date);
        val.put(COLTIME2,time);
        val.put(COLSYSTOLIC2,systolic);
        val.put(COLDIASTOLIC2,diastolic);
        val.put(COLRESULT2,result);

        long stat=db.insert(TABNAME2,null,val);
        if(stat==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean loginUser(String email, String pass){
        //open writables-to write data
        SQLiteDatabase db=this.getWritableDatabase();
        //query db for username
        Cursor cur=db.rawQuery("SELECT*FROM "+TABNAME+" WHERE " +COLMAIL+"=? AND "+COLPASS+"=?", new String[]{email,pass});
        //check result
        if(cur.getCount()>0){
            return true;
        }else{
            return false;
        }

    }


    public boolean checkUser(String mail){
        //make your db writable
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABNAME+" WHERE "+COLMAIL+" =? ",new String[]{mail});

        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer)
    {
        db.execSQL("DROP TABLE IF EXISTS "+TABNAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABNAME2);
        onCreate(db);
    }


    // reading all the directory.
    public ArrayList<blood> readDir() {

        // obj to read our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // creating a cursor with query to read data from table
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABNAME2, null);

        //create a new array list.
        ArrayList<blood> dirModalArrayList = new ArrayList<>();

        // move cursor to first position.
        if (cursor.moveToFirst()) {
            do {
                // adding the data from cursor to our array list.
                dirModalArrayList.add(new blood(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)));
            } while (cursor.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursor.close();
        return dirModalArrayList;
    }
    public int count() {

        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM "+TABNAME2;
        int recordCount = db.rawQuery(sql,
                null).getCount();
        db.close();
        return recordCount;

    }
    public ArrayList<userModel> getUserProfile(String email)
    {
        SQLiteDatabase myDB=this.getReadableDatabase();
        Cursor csr = myDB.rawQuery("SELECT * FROM "+TABNAME+" WHERE "+COLMAIL+"=?",new String[]{email});
        ArrayList<userModel> umArrayList = new ArrayList<>();

        //move cursor to 1st position
        if(csr.moveToFirst())
        {
            do {
                umArrayList.add(new userModel(csr.getInt(0),
                        csr.getString(1),
                        csr.getString(2),
                        csr.getDouble(3),
                        csr.getDouble(4),
                        csr.getString(5),
                        csr.getString(6),
                        csr.getString(7)));
            }while(csr.moveToNext());
        }
        csr.close();
        return umArrayList;
    }

}
