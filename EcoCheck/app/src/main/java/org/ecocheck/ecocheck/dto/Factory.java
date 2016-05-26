package org.ecocheck.ecocheck.dto;

import java.io.Serializable;

/**
 * Created by Ron on 15/02/2016.
 * this is get and set method of all details of data factory
 */
public class Factory implements Serializable
{
    private Integer id;
    private String factory;
    private String branch;
    private String city;
    private String address;
    private String phone;
    private String fax;
    private String contact;
    private String mailing_address;
    private String employee_number;
    private String inspector;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getFactory()
    {
        return factory;
    }

    public void setFactory(String factory)
    {
        this.factory = factory;
    }

    public String getBranch()
    {
        return branch;
    }

    public void setBranch(String branch)
    {
        this.branch = branch;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getFax()
    {
        return fax;
    }

    public void setFax(String fax)
    {
        this.fax = fax;
    }

    public String getContact()
    {
        return contact;
    }

    public void setContact(String contact)
    {
        this.contact = contact;
    }

    public String getMailing_address()
    {
        return mailing_address;
    }

    public void setMailing_address(String mailing_address)
    {
        this.mailing_address = mailing_address;
    }

    public String getEmployee_number()
    {
        return employee_number;
    }

    public void setEmployee_number(String employee_number)
    {
        this.employee_number = employee_number;
    }

    public String getInspector()
    {
        return inspector;
    }

    public void setInspector(String inspector)
    {
        this.inspector = inspector;
    }
}
