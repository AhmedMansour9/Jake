package ikon.ikon.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import ikon.ikon.Activites.Shoping;
import ikon.ikon.Adapter.Banner_Adapter;
import ikon.ikon.Adapter.Categories_Adapter;
import ikon.ikon.Adapter.Products_Adapter;
import ikon.ikon.Adapter.flash_Adapter;
import ikon.ikon.Model.Banner;
import ikon.ikon.Model.Categories;
import ikon.ikon.Model.DetailsProducts;
import ikon.ikon.Model.Product_Detail;
import ikon.ikon.Model.Product_Details;
import ikon.ikon.NetworikConntection;
import ikon.ikon.PreSenter.BannerPresenter;
import ikon.ikon.PreSenter.Categories_Presenter;
import ikon.ikon.PreSenter.Products_Presenter;
import ikon.ikon.Viewes.BannerView;
import ikon.ikon.Viewes.Categories_View;
import ikon.ikon.Viewes.Product_id;
import ikon.ikon.Viewes.Products_View;
import jak.jaaak.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment implements DetailsProducts,BannerView ,Product_id
        ,Products_View,Categories_View,SwipeRefreshLayout.OnRefreshListener {


    public Home() {
        // Required empty public constructor
    }
    Banner_Adapter banerAdapter;
    Categories_Adapter categories_adapter;
    flash_Adapter flash_adapt;
    RecyclerView recyclerVie;
    Categories_Presenter categories;
    BannerPresenter baner;
    int position;
    List<Banner> banne=new ArrayList<>();
    Boolean end;
    LinearLayoutManager linearLayoutManager;
    private RecyclerView rv_autoScroll,recyclerflash;
    RecyclerView recyclerView,recycleBanner,recycleProducts;
    SwipeRefreshLayout mSwipeRefreshLayout;
    View view;
    NetworikConntection networikConntection;
    Products_Presenter products_presenter;
    Products_Adapter products_adapter;
    RelativeLayout rela_five,rela_four;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home, container, false);
        baner=new BannerPresenter(getContext(),this);
        baner.GetBanner("en");
        rela_four=view.findViewById(R.id.rela_four);
        rela_five=view.findViewById(R.id.rela_five);
        networikConntection=new NetworikConntection(getActivity());
        categories=new Categories_Presenter(getContext(),this);
        products_presenter=new Products_Presenter(getContext(),this);
        Recyclview();
        SwipRefresh();
        return view;
    }

    @Override
    public void id(String id,String name) {
        Phones details_product=new Phones();
        Bundle args = new Bundle();
        args.putString("id",id);
        args.putString("name",name);
        details_product.setArguments(args);
        getFragmentManager().beginTransaction()
                .add(R.id.rela, details_product )
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void Categories(List<Categories> list) {
        categories_adapter = new Categories_Adapter(list,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(linearLayoutManager);
        categories_adapter.setOnClicklistner(this);
        recyclerView.setAdapter(categories_adapter);
        mSwipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void ErrorCategories() {

    }

    @Override
    public void onRefresh() {
        if (networikConntection.isNetworkAvailable(getContext())) {
            if (ikon.ikon.Language.isRTL()) {
                mSwipeRefreshLayout.setRefreshing(true);
                categories.GetCategories("ar");
                baner.GetBanner("ar");
                products_presenter.GetFeatureProduct("ar","38");
                products_presenter.GetFeatureProductFlash("ar");

            } else {
                mSwipeRefreshLayout.setRefreshing(true);
                categories.GetCategories("en");
                baner.GetBanner("en");
                products_presenter.GetFeatureProduct("en","38");
                products_presenter.GetFeatureProductFlash("en");
            }
        } else {
//                Snackbar.make(rela,getResources().getString(R.string.internet),1500).show();
        }

    }

    @Override
    public void Products(List<Product_Detail> list) {
        products_adapter = new Products_Adapter(list, getContext());
        products_adapter.setOnClicklistner(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recycleProducts.setLayoutManager(gridLayoutManager);
        recycleProducts.setAdapter(products_adapter);
        mSwipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void ErrorProducts() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void ProductsFlash(List<Product_Detail> list) {
        flash_adapt = new flash_Adapter(list, getContext());
        flash_adapt.setOnClicklistner(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerflash.setLayoutManager(linearLayoutManager);
        recyclerflash.setAdapter(flash_adapt);
        mSwipeRefreshLayout.setRefreshing(false);
        rela_five.setVisibility(View.VISIBLE);
        rela_four.setVisibility(View.VISIBLE);
    }

    @Override
    public void ErrorProductsFlash() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void EmptyProductsFlash() {

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
    public void ProductsDetails(Product_Details list) {

        Details_Product details_product=new Details_Product();
        Bundle args = new Bundle();
        args.putString("address",list.getAddressVendor());
        args.putString("image",list.getProductImage());
        args.putString("id",list.getProductId());
        args.putString("productname",list.getProductName());
        args.putString("price",list.getPrice());
        args.putString("model",list.getModelProduct());
        args.putString("categoryname",list.getCategoryName());
        args.putString("descrip",list.getDescrption());
        details_product.setArguments(args);
        getFragmentManager().beginTransaction()
                .add(R.id.rela, details_product )
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void CartDetails(Product_Details list) {

    }

    private class AutoScrollTask extends TimerTask {
        @Override
        public void run() {
            if (position == banne.size()) {
                end = true;
            } else if (position == 0) {
                end = false;
            }
            if (!end) {
                position++;
                rv_autoScroll.smoothScrollToPosition(position);
            } else {
                position--;
                rv_autoScroll.smoothScrollToPosition(position);
            }

        }
    }
    public static boolean isRTL(Locale locale) {
        final int directionality = Character.getDirectionality(locale.getDisplayName().charAt(0));
        return directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT ||
                directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC;
    }

    @Override
    public void getBanner(List<Banner> banners) {
        banne=banners;
        banerAdapter = new Banner_Adapter(banners,getContext());
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_autoScroll.setLayoutManager(linearLayoutManager);
        rv_autoScroll.setAdapter(banerAdapter);

        if(banne.size()>1) {
            Timer timer = new Timer();
            timer.scheduleAtFixedRate( new AutoScrollTask(), 1000, 2000);
        }

    }
    public static boolean isRTL() {
        return isRTL(Locale.getDefault());
    }

    @Override
    public void Errorbaner() {

    }
    public void Recyclview(){
        recyclerView = view.findViewById(R.id.recycler_Categroies);
        recycleProducts=view.findViewById(R.id.recycler_Products);
        rv_autoScroll = view.findViewById(R.id.recycler_banner2);
        recyclerflash=view.findViewById(R.id.recycler_Prodductoffer);
        rv_autoScroll.setHasFixedSize(true);

        recyclerView.setHasFixedSize(true);
    }
    public void SwipRefresh() {
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_Categories);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setEnabled(true);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                if (networikConntection.isNetworkAvailable(getContext())) {
                    if (ikon.ikon.Language.isRTL()) {
                        mSwipeRefreshLayout.setRefreshing(true);
                        categories.GetCategories("ar");
                        baner.GetBanner("ar");
                        products_presenter.GetFeatureProduct("ar","38");
                        products_presenter.GetFeatureProductFlash("en");
                    } else {
                        mSwipeRefreshLayout.setRefreshing(true);
                        categories.GetCategories("en");
                        baner.GetBanner("en");
                        products_presenter.GetFeatureProductsss("en");
                        products_presenter.GetFeatureProductFlash("ar");
                    }
                } else {
//                Snackbar.make(rela,getResources().getString(R.string.internet),1500).show();
                }
            }

        });

    }


}
