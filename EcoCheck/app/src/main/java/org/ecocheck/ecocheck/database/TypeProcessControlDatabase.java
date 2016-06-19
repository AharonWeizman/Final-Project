package org.ecocheck.ecocheck.database;

/**
 * Created by Ron on 26/05/2016.
 */


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.ecocheck.ecocheck.dto.PersonalExposure;
import org.ecocheck.ecocheck.dto.Process;
import org.ecocheck.ecocheck.dto.TypeProcessControl;
import org.ecocheck.ecocheck.utils.Constants;

import java.util.ArrayList;


public class TypeProcessControlDatabase {

    public String[] columns = {
            Constants.DbFields.COLUMN_REPORT_NUMBER,
            Constants.DbFields.COLUMN_ASSIGNMENT_NAME,
            Constants.DbFields.COLUMN_DEPARTMENT_NAME,
            Process.COLUMN_PROCESS_Process,
            Constants.DbFields.COLUMN_TYPE_PROCESS_CONTROL,
            Constants.DbFields.COLUMN_DESC_PROCESS_CONTROL,
    };


    public static String createTable() {
        return "CREATE TABLE " + Constants.DbFields.TABLE_NAME_TYPE_PROCESS_CONTROL + " ( "
                + Constants.DbFields.COLUMN_DB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + Constants.DbFields.COLUMN_REPORT_NUMBER + " INTEGER, "
                + Constants.DbFields.COLUMN_ASSIGNMENT_NAME + " TEXT NOT NULL, "
                + Constants.DbFields.COLUMN_DEPARTMENT_NAME + " TEXT NOT NULL, "
                + Process.COLUMN_PROCESS_Process + " TEXT NOT NULL, "
                + Constants.DbFields.COLUMN_TYPE_PROCESS_CONTROL + " TEXT, "
                + Constants.DbFields.COLUMN_DESC_PROCESS_CONTROL + " TEXT " + " )";
    }


    public boolean insertTypeProcessControl(TypeProcessControl typeProcessControl) {

        SQLiteDatabase db = DatabaseMultipleManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();

        values.put(Constants.DbFields.COLUMN_REPORT_NUMBER, typeProcessControl.getReportNo());
        values.put(Constants.DbFields.COLUMN_DEPARTMENT_NAME, typeProcessControl.getDepartment());
        values.put(Constants.DbFields.COLUMN_ASSIGNMENT_NAME, typeProcessControl.getAssignmentName());
        values.put(Process.COLUMN_PROCESS_Process, typeProcessControl.getProcess());

        values.put(Constants.DbFields.COLUMN_TYPE_PROCESS_CONTROL, typeProcessControl.getTypeOfProcessControl());
        values.put(Constants.DbFields.COLUMN_DESC_PROCESS_CONTROL, typeProcessControl.getDescOfProcessControl());

        long result = db.insertOrThrow(Constants.DbFields.TABLE_NAME_TYPE_PROCESS_CONTROL, null, values);
        DatabaseMultipleManager.getInstance().closeDatabase();

        if (result == -1) {
            return false;
        }

        return true;

    }

    public ArrayList<TypeProcessControl> getAllTypeProcess(Process process) {
        ArrayList<TypeProcessControl> typeProcessControlList = new ArrayList<>();
        SQLiteDatabase database = DatabaseMultipleManager.getInstance().openDatabase();

        StringBuffer selection = new StringBuffer();
        // report filter
        selection.append(Constants.DbFields.COLUMN_REPORT_NUMBER);
//        selection.append(" =? ");
        selection.append(" = ");
        selection.append(process.getReportNumber());

        //department filter
        selection.append(" AND ");
        selection.append(Constants.DbFields.COLUMN_DEPARTMENT_NAME);
        selection.append(" like '");
        selection.append(process.getDepartment());
        selection.append("'");

        //Assignment Filter
        selection.append(" AND ");
        selection.append(Constants.DbFields.COLUMN_ASSIGNMENT_NAME);
        selection.append(" like '");
        selection.append(process.getAssignment());
        selection.append("'");

        // Prcess filter
        selection.append(" AND ");
        selection.append(Process.COLUMN_PROCESS_Process);
        selection.append(" like '");
        selection.append(process.getProcess());
        selection.append("'");

        Cursor cursor = database.query(Constants.DbFields.TABLE_NAME_TYPE_PROCESS_CONTROL, columns, selection.toString(), null, null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                TypeProcessControl typeProcessControl = new TypeProcessControl();
                typeProcessControl.setDepartment(cursor.getString(cursor.getColumnIndex(Constants.DbFields.COLUMN_DEPARTMENT_NAME)));
                typeProcessControl.setReportNo(cursor.getInt(cursor.getColumnIndex(Constants.DbFields.COLUMN_REPORT_NUMBER)));
                typeProcessControl.setAssignmentName(cursor.getString(cursor.getColumnIndex(Constants.DbFields.COLUMN_ASSIGNMENT_NAME)));
                typeProcessControl.setTypeOfProcessControl(cursor.getString(cursor.getColumnIndex(Constants.DbFields.COLUMN_TYPE_PROCESS_CONTROL)));
                typeProcessControl.setDescOfProcessControl(cursor.getString(cursor.getColumnIndex(Constants.DbFields.COLUMN_DESC_PROCESS_CONTROL)));
                typeProcessControl.setProcess(cursor.getString(cursor.getColumnIndex(Process.COLUMN_PROCESS_Process)));
                typeProcessControlList.add(typeProcessControl);
            } while (cursor.moveToNext());
        }
        DatabaseMultipleManager.getInstance().closeDatabase();
        return typeProcessControlList;
    }

    public boolean isExists(Process process, String typeOfProcess) {

        SQLiteDatabase database = DatabaseMultipleManager.getInstance().openDatabase();

        StringBuffer selection = new StringBuffer();
        selection.append(Constants.DbFields.COLUMN_REPORT_NUMBER);
        selection.append("=");
        selection.append(process.getReportNumber());
        selection.append(" AND ");
        selection.append(Constants.DbFields.COLUMN_DEPARTMENT_NAME);
        selection.append(" like ");
        selection.append("'");
        selection.append(process.getDepartment());
        selection.append("'");
        selection.append(" AND ");
        selection.append(Constants.DbFields.COLUMN_ASSIGNMENT_NAME);
        selection.append(" like ");
        selection.append("'");
        selection.append(process.getAssignment());
        selection.append("'");
        selection.append(" AND ");
        selection.append(Process.COLUMN_PROCESS_Process);
        selection.append(" like ");
        selection.append("'");
        selection.append(process.getProcess());
        selection.append("'");
        selection.append(" AND ");
        selection.append(Constants.DbFields.COLUMN_TYPE_PROCESS_CONTROL);
        selection.append(" like ");
        selection.append("'");
        selection.append(typeOfProcess);
        selection.append("'");

        Cursor cursor = database.query(Constants.DbFields.TABLE_NAME_TYPE_PROCESS_CONTROL, columns, selection.toString(), null, null, null, null);

        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }

    public boolean updateTypeProcessEntry(TypeProcessControl typeProcessControl) {

        StringBuffer selection = new StringBuffer();
        selection.append(Constants.DbFields.COLUMN_REPORT_NUMBER);
//        selection.append("=?");
        selection.append(" = ");
        selection.append(typeProcessControl.getReportNo());

        selection.append(" AND ");
        selection.append(Constants.DbFields.COLUMN_DEPARTMENT_NAME);
        selection.append(" like ");
        selection.append("'");
        selection.append(typeProcessControl.getDepartment());
        selection.append("'");
        selection.append(" AND ");
        selection.append(Constants.DbFields.COLUMN_ASSIGNMENT_NAME);
        selection.append(" like '");
        selection.append(typeProcessControl.getAssignmentName());
        selection.append("'");
        selection.append(" AND ");
        selection.append(Process.COLUMN_PROCESS_Process);
        selection.append(" like '");
        selection.append(typeProcessControl.getProcess());
        selection.append("'");
        selection.append(" AND ");
        selection.append(Constants.DbFields.COLUMN_TYPE_PROCESS_CONTROL);
        selection.append(" like '");
        selection.append(typeProcessControl.getTypeOfProcessControl());
        selection.append("'");

        SQLiteDatabase db = DatabaseMultipleManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.DbFields.COLUMN_DESC_PROCESS_CONTROL, typeProcessControl.getDescOfProcessControl());
        long result = db.update(Constants.DbFields.TABLE_NAME_TYPE_PROCESS_CONTROL, values, selection.toString(), null);
        DatabaseMultipleManager.getInstance().closeDatabase();

        if (result > 0) {
            return true;
        }
        return false;
    }


}

