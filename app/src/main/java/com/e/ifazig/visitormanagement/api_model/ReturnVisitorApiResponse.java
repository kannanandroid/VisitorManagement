package com.e.ifazig.visitormanagement.api_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ReturnVisitorApiResponse {
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

        @SerializedName("VisitorsId")
        @Expose
        private Integer visitorsId;
        @SerializedName("VisitorName")
        @Expose
        private String visitorName;
        @SerializedName("VisitorFrom")
        @Expose
        private String visitorFrom;
        @SerializedName("PhoneNo")
        @Expose
        private String phoneNo;
        @SerializedName("EmailId")
        @Expose
        private String emailId;
        @SerializedName("CompanyName")
        @Expose
        private String companyName;
        @SerializedName("IsActive")
        @Expose
        private Boolean isActive;
        @SerializedName("ImageLocation")
        @Expose
        private String imageLocation;

        public Integer getVisitorsId() {
            return visitorsId;
        }

        public void setVisitorsId(Integer visitorsId) {
            this.visitorsId = visitorsId;
        }

        public String getVisitorName() {
            return visitorName;
        }

        public void setVisitorName(String visitorName) {
            this.visitorName = visitorName;
        }

        public String getVisitorFrom() {
            return visitorFrom;
        }

        public void setVisitorFrom(String visitorFrom) {
            this.visitorFrom = visitorFrom;
        }

        public String getPhoneNo() {
            return phoneNo;
        }

        public void setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
        }

        public String getEmailId() {
            return emailId;
        }

        public void setEmailId(String emailId) {
            this.emailId = emailId;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public Boolean getIsActive() {
            return isActive;
        }

        public void setIsActive(Boolean isActive) {
            this.isActive = isActive;
        }

        public String getImageLocation() {
            return imageLocation;
        }

        public void setImageLocation(String imageLocation) {
            this.imageLocation = imageLocation;
        }

    }

}
