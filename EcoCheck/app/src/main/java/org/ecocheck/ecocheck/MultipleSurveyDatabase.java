package org.ecocheck.ecocheck.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.ecocheck.ecocheck.app.App;
import org.ecocheck.ecocheck.dto.Visits;

/**
 * Created by Ron on 21/03/2016.
 * this class hold the multiple and connected the database together
 */
public class MultipleSurveyDatabase  extends SQLiteOpenHelper
{
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION =8;
    // Database Name
    private static final String DATABASE_NAME = "surveyDBMultiTbl.db";// surveyDBMultiTbl.db is the name of multiple survey database
    private static final String TAG = MultipleSurveyDatabase.class.getSimpleName().toString();

    //constructor
    public MultipleSurveyDatabase( )
    {
        super(App.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(VisitDataBase.createTable());// create the data base we create in VisitDataBase class
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, String.format("SQLiteDatabase.onUpgrade(%d -> %d)", oldVersion, newVersion));

        // Drop table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + Visits.TABLE);// create the visit database from Visits.java class

        onCreate(db);
    }
}
