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
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.ecocheck.ecocheck.R;
import org.ecocheck.ecocheck.adapter.DescPersonalControlAdapter;
import org.ecocheck.ecocheck.adapter.PersonalProtectiveEquipAdapter;
import org.ecocheck.ecocheck.database.PersonalExposureDatabase;
import org.ecocheck.ecocheck.dto.PersonalExposure;
import org.ecocheck.ecocheck.dto.Process;

import java.util.ArrayList;


public class PersonalControlExposureFragment extends Fragment implements View.OnClickListener {

    private Button mBtnAddExposure;
    private Button mBtnPrevious;

    // edit text and auto complete field for personal protective
    private AutoCompleteTextView editPersonalProtectiveEquip, editDescPersonalControl;

    private PersonalProtectiveEquipAdapter mPerProEquipTypeAdapter;
    private DescPersonalControlAdapter mDescPerControlAdapter;

    private PersonalExposureDatabase personalExposureDatabase;

    private Process process;

    private ArrayList<PersonalExposure> personalExposuresList = new ArrayList<>();

    private LinearLayout ll_personal_exposure;


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

        personalExposureDatabase = new PersonalExposureDatabase();

        ll_personal_exposure = (LinearLayout)rootView.findViewById(R.id.ll_personal_exposure);
        ll_personal_exposure.setVisibility(View.VISIBLE);

        mBtnAddExposure = (Button) rootView.findViewById(R.id.btn_add_processes_);
        mBtnPrevious = (Button) rootView.findViewById(R.id.btn_previous_exposure);

        // define personal protective fields to fragment factory process exposure.xml
        editPersonalProtectiveEquip = (AutoCompleteTextView) rootView.findViewById(R.id.atxt_personal_protective_equip_table_exposure);
        editDescPersonalControl = (AutoCompleteTextView) rootView.findViewById(R.id.atxt_desc_personal_control_table_exposure);


        personalExposuresList = personalExposureDatabase.getAllPersonalExposure(process);
        mPerProEquipTypeAdapter = new PersonalProtectiveEquipAdapter(getActivity(), personalExposuresList);
        editPersonalProtectiveEquip.setThreshold(1);
        //Set adapter to AutoCompleteTextView
        editPersonalProtectiveEquip.setAdapter(mPerProEquipTypeAdapter);


        editPersonalProtectiveEquip.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (personalExposuresList.size() > i) {

                    final String equipType = personalExposuresList.get(i).getPerProEquipType();
                    final ArrayList<PersonalExposure> descList = personalExposureDatabase.getSelectedPersonalExposure(process, equipType);
                    mDescPerControlAdapter = new DescPersonalControlAdapter(getActivity(), descList);
                    editDescPersonalControl.setThreshold(1);
                    //Set adapter to AutoCompleteTextView
                    editDescPersonalControl.setAdapter(mDescPerControlAdapter);
                }

            }
        });

        setListener();
    }


    public void setListener() {
        editPersonalProtectiveEquip.setOnClickListener(this);
        editDescPersonalControl.setOnClickListener(this);
        mBtnAddExposure.setOnClickListener(this);
        mBtnPrevious.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.atxt_personal_protective_equip_table_exposure:
                editPersonalProtectiveEquip.showDropDown();
                break;

            case R.id.atxt_desc_personal_control_table_exposure:
                editDescPersonalControl.showDropDown();
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

        if (editPersonalProtectiveEquip.getText().toString().trim().length() > 0) {

            if (personalExposureDatabase.isExists(process, editPersonalProtectiveEquip.getText().toString().trim())) {
                Toast.makeText(getActivity(), "סוג ציוד מגן אישי זה כבר קיים במערכת, אנא הכנס שם אחר", Toast.LENGTH_LONG).show();
            } else {
                PersonalExposure personalExposure = new PersonalExposure();
                personalExposure.setPerControlDesc(editDescPersonalControl.getText().toString().trim());
                personalExposure.setPerProEquipType(editPersonalProtectiveEquip.getText().toString().trim());
                personalExposure.setReportNo(process.getReportNumber());
                personalExposure.setDepartment(process.getDepartment());
                personalExposure.setAssignmentName(process.getAssignment());
                personalExposure.setProcess(process.getProcess());

                boolean isUpdated = personalExposureDatabase.insertExposure(personalExposure);

                if (isUpdated) {
                    Toast.makeText(getActivity(), "המידע נקלט בהצלחה", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "המידע לא נקלט במערכת, אנא נסה שוב", Toast.LENGTH_LONG).show();
                }
            }
        } else {
            Toast.makeText(getActivity(), "הזן שם ונסה שוב", Toast.LENGTH_LONG).show();
        }

    }

    /**
     * pop back previous fragment.
     */
    public void popBackFragment() {
        getActivity().getSupportFragmentManager().popBackStackImmediate();
    }


}

