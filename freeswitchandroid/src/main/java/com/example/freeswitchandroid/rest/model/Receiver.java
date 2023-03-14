package com.example.freeswitchandroid.rest.model;
import android.provider.CallLog;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Receiver {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("archived")
    @Expose
    private Object archived;
    @SerializedName("last_modified")
    @Expose
    private String lastModified;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("receiver_name")
    @Expose
    private String receiverName;
    @SerializedName("display_caller_id")
    @Expose
    private Boolean displayCallerId;
    @SerializedName("receiver_email")
    @Expose
    private String receiverEmail;
    @SerializedName("is_default")
    @Expose
    private Boolean isDefault;
    @SerializedName("is_primary_receiver")
    @Expose
    private Boolean isPrimaryReceiver;
    @SerializedName("receiver_role")
    @Expose
    private String receiverRole;
    @SerializedName("ring_group_position")
    @Expose
    private Integer ringGroupPosition;
    @SerializedName("can_see_all_call_logs")
    @Expose
    private Boolean canSeeAllCallLogs;
    @SerializedName("can_access_call_recordings")
    @Expose
    private Boolean canAccessCallRecordings;
    @SerializedName("can_invite_users")
    @Expose
    private Boolean canInviteUsers;
    @SerializedName("can_process_billings")
    @Expose
    private Boolean canProcessBillings;
    @SerializedName("can_modify_office_hours")
    @Expose
    private Boolean canModifyOfficeHours;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("country_code")
    @Expose
    private String countryCode;
    @SerializedName("date_invited")
    @Expose
    private String dateInvited;
    @SerializedName("business_number")
    @Expose
    private BusinessNumber businessNumber;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("balance")
    @Expose
    private Double balance;
    @SerializedName("call_logs")
    @Expose
    private List<CallLog> callLogs;
    @SerializedName("line")
    @Expose
    private Line line;

    /**
     * No args constructor for use in serialization
     *
     */
    public Receiver() {
    }

    /**
     *
     * @param country
     * @param isPrimaryReceiver
     * @param line
     * @param canAccessCallRecordings
     * @param archived
     * @param dateCreated
     * @param balance
     * @param countryCode
     * @param canProcessBillings
     * @param id
     * @param callLogs
     * @param dateInvited
     * @param receiverName
     * @param displayCallerId
     * @param businessNumber
     * @param userId
     * @param canSeeAllCallLogs
     * @param canModifyOfficeHours
     * @param isDefault
     * @param phoneNumber
     * @param canInviteUsers
     * @param lastModified
     * @param receiverRole
     * @param user
     * @param receiverEmail
     * @param ringGroupPosition
     */
    public Receiver(Integer id, Integer userId, Object archived, String lastModified, String dateCreated, String phoneNumber, String receiverName, Boolean displayCallerId, String receiverEmail, Boolean isDefault, Boolean isPrimaryReceiver, String receiverRole, Integer ringGroupPosition, Boolean canSeeAllCallLogs, Boolean canAccessCallRecordings, Boolean canInviteUsers, Boolean canProcessBillings, Boolean canModifyOfficeHours, String country, String countryCode, String dateInvited, BusinessNumber businessNumber, User user, Double balance, List<CallLog> callLogs, Line line) {
        super();
        this.id = id;
        this.userId = userId;
        this.archived = archived;
        this.lastModified = lastModified;
        this.dateCreated = dateCreated;
        this.phoneNumber = phoneNumber;
        this.receiverName = receiverName;
        this.displayCallerId = displayCallerId;
        this.receiverEmail = receiverEmail;
        this.isDefault = isDefault;
        this.isPrimaryReceiver = isPrimaryReceiver;
        this.receiverRole = receiverRole;
        this.ringGroupPosition = ringGroupPosition;
        this.canSeeAllCallLogs = canSeeAllCallLogs;
        this.canAccessCallRecordings = canAccessCallRecordings;
        this.canInviteUsers = canInviteUsers;
        this.canProcessBillings = canProcessBillings;
        this.canModifyOfficeHours = canModifyOfficeHours;
        this.country = country;
        this.countryCode = countryCode;
        this.dateInvited = dateInvited;
        this.businessNumber = businessNumber;
        this.user = user;
        this.balance = balance;
        this.callLogs = callLogs;
        this.line = line;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public Boolean getDisplayCallerId() {
        return displayCallerId;
    }

    public void setDisplayCallerId(Boolean displayCallerId) {
        this.displayCallerId = displayCallerId;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Boolean getIsPrimaryReceiver() {
        return isPrimaryReceiver;
    }

    public void setIsPrimaryReceiver(Boolean isPrimaryReceiver) {
        this.isPrimaryReceiver = isPrimaryReceiver;
    }

    public String getReceiverRole() {
        return receiverRole;
    }

    public void setReceiverRole(String receiverRole) {
        this.receiverRole = receiverRole;
    }

    public Integer getRingGroupPosition() {
        return ringGroupPosition;
    }

    public void setRingGroupPosition(Integer ringGroupPosition) {
        this.ringGroupPosition = ringGroupPosition;
    }

    public Boolean getCanSeeAllCallLogs() {
        return canSeeAllCallLogs;
    }

    public void setCanSeeAllCallLogs(Boolean canSeeAllCallLogs) {
        this.canSeeAllCallLogs = canSeeAllCallLogs;
    }

    public Boolean getCanAccessCallRecordings() {
        return canAccessCallRecordings;
    }

    public void setCanAccessCallRecordings(Boolean canAccessCallRecordings) {
        this.canAccessCallRecordings = canAccessCallRecordings;
    }

    public Boolean getCanInviteUsers() {
        return canInviteUsers;
    }

    public void setCanInviteUsers(Boolean canInviteUsers) {
        this.canInviteUsers = canInviteUsers;
    }

    public Boolean getCanProcessBillings() {
        return canProcessBillings;
    }

    public void setCanProcessBillings(Boolean canProcessBillings) {
        this.canProcessBillings = canProcessBillings;
    }

    public Boolean getCanModifyOfficeHours() {
        return canModifyOfficeHours;
    }

    public void setCanModifyOfficeHours(Boolean canModifyOfficeHours) {
        this.canModifyOfficeHours = canModifyOfficeHours;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getDateInvited() {
        return dateInvited;
    }

    public void setDateInvited(String dateInvited) {
        this.dateInvited = dateInvited;
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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<CallLog> getCallLogs() {
        return callLogs;
    }

    public void setCallLogs(List<CallLog> callLogs) {
        this.callLogs = callLogs;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

}