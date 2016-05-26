package org.ecocheck.ecocheck.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.ecocheck.ecocheck.FactoryProcess;
import org.ecocheck.ecocheck.R;

/**
 * Created by Ron on 4/14/16.
 */
public class FactoryProcessExposureFragment extends Fragment implements View.OnClickListener
{


    private Button mBtnAddExposure;
    private Button mBtnPrevious;
    private AutoCompleteTextView mAtxtChemicalExposureName;
    private AutoCompleteTextView mAtxtPerProEquipType;
    private AutoCompleteTextView mAtxtDescPerControl;
    private AutoCompleteTextView mAtxtTypeProControl;
   // private AutoCompleteTextView mAtxtMaterialName;
    private AutoCompleteTextView mAtxtUsingMaterial;

    private ArrayAdapter<String> mChemicalExpoNameAdapter;
    private ArrayAdapter<String> mPerProEquipTypeAdapter;
    private ArrayAdapter<String> mDescPerControlAdapter;
    private ArrayAdapter<String> mTypeProControlAdapter;
    private ArrayAdapter<String> mMaterialNameAdapter;
    private ArrayAdapter<String> mUsingMaterialAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_factory_process_exposure, container, false);

        init(rootView);


        return rootView;
    }

    public void init(View rootView) {

        mBtnAddExposure = (Button) rootView.findViewById(R.id.btn_add_exposure_factory_process);
        mBtnPrevious = (Button) rootView.findViewById(R.id.btn_previous_exposure);

        mAtxtChemicalExposureName = (AutoCompleteTextView) rootView.findViewById(R.id.atxt_chemical_table_exposure);
        mAtxtPerProEquipType = (AutoCompleteTextView) rootView.findViewById(R.id.atxt_personal_protective_equip_table_exposure);
        mAtxtDescPerControl = (AutoCompleteTextView) rootView.findViewById(R.id.atxt_desc_personal_control_table_exposure);
        mAtxtTypeProControl = (AutoCompleteTextView) rootView.findViewById(R.id.atxt_type_process_control_table_exposure);
   //     mAtxtMaterialName = (AutoCompleteTextView) rootView.findViewById(R.id.atxt_material_name_table_exposure);
        mAtxtUsingMaterial = (AutoCompleteTextView) rootView.findViewById(R.id.atxt_using_material_table_exposure);

        mChemicalExpoNameAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, getActivity().getResources().getStringArray(R.array.arr_chemical_exposure));
        mAtxtChemicalExposureName.setThreshold(1);
        //Set adapter to AutoCompleteTextView
        mAtxtChemicalExposureName.setAdapter(mChemicalExpoNameAdapter);

        mPerProEquipTypeAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, getActivity().getResources().getStringArray(R.array.arr_personal_pro_equip));
        mAtxtPerProEquipType.setThreshold(1);
        //Set adapter to AutoCompleteTextView
        mAtxtPerProEquipType.setAdapter(mPerProEquipTypeAdapter);

        mDescPerControlAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, getActivity().getResources().getStringArray(R.array.arr_desc_personal_control));
        mAtxtDescPerControl.setThreshold(1);
        //Set adapter to AutoCompleteTextView
        mAtxtDescPerControl.setAdapter(mDescPerControlAdapter);

        mTypeProControlAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, getActivity().getResources().getStringArray(R.array.arr_type_process_control));
        mAtxtTypeProControl.setThreshold(1);
        //Set adapter to AutoCompleteTextView
        mAtxtTypeProControl.setAdapter(mTypeProControlAdapter);

     //   mMaterialNameAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, getActivity().getResources().getStringArray(R.array.arr_material_trade_name));
      //  mAtxtMaterialName.setThreshold(1);
        //Set adapter to AutoCompleteTextView
    //    mAtxtMaterialName.setAdapter(mMaterialNameAdapter);

        mUsingMaterialAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, getActivity().getResources().getStringArray(R.array.arr_using_material));
        mAtxtUsingMaterial.setThreshold(1);
        //Set adapter to AutoCompleteTextView
        mAtxtUsingMaterial.setAdapter(mUsingMaterialAdapter);

        setListener();

    }

    public void setListener() {
        mBtnAddExposure.setOnClickListener(this);
        mBtnPrevious.setOnClickListener(this);
        mAtxtChemicalExposureName.setOnClickListener(this);
        mAtxtPerProEquipType.setOnClickListener(this);
        mAtxtDescPerControl.setOnClickListener(this);
        mAtxtTypeProControl.setOnClickListener(this);
   //     mAtxtMaterialName.setOnClickListener(this);
        mAtxtUsingMaterial.setOnClickListener(this);
    }

    /**
     * to add assignment in the system
     */
    public void addChemicalExposure() {

        LayoutInflater li = LayoutInflater.from(getActivity());
        View promptsView = li.inflate(R.layout.layout_add_dialog, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getActivity());


        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.edt_name_add_dialog);

        final TextView txtMessage = (TextView) promptsView
                .findViewById(R.id.txt_name_add_dialog);

        txtMessage.setText(getString(R.string.msg_add_chemical_exposure));

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // get user input and set it to result
                                // edit text
                                mAtxtChemicalExposureName.setText(userInput.getText());
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn_add_exposure_factory_process:
                addChemicalExposure();
                break;

            case R.id.btn_previous_exposure:
                popBackFragment();
                break;

            case R.id.atxt_chemical_table_exposure:
                mAtxtChemicalExposureName.showDropDown();
                break;

            case R.id.atxt_personal_protective_equip_table_exposure:
                mAtxtPerProEquipType.showDropDown();
                break;

            case R.id.atxt_desc_personal_control_table_exposure:
                mAtxtDescPerControl.showDropDown();
                break;

            case R.id.atxt_type_process_control_table_exposure:
                mAtxtTypeProControl.showDropDown();
                break;

    //        case R.id.atxt_material_name_table_exposure:
      //          mAtxtMaterialName.showDropDown();
      //          break;

            case R.id.atxt_using_material_table_exposure:
                mAtxtUsingMaterial.showDropDown();
                break;

        }

    }

    /**
     * pop back previous fragment.
     */
    public void popBackFragment(){
        getActivity().getSupportFragmentManager().popBackStackImmediate();
    }
}

