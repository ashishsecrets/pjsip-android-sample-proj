package com.example.freeswitchandroid.Pojo;

public class TransferData {

    private String transferNumber;
    private String transferName;
    private String transferText;
    boolean checked = false;


    public TransferData(String transferNumber, String transferName, String transferText) {
        this.transferNumber = transferNumber;
        this.transferName = transferName;
        this.transferText = transferText;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    public String getTransferNumber() {
        return transferNumber;
    }

    public void setTransferNumber(String transferNumber) {
        this.transferNumber = transferNumber;
    }

    public String getTransferName() {
        return transferName;
    }

    public void setTransferName(String transferName) {
        this.transferName = transferName;
    }

    public String getTransferText() {
        return transferText;
    }

    public void setTransferText(String transferText) {
        this.transferText = transferText;
    }
}
