package ikon.ikon.Retrofit;

import java.util.Map;

import ikon.ikon.Model.AccessoriesResponse;
import ikon.ikon.Model.AccessorySubCategoryResoonse;
import ikon.ikon.Model.BannserResponsse;
import ikon.ikon.Model.CartResponse;
import ikon.ikon.Model.CartUpdate_Response;
import ikon.ikon.Model.Cart_Response;
import ikon.ikon.Model.Categories_Response;
import ikon.ikon.Model.ColorResponse;
import ikon.ikon.Model.Counter_Response;
import ikon.ikon.Model.EditProfile_Response;
import ikon.ikon.Model.GetPriceResponse;
import ikon.ikon.Model.IssueResponse;
import ikon.ikon.Model.IssueTubeEnglishResponse;
import ikon.ikon.Model.ListOrderResponse;
import ikon.ikon.Model.ListOrderShoppingResponse;
import ikon.ikon.Model.MyorderShopingResponse;
import ikon.ikon.Model.OrderResponse;
import ikon.ikon.Model.OrderShopping_Response;
import ikon.ikon.Model.Order_Response;
import ikon.ikon.Model.PeoductResponse;
import ikon.ikon.Model.Products_Response;
import ikon.ikon.Model.ProfileResponse;
import ikon.ikon.Model.ResetPassword_Response;
import ikon.ikon.Model.Sales_Response;
import ikon.ikon.Model.ShowProductsResponse;
import ikon.ikon.Model.SpartsResponse;
import ikon.ikon.Model.UserLoginResponse;
import ikon.ikon.Model.UserRegisterResponse;
import ikon.ikon.Model.phonesResponse;
import ikon.ikon.Model.RegisterFaceResponse;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by HP on 04/09/2018.
 */

public interface Apiinterface {


    @POST("signupMobile")
    Call<UserRegisterResponse> register(@QueryMap Map<String,String> queryMab);

    @POST("login")
    Call<UserLoginResponse> Login(@QueryMap Map<String,String> queryMab);

    @POST("password")
    Call<ResetPassword_Response> ResetPass(@QueryMap Map<String,String> queryMab);

    @POST("signupMobileFacebook")
    Call<RegisterFaceResponse> RegisterFace_Book(@QueryMap Map<String,String> queryMab);

    @POST("signupMobileGoogle")
    Call<UserLoginResponse> RegisterGoogle(@QueryMap Map<String,String> queryMab);

    @POST("sparePartsList")
    Call<PeoductResponse> GetProducts(@QueryMap Map<String,String> queryMab);

    @POST("issueTypeList")
    Call<IssueResponse> GetIssueTybe(@QueryMap Map<String,String> queryMab);

    @POST("issueTypeList")
    Call<IssueTubeEnglishResponse> GetIssueEnglish(@QueryMap Map<String,String> queryMab);

    @POST("issuePrice")
    Call<GetPriceResponse> GetPrice(@QueryMap Map<String,String> queryMab);

    @POST("showProductShop")
    Call<phonesResponse> GetPHones(@QueryMap Map<String,String> queryMab);

    @POST("mobileType")
    Call<phonesResponse> GetPotherHones(@QueryMap Map<String,String> queryMab);

        @POST("getAccessoriesCategories")
    Call<AccessoriesResponse> GetAccessories(@QueryMap Map<String,String> queryMab);

    @POST("showAccessoriesShopByCatID")
    Call<AccessorySubCategoryResoonse> GetSubCategories(@QueryMap Map<String,String> queryMab);


    @POST("showSparePartsShop")
    Call<SpartsResponse> GetSparts(@QueryMap Map<String,String> queryMab);

    @POST("maintenanceOrder")
    Call<OrderResponse> Showorder(@QueryMap Map<String,String> queryMab);

    @POST("shopOrder")
    Call<OrderShopping_Response> Ordershop(@QueryMap Map<String,String> queryMab);

    @POST("colorList")
    Call<ColorResponse> GetColors(@QueryMap Map<String,String> queryMab);

    @POST("mobileBanner")
    Call<BannserResponsse> GetBanner(@QueryMap Map<String,String> queryMab);

    @POST("listMaintenanceOrder")
    Call<ListOrderResponse> GetListOrder(@QueryMap Map<String,String> queryMab);

    @POST("orderMaintenanceCustomer")
    Call<ListOrderResponse> GetListOrdercustomer(@QueryMap Map<String,String> queryMab);

    @POST("listShopOrder")
    Call<ListOrderShoppingResponse> GetListOrderShopping(@QueryMap Map<String,String> queryMab);

    @POST("ordersDetails")
    Call<ShowProductsResponse> GetListOrderShoppingById(@QueryMap Map<String,String> queryMab);

    @POST("orderCustomer")
    Call<MyorderShopingResponse> GetListMyOrderShoping(@QueryMap Map<String,String> queryMab);


    @POST("profile-customer")
    Call<ProfileResponse> profileData (@QueryMap Map <String,String> queryMap);

    @POST("showCategory")
    Call<Categories_Response> categories(@QueryMap Map<String, String> queryMab);

    @POST("showProduct")
    Call<Products_Response> ProductsFeature(@QueryMap Map<String, String> queryMab);

    @POST("showProductFeature")
    Call<Products_Response> ProductssssFeature(@QueryMap Map<String, String> queryMab);


    @POST("myCart")
    Call<CartResponse> ShowCart(@QueryMap Map<String,String> queryMab);

    @POST("showProductSpecial")
    Call<Products_Response> Slash(@QueryMap Map<String, String> queryMab);


    @POST("GetProductsFeature")
    Call<Products_Response> GetProductsFeature(@QueryMap Map<String, String> queryMab);

    @POST("showProductFlashSelle")
    Call<Products_Response> ProductsFlash(@QueryMap Map<String, String> queryMab);

    @POST("addToCart")
    Call<Cart_Response> AddCart(@QueryMap Map<String,String> queryMab);

    @POST("deleteFromCart")
    Call<CartResponse> DeleteCart(@QueryMap Map<String,String> queryMab);

    @POST("updateCartAddQty")
    Call<CartUpdate_Response> UpdateCart(@QueryMap Map<String,String> queryMab);

    @POST("updateCartdeleteQty")
    Call<CartUpdate_Response> MinusCart(@QueryMap Map<String,String> queryMab);

    @POST("countAndTotal")
    Call<Counter_Response> Counter(@QueryMap Map<String,String> queryMab);

    @POST("placeOrder")
    Call<Order_Response> Order(@QueryMap Map<String,String> queryMab);

    @POST("getSalesman")
    Call<Sales_Response> getSalesMans(@QueryMap Map<String,String> queryMab);

    @POST("updateMyProfile")
    Call<EditProfile_Response> EditProfile(@QueryMap Map<String,String> queryMab);


}

