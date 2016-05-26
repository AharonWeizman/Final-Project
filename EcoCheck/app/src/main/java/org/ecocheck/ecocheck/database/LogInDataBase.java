package org.ecocheck.ecocheck;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ron on 12/01/2016.
 * this class is for the data base log in and Sign Up
 */
public class LogInDataBase extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="contacts.db";// the data base name
    private static final String TABLE_NAME="contacts";// the data base table name
    private static final String COLUMN_ID="id";// the first column id
    private static final String COLUMN_NAME="name";//the column name
    private static final String COLUMN_EMAIL="email";
    private static final String COLUMN_UNAME="uname";
    private static final String COLUMN_PASS="pass";
    SQLiteDatabase db;// db is afield of SQLiteDataBase Android extension class

    //define the data base data that the user need to enter
    private static final String TABLE_CREATE= "create table contacts (id integer primary key not null , " +
            "name text not null , email text not null , uname text not null , pass text not null);";
    // constructor
    public LogInDataBase(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    // on create creating the data base
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL(TABLE_CREATE);
        this.db=sqLiteDatabase;
    }

    // method that put the data into the data base
    public void insertContact(Contact c)
    {
        db=this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from contacts";
        Cursor cursor= db.rawQuery(query,null);
        int count=cursor.getCount();

        values.put(COLUMN_ID,count);
        values.put(COLUMN_NAME,c.getName());
        values.put(COLUMN_EMAIL, c.getEmail());
        values.put(COLUMN_UNAME,c.getUserName());
        values.put(COLUMN_PASS, c.getPass());

        db.insert(TABLE_NAME, null, values);
        db.close();

    }

    // method that check if the user name and the passWord is match in the user Log In
    public String searchPass(String userName)
    {
        db= this.getReadableDatabase();
        String query = "select uname, pass from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String UserName,PassWord;
        PassWord="not found";

        if(cursor.moveToFirst())
        {
            do
            {
                UserName=cursor.getString(0);// 0 remainng to UserName

                // check if the UserName LogIn is match according to the SignUp
                if(UserName.equals(userName))
                {
                    PassWord=cursor.getString(1);//1 reference to password
                    break;
                }
            }
            while (cursor.moveToNext());

        }
        return PassWord;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        String query= "DROP TABLE IF EXISTS"+ TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
