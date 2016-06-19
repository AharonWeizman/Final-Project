package org.ecocheck.ecocheck.database;

/**
 * Created by Ron on 26/05/2016.
 */


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.ecocheck.ecocheck.dto.Assignment;
import org.ecocheck.ecocheck.utils.Constants;

import java.util.ArrayList;


public class AssignmentDatabase
{

    //
    public String[] columns =
            {
            Constants.DbFields.COLUMN_REPORT_NUMBER,
            Constants.DbFields.COLUMN_ASSIGNMENT_NAME,
            Constants.DbFields.COLUMN_NO_OF_EMPLOYEE,
            Constants.DbFields.COLUMN_DEPARTMENT_NAME
    };


    // create the table name and coulmn of the table
    public static String createTable()
    {
        return "CREATE TABLE " + Constants.DbFields.TABLE_NAME_ASSIGNMENT + " ( "
                + Constants.DbFields.COLUMN_DB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + Constants.DbFields.COLUMN_REPORT_NUMBER + " INTEGER, "
                + Constants.DbFields.COLUMN_ASSIGNMENT_NAME + " TEXT NOT NULL, "
                + Constants.DbFields.COLUMN_NO_OF_EMPLOYEE + " TEXT NOT NULL, "
                + Constants.DbFields.COLUMN_DEPARTMENT_NAME + " TEXT NOT NULL" + " )";
    }


    // method to insert the data of the assignment include report number and department that relationship
    public boolean insertAssignment(Assignment assignment)
    {

        SQLiteDatabase db = DatabaseMultipleManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.DbFields.COLUMN_ASSIGNMENT_NAME, assignment.getAssignmentName());
        values.put(Constants.DbFields.COLUMN_NO_OF_EMPLOYEE, assignment.getEmployeeNo());
        values.put(Constants.DbFields.COLUMN_REPORT_NUMBER, assignment.getReportNo());
        values.put(Constants.DbFields.COLUMN_DEPARTMENT_NAME, assignment.getDepartment());

        long result = db.insert(Constants.DbFields.TABLE_NAME_ASSIGNMENT, null, values);
        DatabaseMultipleManager.getInstance().closeDatabase();

        if (result == -1) {
            return false;
        }

        return true;

    }

    // get all the data of the assignment include report number and department that relationship
    public ArrayList<Assignment> getAllAssignment(String refNo, String deptName)
    {
        ArrayList<Assignment> assignmentList = new ArrayList<>();
        SQLiteDatabase database = DatabaseMultipleManager.getInstance().openDatabase();

        String[] selectionArgs = {
                refNo, deptName};

        StringBuffer selection = new StringBuffer();
        selection.append(Constants.DbFields.COLUMN_REPORT_NUMBER);

        selection.append(" = ");
        selection.append(refNo);
        selection.append(" AND ");
        selection.append(Constants.DbFields.COLUMN_DEPARTMENT_NAME);
        selection.append(" like '");
        selection.append(deptName);
        selection.append("'");

        Cursor cursor = database.query(Constants.DbFields.TABLE_NAME_ASSIGNMENT, columns, selection.toString(), null/*selectionArgs*/, null, null, null);

        if (cursor.getCount() > 0) {

            cursor.moveToFirst();

            do {

                Assignment assignment = new Assignment();
                assignment.setDepartment(cursor.getString(cursor.getColumnIndex(Constants.DbFields.COLUMN_DEPARTMENT_NAME)));
                assignment.setReportNo(cursor.getInt(cursor.getColumnIndex(Constants.DbFields.COLUMN_REPORT_NUMBER)));
                assignment.setAssignmentName(cursor.getString(cursor.getColumnIndex(Constants.DbFields.COLUMN_ASSIGNMENT_NAME)));
                assignment.setEmployeeNo(cursor.getString(cursor.getColumnIndex(Constants.DbFields.COLUMN_NO_OF_EMPLOYEE)));
                assignmentList.add(assignment);
            } while (cursor.moveToNext());
        }

        DatabaseMultipleManager.getInstance().closeDatabase();
        return assignmentList;
    }

    // check if specific assignment exist
    public boolean isAssignmentExists(String refNo, String deptName, String assignmentName)
    {

        SQLiteDatabase database = DatabaseMultipleManager.getInstance().openDatabase();



        StringBuffer selection = new StringBuffer();
        selection.append(Constants.DbFields.COLUMN_REPORT_NUMBER);
        selection.append("=");
        selection.append(refNo);
        selection.append(" AND ");
        selection.append(Constants.DbFields.COLUMN_DEPARTMENT_NAME);
        selection.append(" like ");
        selection.append("'");
        selection.append(deptName);
        selection.append("'");
        selection.append(" AND ");
        selection.append(Constants.DbFields.COLUMN_ASSIGNMENT_NAME);
        selection.append(" like ");
        selection.append("'");
        selection.append(assignmentName);
        selection.append("'");

        Cursor cursor = database.query(Constants.DbFields.TABLE_NAME_ASSIGNMENT, columns, selection.toString(), null, null, null, null);

        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }

    public boolean updateAssignmentEntry(Assignment assignment) {



        StringBuffer selection = new StringBuffer();
        selection.append(Constants.DbFields.COLUMN_REPORT_NUMBER);

        selection.append(" = ");
        selection.append(assignment.getReportNo());

        selection.append(" AND ");
        selection.append(Constants.DbFields.COLUMN_DEPARTMENT_NAME);
        selection.append(" like ");
        selection.append("'");
        selection.append(assignment.getDepartment());
        selection.append("'");
        selection.append(" AND ");
        selection.append(Constants.DbFields.COLUMN_ASSIGNMENT_NAME);
        selection.append(" like '");
        selection.append(assignment.getAssignmentName());
        selection.append("'");

        SQLiteDatabase db = DatabaseMultipleManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();

        values.put(Constants.DbFields.COLUMN_NO_OF_EMPLOYEE, assignment.getEmployeeNo());


        long result = db.update(Constants.DbFields.TABLE_NAME_ASSIGNMENT, values, selection.toString(), null);
        DatabaseMultipleManager.getInstance().closeDatabase();

        if (result > 0) {
            return true;
        }
        return false;
    }

}

