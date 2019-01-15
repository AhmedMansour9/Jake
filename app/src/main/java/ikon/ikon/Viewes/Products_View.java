package ikon.ikon.Viewes;

import java.util.List;

import ikon.ikon.Model.Product_Detail;
import ikon.ikon.Model.Products;

/**
 * Created by Ahmed on 30/12/2018.
 */

public interface Products_View {

    void Products(List<Product_Detail> list);
    void ErrorProducts();

    void ProductsFlash(List<Product_Detail> list);
    void ErrorProductsFlash();

    void EmptyProductsFlash();


}
