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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.ecocheck.ecocheck.R;
import org.ecocheck.ecocheck.adapter.TypeProcessControlAdapter;
import org.ecocheck.ecocheck.database.TypeProcessControlDatabase;
import org.ecocheck.ecocheck.dto.Process;
import org.ecocheck.ecocheck.dto.TypeProcessControl;

import java.util.ArrayList;


public class TypeProcessControlFragment extends Fragment implements View.OnClickListener {

    private Button mBtnAddExposure;
    private Button mBtnPrevious;

    //edit text field field of process control
    private AutoCompleteTextView editTypeProcessControl;
    private EditText editDescProcessControl;

    private TypeProcessControlAdapter mTypeProControlAdapter;

    private Process process;

    private TypeProcessControlDatabase typeProcessControlDatabase;

    private ArrayList<TypeProcessControl> typeProcessControlList = new ArrayList<>();

    private LinearLayout ll_type_process;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_factory_process_exposure, container, false);

        if (getArguments() != null) {
            process = (Process) getArguments().getSerializable("process");
        }


        init(rootView);
        setListener();

        return rootView;
    }

    public void init(View rootView) {

        typeProcessControlDatabase = new TypeProcessControlDatabase();

        ll_type_process = (LinearLayout)rootView.findViewById(R.id.ll_type_process);
        ll_type_process.setVisibility(View.VISIBLE);

        mBtnAddExposure = (Button) rootView.findViewById(R.id.btn_add_processes_);
        mBtnPrevious = (Button) rootView.findViewById(R.id.btn_previous_exposure);

        // define process control fields to fragment factory process exposure.xml
        editTypeProcessControl = (AutoCompleteTextView) rootView.findViewById(R.id.atxt_type_process_control_table_exposure);
        editDescProcessControl = (EditText) rootView.findViewById(R.id.edt_desc_process_control_table_exposure);

        typeProcessControlList = typeProcessControlDatabase.getAllTypeProcess(process);
        mTypeProControlAdapter = new TypeProcessControlAdapter(getActivity(), typeProcessControlList);
        editTypeProcessControl.setThreshold(1);
        //Set adapter to AutoCompleteTextView
        editTypeProcessControl.setAdapter(mTypeProControlAdapter);

        editTypeProcessControl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (typeProcessControlList.size() > i) {
                    editDescProcessControl.setText(typeProcessControlList.get(i).getDescOfProcessControl());
                }
            }
        });

    }

    public void setListener() {
        mBtnAddExposure.setOnClickListener(this);
        mBtnPrevious.setOnClickListener(this);
        editTypeProcessControl.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.atxt_type_process_control_table_exposure:
                editTypeProcessControl.showDropDown();
                break;

            case R.id.btn_previous_exposure:
                popBackFragment();
                break;

            case R.id.btn_add_processes_:
                addTypeProcessControl();
                break;

        }

    }

    public void addTypeProcessControl() {

        if (editTypeProcessControl.getText().toString().trim().length() > 0) {

            if (typeProcessControlDatabase.isExists(process, editTypeProcessControl.getText().toString().trim())) {
                Toast.makeText(getActivity(), "סוג בקרה על תהליך זה קיים במערכת, אנא הכנס שם אחר", Toast.LENGTH_LONG).show();
            } else {
                TypeProcessControl typeProcessControl = new TypeProcessControl();
                typeProcessControl.setTypeOfProcessControl(editTypeProcessControl.getText().toString().trim());
                typeProcessControl.setDescOfProcessControl(editDescProcessControl.getText().toString().trim());
                typeProcessControl.setReportNo(process.getReportNumber());
                typeProcessControl.setDepartment(process.getDepartment());
                typeProcessControl.setAssignmentName(process.getAssignment());
                typeProcessControl.setProcess(process.getProcess());

                boolean isUpdated = typeProcessControlDatabase.insertTypeProcessControl(typeProcessControl);

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

