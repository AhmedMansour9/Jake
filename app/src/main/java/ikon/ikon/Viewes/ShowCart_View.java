package ikon.ikon.Viewes;

import java.util.List;

import ikon.ikon.Model.Cart;
import ikon.ikon.Model.Cart_Details;

/**
 * Created by Ahmed on 31/12/2018.
 */

public interface ShowCart_View {

    void ShowCart(List<Cart_Details> list);
    void ShowTotalprice(String price);
    void Error();
    void NoProduct();

}
