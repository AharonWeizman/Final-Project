package org.ecocheck.ecocheck;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Ron on 29/01/2016.
 */
public class AddNewFactoryTest extends ActivityInstrumentationTestCase2<AddNewFactory>
{
    AddNewFactory mActivity;
    private EditText afikNumber;//edit text field
    private EditText factory;//edit text field
    private EditText branch;//edit text field
    private EditText city;//edit text field
    private EditText address ;//edit text field
    private EditText phoneNumber ;//edit text field
    private EditText contactPerson ;//edit text field
    private EditText mailingAddress;//edit text field
    private EditText employeesNumber ;//edit text field
    private EditText inspector ;//edit text field
    private Button btnAddFactory;// button field

    public  AddNewFactoryTest(Class LogIN)
    {
        super("org.ecocheck.ecocheck.AddNewFactory",AddNewFactory.class);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        mActivity = this.getActivity();

        afikNumber = (EditText) mActivity.findViewById(R.id.editText_AfikNumber);//take the context from activity_add_new_factory.xml
        factory = (EditText) mActivity.findViewById(R.id.editText_Factory);//take the context from activity_add_new_factory.xml
        branch = (EditText) mActivity.findViewById(R.id.editText_Branch);//take the context from activity_add_new_factory.xml
        city = (EditText) mActivity.findViewById(R.id.editText_City);//take the context from activity_add_new_factory.xml
        address = (EditText) mActivity.findViewById(R.id.editText_Adress);//take the context from activity_add_new_factory.xml
        phoneNumber = (EditText) mActivity.findViewById(R.id.editText_PhoneNumber);//take the context from activity_add_new_factory.xml
        contactPerson = (EditText) mActivity.findViewById(R.id.editText_ContactPerson);//take the context from activity_add_new_factory.xml
        mailingAddress = (EditText) mActivity.findViewById(R.id.editText_MailingAddress);//take the context from activity_add_new_factory.xml
        employeesNumber = (EditText) mActivity.findViewById(R.id.editText_NumberOfEmployees);//take the context from activity_add_new_factory.xml
        inspector = (EditText) mActivity.findViewById(R.id.editText_Inspector);//take the context from activity_add_new_factory.xml
        btnAddFactory=(Button) mActivity.findViewById(R.id.AddFactory);//take the context from activity_add_new_factory.xml add factory button
    }
    //test the TextField
    public void testPreconditions()
    {
        assertNotNull(afikNumber);
        assertNotNull(factory);
        assertNotNull(branch);
        assertNotNull(city);
        assertNotNull(address);
        assertNotNull(phoneNumber);
        assertNotNull(contactPerson);
        assertNotNull(mailingAddress);
        assertNotNull(employeesNumber);
        assertNotNull(inspector);
    }


    public void testText()
    {

        assertEquals("", afikNumber.getText());
        assertEquals("", factory.getText());
        assertEquals("", branch.getText());
        assertEquals("", city.getText());
        assertEquals("", address.getText());
        assertEquals("", phoneNumber.getText());
        assertEquals("", contactPerson.getText());
        assertEquals("", mailingAddress.getText());
        assertEquals("", employeesNumber.getText());
        assertEquals("", inspector.getText());
    }

    //method to check  if the button LogIn in LogIn class is not null
    @SmallTest
    public void testAddFactoryButton()
    {
        assertNotNull(btnAddFactory);
    }
}
