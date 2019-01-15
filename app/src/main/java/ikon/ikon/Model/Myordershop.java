package ikon.ikon.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ic on 9/20/2018.
 */

public class Myordershop {
    @SerializedName("orders_id")
    @Expose
    private String ordersId;
    @SerializedName("total_tax")
    @Expose
    private String totalTax;
    @SerializedName("customers_id")
    @Expose
    private String customersId;
    @SerializedName("customers_name")
    @Expose
    private String customersName;
    @SerializedName("customers_company")
    @Expose
    private Object customersCompany;
    @SerializedName("customers_street_address")
    @Expose
    private String customersStreetAddress;
    @SerializedName("customers_suburb")
    @Expose
    private String customersSuburb;
    @SerializedName("customers_city")
    @Expose
    private String customersCity;
    @SerializedName("customers_postcode")
    @Expose
    private String customersPostcode;
    @SerializedName("customers_state")
    @Expose
    private String customersState;
    @SerializedName("customers_country")
    @Expose
    private String customersCountry;
    @SerializedName("customers_telephone")
    @Expose
    private String customersTelephone;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("customers_address_format_id")
    @Expose
    private Object customersAddressFormatId;
    @SerializedName("delivery_name")
    @Expose
    private String deliveryName;
    @SerializedName("delivery_company")
    @Expose
    private Object deliveryCompany;
    @SerializedName("delivery_street_address")
    @Expose
    private String deliveryStreetAddress;
    @SerializedName("delivery_suburb")
    @Expose
    private String deliverySuburb;
    @SerializedName("delivery_city")
    @Expose
    private String deliveryCity;
    @SerializedName("delivery_postcode")
    @Expose
    private String deliveryPostcode;
    @SerializedName("delivery_state")
    @Expose
    private String deliveryState;
    @SerializedName("delivery_country")
    @Expose
    private String deliveryCountry;
    @SerializedName("delivery_address_format_id")
    @Expose
    private Object deliveryAddressFormatId;
    @SerializedName("billing_name")
    @Expose
    private String billingName;
    @SerializedName("billing_company")
    @Expose
    private Object billingCompany;
    @SerializedName("billing_street_address")
    @Expose
    private String billingStreetAddress;
    @SerializedName("billing_suburb")
    @Expose
    private String billingSuburb;
    @SerializedName("billing_city")
    @Expose
    private String billingCity;
    @SerializedName("billing_postcode")
    @Expose
    private String billingPostcode;
    @SerializedName("billing_state")
    @Expose
    private String billingState;
    @SerializedName("billing_country")
    @Expose
    private String billingCountry;
    @SerializedName("billing_address_format_id")
    @Expose
    private String billingAddressFormatId;
    @SerializedName("payment_method")
    @Expose
    private String paymentMethod;
    @SerializedName("cc_type")
    @Expose
    private String ccType;
    @SerializedName("cc_owner")
    @Expose
    private String ccOwner;
    @SerializedName("cc_number")
    @Expose
    private String ccNumber;
    @SerializedName("cc_expires")
    @Expose
    private String ccExpires;
    @SerializedName("last_modified")
    @Expose
    private String lastModified;
    @SerializedName("date_purchased")
    @Expose
    private String datePurchased;
    @SerializedName("orders_date_finished")
    @Expose
    private Object ordersDateFinished;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("currency_value")
    @Expose
    private Object currencyValue;
    @SerializedName("order_price")
    @Expose
    private String orderPrice;
    @SerializedName("shipping_cost")
    @Expose
    private String shippingCost;
    @SerializedName("shipping_method")
    @Expose
    private String shippingMethod;
    @SerializedName("shipping_duration")
    @Expose
    private Object shippingDuration;
    @SerializedName("order_information")
    @Expose
    private String orderInformation;
    @SerializedName("is_seen")
    @Expose
    private String isSeen;
    @SerializedName("coupon_code")
    @Expose
    private String couponCode;
    @SerializedName("coupon_amount")
    @Expose
    private String couponAmount;
    @SerializedName("exclude_product_ids")
    @Expose
    private String excludeProductIds;
    @SerializedName("product_categories")
    @Expose
    private String productCategories;
    @SerializedName("excluded_product_categories")
    @Expose
    private String excludedProductCategories;
    @SerializedName("free_shipping")
    @Expose
    private String freeShipping;
    @SerializedName("product_ids")
    @Expose
    private String productIds;
    @SerializedName("ordered_source")
    @Expose
    private String orderedSource;
    @SerializedName("delivery_phone")
    @Expose
    private String deliveryPhone;
    @SerializedName("billing_phone")
    @Expose
    private String billingPhone;
    @SerializedName("transaction_id")
    @Expose
    private Object transactionId;
    @SerializedName("total_price")
    @Expose
    private String totalPrice;
    @SerializedName("orders_status_id")
    @Expose
    private String ordersStatusId;
    @SerializedName("orders_status")
    @Expose
    private String ordersStatus;

    public String getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(String ordersId) {
        this.ordersId = ordersId;
    }

    public String getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(String totalTax) {
        this.totalTax = totalTax;
    }

    public String getCustomersId() {
        return customersId;
    }

    public void setCustomersId(String customersId) {
        this.customersId = customersId;
    }

    public String getCustomersName() {
        return customersName;
    }

    public void setCustomersName(String customersName) {
        this.customersName = customersName;
    }

    public Object getCustomersCompany() {
        return customersCompany;
    }

    public void setCustomersCompany(Object customersCompany) {
        this.customersCompany = customersCompany;
    }

    public String getCustomersStreetAddress() {
        return customersStreetAddress;
    }

    public void setCustomersStreetAddress(String customersStreetAddress) {
        this.customersStreetAddress = customersStreetAddress;
    }

    public String getCustomersSuburb() {
        return customersSuburb;
    }

    public void setCustomersSuburb(String customersSuburb) {
        this.customersSuburb = customersSuburb;
    }

    public String getCustomersCity() {
        return customersCity;
    }

    public void setCustomersCity(String customersCity) {
        this.customersCity = customersCity;
    }

    public String getCustomersPostcode() {
        return customersPostcode;
    }

    public void setCustomersPostcode(String customersPostcode) {
        this.customersPostcode = customersPostcode;
    }

    public String getCustomersState() {
        return customersState;
    }

    public void setCustomersState(String customersState) {
        this.customersState = customersState;
    }

    public String getCustomersCountry() {
        return customersCountry;
    }

    public void setCustomersCountry(String customersCountry) {
        this.customersCountry = customersCountry;
    }

    public String getCustomersTelephone() {
        return customersTelephone;
    }

    public void setCustomersTelephone(String customersTelephone) {
        this.customersTelephone = customersTelephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getCustomersAddressFormatId() {
        return customersAddressFormatId;
    }

    public void setCustomersAddressFormatId(Object customersAddressFormatId) {
        this.customersAddressFormatId = customersAddressFormatId;
    }

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public Object getDeliveryCompany() {
        return deliveryCompany;
    }

    public void setDeliveryCompany(Object deliveryCompany) {
        this.deliveryCompany = deliveryCompany;
    }

    public String getDeliveryStreetAddress() {
        return deliveryStreetAddress;
    }

    public void setDeliveryStreetAddress(String deliveryStreetAddress) {
        this.deliveryStreetAddress = deliveryStreetAddress;
    }

    public String getDeliverySuburb() {
        return deliverySuburb;
    }

    public void setDeliverySuburb(String deliverySuburb) {
        this.deliverySuburb = deliverySuburb;
    }

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    public String getDeliveryPostcode() {
        return deliveryPostcode;
    }

    public void setDeliveryPostcode(String deliveryPostcode) {
        this.deliveryPostcode = deliveryPostcode;
    }

    public String getDeliveryState() {
        return deliveryState;
    }

    public void setDeliveryState(String deliveryState) {
        this.deliveryState = deliveryState;
    }

    public String getDeliveryCountry() {
        return deliveryCountry;
    }

    public void setDeliveryCountry(String deliveryCountry) {
        this.deliveryCountry = deliveryCountry;
    }

    public Object getDeliveryAddressFormatId() {
        return deliveryAddressFormatId;
    }

    public void setDeliveryAddressFormatId(Object deliveryAddressFormatId) {
        this.deliveryAddressFormatId = deliveryAddressFormatId;
    }

    public String getBillingName() {
        return billingName;
    }

    public void setBillingName(String billingName) {
        this.billingName = billingName;
    }

    public Object getBillingCompany() {
        return billingCompany;
    }

    public void setBillingCompany(Object billingCompany) {
        this.billingCompany = billingCompany;
    }

    public String getBillingStreetAddress() {
        return billingStreetAddress;
    }

    public void setBillingStreetAddress(String billingStreetAddress) {
        this.billingStreetAddress = billingStreetAddress;
    }

    public String getBillingSuburb() {
        return billingSuburb;
    }

    public void setBillingSuburb(String billingSuburb) {
        this.billingSuburb = billingSuburb;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingPostcode() {
        return billingPostcode;
    }

    public void setBillingPostcode(String billingPostcode) {
        this.billingPostcode = billingPostcode;
    }

    public String getBillingState() {
        return billingState;
    }

    public void setBillingState(String billingState) {
        this.billingState = billingState;
    }

    public String getBillingCountry() {
        return billingCountry;
    }

    public void setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
    }

    public String getBillingAddressFormatId() {
        return billingAddressFormatId;
    }

    public void setBillingAddressFormatId(String billingAddressFormatId) {
        this.billingAddressFormatId = billingAddressFormatId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCcType() {
        return ccType;
    }

    public void setCcType(String ccType) {
        this.ccType = ccType;
    }

    public String getCcOwner() {
        return ccOwner;
    }

    public void setCcOwner(String ccOwner) {
        this.ccOwner = ccOwner;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public String getCcExpires() {
        return ccExpires;
    }

    public void setCcExpires(String ccExpires) {
        this.ccExpires = ccExpires;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getDatePurchased() {
        return datePurchased;
    }

    public void setDatePurchased(String datePurchased) {
        this.datePurchased = datePurchased;
    }

    public Object getOrdersDateFinished() {
        return ordersDateFinished;
    }

    public void setOrdersDateFinished(Object ordersDateFinished) {
        this.ordersDateFinished = ordersDateFinished;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Object getCurrencyValue() {
        return currencyValue;
    }

    public void setCurrencyValue(Object currencyValue) {
        this.currencyValue = currencyValue;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(String shippingCost) {
        this.shippingCost = shippingCost;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public Object getShippingDuration() {
        return shippingDuration;
    }

    public void setShippingDuration(Object shippingDuration) {
        this.shippingDuration = shippingDuration;
    }

    public String getOrderInformation() {
        return orderInformation;
    }

    public void setOrderInformation(String orderInformation) {
        this.orderInformation = orderInformation;
    }

    public String getIsSeen() {
        return isSeen;
    }

    public void setIsSeen(String isSeen) {
        this.isSeen = isSeen;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(String couponAmount) {
        this.couponAmount = couponAmount;
    }

    public String getExcludeProductIds() {
        return excludeProductIds;
    }

    public void setExcludeProductIds(String excludeProductIds) {
        this.excludeProductIds = excludeProductIds;
    }

    public String getProductCategories() {
        return productCategories;
    }

    public void setProductCategories(String productCategories) {
        this.productCategories = productCategories;
    }

    public String getExcludedProductCategories() {
        return excludedProductCategories;
    }

    public void setExcludedProductCategories(String excludedProductCategories) {
        this.excludedProductCategories = excludedProductCategories;
    }

    public String getFreeShipping() {
        return freeShipping;
    }

    public void setFreeShipping(String freeShipping) {
        this.freeShipping = freeShipping;
    }

    public String getProductIds() {
        return productIds;
    }

    public void setProductIds(String productIds) {
        this.productIds = productIds;
    }

    public String getOrderedSource() {
        return orderedSource;
    }

    public void setOrderedSource(String orderedSource) {
        this.orderedSource = orderedSource;
    }

    public String getDeliveryPhone() {
        return deliveryPhone;
    }

    public void setDeliveryPhone(String deliveryPhone) {
        this.deliveryPhone = deliveryPhone;
    }

    public String getBillingPhone() {
        return billingPhone;
    }

    public void setBillingPhone(String billingPhone) {
        this.billingPhone = billingPhone;
    }

    public Object getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Object transactionId) {
        this.transactionId = transactionId;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrdersStatusId() {
        return ordersStatusId;
    }

    public void setOrdersStatusId(String ordersStatusId) {
        this.ordersStatusId = ordersStatusId;
    }

    public String getOrdersStatus() {
        return ordersStatus;
    }

    public void setOrdersStatus(String ordersStatus) {
        this.ordersStatus = ordersStatus;
    }


}