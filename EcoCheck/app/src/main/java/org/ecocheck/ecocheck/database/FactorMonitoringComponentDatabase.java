package org.ecocheck.ecocheck.database;

/**
 * Created by Ron on 26/05/2016.
 */


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.ecocheck.ecocheck.dto.FactorsMonitoringComponent;
import org.ecocheck.ecocheck.dto.MaterialAssociatedTask;
import org.ecocheck.ecocheck.dto.Process;
import org.ecocheck.ecocheck.utils.Constants;

import java.util.ArrayList;


public class FactorMonitoringComponentDatabase {

    public String[] columns = {
            Constants.DbFields.COLUMN_REPORT_NUMBER,
            Constants.DbFields.COLUMN_ASSIGNMENT_NAME,
            Constants.DbFields.COLUMN_DEPARTMENT_NAME,
            Process.COLUMN_PROCESS_Process,

            Constants.DbFields.COLUMN_QUICK_CHOICE,
            Constants.DbFields.COLUMN_FACTOR,
            Constants.DbFields.COLUMN_POLICY_LEVEL,
            Constants.DbFields.COLUMN_REGULARITY_TYPE,
            Constants.DbFields.COLUMN_UNIT,
            Constants.DbFields.COLUMN_NO_OF_SAMPLE,
            Constants.DbFields.COLUMN_INGREDIENTS,
            Constants.DbFields.COLUMN_SEARCH_ADD,
            Constants.DbFields.COLUMN_MATERIAL_COMPONENT_PRESENT,

    };


    public static String createTable() {
        return "CREATE TABLE " + Constants.DbFields.TABLE_NAME_FACTOR_MONITORING_TASK + " ( "
                + Constants.DbFields.COLUMN_DB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + Constants.DbFields.COLUMN_REPORT_NUMBER + " INTEGER, "
                + Constants.DbFields.COLUMN_ASSIGNMENT_NAME + " TEXT NOT NULL, "
                + Constants.DbFields.COLUMN_DEPARTMENT_NAME + " TEXT NOT NULL, "
                + Process.COLUMN_PROCESS_Process + " TEXT NOT NULL, "
                + Constants.DbFields.COLUMN_QUICK_CHOICE + " TEXT, "
                + Constants.DbFields.COLUMN_FACTOR + " TEXT, "
                + Constants.DbFields.COLUMN_REGULARITY_TYPE + " TEXT, "
                + Constants.DbFields.COLUMN_POLICY_LEVEL + " TEXT, "
                + Constants.DbFields.COLUMN_UNIT + " TEXT, "
                + Constants.DbFields.COLUMN_NO_OF_SAMPLE + " TEXT, "
                + Constants.DbFields.COLUMN_INGREDIENTS + " TEXT, "
                + Constants.DbFields.COLUMN_SEARCH_ADD + " TEXT, "
                + Constants.DbFields.COLUMN_MATERIAL_COMPONENT_PRESENT + " TEXT " + " )";
    }


    public boolean insertFactorComponent(FactorsMonitoringComponent factorsMonitoringComponent) {

        SQLiteDatabase db = DatabaseMultipleManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();

        values.put(Constants.DbFields.COLUMN_REPORT_NUMBER, factorsMonitoringComponent.getReportNo());
        values.put(Constants.DbFields.COLUMN_DEPARTMENT_NAME, factorsMonitoringComponent.getDepartment());
        values.put(Constants.DbFields.COLUMN_ASSIGNMENT_NAME, factorsMonitoringComponent.getAssignmentName());
        values.put(Process.COLUMN_PROCESS_Process, factorsMonitoringComponent.getProcess());

        values.put(Constants.DbFields.COLUMN_QUICK_CHOICE, factorsMonitoringComponent.getmQuickChoice());
        values.put(Constants.DbFields.COLUMN_FACTOR, factorsMonitoringComponent.getFactor());
        values.put(Constants.DbFields.COLUMN_REGULARITY_TYPE, factorsMonitoringComponent.getRegularityType());
        values.put(Constants.DbFields.COLUMN_POLICY_LEVEL, factorsMonitoringComponent.getPolicyLevel());
        values.put(Constants.DbFields.COLUMN_UNIT, factorsMonitoringComponent.getUnit());
        values.put(Constants.DbFields.COLUMN_NO_OF_SAMPLE, factorsMonitoringComponent.getNoOfSample());
        values.put(Constants.DbFields.COLUMN_INGREDIENTS, factorsMonitoringComponent.getIngredients());
        values.put(Constants.DbFields.COLUMN_SEARCH_ADD, factorsMonitoringComponent.getSearchAdd());
        values.put(Constants.DbFields.COLUMN_MATERIAL_COMPONENT_PRESENT, factorsMonitoringComponent.getMaterialComponentPresent());

        long result = db.insertOrThrow(Constants.DbFields.TABLE_NAME_FACTOR_MONITORING_TASK, null, values);
        DatabaseMultipleManager.getInstance().closeDatabase();

        if (result == -1) {
            return false;
        }

        return true;

    }

    public ArrayList<FactorsMonitoringComponent> getAllFactorMonitoringComponent(String refNo, String deptName, String assignName, String processName) {
        ArrayList<FactorsMonitoringComponent> typeProcessControlList = new ArrayList<>();
        SQLiteDatabase database = DatabaseMultipleManager.getInstance().openDatabase();

        StringBuffer selection = new StringBuffer();
        // report filter
        selection.append(Constants.DbFields.COLUMN_REPORT_NUMBER);
//        selection.append(" =? ");
        selection.append(" = ");
        selection.append(refNo);

        //department filter
        selection.append(" AND ");
        selection.append(Constants.DbFields.COLUMN_DEPARTMENT_NAME);
        selection.append(" like '");
        selection.append(deptName);
        selection.append("'");

        //Assignment Filter
        selection.append(" AND ");
        selection.append(Constants.DbFields.COLUMN_ASSIGNMENT_NAME);
        selection.append(" like '");
        selection.append(assignName);
        selection.append("'");

        // Prcess filter
        selection.append(" AND ");
        selection.append(Process.COLUMN_PROCESS_Process);
        selection.append(" like '");
        selection.append(processName);
        selection.append("'");

        Cursor cursor = database.query(Constants.DbFields.TABLE_NAME_FACTOR_MONITORING_TASK, columns, selection.toString(), null/*selectionArgs*/, null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                FactorsMonitoringComponent factorsMonitoringComponent = new FactorsMonitoringComponent();
                factorsMonitoringComponent.setDepartment(cursor.getString(cursor.getColumnIndex(Constants.DbFields.COLUMN_DEPARTMENT_NAME)));
                factorsMonitoringComponent.setReportNo(cursor.getInt(cursor.getColumnIndex(Constants.DbFields.COLUMN_REPORT_NUMBER)));
                factorsMonitoringComponent.setAssignmentName(cursor.getString(cursor.getColumnIndex(Constants.DbFields.COLUMN_ASSIGNMENT_NAME)));
                factorsMonitoringComponent.setProcess(cursor.getString(cursor.getColumnIndex(Process.COLUMN_PROCESS_Process)));
                factorsMonitoringComponent.setQuickChoice(cursor.getString(cursor.getColumnIndex(Constants.DbFields.COLUMN_QUICK_CHOICE)));
                factorsMonitoringComponent.setFactor(cursor.getString(cursor.getColumnIndex(Constants.DbFields.COLUMN_FACTOR)));
                factorsMonitoringComponent.setPolicyLevel(cursor.getString(cursor.getColumnIndex(Constants.DbFields.COLUMN_POLICY_LEVEL)));
                factorsMonitoringComponent.setRegularityType(cursor.getString(cursor.getColumnIndex(Constants.DbFields.COLUMN_REGULARITY_TYPE)));
                factorsMonitoringComponent.setUnit(cursor.getString(cursor.getColumnIndex(Constants.DbFields.COLUMN_UNIT)));
                factorsMonitoringComponent.setNoOfSample(cursor.getString(cursor.getColumnIndex(Constants.DbFields.COLUMN_NO_OF_SAMPLE)));
                factorsMonitoringComponent.setIngredients(cursor.getString(cursor.getColumnIndex(Constants.DbFields.COLUMN_INGREDIENTS)));
                factorsMonitoringComponent.setSearchAdd(cursor.getString(cursor.getColumnIndex(Constants.DbFields.COLUMN_SEARCH_ADD)));
                factorsMonitoringComponent.setMaterialComponentPresent(cursor.getString(cursor.getColumnIndex(Constants.DbFields.COLUMN_MATERIAL_COMPONENT_PRESENT)));
                typeProcessControlList.add(factorsMonitoringComponent);
            } while (cursor.moveToNext());
        }
        DatabaseMultipleManager.getInstance().closeDatabase();
        return typeProcessControlList;
    }

    public boolean isExists(Process process, String quickChoice) {

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
        selection.append(Constants.DbFields.COLUMN_QUICK_CHOICE);
        selection.append(" like ");
        selection.append("'");
        selection.append(quickChoice);
        selection.append("'");

        Cursor cursor = database.query(Constants.DbFields.TABLE_NAME_FACTOR_MONITORING_TASK, columns, selection.toString(), null, null, null, null);

        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }

    public boolean updateFactorMonitorComponent(FactorsMonitoringComponent factorsMonitoringComponent) {

        StringBuffer selection = new StringBuffer();
        selection.append(Constants.DbFields.COLUMN_REPORT_NUMBER);

        selection.append(" = ");
        selection.append(factorsMonitoringComponent.getReportNo());

        selection.append(" AND ");
        selection.append(Constants.DbFields.COLUMN_DEPARTMENT_NAME);
        selection.append(" like ");
        selection.append("'");
        selection.append(factorsMonitoringComponent.getDepartment());
        selection.append("'");

        selection.append(" AND ");
        selection.append(Constants.DbFields.COLUMN_ASSIGNMENT_NAME);
        selection.append(" like '");
        selection.append(factorsMonitoringComponent.getAssignmentName());
        selection.append("'");

        selection.append(" AND ");
        selection.append(Process.COLUMN_PROCESS_Process);
        selection.append(" like '");
        selection.append(factorsMonitoringComponent.getProcess());
        selection.append("'");

        selection.append(" AND ");
        selection.append(Constants.DbFields.COLUMN_QUICK_CHOICE);
        selection.append(" like '");
        selection.append(factorsMonitoringComponent.getmQuickChoice());
        selection.append("'");

        SQLiteDatabase db = DatabaseMultipleManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.DbFields.COLUMN_FACTOR, factorsMonitoringComponent.getFactor());
        values.put(Constants.DbFields.COLUMN_REGULARITY_TYPE, factorsMonitoringComponent.getRegularityType());
        values.put(Constants.DbFields.COLUMN_POLICY_LEVEL, factorsMonitoringComponent.getPolicyLevel());
        values.put(Constants.DbFields.COLUMN_UNIT, factorsMonitoringComponent.getUnit());
        values.put(Constants.DbFields.COLUMN_NO_OF_SAMPLE, factorsMonitoringComponent.getNoOfSample());
        values.put(Constants.DbFields.COLUMN_INGREDIENTS, factorsMonitoringComponent.getIngredients());
        values.put(Constants.DbFields.COLUMN_SEARCH_ADD, factorsMonitoringComponent.getSearchAdd());
        values.put(Constants.DbFields.COLUMN_MATERIAL_COMPONENT_PRESENT, factorsMonitoringComponent.getMaterialComponentPresent());
        long result = db.update(Constants.DbFields.TABLE_NAME_FACTOR_MONITORING_TASK, values, selection.toString(), null);
        DatabaseMultipleManager.getInstance().closeDatabase();

        if (result > 0) {
            return true;
        }
        return false;
    }


}
