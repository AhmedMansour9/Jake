package ikon.ikon.Model;

/**
 * Created by Ahmed on 30/12/2018.
 */

public class Product_Details {


    private String productId,productName,productImage,price,modelProduct,
            CategoryName,brandName,addressVendor,phoneVendor,VendorName,Descrption,ProductQuantity;

    public String getProductQuantity() {
        return ProductQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        ProductQuantity = productQuantity;
    }

    public String getDescrption() {
        return Descrption;
    }

    public void setDescrption(String descrption) {
        Descrption = descrption;
    }

    public String getVendorName() {
        return VendorName;
    }

    public void setVendorName(String vendorName) {
        VendorName = vendorName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getModelProduct() {
        return modelProduct;
    }

    public void setModelProduct(String modelProduct) {
        this.modelProduct = modelProduct;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getAddressVendor() {
        return addressVendor;
    }

    public void setAddressVendor(String addressVendor) {
        this.addressVendor = addressVendor;
    }

    public String getPhoneVendor() {
        return phoneVendor;
    }

    public void setPhoneVendor(String phoneVendor) {
        this.phoneVendor = phoneVendor;
    }
}

