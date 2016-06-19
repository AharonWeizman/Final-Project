package org.ecocheck.ecocheck.dto;

/**
 * Created by Ron on 26/05/2016.
 */



public class ChemicalExposure
 {

    private String mChemicalExposureName;
    private String mDescInhalantExposure;
    private String mProcess;
    private String mAssignmentName;
    private String mDepartment;
    private int mReportNo;

    public String getAssignmentName() {
        return mAssignmentName;
    }

    public void setAssignmentName(String mAssignmentName) {
        this.mAssignmentName = mAssignmentName;
    }

    public String getDepartment() {
        return mDepartment;
    }

    public void setDepartment(String mDepartment) {
        this.mDepartment = mDepartment;
    }

    public int getReportNo() {
        return mReportNo;
    }

    public void setReportNo(int mReport_no) {
        this.mReportNo = mReport_no;
    }

    public String getProcess() {
        return mProcess;
    }

    public void setProcess(String mProcess) {
        this.mProcess = mProcess;
    }


    public String getDescInhalantExposure() {
        return mDescInhalantExposure;
    }

    public void setDescInhalantExposure(String mDescInhalantExposure) {
        this.mDescInhalantExposure = mDescInhalantExposure;
    }

    public String getChemicalExposureName() {
        return mChemicalExposureName;
    }

    public void setChemicalExposureName(String mChemicalExposureName) {
        this.mChemicalExposureName = mChemicalExposureName;
    }

}

