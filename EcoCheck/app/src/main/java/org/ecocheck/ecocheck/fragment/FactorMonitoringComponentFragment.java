package org.ecocheck.ecocheck.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.ecocheck.ecocheck.R;
import org.ecocheck.ecocheck.database.FactorMonitoringComponentDatabase;
import org.ecocheck.ecocheck.dto.FactorsMonitoringComponent;
import org.ecocheck.ecocheck.dto.Process;

/**
 * Created by Ron on 4/14/16.
 */
public class FactorMonitoringComponentFragment extends Fragment implements View.OnClickListener {

    private Button mBtnAddExposure;
    private Button mBtnPrevious;

    private Process process;

    private FactorMonitoringComponentDatabase componentDatabase;



    // edit text field for components and elements fields
    private EditText editFastChoice, editFactor, editPolicyLevel, editRegularityType, editUnit, editNumOfSample, editIngredients, editSearch_add, editMaterialComponentPercent;

    private LinearLayout ll_factor_monitoring_component;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_factory_process_exposure, container, false);

        if (getArguments() != null) {
            process = (Process) getArguments().getSerializable("process");
        }


        init(rootView);


        return rootView;
    }

    public void init(View rootView) {


        componentDatabase = new FactorMonitoringComponentDatabase();

        ll_factor_monitoring_component = (LinearLayout)rootView.findViewById(R.id.ll_factor_monitoring_component);
        ll_factor_monitoring_component.setVisibility(View.VISIBLE);

        mBtnAddExposure = (Button) rootView.findViewById(R.id.btn_add_processes_);
        mBtnPrevious = (Button) rootView.findViewById(R.id.btn_previous_exposure);



        // define components and elements tasks  fields to fragment factory process exposure.xml
        editFastChoice = (EditText) rootView.findViewById(R.id.edt_quick_choice_table_exposure);
        editFactor = (EditText) rootView.findViewById(R.id.edt_factor_level_table_exposure);
        editPolicyLevel = (EditText) rootView.findViewById(R.id.edt_policy_level_table_exposure);
        editRegularityType = (EditText) rootView.findViewById(R.id.edt_regularity_type_table_exposure);
        editUnit = (EditText) rootView.findViewById(R.id.edt_unit_table_exposure);
        editNumOfSample = (EditText) rootView.findViewById(R.id.edt_numOf_sample_table_exposure);
        editIngredients = (EditText) rootView.findViewById(R.id.edt_ingredients_table_exposure);
        editSearch_add = (EditText) rootView.findViewById(R.id.edt_search_add_table_exposure);
        editMaterialComponentPercent = (EditText) rootView.findViewById(R.id.edt_material_component_percent_table_exposure);

        setListener();

    }

    public void setListener() {
        mBtnAddExposure.setOnClickListener(this);
        mBtnPrevious.setOnClickListener(this);




    }


    public void addFactoryMonitoringComponents() {

        if (editFastChoice.getText().toString().trim().length() > 0) {

            if (componentDatabase.isExists(process, editFastChoice.getText().toString().trim())) {
                Toast.makeText(getActivity(), "בחירה מהירה זו כבר קיימת במערכת, אנא הכנס שם אחר", Toast.LENGTH_LONG).show();
            } else {

                FactorsMonitoringComponent component = new FactorsMonitoringComponent();

                component.setReportNo(process.getReportNumber());
                component.setDepartment(process.getDepartment());
                component.setAssignmentName(process.getAssignment());
                component.setProcess(process.getProcess());
                component.setQuickChoice(editFastChoice.getText().toString());
                component.setFactor(editFactor.getText().toString());
                component.setPolicyLevel(editPolicyLevel.getText().toString());
                component.setRegularityType(editRegularityType.getText().toString());
                component.setUnit(editUnit.getText().toString());
                component.setNoOfSample(editNumOfSample.getText().toString());
                component.setIngredients(editIngredients.getText().toString());
                component.setSearchAdd(editSearch_add.getText().toString());
                component.setMaterialComponentPresent(editMaterialComponentPercent.getText().toString());


                //check if the data was insert to db
                boolean isInserted = componentDatabase.insertFactorComponent(component);
                if (isInserted == true)
                    Toast.makeText(getActivity(), "המידע נקלט בהצלחה", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getActivity(), "המידע לא נקלט במערכת, אנא נסה שוב", Toast.LENGTH_LONG).show();

            }


        } else {
            Toast.makeText(getActivity(), "הזן שם ונסה שוב", Toast.LENGTH_LONG).show();
        }


    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn_previous_exposure:
                popBackFragment();
                break;

            case R.id.btn_add_processes_:
                addFactoryMonitoringComponents();
                break;

        }

    }

    /**
     * pop back previous fragment.
     */
    public void popBackFragment() {
        getActivity().getSupportFragmentManager().popBackStackImmediate();
    }
}


