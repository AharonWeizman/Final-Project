package org.ecocheck.ecocheck;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.ecocheck.ecocheck.adapter.MaterialAdapter;
import org.ecocheck.ecocheck.database.DataBaseFactory;
import org.ecocheck.ecocheck.dto.Material;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;
import java.util.ArrayList;


public class Materials extends AppCompatActivity
{

    ListView listview;
    DataBaseFactory dataBaseFactory;

    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materials);

      

        listview = (ListView) findViewById(R.id.listview);

        // creating the data base according the DataBaseFactory class
        dataBaseFactory = new DataBaseFactory(this);

        ArrayList<Material> materials = null;
        ///
        // check the intent extras
        try
        {
            // cal the func getAllData that we create on DataBaseHelper class
            materials = dataBaseFactory.getAllMaterialsData();
        }
        catch (Exception ex)
        {
            Log.d("MyApp","I am here");
        }

        listview.setAdapter(new MaterialAdapter(this, R.layout.activity_materials, materials));
    }



    public class JSONTask extends AsyncTask<String,String,String>
    {

        @Override
        protected String doInBackground(String... urls) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(urls[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null)
                        reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);

        }
    }


    ///

}
