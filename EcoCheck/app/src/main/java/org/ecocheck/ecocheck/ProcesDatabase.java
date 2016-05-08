package org.ecocheck.ecocheck.database;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import org.ecocheck.ecocheck.dto.Process;

/**
 * Created by Ron on 27/04/2016.
 */
public class ProcesDatabase
{
    private Process process;

    //constructor
    public ProcesDatabase( )
    {
        process= new Process();
    }

    public static String createTable()
    {
        return "CREATE TABLE " + Process.TABLE + "("
                + Process.KEY_ReportId + " PRIMARY KEY,"
                + Process.KEY_Name_Department + " TEXT, "
                + Process.COLUMN_PROCESS_Assignment + " TEXT, "
                + Process.COLUMN_PROCESS_NumOfEmployee + " TEXT, "
                + Process.COLUMN_PROCESS_Process + " TEXT, "
                + Process.COLUMN_PROCESS_ProcessComment + " TEXT, "
                + Process.COLUMN_PROCESS_ProcessDuration + " TEXT, "
                + Process.COLUMN_PROCESS_ProcessMethod + " TEXT, "
                + Process.COLUMN_PROCESS_ProcessBodyExposure + " TEXT, "
                + Process.COLUMN_PROCESS_ProcessRecommendation + " TEXT, "
                + Process.COLUMN_PROCESS_Warm + " TEXT, "
                + Process.COLUMN_PROCESS_Closed + " TEXT, "
                + Process.COLUMN_PROCESS_ProcessAmount + " TEXT, "
                + Process.COLUMN_PROCESS_NotChemicalExposure + " TEXT, "
                + Process.COLUMN_PROCESS_InhalantChemicalExposure + " TEXT, "
                + Process.COLUMN_PROCESS_PersonalProtectiveEquip + " TEXT, "
                + Process.COLUMN_PROCESS_DescPersonalControl + " TEXT, "
                + Process.COLUMN_PROCESS_TypeProcessControl + " TEXT, "
                + Process.COLUMN_PROCESS_DescProcessControl + " TEXT, "
                + Process.COLUMN_PROCESS_MaterialCommercialName + " TEXT, "
                + Process.COLUMN_PROCESS_FrequencyUsingMaterial + " TEXT, "
                + Process.COLUMN_PROCESS_Linked + " TEXT, "
                + Process.COLUMN_PROCESS_TinnyAmount + " TEXT, "
                + Process.COLUMN_PROCESS_Msds + " TEXT, "
                + Process.COLUMN_PROCESS_FastChoice + " TEXT, "
                + Process.COLUMN_PROCESS_factor + " TEXT, "
                + Process.COLUMN_PROCESS_PolicyLevel + " TEXT, "
                + Process.COLUMN_PROCESS_RegularityType + " TEXT, "
                + Process.COLUMN_PROCESS_Unit + " TEXT, "
                + Process.COLUMN_PROCESS_NumOfSample + " TEXT, "
                + Process.COLUMN_PROCESS_Ingredients + " TEXT, "
                + Process.COLUMN_PROCESS_Search_add + " TEXT, "
                + Process.COLUMN_PROCESS_MaterialComponentPercent + " TEXT )";

    }

    // method that insert the data to process data base
    public boolean insertProcessData(Process process)
    {
        SQLiteDatabase db = DatabaseMultipleManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Process.KEY_ReportId, process.getReportNumber());
        values.put(Process.KEY_Name_Department, process.getDepartment());
        values.put(Process.COLUMN_PROCESS_Assignment, process.getAssignment());
        values.put(Process.COLUMN_PROCESS_NumOfEmployee, process.getNumOfEmployee());
        values.put(Process.COLUMN_PROCESS_Process, process.getProcess());
        values.put(Process.COLUMN_PROCESS_ProcessComment, process.getProcessComment());
        values.put(Process.COLUMN_PROCESS_ProcessDuration, process.getProcessDuration());
        values.put(Process.COLUMN_PROCESS_ProcessMethod, process.getProcessMethod());
        values.put(Process.COLUMN_PROCESS_ProcessBodyExposure, String.valueOf(process.isProcessBodyExposure()));
        values.put(Process.COLUMN_PROCESS_ProcessRecommendation, process.getProcessRecommendation());
        values.put(Process.COLUMN_PROCESS_Warm, String.valueOf(process.isWarm()));
        values.put(Process.COLUMN_PROCESS_Closed, String.valueOf(process.isClosed()));
        values.put(Process.COLUMN_PROCESS_ProcessAmount, process.getProcessAmount());
        values.put(Process.COLUMN_PROCESS_NotChemicalExposure, process.getNotChemicalExposure());
        values.put(Process.COLUMN_PROCESS_InhalantChemicalExposure, process.getInhalantChemicalExposure());
        values.put(Process.COLUMN_PROCESS_PersonalProtectiveEquip, process.getPersonalProtectiveEquip());
        values.put(Process.COLUMN_PROCESS_DescPersonalControl, process.getDescPersonalControl());
        values.put(Process.COLUMN_PROCESS_TypeProcessControl, process.getTypeProcessControl());
        values.put(Process.COLUMN_PROCESS_DescProcessControl, process.getDescProcessControl());
        values.put(Process.COLUMN_PROCESS_MaterialCommercialName, process.getMaterialCommercialName());
        values.put(Process.COLUMN_PROCESS_FrequencyUsingMaterial, process.getFrequencyUsingMaterial());
        values.put(Process.COLUMN_PROCESS_Linked, String.valueOf(process.isLinked()));
        values.put(Process.COLUMN_PROCESS_Msds, String.valueOf(process.isMsds()));
        values.put(Process.COLUMN_PROCESS_TinnyAmount, String.valueOf(process.isTinnyAmount()));
        values.put(Process.COLUMN_PROCESS_FastChoice, process.getFastChoice());
        values.put(Process.COLUMN_PROCESS_factor, process.getFactor());
        values.put(Process.COLUMN_PROCESS_PolicyLevel, process.getPolicyLevel());
        values.put(Process.COLUMN_PROCESS_RegularityType, process.getRegularityType());
        values.put(Process.COLUMN_PROCESS_Unit, process.getUnit());
        values.put(Process.COLUMN_PROCESS_NumOfSample, process.get_numOfSample());
        values.put(Process.COLUMN_PROCESS_Ingredients, process.getIngredients());
        values.put(Process.COLUMN_PROCESS_Search_add,process.getSearch_add());
        values.put(Process.COLUMN_PROCESS_MaterialComponentPercent, process.getMaterialComponentPercent());

        //check if the data was insert
        long result=  db.insert(Process.TABLE,null,values);
        DatabaseMultipleManager.getInstance().closeDatabase();
        if(result == -1)
        {
            return false;
        }
        else
            return true;

    }
}
