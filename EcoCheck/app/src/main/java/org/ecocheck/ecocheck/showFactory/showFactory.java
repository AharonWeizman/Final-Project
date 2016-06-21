package org.ecocheck.ecocheck.ShowFactory;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Canvas;

import org.ecocheck.ecocheck.R;

import android.print.PrintAttributes;
import android.print.PrintAttributes.Margins;
import android.print.PrintAttributes.Resolution;
import android.print.pdf.PrintedPdfDocument;

import android.graphics.pdf.PdfDocument;
import android.graphics.pdf.PdfDocument.Page;
import android.graphics.pdf.PdfDocument.PageInfo;

import android.graphics.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.*;
import android.net.Uri;

public class showFactory extends AppCompatActivity
{
    TextView afikNumber,factoryName,branch,city,address,
            phoneNumber,faxNumber,contact,mailingAddress,
            employeeNumber,inspector;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_factory);

        afikNumber= (TextView) findViewById(R.id.Text_AfikNumber_show);
        factoryName= (TextView) findViewById(R.id.Text_Factory_show);
        branch= (TextView) findViewById(R.id.Text_Branch_show);
        city= (TextView) findViewById(R.id.Text_City_show);
        address= (TextView) findViewById(R.id.Text_Adress_show);
        phoneNumber= (TextView) findViewById(R.id.Text_PhoneNumber_show);
        faxNumber= (TextView) findViewById(R.id.Text_FaxNumber_show);
        contact= (TextView) findViewById(R.id.Text_ContactPerson_show);
        mailingAddress= (TextView) findViewById(R.id.Text_MailingAddress_show);
        employeeNumber= (TextView) findViewById(R.id.Text_NumberOfEmployees_show);
        inspector= (TextView) findViewById(R.id.Text_Inspector_show);

        Intent intentExtras=getIntent();

        String AfikNumber=intentExtras.getStringExtra("AfikNumber");
        String FactoryName=intentExtras.getStringExtra("FactoryName");
        String Branch=intentExtras.getStringExtra("Branch");
        String City=intentExtras.getStringExtra("City");
        String Address=intentExtras.getStringExtra("Address");
        String Phone=intentExtras.getStringExtra("PhoneNumber");
        String Fax=intentExtras.getStringExtra("faxNumber");
        String Contact=intentExtras.getStringExtra("Contact");
        String MailingAddress=intentExtras.getStringExtra("MailingAddress");
        String EmployeeNumber=intentExtras.getStringExtra("EmployeeNumber");
        String Inspector=intentExtras.getStringExtra("Inspector");

        afikNumber.setText(AfikNumber);
        factoryName.setText(FactoryName);
        branch.setText(Branch);
        city.setText(City);
        address.setText(Address);
        phoneNumber.setText(Phone);
        faxNumber.setText(Fax);
        contact.setText(Contact);
        mailingAddress.setText(MailingAddress);
        employeeNumber.setText(EmployeeNumber);
        inspector.setText(Inspector);

    }

    public void btnConvertPDF(View view)
    {
        String description = "Pdf created!";

        File pdfFile = createPDF();
        // case pdf cannot be created do not open the email app, show a warning
        if (pdfFile == null)
        {

            description = "Cannot create the pdf";
            Toast.makeText(this, description, Toast.LENGTH_LONG)
                    .show();
        }

        Toast.makeText(this, description, Toast.LENGTH_LONG)
                .show();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private File createPDF()
    {
        File pdfFile = null;

        // Create a   blank PDF document in memory
        PrintAttributes printAttrs = new PrintAttributes.Builder().
                setColorMode(PrintAttributes.COLOR_MODE_COLOR).
                setMediaSize(PrintAttributes.MediaSize.NA_LETTER).
                setResolution(new PrintAttributes.Resolution("zooey", PRINT_SERVICE, 100, 100)).
                setMinMargins(PrintAttributes.Margins.NO_MARGINS).
                build();
        PdfDocument document = new PrintedPdfDocument(this, printAttrs);

        // get the reference to the container RelativeLayout
        View content = getContentView();

        /*  display all contents of the view like the view  and remove the button from the pdf by reset their height
          */
        Button createPDFButton = (Button) findViewById(R.id.sendToEmail);

        int neededPageSizeWidth = content.getMeasuredWidth();
        int neededPageSizeHeight = content.getMeasuredHeight();

        neededPageSizeHeight -= createPDFButton.getHeight();

        // create a page description, with size
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder( neededPageSizeWidth, neededPageSizeHeight, 1).create();
        // create a new page from the PageInfo
        PdfDocument.Page page = document.startPage(pageInfo);

        //use canvas of page object and draw in it
        Canvas canvas = page.getCanvas();
        content.draw(canvas);

        // do final processing of the page
        document.finishPage(page);


        try {

            /*  creating the file  at externalStorage/Documents/EcoCheck path
            
                with filename factoryInfo<name><date>.pdf format
             */

            File pdfFolder = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOCUMENTS), "EcoCheck");
            if (!pdfFolder.exists())
            {
                boolean res = pdfFolder.mkdirs();
                Log.i("LOG_TAG", "Pdf Directory created");
            }

            //Create time stamp
            Date date = new Date();
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(date);

            // create the file in path pdfFolder with name factoryInfo<name><date>.pdf format
            pdfFile = new File(pdfFolder, timeStamp + ".pdf");

            OutputStream output = new FileOutputStream(pdfFile);

            //close gracefully
            document.writeTo(output);
            document.close();
            output.close();

        } catch (IOException e)
        {
            throw new RuntimeException("Error generating file", e);
        }

        return pdfFile;
    }

    private View getContentView() {
        return findViewById(R.id.relativeLayout);
    }

    public void btnSendToEmail(View view) {


        // try to create the pdf file if it fails
        File pdfFile = createPDF();
        if (pdfFile == null)
        {
            
            Toast.makeText(this, "Cannot create the pdf", Toast.LENGTH_LONG)
                    .show();

            return;
        }

        /*
         call the intent send email, add attachment, and set permission to read it,
         open chooser for email, and send
          */

        Uri path = Uri.fromFile(pdfFile);
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        // set the type to 'email'
        emailIntent.setType("vnd.android.cursor.dir/email");
       

        emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
      
        emailIntent.putExtra(Intent.EXTRA_STREAM, path);

        
        //        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }
}
