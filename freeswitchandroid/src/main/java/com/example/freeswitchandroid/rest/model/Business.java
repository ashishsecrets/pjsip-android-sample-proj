package com.example.freeswitchandroid.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Business {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("last_modified")
    @Expose
    private String lastModified;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("job_title")
    @Expose
    private String jobTitle;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("instagram")
    @Expose
    private String instagram;
    @SerializedName("twitter")
    @Expose
    private String twitter;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("reg_number")
    @Expose
    private Object regNumber;
    @SerializedName("start_date")
    @Expose
    private Object startDate;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("whatsapp_no")
    @Expose
    private Object whatsappNo;
    @SerializedName("sector")
    @Expose
    private String sector;
    @SerializedName("no_of_employees")
    @Expose
    private String noOfEmployees;
    @SerializedName("years_in_business")
    @Expose
    private String yearsInBusiness;
    @SerializedName("business_hours_always_open")
    @Expose
    private Boolean businessHoursAlwaysOpen;
    @SerializedName("business_timezone")
    @Expose
    private String businessTimezone;
    @SerializedName("verified")
    @Expose
    private Boolean verified;
    @SerializedName("owner")
    @Expose
    private Integer owner;
    @SerializedName("number")
    @Expose
    private Integer number;

    /**
     * No args constructor for use in serialization
     *
     */
    public Business() {
    }

    /**
     *
     * @param owner
     * @param website
     * @param address
     * @param jobTitle
     * @param verified
     * @param instagram
     * @param whatsappNo
     * @param businessHoursAlwaysOpen
     * @param yearsInBusiness
     * @param number
     * @param twitter
     * @param regNumber
     * @param dateCreated
     * @param name
     * @param noOfEmployees
     * @param id
     * @param lastModified
     * @param businessTimezone
     * @param sector
     * @param startDate
     */
    public Business(Integer id, String lastModified, String dateCreated, String jobTitle, String website, String instagram, String twitter, String name, Object regNumber, Object startDate, String address, Object whatsappNo, String sector, String noOfEmployees, String yearsInBusiness, Boolean businessHoursAlwaysOpen, String businessTimezone, Boolean verified, Integer owner, Integer number) {
        super();
        this.id = id;
        this.lastModified = lastModified;
        this.dateCreated = dateCreated;
        this.jobTitle = jobTitle;
        this.website = website;
        this.instagram = instagram;
        this.twitter = twitter;
        this.name = name;
        this.regNumber = regNumber;
        this.startDate = startDate;
        this.address = address;
        this.whatsappNo = whatsappNo;
        this.sector = sector;
        this.noOfEmployees = noOfEmployees;
        this.yearsInBusiness = yearsInBusiness;
        this.businessHoursAlwaysOpen = businessHoursAlwaysOpen;
        this.businessTimezone = businessTimezone;
        this.verified = verified;
        this.owner = owner;
        this.number = number;
    }

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

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(Object regNumber) {
        this.regNumber = regNumber;
    }

    public Object getStartDate() {
        return startDate;
    }

    public void setStartDate(Object startDate) {
        this.startDate = startDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Object getWhatsappNo() {
        return whatsappNo;
    }

    public void setWhatsappNo(Object whatsappNo) {
        this.whatsappNo = whatsappNo;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getNoOfEmployees() {
        return noOfEmployees;
    }

    public void setNoOfEmployees(String noOfEmployees) {
        this.noOfEmployees = noOfEmployees;
    }

    public String getYearsInBusiness() {
        return yearsInBusiness;
    }

    public void setYearsInBusiness(String yearsInBusiness) {
        this.yearsInBusiness = yearsInBusiness;
    }

    public Boolean getBusinessHoursAlwaysOpen() {
        return businessHoursAlwaysOpen;
    }

    public void setBusinessHoursAlwaysOpen(Boolean businessHoursAlwaysOpen) {
        this.businessHoursAlwaysOpen = businessHoursAlwaysOpen;
    }

    public String getBusinessTimezone() {
        return businessTimezone;
    }

    public void setBusinessTimezone(String businessTimezone) {
        this.businessTimezone = businessTimezone;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

}