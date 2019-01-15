package ikon.ikon.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ahmed on 06/01/2019.
 */

public class Sales {


    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("SalesMan")
    @Expose
    private List<SalesMan> salesMan = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<SalesMan> getSalesMan() {
        return salesMan;
    }

    public void setSalesMan(List<SalesMan> salesMan) {
        this.salesMan = salesMan;
    }

}
