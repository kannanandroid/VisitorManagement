package com.e.ifazig.visitormanagement.api_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GetEmployeePurposeDetailsApiResponse {


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
    public class EmployeeDetail {

        @SerializedName("EmployeeId")
        @Expose
        private Integer employeeId;
        @SerializedName("EmployeeCode")
        @Expose
        private String employeeCode;
        @SerializedName("EmployeeName")
        @Expose
        private String employeeName;
        @SerializedName("DOJ")
        @Expose
        private String dOJ;
        @SerializedName("Desigination")
        @Expose
        private String desigination;
        @SerializedName("PhoneNo")
        @Expose
        private String phoneNo;
        @SerializedName("EmailId")
        @Expose
        private String emailId;
        @SerializedName("ImageLocation")
        @Expose
        private String imageLocation;
        @SerializedName("IsActive")
        @Expose
        private Boolean isActive;
        @SerializedName("CId")
        @Expose
        private Integer cId;
        @SerializedName("MId")
        @Expose
        private String mId;
        @SerializedName("CDate")
        @Expose
        private String cDate;
        @SerializedName("MDate")
        @Expose
        private String mDate;
        @SerializedName("CompanyId")
        @Expose
        private Integer companyId;
        @SerializedName("LocationId")
        @Expose
        private Integer locationId;
        @SerializedName("BuildingId")
        @Expose
        private Integer buildingId;

        public Integer getEmployeeId() {
            return employeeId;
        }

        public void setEmployeeId(Integer employeeId) {
            this.employeeId = employeeId;
        }

        public String getEmployeeCode() {
            return employeeCode;
        }

        public void setEmployeeCode(String employeeCode) {
            this.employeeCode = employeeCode;
        }

        public String getEmployeeName() {
            return employeeName;
        }

        public void setEmployeeName(String employeeName) {
            this.employeeName = employeeName;
        }

        public String getDOJ() {
            return dOJ;
        }

        public void setDOJ(String dOJ) {
            this.dOJ = dOJ;
        }

        public String getDesigination() {
            return desigination;
        }

        public void setDesigination(String desigination) {
            this.desigination = desigination;
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

        public String getImageLocation() {
            return imageLocation;
        }

        public void setImageLocation(String imageLocation) {
            this.imageLocation = imageLocation;
        }

        public Boolean getIsActive() {
            return isActive;
        }

        public void setIsActive(Boolean isActive) {
            this.isActive = isActive;
        }

        public Integer getCId() {
            return cId;
        }

        public void setCId(Integer cId) {
            this.cId = cId;
        }

        public String getMId() {
            return mId;
        }

        public void setMId(String mId) {
            this.mId = mId;
        }

        public String getCDate() {
            return cDate;
        }

        public void setCDate(String cDate) {
            this.cDate = cDate;
        }

        public String getMDate() {
            return mDate;
        }

        public void setMDate(String mDate) {
            this.mDate = mDate;
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

    }
    public class PurposeofVisitDetail {

        @SerializedName("PurposeId")
        @Expose
        private Integer purposeId;
        @SerializedName("PurposeName")
        @Expose
        private String purposeName;
        @SerializedName("IsActive")
        @Expose
        private Boolean isActive;
        @SerializedName("CId")
        @Expose
        private Integer cId;
        @SerializedName("MId")
        @Expose
        private String mId;
        @SerializedName("CDate")
        @Expose
        private String cDate;
        @SerializedName("MDate")
        @Expose
        private String mDate;
        @SerializedName("CompanyId")
        @Expose
        private Integer companyId;
        @SerializedName("LocationId")
        @Expose
        private Integer locationId;
        @SerializedName("BuildingId")
        @Expose
        private Integer buildingId;

        public Integer getPurposeId() {
            return purposeId;
        }

        public void setPurposeId(Integer purposeId) {
            this.purposeId = purposeId;
        }

        public String getPurposeName() {
            return purposeName;
        }

        public void setPurposeName(String purposeName) {
            this.purposeName = purposeName;
        }

        public Boolean getIsActive() {
            return isActive;
        }

        public void setIsActive(Boolean isActive) {
            this.isActive = isActive;
        }

        public Integer getCId() {
            return cId;
        }

        public void setCId(Integer cId) {
            this.cId = cId;
        }

        public String getMId() {
            return mId;
        }

        public void setMId(String mId) {
            this.mId = mId;
        }

        public String getCDate() {
            return cDate;
        }

        public void setCDate(String cDate) {
            this.cDate = cDate;
        }

        public String getMDate() {
            return mDate;
        }

        public void setMDate(String mDate) {
            this.mDate = mDate;
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

    }
    public class ReturnData {

        @SerializedName("EmployeeDetails")
        @Expose
        private List<EmployeeDetail> employeeDetails = new ArrayList<EmployeeDetail>();
        @SerializedName("PurposeofVisitDetails")
        @Expose
        private List<PurposeofVisitDetail> purposeofVisitDetails = new ArrayList<PurposeofVisitDetail>();

        public List<EmployeeDetail> getEmployeeDetails() {
            return employeeDetails;
        }

        public void setEmployeeDetails(List<EmployeeDetail> employeeDetails) {
            this.employeeDetails = employeeDetails;
        }

        public List<PurposeofVisitDetail> getPurposeofVisitDetails() {
            return purposeofVisitDetails;
        }

        public void setPurposeofVisitDetails(List<PurposeofVisitDetail> purposeofVisitDetails) {
            this.purposeofVisitDetails = purposeofVisitDetails;
        }

    }
}
