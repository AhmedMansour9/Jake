package ikon.ikon.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ic on 9/5/2018.
 */

public class UserLogin {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("user_token")
    @Expose
    private String userToken;
    @SerializedName("customers_name")
    @Expose
    private String customersName;
    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("customers_phone")
    @Expose
    private String customersPhone;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getCustomersName() {
        return customersName;
    }

    public void setCustomersName(String customersName) {
        this.customersName = customersName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getCustomersPhone() {
        return customersPhone;
    }

    public void setCustomersPhone(String customersPhone) {
        this.customersPhone = customersPhone;
    }
}
