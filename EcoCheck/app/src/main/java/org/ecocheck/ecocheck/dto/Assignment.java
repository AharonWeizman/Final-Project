package org.ecocheck.ecocheck.dto;

/**
 * Created by Ron on 26/05/2016.
 */


import java.io.Serializable;


public class Assignment implements Serializable {

    public String mAssignmentName;
    public String mEmployeeNo;
    public String mDepartment;
    public int mReportNo;

    public String getAssignmentName() {
        return mAssignmentName;
    }

    public void setAssignmentName(String mAssignmentName) {
        this.mAssignmentName = mAssignmentName;
    }

    public String getEmployeeNo() {
        return mEmployeeNo;
    }

    public void setEmployeeNo(String mEmployeeNo) {
        this.mEmployeeNo = mEmployeeNo;
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

}

