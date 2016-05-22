package org.ecocheck.ecocheck.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
public class FactoryProcessFragment extends Fragment implements View.OnClickListener {

    private Button mBtnNext;
    private Button mBtnAddDept;
    private Button mBtnAddAssignment;
    private Button mBtnAddProcess;

    FactoryProcess factoryProcess;
    private EditText mEdtDeptName;
    private EditText mAtxtAssignmentName;
    // private AutoCompleteTextView mAtxtAssignmentName;
    private AutoCompleteTextView mAtxtProcessName;
    private AutoCompleteTextView mAtxtDrationProcess;
    private AutoCompleteTextView mAtxtMethod;
    private AutoCompleteTextView mAtxtAmount;

    //   private ArrayAdapter<String> mAssignmentAdapter;
    private ArrayAdapter<String> mProcessAdapter;
    private ArrayAdapter<String> mDurationAdapter;
    private ArrayAdapter<String> mAmountAdapter;
    private ArrayAdapter<String> mMethodAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_factory_process, container, false);

        init(rootView);

        factoryProcess= new FactoryProcess();
   //     AddProcessData();

        return rootView;
    }

    /**
     * initialize UI component in the class
     *
     * @param rootView
     */
    public void init(View rootView) {

        mBtnNext = (Button) rootView.findViewById(R.id.btn_next);
        mBtnAddAssignment = (Button)rootView.findViewById(R.id.btn_add_assignment_factory_process);
        mBtnAddDept = (Button)rootView.findViewById(R.id.btn_add_dept_factory_process);
        mBtnAddProcess = (Button)rootView.findViewById(R.id.btn_add_process_factory_process);

        mEdtDeptName = (EditText)rootView.findViewById(R.id.edt_department_factory_process);
        mAtxtAssignmentName = (EditText)rootView.findViewById(R.id.edt_assignment_name_factory_process);
        mAtxtProcessName = (AutoCompleteTextView) rootView.findViewById(R.id.atxt_processes_table_process);
        mAtxtDrationProcess = (AutoCompleteTextView) rootView.findViewById(R.id.atxt_duration_table_process);
        mAtxtAmount = (AutoCompleteTextView) rootView.findViewById(R.id.atxt_amount_table_process);
        mAtxtMethod = (AutoCompleteTextView) rootView.findViewById(R.id.atxt_method_table_process);


        //enable the array adapter for drop dawn list
        mProcessAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, getActivity().getResources().getStringArray(R.array.arr_process));
        mAtxtProcessName.setThreshold(1);
        //Set adapter to AutoCompleteTextView
        mAtxtProcessName.setAdapter(mProcessAdapter);

        mDurationAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, getActivity().getResources().getStringArray(R.array.arr_duration));
        mAtxtDrationProcess.setThreshold(1);
        //Set adapter to AutoCompleteTextView
        mAtxtDrationProcess.setAdapter(mDurationAdapter);


        mAmountAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, getActivity().getResources().getStringArray(R.array.arr_amount));
        mAtxtAmount.setThreshold(1);
        //Set adapter to AutoCompleteTextView
        mAtxtAmount.setAdapter(mAmountAdapter);


        mMethodAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, getActivity().getResources().getStringArray(R.array.arr_method));
        mAtxtMethod.setThreshold(1);
        //Set adapter to AutoCompleteTextView
        mAtxtMethod.setAdapter(mMethodAdapter);


        setListener();
    }

    /**
     * sets listener for the UI elements.
     */
    public void setListener() {
        mBtnNext.setOnClickListener(this);
        mBtnAddProcess.setOnClickListener(this);
        mBtnAddDept.setOnClickListener(this);
        mBtnAddAssignment.setOnClickListener(this);
        mAtxtAmount.setOnClickListener(this);
        mAtxtProcessName.setOnClickListener(this);
        mAtxtAssignmentName.setOnClickListener(this);
        mAtxtDrationProcess.setOnClickListener(this);
        mAtxtMethod.setOnClickListener(this);
    }


    @Override
    public void onClick(View view)
    {

        switch (view.getId())
        {
            case R.id.btn_next:
                loadNextFragment();
                break;

            case R.id.btn_add_assignment_factory_process:
                addAssignment();
                break;

            case R.id.btn_add_dept_factory_process:
                addDepartment();
                break;

            case R.id.btn_add_process_factory_process:
                addProcess();
                break;

     /*       case R.id.atxt_assignment_name_factory_process:
                mAtxtAssignmentName.showDropDown();
                break;*/

            case R.id.atxt_processes_table_process:
                mAtxtProcessName.showDropDown();
                break;

            case R.id.atxt_duration_table_process:
                mAtxtDrationProcess.showDropDown();
                break;

            case R.id.atxt_amount_table_process:
                mAtxtAmount.showDropDown();
                break;

            case R.id.atxt_method_table_process:
                mAtxtMethod.showDropDown();
                break;


        }

    }

    /**
     * to load next exposure fragment.
     */
    public void loadNextFragment() {
        Fragment fragment = new FactoryProcessExposureFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.main_container, fragment);
        fragmentTransaction.addToBackStack("factory_process");
        fragmentTransaction.commit();

    }

    /**
     * to add department in the system
     */
    public void addDepartment(){

        LayoutInflater li = LayoutInflater.from(getActivity());
        View promptsView = li.inflate(R.layout.layout_add_dialog, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getActivity());

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.edt_name_add_dialog);

        final TextView txtMessage = (TextView) promptsView
                .findViewById(R.id.txt_name_add_dialog);

        txtMessage.setText(getString(R.string.msg_add_department));

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and set it to result
                                // edit text
                                mEdtDeptName.setText(userInput.getText());
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

    /**
     * dialog to add process in the system
     */
    public void addProcess(){

        LayoutInflater li = LayoutInflater.from(getActivity());
        View promptsView = li.inflate(R.layout.layout_add_dialog, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getActivity());

        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.edt_name_add_dialog);

        final TextView txtMessage = (TextView) promptsView
                .findViewById(R.id.txt_name_add_dialog);

        txtMessage.setText(getString(R.string.msg_add_process));

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and set it to result
                                // edit text
                                mAtxtProcessName.setText(userInput.getText());
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

    /**
     * to add assignment in the system
     */
    public void addAssignment(){

        LayoutInflater li = LayoutInflater.from(getActivity());
        View promptsView = li.inflate(R.layout.layout_add_dialog, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getActivity());


        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.edt_name_add_dialog);

        final TextView txtMessage = (TextView) promptsView
                .findViewById(R.id.txt_name_add_dialog);
        txtMessage.setText(getString(R.string.msg_add_assignment));

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and set it to result
                                // edit text
                                mAtxtAssignmentName.setText(userInput.getText());
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




}
