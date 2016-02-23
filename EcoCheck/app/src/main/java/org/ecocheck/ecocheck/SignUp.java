package org.ecocheck.ecocheck;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.ecocheck.ecocheck.database.LogInDataBase;
import org.ecocheck.ecocheck.dto.Contact;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ron on 12/01/2016.
 * sign up class
 * fill up the field, and check the validation of the data that the user input
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
        if(v.getId()==R.id.button_Sing_up)
        {
            EditText name= (EditText)findViewById(R.id.TFname);
            EditText emai= (EditText)findViewById(R.id.TFemail);
            EditText uName= (EditText)findViewById(R.id.TFuserName);
            EditText pass= (EditText)findViewById(R.id.TFpassWord);
            EditText ConfirmPass= (EditText)findViewById(R.id.TFConfirmPass);

            String namestr= name.getText().toString();
            //call the method isValidName
            if(!isValidName(namestr))
            {
                name.setError("must to put name");//Edit text Name trow error if the name field is empty
            }

            String emailstr= emai.getText().toString();
            //call the method valid email
            if (!isValidEmail(emailstr))
            {
                emai.setError("Invalid Email");
            }

            String unamestr= uName.getText().toString();
            //call the method isValidUserName
            if(!isValidUserName(unamestr))
            {
                uName.setError("Invalid UserName. at least 3 character");
            }

            String passStr= pass.getText().toString();
            //call the method valid password
            if (!isValidPassword(passStr))
            {
                pass.setError("Invalid Password. at least 5 character ");//pas edit text throw invalid password
            }

            String confirmpassStr= ConfirmPass.getText().toString();

            //check if the PassWord And Confirm PassWord is equals
            if(!passStr.equals(confirmpassStr))
            {
                //pop up massage
                Toast passW= Toast.makeText(SignUp.this,"PassWord don't match",Toast.LENGTH_SHORT);
                passW.show();
            }

            // check if all the data the user put is ok. put the user log in data in data base and enter to eco check main activity.
            if(isValidName(namestr) && isValidUserName(unamestr)&& isValidEmail(emailstr)&& isValidPassword(passStr)&& passStr.equals(confirmpassStr))
            {
                //insert the details in the data base
                Contact c= new Contact();// make object of contact class
                c.setName(namestr);
                c.setEmail(emailstr);
                c.setUserName(unamestr);
                c.setPass(passStr);

                logInDataBase.insertContact(c);

                Intent intent = new Intent("org.ecocheck.ecocheck.EcoCheckActivity");//take the context from
                startActivity(intent);


            }

        }
    }


    // check valid email
    private boolean isValidEmail(String email)
    {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // valid password method
    private boolean isValidPassword(String pass)
    {
        if (pass != null && pass.length() > 4)
        {
            return true;
        }
        return false;
    }

    // method that check validation userName
    private boolean isValidUserName(String userN)
    {
        if(userN!=null && userN.length()>2)
        {
            return true;
        }
        return false;
    }

    private boolean isValidName(String Name)
    {
        if(Name!=null && Name.length()>1)
        {
            return true;
        }
        return false;
    }


}
