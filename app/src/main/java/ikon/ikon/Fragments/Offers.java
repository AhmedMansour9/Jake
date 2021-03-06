package ikon.ikon.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

import ikon.ikon.Activites.Shoping;
import ikon.ikon.Adapter.Offers_adapter;
import ikon.ikon.Adapter.Products_Adapter;
import ikon.ikon.Language;
import ikon.ikon.Model.Count;
import ikon.ikon.Model.DetailsProducts;
import ikon.ikon.Model.Product_Detail;
import ikon.ikon.Model.Product_Details;
import ikon.ikon.NetworikConntection;
import ikon.ikon.PreSenter.AddCart_Presenter;
import ikon.ikon.PreSenter.CounterPresenter;
import ikon.ikon.PreSenter.GetPhonesPresenter;
import ikon.ikon.PreSenter.Get_Counter_Presenter;
import ikon.ikon.PreSenter.Products_Presenter;
import ikon.ikon.Viewes.Cart_View;
import ikon.ikon.Viewes.Counter_View;
import ikon.ikon.Viewes.OnBackPressed;
import ikon.ikon.Viewes.Products_View;
import jak.jaaak.R;

import static android.content.Context.MODE_PRIVATE;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class Offers extends Fragment implements Counter_View,Cart_View,DetailsProducts,Products_View,SwipeRefreshLayout.OnRefreshListener,Count {


    public Offers() {
        // Required empty public constructor
    }

    EditText product;
    GetPhonesPresenter phons;
    View view;
    RecyclerView recyclerView;
    SharedPreferences shared;
    Offers_adapter adapter;
    String Lan;
    SwipeRefreshLayout mSwipeRefreshLayout;
    GridLayoutManager gridLayoutManager;
    CounterPresenter cn;
    NetworikConntection checkNetWork;
    RelativeLayout RelativePhone;
    SharedPreferences share;
    Products_Presenter products_presenter;
    Products_Adapter products_adapter;
    String id;
    ImageView Search;
    AddCart_Presenter addCart;
    SharedPreferences prefs;
    String userToken;
    ProgressBar ProgrossSpare;
    Get_Counter_Presenter counter_presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_offers, container, false);
        counter_presenter=new Get_Counter_Presenter(getApplicationContext(),this);
        product = view.findViewById(R.id.findyourproduct);
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        shared = getActivity().getSharedPreferences("Language", MODE_PRIVATE);
        RelativePhone = view.findViewById(R.id.RelativePhone);
        Lan = shared.getString("Lann", null);
        cn = new CounterPresenter(getContext(), this);
        checkNetWork = new NetworikConntection(getActivity());

        Search = view.findViewById(R.id.Search);
        Recyclview();
        SwipRefresh();
        products_presenter = new Products_Presenter(getContext(), this);




        return view;
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible) {
         Shoping.Visablty=true;
        }
        else
        {

        }

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Shoping.Visablty = true;
    }
    public void Recyclview() {
        prefs=this.getActivity().getSharedPreferences( "login", Context.MODE_PRIVATE );
        userToken=prefs.getString( "logggin",null);
        addCart=new AddCart_Presenter(getContext(),this);
        ProgrossSpare=view.findViewById(R.id.Progrossoffer);

        recyclerView = view.findViewById(R.id.recycler_phones);
        recyclerView.setHasFixedSize(true);
    }

//    public void RecycleviewSerach() {
//        product.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                adapter.getFilter().filter(charSequence);
//                adapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//            }
//        });
//    }

    public void SwipRefresh(){
        mSwipeRefreshLayout =  view.findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
//                if(checkNetWork.isNetworkAvailable(getActivity())){
                    mSwipeRefreshLayout.setEnabled(true);
                    mSwipeRefreshLayout.setRefreshing(true);
                    if (isRTL()) {
                        products_presenter.GetSlashProduct("ar");
                    } else {
                        products_presenter.GetSlashProduct("en");
                    }

//                }else {
//                    Snackbar.make(RelativePhone,R.string.internet,1000).show();
//                }
            }
        });
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
    public void onRefresh() {
//        if(checkNetWork.isNetworkAvailable(getActivity())) {
            mSwipeRefreshLayout.setEnabled(true);

            if (isRTL()) {
                products_presenter.GetSlashProduct("ar");
            } else {
                products_presenter.GetSlashProduct("en");
            }

//        }else {
//            Snackbar.make(RelativePhone,R.string.internet,1000).show();
//        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void count(String con) {
//        Navigation.T_Cart.setText(con);
//        Shoping.T_Cartshop.setText(con);

    }

    @Override
    public void Products(List<Product_Detail> list) {
        adapter = new Offers_adapter(list,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        adapter.setOnClicklistner(this);
//        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        gridLayoutManager = new GridLayoutManager(getActivity(), 2);
//        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        mSwipeRefreshLayout.setRefreshing(false);
//        RecycleviewSerach();

    }

    @Override
    public void ErrorProducts() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void ProductsFlash(List<Product_Detail> list) {

    }

    @Override
    public void ErrorProductsFlash() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void EmptyProductsFlash() {
        mSwipeRefreshLayout.setRefreshing(false);
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
                .replace(R.id.RelativeOffer, details_product )
                .addToBackStack("details_offers")
                .commit();

    }


    @Override
    public void CartDetails(Product_Details list) {
        ProgrossSpare.setVisibility(View.VISIBLE);
        if(Language.isRTL()){
            addCart.Add_toCart("ar",userToken,list.getProductId());
        }else {
            addCart.Add_toCart("en",userToken,list.getProductId());
        }
    }

    @Override
    public void Success() {
        Toast.makeText(getContext(),getResources().getString(R.string.cartsuccesfull), Toast.LENGTH_SHORT).show();
        ProgrossSpare.setVisibility(View.GONE);

    }

    @Override
    public void Youhavethisproduct() {
        Toast.makeText(getContext(), view.getResources().getString(R.string.productexist), Toast.LENGTH_SHORT).show();
        counter_presenter.GetCounter(userToken,"en");
        ProgrossSpare.setVisibility(View.GONE);
    }

    @Override
    public void Failed() {
        ProgrossSpare.setVisibility(View.GONE);
    }

    @Override
    public void DeleteCart(String countity) {

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



}