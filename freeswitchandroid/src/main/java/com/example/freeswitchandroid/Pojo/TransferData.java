package com.example.freeswitchandroid.Pojo;

public class TransferData {

    private String transferNumber;
    private String transferName;
    private String transferText;
    boolean checked = false;


    public TransferData(String transferNumber, String transferName) {
        this.transferNumber = transferNumber;
        this.transferName = transferName;
        if(transferName != null && !transferName.isEmpty()) {
            this.transferText = transferName.substring(0, 1);
        }
        else{
            this.transferText = transferNumber.substring(0,1);
        }
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
