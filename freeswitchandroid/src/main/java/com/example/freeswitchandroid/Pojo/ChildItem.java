package com.example.freeswitchandroid.Pojo;

public class ChildItem {

    // Declaration of the variable
    private String ChildItemTitle;

    // Constructor of the class
    // to initialize the variable*
    public ChildItem(String childItemTitle)
    {
        this.ChildItemTitle = childItemTitle;
    }

    // Getter and Setter method
    // for the parameter
    public String getChildItemTitle()
    {
        return ChildItemTitle;
    }

    public void setChildItemTitle(
            String childItemTitle)
    {
        ChildItemTitle = childItemTitle;
    }
}
