package org.ecocheck.ecocheck.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.ecocheck.ecocheck.dto.Recommendation;

import java.util.ArrayList;

/**
 * Created by Ron on 04/04/2016.
 */
public class RecommendationDatabase
{
    private Recommendation recommendation;// recommendation is afield from Recommendation.java dto

    //constructor
    public RecommendationDatabase()
    {

        recommendation = new Recommendation();
    }
    //TODO check why tere is recommendation table create
    // create the recommendation table according recommendation class table that i create
    public static String createTable()
    {
        return "CREATE TABLE " + Recommendation.TABLE + "("
                + Recommendation.KEY_ReportId + " PRIMARY KEY,"
                + Recommendation.COLUMN_RECOMMENDATION + " TEXT )";
    }

    public boolean insertRecommendationData(Recommendation recommendation)
    {
        SQLiteDatabase db = DatabaseMultipleManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();

        values.put(Recommendation.KEY_ReportId, recommendation.getReportNumber());
        values.put(Recommendation.COLUMN_RECOMMENDATION, recommendation.getRecommendation());

        //check if the data was insert
        long result=  db.insert(Recommendation.TABLE,null,values);
        DatabaseMultipleManager.getInstance().closeDatabase();
        if(result == -1)
        {
            return false;
        }
        else
            return true;
    }

    // method that get all the data
    public ArrayList<Recommendation> getAllData()
    {
        SQLiteDatabase db = DatabaseMultipleManager.getInstance().openDatabase();

        Cursor cursor = db.rawQuery("select * from " + Recommendation.TABLE, null);//* mean take all the data

        //make factory list
        ArrayList<Recommendation> recommendations = new ArrayList<Recommendation>();

        if (cursor.moveToFirst()) {
            do {
                //make factory object
                Recommendation recommendation = new Recommendation();
                //set the values
                recommendation.setReportNumber(cursor.getInt(cursor.getColumnIndex(Recommendation.KEY_ReportId)));
                recommendation.setRecommendation(cursor.getString(cursor.getColumnIndex(Recommendation.COLUMN_RECOMMENDATION)));


                //add to Recommendation list
                recommendations.add(recommendation);
            } while (cursor.moveToNext());
        }
        return recommendations;
    }

    // method that get the data
    public ArrayList<Recommendation> getData(int report_number, String recomandtion)
    {
        SQLiteDatabase db = DatabaseMultipleManager.getInstance().openDatabase();
        Cursor cursor = db.query(Recommendation.TABLE, new String[]{Recommendation.KEY_ReportId, Recommendation.COLUMN_RECOMMENDATION},
                Recommendation.KEY_ReportId + " LIKE ? and " + Recommendation.COLUMN_RECOMMENDATION + " LIKE ?"
                , new String[]{"%" + report_number + "%", "%" + recomandtion + "%"}, null, null, null);
        //make factory list
        ArrayList<Recommendation> recommendations = new ArrayList<Recommendation>();



        if (cursor.moveToFirst())
        {
            do
            {
                //make factory object
                Recommendation recommendation = new Recommendation();


                recommendation.setReportNumber(cursor.getInt(cursor.getColumnIndex(Recommendation.KEY_ReportId)));
                recommendation.setRecommendation(cursor.getString(cursor.getColumnIndex(Recommendation.COLUMN_RECOMMENDATION)));

                //add to factory list
                recommendations.add(recommendation);

            } while (cursor.moveToNext());
        }

        return recommendations;

    }

    //method to update data
    public boolean updateData(Recommendation recommendation)
    {


        SQLiteDatabase db = DatabaseMultipleManager.getInstance().openDatabase();// create the data base
        ContentValues contentValues = new ContentValues();//this is import class
        contentValues.put(Recommendation.KEY_ReportId, recommendation.getReportNumber());
        contentValues.put(Recommendation.COLUMN_RECOMMENDATION, recommendation.getRecommendation());

        db.update(Recommendation.TABLE, contentValues, "id = ?", new String[]{Integer.toString(recommendation.getReportNumber())});//upsate acording to report number
        // to know if the data was update
        return true;
    }

    //method to delete data
    //method that delete data
    public Integer deleteData(String id)
    {
        //TODO try difrent wat to delet data
        //we delete the data a cording the id argument
        SQLiteDatabase db= DatabaseMultipleManager.getInstance().openDatabase();// create the data base
        //android studio delete function of SQLiteDatabase
        return db.delete(Recommendation.TABLE, "id= ?",new String[] {id});//"ID= ?" is the way to ask if there is id that was delete
    }
}
