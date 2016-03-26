package org.ecocheck.ecocheck.app;

import android.app.Application;
import android.content.Context;

import org.ecocheck.ecocheck.database.DatabaseMultipleManager;
import org.ecocheck.ecocheck.database.MultipleSurveyDatabase;

/**
 * Created by Ron on 21/03/2016.
 */
public class  App extends Application
{
    private static Context context;
    private static MultipleSurveyDatabase multipleSurveyDatabase;

    @Override
    public void onCreate()
    {
        super.onCreate();
        context = this.getApplicationContext();
        multipleSurveyDatabase = new MultipleSurveyDatabase();
        DatabaseMultipleManager.initializeInstance(multipleSurveyDatabase);

    }

    public static Context getContext(){
        return context;
    }

}

