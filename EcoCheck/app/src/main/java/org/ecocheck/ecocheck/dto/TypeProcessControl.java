package org.ecocheck.ecocheck.dto;

/**
 * Created by Ron on 26/05/2016.
 */

public class TypeProcessControl {

    private String mTypeOfProcessControl;
    private String mDescOfProcessControl;
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

    public String getTypeOfProcessControl() {
        return mTypeOfProcessControl;
    }

    public void setTypeOfProcessControl(String mTypeOfProcessControl) {
        this.mTypeOfProcessControl = mTypeOfProcessControl;
    }

    public String getDescOfProcessControl() {
        return mDescOfProcessControl;
    }

    public void setDescOfProcessControl(String mDescOfProcessControl) {
        this.mDescOfProcessControl = mDescOfProcessControl;
    }

    public boolean isNew() {
        return mNew;
    }

    public void setNew(boolean mNew) {
        this.mNew = mNew;
    }

}

