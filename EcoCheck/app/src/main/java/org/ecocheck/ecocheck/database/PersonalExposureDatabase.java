package org.ecocheck.ecocheck.database;

/**
 * Created by Ron on 26/05/2016.
 */


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.ecocheck.ecocheck.dto.ChemicalExposure;
import org.ecocheck.ecocheck.dto.PersonalExposure;
import org.ecocheck.ecocheck.dto.Process;
import org.ecocheck.ecocheck.utils.Constants;

import java.util.ArrayList;


public class PersonalExposureDatabase {

    public String[] columns = {
            Constants.DbFields.COLUMN_REPORT_NUMBER,
            Constants.DbFields.COLUMN_ASSIGNMENT_NAME,
            Constants.DbFields.COLUMN_DEPARTMENT_NAME,
            Process.COLUMN_PROCESS_Process,
            Constants.DbFields.COLUMN_PERSONAL_PROTECTIVE_EQUIP_TYPE,
            Constants.DbFields.COLUMN_PERSONAL_CONTROL_DESC,
    };


    public static String createTable() {
        return "CREATE TABLE " + Constants.DbFields.TABLE_NAME_PERSONAL_EXPOSURE + " ( "
                + Constants.DbFields.COLUMN_DB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + Constants.DbFields.COLUMN_REPORT_NUMBER + " INTEGER, "
                + Constants.DbFields.COLUMN_ASSIGNMENT_NAME + " TEXT NOT NULL, "
                + Constants.DbFields.COLUMN_DEPARTMENT_NAME + " TEXT NOT NULL, "
                + Process.COLUMN_PROCESS_Process + " TEXT NOT NULL, "
                + Constants.DbFields.COLUMN_PERSONAL_PROTECTIVE_EQUIP_TYPE + " TEXT, "
                + Constants.DbFields.COLUMN_PERSONAL_CONTROL_DESC + " TEXT " + " )";
    }


    public boolean insertExposure(PersonalExposure chemicalExposure) {

        SQLiteDatabase db = DatabaseMultipleManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();

        values.put(Constants.DbFields.COLUMN_REPORT_NUMBER, chemicalExposure.getReportNo());
        values.put(Constants.DbFields.COLUMN_DEPARTMENT_NAME, chemicalExposure.getDepartment());
        values.put(Constants.DbFields.COLUMN_ASSIGNMENT_NAME, chemicalExposure.getAssignmentName());
        values.put(Process.COLUMN_PROCESS_Process, chemicalExposure.getProcess());

        values.put(Constants.DbFields.COLUMN_PERSONAL_PROTECTIVE_EQUIP_TYPE, chemicalExposure.getPerProEquipType());
        values.put(Constants.DbFields.COLUMN_PERSONAL_CONTROL_DESC, chemicalExposure.getPerControlDesc());

        long result = db.insertOrThrow(Constants.DbFields.TABLE_NAME_PERSONAL_EXPOSURE, null, values);
        DatabaseMultipleManager.getInstance().closeDatabase();

        if (result == -1) {
            return false;
        }

        return true;

    }

    public ArrayList<PersonalExposure> getAllPersonalExposure(Process process) {
        ArrayList<PersonalExposure> personalExposureList = new ArrayList<>();
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

        Cursor cursor = database.query(Constants.DbFields.TABLE_NAME_PERSONAL_EXPOSURE, columns, selection.toString(), null/*selectionArgs*/, null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                PersonalExposure personalExposure = new PersonalExposure();
                personalExposure.setDepartment(cursor.getString(cursor.getColumnIndex(Constants.DbFields.COLUMN_DEPARTMENT_NAME)));
                personalExposure.setReportNo(cursor.getInt(cursor.getColumnIndex(Constants.DbFields.COLUMN_REPORT_NUMBER)));
                personalExposure.setAssignmentName(cursor.getString(cursor.getColumnIndex(Constants.DbFields.COLUMN_ASSIGNMENT_NAME)));
                personalExposure.setPerControlDesc(cursor.getString(cursor.getColumnIndex(Constants.DbFields.COLUMN_PERSONAL_CONTROL_DESC)));
                personalExposure.setPerProEquipType(cursor.getString(cursor.getColumnIndex(Constants.DbFields.COLUMN_PERSONAL_PROTECTIVE_EQUIP_TYPE)));
                personalExposure.setProcess(cursor.getString(cursor.getColumnIndex(Process.COLUMN_PROCESS_Process)));
                personalExposureList.add(personalExposure);
            } while (cursor.moveToNext());
        }
        DatabaseMultipleManager.getInstance().closeDatabase();
        return personalExposureList;
    }

    public boolean isExists(Process process, String perProEquipType) {

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
        selection.append(Constants.DbFields.COLUMN_PERSONAL_PROTECTIVE_EQUIP_TYPE);
        selection.append(" like ");
        selection.append("'");
        selection.append(perProEquipType);
        selection.append("'");

        Cursor cursor = database.query(Constants.DbFields.TABLE_NAME_PERSONAL_EXPOSURE, columns, selection.toString(), null, null, null, null);

        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }

    public boolean updateChemicalExpoEntry(PersonalExposure chemicalExposure) {

        StringBuffer selection = new StringBuffer();
        selection.append(Constants.DbFields.COLUMN_REPORT_NUMBER);
//        selection.append("=?");
        selection.append(" = ");
        selection.append(chemicalExposure.getReportNo());

        selection.append(" AND ");
        selection.append(Constants.DbFields.COLUMN_DEPARTMENT_NAME);
        selection.append(" like ");
        selection.append("'");
        selection.append(chemicalExposure.getDepartment());
        selection.append("'");
        selection.append(" AND ");
        selection.append(Constants.DbFields.COLUMN_ASSIGNMENT_NAME);
        selection.append(" like '");
        selection.append(chemicalExposure.getAssignmentName());
        selection.append("'");
        selection.append(" AND ");
        selection.append(Process.COLUMN_PROCESS_Process);
        selection.append(" like '");
        selection.append(chemicalExposure.getProcess());
        selection.append("'");
        selection.append(" AND ");
        selection.append(Constants.DbFields.COLUMN_PERSONAL_PROTECTIVE_EQUIP_TYPE);
        selection.append(" like '");
        selection.append(chemicalExposure.getPerProEquipType());
        selection.append("'");

        SQLiteDatabase db = DatabaseMultipleManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.DbFields.COLUMN_NO_OF_EMPLOYEE, chemicalExposure.getPerControlDesc());
        long result = db.update(Constants.DbFields.TABLE_NAME_PERSONAL_EXPOSURE, values, selection.toString(), null);
        DatabaseMultipleManager.getInstance().closeDatabase();

        if (result > 0) {
            return true;
        }
        return false;
    }


    public ArrayList<PersonalExposure> getSelectedPersonalExposure(Process process, String equipmentType) {
        ArrayList<PersonalExposure> personalExposureList = new ArrayList<>();
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

        // Prcess filter
        selection.append(" AND ");
        selection.append(Constants.DbFields.COLUMN_PERSONAL_PROTECTIVE_EQUIP_TYPE);
        selection.append(" like '");
        selection.append(equipmentType);
        selection.append("'");

        Cursor cursor = database.query(Constants.DbFields.TABLE_NAME_PERSONAL_EXPOSURE, columns, selection.toString(), null/*selectionArgs*/, null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                PersonalExposure personalExposure = new PersonalExposure();
                personalExposure.setDepartment(cursor.getString(cursor.getColumnIndex(Constants.DbFields.COLUMN_DEPARTMENT_NAME)));
                personalExposure.setReportNo(cursor.getInt(cursor.getColumnIndex(Constants.DbFields.COLUMN_REPORT_NUMBER)));
                personalExposure.setAssignmentName(cursor.getString(cursor.getColumnIndex(Constants.DbFields.COLUMN_ASSIGNMENT_NAME)));
                personalExposure.setPerControlDesc(cursor.getString(cursor.getColumnIndex(Constants.DbFields.COLUMN_PERSONAL_CONTROL_DESC)));
                personalExposure.setPerProEquipType(cursor.getString(cursor.getColumnIndex(Constants.DbFields.COLUMN_PERSONAL_PROTECTIVE_EQUIP_TYPE)));
                personalExposure.setProcess(cursor.getString(cursor.getColumnIndex(Process.COLUMN_PROCESS_Process)));
                personalExposureList.add(personalExposure);
            } while (cursor.moveToNext());
        }
        DatabaseMultipleManager.getInstance().closeDatabase();
        return personalExposureList;
    }

}

