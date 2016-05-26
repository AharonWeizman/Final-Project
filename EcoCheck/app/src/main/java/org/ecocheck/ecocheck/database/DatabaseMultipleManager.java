package org.ecocheck.ecocheck.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ron on 19/03/2016.
 * this class initialize the database open it and close it
 */
public class DatabaseMultipleManager
{
    private Integer mOpenCounter = 0;

    private static DatabaseMultipleManager  instance;
    private static SQLiteOpenHelper mDatabaseHelper;//mDatabaseHelper is field from SQLiteOpenHelper .java android class
    private SQLiteDatabase mDatabase;// mDatabase is field from SQLiteDatabase.java android class

    // initialize DatabaseMultipleManager with  SQLiteOpenHelper
    public static synchronized void initializeInstance(SQLiteOpenHelper helper)
    {
        if (instance == null)
        {
            instance = new DatabaseMultipleManager ();
            mDatabaseHelper = helper;
        }
    }


    public static synchronized DatabaseMultipleManager  getInstance() {
        if (instance == null)
        {
            throw new IllegalStateException(DatabaseMultipleManager.class.getSimpleName() +
                    " is not initialized, call initializeInstance(..) method first.");
        }

        return instance;
    }

    // open database
    public synchronized SQLiteDatabase openDatabase() {
        mOpenCounter+=1;
        if(mOpenCounter == 1) {
            // Opening new database
            mDatabase = mDatabaseHelper.getWritableDatabase();
        }
        return mDatabase;
    }

    //close database
    public synchronized void closeDatabase() {
        mOpenCounter-=1;
        if(mOpenCounter == 0) {
            // Closing database
            mDatabase.close();

        }
    }
}
