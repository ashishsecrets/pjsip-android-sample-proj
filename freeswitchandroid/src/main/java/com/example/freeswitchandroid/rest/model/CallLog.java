
package com.example.freeswitchandroid.rest.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CallLog {

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
    private Object sipCallId;
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
    @SerializedName("caller_name")
    @Expose
    private Object callerName;
    @SerializedName("note")
    @Expose
    private Object note;
    @SerializedName("call_notes")
    @Expose
    private List<Object> callNotes;

    /**
     * No args constructor for use in serialization
     *
     */
    public CallLog() {
    }

    /**
     *
     * @param note
     * @param recording
     * @param isForwardedCall
     * @param hasVoiceMail
     * @param callType
     * @param callerName
     * @param archived
     * @param dateCreated
     * @param durationSecs
     * @param carrierName
     * @param provider
     * @param originationType
     * @param recipientName
     * @param startTime
     * @param id
     * @param isDialed
     * @param direction
     * @param sipCallId
     * @param cost
     * @param receiver
     * @param businessNumber
     * @param label
     * @param capturedDigits
     * @param disposition
     * @param isMissedCall
     * @param originallyCalledFrom
     * @param callNotes
     * @param lastModified
     * @param callerId
     * @param costCurrency
     * @param user
     * @param recipientNumber
     */
    public CallLog(Integer id, Object archived, String lastModified, String dateCreated, String callerId, Integer durationSecs, String costCurrency, String cost, String startTime, Object carrierName, Object capturedDigits, Boolean isMissedCall, Boolean hasVoiceMail, Boolean isForwardedCall, Boolean isDialed, String recording, String label, Object sipCallId, String recipientNumber, Object recipientName, String direction, String disposition, String originationType, String callType, String originallyCalledFrom, BusinessNumber businessNumber, User user, Object provider, Object receiver, Object callerName, Object note, List<Object> callNotes) {
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
        this.hasVoiceMail = hasVoiceMail;
        this.isForwardedCall = isForwardedCall;
        this.isDialed = isDialed;
        this.recording = recording;
        this.label = label;
        this.sipCallId = sipCallId;
        this.recipientNumber = recipientNumber;
        this.recipientName = recipientName;
        this.direction = direction;
        this.disposition = disposition;
        this.originationType = originationType;
        this.callType = callType;
        this.originallyCalledFrom = originallyCalledFrom;
        this.businessNumber = businessNumber;
        this.user = user;
        this.provider = provider;
        this.receiver = receiver;
        this.callerName = callerName;
        this.note = note;
        this.callNotes = callNotes;
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

    public Object getSipCallId() {
        return sipCallId;
    }

    public void setSipCallId(Object sipCallId) {
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

    public Object getCallerName() {
        return callerName;
    }

    public void setCallerName(Object callerName) {
        this.callerName = callerName;
    }

    public Object getNote() {
        return note;
    }

    public void setNote(Object note) {
        this.note = note;
    }

    public List<Object> getCallNotes() {
        return callNotes;
    }

    public void setCallNotes(List<Object> callNotes) {
        this.callNotes = callNotes;
    }

}