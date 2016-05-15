package org.ecocheck.ecocheck.dto;

import org.ecocheck.ecocheck.FactoryRecommendation;

import java.io.Serializable;

/**
 * Created by Ron on 04/04/2016.
 */
public class Recommendation implements Serializable
{
    int reportNumber;
    String recommendation;

    public static final String TAG = FactoryRecommendation.class.getSimpleName();

    public static final String TABLE = "Recommendations";
    public static final String KEY_ReportId = "ReportNumber";
    public static final String COLUMN_RECOMMENDATION = "recommendation";


    public String getRecommendation()
    {
        return recommendation;
    }

    public void setRecommendation(String recommendation)
    {
        this.recommendation = recommendation;
    }

    public int getReportNumber()
    {
        return reportNumber;
    }

    public void setReportNumber(int reportNumber)
    {
        this.reportNumber = reportNumber;
    }


}
