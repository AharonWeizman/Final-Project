package org.ecocheck.ecocheck.showSurvey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.ecocheck.ecocheck.R;
import org.ecocheck.ecocheck.database.MultipleSurveyDatabase;
import org.ecocheck.ecocheck.dto.Department;
import org.ecocheck.ecocheck.dto.Recommendation;

import java.util.ArrayList;

public class showVisitSurvey extends AppCompatActivity
{


    MultipleSurveyDatabase multipleSurveyDatabase;

    //array list that will hold all the report number from recommendation db
    ArrayList<String>recommendationsReportNumber;
    //Array list that will hold all the object db of recommendation
    ArrayList<Recommendation>allRecommendation;

    TextView editVisitDate,editReportNumber,editSample, editLaboratory, editComments,editFactoryCoice;
    TextView txtConfirm;
    TextView visitText;
    ///////////////////////////////////////////////////////////////////////////////////////
    // to pass the data to new activity recommendation
    Bundle bundle;

    String repoNumber;
    //the recommendation the user insert
    String recommendationValue;
    Recommendation recommendation;
    // the position of the column of each recommendation db
    int position;
    // boolean var that will check if their is the same report number in recommendation.db like chosen visit.db
    boolean sameReportNumber;
    //////////////////////////////////////////////////////////////////////////

    //hold all the report number of process survey
    ArrayList<String> processReportNumber;
    // to pass the data to new activity process
    Bundle bundleProcess;
    String reportNumberProcess;
    Department department;
    int processPosition;
    // boolean var that will check if their is the same report number in process.db like chosen visit.db
    boolean sameReportNumberProcess;
    String factoryName;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_visit_survey);

     /*
       * all the code that thke the data from the view all survey data and pass to here
      *///////////////////////////////////////////////////////////////////////////////////////////////////////
        multipleSurveyDatabase= new MultipleSurveyDatabase();
        visitText=(TextView)findViewById(R.id.VisitTextView);
        editVisitDate = (TextView) findViewById(R.id.Text_visitDate_show);
        editReportNumber = (TextView) findViewById(R.id.Text_reportNumber_show);
        editFactoryCoice = (TextView) findViewById(R.id.Text_factoyChoice_show);
        editSample = (TextView) findViewById(R.id.Text_sampels_show);
        editLaboratory = (TextView) findViewById(R.id.Text_laboratory_show);
        editComments = (TextView) findViewById(R.id.Text_comments_show);
        txtConfirm = (TextView) findViewById(R.id.Text_confirmed_show);


        // create intent that will pass the data from this class to showRecommendationSurvey class

        Intent intentExtras=getIntent();

        // get the factory name according the "factory" key that pass from viewAllSurvey class
         factoryName=intentExtras.getStringExtra("factroy");
        // get the visit date according the "visit" key that pass from viewAllSurvey class
        String visitDate= intentExtras.getStringExtra("visitDate");
        // get the report number according the "report number" key that pass from viewAllSurvey class
        String reportNumber=intentExtras.getStringExtra("reportNumber");
        // get the sample name according the "sample" key that pass from viewAllSurvey class
        String sampleName= intentExtras.getStringExtra("Sample");
        // get the laboratory number  according the "laboratoryNumber " key that pass from viewAllSurvey class
        String laboratory= intentExtras.getStringExtra("laboratoryNumber");
        // get the confirm boolean  according the "confirm " key that pass from viewAllSurvey class
        String confirm= intentExtras.getStringExtra("confirm");
        // get the comments data  according the "comment " key that pass from viewAllSurvey class
        String comments= intentExtras.getStringExtra("comment");

        //set the all the visit edit value text and show the show them according the visit database
        editFactoryCoice.setText(factoryName);
        editReportNumber.setText(reportNumber);
        editVisitDate.setText(visitDate);
        editSample.setText(sampleName);
        editLaboratory.setText(laboratory);
        txtConfirm.setText(confirm);
        editComments.setText(comments);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /*
         help to conect recommendation to visit according report number
         */
        // for the new intent that will start showRecommendationSurvey
        bundle= new Bundle();
        recommendation= new Recommendation();

        //method from MultipleSurveyDatabase that will hold all the report number from recommendation db
        recommendationsReportNumber=multipleSurveyDatabase.getAllReportNumberRecommendation();
        //method from MultipleSurveyDatabase that will hold all recommendation data of each column
        allRecommendation=multipleSurveyDatabase.getAllRecommendation();

        //  string  chosen report number from visit.db
        repoNumber= reportNumber;

        /*
         *   if their is reportNumber in recommendation.db like chosen report visit.db
         *   set sameReportNumber to true
          */
        if(recommendationsReportNumber.contains(repoNumber))
        {
            // if true that mean that visit db and recommendation db hold the same report number
            sameReportNumber=true;
            // int position hold the index of report number and we use that to show the recommendation column
            position= recommendationsReportNumber.indexOf(repoNumber);

        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // help to conect process to visit according report number
        bundleProcess = new Bundle();
        department = new Department();
        //take all the report number in process survey
        processReportNumber=multipleSurveyDatabase.getAllReportNumberProcess();
        // reportNumberProcess equals to repport number from visit
        reportNumberProcess=reportNumber;

        /*
         *   if their is reportNumber in process.db like chosen report visit.db
         *   set sameReportNumber to true
          */

        if(processReportNumber.contains(reportNumberProcess))
        {
            sameReportNumberProcess= true;
            processPosition= processReportNumber.indexOf(reportNumberProcess);
        }
    }

    //method that when the user click the button check if sameReportNumber=true if yes will open showRecommendationSurvey
    public void btnShowRecommendation(View view)
    {
        //TODO need to check on clean db this is not trow out from the app
        if(recommendationsReportNumber!=null && sameReportNumber==true)
        {
            // start the activity will open showRecommendationSurvey.class
            final Intent intentBundle= new Intent(this,showRecommendationSurvey.class);

            //pass the repoNumber according the key "ReportNumber"
            bundle.putString("ReportNumber",repoNumber);

            //get the position of the reportNumber
            Recommendation recommendation=allRecommendation.get(position);
            // pass the recommendation value according to report number column key "recommendation"
            recommendationValue= recommendation.getRecommendation();
            bundle.putString("recommendation",recommendationValue);
            intentBundle.putExtras(bundle);
            //start TryToShow class activity
            startActivity(intentBundle);


        }
        else
        {
            Toast passW = Toast.makeText(showVisitSurvey.this, "their is not report number: "+ repoNumber +" in recommendation database"  , Toast.LENGTH_LONG);
            passW.show();
        }
    }



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     /*
        mthod that help to connect process task  to visit according report number
         */

    public void btnShowProcess(View view)
    {
        // if thier is the same report number in process survey db and visit db
        if(processReportNumber!=null && sameReportNumberProcess== true)
        {
            final Intent intentBundle= new Intent(this,showProcessSurvey.class);

            //move the value of the report number to showPorcessSurvey class with the key
            bundleProcess.putString("reportNumber", reportNumberProcess);
            bundleProcess.putString("factoryName", factoryName);

            intentBundle.putExtras(bundleProcess);
            startActivity(intentBundle);
        }

        else
        {
            Toast passW = Toast.makeText(showVisitSurvey.this, "their is not report number: "+ reportNumberProcess +" in recommendation database"  , Toast.LENGTH_LONG);
            passW.show();
        }
    }

}
