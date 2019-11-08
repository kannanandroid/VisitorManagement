package com.e.ifazig.visitormanagement.api_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GetPriorityApiResponse {
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

        @SerializedName("PriorityName")
        @Expose
        private String priorityName;
        @SerializedName("PriorityDescription")
        @Expose
        private String priorityDescription;
        @SerializedName("PriorityID")
        @Expose
        private Integer priorityID;
        @SerializedName("ResponseTime")
        @Expose
        private String responseTime;
        @SerializedName("ResolutionTime")
        @Expose
        private String resolutionTime;
        @SerializedName("RemainderEmailTime")
        @Expose
        private String remainderEmailTime;
        @SerializedName("RemainderEmailID")
        @Expose
        private String remainderEmailID;
        @SerializedName("OtherRemainderEmailID")
        @Expose
        private String otherRemainderEmailID;

        public String getPriorityName() {
            return priorityName;
        }

        public void setPriorityName(String priorityName) {
            this.priorityName = priorityName;
        }

        public String getPriorityDescription() {
            return priorityDescription;
        }

        public void setPriorityDescription(String priorityDescription) {
            this.priorityDescription = priorityDescription;
        }

        public Integer getPriorityID() {
            return priorityID;
        }

        public void setPriorityID(Integer priorityID) {
            this.priorityID = priorityID;
        }

        public String getResponseTime() {
            return responseTime;
        }

        public void setResponseTime(String responseTime) {
            this.responseTime = responseTime;
        }

        public String getResolutionTime() {
            return resolutionTime;
        }

        public void setResolutionTime(String resolutionTime) {
            this.resolutionTime = resolutionTime;
        }

        public String getRemainderEmailTime() {
            return remainderEmailTime;
        }

        public void setRemainderEmailTime(String remainderEmailTime) {
            this.remainderEmailTime = remainderEmailTime;
        }

        public String getRemainderEmailID() {
            return remainderEmailID;
        }

        public void setRemainderEmailID(String remainderEmailID) {
            this.remainderEmailID = remainderEmailID;
        }

        public String getOtherRemainderEmailID() {
            return otherRemainderEmailID;
        }

        public void setOtherRemainderEmailID(String otherRemainderEmailID) {
            this.otherRemainderEmailID = otherRemainderEmailID;
        }

    }
}
