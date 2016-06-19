package org.ecocheck.ecocheck.database;

/**
 * Created by Ron on 26/05/2016.
 */


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.ecocheck.ecocheck.dto.Department;
import org.ecocheck.ecocheck.utils.Constants;

import java.util.ArrayList;


public class DepartmentDatabase {

    

    public String[] columns = {
            Constants.DbFields.COLUMN_DB_ID,
            Constants.DbFields.COLUMN_DEPARTMENT_NAME,
            Constants.DbFields.COLUMN_REPORT_NUMBER,
    };

    public DepartmentDatabase() {

    }

    public static String createTable() {
        return "CREATE TABLE " + Constants.DbFields.TABLE_NAME_DEPT + "("
                + Constants.DbFields.COLUMN_DB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + Constants.DbFields.COLUMN_REPORT_NUMBER + " INTEGER NOT NULL, "
                + Constants.DbFields.COLUMN_DEPARTMENT_NAME + " TEXT NOT NULL " + " )";
    }

    public boolean insertDepartment(Department dept) {
        SQLiteDatabase db = DatabaseMultipleManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.DbFields.COLUMN_REPORT_NUMBER, dept.getReportNo());
        values.put(Constants.DbFields.COLUMN_DEPARTMENT_NAME, dept.getDeptName());

        long result = db.insert(Constants.DbFields.TABLE_NAME_DEPT, null, values);
        DatabaseMultipleManager.getInstance().closeDatabase();
        if (result == -1) {
            return false;
        }

        return true;
    }

    public ArrayList<Department> getAllDepartment(String refNo) {
        ArrayList<Department> departmentList = new ArrayList<>();
        SQLiteDatabase database = DatabaseMultipleManager.getInstance().openDatabase();

        String[] selectionArgs = {refNo};

        StringBuffer selection = new StringBuffer();
        selection.append(Constants.DbFields.COLUMN_REPORT_NUMBER);
        selection.append("=?");

//        String selection = Constants.DbFields.COLUMN_REPORT_NUMBER + "\'"+refNo+"\'";
        Cursor cursor = database.query(Constants.DbFields.TABLE_NAME_DEPT, columns, selection.toString(), selectionArgs, null, null, null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();

            do{
                Department department = new Department();
                department.setDeptName(cursor.getString(cursor.getColumnIndex(Constants.DbFields.COLUMN_DEPARTMENT_NAME)));
                department.setReportNo(cursor.getInt(cursor.getColumnIndex(Constants.DbFields.COLUMN_REPORT_NUMBER)));
                department.set_ID(cursor.getLong(cursor.getColumnIndex(Constants.DbFields.COLUMN_DB_ID)));
                departmentList.add(department);
            }while (cursor.moveToNext());

        }

        return departmentList;
    }

    public boolean isDepartmentExists(String refNo, String deptName){

        SQLiteDatabase database = DatabaseMultipleManager.getInstance().openDatabase();

        String[] selectionArgs = {
                refNo, deptName};

        StringBuffer selection = new StringBuffer();
        selection.append(Constants.DbFields.COLUMN_REPORT_NUMBER);
        selection.append("=?");
        selection.append(" AND ");
        selection.append(Constants.DbFields.COLUMN_DEPARTMENT_NAME);
        selection.append("=?");

        Cursor cursor = database.query(Constants.DbFields.TABLE_NAME_DEPT, columns,  selection.toString(), selectionArgs, null, null, null);

        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }

}
