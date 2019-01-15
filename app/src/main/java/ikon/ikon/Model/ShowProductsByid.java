package ikon.ikon.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ic on 9/17/2018.
 */

public class ShowProductsByid {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("Details")
    @Expose
    private List<Detail_Products> details = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Detail_Products> getDetails() {
        return details;
    }

    public void setDetails(List<Detail_Products> details) {
        this.details = details;
    }
}
