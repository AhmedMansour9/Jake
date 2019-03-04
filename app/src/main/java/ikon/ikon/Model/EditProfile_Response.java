package ikon.ikon.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditProfile_Response {

    @SerializedName("data")
    @Expose
    private EditUser data;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("error")
    @Expose
    private String error;

    public EditUser getData() {
        return data;
    }

    public void setData(EditUser data) {
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
