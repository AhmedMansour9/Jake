package ikon.ikon.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ahmed on 06/01/2019.
 */

public class Product_Orders {


    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("orders_products_id")
    @Expose
    private String ordersProductsId;
    @SerializedName("orders_id")
    @Expose
    private String ordersId;
    @SerializedName("products_id")
    @Expose
    private String productsId;
    @SerializedName("products_model")
    @Expose
    private Object productsModel;
    @SerializedName("products_name")
    @Expose
    private String productsName;
    @SerializedName("products_price")
    @Expose
    private String productsPrice;
    @SerializedName("final_price")
    @Expose
    private String finalPrice;
    @SerializedName("products_tax")
    @Expose
    private String productsTax;
    @SerializedName("products_quantity")
    @Expose
    private String productsQuantity;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOrdersProductsId() {
        return ordersProductsId;
    }

    public void setOrdersProductsId(String ordersProductsId) {
        this.ordersProductsId = ordersProductsId;
    }

    public String getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(String ordersId) {
        this.ordersId = ordersId;
    }

    public String getProductsId() {
        return productsId;
    }

    public void setProductsId(String productsId) {
        this.productsId = productsId;
    }

    public Object getProductsModel() {
        return productsModel;
    }

    public void setProductsModel(Object productsModel) {
        this.productsModel = productsModel;
    }

    public String getProductsName() {
        return productsName;
    }

    public void setProductsName(String productsName) {
        this.productsName = productsName;
    }

    public String getProductsPrice() {
        return productsPrice;
    }

    public void setProductsPrice(String productsPrice) {
        this.productsPrice = productsPrice;
    }

    public String getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(String finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getProductsTax() {
        return productsTax;
    }

    public void setProductsTax(String productsTax) {
        this.productsTax = productsTax;
    }

    public String getProductsQuantity() {
        return productsQuantity;
    }

    public void setProductsQuantity(String productsQuantity) {
        this.productsQuantity = productsQuantity;
    }

}
