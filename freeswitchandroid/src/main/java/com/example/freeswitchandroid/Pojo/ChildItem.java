package com.example.freeswitchandroid.Pojo;

public class ChildItem {

    // Declaration of the variable
    private String ChildItemTitle;
    private int ChildItemImg;
    private String ChildItemTxt;

    private String number;

    // Constructor of the class
    // to initialize the variable*
    public ChildItem(String number, String childItemTitle, int childItemImg, String childItemTxt)
    {
        this.number = number;
        this.ChildItemTitle = childItemTitle;
        this.ChildItemImg = childItemImg;
        this.ChildItemTxt = childItemTxt;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    // Getter and Setter method
    // for the parameter
    public String getChildItemTitle()
    {
        return ChildItemTitle;
    }

    public int getChildItemImg()
    {
        return ChildItemImg;
    }

    public String getChildItemTxt()
    {
        return ChildItemTxt;
    }

    public void setChildItemTitle(
            String childItemTitle)
    {
        ChildItemTitle = childItemTitle;
    }
}
