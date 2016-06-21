package org.ecocheck.ecocheck.fragment;

/**
 * Created by Ron on 26/05/2016.
 */


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.ecocheck.ecocheck.R;
import org.ecocheck.ecocheck.database.MaterialAssociatedTaskDatabase;
import org.ecocheck.ecocheck.dto.ChemicalExposure;
import org.ecocheck.ecocheck.dto.MaterialAssociatedTask;
import org.ecocheck.ecocheck.dto.Process;


public class MaterialAssociatedTaskFragment extends Fragment implements View.OnClickListener {

    private Button mBtnAddExposure;
    private Button mBtnPrevious;

    // edit text  and check box field for materials associated tasks field
    private AutoCompleteTextView editFrequencyUsingMaterial;
    private EditText editMaterialCommercialName;
    private CheckBox checkLinked, checkTinnyAmount, checkMsds;

    private ArrayAdapter<String> mUsingMaterialAdapter;

    private MaterialAssociatedTaskDatabase materialAssociatedTaskDatabase;

    private Process process;

    private LinearLayout ll_material_asso_task;

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

        materialAssociatedTaskDatabase = new MaterialAssociatedTaskDatabase();

        ll_material_asso_task = (LinearLayout)rootView.findViewById(R.id.ll_material_asso_task);
        ll_material_asso_task.setVisibility(View.VISIBLE);

        mBtnAddExposure = (Button) rootView.findViewById(R.id.btn_add_processes_);
        mBtnPrevious = (Button) rootView.findViewById(R.id.btn_previous_exposure);

        // define materials associated tasks  fields to fragment factory process exposure.xml
        editMaterialCommercialName = (EditText) rootView.findViewById(R.id.atxt_material_name_table_exposure);
        editFrequencyUsingMaterial = (AutoCompleteTextView) rootView.findViewById(R.id.atxt_using_material_table_exposure);
        checkLinked = (CheckBox) rootView.findViewById(R.id.chk_linked_table_exposure);
        checkTinnyAmount = (CheckBox) rootView.findViewById(R.id.chk_tiny_amt_table_exposure);
        checkMsds = (CheckBox) rootView.findViewById(R.id.chk_msds_table_exposure);


        mUsingMaterialAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, getActivity().getResources().getStringArray(R.array.arr_using_material));
        editFrequencyUsingMaterial.setThreshold(1);
        //Set adapter to AutoCompleteTextView
        editFrequencyUsingMaterial.setAdapter(mUsingMaterialAdapter);

        setListener();
    }

    public void setListener() {
        editFrequencyUsingMaterial.setOnClickListener(this);
        mBtnAddExposure.setOnClickListener(this);
        mBtnPrevious.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.atxt_using_material_table_exposure:
                editFrequencyUsingMaterial.showDropDown();
                break;

            case R.id.btn_previous_exposure:
                popBackFragment();
                break;

            case R.id.btn_add_processes_:
                addChemicalExposure();
                break;

        }

    }


    public void addChemicalExposure() {

        if (editMaterialCommercialName.getText().toString().trim().length() > 0) {

            if(materialAssociatedTaskDatabase.isExists(process, editMaterialCommercialName.getText().toString().trim())){
                Toast.makeText(getActivity(), "שם מסחרי זה כבר קיים, אנא הכנס שם אחר", Toast.LENGTH_SHORT).show();
            }else{

                MaterialAssociatedTask materialAssociatedTask = new MaterialAssociatedTask();
                materialAssociatedTask.setmMSDS(checkMsds.isChecked());
                materialAssociatedTask.setmTinyAmount(checkTinnyAmount.isChecked());
                materialAssociatedTask.setmLinked(checkLinked.isChecked());
                materialAssociatedTask.setUsingMaterial(editFrequencyUsingMaterial.getText().toString().trim());
                materialAssociatedTask.setMaterialTradeName(editMaterialCommercialName.getText().toString().trim());

                materialAssociatedTask.setReportNo(process.getReportNumber());
                materialAssociatedTask.setDepartment(process.getDepartment());
                materialAssociatedTask.setAssignmentName(process.getAssignment());
                materialAssociatedTask.setProcess(process.getProcess());

                boolean isUpdated = materialAssociatedTaskDatabase.insertMaterialAssoTask(materialAssociatedTask);

                if (isUpdated) {
                    Toast.makeText(getActivity(), "המידע נקלט בהצלחה", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "המידע לא נקלט במערכת אנא נסה שוב", Toast.LENGTH_SHORT).show();
                }

            }

        } else {
            Toast.makeText(getActivity(), "הזן שם ונסה שוב", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * pop back previous fragment.
     */
    public void popBackFragment() {
        getActivity().getSupportFragmentManager().popBackStackImmediate();
    }

}

