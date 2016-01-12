package org.ecocheck.ecocheck;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/**
 * Created by Ron on 12/01/2016.
 * LogIn class reference to click signUP and login Button
 * on Click LogIn Button there is a check that the user name and Password are good and define in the SQLite data base
 */
public class LogIn extends AppCompatActivity
{
    LogInDataBase logInDataBase= new LogInDataBase(this);//logInDataBase is an object of logInDataBase class
    private  static Button btnLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);//run the layout activity_Log_in
    }


    public void onButtonClick(View v)
    {
        // check if the button Log In was pressed
        if(v.getId()==R.id.button_logIn)
        {
            // a is the reference to the userName Edit Text
            EditText a = (EditText) findViewById(R.id.username);
            String str = a.getText().toString();
            //b is the reference to the passWord Edit Text
            EditText b = (EditText) findViewById(R.id.TFpassword);
            String pass = b.getText().toString();
            //Password get the value from LogInDataBase.searchPass
            String password = logInDataBase.searchPass(str);
            //if the pass word is match it will be open the ecoCheckActivity
            if (pass.equals(password))
            {
                Intent intent = new Intent("org.ecocheck.ecocheck.EcoCheckActivity");//take the context from
                startActivity(intent);
            }
            else
            {
                //pop up massage
                Toast passW = Toast.makeText(LogIn.this, "User name and password not match", Toast.LENGTH_SHORT);
                passW.show();
            }

        }


        //check if the button Sign Up was pressed
        if(v.getId()== R.id.btnSignUp)
        {
            Intent i=new Intent( "org.ecocheck.ecocheck.SignUp");
            startActivity(i);
        }
    }
}
