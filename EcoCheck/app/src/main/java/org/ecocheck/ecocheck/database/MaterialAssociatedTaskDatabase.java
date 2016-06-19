package org.ecocheck.ecocheck.database;

/**
 * Created by Ron on 26/05/2016.
 */


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.ecocheck.ecocheck.dto.MaterialAssociatedTask;
import org.ecocheck.ecocheck.dto.Process;
import org.ecocheck.ecocheck.dto.TypeProcessControl;
import org.ecocheck.ecocheck.utils.Constants;

import java.util.ArrayList;


public class MaterialAssociatedTaskDatabase {

    public String[] columns = {
            Constants.DbFields.COLUMN_REPORT_NUMBER,
            Constants.DbFields.COLUMN_ASSIGNMENT_NAME,
            Constants.DbFields.COLUMN_DEPARTMENT_NAME,
            Process.COLUMN_PROCESS_Process,

            Constants.DbFields.COLUMN_MATERIAL_TRADE_NAME,
            Constants.DbFields.COLUMN_USING_MATERIAL,
            Constants.DbFields.COLUMN_LINKED,
            Constants.DbFields.COLUMN_TINY_AMOUNT,
            Constants.DbFields.COLUMN_MSDS,

    };


    public static String createTable() {
        return "CREATE TABLE " + Constants.DbFields.TABLE_NAME_MATERIAL_ASSO_TASK + " ( "
                + Constants.DbFields.COLUMN_DB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + Constants.DbFields.COLUMN_REPORT_NUMBER + " INTEGER, "
                + Constants.DbFields.COLUMN_ASSIGNMENT_NAME + " TEXT NOT NULL, "
                + Constants.DbFields.COLUMN_DEPARTMENT_NAME + " TEXT NOT NULL, "
                + Process.COLUMN_PROCESS_Process + " TEXT NOT NULL, "
                + Constants.DbFields.COLUMN_MATERIAL_TRADE_NAME + " TEXT, "
                + Constants.DbFields.COLUMN_USING_MATERIAL + " TEXT, "
                + Constants.DbFields.COLUMN_LINKED + " TEXT, "
                + Constants.DbFields.COLUMN_TINY_AMOUNT + " TEXT, "
                + Constants.DbFields.COLUMN_MSDS + " TEXT " + " )";
    }


    public boolean insertMaterialAssoTask(MaterialAssociatedTask materialAssociatedTask) {

        SQLiteDatabase db = DatabaseMultipleManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();

        values.put(Constants.DbFields.COLUMN_REPORT_NUMBER, materialAssociatedTask.getReportNo());
        values.put(Constants.DbFields.COLUMN_DEPARTMENT_NAME, materialAssociatedTask.getDepartment());
        values.put(Constants.DbFields.COLUMN_ASSIGNMENT_NAME, materialAssociatedTask.getAssignmentName());
        values.put(Process.COLUMN_PROCESS_Process, materialAssociatedTask.getProcess());

        values.put(Constants.DbFields.COLUMN_MATERIAL_TRADE_NAME, materialAssociatedTask.getMaterialTradeName());
        values.put(Constants.DbFields.COLUMN_USING_MATERIAL, materialAssociatedTask.getUsingMaterial());
        
        values.put(Constants.DbFields.COLUMN_LINKED,String.valueOf(materialAssociatedTask.ismLinked()));
        values.put(Constants.DbFields.COLUMN_TINY_AMOUNT,String.valueOf(materialAssociatedTask.ismTinyAmount()));
        values.put(Constants.DbFields.COLUMN_MSDS,String.valueOf(materialAssociatedTask.ismMSDS()));

        long result = db.insertOrThrow(Constants.DbFields.TABLE_NAME_MATERIAL_ASSO_TASK, null, values);
        DatabaseMultipleManager.getInstance().closeDatabase();

        if (result == -1) {
            return false;
        }

        return true;

    }

    public ArrayList<MaterialAssociatedTask> getAllMaterialTask(String refNo, String deptName, String assignName, String processName)
    {
        ArrayList<MaterialAssociatedTask> typeProcessControlList = new ArrayList<>();
        SQLiteDatabase database = DatabaseMultipleManager.getInstance().openDatabase();

        StringBuffer selection = new StringBuffer();
        // report filter
        selection.append(Constants.DbFields.COLUMN_REPORT_NUMBER);

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

        Cursor cursor = database.query(Constants.DbFields.TABLE_NAME_MATERIAL_ASSO_TASK, columns, selection.toString(), null/*selectionArgs*/, null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                MaterialAssociatedTask typeProcessControl = new MaterialAssociatedTask();
                typeProcessControl.setDepartment(cursor.getString(cursor.getColumnIndex(Constants.DbFields.COLUMN_DEPARTMENT_NAME)));
                typeProcessControl.setReportNo(cursor.getInt(cursor.getColumnIndex(Constants.DbFields.COLUMN_REPORT_NUMBER)));
                typeProcessControl.setAssignmentName(cursor.getString(cursor.getColumnIndex(Constants.DbFields.COLUMN_ASSIGNMENT_NAME)));
                typeProcessControl.setProcess(cursor.getString(cursor.getColumnIndex(Process.COLUMN_PROCESS_Process)));
                typeProcessControl.setMaterialTradeName(cursor.getString(cursor.getColumnIndex(Constants.DbFields.COLUMN_MATERIAL_TRADE_NAME)));
                typeProcessControl.setUsingMaterial(cursor.getString(cursor.getColumnIndex(Constants.DbFields.COLUMN_USING_MATERIAL)));
                

                final String materialAssociatedTaskLinked=cursor.getString(cursor.getColumnIndex(Constants.DbFields.COLUMN_LINKED)) ;
                if(materialAssociatedTaskLinked!=null && materialAssociatedTaskLinked.equalsIgnoreCase("true"))
                {
                    typeProcessControl.setmLinked(true);
                }
                else
                {
                    typeProcessControl.setmLinked(false);
                }

                final String materialAssociatedTaskTiny=cursor.getString(cursor.getColumnIndex(Constants.DbFields.COLUMN_TINY_AMOUNT)) ;
                if(materialAssociatedTaskTiny!=null && materialAssociatedTaskTiny.equalsIgnoreCase("true"))
                {
                    typeProcessControl.setmTinyAmount(true);
                }
                else
                {
                    typeProcessControl.setmTinyAmount(false);
                }

                final String materialAssociatedTaskMSDS=cursor.getString(cursor.getColumnIndex(Constants.DbFields.COLUMN_MSDS)) ;
                if(materialAssociatedTaskMSDS!=null && materialAssociatedTaskMSDS.equalsIgnoreCase("true"))
                {
                    typeProcessControl.setmMSDS(true);
                }
                else
                {
                    typeProcessControl.setmMSDS(false);
                }

                typeProcessControlList.add(typeProcessControl);
            } while (cursor.moveToNext());
        }
        DatabaseMultipleManager.getInstance().closeDatabase();
        return typeProcessControlList;
    }

    public boolean isExists(Process process, String materialTradeName) {

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
        selection.append(Constants.DbFields.COLUMN_MATERIAL_TRADE_NAME);
        selection.append(" like ");
        selection.append("'");
        selection.append(materialTradeName);
        selection.append("'");

        Cursor cursor = database.query(Constants.DbFields.TABLE_NAME_MATERIAL_ASSO_TASK, columns, selection.toString(), null, null, null, null);

        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }

    public boolean updateTypeProcessEntry(MaterialAssociatedTask materialAssociatedTask) {

        StringBuffer selection = new StringBuffer();
        selection.append(Constants.DbFields.COLUMN_REPORT_NUMBER);
//        selection.append("=?");
        selection.append(" = ");
        selection.append(materialAssociatedTask.getReportNo());

        selection.append(" AND ");
        selection.append(Constants.DbFields.COLUMN_DEPARTMENT_NAME);
        selection.append(" like ");
        selection.append("'");
        selection.append(materialAssociatedTask.getDepartment());
        selection.append("'");
        selection.append(" AND ");
        selection.append(Constants.DbFields.COLUMN_ASSIGNMENT_NAME);
        selection.append(" like '");
        selection.append(materialAssociatedTask.getAssignmentName());
        selection.append("'");
        selection.append(" AND ");
        selection.append(Process.COLUMN_PROCESS_Process);
        selection.append(" like '");
        selection.append(materialAssociatedTask.getProcess());
        selection.append("'");
        selection.append(" AND ");
        selection.append(Constants.DbFields.COLUMN_MATERIAL_TRADE_NAME);
        selection.append(" like '");
        selection.append(materialAssociatedTask.getMaterialTradeName());
        selection.append("'");

        SQLiteDatabase db = DatabaseMultipleManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.DbFields.COLUMN_USING_MATERIAL, materialAssociatedTask.getUsingMaterial());
      


        long result = db.update(Constants.DbFields.TABLE_NAME_MATERIAL_ASSO_TASK, values, selection.toString(), null);
        DatabaseMultipleManager.getInstance().closeDatabase();

        if (result > 0) {
            return true;
        }
        return false;
    }


}

