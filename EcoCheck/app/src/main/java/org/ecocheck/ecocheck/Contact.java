package org.ecocheck.ecocheck;

/**
 * Created by Ron on 12/01/2016.
 * this class is get and set class for the signUp class field
 */
public class Contact
{
    int id;
    String name,email,userName,pass;

    public void setId(int id)
    {
        this.id=id;
    }

    public int getId()
    {
        return this.id;
    }

    public void setName(String name)
    {
        this.name=name;
    }

    public String getName()
    {
        return this.name;
    }

    public void setEmail(String email)
    {
        this.email=email;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setUserName(String userName)
    {
        this.userName=userName;
    }

    public String getUserName()
    {
        return this.userName;
    }

    public void setPass(String pass)
    {
        this.pass=pass;
    }

    public String getPass()
    {
        return this.pass;
    }


}
