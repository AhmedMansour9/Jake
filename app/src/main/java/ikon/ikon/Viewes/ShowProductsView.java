package ikon.ikon.Viewes;

import java.util.List;

import ikon.ikon.Model.Product_Orders;
import ikon.ikon.Model.ShopOrder;
import ikon.ikon.Model.ShowOrdersyid;

/**
 * Created by ic on 9/17/2018.
 */

public interface ShowProductsView {
    void GetListOrderShopping(List<Product_Orders> list);
    void Errorlistorder();

}
