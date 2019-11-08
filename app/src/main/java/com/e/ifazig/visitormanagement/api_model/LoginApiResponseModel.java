package com.e.ifazig.visitormanagement.api_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginApiResponseModel {
    @SerializedName("Status")
    @Expose
    private Boolean status;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("ReturnData")
    @Expose
    private ReturnData returnData;
    @SerializedName("Method")
    @Expose
    private String method;
    @SerializedName("SessionID")
    @Expose
    private String sessionID;
    @SerializedName("ID")
    @Expose
    private String iD;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ReturnData getReturnData() {
        return returnData;
    }

    public void setReturnData(ReturnData returnData) {
        this.returnData = returnData;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }
    public class ReturnData {

        @SerializedName("FromFramework")
        @Expose
        private Boolean fromFramework;
        @SerializedName("CountryID")
        @Expose
        private Integer countryID;
        @SerializedName("CountryIDCurrent")
        @Expose
        private Integer countryIDCurrent;
        @SerializedName("RoleID")
        @Expose
        private Integer roleID;
        @SerializedName("UserID")
        @Expose
        private Integer userID;
        @SerializedName("CompanyIDUser")
        @Expose
        private Integer companyIDUser;
        @SerializedName("LocationIDUser")
        @Expose
        private Integer locationIDUser;
        @SerializedName("CompanyName")
        @Expose
        private String companyName;
        @SerializedName("CompanyIDCurrent")
        @Expose
        private Integer companyIDCurrent;
        @SerializedName("LocationName")
        @Expose
        private String locationName;
        @SerializedName("LocationIDCurrent")
        @Expose
        private Integer locationIDCurrent;
        @SerializedName("GroupID")
        @Expose
        private Integer groupID;
        @SerializedName("GroupIDCurrent")
        @Expose
        private Integer groupIDCurrent;
        @SerializedName("LanguageID")
        @Expose
        private Integer languageID;
        @SerializedName("UserFirstName")
        @Expose
        private String userFirstName;
        @SerializedName("UserLastName")
        @Expose
        private String userLastName;
        @SerializedName("ThemeFolderPath")
        @Expose
        private String themeFolderPath;
        @SerializedName("CompLogo")
        @Expose
        private String compLogo;
        @SerializedName("Announcement")
        @Expose
        private String announcement;

        public Boolean getFromFramework() {
            return fromFramework;
        }

        public void setFromFramework(Boolean fromFramework) {
            this.fromFramework = fromFramework;
        }

        public Integer getCountryID() {
            return countryID;
        }

        public void setCountryID(Integer countryID) {
            this.countryID = countryID;
        }

        public Integer getCountryIDCurrent() {
            return countryIDCurrent;
        }

        public void setCountryIDCurrent(Integer countryIDCurrent) {
            this.countryIDCurrent = countryIDCurrent;
        }

        public Integer getRoleID() {
            return roleID;
        }

        public void setRoleID(Integer roleID) {
            this.roleID = roleID;
        }

        public Integer getUserID() {
            return userID;
        }

        public void setUserID(Integer userID) {
            this.userID = userID;
        }

        public Integer getCompanyIDUser() {
            return companyIDUser;
        }

        public void setCompanyIDUser(Integer companyIDUser) {
            this.companyIDUser = companyIDUser;
        }

        public Integer getLocationIDUser() {
            return locationIDUser;
        }

        public void setLocationIDUser(Integer locationIDUser) {
            this.locationIDUser = locationIDUser;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public Integer getCompanyIDCurrent() {
            return companyIDCurrent;
        }

        public void setCompanyIDCurrent(Integer companyIDCurrent) {
            this.companyIDCurrent = companyIDCurrent;
        }

        public String getLocationName() {
            return locationName;
        }

        public void setLocationName(String locationName) {
            this.locationName = locationName;
        }

        public Integer getLocationIDCurrent() {
            return locationIDCurrent;
        }

        public void setLocationIDCurrent(Integer locationIDCurrent) {
            this.locationIDCurrent = locationIDCurrent;
        }

        public Integer getGroupID() {
            return groupID;
        }

        public void setGroupID(Integer groupID) {
            this.groupID = groupID;
        }

        public Integer getGroupIDCurrent() {
            return groupIDCurrent;
        }

        public void setGroupIDCurrent(Integer groupIDCurrent) {
            this.groupIDCurrent = groupIDCurrent;
        }

        public Integer getLanguageID() {
            return languageID;
        }

        public void setLanguageID(Integer languageID) {
            this.languageID = languageID;
        }

        public String getUserFirstName() {
            return userFirstName;
        }

        public void setUserFirstName(String userFirstName) {
            this.userFirstName = userFirstName;
        }

        public String getUserLastName() {
            return userLastName;
        }

        public void setUserLastName(String userLastName) {
            this.userLastName = userLastName;
        }

        public String getThemeFolderPath() {
            return themeFolderPath;
        }

        public void setThemeFolderPath(String themeFolderPath) {
            this.themeFolderPath = themeFolderPath;
        }

        public String getCompLogo() {
            return compLogo;
        }

        public void setCompLogo(String compLogo) {
            this.compLogo = compLogo;
        }

        public String getAnnouncement() {
            return announcement;
        }

        public void setAnnouncement(String announcement) {
            this.announcement = announcement;
        }

    }
 }
