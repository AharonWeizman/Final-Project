package org.ecocheck.ecocheck;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.ecocheck.ecocheck.database.RecommendationDatabase;
import org.ecocheck.ecocheck.dto.Recommendation;

public class FactoryRecommendation extends AppCompatActivity
{
    RecommendationDatabase recommendationDatabase;
    EditText editRecommendation,editReportNumber;
    Button btnAddRecommendation;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factory_recommendation);

        recommendationDatabase= new RecommendationDatabase();
        editReportNumber=(EditText)findViewById(R.id.editText_factoyReportNumber);
        editRecommendation=(EditText)findViewById(R.id.editText_factory_recommendation);

        btnAddRecommendation=(Button)findViewById(R.id.button_Recommendation);

        AddData();
    }

    //method to insert data
    public void AddData()
    {
        btnAddRecommendation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)// on click call to insertFactoryData
            // method from RecommendationDatabase class
            {
                Recommendation recommendation = new Recommendation();

                //throw error if in the report number is empty
                if (isEmptyFieldNumber(editReportNumber)) {
                    editReportNumber.setError("שדה זה הינו חובה");
                }

                if (!isEmptyFieldNumber(editReportNumber)) {
                    recommendation.setReportNumber(Integer.parseInt(editReportNumber.getText().toString()));
                    recommendation.setRecommendation(editRecommendation.getText().toString());


                    boolean isInserted = recommendationDatabase.insertRecommendationData(recommendation);
                    if (isInserted == true)
                        Toast.makeText(FactoryRecommendation.this, "המלצה הוזנה בהצלחה",
                                Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(FactoryRecommendation.this, "פרטי המלצה לא הוזנו, למספר דוח זה קיים המלצה במערכת",
                                Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //enable option menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_eco_check, menu);
        return true;
    }

    @Override
    //method to enable menu bar click user choose
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if(id== R.id.factory_process)
        {
            Intent intent = new Intent("org.ecocheck.ecocheck.FactoryProcess");//take the context from activity_factory_process.xml
            startActivity(intent);
        }

        // case the user click the visit factory in the option menu it will open activity_visit_factory.xml
        if(id== R.id.visit_factory)
        {
            Intent intent = new Intent("org.ecocheck.ecocheck.VisitFactory");//take the context from activity_Materials.xml
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    // method to check if report number or laboratory is empty
    private boolean isEmptyFieldNumber(EditText repoNumber)
    {
        // return true if report number is empty
        return repoNumber.getText().toString().trim().length() == 0;
    }
}
