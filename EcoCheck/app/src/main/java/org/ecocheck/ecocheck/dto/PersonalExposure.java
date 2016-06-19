package org.ecocheck.ecocheck.dto;

/**
 * Created by Ron on 26/05/2016.
 */

public class PersonalExposure {

    private String mPerProEquipType;
    private String mPerControlDesc;
    private boolean mNew;

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

    public String getPerProEquipType() {
        return mPerProEquipType;
    }

    public void setPerProEquipType(String mPerProEquipType) {
        this.mPerProEquipType = mPerProEquipType;
    }

    public String getPerControlDesc() {
        return mPerControlDesc;
    }

    public void setPerControlDesc(String mPerControlDesc) {
        this.mPerControlDesc = mPerControlDesc;
    }

    public boolean isNew() {
        return mNew;
    }

    public void setNew(boolean mNew) {
        this.mNew = mNew;
    }

}

