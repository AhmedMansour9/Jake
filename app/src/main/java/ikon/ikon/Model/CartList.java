package ikon.ikon.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ahmed on 02/01/2019.
 */

public class CartList {


    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("cart")
    @Expose
    private List<Cart_Details> cart = null;
    @SerializedName("count_carts")
    @Expose
    private String countCarts;
    @SerializedName("total_carts")
    @Expose
    private String totalCarts;

    @SerializedName("counter")
    @Expose
    private String counter;



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Cart_Details> getCart() {
        return cart;
    }

    public void setCart(List<Cart_Details> cart) {
        this.cart = cart;
    }
    public String getCountCarts() {
        return countCarts;
    }

    public void setCountCarts(String countCarts) {
        this.countCarts = countCarts;
    }

    public String getTotalCarts() {
        return totalCarts;
    }

    public void setTotalCarts(String totalCarts) {
        this.totalCarts = totalCarts;
    }

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

}
