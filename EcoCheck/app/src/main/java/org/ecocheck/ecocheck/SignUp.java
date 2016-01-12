package org.ecocheck.ecocheck;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Ron on 12/01/2016.
 * this class is the SignUp method and fields
 */
public class SignUp extends Activity
{
    LogInDataBase logInDataBase= new LogInDataBase(this);//make object type LogInDataBase class
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


    }

    public void onSignUpClick(View v)
    {
        // check if the Sign Up button was clicked
        if(v.getId()==R.id.button_Sing_up)
        {
            EditText name= (EditText)findViewById(R.id.TFname);
            EditText emai= (EditText)findViewById(R.id.TFemail);
            EditText uName= (EditText)findViewById(R.id.TFuserName);
            EditText pass= (EditText)findViewById(R.id.TFpassWord);
            EditText ConfirmPass= (EditText)findViewById(R.id.TFConfirmPass);

            String namestr= name.getText().toString();
            String emailstr= emai.getText().toString();
            String unamestr= uName.getText().toString();
            String passStr= pass.getText().toString();
            String confirmpassStr= ConfirmPass.getText().toString();

            //check if the PassWord And Confirm PassWord is not equals
            if(!passStr.equals(confirmpassStr))
            {
                //pop up massage
                Toast passW= Toast.makeText(SignUp.this,"PassWord don't match",Toast.LENGTH_SHORT);
                passW.show();
            }

            else
            {
                //insert the details in the data base
                Contact c= new Contact();// make object of contact class
                c.setName(namestr);
                c.setEmail(emailstr);
                c.setUserName(unamestr);
                c.setPass(passStr);

                logInDataBase.insertContact(c);

            }

        }
    }
}
