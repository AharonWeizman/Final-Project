package org.ecocheck.ecocheck;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class EcoCheckActivity extends ActionBarActivity
{
    private  static Button button_Add_Factory;//the button that refer to add factory
    private  static Button button_Find_Factory;//the button that refer to find factory
    private  static Button button_View_All_Factory;//the button that refer to viewAllFactory

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eco_check);
        AddNewFactoryClickListener();//call the method AddNewFactoryClickListener
        FindFactoryClickListener();//call the method FindFactoryClickListener
        ViewAllFactoryClickListener();//call the method ViewAllFactory
    }

    //method that on butoon click open new activity of AddNewFactory
    public void AddNewFactoryClickListener()
    {
        button_Add_Factory=(Button) findViewById(R.id.NewFactory);//instance for NewFactory Button in activity_eco_Check.xml
        button_Add_Factory.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("org.ecocheck.ecocheck.AddNewFactory");//take the context from activity_add_new_factory.xml
                        startActivity(intent);
                    }
                }
        );
    }

    //method that on butoon click open new activity of FindFactory
    public void FindFactoryClickListener()
    {
        button_Find_Factory= (Button)findViewById(R.id.findFactory);//instance for FindFactory Button in activity_eco_Check.xml
        button_Find_Factory.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent("org.ecocheck.ecocheck.FindFactory");//take the context from activity_find_factory.xml
                        startActivity(intent);
                    }
                }
        );
    }

    //method that on butoon click open new activity of ViewAllFactory class
    public void ViewAllFactoryClickListener()
    {
        button_View_All_Factory= (Button)findViewById(R.id.AllTheFactory);//instance for FindFactory Button in activity_eco_Check.xml
        button_View_All_Factory.setOnClickListener
                (
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent("org.ecocheck.ecocheck.ViewAllFactory");//take the context from activity_find_factory.xml
                        startActivity(intent);
                    }
                }
        );
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_eco_check, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
