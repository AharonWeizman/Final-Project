package org.ecocheck.ecocheck;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.ecocheck.ecocheck.adapter.FactoryArrayAdapter;
import org.ecocheck.ecocheck.adapter.VisitArrayAdapter;
import org.ecocheck.ecocheck.database.MultipleSurveyDatabase;
import org.ecocheck.ecocheck.database.VisitDataBase;
import org.ecocheck.ecocheck.dto.Factory;
import org.ecocheck.ecocheck.dto.Visits;

import java.util.ArrayList;

public class ViewAllSurvey extends AppCompatActivity {
   // VisitDataBase visitDataBase;
    MultipleSurveyDatabase multipleSurveyDatabase;
    TextView EmptySurvey;
    ListView listViewSurvey;
    ArrayList<Visits> visitses;
    VisitArrayAdapter visitArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_survey);

     //   visitDataBase = new VisitDataBase();
        multipleSurveyDatabase= new MultipleSurveyDatabase();

        EmptySurvey = (TextView) findViewById(R.id.emptySurvey);

        listViewSurvey = (ListView) findViewById(R.id.SurveylistView);

        listViewSurvey.setEmptyView(EmptySurvey);

        try {
            Intent intent = getIntent();
            visitses = (ArrayList<Visits>) intent.getSerializableExtra("Survey_list");//
            if (visitses == null) {
                visitses = multipleSurveyDatabase.getAllData();
            }
        } catch (Exception ex) {
            // cal the func getAllData that we create on DataBaseHelper class
            visitses = multipleSurveyDatabase.getAllData();
        }
        // Setup cursor adapter using cursor from last step
        visitArrayAdapter = new VisitArrayAdapter(this,
                R.layout.activity_view_all_survey, visitses);
        // Attach cursor adapter to the ListView
        listViewSurvey.setAdapter(visitArrayAdapter);

        listViewSurvey.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override

            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id)
            {
                Visits visits= visitses.get(position);
                String message = "report number" + visits.getReportNumber() + "\n";
                message += "factory name:" + visits.getFactory() + "\n";
                message += "visit date" + visits.getVisitDate() + "\n";
                message += "laboratory:" + visits.getLaboratory() + "\n";
                message += "sample:" + visits.getSample() + "\n";
                message += "coments: " + visits.getComments() + "\n";
                message += "confirm:" + visits.isConfirmed();

                showMassage("מידע על הביקור", message);
            }
            

        });
    }

    public void showMassage(String title, String message) {
        // instance of alertDialog window
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);// cancel it after is use
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();// show our dialog
    }
}
