package org.ecocheck.ecocheck;

import java.util.ArrayList;

import org.ecocheck.ecocheck.adapter.FactoryArrayAdapter;
import org.ecocheck.ecocheck.database.DataBaseFactory;
import org.ecocheck.ecocheck.dto.Factory;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;

public class ViewAllFactory extends AppCompatActivity
{

    DataBaseFactory dataBaseFactory;
    TextView empty;
    ListView listView;
    FactoryArrayAdapter factoryAdapter;
    ArrayList<Factory> factories;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_factory);

        // creating the data base according the DataBaseFactory class
        // constructor
        dataBaseFactory = new DataBaseFactory(this);

        empty = (TextView) findViewById(R.id.empty);//the case there are no factory the screen will display empty on all factory layout

        // Find ListView to populate
        listView = (ListView) findViewById(R.id.listView);

        // to show empty message
        listView.setEmptyView(empty);

        // check the intent extras
        try
        {
            Intent intent = getIntent();
            factories = (ArrayList<Factory>) intent.getSerializableExtra("factory_list");//
            if(factories == null)
            {
                factories = dataBaseFactory.getAllData();
            }
        }
        catch (Exception ex)
        {
            // cal the func getAllData that we create on DataBaseHelper class
            factories = dataBaseFactory.getAllData();
        }
        // Setup cursor adapter using cursor from last step
        factoryAdapter = new FactoryArrayAdapter(this,
                R.layout.activity_view_all_factory, factories);
        // Attach cursor adapter to the ListView
        listView.setAdapter(factoryAdapter);

        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Factory factory = factories.get(position);
                String message = "Factory Name: " + factory.getFactory() + "\n";
                message += "Branch: " + factory.getBranch() + "\n";
                message += "City: " + factory.getCity() + "\n";
                message += "Address: " + factory.getAddress() + "\n";
                message += "Phone: " + factory.getPhone() + "\n";
                message += "Fax: " + factory.getFax() + "\n";
                message += "Contact: " + factory.getContact() + "\n";
                message += "Mailing Address: " + factory.getMailing_address()
                        + "\n";
                message += "Employee Number: " + factory.getEmployee_number()
                        + "\n";
                message += "Inspector: " + factory.getInspector();

                showMassage("Factory Info", message);
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
