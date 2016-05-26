package org.ecocheck.ecocheck;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ron on 02/01/2016.
 * this is the data base class to the factory information
 */
public class DataBaseFactory extends SQLiteOpenHelper
{
    //all the table add line data
    public static final String DATABASE_NAME = "FactoryDB.db";//the data base name
    public static  final String TABLE_NAME = "factory_info";// the name of the table
    public static final String FACTORY_COLUMN_AFIK = "id";
    public static final String FACTORY_COLUMN_FACTORY = "factory";
    public static final String FACTORY_COLUMN_BRANCH = "branch";
    public static final String FACTORY_COLUMN_CITY = "city";
    public static final String FACTORY_COLUMN_ADDRESS = "address";
    public static final String FACTORY_COLUMN_PHONE = "phone";
    public static final String FACTORY_COLUMN_FAX = "fax";
    public static final String FACTORY_COLUMN_CONTACT = "contact_person";
    public static final String FACTORY_COLUMN_MAILING_ADDRESS = "mailing_address";
    public static final String FACTORY_COLUMN_EMPLOYEE_NUMBER = "employee_number";
    public static final String FACTORY_COLUMN_INSPECTOR = "inspector";

    //constructor
    public DataBaseFactory(Context context)
    {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    //to create the table(factory_info)
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table " + TABLE_NAME + " (id integer primary key,factory text, branch text,city text,address text, phone text,fax text,contact_person text,mailing_address text,employee_number text,inspector text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2)
    {
        db.execSQL("DROP TABLE IF EXIST "+ TABLE_NAME);
        //call the method on create
        onCreate(db);
    }

    //method to insert the factory data
    public  boolean insertFactoryData(String afik,String factory, String branch, String city,String address,String phone,String fax,String contact, String mailing_adress,String emloyee_number, String inspector)
    {
        SQLiteDatabase db= this.getWritableDatabase();// create the data base
        ContentValues contentValues= new ContentValues();//this is import class

        contentValues.put(FACTORY_COLUMN_AFIK,afik);//check if neasesery
        contentValues.put(FACTORY_COLUMN_FACTORY,factory);
        contentValues.put(FACTORY_COLUMN_BRANCH,branch);
        contentValues.put(FACTORY_COLUMN_CITY,city);
        contentValues.put(FACTORY_COLUMN_ADDRESS,address);
        contentValues.put(FACTORY_COLUMN_PHONE,phone);
        contentValues.put(FACTORY_COLUMN_FAX,fax);
        contentValues.put(FACTORY_COLUMN_CONTACT,contact);
        contentValues.put(FACTORY_COLUMN_MAILING_ADDRESS,mailing_adress);
        contentValues.put(FACTORY_COLUMN_EMPLOYEE_NUMBER,emloyee_number);
        contentValues.put(FACTORY_COLUMN_INSPECTOR,inspector);

        //check if the data was insert
        long result=  db.insert(TABLE_NAME,null,contentValues);
        if(result == -1)
        {
            return false;
        }
        else
            return true;

    }

    // method that give me all the data
    public Cursor getAllData()
    {
        //TODO check if instead select from Table_name tkae only afik the Primery Key
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from "+ TABLE_NAME,null);//* mean take all the data
        return res;// instance of Cursor android studio dataBase Method
    }

    //method to update data
    public boolean updateData(Integer id,String factory, String branch, String city,String address,String phone,String fax,String contact, String mailing_address,String employee_number, String inspector)
    {
        SQLiteDatabase db= this.getWritableDatabase();// create the data base
        ContentValues contentValues= new ContentValues();//this is import class

        contentValues.put(FACTORY_COLUMN_FACTORY,factory);
        contentValues.put(FACTORY_COLUMN_BRANCH,branch);
        contentValues.put(FACTORY_COLUMN_CITY,city);
        contentValues.put(FACTORY_COLUMN_ADDRESS,address);
        contentValues.put(FACTORY_COLUMN_PHONE,phone);
        contentValues.put(FACTORY_COLUMN_FAX,fax);
        contentValues.put(FACTORY_COLUMN_CONTACT,contact);
        contentValues.put(FACTORY_COLUMN_MAILING_ADDRESS,mailing_address);
        contentValues.put(FACTORY_COLUMN_EMPLOYEE_NUMBER,employee_number);
        contentValues.put(FACTORY_COLUMN_INSPECTOR,inspector);

        db.update(TABLE_NAME, contentValues, "id = ?", new String[] { Integer.toString(id) });
        // to know if the data was update
        return true;
    }

    //method that delete data
    public Integer deleteData(String id)
    {
        //TODO try difrent wat to delet data
        //we delete the data a cording the id argument
        SQLiteDatabase db= this.getWritableDatabase();// create the data base
        //android studio delete function of SQLiteDatabase
        return db.delete(TABLE_NAME, "id= ?",new String[] {id});//"ID= ?" is the way to ask if there is id that was delete
    }

}
