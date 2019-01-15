package ikon.ikon.Viewes;

import java.util.List;

import ikon.ikon.Model.Products;
import ikon.ikon.Model.SalesMan;

/**
 * Created by Ahmed on 06/01/2019.
 */

public interface Sales_View {
    void GetSalesMan(List<SalesMan> a);
    void showError(String error);

}
