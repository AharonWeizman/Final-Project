package org.ecocheck.ecocheck.showSurvey;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.print.PrintAttributes;
import android.print.pdf.PrintedPdfDocument;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import org.ecocheck.ecocheck.R;
import org.ecocheck.ecocheck.adapter.DepartmentAdapter;
import org.ecocheck.ecocheck.adapter.SeparatedListAdapter;
import org.ecocheck.ecocheck.database.AssignmentDatabase;
import org.ecocheck.ecocheck.database.ChemicalExposureDatabase;
import org.ecocheck.ecocheck.database.FactorMonitoringComponentDatabase;
import org.ecocheck.ecocheck.database.MaterialAssociatedTaskDatabase;
import org.ecocheck.ecocheck.database.MultipleSurveyDatabase;
import org.ecocheck.ecocheck.database.PersonalExposureDatabase;
import org.ecocheck.ecocheck.database.ProcesDatabase;
import org.ecocheck.ecocheck.database.TypeProcessControlDatabase;
import org.ecocheck.ecocheck.dto.Assignment;
import org.ecocheck.ecocheck.dto.ChemicalExposure;
import org.ecocheck.ecocheck.dto.Department;
import org.ecocheck.ecocheck.dto.FactorsMonitoringComponent;
import org.ecocheck.ecocheck.dto.Material;
import org.ecocheck.ecocheck.dto.MaterialAssociatedTask;
import org.ecocheck.ecocheck.dto.PersonalExposure;
import org.ecocheck.ecocheck.dto.Process;
import org.ecocheck.ecocheck.dto.TypeProcessControl;
import org.ecocheck.ecocheck.dto.Visits;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class showProcessSurvey extends AppCompatActivity  implements AdapterView.OnItemSelectedListener
{

    public final static String ITEM_TITLE = "title";
    public final static String ITEM_CAPTION = "caption";

    public Map<String,?> createItem(String title, String caption) {
        Map<String,String> item = new HashMap<String,String>();
        item.put(ITEM_TITLE, title);
        item.put(ITEM_CAPTION, caption);
        return item;
    }


    TextView reportNumberText;




    MultipleSurveyDatabase multipleSurveyDatabase;
    ArrayList<String> allDepartment;
    ArrayList<String> allAssignment;
    ArrayList<Process> mProcessList;
    ArrayList<Assignment>allAssigmentsObject;
    int reportNo;
    String factoryName;

    ArrayList<Assignment> mAssignmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_process_survey);
        reportNumberText = (TextView) findViewById(R.id.hold_report_nimber_conect);


        Intent intentExtras = getIntent();

        // get the value according the key from show visit
        String reportNumber = intentExtras.getStringExtra("reportNumber");
        factoryName = intentExtras.getStringExtra("factoryName");
        reportNumberText.setText(reportNumber);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        //take the string report number and do value of integer
        this.reportNo = Integer.valueOf(reportNumber);
        multipleSurveyDatabase = new MultipleSurveyDatabase();
        // thake all department with same report number and put in string arrayList
        allDepartment = multipleSurveyDatabase.getAllDepartmentDataSameReport(this.reportNo);
        int index = 0;
        //put all the department in String Array
        String[] arrayAllDepartment = new String[allDepartment.size()];
        for (String Dep : allDepartment) {
            arrayAllDepartment[index] = Dep;
            index++;
        }

        // choose with spinner one of the department from database
        Spinner spinner1 = (Spinner) findViewById(R.id.departmentsSpinner);
        spinner1.setOnItemSelectedListener(this);



        //put all department from datanase in array string
        String[] allDepartmentArr = new String[allDepartment.size()];
        allDepartmentArr = allDepartment.toArray(allDepartmentArr);

        SpinnerAdapter dataAdapter = new SpinnerAdapter(this, allDepartmentArr);

        spinner1.setAdapter(dataAdapter);

        View content = getContentView();
     //   content.setVisibility(View.INVISIBLE);


    }

    //spinnet listener
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id)
    {
        // An item was selected.  retrieve the selected item using

        Spinner spinner = (Spinner) parent;
        if(spinner.getId() == R.id.departmentsSpinner)
        {
            String departmentName = (String) parent.getItemAtPosition(pos);

            AssignmentDatabase assignmentDatabase = new AssignmentDatabase();

            String reportNoString = String.valueOf(this.reportNo);

            // get all the assignment that conect to the report number and the departmnet
            mAssignmentList = assignmentDatabase.getAllAssignment(reportNoString,
                    departmentName);

            List<String> list = new ArrayList<String>();

            for (Assignment assignment : mAssignmentList) {
                String rep = String.format("%s - %s",
                        assignment.getAssignmentName(),
                        assignment.getEmployeeNo());

                list.add(rep);
            }

            // with spinner choose the assignment from the list
            Spinner spinner1 = (Spinner) findViewById(R.id.assignmentSpinner);
            spinner1.setOnItemSelectedListener(this);

            // create array string that will hold all the assignment in aray
            String[] allAssignsArr = new String[list.size()];
            allAssignsArr = list.toArray(allAssignsArr);

            SpinnerAdapter dataAdapter = new SpinnerAdapter(this, allAssignsArr);
            spinner1.setAdapter(dataAdapter);

            //reset control
            mProcessList = null;
            Spinner spinnerProcess = (Spinner) findViewById(R.id.processSpinner);
            spinnerProcess.setOnItemSelectedListener(this);

            String[] allProcessArr = new String[0];
            SpinnerAdapter processDataAdapter = new SpinnerAdapter(this, allProcessArr);
            spinnerProcess.setAdapter(processDataAdapter);




            SeparatedListAdapter adapter = new SeparatedListAdapter(this);
            ListView listView = (ListView) findViewById(R.id.list_process_data);
            listView.setAdapter( adapter );
            ///reset listview height
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = 0;
            listView.requestLayout();
//        }
        }
        else if(spinner.getId() == R.id.assignmentSpinner)
        {
            if (this.mAssignmentList.size() == 0){
                return;
            }

            // test for pos > that current mAssignmentList, it should not happen if  double checking
            if (pos >= this.mAssignmentList.size()){
                //invalid
                return;
            }
            //get process data from assignment
            Assignment assignment = this.mAssignmentList.get(pos);

            ProcesDatabase procesDatabase = new ProcesDatabase();


             mProcessList = new ArrayList<>();
            String reportNoString = String.valueOf(this.reportNo);
            mProcessList = procesDatabase.getAllProcessList( reportNoString,
                    assignment.getDepartment(),
                    assignment.getAssignmentName());



            //put all process in array list
            List<String> list = new ArrayList<String>();
            for (Process process : mProcessList) {
                String rep = process.getProcess();

                list.add(rep);
            }

            // view all process with spinner
            Spinner spinner1 = (Spinner) findViewById(R.id.processSpinner);
            spinner1.setOnItemSelectedListener(this);

            String[] allProcessArr = new String[list.size()];
            allProcessArr = list.toArray(allProcessArr);

            SpinnerAdapter dataAdapter = new SpinnerAdapter(this, allProcessArr);
            spinner1.setAdapter(dataAdapter);

            //reset list view
            SeparatedListAdapter adapter = new SeparatedListAdapter(this);
            ListView listView = (ListView) findViewById(R.id.list_process_data);
            listView.setAdapter( adapter );
            ///reset listview height
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = 0;
            listView.requestLayout();

        }
        else if(spinner.getId() == R.id.processSpinner)
        {
            if (this.mAssignmentList.size() == 0){
                return;
            }

            if (this.mProcessList.size() == 0){
                return;
            }

            // test for pos > that current mAssignmentList, it should not happen but we are double checking
            if (pos >= this.mProcessList.size()){
                //invalid
                return;
            }

            //get process data from assignment
            Spinner assigmentSpinner = (Spinner) findViewById(R.id.assignmentSpinner);
            int assigmentSpinnerPos = assigmentSpinner.getSelectedItemPosition();
            Assignment assignment = this.mAssignmentList.get(assigmentSpinnerPos);

            // calculate a height for the list view
            int entryCount = 0;

            String reportNoString = String.valueOf(this.reportNo);
            //get process data from assignment
            Process process = this.mProcessList.get(pos);

            // add al the five fragmnet data to the process that conect to him
            List<Map<String,?>> security = new LinkedList<Map<String,?>>();

            security.add(createItem("שם תהליך:", process.getProcess()));

            security.add(createItem("הערה לתהליך:", process.getProcessComment()));
            security.add(createItem("משך תהליך:", process.getProcessDuration()));
            security.add(createItem("שיטה:", process.getProcessMethod()));
            security.add(createItem("חשיפה עורית:", process.isProcessBodyExposure()?"כן": "לא"));
            security.add(createItem("סיכום/המלצה", process.getProcessRecommendation()));

            security.add(createItem("חם/קר:", process.isWarm() ? "כן" : "לא"));
            security.add(createItem("סגור/פתוח:", process.isClosed()  ? "כן" : "לא"));

            security.add(createItem("כמות:", process.getProcessAmount()));

            entryCount += security.size();

            ///// Type Chemical Exposure
            ChemicalExposureDatabase chemicalExposureDb = new ChemicalExposureDatabase();
            ArrayList<ChemicalExposure> typeChemicalExposureList = chemicalExposureDb.getAllChemicalExposure(process);

            List<Map<String,?>> chemicalExposureItems = new LinkedList<Map<String,?>>();
            for (ChemicalExposure chemicalExposure : typeChemicalExposureList) {
                chemicalExposureItems.add(createItem("חשיפה לא כימית:", chemicalExposure.getChemicalExposureName()));
                chemicalExposureItems.add(createItem("תיאור חשיפה לא נשמתית:", chemicalExposure.getDescInhalantExposure()));
            }

            entryCount += (chemicalExposureItems.size() * 2);

            ///// Personal Exposure
            PersonalExposureDatabase personalExposureDb = new PersonalExposureDatabase();
            ArrayList<PersonalExposure> personalExposures = personalExposureDb.getAllPersonalExposure(process);

            List<Map<String,?>> personalExposureItems = new LinkedList<Map<String,?>>();
            for (PersonalExposure personalExposure : personalExposures) {
                personalExposureItems.add(createItem("סוג מיוד מגן אישי:", personalExposure.getPerProEquipType()));
                personalExposureItems.add(createItem("תיאור בקרה אישית:", personalExposure.getPerControlDesc()));
          
            }

            entryCount += (personalExposureItems.size() * 2);

            ///// Type Process Control
            TypeProcessControlDatabase typeProcessControlDb = new TypeProcessControlDatabase();
            ArrayList<TypeProcessControl> typeProcessControlList = typeProcessControlDb.getAllTypeProcess(process);

            List<Map<String,?>> typeProcessItems = new LinkedList<Map<String,?>>();//LinkedList<Map<String,?>>();

            for (TypeProcessControl typeProcess : typeProcessControlList) {
                typeProcessItems.add(createItem("סוג בקרה על תהליך:", typeProcess.getTypeOfProcessControl()));
                typeProcessItems.add(createItem("תיאור בקרת תהליך:", typeProcess.getDescOfProcessControl()));
         
            }

            entryCount += (typeProcessItems.size() * 2);

            ///// Material Associated Task
            MaterialAssociatedTaskDatabase materialAssociatedTaskDb = new MaterialAssociatedTaskDatabase();


            ArrayList<MaterialAssociatedTask> materialAssociatedTaskList = materialAssociatedTaskDb.getAllMaterialTask(reportNoString,
                    assignment.getDepartment(),
                    assignment.getAssignmentName(),
                    process.getProcess());

            List<Map<String,?>> materialAssociatedTaskItems = new LinkedList<Map<String,?>>();//LinkedList<Map<String,?>>();

            for (MaterialAssociatedTask material : materialAssociatedTaskList) {
                materialAssociatedTaskItems.add(createItem("חומר/שם מסחרי:", material.getMaterialTradeName()));
                materialAssociatedTaskItems.add(createItem("תדירות שימוש בחומר", material.getUsingMaterial()));
              
                materialAssociatedTaskItems.add(createItem("מקושר:", material.ismLinked()? "כן" : "לא"));
                materialAssociatedTaskItems.add(createItem("כמות זעירה", material.ismTinyAmount()? "כן" : "לא"));
                materialAssociatedTaskItems.add(createItem("msds", material.ismMSDS()? "כן" : "לא"));
            }

            entryCount += (materialAssociatedTaskItems.size() * 5);

            ///// Facto Monitoring Task
            FactorMonitoringComponentDatabase factorsMonitoringDb = new FactorMonitoringComponentDatabase();


            ArrayList<FactorsMonitoringComponent> factoriMonitoringList = factorsMonitoringDb.getAllFactorMonitoringComponent(reportNoString,
                    assignment.getDepartment(),
                    assignment.getAssignmentName(),
                    process.getProcess());

            List<Map<String,?>> factorMonitorItems = new LinkedList<Map<String,?>>();

            for (FactorsMonitoringComponent factorMonitor : factoriMonitoringList) {
                factorMonitorItems.add(createItem("בחירה מהירה:", factorMonitor.getmQuickChoice()));
                factorMonitorItems.add(createItem("גורם:", factorMonitor.getFactor()));
                factorMonitorItems.add(createItem("רמה מותרת:", factorMonitor.getRegularityType()));
                factorMonitorItems.add(createItem("סוג תקן:", factorMonitor.getPolicyLevel()));
                factorMonitorItems.add(createItem("יחידה:", factorMonitor.getUnit()));

                factorMonitorItems.add(createItem("מספר דגימות:", factorMonitor.getNoOfSample()));
                factorMonitorItems.add(createItem("מרכיב:", factorMonitor.getIngredients()));
                factorMonitorItems.add(createItem("חיפוש/הוספה:", factorMonitor.getSearchAdd()));
                factorMonitorItems.add(createItem("אחוז מרכיב בחומר", factorMonitor.getMaterialComponentPresent()));
            }


            entryCount += (factorMonitorItems.size() * 9);



            // create  list and custom adapter
            SeparatedListAdapter adapter = new SeparatedListAdapter(this);

            adapter.addSection("הצגת נתוני תהליך:", new SimpleAdapter(this, security, R.layout.list_complex,
                    new String[] { ITEM_TITLE, ITEM_CAPTION }, new int[] { R.id.list_complex_title, R.id.list_complex_caption }));

            adapter.addSection("הצגת נתוני חשיפה לא כימית:", new SimpleAdapter(this, chemicalExposureItems, R.layout.list_complex,
                    new String[] { ITEM_TITLE, ITEM_CAPTION }, new int[] { R.id.list_complex_title, R.id.list_complex_caption }));

            adapter.addSection("הצגת נתוני בקרה אישית:", new SimpleAdapter(this, personalExposureItems, R.layout.list_complex,
                    new String[] { ITEM_TITLE, ITEM_CAPTION }, new int[] { R.id.list_complex_title, R.id.list_complex_caption }));

            adapter.addSection("הצגת נתוני בקרת תהליך:", new SimpleAdapter(this, typeProcessItems, R.layout.list_complex,
                    new String[] { ITEM_TITLE, ITEM_CAPTION }, new int[] { R.id.list_complex_title, R.id.list_complex_caption }));

            adapter.addSection("הצגת נתוני חומרים מקושרים למטלות:", new SimpleAdapter(this, materialAssociatedTaskItems, R.layout.list_complex,
                    new String[] { ITEM_TITLE, ITEM_CAPTION }, new int[] { R.id.list_complex_title, R.id.list_complex_caption }));

            adapter.addSection("הצגת נתוני מרכיבים וגורמים לניטור:", new SimpleAdapter(this, factorMonitorItems, R.layout.list_complex,
                    new String[] { ITEM_TITLE, ITEM_CAPTION }, new int[] { R.id.list_complex_title, R.id.list_complex_caption }));





            ListView listView = (ListView) findViewById(R.id.list_process_data);
            listView.setAdapter( adapter );

            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = entryCount * 40;
            listView.requestLayout();

            /// PDF create
            MultipleSurveyDatabase multipleSurveyDatabase= new MultipleSurveyDatabase();
            // cal the func getAllData that we create on DataBaseHelper class
            ArrayList<Visits> visits = multipleSurveyDatabase.getAllData();

            Visits aVisit = null;
            for (Visits visit : visits)
            {

                // check if the visit report number is equal process survey
                if(visit.getReportNumber() == assignment.getReportNo()){
                    aVisit = visit;
                    break;
                }
            }

            // put context from show survey PDF
            TextView sampleNameValueTextView = (TextView) findViewById(R.id.sampleNameValueTextView);
            TextView visitDateValueTextView = (TextView) findViewById(R.id.visitDateValueTextView);
            TextView reportNumberTextView = (TextView) findViewById(R.id.reportNumberValueTextView);
            TextView factoryNameValueTextView = (TextView) findViewById(R.id.factoryNameValueTextView);
            TextView numberOfEmployeeValueTextView = (TextView) findViewById(R.id.numberOfEmployeeValueTextView);
            TextView assignmentValueTextView = (TextView) findViewById(R.id.assignmentValueTextView);
            TextView departmentValueTextView = (TextView) findViewById(R.id.departmentValueTextView);

            PersonalExposure aPersonalExposure = null;
            for (PersonalExposure personalExposure : personalExposures) {


                aPersonalExposure = personalExposure;
            }

            TextView typeProcessValueTextView = (TextView) findViewById(R.id.typeProcessValueTextView);
            TextView personalProtectiveValueTextView = (TextView) findViewById(R.id.personalProtectiveValueTextView);
            TextView notChemicalExposure = (TextView) findViewById(R.id.notChemicalExposureValue);
            TextView amountValueTextView = (TextView) findViewById(R.id.amountValue);
            TextView openCloseValueTextView = (TextView) findViewById(R.id.open_close1Value);
            TextView coldWarnValueTextView = (TextView) findViewById(R.id.cold_warnValue);
            TextView exposureValueTextView = (TextView) findViewById(R.id.exposureValue);
            TextView methodValueTextView = (TextView) findViewById(R.id.methodValue);
            TextView durationOfProcessValueTextView = (TextView) findViewById(R.id.durationOfProcessValue);
            TextView commentProcessValueTextView = (TextView) findViewById(R.id.commentProcessValue);
            TextView processValueTextView = (TextView) findViewById(R.id.processValue);
            TextView newValueTextView = (TextView) findViewById(R.id.new1Part1FalseTextView);
            TextView descProcessControlValueTextView = (TextView) findViewById(R.id.descriptionOfProcessControl1ValueTextView);
            TextView new2ValueTextView = (TextView) findViewById(R.id.new1Part2aFalseTextView);

            TextView descriptionOfPersonalControlValueTextView = (TextView) findViewById(R.id.descriptionOfPersonalControlValueTextView);
            TextView descriptionOfInhalantValueTextView = (TextView) findViewById(R.id.descriptionOfInhalantValueTextView);
            TextView descriptionOfRecommendationValueTextView = (TextView) findViewById(R.id.descriptionOfRecommendationValueTextView);

            ///MultipleSurveyDatabase get the visit data to show on PDF
            sampleNameValueTextView.setText( aVisit.getSample() );
            visitDateValueTextView.setText( aVisit.getVisitDate() );
            reportNumberTextView.setText( String.valueOf( assignment.getReportNo() ) );
            factoryNameValueTextView.setText( factoryName );
            numberOfEmployeeValueTextView.setText( assignment.getEmployeeNo() );
            assignmentValueTextView.setText( assignment.getAssignmentName() );
            departmentValueTextView.setText( assignment.getDepartment() );

//            TypeProcessControl aTypeProcess = null;
            String typeProcessValue = "";
            for (TypeProcessControl typeProcess : typeProcessControlList) {

                typeProcessValue = typeProcess.getProcess();
                break;
            }
            typeProcessValueTextView.setText( typeProcessValue );

            String personaProtective = "";
            String isNewPersonalProtective = "";
            for (PersonalExposure personalExposure : personalExposures) {

                personaProtective += personalExposure.getPerProEquipType() + " " + personalExposure.getPerControlDesc();
                isNewPersonalProtective = personalExposure.isNew() ? "כן" : "לא";
                break;
            }
            if (aPersonalExposure != null) {
                personalProtectiveValueTextView.setText(aPersonalExposure.getPerProEquipType());
            }

            String chemicalExposureDesc = "";
            String inhalantChemical = "";
            for (ChemicalExposure chemicalExposure : typeChemicalExposureList) {

                chemicalExposureDesc = chemicalExposure.getChemicalExposureName();
                inhalantChemical = chemicalExposure.getDescInhalantExposure();

                break;
            }

            // set the value on PDF view
            notChemicalExposure.setText( chemicalExposureDesc );
            amountValueTextView.setText( process.getProcessAmount() );
            openCloseValueTextView.setText( process.isClosed() ? "סגור" : "פתוח" );
            coldWarnValueTextView.setText( process.isWarm() ? "כן" : "לא" );
            exposureValueTextView.setText( process.isProcessBodyExposure() ? "כן" : "לא" );

            methodValueTextView.setText( process.getProcessMethod() );
            durationOfProcessValueTextView.setText( process.getProcessDuration() );
            commentProcessValueTextView.setText( process.getProcessComment() );
            processValueTextView.setText( process.getProcess() );

            String isNewTypeProcess = "";
            String descProcessControl = "";
            for (TypeProcessControl typeProcess : typeProcessControlList) {
                isNewTypeProcess = typeProcess.isNew() ? "כן" : "לא";
                descProcessControl = typeProcess.getDescOfProcessControl();
                break;
            }
            newValueTextView.setText( isNewTypeProcess );
            descProcessControlValueTextView.setText( descProcessControl );

            new2ValueTextView.setText( isNewPersonalProtective );

            if (aPersonalExposure != null) {
                descriptionOfPersonalControlValueTextView.setText(aPersonalExposure.getPerControlDesc());
            }

            descriptionOfInhalantValueTextView.setText( inhalantChemical );
            descriptionOfRecommendationValueTextView.setText( process.getProcessRecommendation() );



            /// save a list of ids to fill

            TableLayout ll = (TableLayout) findViewById(R.id.factory_components_summary);
            String factorsMonitoringComponentResume = "";
            for(int n=0; n < factoriMonitoringList.size(); ++n){

                FactorsMonitoringComponent factorMonitor = factoriMonitoringList.get(n);

                TableRow row = new TableRow(this);
                row.setBackgroundColor(Color.BLACK);

                TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
                row.setLayoutParams(lp);


                TextView factorsMonitoringComponentsValueTextView = (TextView)getLayoutInflater().inflate(R.layout.textview_show_process_factor_mon, null);

                /// linked
                TextView linkedValueTextView = (TextView)getLayoutInflater().inflate(R.layout.textview_show_process_factor_mon, null);

                /// msds
                TextView msdsValueTextView = (TextView)getLayoutInflater().inflate(R.layout.textview_show_process_factor_mon, null);

                /// tinyAmount
                TextView tinyAmountValueTextView = (TextView)getLayoutInflater().inflate(R.layout.textview_show_process_factor_mon, null);

                /// usingMaterial
                TextView usingMaterialValueTextView = (TextView)getLayoutInflater().inflate(R.layout.textview_show_process_factor_mon, null);

                /// materialTrade
                TextView materialTradeValueTextView = (TextView)getLayoutInflater().inflate(R.layout.textview_show_process_factor_mon, null);

                ////
                ArrayList<String> list = new ArrayList<String>();

                if (TextUtils.isEmpty(factorMonitor.getFactor()) == false) {
                    list.add(factorMonitor.getFactor());
                }
                if (TextUtils.isEmpty(factorMonitor.getmQuickChoice()) == false) {
                    list.add(factorMonitor.getmQuickChoice());
                }
                if (TextUtils.isEmpty(factorMonitor.getRegularityType()) == false) {
                    list.add(factorMonitor.getRegularityType());
                }
                if (TextUtils.isEmpty(factorMonitor.getPolicyLevel()) == false) {
                    list.add(factorMonitor.getPolicyLevel());
                }
                if (TextUtils.isEmpty(factorMonitor.getUnit()) == false) {
                    list.add(factorMonitor.getUnit());
                }
                if (TextUtils.isEmpty(factorMonitor.getNoOfSample()) == false) {
                    list.add(factorMonitor.getNoOfSample());
                }
                if (TextUtils.isEmpty(factorMonitor.getIngredients()) == false) {
                    list.add(factorMonitor.getIngredients());
                }

                if (TextUtils.isEmpty(factorMonitor.getSearchAdd()) == false) {
                    list.add(factorMonitor.getSearchAdd());
                }

                if (TextUtils.isEmpty(factorMonitor.getMaterialComponentPresent()) == false) {
                    list.add(factorMonitor.getMaterialComponentPresent());
                }

                String result = TextUtils.join(", ", list);

                factorsMonitoringComponentResume = result;

                if (n >= materialAssociatedTaskList.size() ){
                    continue;
                }

                MaterialAssociatedTask material = materialAssociatedTaskList.get(n);

                factorsMonitoringComponentsValueTextView.setText( factorsMonitoringComponentResume );
                linkedValueTextView.setText( material.ismLinked()? "כן" : "לא"  );
                msdsValueTextView.setText( material.ismMSDS()? "כן" : "לא"  );
                tinyAmountValueTextView.setText( material.ismTinyAmount()? "כן" : "לא" );
                usingMaterialValueTextView.setText( material.getUsingMaterial() );
                materialTradeValueTextView.setText( material.getMaterialTradeName() );

                row.addView(factorsMonitoringComponentsValueTextView);
                row.addView(linkedValueTextView);
                row.addView(msdsValueTextView);
                row.addView(tinyAmountValueTextView);

                row.addView(usingMaterialValueTextView);
                row.addView(materialTradeValueTextView);
                ll.addView(row,n+1);

                //set dimensions & margins
                int margin = dpsToPixels(1); // margins around the tectview to see the line around the textview
                ViewGroup.LayoutParams factorsMonParams = factorsMonitoringComponentsValueTextView.getLayoutParams();
                factorsMonParams.width = dpsToPixels(620);
                factorsMonParams.height = dpsToPixels(60);

                ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) factorsMonitoringComponentsValueTextView
                        .getLayoutParams();
                mlp.setMargins(margin, margin, margin, margin);
                factorsMonitoringComponentsValueTextView.setLayoutParams(mlp);

                /// linked
                ViewGroup.LayoutParams linkedValueParams = linkedValueTextView.getLayoutParams();
                linkedValueParams.width = dpsToPixels(80);
                linkedValueParams.height = dpsToPixels(60);
                linkedValueTextView.setLayoutParams(linkedValueParams);

                mlp = (ViewGroup.MarginLayoutParams) linkedValueTextView
                        .getLayoutParams();
                mlp.setMargins(margin, margin, margin, margin);
                linkedValueTextView.setLayoutParams(mlp);

                /// msds
                ViewGroup.LayoutParams msdsValueParams = msdsValueTextView.getLayoutParams();
                msdsValueParams.width = dpsToPixels(100);
                msdsValueParams.height = dpsToPixels(60);
                msdsValueTextView.setLayoutParams(msdsValueParams);

                mlp = (ViewGroup.MarginLayoutParams) msdsValueTextView
                        .getLayoutParams();
                mlp.setMargins(margin, margin, margin, margin);
                msdsValueTextView.setLayoutParams(mlp);

                /// tinyAmount
                ViewGroup.LayoutParams tinyAmountValueParams = tinyAmountValueTextView.getLayoutParams();
                tinyAmountValueParams.width = dpsToPixels(160);
                tinyAmountValueParams.height = dpsToPixels(60);
                tinyAmountValueTextView.setLayoutParams(tinyAmountValueParams);

                mlp = (ViewGroup.MarginLayoutParams) tinyAmountValueTextView
                        .getLayoutParams();
                mlp.setMargins(margin, margin, margin, margin);
                tinyAmountValueTextView.setLayoutParams(mlp);

                /// usingMaterial
                ViewGroup.LayoutParams usingMaterialValueParams = usingMaterialValueTextView.getLayoutParams();
                usingMaterialValueParams.width = dpsToPixels(320);
                usingMaterialValueParams.height = dpsToPixels(60);
                usingMaterialValueTextView.setLayoutParams(usingMaterialValueParams);

                mlp = (ViewGroup.MarginLayoutParams) usingMaterialValueTextView
                        .getLayoutParams();
                mlp.setMargins(margin, margin, margin, margin);
                usingMaterialValueTextView.setLayoutParams(mlp);

                /// materialTrade
                ViewGroup.LayoutParams materialTradeValueParams = materialTradeValueTextView.getLayoutParams();
                materialTradeValueParams.width = dpsToPixels(200);
                materialTradeValueParams.height = dpsToPixels(60);
                materialTradeValueTextView.setLayoutParams(materialTradeValueParams);

                mlp = (ViewGroup.MarginLayoutParams) materialTradeValueTextView
                        .getLayoutParams();
                mlp.setMargins(margin, margin, margin, margin);
                materialTradeValueTextView.setLayoutParams(mlp);
            }

        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }


    ///PDF
    public void btnConvertPDF(View view)
    {
        String description = "קובץ PDF נוצר";

        File pdfFile = createPDF();
        if (pdfFile == null){
            ///  the pdf file cannot be created do not open the email app, show a warning
            description = "Cannot create the pdf";
            Toast.makeText(this, description, Toast.LENGTH_LONG)
                    .show();
        }

        Toast.makeText(this, description, Toast.LENGTH_LONG)
                .show();
    }

    private int dpsToPixels(float dps)
    {
        final float scale = this.getResources().getDisplayMetrics().density;
        int pixels = (int) (dps * scale + 0.5f);

        return pixels;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private File createPDF()
    {
        File pdfFile = null;

        // Create a new empty PDF document in memory
        //  to optionally be printable,  add PrintAttributes
        //   PrintedPdfDocument. Simpler: new PdfDocument().
        PrintAttributes printAttrs = new PrintAttributes.Builder().
                setColorMode(PrintAttributes.COLOR_MODE_COLOR).
                setMediaSize(PrintAttributes.MediaSize.NA_LETTER).
                setResolution(new PrintAttributes.Resolution("zooey", PRINT_SERVICE, 100, 100)).
                setMinMargins(PrintAttributes.Margins.NO_MARGINS).
                build();
        PdfDocument document = new PrintedPdfDocument(this, printAttrs);

        // get the reference to the container RelativeLayout
        View content = getContentView();

        /* for the pdf Page to display all contents of the view  be the same as the
         view contentSize,  use content.getMeasuredWidth(), content.getMeasuredHeight() for this,
         it has all the size needed for the view and remove the button on bottom
         rest their height
          */
        Button createPDFButton = (Button) findViewById(R.id.sendToEmail);

        int neededPageSizeWidth = content.getMeasuredWidth();
        int neededPageSizeHeight = content.getMeasuredHeight();

        neededPageSizeHeight -= createPDFButton.getHeight();

        // create a page description, with size
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder( neededPageSizeWidth, neededPageSizeHeight, 1).create();
        // create a new page from the PageInfo
        PdfDocument.Page page = document.startPage(pageInfo);

        ///use canvas of page object and draw in it
        Canvas canvas = page.getCanvas();
        content.draw(canvas);

        // do final processing of the page
        document.finishPage(page);

        // could add more pages in a longer doc app,

        try {

            /*  creating the file  at externalStorage/Documents/EcoCheck path
            folders that don't exist in between will be created,
            with filename factoryInfo<name><date>.pdf format
             */

            File pdfFolder = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOCUMENTS), "EcoCheck");
            if (!pdfFolder.exists()) {
                boolean res = pdfFolder.mkdirs();
                Log.i("LOG_TAG", "Pdf Directory created");
            }

            //Create time stamp
            Date date = new Date();
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(date);

            // create the file in path pdfFolder with name factoryInfo<name><date>.pdf format
            pdfFile = new File(pdfFolder, timeStamp + "_survey" + ".pdf");

            OutputStream output = new FileOutputStream(pdfFile);

            ///close gracefully
            document.writeTo(output);
            document.close();
            output.close();

        } catch (IOException e) {
            throw new RuntimeException("Error generating file", e);
        }

        return pdfFile;
    }


    private View getContentView() {
        return findViewById(R.id.completeView);
    }

    public void btnSendToEmail(View view) {

        /// try to create the pdf file if it fails
        File pdfFile = createPDF();
        if (pdfFile == null) {
            ///  the pdf file cannot be created  not open the email app, show a warning
            Toast.makeText(this, "Cannot create the pdf", Toast.LENGTH_LONG)
                    .show();

            return;
        }

        /*
         call the intent send email, add attachment, and set permission to read it,
         open chooser for email, and send
          */

        Uri path = Uri.fromFile(pdfFile);
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
// set the type to 'email'
        emailIntent.setType("vnd.android.cursor.dir/email");
//        String to[] = {"asd@gmail.com"};
//        emailIntent .putExtra(Intent.EXTRA_EMAIL, to);

        emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        emailIntent.setType("application/pdf");
        emailIntent.putExtra(Intent.EXTRA_STREAM, path);

// the mail subject
//        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }


    ///Adapter spinner
    public class SpinnerAdapter extends BaseAdapter
    {
        LayoutInflater inflator;
        String[] mCounting;

        public SpinnerAdapter( Context context ,String[] counting)
        {
            inflator = LayoutInflater.from(context);
            mCounting=counting;
        }

        @Override
        public int getCount()
        {
            return mCounting.length;
        }

        @Override
        public Object getItem(int position)
        {
            return mCounting[position];
        }

        @Override
        public long getItemId(int position)
        {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            convertView = inflator.inflate(R.layout.spinner_item_right, null);
            TextView tv = (TextView)convertView;

            tv.setText(mCounting[position]);
            return convertView;
        }
    }


}

