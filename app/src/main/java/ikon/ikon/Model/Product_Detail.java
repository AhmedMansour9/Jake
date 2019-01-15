package ikon.ikon.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ahmed on 30/12/2018.
 */

public class Product_Detail {

    @SerializedName("products_id")
    @Expose
    private String productsId;
    @SerializedName("products_quantity")
    @Expose
    private String productsQuantity;
    @SerializedName("products_model")
    @Expose
    private String productsModel;
    @SerializedName("products_image")
    @Expose
    private String productsImage;
    @SerializedName("products_price")
    @Expose
    private String productsPrice;
    @SerializedName("products_date_added")
    @Expose
    private String productsDateAdded;
    @SerializedName("products_last_modified")
    @Expose
    private Object productsLastModified;
    @SerializedName("products_date_available")
    @Expose
    private Object productsDateAvailable;
    @SerializedName("products_weight")
    @Expose
    private String productsWeight;
    @SerializedName("products_weight_unit")
    @Expose
    private String productsWeightUnit;
    @SerializedName("products_status")
    @Expose
    private String productsStatus;
    @SerializedName("products_tax_class_id")
    @Expose
    private String productsTaxClassId;
    @SerializedName("manufacturers_id")
    @Expose
    private String manufacturersId;
    @SerializedName("products_ordered")
    @Expose
    private String productsOrdered;
    @SerializedName("products_liked")
    @Expose
    private String productsLiked;
    @SerializedName("low_limit")
    @Expose
    private String lowLimit;
    @SerializedName("is_feature")
    @Expose
    private String isFeature;
    @SerializedName("products_slug")
    @Expose
    private String productsSlug;
    @SerializedName("products_type")
    @Expose
    private String productsType;
    @SerializedName("products_min_order")
    @Expose
    private String productsMinOrder;
    @SerializedName("products_max_stock")
    @Expose
    private String productsMaxStock;
    @SerializedName("language_id")
    @Expose
    private String languageId;
    @SerializedName("products_name")
    @Expose
    private String productsName;
    @SerializedName("products_description")
    @Expose
    private String productsDescription;
    @SerializedName("products_url")
    @Expose
    private String productsUrl;
    @SerializedName("products_viewed")
    @Expose
    private String productsViewed;
    @SerializedName("products_left_banner")
    @Expose
    private String productsLeftBanner;
    @SerializedName("products_left_banner_start_date")
    @Expose
    private String productsLeftBannerStartDate;
    @SerializedName("products_left_banner_expire_date")
    @Expose
    private String productsLeftBannerExpireDate;
    @SerializedName("products_right_banner")
    @Expose
    private String productsRightBanner;
    @SerializedName("products_right_banner_start_date")
    @Expose
    private String productsRightBannerStartDate;
    @SerializedName("products_right_banner_expire_date")
    @Expose
    private String productsRightBannerExpireDate;
    @SerializedName("manufacturers_name")
    @Expose
    private String manufacturersName;
    @SerializedName("manufacturers_image")
    @Expose
    private String manufacturersImage;
    @SerializedName("date_added")
    @Expose
    private String dateAdded;
    @SerializedName("last_modified")
    @Expose
    private Object lastModified;
    @SerializedName("manufacturers_slug")
    @Expose
    private String manufacturersSlug;
    @SerializedName("manufacturers_url")
    @Expose
    private String manufacturersUrl;
    @SerializedName("discount_price")
    @Expose
    private String discountPrice;
    @SerializedName("defaultStock")
    @Expose
    private Integer defaultStock;
    @SerializedName("isLiked")
    @Expose
    private String isLiked;

    public String getProductsId() {
        return productsId;
    }

    public void setProductsId(String productsId) {
        this.productsId = productsId;
    }

    public String getProductsQuantity() {
        return productsQuantity;
    }

    public void setProductsQuantity(String productsQuantity) {
        this.productsQuantity = productsQuantity;
    }

    public String getProductsModel() {
        return productsModel;
    }

    public void setProductsModel(String productsModel) {
        this.productsModel = productsModel;
    }

    public String getProductsImage() {
        return productsImage;
    }

    public void setProductsImage(String productsImage) {
        this.productsImage = productsImage;
    }

    public String getProductsPrice() {
        return productsPrice;
    }

    public void setProductsPrice(String productsPrice) {
        this.productsPrice = productsPrice;
    }

    public String getProductsDateAdded() {
        return productsDateAdded;
    }

    public void setProductsDateAdded(String productsDateAdded) {
        this.productsDateAdded = productsDateAdded;
    }

    public Object getProductsLastModified() {
        return productsLastModified;
    }

    public void setProductsLastModified(Object productsLastModified) {
        this.productsLastModified = productsLastModified;
    }

    public Object getProductsDateAvailable() {
        return productsDateAvailable;
    }

    public void setProductsDateAvailable(Object productsDateAvailable) {
        this.productsDateAvailable = productsDateAvailable;
    }

    public String getProductsWeight() {
        return productsWeight;
    }

    public void setProductsWeight(String productsWeight) {
        this.productsWeight = productsWeight;
    }

    public String getProductsWeightUnit() {
        return productsWeightUnit;
    }

    public void setProductsWeightUnit(String productsWeightUnit) {
        this.productsWeightUnit = productsWeightUnit;
    }

    public String getProductsStatus() {
        return productsStatus;
    }

    public void setProductsStatus(String productsStatus) {
        this.productsStatus = productsStatus;
    }

    public String getProductsTaxClassId() {
        return productsTaxClassId;
    }

    public void setProductsTaxClassId(String productsTaxClassId) {
        this.productsTaxClassId = productsTaxClassId;
    }

    public String getManufacturersId() {
        return manufacturersId;
    }

    public void setManufacturersId(String manufacturersId) {
        this.manufacturersId = manufacturersId;
    }

    public String getProductsOrdered() {
        return productsOrdered;
    }

    public void setProductsOrdered(String productsOrdered) {
        this.productsOrdered = productsOrdered;
    }

    public String getProductsLiked() {
        return productsLiked;
    }

    public void setProductsLiked(String productsLiked) {
        this.productsLiked = productsLiked;
    }

    public String getLowLimit() {
        return lowLimit;
    }

    public void setLowLimit(String lowLimit) {
        this.lowLimit = lowLimit;
    }

    public String getIsFeature() {
        return isFeature;
    }

    public void setIsFeature(String isFeature) {
        this.isFeature = isFeature;
    }

    public String getProductsSlug() {
        return productsSlug;
    }

    public void setProductsSlug(String productsSlug) {
        this.productsSlug = productsSlug;
    }

    public String getProductsType() {
        return productsType;
    }

    public void setProductsType(String productsType) {
        this.productsType = productsType;
    }

    public String getProductsMinOrder() {
        return productsMinOrder;
    }

    public void setProductsMinOrder(String productsMinOrder) {
        this.productsMinOrder = productsMinOrder;
    }

    public String getProductsMaxStock() {
        return productsMaxStock;
    }

    public void setProductsMaxStock(String productsMaxStock) {
        this.productsMaxStock = productsMaxStock;
    }

    public String getLanguageId() {
        return languageId;
    }

    public void setLanguageId(String languageId) {
        this.languageId = languageId;
    }

    public String getProductsName() {
        return productsName;
    }

    public void setProductsName(String productsName) {
        this.productsName = productsName;
    }

    public String getProductsDescription() {
        return productsDescription;
    }

    public void setProductsDescription(String productsDescription) {
        this.productsDescription = productsDescription;
    }

    public String getProductsUrl() {
        return productsUrl;
    }

    public void setProductsUrl(String productsUrl) {
        this.productsUrl = productsUrl;
    }

    public String getProductsViewed() {
        return productsViewed;
    }

    public void setProductsViewed(String productsViewed) {
        this.productsViewed = productsViewed;
    }

    public String getProductsLeftBanner() {
        return productsLeftBanner;
    }

    public void setProductsLeftBanner(String productsLeftBanner) {
        this.productsLeftBanner = productsLeftBanner;
    }

    public String getProductsLeftBannerStartDate() {
        return productsLeftBannerStartDate;
    }

    public void setProductsLeftBannerStartDate(String productsLeftBannerStartDate) {
        this.productsLeftBannerStartDate = productsLeftBannerStartDate;
    }

    public String getProductsLeftBannerExpireDate() {
        return productsLeftBannerExpireDate;
    }

    public void setProductsLeftBannerExpireDate(String productsLeftBannerExpireDate) {
        this.productsLeftBannerExpireDate = productsLeftBannerExpireDate;
    }

    public String getProductsRightBanner() {
        return productsRightBanner;
    }

    public void setProductsRightBanner(String productsRightBanner) {
        this.productsRightBanner = productsRightBanner;
    }

    public String getProductsRightBannerStartDate() {
        return productsRightBannerStartDate;
    }

    public void setProductsRightBannerStartDate(String productsRightBannerStartDate) {
        this.productsRightBannerStartDate = productsRightBannerStartDate;
    }

    public String getProductsRightBannerExpireDate() {
        return productsRightBannerExpireDate;
    }

    public void setProductsRightBannerExpireDate(String productsRightBannerExpireDate) {
        this.productsRightBannerExpireDate = productsRightBannerExpireDate;
    }

    public String getManufacturersName() {
        return manufacturersName;
    }

    public void setManufacturersName(String manufacturersName) {
        this.manufacturersName = manufacturersName;
    }

    public String getManufacturersImage() {
        return manufacturersImage;
    }

    public void setManufacturersImage(String manufacturersImage) {
        this.manufacturersImage = manufacturersImage;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Object getLastModified() {
        return lastModified;
    }

    public void setLastModified(Object lastModified) {
        this.lastModified = lastModified;
    }

    public String getManufacturersSlug() {
        return manufacturersSlug;
    }

    public void setManufacturersSlug(String manufacturersSlug) {
        this.manufacturersSlug = manufacturersSlug;
    }

    public String getManufacturersUrl() {
        return manufacturersUrl;
    }

    public void setManufacturersUrl(String manufacturersUrl) {
        this.manufacturersUrl = manufacturersUrl;
    }

    public String getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
    }


    public Integer getDefaultStock() {
        return defaultStock;
    }

    public void setDefaultStock(Integer defaultStock) {
        this.defaultStock = defaultStock;
    }

    public String getIsLiked() {
        return isLiked;
    }

    public void setIsLiked(String isLiked) {
        this.isLiked = isLiked;
    }


}
