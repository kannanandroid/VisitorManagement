package com.e.ifazig.visitormanagement.api_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GetScoreApiResponse {
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

        @SerializedName("ScoreID")
        @Expose
        private Integer scoreID;
        @SerializedName("Rating")
        @Expose
        private Integer Rating;

        @SerializedName("ScoreName")
        @Expose
        private String scoreName;
        @SerializedName("ScoreImage")
        @Expose
        private String scoreImage;
        @SerializedName("IsSubCategory")
        @Expose
        private Boolean isSubCategory;

        public Integer getScoreID() {
            return scoreID;
        }

        public void setScoreID(Integer scoreID) {
            this.scoreID = scoreID;
        }

        public String getScoreName() {
            return scoreName;
        }

        public void setScoreName(String scoreName) {
            this.scoreName = scoreName;
        }

        public String getScoreImage() {
            return scoreImage;
        }

        public void setScoreImage(String scoreImage) {
            this.scoreImage = scoreImage;
        }

        public Boolean getIsSubCategory() {
            return isSubCategory;
        }

        public void setIsSubCategory(Boolean isSubCategory) {
            this.isSubCategory = isSubCategory;
        }

        public Integer getRating() {
            return Rating;
        }

        public void setRating(Integer rating) {
            Rating = rating;
        }
    }
}
