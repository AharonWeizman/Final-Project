package org.ecocheck.ecocheck;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Ron on 29/01/2016.
 */
public class SignUpTest extends ActivityInstrumentationTestCase2<SignUp>
{
    SignUp mActivity;
    private EditText Name;//edit text field represent the name of the user
    private EditText EmailAddress;//edit text field represent the email user
    private EditText UserName;//edit text field represent the UserName
    private EditText PassWord;//edit text field represent the password user
    private EditText ConfirmPassWord ;//edit text field confirm password
    private Button btnsignUP;// button field

    public  SignUpTest(Class LogIN)
    {
        super("org.ecocheck.ecocheck.SignUp",SignUp.class);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        mActivity = this.getActivity();

        Name = (EditText) mActivity.findViewById(R.id.TFname);//take the context from activity_sign_up.xml name field
        EmailAddress = (EditText) mActivity.findViewById(R.id.TFemail);//take the context from activity_sign_up.xml email field
        UserName = (EditText) mActivity.findViewById(R.id.TFuserName);//take the context from activity_sign_up.xml username field
        PassWord = (EditText) mActivity.findViewById(R.id.TFpassWord);//take the context from activity_sign_up.xml password field
        ConfirmPassWord = (EditText) mActivity.findViewById(R.id.TFConfirmPass);//take the context from activity_sign_up.xml confirm passWord field


    }
    //test the TextField
    public void testPreconditions()
    {
        assertNotNull(Name);
        assertNotNull(EmailAddress);
        assertNotNull(UserName);
        assertNotNull(PassWord);
        assertNotNull(ConfirmPassWord);
    }


    public void testText()
    {

        assertEquals("", Name.getText());
        assertEquals("", EmailAddress.getText());
        assertEquals("", UserName.getText());
        assertEquals("", PassWord.getText());
        assertEquals("", ConfirmPassWord.getText());
    }

    //method to check  if the button LogIn in LogIn class is not null
    @SmallTest
    public void testSignUpButton()
    {

        btnsignUP=(Button) getActivity().findViewById(R.id.button_Sing_up);//take the context from activity_sign_up.xml signUp Button
        assertNotNull(btnsignUP);
    }

}
