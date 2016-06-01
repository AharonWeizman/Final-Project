package org.ecocheck.ecocheck.dto;

/**
 * Created by bhavin on 5/24/16.
 */
public class MaterialAssociatedTask {

    private String mMaterialTradeName;
    private String mUsingMaterial;
    private String mLinked;
    private String mTinyAmount;
    private String mMSDS;

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

    public String getLinked() {
        return mLinked;
    }

    public void setLinked(String mLinked) {
        this.mLinked = mLinked;
    }

    public String getTinyAmount() {
        return mTinyAmount;
    }

    public void setTinyAmount(String mTinyAmount) {
        this.mTinyAmount = mTinyAmount;
    }

    public String getMSDS() {
        return mMSDS;
    }

    public void setMSDS(String mMSDS) {
        this.mMSDS = mMSDS;
    }
}
