package org.ecocheck.ecocheck;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import org.ecocheck.ecocheck.database.MultipleSurveyDatabase;
import org.ecocheck.ecocheck.database.VisitDataBase;
import org.ecocheck.ecocheck.dto.Visits;

import java.util.ArrayList;

public class FindSurvey extends AppCompatActivity
{
    MultipleSurveyDatabase multipleSurveyDatabase;
    EditText editVisitDate;
    EditText editReportNumber;
    EditText editSample;
    EditText editLaboratory;
    EditText editComments;
    EditText editFactoryCoice;
    Button btnFindSurvey;
    CheckBox checkConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_survey);

        multipleSurveyDatabase= new MultipleSurveyDatabase();

        editVisitDate=(EditText)findViewById(R.id.editText_visitDate);
        editReportNumber=(EditText)findViewById(R.id.editText_reportNumber);
        editFactoryCoice=(EditText)findViewById(R.id.editText_factoyChoice);
        editSample=(EditText)findViewById(R.id.editText_sampels);
        editLaboratory=(EditText)findViewById(R.id.editText_laboratory);
        editComments=(EditText)findViewById(R.id.editText_comments);
        checkConfirm=(CheckBox)findViewById(R.id.checkbox_confirmed);

        btnFindSurvey=(Button)findViewById(R.id.Button_FindVisit);
        SearchData();
    }

    public void SearchData()
    {
        btnFindSurvey.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                //call the search method
                ArrayList<Visits> visitses= multipleSurveyDatabase.getData(editReportNumber.getText().toString(),
                        editFactoryCoice.getText().toString(), editVisitDate.getText().toString(), editLaboratory.getText().toString(),
                        editSample.getText().toString(), editComments.getText().toString(), checkConfirm);

                //call the view factory activity
                Intent intent = new Intent(getApplicationContext(), ViewAllSurvey.class);
                //intent.putExtra("Report_list", visitses);
                intent.putExtra("Survey_list", visitses);// changess
                startActivity(intent);
            }
        });
    }
}
