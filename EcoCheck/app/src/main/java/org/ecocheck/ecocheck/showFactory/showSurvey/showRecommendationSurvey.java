package org.ecocheck.ecocheck.showSurvey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.ecocheck.ecocheck.R;

public class showRecommendationSurvey extends AppCompatActivity
{
    TextView reportNumberText;
    TextView recommendationText;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_recommendation_survey);

        // create the Text view from showRecommendationSurvey layout
        reportNumberText= (TextView) findViewById(R.id.Text_factoyReportNumber_show);
        recommendationText= (TextView) findViewById(R.id.Text_factory_recommendation_show);

        Intent intentExtras=getIntent();

        // get the value according the key from TryToShow
        String reportNumber=intentExtras.getStringExtra("ReportNumber");
        String recommend= intentExtras.getStringExtra("recommendation");


        //set the value on the layout
        reportNumberText.setText(reportNumber);
        recommendationText.setText(recommend);


    }
}
