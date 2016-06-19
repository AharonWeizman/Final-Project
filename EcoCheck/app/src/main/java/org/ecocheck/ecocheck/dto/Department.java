package org.ecocheck.ecocheck.dto;

/**
 * Created by Ron on 26/05/2016.
 */


import java.io.Serializable;


public class Department implements Serializable
{

    private String mDeptName;
    private int mReportNo;
    private long _ID;



    public String getDeptName() {
        return mDeptName;
    }

    public void setDeptName(String mDeptName) {
        this.mDeptName = mDeptName;
    }

    public int getReportNo() {
        return mReportNo;
    }

    public void setReportNo(int mReportNo) {
        this.mReportNo = mReportNo;
    }

    public long get_ID() {
        return _ID;
    }

    public void set_ID(long _ID) {
        this._ID = _ID;
    }
}

