package com.example.freeswitchandroid.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BusinessNumber {

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
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("forward_calls_to_number")
    @Expose
    private Object forwardCallsToNumber;
    @SerializedName("business_name")
    @Expose
    private Object businessName;
    @SerializedName("other_number")
    @Expose
    private Object otherNumber;
    @SerializedName("provisioned")
    @Expose
    private Boolean provisioned;
    @SerializedName("caller_tunes_url")
    @Expose
    private String callerTunesUrl;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("country_code")
    @Expose
    private String countryCode;
    @SerializedName("enable_business_features")
    @Expose
    private Boolean enableBusinessFeatures;
    @SerializedName("is_primary")
    @Expose
    private Boolean isPrimary;
    @SerializedName("ring_strategy")
    @Expose
    private String ringStrategy;
    @SerializedName("verification_request_date")
    @Expose
    private Object verificationRequestDate;
    @SerializedName("verified")
    @Expose
    private Boolean verified;
    @SerializedName("verified_date")
    @Expose
    private Object verifiedDate;
    @SerializedName("expires")
    @Expose
    private String expires;
    @SerializedName("always_open")
    @Expose
    private Boolean alwaysOpen;
    @SerializedName("max_receivers")
    @Expose
    private Integer maxReceivers;
    @SerializedName("number")
    @Expose
    private Integer number;
    @SerializedName("parent_number")
    @Expose
    private Integer parentNumber;
    @SerializedName("primary_receiver")
    @Expose
    private Integer primaryReceiver;
    @SerializedName("provider")
    @Expose
    private String provider;
    @SerializedName("user")
    @Expose
    private Integer user;
    @SerializedName("wazo_tenant")
    @Expose
    private Object wazoTenant;
    @SerializedName("verified_by")
    @Expose
    private Object verifiedBy;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("receivers")
    @Expose
    private List<Receiver> receivers;
    @SerializedName("has_greetings_added")
    @Expose
    private Boolean hasGreetingsAdded;
    @SerializedName("has_office_hours_added")
    @Expose
    private Boolean hasOfficeHoursAdded;
    @SerializedName("business")
    @Expose
    private Object business;
    @SerializedName("flag_icon")
    @Expose
    private String flagIcon;
    @SerializedName("balance")
    @Expose
    private Double balance;


    /**
     * No args constructor for use in serialization
     *
     */
    public BusinessNumber() {
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Object getForwardCallsToNumber() {
        return forwardCallsToNumber;
    }

    public void setForwardCallsToNumber(Object forwardCallsToNumber) {
        this.forwardCallsToNumber = forwardCallsToNumber;
    }

    public Object getBusinessName() {
        return businessName;
    }

    public void setBusinessName(Object businessName) {
        this.businessName = businessName;
    }

    public Object getOtherNumber() {
        return otherNumber;
    }

    public void setOtherNumber(Object otherNumber) {
        this.otherNumber = otherNumber;
    }

    public Boolean getProvisioned() {
        return provisioned;
    }

    public void setProvisioned(Boolean provisioned) {
        this.provisioned = provisioned;
    }

    public String getCallerTunesUrl() {
        return callerTunesUrl;
    }

    public void setCallerTunesUrl(String callerTunesUrl) {
        this.callerTunesUrl = callerTunesUrl;
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

    public Boolean getEnableBusinessFeatures() {
        return enableBusinessFeatures;
    }

    public void setEnableBusinessFeatures(Boolean enableBusinessFeatures) {
        this.enableBusinessFeatures = enableBusinessFeatures;
    }

    public Boolean getPrimary() {
        return isPrimary;
    }

    public void setPrimary(Boolean primary) {
        isPrimary = primary;
    }

    public String getRingStrategy() {
        return ringStrategy;
    }

    public void setRingStrategy(String ringStrategy) {
        this.ringStrategy = ringStrategy;
    }

    public Object getVerificationRequestDate() {
        return verificationRequestDate;
    }

    public void setVerificationRequestDate(Object verificationRequestDate) {
        this.verificationRequestDate = verificationRequestDate;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public Object getVerifiedDate() {
        return verifiedDate;
    }

    public void setVerifiedDate(Object verifiedDate) {
        this.verifiedDate = verifiedDate;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public Boolean getAlwaysOpen() {
        return alwaysOpen;
    }

    public void setAlwaysOpen(Boolean alwaysOpen) {
        this.alwaysOpen = alwaysOpen;
    }

    public Integer getMaxReceivers() {
        return maxReceivers;
    }

    public void setMaxReceivers(Integer maxReceivers) {
        this.maxReceivers = maxReceivers;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getParentNumber() {
        return parentNumber;
    }

    public void setParentNumber(Integer parentNumber) {
        this.parentNumber = parentNumber;
    }

    public Integer getPrimaryReceiver() {
        return primaryReceiver;
    }

    public void setPrimaryReceiver(Integer primaryReceiver) {
        this.primaryReceiver = primaryReceiver;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Object getWazoTenant() {
        return wazoTenant;
    }

    public void setWazoTenant(Object wazoTenant) {
        this.wazoTenant = wazoTenant;
    }

    public Object getVerifiedBy() {
        return verifiedBy;
    }

    public void setVerifiedBy(Object verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Receiver> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<Receiver> receivers) {
        this.receivers = receivers;
    }

    public Boolean getHasGreetingsAdded() {
        return hasGreetingsAdded;
    }

    public void setHasGreetingsAdded(Boolean hasGreetingsAdded) {
        this.hasGreetingsAdded = hasGreetingsAdded;
    }

    public Boolean getHasOfficeHoursAdded() {
        return hasOfficeHoursAdded;
    }

    public void setHasOfficeHoursAdded(Boolean hasOfficeHoursAdded) {
        this.hasOfficeHoursAdded = hasOfficeHoursAdded;
    }

    public Object getBusiness() {
        return business;
    }

    public void setBusiness(Object business) {
        this.business = business;
    }

    public String getFlagIcon() {
        return flagIcon;
    }

    public void setFlagIcon(String flagIcon) {
        this.flagIcon = flagIcon;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    /**
     *
     * @param country
     * @param verificationRequestDate
     * @param expires
     * @param wazoTenant
     * @param businessName
     * @param archived
     * @param number
     * @param dateCreated
     * @param provisioned
     * @param verifiedDate
     * @param provider
     * @param countryCode
     * @param isPrimary
     * @param id
     * @param otherNumber
     * @param verified
     * @param ringStrategy
     * @param label
     * @param enableBusinessFeatures
     * @param verifiedBy
     * @param primaryReceiver
     * @param phoneNumber
     * @param parentNumber
     * @param alwaysOpen
     * @param lastModified
     * @param maxReceivers
     * @param forwardCallsToNumber
     * @param user
     * @param callerTunesUrl
     * @param country
     * @param expires
     * @param businessName
     * @param archived
     * @param number
     * @param dateCreated
     * @param provisioned
     * @param receivers
     * @param countryCode
     * @param isPrimary
     * @param id
     * @param otherNumber
     * @param verified
     * @param ringStrategy
     * @param label
     * @param enableBusinessFeatures
     * @param primaryReceiver
     * @param phoneNumber
     * @param parentNumber
     * @param alwaysOpen
     * @param lastModified
     * @param maxReceivers
     * @param forwardCallsToNumber
     * @param user
     * @param callerTunesUrl
     */
    public BusinessNumber(Integer id, Integer userId, List<Receiver> receivers, Object archived, String lastModified, String dateCreated, String label, String phoneNumber, Object forwardCallsToNumber, Object businessName, Object otherNumber, Boolean provisioned, String callerTunesUrl, String country, String countryCode, Boolean enableBusinessFeatures, Boolean isPrimary, Boolean hasGreetingsAdded, Boolean hasOfficeHoursAdded, String ringStrategy, Object verificationRequestDate, Boolean verified, Object verifiedDate, String expires, Boolean alwaysOpen, Integer maxReceivers, Integer number, Integer parentNumber, Integer primaryReceiver, String provider, Integer user, Object wazoTenant, Object verifiedBy, Object business, String flagIcon, Double balance) {
        super();
        this.id = id;
        this.archived = archived;
        this.forwardCallsToNumber = forwardCallsToNumber;
        this.businessName = businessName;
        this.otherNumber = otherNumber;
        this.provisioned = provisioned;
        this.enableBusinessFeatures = enableBusinessFeatures;
        this.isPrimary = isPrimary;
        this.verificationRequestDate = verificationRequestDate;
        this.verified = verified;
        this.verifiedDate = verifiedDate;
        this.alwaysOpen = alwaysOpen;
        this.maxReceivers = maxReceivers;
        this.number = number;
        this.parentNumber = parentNumber;
        this.primaryReceiver = primaryReceiver;
        this.provider = provider;
        this.user = user;
        this.wazoTenant = wazoTenant;
        this.verifiedBy = verifiedBy;
        this.userId = userId;
        this.receivers = receivers;
        this.lastModified = lastModified;
        this.dateCreated = dateCreated;
        this.label = label;
        this.phoneNumber = phoneNumber;
        this.callerTunesUrl = callerTunesUrl;
        this.country = country;
        this.countryCode = countryCode;
        this.hasGreetingsAdded = hasGreetingsAdded;
        this.hasOfficeHoursAdded = hasOfficeHoursAdded;
        this.ringStrategy = ringStrategy;
        this.expires = expires;
        this.business = business;
        this.flagIcon = flagIcon;
        this.balance = balance;
    }

}

