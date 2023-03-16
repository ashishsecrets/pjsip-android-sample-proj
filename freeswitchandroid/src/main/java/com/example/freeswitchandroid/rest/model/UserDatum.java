package com.example.freeswitchandroid.rest.model;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


    public class UserDatum {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("business_numbers")
        @Expose
        private List<BusinessNumber> businessNumbers;
        @SerializedName("last_login")
        @Expose
        private Object lastLogin;
        @SerializedName("is_superuser")
        @Expose
        private Boolean isSuperuser;
        @SerializedName("username")
        @Expose
        private String username;
        @SerializedName("first_name")
        @Expose
        private String firstName;
        @SerializedName("last_name")
        @Expose
        private String lastName;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("is_staff")
        @Expose
        private Boolean isStaff;
        @SerializedName("is_active")
        @Expose
        private Boolean isActive;
        @SerializedName("date_joined")
        @Expose
        private String dateJoined;
        @SerializedName("balance_currency")
        @Expose
        private String balanceCurrency;
        @SerializedName("balance")
        @Expose
        private String balance;
        @SerializedName("promo_balance_currency")
        @Expose
        private String promoBalanceCurrency;
        @SerializedName("promo_balance")
        @Expose
        private String promoBalance;
        @SerializedName("job_title")
        @Expose
        private String jobTitle;
        @SerializedName("home_address")
        @Expose
        private Object homeAddress;
        @SerializedName("mobile")
        @Expose
        private String mobile;
        @SerializedName("mobile2")
        @Expose
        private Object mobile2;
        @SerializedName("personal_email")
        @Expose
        private String personalEmail;
        @SerializedName("personal_identification_front")
        @Expose
        private Object personalIdentificationFront;
        @SerializedName("personal_identification_back")
        @Expose
        private Object personalIdentificationBack;
        @SerializedName("government_id_number")
        @Expose
        private Object governmentIdNumber;
        @SerializedName("profile_picture")
        @Expose
        private Object profilePicture;
        @SerializedName("live_picture")
        @Expose
        private Object livePicture;
        @SerializedName("employment_status")
        @Expose
        private Object employmentStatus;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("country_code")
        @Expose
        private String countryCode;
        @SerializedName("state")
        @Expose
        private String state;
        @SerializedName("receive_notifications")
        @Expose
        private Boolean receiveNotifications;
        @SerializedName("voip_token")
        @Expose
        private String voipToken;
        @SerializedName("invitation_code")
        @Expose
        private String invitationCode;
        @SerializedName("verification_request_date")
        @Expose
        private Object verificationRequestDate;
        @SerializedName("verified")
        @Expose
        private Boolean verified;
        @SerializedName("verified_date")
        @Expose
        private Object verifiedDate;
        @SerializedName("birth_date")
        @Expose
        private String birthDate;
        @SerializedName("last_modified")
        @Expose
        private String lastModified;
        @SerializedName("mobile_verified")
        @Expose
        private Boolean mobileVerified;
        @SerializedName("email_verified")
        @Expose
        private Boolean emailVerified;
        @SerializedName("for_personal_use")
        @Expose
        private Boolean forPersonalUse;
        @SerializedName("for_business_use")
        @Expose
        private Boolean forBusinessUse;
        @SerializedName("next_two_factor_verification")
        @Expose
        private Object nextTwoFactorVerification;
        @SerializedName("has_usable_pin")
        @Expose
        private Boolean hasUsablePin;
        @SerializedName("has_completed_signup")
        @Expose
        private Boolean hasCompletedSignup;
        @SerializedName("has_completed_number_flow")
        @Expose
        private Boolean hasCompletedNumberFlow;
        @SerializedName("has_topped_up")
        @Expose
        private Boolean hasToppedUp;
        @SerializedName("date_modified")
        @Expose
        private String dateModified;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("groups")
        @Expose
        private List<Object> groups;
        @SerializedName("user_permissions")
        @Expose
        private List<Object> userPermissions;
        @SerializedName("business")
        @Expose
        private List<Business> business;
        @SerializedName("number_of_invites")
        @Expose
        private Integer numberOfInvites;
        @SerializedName("receivers")
        @Expose
        private List<Receiver> receivers;

        /**
         * No args constructor for use in serialization
         *
         */
        public UserDatum() {
        }

        /**
         *
         * @param businessNumbers
         * @param id
         * @param lastLogin
         * @param verificationRequestDate
         * @param country
         * @param dateJoined
         * @param jobTitle
         * @param mobileVerified
         * @param hasCompletedNumberFlow
         * @param userPermissions
         * @param isActive
         * @param employmentStatus
         * @param personalEmail
         * @param balance
         * @param verifiedDate
         * @param state
         * @param invitationCode
         * @param homeAddress
         * @param forBusinessUse
         * @param isStaff
         * @param hasToppedUp
         * @param numberOfInvites
         * @param nextTwoFactorVerification
         * @param firstName
         * @param profilePicture
         * @param livePicture
         * @param voipToken
         * @param lastModified
         * @param lastName
         * @param gender
         * @param isSuperuser
         * @param hasUsablePin
         * @param hasCompletedSignup
         * @param receivers
         * @param countryCode
         * @param mobile2
         * @param personalIdentificationBack
         * @param governmentIdNumber
         * @param email
         * @param promoBalance
         * @param receiveNotifications
         * @param business
         * @param mobile
         * @param verified
         * @param personalIdentificationFront
         * @param groups
         * @param dateModified
         * @param birthDate
         * @param emailVerified
         * @param forPersonalUse
         * @param balanceCurrency
         * @param promoBalanceCurrency
         * @param username
         */
        public UserDatum(Integer id, List<BusinessNumber> businessNumbers, Object lastLogin, Boolean isSuperuser, String username, String firstName, String lastName, String email, Boolean isStaff, Boolean isActive, String dateJoined, String balanceCurrency, String balance, String promoBalanceCurrency, String promoBalance, String jobTitle, Object homeAddress, String mobile, Object mobile2, String personalEmail, Object personalIdentificationFront, Object personalIdentificationBack, Object governmentIdNumber, Object profilePicture, Object livePicture, Object employmentStatus, String gender, String countryCode, String state, Boolean receiveNotifications, String voipToken, String invitationCode, Object verificationRequestDate, Boolean verified, Object verifiedDate, String birthDate, String lastModified, Boolean mobileVerified, Boolean emailVerified, Boolean forPersonalUse, Boolean forBusinessUse, Object nextTwoFactorVerification, Boolean hasUsablePin, Boolean hasCompletedSignup, Boolean hasCompletedNumberFlow, Boolean hasToppedUp, String dateModified, String country, List<Object> groups, List<Object> userPermissions, List<Business> business, Integer numberOfInvites, List<Receiver> receivers) {
            super();
            this.id = id;
            this.businessNumbers = businessNumbers;
            this.lastLogin = lastLogin;
            this.isSuperuser = isSuperuser;
            this.username = username;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.isStaff = isStaff;
            this.isActive = isActive;
            this.dateJoined = dateJoined;
            this.balanceCurrency = balanceCurrency;
            this.balance = balance;
            this.promoBalanceCurrency = promoBalanceCurrency;
            this.promoBalance = promoBalance;
            this.jobTitle = jobTitle;
            this.homeAddress = homeAddress;
            this.mobile = mobile;
            this.mobile2 = mobile2;
            this.personalEmail = personalEmail;
            this.personalIdentificationFront = personalIdentificationFront;
            this.personalIdentificationBack = personalIdentificationBack;
            this.governmentIdNumber = governmentIdNumber;
            this.profilePicture = profilePicture;
            this.livePicture = livePicture;
            this.employmentStatus = employmentStatus;
            this.gender = gender;
            this.countryCode = countryCode;
            this.state = state;
            this.receiveNotifications = receiveNotifications;
            this.voipToken = voipToken;
            this.invitationCode = invitationCode;
            this.verificationRequestDate = verificationRequestDate;
            this.verified = verified;
            this.verifiedDate = verifiedDate;
            this.birthDate = birthDate;
            this.lastModified = lastModified;
            this.mobileVerified = mobileVerified;
            this.emailVerified = emailVerified;
            this.forPersonalUse = forPersonalUse;
            this.forBusinessUse = forBusinessUse;
            this.nextTwoFactorVerification = nextTwoFactorVerification;
            this.hasUsablePin = hasUsablePin;
            this.hasCompletedSignup = hasCompletedSignup;
            this.hasCompletedNumberFlow = hasCompletedNumberFlow;
            this.hasToppedUp = hasToppedUp;
            this.dateModified = dateModified;
            this.country = country;
            this.groups = groups;
            this.userPermissions = userPermissions;
            this.business = business;
            this.numberOfInvites = numberOfInvites;
            this.receivers = receivers;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public List<BusinessNumber> getBusinessNumbers() {
            return businessNumbers;
        }

        public void setBusinessNumbers(List<BusinessNumber> businessNumbers) {
            this.businessNumbers = businessNumbers;
        }

        public Object getLastLogin() {
            return lastLogin;
        }

        public void setLastLogin(Object lastLogin) {
            this.lastLogin = lastLogin;
        }

        public Boolean getIsSuperuser() {
            return isSuperuser;
        }

        public void setIsSuperuser(Boolean isSuperuser) {
            this.isSuperuser = isSuperuser;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Boolean getIsStaff() {
            return isStaff;
        }

        public void setIsStaff(Boolean isStaff) {
            this.isStaff = isStaff;
        }

        public Boolean getIsActive() {
            return isActive;
        }

        public void setIsActive(Boolean isActive) {
            this.isActive = isActive;
        }

        public String getDateJoined() {
            return dateJoined;
        }

        public void setDateJoined(String dateJoined) {
            this.dateJoined = dateJoined;
        }

        public String getBalanceCurrency() {
            return balanceCurrency;
        }

        public void setBalanceCurrency(String balanceCurrency) {
            this.balanceCurrency = balanceCurrency;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getPromoBalanceCurrency() {
            return promoBalanceCurrency;
        }

        public void setPromoBalanceCurrency(String promoBalanceCurrency) {
            this.promoBalanceCurrency = promoBalanceCurrency;
        }

        public String getPromoBalance() {
            return promoBalance;
        }

        public void setPromoBalance(String promoBalance) {
            this.promoBalance = promoBalance;
        }

        public String getJobTitle() {
            return jobTitle;
        }

        public void setJobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
        }

        public Object getHomeAddress() {
            return homeAddress;
        }

        public void setHomeAddress(Object homeAddress) {
            this.homeAddress = homeAddress;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public Object getMobile2() {
            return mobile2;
        }

        public void setMobile2(Object mobile2) {
            this.mobile2 = mobile2;
        }

        public String getPersonalEmail() {
            return personalEmail;
        }

        public void setPersonalEmail(String personalEmail) {
            this.personalEmail = personalEmail;
        }

        public Object getPersonalIdentificationFront() {
            return personalIdentificationFront;
        }

        public void setPersonalIdentificationFront(Object personalIdentificationFront) {
            this.personalIdentificationFront = personalIdentificationFront;
        }

        public Object getPersonalIdentificationBack() {
            return personalIdentificationBack;
        }

        public void setPersonalIdentificationBack(Object personalIdentificationBack) {
            this.personalIdentificationBack = personalIdentificationBack;
        }

        public Object getGovernmentIdNumber() {
            return governmentIdNumber;
        }

        public void setGovernmentIdNumber(Object governmentIdNumber) {
            this.governmentIdNumber = governmentIdNumber;
        }

        public Object getProfilePicture() {
            return profilePicture;
        }

        public void setProfilePicture(Object profilePicture) {
            this.profilePicture = profilePicture;
        }

        public Object getLivePicture() {
            return livePicture;
        }

        public void setLivePicture(Object livePicture) {
            this.livePicture = livePicture;
        }

        public Object getEmploymentStatus() {
            return employmentStatus;
        }

        public void setEmploymentStatus(Object employmentStatus) {
            this.employmentStatus = employmentStatus;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public Boolean getReceiveNotifications() {
            return receiveNotifications;
        }

        public void setReceiveNotifications(Boolean receiveNotifications) {
            this.receiveNotifications = receiveNotifications;
        }

        public String getVoipToken() {
            return voipToken;
        }

        public void setVoipToken(String voipToken) {
            this.voipToken = voipToken;
        }

        public String getInvitationCode() {
            return invitationCode;
        }

        public void setInvitationCode(String invitationCode) {
            this.invitationCode = invitationCode;
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

        public String getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(String birthDate) {
            this.birthDate = birthDate;
        }

        public String getLastModified() {
            return lastModified;
        }

        public void setLastModified(String lastModified) {
            this.lastModified = lastModified;
        }

        public Boolean getMobileVerified() {
            return mobileVerified;
        }

        public void setMobileVerified(Boolean mobileVerified) {
            this.mobileVerified = mobileVerified;
        }

        public Boolean getEmailVerified() {
            return emailVerified;
        }

        public void setEmailVerified(Boolean emailVerified) {
            this.emailVerified = emailVerified;
        }

        public Boolean getForPersonalUse() {
            return forPersonalUse;
        }

        public void setForPersonalUse(Boolean forPersonalUse) {
            this.forPersonalUse = forPersonalUse;
        }

        public Boolean getForBusinessUse() {
            return forBusinessUse;
        }

        public void setForBusinessUse(Boolean forBusinessUse) {
            this.forBusinessUse = forBusinessUse;
        }

        public Object getNextTwoFactorVerification() {
            return nextTwoFactorVerification;
        }

        public void setNextTwoFactorVerification(Object nextTwoFactorVerification) {
            this.nextTwoFactorVerification = nextTwoFactorVerification;
        }

        public Boolean getHasUsablePin() {
            return hasUsablePin;
        }

        public void setHasUsablePin(Boolean hasUsablePin) {
            this.hasUsablePin = hasUsablePin;
        }

        public Boolean getHasCompletedSignup() {
            return hasCompletedSignup;
        }

        public void setHasCompletedSignup(Boolean hasCompletedSignup) {
            this.hasCompletedSignup = hasCompletedSignup;
        }

        public Boolean getHasCompletedNumberFlow() {
            return hasCompletedNumberFlow;
        }

        public void setHasCompletedNumberFlow(Boolean hasCompletedNumberFlow) {
            this.hasCompletedNumberFlow = hasCompletedNumberFlow;
        }

        public Boolean getHasToppedUp() {
            return hasToppedUp;
        }

        public void setHasToppedUp(Boolean hasToppedUp) {
            this.hasToppedUp = hasToppedUp;
        }

        public String getDateModified() {
            return dateModified;
        }

        public void setDateModified(String dateModified) {
            this.dateModified = dateModified;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public List<Object> getGroups() {
            return groups;
        }

        public void setGroups(List<Object> groups) {
            this.groups = groups;
        }

        public List<Object> getUserPermissions() {
            return userPermissions;
        }

        public void setUserPermissions(List<Object> userPermissions) {
            this.userPermissions = userPermissions;
        }

        public List<Business> getBusiness() {
            return business;
        }

        public void setBusiness(List<Business> business) {
            this.business = business;
        }

        public Integer getNumberOfInvites() {
            return numberOfInvites;
        }

        public void setNumberOfInvites(Integer numberOfInvites) {
            this.numberOfInvites = numberOfInvites;
        }

        public List<Receiver> getReceivers() {
            return receivers;
        }

        public void setReceivers(List<Receiver> receivers) {
            this.receivers = receivers;
        }

    }

