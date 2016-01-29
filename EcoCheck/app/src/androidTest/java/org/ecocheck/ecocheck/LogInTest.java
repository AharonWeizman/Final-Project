package org.ecocheck.ecocheck;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Ron on 29/01/2016.
 */
public class LogInTest extends ActivityInstrumentationTestCase2 <LogIn>
{
    LogIn mActivity;
    private EditText username;//edit text field username
    private EditText password;//edit text field password

    public  LogInTest(Class LogIN)
    {
        super("org.ecocheck.ecocheck.LogIn",LogIn.class);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        mActivity = this.getActivity();

        username = (EditText) mActivity.findViewById(R.id.username);//take the context from activity_log_in.xml username field
        password = (EditText) mActivity.findViewById(R.id.TFpassword);//take the context from activity_log_in.xml TFpassword field


    }

    public void testPreconditions()
    {
        assertNotNull(username);
        assertNotNull(password);
    }


    public void testText()
    {

        assertEquals("",username.getText());
        assertEquals("", password.getText());
    }


    //method to check  if the button LogIn in LogIn class is not null
    @SmallTest
    public void testLogInButton()
    {
        Button btnLogIn= (Button) getActivity().findViewById(R.id.button_logIn);
        assertNotNull(btnLogIn);
    }

    //method to check  if the button SignUp in LogIn class is not null
    @SmallTest
    public void testSignUpButton()
    {
        Button btnSignUp= (Button) getActivity().findViewById(R.id.btnSignUp);
        assertNotNull(btnSignUp);
    }


}
