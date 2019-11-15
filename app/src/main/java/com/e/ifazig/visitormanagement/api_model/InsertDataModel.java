package com.e.ifazig.visitormanagement.api_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class InsertDataModel {

    @SerializedName("demoval")
    @Expose
    private String demoval;
    @SerializedName("eVisitors")
    @Expose
    private List<EVisitor> eVisitors = new ArrayList<EVisitor>();
    @SerializedName("tVisiting")
    @Expose
    private List<TVisiting> tVisiting = new ArrayList<TVisiting>();

    public List<EVisitor> getEVisitors() {
        return eVisitors;
    }

    public void setEVisitors(List<EVisitor> eVisitors) {
        this.eVisitors = eVisitors;
    }

    public List<TVisiting> getTVisiting() {
        return tVisiting;
    }

    public void setTVisiting(List<TVisiting> tVisiting) {
        this.tVisiting = tVisiting;
    }

    public String getDemoval() {
        return demoval;
    }

    public void setDemoval(String demoval) {
        this.demoval = demoval;
    }

    public static class EVisitor {

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
    public static class TVisiting {

        @SerializedName("VisitId")
        @Expose
        private Integer visitId;
        @SerializedName("UserID")
        @Expose
        private Integer UserID;
        @SerializedName("VisitorId")
        @Expose
        private Integer visitorId;
        @SerializedName("EmployeeId")
        @Expose
        private Integer employeeId;
        @SerializedName("PurposeId")
        @Expose
        private Integer purposeId;
        @SerializedName("CompanyId")
        @Expose
        private Integer companyId;
        @SerializedName("LocationId")
        @Expose
        private Integer locationId;
        @SerializedName("BuildingId")
        @Expose
        private Integer buildingId;
        @SerializedName("InTime")
        @Expose
        private String inTime;
        @SerializedName("OutTime")
        @Expose
        private String outTime;

        public Integer getVisitId() {
            return visitId;
        }

        public void setVisitId(Integer visitId) {
            this.visitId = visitId;
        }

        public Integer getVisitorId() {
            return visitorId;
        }

        public void setVisitorId(Integer visitorId) {
            this.visitorId = visitorId;
        }

        public Integer getEmployeeId() {
            return employeeId;
        }

        public void setEmployeeId(Integer employeeId) {
            this.employeeId = employeeId;
        }

        public Integer getPurposeId() {
            return purposeId;
        }

        public void setPurposeId(Integer purposeId) {
            this.purposeId = purposeId;
        }

        public Integer getCompanyId() {
            return companyId;
        }

        public void setCompanyId(Integer companyId) {
            this.companyId = companyId;
        }

        public Integer getLocationId() {
            return locationId;
        }

        public void setLocationId(Integer locationId) {
            this.locationId = locationId;
        }

        public Integer getBuildingId() {
            return buildingId;
        }

        public void setBuildingId(Integer buildingId) {
            this.buildingId = buildingId;
        }

        public String getInTime() {
            return inTime;
        }

        public void setInTime(String inTime) {
            this.inTime = inTime;
        }

        public String getOutTime() {
            return outTime;
        }

        public void setOutTime(String outTime) {
            this.outTime = outTime;
        }

        public Integer getUserID() {
            return UserID;
        }

        public void setUserID(Integer userID) {
            UserID = userID;
        }
    }
}



