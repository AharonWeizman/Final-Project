package org.ecocheck.ecocheck.dto;

import org.ecocheck.ecocheck.VisitFactory;
import java.io.Serializable;

/**
 * Created by Ron on 18/03/2016.
 * this is get and set clases of visit file
 */
public class Visits implements Serializable
{
    int laboratory,reportNumber;
    String visitDate; // Sargis
    String sample,factory;
    String comments;

    boolean Confirmed;
    /**
     * try to build the multiple database
     */
    public static final String TAG = VisitFactory.class.getSimpleName();

    public static final String TABLE = "Visit";
    public static final String KEY_ReportId = "ReportNumber";
    public static final String KEY_Name = "FactoryName";
    public static final String VISIT_COLUMN_VISIT_DATE = "visit_date";
    public static final String VISIT_COLUMN_VISIT_LABORATORY = "laboratory";
    public static final String VISIT_COLUMN_VISIT_SAMPLE = "sample";
    public static final String VISIT_COLUMN_VISIT_COMMENTS = "comments";
    public static final String VISIT_COLUMN_VISIT_CONFORMED = "conformed";

    /**
     * get and set methods
     */

    public String getVisitDate()
    {
        return visitDate;
    }

    public void setVisitDate(String date) {

        this.visitDate = date;
    }

    public int getReportNumber()
    {
        return reportNumber;
    }

    public void setReportNumber(int samplesNumber)
    {
        this.reportNumber = samplesNumber;
    }

    public int getLaboratory()
    {
        return laboratory;
    }

    public void setLaboratory(int laboratory)
    {
        this.laboratory = laboratory;
    }

    public String getSample()
    {
        return sample;
    }

    public void setSample(String sample)
    {
        this.sample = sample;
    }

    public String getComments()
    {
        return comments;
    }

    public void setComments(String comments)
    {
        this.comments = comments;
    }

    public boolean isConfirmed()
    {
        return Confirmed;
    }

    public void setConfirmed(boolean confirmed)
    {
        Confirmed = confirmed;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }
}
