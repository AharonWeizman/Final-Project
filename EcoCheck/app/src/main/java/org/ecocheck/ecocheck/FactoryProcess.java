package org.ecocheck.ecocheck;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.ecocheck.ecocheck.database.DatabaseMultipleManager;
import org.ecocheck.ecocheck.database.MultipleSurveyDatabase;
import org.ecocheck.ecocheck.database.ProcesDatabase;
import org.ecocheck.ecocheck.dto.*;
import org.ecocheck.ecocheck.dto.Process;
import org.ecocheck.ecocheck.fragment.FactoryProcessFragment;

public class FactoryProcess extends AppCompatActivity
{
    ProcesDatabase procesDatabase;

    // butoon add process data
    Button btnAddProcessData;

    //edit text field for report number
    EditText editReportNumber;

    //edit text field for department
    EditText editDepartment;

    //edit text field for assignment
    EditText editAssignment, editNumOfEmployee;

    //edit text and auto complete  and check box field for process
    AutoCompleteTextView editProcess,editProcessDuration,editProcessMethod,ediProcessAmount;
    EditText editProcessComment, editProcessRecommendation;
    CheckBox checkProcessBodyExposure,checkWarm,checkClosed;

    //edit text and auto complete field for chemical exposure
    AutoCompleteTextView editNotChemicalExposure;
    EditText editInhalantChemicalExposure;

    // edit text and auto complete field for personal protective
    AutoCompleteTextView editPersonalProtectiveEquip,editDescPersonalControl;


    //edit text field field of process control
    AutoCompleteTextView  editTypeProcessControl;
    EditText editDescProcessControl;

    // edit text  and check box field for materials associated tasks field
    AutoCompleteTextView editFrequencyUsingMaterial;
    EditText editMaterialCommercialName;
    CheckBox checkLinked, checkTinnyAmount,checkMsds;

    // edit text field for components and elements fields
    EditText editFastChoice,editFactor,editPolicyLevel,editRegularityType,editUnit,editNumOfSample,editIngredients,editSearch_add,editMaterialComponentPercent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factory_process);

//        MultipleSurveyDatabase multipleSurveyDatabase =
//        DatabaseMultipleManager.getInstance().openDatabase();

        loadFragment();
        procesDatabase= new ProcesDatabase();

        //define add process data button field to fragment factory process.xml
        btnAddProcessData= (Button)findViewById(R.id.btn_add_processes_);

        // define report number field to fragment factory process.xml
        editReportNumber=(EditText)findViewById(R.id.edt_report_no_factory_process);

        //define department field to fragment factory process.xml
        editDepartment= (EditText)findViewById(R.id.edt_department_factory_process);

        //define assignment field to fragment factory process.xml
        editAssignment= (EditText)findViewById(R.id.atxt_assignment_name_factory_process);
        editNumOfEmployee= (EditText)findViewById(R.id.edt_no_employee_factory_process);

        // define process fields to fragment factory process.xml
        editProcess=  (AutoCompleteTextView)findViewById(R.id.atxt_processes_table_process);
        editProcessComment= (EditText)findViewById(R.id.edt_comment_table_process);
        editProcessDuration= (AutoCompleteTextView)findViewById(R.id.atxt_duration_table_process);
        editProcessMethod= (AutoCompleteTextView)findViewById(R.id.atxt_method_table_process);
        checkProcessBodyExposure= (CheckBox)findViewById(R.id.chk_exposure_table_process);
        editProcessRecommendation= (EditText)findViewById(R.id.edt_recomm_summery_table_process);
        checkWarm= (CheckBox)findViewById(R.id.chk_cold_warm_table_process);
        checkClosed= (CheckBox)findViewById(R.id.chk_open_close_table_process);
        ediProcessAmount= (AutoCompleteTextView)findViewById(R.id.atxt_amount_table_process);

        // define chemical exposure fields to fragment factory process exposure.xml
        editNotChemicalExposure= (AutoCompleteTextView)findViewById(R.id.atxt_chemical_table_exposure);
        editInhalantChemicalExposure= (EditText)findViewById(R.id.edt_desc_inhalant_table_exposure);

        // define personal protective fields to fragment factory process exposure.xml
        editPersonalProtectiveEquip= (AutoCompleteTextView)findViewById(R.id.atxt_personal_protective_equip_table_exposure);
        editDescPersonalControl= (AutoCompleteTextView)findViewById(R.id.atxt_desc_personal_control_table_exposure);

        // define process control fields to fragment factory process exposure.xml
        editTypeProcessControl=(AutoCompleteTextView)findViewById(R.id.atxt_type_process_control_table_exposure);
        editDescProcessControl= (EditText)findViewById(R.id.edt_desc_process_control_table_exposure);

        // define materials associated tasks  fields to fragment factory process exposure.xml
        editMaterialCommercialName= (EditText)findViewById(R.id.atxt_material_name_table_exposure);
        editFrequencyUsingMaterial= (AutoCompleteTextView)findViewById(R.id.atxt_using_material_table_exposure);
        checkLinked= (CheckBox)findViewById(R.id.chk_linked_table_exposure);
        checkTinnyAmount= (CheckBox)findViewById(R.id.chk_tiny_amt_table_exposure);
        checkMsds= (CheckBox)findViewById(R.id.chk_msds_table_exposure);

        // define components and elements tasks  fields to fragment factory process exposure.xml
        editFastChoice= (EditText)findViewById(R.id.edt_quick_choice_table_exposure);
        editFactor= (EditText)findViewById(R.id.edt_factor_level_table_exposure);
        editPolicyLevel= (EditText)findViewById(R.id.edt_policy_level_table_exposure);
        editRegularityType=  (EditText)findViewById(R.id.edt_regularity_type_table_exposure);
        editUnit= (EditText)findViewById(R.id.edt_unit_table_exposure);
        editNumOfSample= (EditText)findViewById(R.id.edt_numOf_sample_table_exposure);
        editIngredients= (EditText)findViewById(R.id.edt_ingredients_table_exposure);
        editSearch_add= (EditText)findViewById(R.id.edt_search_add_table_exposure);
        editMaterialComponentPercent= (EditText)findViewById(R.id.edt_material_component_percent_table_exposure);

        //   ProcessData();

    }

    public void loadFragment()
    {
        Fragment fragment = new FactoryProcessFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.main_container, fragment);
        fragmentTransaction.commit();
    }

    public void ProcessData()
    {




        Process process = new Process();

        process.setReportNumber(Integer.parseInt(editReportNumber.getText().toString()));

        //insert department data
        process.setDepartment(editDepartment.getText().toString());

        //insert assignment data
        process.setAssignment(editAssignment.getText().toString());
        process.setNumOfEmployee(editNumOfEmployee.getText().toString());

        // insert process data
        process.setProcess(editProcess.getText().toString());
        process.setProcessComment(editProcessComment.getText().toString());
        process.setProcessDuration(editProcessDuration.getText().toString());
        process.setProcessMethod(editProcessMethod.getText().toString());
        process.setProcessRecommendation(editProcessRecommendation.getText().toString());
        process.setProcessAmount(ediProcessAmount.getText().toString());
        //check boolean body Exposure, and warm, and closed
        boolean isBodyExposure, isWarm, isClosed;

        if (checkProcessBodyExposure.isChecked()) {
            isBodyExposure = true;
        } else {
            isBodyExposure = false;
        }
        process.setProcessBodyExposure(isBodyExposure);

        if (checkWarm.isChecked())
            isWarm = true;
        else
            isWarm = false;
        process.setWarm(isWarm);

        if (checkClosed.isChecked())
            isClosed = true;
        else
            isClosed = false;
        process.setClosed(isClosed);

        boolean isLinked, isMSDS, isTinneyAmount;

        if (checkLinked.isChecked())
            isLinked = true;
        else
            isLinked = false;
//        process.setLinked(isLinked);

        if (checkMsds.isChecked())
            isMSDS = true;
        else
            isMSDS = false;
//        process.setMsds(isMSDS);

        if (checkTinnyAmount.isChecked())
            isTinneyAmount = true;
        else
            isTinneyAmount = false;


    }

    public void UpdateProcessData()
    {
        Process process = new Process();

        process.setReportNumber(Integer.parseInt(editReportNumber.getText().toString()));

        //insert department data
        process.setDepartment(editDepartment.getText().toString());

        //insert assignment data
        process.setAssignment(editAssignment.getText().toString());
        process.setNumOfEmployee(editNumOfEmployee.getText().toString());

        // insert process data
        process.setProcess(editProcess.getText().toString());
        process.setProcessComment(editProcessComment.getText().toString());
        process.setProcessDuration(editProcessDuration.getText().toString());
        process.setProcessMethod(editProcessMethod.getText().toString());
        process.setProcessRecommendation(editProcessRecommendation.getText().toString());
        process.setProcessAmount(ediProcessAmount.getText().toString());
        //check boolean body Exposure, and warm, and closed
        boolean isBodyExposure, isWarm, isClosed;

        if (checkProcessBodyExposure.isChecked()) {
            isBodyExposure = true;
        } else {
            isBodyExposure = false;
        }
        process.setProcessBodyExposure(isBodyExposure);

        if (checkWarm.isChecked())
            isWarm = true;
        else
            isWarm = false;
        process.setWarm(isWarm);

        if (checkClosed.isChecked())
            isClosed = true;
        else
            isClosed = false;
        process.setClosed(isClosed);

        boolean isLinked, isMSDS, isTinneyAmount;

        if (checkLinked.isChecked())
            isLinked = true;
        else
            isLinked = false;
//        process.setLinked(isLinked);

        if (checkMsds.isChecked())
            isMSDS = true;
        else
            isMSDS = false;
//        process.setMsds(isMSDS);

        if (checkTinnyAmount.isChecked())
            isTinneyAmount = true;
        else
            isTinneyAmount = false;


    }

    //enable option menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_eco_check, menu);
        return true;
    }

    @Override
    //method to enable menu bar click user choose
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        // case the user click the factory recommendation in the option menu it will open activity_factory_recommendations.xml
        if(id== R.id.factory_recommendations)
        {
            Intent intent = new Intent("org.ecocheck.ecocheck.FactoryRecommendation");//take the context from activity_factory_recommendations.xml
            startActivity(intent);
        }


        // case the user click the visit factory in the option menu it will open activity_visit_factory.xml
        if(id== R.id.visit_factory)
        {
            Intent intent = new Intent("org.ecocheck.ecocheck.VisitFactory");//take the context from activity_visit_factory.xml
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
