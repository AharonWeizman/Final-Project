package org.ecocheck.ecocheck;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.ecocheck.ecocheck.database.DataBaseFactory;
import org.ecocheck.ecocheck.dto.Factory;

public class AddNewFactory extends AppCompatActivity
{
    DataBaseFactory dataBaseFactory;
    EditText editAfik,editFactory,editBranch,editCity,editAddress,editPhone,editFax,editContactPerson, editMailingAddress, editEmployeesNumber,editInspector;//Edit text field acording activity_add_new_factory
    Button btnAddFactory;//field button that refer to activity_add_new_factory click on this button will add the factory details to mySql.

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_factory);
        dataBaseFactory= new DataBaseFactory(this);//creating the data base according the DataBaseFactory class constructor

        editAfik=(EditText)findViewById(R.id.editText_AfikNumber);//give the field the EditText from activity_add_new_factory
        editFactory=(EditText)findViewById(R.id.editText_Factory);//give the field the EditText from activity_add_new_factory
        editBranch=(EditText)findViewById(R.id.editText_Branch);//give the field the EditText from activity_add_new_factory
        editCity=(EditText)findViewById(R.id.editText_City);//give the field the EditText from activity_add_new_factory
        editAddress=(EditText)findViewById(R.id.editText_Adress);//give the field the EditText from activity_add_new_factory
        editPhone=(EditText)findViewById(R.id.editText_PhoneNumber);//give the field the EditText from activity_add_new_factory
        editFax=(EditText)findViewById(R.id.editText_FaxNumber);//give the field the EditText from activity_add_new_factory
        editContactPerson=(EditText)findViewById(R.id.editText_ContactPerson);//give the field the EditText from activity_add_new_factory
        editMailingAddress=(EditText)findViewById(R.id.editText_MailingAddress);//give the field the EditText from activity_add_new_factory
        editEmployeesNumber=(EditText)findViewById(R.id.editText_NumberOfEmployees);//give the field the EditText from activity_add_new_factory
        editInspector=(EditText)findViewById(R.id.editText_Inspector);//give the field  EditText parmeter from activity_add_new_factory

        btnAddFactory=(Button)findViewById(R.id.AddFactory);//give the field parmeter from activity_add_new_factory click

        AddData();
    }

    //method to add data to the sqlLite DataBase on click
    public void AddData()
    {
        btnAddFactory.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)// on click call to insertFactoryData
            // method from DataBaseFactory class
            {
                Factory factory = new Factory();
                factory.setId(Integer.parseInt(editAfik.getText().toString()));
                factory.setFactory(editFactory.getText().toString());
                factory.setBranch(editBranch.getText().toString());
                factory.setCity(editCity.getText().toString());
                factory.setAddress(editAddress.getText().toString());
                factory.setPhone(editPhone.getText().toString());
                factory.setFax(editFax.getText().toString());
                factory.setContact(editContactPerson.getText().toString());
                factory.setMailing_address(editMailingAddress.getText()
                        .toString());
                factory.setEmployee_number(editEmployeesNumber.getText()
                        .toString());
                factory.setInspector(editInspector.getText().toString());

                boolean isInserted = dataBaseFactory.insertFactoryData(factory);
                if (isInserted == true)
                    Toast.makeText(AddNewFactory.this, "Data Inserted",
                            Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(AddNewFactory.this, "Data not Inserted",
                            Toast.LENGTH_LONG).show();
            }
        });
    }

    public void showMassage(String title, String message)
    {
        //instance of alertDialog window
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);// cancel it after is use
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();//show our dialog
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
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
