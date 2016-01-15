package org.ecocheck.ecocheck;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Ron on 15/01/2016.
 */
public class EcoCheckActivityTest extends ActivityInstrumentationTestCase2 <EcoCheckActivity>
{
    public EcoCheckActivityTest(Class EcoCheckActivity)
    {
        super(EcoCheckActivity);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
    }


    //method to check  if the button AddNewFactory in EcoCheckActivity class is not null
    @SmallTest
    public void testAddFactoryButton()
    {
        Button btAddFactory= (Button) getActivity().findViewById(R.id.AddFactory);
        assertNotNull(btAddFactory);
    }

    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
}



