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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.ecocheck.ecocheck.R;
import org.ecocheck.ecocheck.adapter.ChemicalExposureAdapter;
import org.ecocheck.ecocheck.database.ChemicalExposureDatabase;
import org.ecocheck.ecocheck.dto.ChemicalExposure;
import org.ecocheck.ecocheck.dto.Process;

import java.util.ArrayList;


public class ChemicalExposureFragment extends Fragment implements View.OnClickListener {

    private ChemicalExposureAdapter mChemicalExpoNameAdapter;

    private Button mBtnAddExposure;
    private Button mBtnPrevious;

    //edit text and auto complete field for chemical exposure
    private EditText editInhalantChemicalExposure;
    private AutoCompleteTextView editNotChemicalExposure;

    private Process process;

    private ChemicalExposureDatabase chemicalExposureDatabase;

    private ArrayList<ChemicalExposure> chemicalExposuresList;

    private LinearLayout ll_chemical_exposure;

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

        chemicalExposureDatabase = new ChemicalExposureDatabase();

        ll_chemical_exposure = (LinearLayout)rootView.findViewById(R.id.ll_chemical_exposure);
        ll_chemical_exposure.setVisibility(View.VISIBLE);

        mBtnAddExposure = (Button) rootView.findViewById(R.id.btn_add_processes_);
        mBtnPrevious = (Button) rootView.findViewById(R.id.btn_previous_exposure);

        // define chemical exposure fields to fragment factory process exposure.xml
        editNotChemicalExposure = (AutoCompleteTextView) rootView.findViewById(R.id.atxt_chemical_table_exposure);
        editInhalantChemicalExposure = (EditText) rootView.findViewById(R.id.edt_desc_inhalant_table_exposure);



        if(process!=null){
            chemicalExposuresList =   chemicalExposureDatabase.getAllChemicalExposure(process);
            mChemicalExpoNameAdapter = new ChemicalExposureAdapter(getActivity(), chemicalExposuresList);
            editNotChemicalExposure.setThreshold(1);
            //Set adapter to AutoCompleteTextView
            editNotChemicalExposure.setAdapter(mChemicalExpoNameAdapter);
        }

        editNotChemicalExposure.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(chemicalExposuresList.size()>i){
                    editInhalantChemicalExposure.setText(chemicalExposuresList.get(i).getDescInhalantExposure());
                }
            }
        });

        setListener();
    }

    public void setListener() {
        mBtnAddExposure.setOnClickListener(this);
        mBtnPrevious.setOnClickListener(this);
        editNotChemicalExposure.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.atxt_chemical_table_exposure:
                editNotChemicalExposure.showDropDown();
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

        if (editNotChemicalExposure.getText().toString().trim().length() > 0) {

            if(chemicalExposureDatabase.isExists(process, editNotChemicalExposure.getText().toString().trim())){
                Toast.makeText(getActivity(), "חשיפה לא כימית זו כבר קיימת במערכת, אנא הכנס שם אחר", Toast.LENGTH_LONG).show();
            }else{
                ChemicalExposure chemicalExposure = new ChemicalExposure();
                chemicalExposure.setChemicalExposureName(editNotChemicalExposure.getText().toString().trim());
                chemicalExposure.setDescInhalantExposure(editInhalantChemicalExposure.getText().toString().trim());
                chemicalExposure.setReportNo(process.getReportNumber());
                chemicalExposure.setDepartment(process.getDepartment());
                chemicalExposure.setAssignmentName(process.getAssignment());
                chemicalExposure.setProcess(process.getProcess());

                boolean isUpdated = chemicalExposureDatabase.insertChemicalExposure(chemicalExposure);

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

