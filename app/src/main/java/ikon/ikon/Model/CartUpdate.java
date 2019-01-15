package ikon.ikon.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ahmed on 03/01/2019.
 */

public class CartUpdate {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("cart")
    @Expose
    private String cart;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCart() {
        return cart;
    }

    public void setCart(String cart) {
        this.cart = cart;
    }
}
