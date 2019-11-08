package com.e.ifazig.visitormanagement.api_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GetSubCategoryApiResponse {
    @SerializedName("Status")
    @Expose
    private Boolean status;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("ReturnData")
    @Expose
    private List<ReturnDatum> returnData = new ArrayList<ReturnDatum>();
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

    public List<ReturnDatum> getReturnData() {
        return returnData;
    }

    public void setReturnData(List<ReturnDatum> returnData) {
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

    public class ReturnDatum {

        @SerializedName("SubCategoryID")
        @Expose
        private Integer subCategoryID;
        @SerializedName("SubCategoryName")
        @Expose
        private String subCategoryName;
        @SerializedName("SubCategoryImage")
        @Expose
        private String subCategoryImage;
        @SerializedName("Commant")
        @Expose
        private Boolean commant;

        public Integer getSubCategoryID() {
            return subCategoryID;
        }

        public void setSubCategoryID(Integer subCategoryID) {
            this.subCategoryID = subCategoryID;
        }

        public String getSubCategoryName() {
            return subCategoryName;
        }

        public void setSubCategoryName(String subCategoryName) {
            this.subCategoryName = subCategoryName;
        }

        public String getSubCategoryImage() {
            return subCategoryImage;
        }

        public void setSubCategoryImage(String subCategoryImage) {
            this.subCategoryImage = subCategoryImage;
        }

        public Boolean getCommant() {
            return commant;
        }

        public void setCommant(Boolean commant) {
            this.commant = commant;
        }
    }
}
