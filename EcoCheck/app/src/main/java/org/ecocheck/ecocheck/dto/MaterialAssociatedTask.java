package org.ecocheck.ecocheck.dto;

/**
 * Created by Ron on 26/05/2016.
 */



public class MaterialAssociatedTask {

    private String mMaterialTradeName;
    private String mUsingMaterial;
   



    private boolean mLinked;
    private boolean mTinyAmount;
    private boolean mMSDS;

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

    public String getMaterialTradeName() {
        return mMaterialTradeName;
    }

    public void setMaterialTradeName(String mMaterialTradeName) {
        this.mMaterialTradeName = mMaterialTradeName;
    }

    public String getUsingMaterial() {
        return mUsingMaterial;
    }

    public void setUsingMaterial(String mUsingMaterial) {
        this.mUsingMaterial = mUsingMaterial;
    }

  

    public boolean ismMSDS() {
        return mMSDS;
    }

    public void setmMSDS(boolean mMSDS) {
        this.mMSDS = mMSDS;
    }

    public boolean ismLinked() {
        return mLinked;
    }

    public void setmLinked(boolean mLinked) {
        this.mLinked = mLinked;
    }

    public boolean ismTinyAmount() {
        return mTinyAmount;
    }

    public void setmTinyAmount(boolean mTinyAmount) {
        this.mTinyAmount = mTinyAmount;
    }
}

