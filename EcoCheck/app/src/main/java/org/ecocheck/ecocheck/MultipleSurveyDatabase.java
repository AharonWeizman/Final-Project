package org.ecocheck.ecocheck.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.Toast;

import org.ecocheck.ecocheck.app.App;
import org.ecocheck.ecocheck.dto.*;
import org.ecocheck.ecocheck.dto.Process;

import java.util.ArrayList;

/**
 * Created by Ron on 21/03/2016.

 */
public class MultipleSurveyDatabase  extends SQLiteOpenHelper
{
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 43;

    // Database Name
    private static final String DATABASE_NAME = "surveyDBMultiTbl.db";

    private static final String
            TAG = MultipleSurveyDatabase.class.getSimpleName().toString();


    public MultipleSurveyDatabase( )
    {
        super(App.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(RecommendationDatabase.createTable());
        // create the data base we create in VisitDataBase class
        db.execSQL(VisitDataBase.createTable());

        db.execSQL(ProcesDatabase.createTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.d(TAG, String.format("SQLiteDatabase.onUpgrade(%d -> %d)",
                oldVersion, newVersion));

        // Drop table if existed, all data will be gone!!!
        // create from Visits class
        db.execSQL("DROP TABLE IF EXISTS " + Visits.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Recommendation.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Process.TABLE);

        onCreate(db);
    }
    //method that get when we want to find specific data
    public ArrayList<Visits> getData
    (
            String report_number,
            String factory_name,
            String visit_date,
            String laboratory,
            String sample,
            String comments,
            CheckBox bConformed)
    {

        String conformed = "false";
        //check if confirm check box is checked
        if (bConformed.isChecked()) conformed = "true";

        SQLiteDatabase db = this.getWritableDatabase();

        // builing a SQLite statement with checking if the field is not empty
        int qModules = 0;
        String selectQuery = " select * from "+Visits.TABLE+" where ";
        if (factory_name.length() != 0)
        {
            factory_name = "%" + factory_name + "%";

            selectQuery +=
                    Visits.KEY_Name+" like '" + factory_name;

            qModules++;
        }
        if (visit_date.length() != 0)
        {
            visit_date = "%" + visit_date + "%";

            if (qModules == 0)
            {
                selectQuery +=
                        Visits.VISIT_COLUMN_VISIT_DATE+" like '" + visit_date;
            }
            else
            {
                selectQuery +=
                        "' and "+Visits.VISIT_COLUMN_VISIT_DATE+" like '" + visit_date;
            }

            qModules++;
        }
        if (report_number.length() != 0)
        {
            report_number = "%" + report_number + "%";

            if (qModules == 0)
            {
                selectQuery +=
                        Visits.KEY_ReportId+" like '" + report_number;
            }
            else
            {
                selectQuery +=
                        "' and "+Visits.KEY_ReportId+" like '" + report_number;
            }

            qModules++;
        }
        if (sample.length() != 0)
        {
            sample = "%" + sample + "%";

            if (qModules == 0)
            {
                selectQuery +=
                        Visits.VISIT_COLUMN_VISIT_SAMPLE+" like '" + sample;
            }
            else
            {
                selectQuery +=
                        "' and "+Visits.VISIT_COLUMN_VISIT_SAMPLE+" like '" + sample;
            }

            qModules++;
        }
        if (laboratory.length() != 0)
        {
            laboratory = "%" + laboratory + "%";

            if (qModules == 0)
            {
                selectQuery +=
                        Visits.VISIT_COLUMN_VISIT_LABORATORY+" like '" + laboratory;
            }
            else
            {
                selectQuery +=
                        "' and "+Visits.VISIT_COLUMN_VISIT_LABORATORY+" like '" + laboratory;
            }

            qModules++;
        }
        if (comments.length() != 0)
        {
            comments = "%" + comments + "%";

            if (qModules == 0)
            {
                selectQuery +=
                        Visits.VISIT_COLUMN_VISIT_COMMENTS+" like '" + comments;
            }
            else
            {
                selectQuery +=
                        "' and "+Visits.VISIT_COLUMN_VISIT_COMMENTS+" like '" + comments;
            }

            qModules++;
        }
        if (conformed.length() != 0 && conformed.compareTo("true") == 0)
        {
            conformed = "%" + conformed + "%";

            if (qModules == 0)
            {
                selectQuery +=
                        Visits.VISIT_COLUMN_VISIT_CONFORMED+" like '" + conformed;
            }
            else
            {
                selectQuery +=
                        "' and "+Visits.VISIT_COLUMN_VISIT_CONFORMED+" like '" + conformed;
            }

            qModules++;
        }
        selectQuery +="'";


        // performing a query of database
        Cursor cursor;
        //make factory list
        ArrayList<Visits> visitses = new ArrayList<Visits>();

        if (qModules != 0)
        {
            cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    //make factory object
                    Visits visits = new Visits();

                    visits.setFactory(cursor.getString(
                            cursor.getColumnIndex(Visits.KEY_Name)));
                    visits.setVisitDate(cursor.getString(
                            cursor.getColumnIndex(Visits.VISIT_COLUMN_VISIT_DATE)));

                    String reportId = cursor.getString(
                            cursor.getColumnIndex(Visits.KEY_ReportId));
                    visits.setReportNumber(Integer.parseInt(reportId));

                    visits.setLaboratory(cursor.getInt(
                            cursor.getColumnIndex(Visits.VISIT_COLUMN_VISIT_LABORATORY)));
                    visits.setSample(cursor.getString(
                            cursor.getColumnIndex(Visits.VISIT_COLUMN_VISIT_SAMPLE)));
                    visits.setComments(cursor.getString(
                            cursor.getColumnIndex(Visits.VISIT_COLUMN_VISIT_COMMENTS)));

                    // Strings "true/false" for the check box
                    String checkBox = cursor.getString(
                            cursor.getColumnIndex(Visits.VISIT_COLUMN_VISIT_CONFORMED));


                    if (checkBox.compareTo("true") == 0) {
                        visits.setConfirmed(true);
                    } else {
                        visits.setConfirmed(false);
                    }

                    //add to factory list
                    visitses.add(visits);

                } while (cursor.moveToNext());
            }
        }

        return visitses;
    }

    // method that get all the visit data list factory
    public ArrayList<Visits> getAllData()
    {
        SQLiteDatabase
                db = DatabaseMultipleManager.getInstance().openDatabase();

        //* mean take all the data
        Cursor
                cursor = db.rawQuery("select * from " + Visits.TABLE, null);

        //make factory list
        ArrayList<Visits> visitses = new ArrayList<Visits>();

        if (cursor.moveToFirst()) {
            do
            {
                //make factory object
                Visits visits = new Visits();
                //set the values
                visits.setReportNumber(cursor.getInt(
                        cursor.getColumnIndex(Visits.KEY_ReportId)));
                visits.setFactory(cursor.getString(
                        cursor.getColumnIndex(Visits.KEY_Name)));
                visits.setVisitDate(cursor.getString(
                        cursor.getColumnIndex(Visits.VISIT_COLUMN_VISIT_DATE)));
                visits.setLaboratory(cursor.getInt(
                        cursor.getColumnIndex(Visits.VISIT_COLUMN_VISIT_LABORATORY)));
                visits.setSample(cursor.getString(
                        cursor.getColumnIndex(Visits.VISIT_COLUMN_VISIT_SAMPLE)));
                visits.setComments(cursor.getString(
                        cursor.getColumnIndex(Visits.VISIT_COLUMN_VISIT_COMMENTS)));

                //  Strings "true/false" for the boolean checkbox
                String checkBox = cursor.getString(
                        cursor.getColumnIndex(Visits.VISIT_COLUMN_VISIT_CONFORMED));

                // check if confirm was clicked
                if (checkBox.compareTo("true") == 0)
                {
                    visits.setConfirmed(true);
                }
                else
                {
                    visits.setConfirmed(false);
                }

                //add to visits list
                visitses.add(visits);
            }
            while (cursor.moveToNext());
        }
        return visitses;
    }
}
