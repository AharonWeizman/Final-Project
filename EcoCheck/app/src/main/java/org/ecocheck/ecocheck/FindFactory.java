package org.ecocheck.ecocheck;

import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;

import org.ecocheck.ecocheck.database.DataBaseFactory;
import org.ecocheck.ecocheck.dto.Factory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FindFactory extends AppCompatActivity {
    DataBaseFactory dataBaseFactory;
    EditText editFactory, editBranch, editCity, editAddress, editPhone, editFax, editContactPerson, editMailingAddress, editEmployeesNumber, editInspector;//Edit text field acording activity_add_new_factory
    Button btnSearchFactory;//field button that refer to activity_add_new_factory click on this button will add the factory details to mySql.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_factory);
        dataBaseFactory = new DataBaseFactory(this);//creating the data base according the DataBaseFactory class constructor

        editFactory = (EditText) findViewById(R.id.editText_Factory);//give the field the EditText from activity_add_new_factory
        editBranch = (EditText) findViewById(R.id.editText_Branch);//give the field the EditText from activity_add_new_factory
        editCity = (EditText) findViewById(R.id.editText_City);//give the field the EditText from activity_add_new_factory
        editAddress = (EditText) findViewById(R.id.editText_Adress);//give the field the EditText from activity_add_new_factory
        editPhone = (EditText) findViewById(R.id.editText_PhoneNumber);//give the field the EditText from activity_add_new_factory
        editFax = (EditText) findViewById(R.id.editText_FaxNumber);//give the field the EditText from activity_add_new_factory
        editContactPerson = (EditText) findViewById(R.id.editText_ContactPerson);//give the field the EditText from activity_add_new_factory
        editMailingAddress = (EditText) findViewById(R.id.editText_MailingAddress);//give the field the EditText from activity_add_new_factory
        editEmployeesNumber = (EditText) findViewById(R.id.editText_NumberOfEmployees);//give the field the EditText from activity_add_new_factory
        editInspector = (EditText) findViewById(R.id.editText_Inspector);//give the field  EditText parmeter from activity_add_new_factory

        btnSearchFactory = (Button) findViewById(R.id.SearchFactory);//give the field parmeter from activity_add_new_factory click

        SearchData();
    }

    // method to add data to the sqlLite DataBase on click
    public void SearchData() {
        btnSearchFactory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //call the search method
                ArrayList<Factory> factories= dataBaseFactory.getData(editFactory.getText().toString(),
                        editBranch.getText().toString(), editCity.getText().toString(), editAddress.getText().toString(),
                        editPhone.getText().toString(), editFax.getText().toString(), editContactPerson.getText().toString(),
                        editMailingAddress.getText().toString(), editEmployeesNumber.getText().toString(),
                        editInspector.getText().toString());

                //call the view factory activity
                Intent intent = new Intent(getApplicationContext(), ViewAllFactory.class);
                intent.putExtra("factory_list", factories);
                startActivity(intent);
            }
        });
    }

}
