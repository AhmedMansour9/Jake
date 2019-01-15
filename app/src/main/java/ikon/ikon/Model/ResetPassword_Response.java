package ikon.ikon.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import ikon.ikon.Activites.ResetPassword;

/**
 * Created by Ahmed on 08/01/2019.
 */

public class ResetPassword_Response {

    @SerializedName("data")
    @Expose
    private ikon.ikon.Model.ResetPassword data;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("error")
    @Expose
    private String error;

    public ikon.ikon.Model.ResetPassword getData() {
        return data;
    }

    public void setData(ikon.ikon.Model.ResetPassword data) {
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
