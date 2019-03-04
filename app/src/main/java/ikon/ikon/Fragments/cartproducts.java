package ikon.ikon.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ikon.ikon.Activites.Shoping;
import ikon.ikon.Adapter.Cart_Adapter;
import ikon.ikon.Language;
import ikon.ikon.Model.Cart;
import ikon.ikon.Model.Cart_Details;
import ikon.ikon.NetworikConntection;
import ikon.ikon.PreSenter.AddCart_Presenter;
import ikon.ikon.PreSenter.ShowCart_Presenter;
import ikon.ikon.Viewes.Cart_View;
import ikon.ikon.Viewes.Count_View;
import ikon.ikon.Viewes.ShowCart_View;
import jak.jaaak.R;

import static android.content.Context.MODE_PRIVATE;


public class cartproducts extends Fragment implements ShowCart_View,SwipeRefreshLayout.OnRefreshListener,Count_View,Cart_View {
    ShowCart_Presenter showCart_presenter;
    RecyclerView recyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    View view;
    Cart_Adapter cart_adapter;
    SharedPreferences Shared;
    String user;
//    AddCart_Presenter addCart;
    TextView T_Price,TotalPrice;
    Button order;
    Button requestorder;
    String Price;
    String id;
    RelativeLayout relatwo,rel;
    FrameLayout cartframe;
    NetworikConntection networikConntection;
    List<Cart> listss;
    TextView NoProducts;
    AddCart_Presenter addCart;
    Boolean cart=false;
    String Id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.activity_cartproducts, container, false);
        showCart_presenter=new ShowCart_Presenter(getContext(),this);
        Shared=getActivity().getSharedPreferences("login",MODE_PRIVATE);
        init();
        Recyclview();
        SwipRefresh();
        RequestOrder();
      return view;
    }


    public void init(){
        listss=new ArrayList<>();
        cartframe=view.findViewById(R.id.cartframe);
        NoProducts=view.findViewById(R.id.noproduct);
        networikConntection=new NetworikConntection(getActivity());
        requestorder=view.findViewById(R.id.requestorder);
        relatwo=view.findViewById(R.id.relatwo);
        rel=view.findViewById(R.id.rel);
        user=Shared.getString("logggin",null);
        T_Price=view.findViewById(R.id.T_Price);
//        TotalPrice=view.findViewById(R.id.Price);
        addCart=new AddCart_Presenter(getContext(),this);
//        order=view.findViewById(R.id.servicerequest);

    }
    public void RequestOrder(){
        requestorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(networikConntection.isNetworkAvailable(getContext())) {
                    if (listss != null) {
                        OrderLocation fragmen = new OrderLocation();
                        Bundle args = new Bundle();
                        args.putString("price", Price);

                        fragmen.setArguments(args);
                        getFragmentManager().beginTransaction()
                                .replace(R.id.cartframe, fragmen)
                                .addToBackStack(null)
                                .commitAllowingStateLoss();
                    } else {
                        Toast.makeText(getContext(),getResources().getString(R.string.noproducts), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Snackbar.make(cartframe,getResources().getString(R.string.internet),1500).show();
                }
            }
        });
    }
    public void Recyclview(){
        recyclerView = view.findViewById(R.id.recycler_Cart);
        recyclerView.setHasFixedSize(true);
    }

    public void SwipRefresh(){
        mSwipeRefreshLayout =  view.findViewById(R.id.swipe_contain);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                cart=true;
                if (networikConntection.isNetworkAvailable(getContext())) {
                    if (user != null) {
                        if (Language.isRTL()) {
                            mSwipeRefreshLayout.setRefreshing(true);
                            showCart_presenter.ShowCart("ar", user);
                        } else {
                            mSwipeRefreshLayout.setRefreshing(true);
                            showCart_presenter.ShowCart("en", user);
                        }
                    }else {
                        Snackbar.make(cartframe,getResources().getString(R.string.internet),1500).show();
                    }
                }
            }

        });
    }

    @Override
    public void ShowCart(List<Cart_Details> list) {
//            id=String.valueOf(list.get(0).getId());
//        listss=list;
        if(cart) {
            if (list != null) {
                cart_adapter = new Cart_Adapter(list, getContext());
                cart_adapter.count(this);
//                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);

                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(cart_adapter);
                mSwipeRefreshLayout.setRefreshing(false);

            }
            mSwipeRefreshLayout.setRefreshing(false);
            rel.setVisibility(View.VISIBLE);
            relatwo.setVisibility(View.VISIBLE);

        }
    }



    @Override
    public void ShowTotalprice(String price) {
         Price = price;
        T_Price.setText(price + "LE");
        rel.setVisibility(View.VISIBLE);
        relatwo.setVisibility(View.VISIBLE);
        mSwipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void Error() {
        mSwipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void NoProduct() {
        NoProducts.setVisibility(View.VISIBLE);
        rel.setVisibility(View.GONE);
        relatwo.setVisibility(View.GONE);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        cart=true;
        if(networikConntection .isNetworkAvailable(getActivity())){
            if (user != null) {
                if (Language.isRTL()) {
                    mSwipeRefreshLayout.setRefreshing(true);
                    showCart_presenter.ShowCart("ar", user);
                } else {
                    mSwipeRefreshLayout.setRefreshing(true);
                    showCart_presenter.ShowCart("en", user);
                }
            }
            else {
                Snackbar.make(cartframe,getResources().getString(R.string.internet),1500).show();
            }
        }
    }
    @Override
    public void count_plus(String id) {
        cart=false;
        if(networikConntection.isNetworkAvailable(getActivity())) {
//            mSwipeRefreshLayout.setRefreshing(true);
            if (Language.isRTL()) {
                addCart.UpdateCart("ar", user, id);
            } else {
                addCart.UpdateCart("en", user, id);
            }
        }else {
            Snackbar.make(cartframe,getResources().getString(R.string.internet),1500).show();
        }
    }

    @Override
    public void count_minus(String id) {
        cart=false;
        if(networikConntection.isNetworkAvailable(getActivity())) {
//            mSwipeRefreshLayout.setRefreshing(true);
            if (Language.isRTL()) {
                addCart.MinusCart("ar", user, id);
            } else {
                addCart.MinusCart("en", user, id);
            }
        }else {
            Snackbar.make(cartframe,getResources().getString(R.string.internet),1500).show();
        }

    }
    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible) {
            Shoping.Visablty = true;
        } else {

        }

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Shoping.Visablty = true;
    }

    @Override
    public void delete(String id,String poistion) {
        Id=poistion;
        if(networikConntection.isNetworkAvailable(getActivity())) {
            cart=true;
            mSwipeRefreshLayout.setRefreshing(true);
            if (Language.isRTL()) {
                addCart.Delete_toCart("ar", user, id);
            } else {
                addCart.Delete_toCart("en", user, id);
            }
        }else {
            Snackbar.make(cartframe,getResources().getString(R.string.internet),1500).show();
        }
    }

    @Override
    public void Success() {
            if (Language.isRTL()) {
//                mSwipeRefreshLayout.setRefreshing(true);
                showCart_presenter.ShowCart("ar", user);
            } else {
//                mSwipeRefreshLayout.setRefreshing(true);
                showCart_presenter.ShowCart("en", user);
            }

    }

    @Override
    public void Youhavethisproduct() {
//        Toast.makeText(getContext(), view.getResources().getString(R.string.productexist), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Failed() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void DeleteCart(String countity) {


        if(networikConntection.isNetworkAvailable(getActivity())) {
            if (Language.isRTL()) {
                mSwipeRefreshLayout.setRefreshing(true);
                showCart_presenter.ShowCart("ar", user);
            } else {
                mSwipeRefreshLayout.setRefreshing(true);
                showCart_presenter.ShowCart("en", user);
            }
        }else {
            Snackbar.make(cartframe,getResources().getString(R.string.internet),1500).show();
        }
        if(Integer.parseInt(countity)!=0) {

            if (ikon.ikon.Language.isRTL()) {
                TabLayout.Tab tab = Shoping.tabLayout.getTabAt(1); // fourth tab
                View tabView = tab.getCustomView();
                TextView textView = tabView.findViewById(R.id.cartt);
                textView.setVisibility(View.VISIBLE);
                textView.setText(countity);
            } else {
                TabLayout.Tab tab = Shoping.tabLayout.getTabAt(3); // fourth tab
                View tabView = tab.getCustomView();
                TextView textView = tabView.findViewById(R.id.cartt);
                textView.setVisibility(View.VISIBLE);
                textView.setText(countity);
            }
        }else if(Integer.parseInt(countity)==0){
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

}
