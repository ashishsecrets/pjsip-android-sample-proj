package com.example.freeswitchandroid.rest.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("last_modified")
    @Expose
    private String lastModified;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("caller_id")
    @Expose
    private String callerId;
    @SerializedName("duration_secs")
    @Expose
    private Integer durationSecs;
    @SerializedName("cost_currency")
    @Expose
    private String costCurrency;
    @SerializedName("cost")
    @Expose
    private String cost;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("carrier_name")
    @Expose
    private Object carrierName;
    @SerializedName("captured_digits")
    @Expose
    private Object capturedDigits;
    @SerializedName("is_missed_call")
    @Expose
    private Boolean isMissedCall;
    @SerializedName("has_voice_mail")
    @Expose
    private Boolean hasVoiceMail;
    @SerializedName("is_forwarded_call")
    @Expose
    private Boolean isForwardedCall;
    @SerializedName("is_dialed")
    @Expose
    private Boolean isDialed;
    @SerializedName("recording")
    @Expose
    private String recording;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("sip_call_id")
    @Expose
    private String sipCallId;
    @SerializedName("recipient_number")
    @Expose
    private String recipientNumber;
    @SerializedName("recipient_name")
    @Expose
    private Object recipientName;
    @SerializedName("direction")
    @Expose
    private String direction;
    @SerializedName("disposition")
    @Expose
    private String disposition;
    @SerializedName("origination_type")
    @Expose
    private String originationType;
    @SerializedName("call_type")
    @Expose
    private String callType;
    @SerializedName("originally_called_from")
    @Expose
    private String originallyCalledFrom;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("provider")
    @Expose
    private Object provider;
    @SerializedName("receiver")
    @Expose
    private Object receiver;
    @SerializedName("caller_name")
    @Expose
    private Object callerName;
    @SerializedName("call_notes")
    @Expose
    private List<Object> callNotes;
    @SerializedName("note")
    @Expose
    private Object note;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getCallerId() {
        return callerId;
    }

    public void setCallerId(String callerId) {
        this.callerId = callerId;
    }

    public Integer getDurationSecs() {
        return durationSecs;
    }

    public void setDurationSecs(Integer durationSecs) {
        this.durationSecs = durationSecs;
    }

    public String getCostCurrency() {
        return costCurrency;
    }

    public void setCostCurrency(String costCurrency) {
        this.costCurrency = costCurrency;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Object getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(Object carrierName) {
        this.carrierName = carrierName;
    }

    public Object getCapturedDigits() {
        return capturedDigits;
    }

    public void setCapturedDigits(Object capturedDigits) {
        this.capturedDigits = capturedDigits;
    }

    public Boolean getIsMissedCall() {
        return isMissedCall;
    }

    public void setIsMissedCall(Boolean isMissedCall) {
        this.isMissedCall = isMissedCall;
    }

    public Boolean getHasVoiceMail() {
        return hasVoiceMail;
    }

    public void setHasVoiceMail(Boolean hasVoiceMail) {
        this.hasVoiceMail = hasVoiceMail;
    }

    public Boolean getIsForwardedCall() {
        return isForwardedCall;
    }

    public void setIsForwardedCall(Boolean isForwardedCall) {
        this.isForwardedCall = isForwardedCall;
    }

    public Boolean getIsDialed() {
        return isDialed;
    }

    public void setIsDialed(Boolean isDialed) {
        this.isDialed = isDialed;
    }

    public String getRecording() {
        return recording;
    }

    public void setRecording(String recording) {
        this.recording = recording;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getSipCallId() {
        return sipCallId;
    }

    public void setSipCallId(String sipCallId) {
        this.sipCallId = sipCallId;
    }

    public String getRecipientNumber() {
        return recipientNumber;
    }

    public void setRecipientNumber(String recipientNumber) {
        this.recipientNumber = recipientNumber;
    }

    public Object getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(Object recipientName) {
        this.recipientName = recipientName;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDisposition() {
        return disposition;
    }

    public void setDisposition(String disposition) {
        this.disposition = disposition;
    }

    public String getOriginationType() {
        return originationType;
    }

    public void setOriginationType(String originationType) {
        this.originationType = originationType;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getOriginallyCalledFrom() {
        return originallyCalledFrom;
    }

    public void setOriginallyCalledFrom(String originallyCalledFrom) {
        this.originallyCalledFrom = originallyCalledFrom;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Object getProvider() {
        return provider;
    }

    public void setProvider(Object provider) {
        this.provider = provider;
    }

    public Object getReceiver() {
        return receiver;
    }

    public void setReceiver(Object receiver) {
        this.receiver = receiver;
    }

    public Object getCallerName() {
        return callerName;
    }

    public void setCallerName(Object callerName) {
        this.callerName = callerName;
    }

    public List<Object> getCallNotes() {
        return callNotes;
    }

    public void setCallNotes(List<Object> callNotes) {
        this.callNotes = callNotes;
    }

    public Object getNote() {
        return note;
    }

    public void setNote(Object note) {
        this.note = note;
    }

}