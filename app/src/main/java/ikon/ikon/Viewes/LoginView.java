package ikon.ikon.Viewes;

/**
 * Created by HP on 04/09/2018.
 */

public interface LoginView {

    void openMain(String a);
    void OpenRole(String UserName,String Email,String usertoken,String phone);
    void showError(String error);
    void invalideemail(String error);

}
