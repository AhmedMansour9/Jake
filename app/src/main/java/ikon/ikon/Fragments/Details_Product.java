package ikon.ikon.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import ikon.ikon.Activites.Login;
import ikon.ikon.Activites.Shoping;
import ikon.ikon.Language;
import ikon.ikon.NetworikConntection;
import ikon.ikon.PreSenter.AddCart_Presenter;
import ikon.ikon.PreSenter.Get_Counter_Presenter;
import ikon.ikon.Viewes.Cart_View;
import ikon.ikon.Viewes.Counter_View;
import jak.jaaak.R;

import static android.content.Context.MODE_PRIVATE;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class Details_Product extends Fragment implements Counter_View,Cart_View {


    public Details_Product() {
        // Required empty public constructor
    }

    View view;
    String Address,Id,ProductName,Price,Model,CategoryName,BrandName,PhoneVendor,image,VendorName,Desciption;
    TextView T_Descrption,T_Address,T_ProductName,T_Price,T_Model,T_CategoryName,T_BrandName,T_PhoneVendor,loan,T_VendorName;
    ImageView ImagProduct;
    SwipeRefreshLayout mSwipeRefreshLayout;
    NetworikConntection networikConntection;
    RelativeLayout rela;
    Button Add_Cart;
    ProgressBar ProgrossSpare;
    AddCart_Presenter addCart;
    SharedPreferences prefs;
    String userToken;
    SharedPreferences.Editor Shared;
    Get_Counter_Presenter counter_presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_details__product, container, false);
        addCart=new AddCart_Presenter(getContext(),this);
        prefs=this.getActivity().getSharedPreferences( "login", MODE_PRIVATE );
        userToken=prefs.getString( "logggin",null);
        Shared=getActivity().getSharedPreferences("login",MODE_PRIVATE).edit();
        counter_presenter=new Get_Counter_Presenter(getApplicationContext(),this);
        init();
        getData();
        Add_To_Cart();
        return view;
    }
    public void init(){
        T_ProductName=view.findViewById(R.id.T_Name);
        T_Price=view.findViewById(R.id.T_Price);
        ImagProduct=view.findViewById(R.id.Image_product);
        T_Descrption=view.findViewById(R.id.T_Descrption);
        Add_Cart=view.findViewById(R.id.add_cart);
        ProgrossSpare=view.findViewById(R.id.Progross);
    }
    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible) {
            Shoping.Visablty = false;
        } else {

        }

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Shoping.Visablty = false;
    }

    public void getData(){
        Bundle a=getArguments();
        if(a!=null){
            Id=a.getString("id");
            ProductName=a.getString("productname");
            Price=a.getString("price");
            Model=a.getString("model");
            image=a.getString("image");
            Desciption=a.getString("descrip");
            T_Descrption.setText(Desciption);
            T_ProductName.setText(ProductName);
            T_Price.setText(Price+" LE");
//            T_PhoneVendor.setText(PhoneVendor);
            Picasso.with(getActivity())
                    .load("https://jak-go.com/"+image)
                    .into(ImagProduct, new Callback() {
                        @Override
                        public void onSuccess() {
                        }

                        @Override
                        public void onError() {
                        }
                    });
        }
    }

    @Override
    public void Success() {
        Toast.makeText(getContext(),getResources().getString(R.string.cartsuccesfull), Toast.LENGTH_SHORT).show();
        counter_presenter.GetCounter(userToken,"en");
        ProgrossSpare.setVisibility(View.GONE);
    }

    @Override
    public void Youhavethisproduct() {
        if(view!=null) {
            Toast.makeText(getActivity(),getResources().getString(R.string.productexist), Toast.LENGTH_SHORT).show();
        }
        ProgrossSpare.setVisibility(View.GONE);
    }

    @Override
    public void Failed() {

        ProgrossSpare.setVisibility(View.GONE);
    }

    @Override
    public void DeleteCart(String countity) {

    }

    public void Add_To_Cart(){
        Add_Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgrossSpare.setVisibility(View.VISIBLE);
                if(userToken!=null) {
                    if (Language.isRTL()) {
                        addCart.Add_toCart("ar", userToken, Id);
                    } else {
                        addCart.Add_toCart("en", userToken, Id);
                    }
                }else {
                    Toast.makeText(getActivity(), ""+getResources().getString(R.string.usernotlogin)
                            , Toast.LENGTH_SHORT).show();
                    Shared.putString("logggin",null);
                    Shared.putString("name",null);
                    Shared.putString("email",null);

                    startActivity(new Intent(getActivity(), Login.class));
                    getActivity().finish();

                }

            }
        });


    }

    @Override
    public void Counter(String counter) {
        if(Integer.parseInt(counter)!=0) {

            if (ikon.ikon.Language.isRTL()) {
                TabLayout.Tab tab = Shoping.tabLayout.getTabAt(1); // fourth tab
                View tabView = tab.getCustomView();
                TextView textView = tabView.findViewById(R.id.cartt);
                textView.setVisibility(View.VISIBLE);
                textView.setText(counter);
            } else {
                TabLayout.Tab tab = Shoping.tabLayout.getTabAt(3); // fourth tab
                View tabView = tab.getCustomView();
                TextView textView = tabView.findViewById(R.id.cartt);
                textView.setVisibility(View.VISIBLE);
                textView.setText(counter);
            }
        }else if(Integer.parseInt(counter)==0){
            if (ikon.ikon.Language.isRTL()) {
                TabLayout.Tab tab = Shoping.tabLayout.getTabAt(1); // fourth tab
                View tabView = tab.getCustomView();
                TextView textView = tabView.findViewById(R.id.cartt);
                textView.setVisibility(View.GONE);
            } else {
                TabLayout.Tab tab = Shoping.tabLayout.getTabAt(3); // fourth tab
                View tabView = tab.getCustomView();
                TextView textView = tabView.findViewById(R.id.cartt);
                textView.setVisibility(View.GONE);

            }
        }
    }

    @Override
    public void Error() {

    }
    @Override
    public void onStop() {
        super.onStop();
        Shoping.Visablty = true;
    }
}
