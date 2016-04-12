package org.ecocheck.ecocheck;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.ecocheck.ecocheck.database.VisitDataBase;
import org.ecocheck.ecocheck.dto.Visits;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;

public class VisitFactory extends AppCompatActivity
{

    VisitDataBase visitDataBase;
    EditText editVisitDate,editReportNumber,editSample, editLaboratory, editComments,editFactoryCoice;
    Button btnAddVisit,btnUpdateData;
    CheckBox checkConfirm;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_factory);
        visitDataBase= new VisitDataBase();

        editVisitDate=(EditText)findViewById(R.id.editText_visitDate);
        editReportNumber=(EditText)findViewById(R.id.editText_reportNumber);
        editFactoryCoice=(EditText)findViewById(R.id.editText_factoyChoice);
        editSample=(EditText)findViewById(R.id.editText_sampels);
        editLaboratory=(EditText)findViewById(R.id.editText_laboratory);
        editComments=(EditText)findViewById(R.id.editText_comments);
        checkConfirm=(CheckBox)findViewById(R.id.checkbox_confirmed);

        btnAddVisit=(Button)findViewById(R.id.Button_addVisit);
        btnUpdateData=(Button)findViewById(R.id.Button_updateVisit);

        AddData();
    //    UpdateData();

    }

    //method to add data to the sqlLite DataBase on click
    public void AddData()
    {
        btnAddVisit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)// on click call to insertFactoryData
            // method from VisitDatabase class
            {
                Visits visits= new Visits();

                //throw error if in the report number is empty
                if(isEmptyFieldNumber(editReportNumber))
                {
                    editReportNumber.setError("שדה זה הינו חובה");
                }

                //throe error if laboratory field is empty
                if(isEmptyFieldNumber(editLaboratory))
                {
                    editLaboratory.setError("שדה זה הינו חובה");
                }

                //check valid date format
                String date=editVisitDate.getText().toString();
                if(!isValidDate(date))
                {
                    editVisitDate.setError("הזן תאריך לפי פורמט dd/mm/yyyy");
                }

                if(!isEmptyFieldNumber(editReportNumber)&& !isEmptyFieldNumber(editLaboratory) && isValidDate(date))
                {
                    visits.setReportNumber(Integer.parseInt(editReportNumber.getText().toString()));
                    visits.setFactory(editFactoryCoice.getText().toString());
                    visits.setVisitDate(editVisitDate.getText().toString());
                    visits.setSample(editSample.getText().toString());
                    visits.setLaboratory(Integer.parseInt(editLaboratory.getText().toString()));
                    visits.setComments(editComments.getText().toString());
                    //check if the user click confirm in the checkBox
                    boolean isConfirmed;
                    if (checkConfirm.isChecked())
                    {
                        isConfirmed = true;
                    } else
                    {
                        isConfirmed = false;
                    }
                    visits.setConfirmed(isConfirmed);

                    //     visitDataBase.insertVisitData(visits);

                    boolean isInserted = visitDataBase.insertVisitData(visits);
                    if (isInserted == true)
                        Toast.makeText(VisitFactory.this, "Data Inserted",
                                Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(VisitFactory.this, "Data not Inserted",
                                Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    public void UpdateData()

    {
        btnUpdateData.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            Visits visits= new Visits();
                            @Override
                            public void onClick(View v)
                            {
                                if(isEmptyFieldNumber(editReportNumber))
                                {
                                    editReportNumber.setError("שדה זה הינו חובה");
                                }

                                if(!isEmptyFieldNumber(editReportNumber))
                                {
                                    visits.setReportNumber(Integer.parseInt(editReportNumber.getText().toString()));
                                    visits.setFactory(editFactoryCoice.getText().toString());
                                    visits.setVisitDate(editVisitDate.getText().toString());
                                    visits.setSample(editSample.getText().toString());
                                    visits.setLaboratory(Integer.parseInt(editLaboratory.getText().toString()));
                                    visits.setComments(editComments.getText().toString());
                                    //check if the user click confirm in the checkBox
                                    boolean isConfirmed;
                                    if (checkConfirm.isChecked())
                                    {
                                        isConfirmed = true;
                                    } else
                                    {
                                        isConfirmed = false;
                                    }
                                    visits.setConfirmed(isConfirmed);

                                    //     visitDataBase.insertVisitData(visits);

                                    boolean isInserted = visitDataBase.updateData(visits);
                                    if (isInserted == true)
                                        Toast.makeText(VisitFactory.this, "עודכן",
                                                Toast.LENGTH_LONG).show();
                                    else
                                        Toast.makeText(VisitFactory.this, "לא עודכן",
                                                Toast.LENGTH_LONG).show();

                                }
                            }
                        }
                );
    }


    //TODO maby need to add the option menu


    // method to check if report number or laboratory is empty
    private boolean isEmptyFieldNumber(EditText repoNumber)
    {
        // return true if report number is empty
        return repoNumber.getText().toString().trim().length() == 0;
    }

   //check the format date user input dd/mm/yyyy
    public static boolean isValidDate(String inDate)
    {

        if (inDate.matches("\\d{2}/\\d{2}/\\d{4}"))
            return true;
        return false;

    }


}
