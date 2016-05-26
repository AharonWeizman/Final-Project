package org.ecocheck.ecocheck.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import org.ecocheck.ecocheck.dto.Factory;
import org.ecocheck.ecocheck.dto.Visits;

import java.util.ArrayList;

/**
 * Created by Ron on 19/03/2016.
 */
public class VisitDataBase {
    private Visits visits;//field from Visits class


    //constructor
    public VisitDataBase() {
        visits = new Visits();
    }


    // create the table according visits.java class table that i create
    //TODO check if is ok
    public static String createTable() {
        return "CREATE TABLE " + Visits.TABLE + "("
                + Visits.KEY_ReportId + " PRIMARY KEY,"
                + Visits.KEY_Name + " TEXT, "
                + Visits.VISIT_COLUMN_VISIT_DATE + " TEXT, "
                + Visits.VISIT_COLUMN_VISIT_LABORATORY + " TEXT, "
                + Visits.VISIT_COLUMN_VISIT_SAMPLE + " TEXT, "
                + Visits.VISIT_COLUMN_VISIT_COMMENTS + " TEXT, "
                + Visits.VISIT_COLUMN_VISIT_CONFORMED + " TEXT )";

    }

    // insert the data put in the database
    public boolean insertVisitData(Visits visits)
    {
        int visitId;
        SQLiteDatabase db = DatabaseMultipleManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Visits.KEY_ReportId, visits.getReportNumber());
        values.put(Visits.KEY_Name, visits.getFactory());
        values.put(visits.VISIT_COLUMN_VISIT_DATE, visits.getVisitDate());
        values.put(visits.VISIT_COLUMN_VISIT_LABORATORY, visits.getLaboratory());
        values.put(visits.VISIT_COLUMN_VISIT_SAMPLE, visits.getSample());
        values.put(visits.VISIT_COLUMN_VISIT_COMMENTS, visits.getComments());
        values.put(visits.VISIT_COLUMN_VISIT_CONFORMED, visits.isConfirmed());



        //check if the data was insert
        long result=  db.insert(Visits.TABLE,null,values);
        DatabaseMultipleManager.getInstance().closeDatabase();
        if(result == -1)
        {
            return false;
        }
        else
            return true;


    }


    public ArrayList<Visits> getAllData() {
        SQLiteDatabase db = DatabaseMultipleManager.getInstance().openDatabase();

        Cursor cursor = db.rawQuery("select * from " + Visits.TABLE, null);//* mean take all the data

        //make factory list
        ArrayList<Visits> visitses = new ArrayList<Visits>();

        if (cursor.moveToFirst()) {
            do {
                //make factory object
                Visits visits = new Visits();
                //set the values
                visits.setReportNumber(cursor.getInt(cursor.getColumnIndex(Visits.KEY_ReportId)));
                visits.setFactory(cursor.getString(cursor.getColumnIndex(Visits.KEY_Name)));
                visits.setVisitDate(cursor.getString(cursor.getColumnIndex(Visits.VISIT_COLUMN_VISIT_DATE)));
                visits.setLaboratory(cursor.getInt(cursor.getColumnIndex(Visits.VISIT_COLUMN_VISIT_LABORATORY)));
                visits.setSample(cursor.getString(cursor.getColumnIndex(Visits.VISIT_COLUMN_VISIT_SAMPLE)));
                visits.setComments(cursor.getString(cursor.getColumnIndex(Visits.VISIT_COLUMN_VISIT_COMMENTS)));
                visits.setConfirmed(cursor.isNull(cursor.getColumnIndex(Visits.VISIT_COLUMN_VISIT_CONFORMED)));// check if ok

                //add to visits list
                visitses.add(visits);
            } while (cursor.moveToNext());
        }
        return visitses;
    }

    public ArrayList<Visits> getData(int report_number, String factory_name, int visit_date, int laboratory, String comments, boolean conformed)
    {
        SQLiteDatabase db = DatabaseMultipleManager.getInstance().openDatabase();
        Cursor cursor = db.query(Visits.TABLE, new String[]{Visits.KEY_ReportId, Visits.KEY_Name, Visits.VISIT_COLUMN_VISIT_DATE, Visits.VISIT_COLUMN_VISIT_LABORATORY, Visits.VISIT_COLUMN_VISIT_SAMPLE, Visits.VISIT_COLUMN_VISIT_COMMENTS, Visits.VISIT_COLUMN_VISIT_CONFORMED},
                Visits.KEY_ReportId + " LIKE ? and " + Visits.KEY_Name + " LIKE ? and " + Visits.VISIT_COLUMN_VISIT_DATE + " LIKE ? and " + Visits.VISIT_COLUMN_VISIT_LABORATORY + " LIKE ? and " + Visits.VISIT_COLUMN_VISIT_SAMPLE + " LIKE ? and " + Visits.VISIT_COLUMN_VISIT_COMMENTS + " LIKE ? and " + Visits.VISIT_COLUMN_VISIT_CONFORMED + " LIKE ?"
                , new String[]{"%" + report_number + "%", "%" + factory_name + "%", "%" + visit_date + "%", "%" + laboratory + "%", "%" + comments + "%", "%" + conformed + "%"}, null, null, null);
        //make factory list
        ArrayList<Visits> visitses = new ArrayList<Visits>();



        if (cursor.moveToFirst()) {
            do {
                //make factory object
                Visits visits = new Visits();


                visits.setFactory(cursor.getString(cursor.getColumnIndex(Visits.KEY_Name)));
                visits.setVisitDate(cursor.getString(cursor.getColumnIndex(Visits.VISIT_COLUMN_VISIT_DATE)));
                visits.setLaboratory(cursor.getInt(cursor.getColumnIndex(Visits.VISIT_COLUMN_VISIT_LABORATORY)));
                visits.setSample(cursor.getString(cursor.getColumnIndex(Visits.VISIT_COLUMN_VISIT_SAMPLE)));
                visits.setComments(cursor.getString(cursor.getColumnIndex(Visits.VISIT_COLUMN_VISIT_COMMENTS)));
                visits.setConfirmed(cursor.isNull(cursor.getColumnIndex(Visits.VISIT_COLUMN_VISIT_CONFORMED)));

                //add to factory list
                visitses.add(visits);

            } while (cursor.moveToNext());
        }

        return visitses;

    }

    public boolean updateData(Visits visits)
    {


        SQLiteDatabase db = DatabaseMultipleManager.getInstance().openDatabase();// create the data base
        ContentValues contentValues = new ContentValues();//this is import class
        contentValues.put(visits.KEY_ReportId, visits.getReportNumber());
        contentValues.put(visits.KEY_Name, visits.getFactory());
        contentValues.put(Visits.VISIT_COLUMN_VISIT_DATE,visits.getVisitDate());
        contentValues.put(Visits.VISIT_COLUMN_VISIT_LABORATORY,visits.getLaboratory());
        contentValues.put(Visits.VISIT_COLUMN_VISIT_SAMPLE,visits.getSample());
        contentValues.put(Visits.VISIT_COLUMN_VISIT_COMMENTS,visits.getComments());
        contentValues.put(Visits.VISIT_COLUMN_VISIT_CONFORMED,visits.isConfirmed());

        db.update(Visits.TABLE, contentValues, "id = ?", new String[]{Integer.toString(visits.getReportNumber())});//upsate acording to report number
        // to know if the data was update
        return true;

    }

    //method that delete data
    public Integer deleteData(String id)
    {
        //TODO try difrent wat to delet data
        //we delete the data a cording the id argument
        SQLiteDatabase db= DatabaseMultipleManager.getInstance().openDatabase();// create the data base
        //android studio delete function of SQLiteDatabase
        return db.delete(Visits.TABLE, "id= ?",new String[] {id});//"ID= ?" is the way to ask if there is id that was delete
    }


}
