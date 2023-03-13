package com.example.freeswitchandroid.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CallsEndDatum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("archived")
    @Expose
    private Object archived;
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
    @SerializedName("is_rejected_call")
    @Expose
    private Boolean isRejectedCall;
    @SerializedName("is_forwarded_call")
    @Expose
    private Boolean isForwardedCall;
    @SerializedName("has_voice_mail")
    @Expose
    private Boolean hasVoiceMail;
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
    private String recipientName;
    @SerializedName("business_number")
    @Expose
    private BusinessNumber businessNumber;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("provider")
    @Expose
    private Object provider;
    @SerializedName("receiver")
    @Expose
    private Object receiver;

    /**
     * No args constructor for use in serialization
     */
    public CallsEndDatum() {
    }

    /**
     * @param sipCallId
     * @param cost
     * @param receiver
     * @param recording
     * @param businessNumber
     * @param label
     * @param hasVoiceMail
     * @param archived
     * @param capturedDigits
     * @param dateCreated
     * @param isMissedCall
     * @param isRejectedCall
     * @param isForwardedCall
     * @param durationSecs
     * @param carrierName
     * @param provider
     * @param recipientName
     * @param startTime
     * @param id
     * @param lastModified
     * @param callerId
     * @param costCurrency
     * @param user
     * @param isDialed
     * @param recipientNumber
     */
    public CallsEndDatum(Integer id, Object archived, String lastModified, String dateCreated, String callerId, Integer durationSecs, String costCurrency, String cost, String startTime, Object carrierName, Object capturedDigits, Boolean isMissedCall, Boolean isForwardedCall, Boolean isRejectedCall, Boolean hasVoiceMail, Boolean isDialed, String recording, String label, String sipCallId, String recipientNumber, String recipientName, BusinessNumber businessNumber, User user, Object provider, Object receiver) {
        super();
        this.id = id;
        this.archived = archived;
        this.lastModified = lastModified;
        this.dateCreated = dateCreated;
        this.callerId = callerId;
        this.durationSecs = durationSecs;
        this.costCurrency = costCurrency;
        this.cost = cost;
        this.startTime = startTime;
        this.carrierName = carrierName;
        this.capturedDigits = capturedDigits;
        this.isMissedCall = isMissedCall;
        this.isRejectedCall = isRejectedCall;
        this.isForwardedCall = isForwardedCall;
        this.hasVoiceMail = hasVoiceMail;
        this.isDialed = isDialed;
        this.recording = recording;
        this.label = label;
        this.sipCallId = sipCallId;
        this.recipientNumber = recipientNumber;
        this.recipientName = recipientName;
        this.businessNumber = businessNumber;
        this.user = user;
        this.provider = provider;
        this.receiver = receiver;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getArchived() {
        return archived;
    }

    public void setArchived(Object archived) {
        this.archived = archived;
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
    public Boolean getIsRejectedCall() {
        return isRejectedCall;
    }
    public Boolean getIsForwardedCall() {
        return isForwardedCall;
    }

    public void setIsMissedCall(Boolean isMissedCall) {
        this.isMissedCall = isMissedCall;
    }
    public void setIsRejectedCall(Boolean isRejectedCall) {
        this.isRejectedCall = isRejectedCall;
    }
    public void setIsForwardedCall(Boolean isForwardedCall) {
        this.isForwardedCall = isForwardedCall;
    }

    public Boolean getHasVoiceMail() {
        return hasVoiceMail;
    }

    public void setHasVoiceMail(Boolean hasVoiceMail) {
        this.hasVoiceMail = hasVoiceMail;
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

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public BusinessNumber getBusinessNumber() {
        return businessNumber;
    }

    public void setBusinessNumber(BusinessNumber businessNumber) {
        this.businessNumber = businessNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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

}
