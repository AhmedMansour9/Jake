package ikon.ikon.Activites;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;
import java.util.Locale;

import ikon.ikon.Adapter.Products_id_Adapter;
import ikon.ikon.Fragments.OrderLocation;
import ikon.ikon.Model.Product_Orders;
import ikon.ikon.Model.ShowOrdersyid;
import ikon.ikon.PreSenter.CounterPresenter;
import ikon.ikon.PreSenter.ShowOrdersByid_Presenter;
import ikon.ikon.Viewes.ShowProductsView;
import jak.jaaak.R;

import static android.content.Context.MODE_PRIVATE;

public class ShowProductsId extends Fragment implements SwipeRefreshLayout.OnRefreshListener,ShowProductsView {
    View view;
    RecyclerView recyclerView;
    SharedPreferences shared;
    Products_id_Adapter adapter;
    String Lan;
    SwipeRefreshLayout mSwipeRefreshLayout;
    GridLayoutManager gridLayoutManager;
    CounterPresenter cn;
    ShowOrdersByid_Presenter listorderPresenter;
    String Id, Addresss, FirstName, price, Latitude, longetude;
    Button getlocationn;
    SharedPreferences Shared;
    String user;
    public ShowProductsId() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_show_products_id, container, false);
        listorderPresenter = new ShowOrdersByid_Presenter(getActivity(), this);
        shared = getActivity().getSharedPreferences("Language", MODE_PRIVATE);
        Lan = shared.getString("Lann", null);
        Shared=getActivity().getSharedPreferences("login",MODE_PRIVATE);
        user=Shared.getString("logggin",null);

        GetData();
        Recyclview();
        SwipRefresh();


        return view;
    }
    public void GetData(){
        Bundle bundle=getArguments();
        if(bundle!=null){
        Id=bundle.getString("id");
        }
    }

    public void Recyclview() {
        recyclerView = view.findViewById(R.id.recycler_ordersshoppingid);
        recyclerView.setHasFixedSize(true);
    }

    public void SwipRefresh() {
        mSwipeRefreshLayout = view.findViewById(R.id.swimmp);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                mSwipeRefreshLayout.setEnabled(true);

                    if (isRTL()) {
                        listorderPresenter.GetListOrder("ar",Id,user);
                    } else {
                        listorderPresenter.GetListOrder("en",Id,user);
                    }


            }
        });
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setEnabled(true);

            if (isRTL()) {
                listorderPresenter.GetListOrder("ar",Id,user);
            } else {
                listorderPresenter.GetListOrder("en",Id,user);
            }


    }

    public static boolean isRTL() {
        return isRTL(Locale.getDefault());
    }

    public static boolean isRTL(Locale locale) {
        final int directionality = Character.getDirectionality(locale.getDisplayName().charAt(0));
        return directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT ||
                directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC;
    }

    @Override
    public void GetListOrderShopping(List<Product_Orders> list) {
        adapter = new Products_id_Adapter(list,getActivity());
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
        gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        mSwipeRefreshLayout.setEnabled(false);

    }

    @Override
    public void Errorlistorder() {
        mSwipeRefreshLayout.setEnabled(false);
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

}