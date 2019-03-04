package ikon.ikon.Activites;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import ikon.ikon.*;
import ikon.ikon.Fragments.Home;
import ikon.ikon.Fragments.Offers;
import ikon.ikon.Fragments.OrderLocation;
import ikon.ikon.Fragments.Profile;
import ikon.ikon.Fragments.Setting;
import ikon.ikon.Fragments.cartproducts;
import ikon.ikon.Model.Cart;
import ikon.ikon.PreSenter.Get_Counter_Presenter;
import ikon.ikon.Viewes.Counter_View;
import ikon.ikon.Viewes.OnBackPressed;
import jak.jaaak.R;

public class Shoping extends AppCompatActivity  implements Counter_View {
    public static TabLayout tabLayout;
    private ViewPager viewPager;
   public static TextView T_Cartshop;
    private List<Cart> liscart=new LinkedList<>();
    ImageView btncartshop;
    SharedPreferences share;
    SharedPreferences sha;
    SharedPreferences shaLan;
    public static TextView T_Title;
    Get_Counter_Presenter counter_presenter;
    SharedPreferences Shared;
    TextView textcounter;
    String user;
    View view3;
    Offers offers;
    public static Boolean Visablty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shaLan=getSharedPreferences("Language",MODE_PRIVATE);
        String Lan=shaLan.getString("Lann",null);
        Shared=getSharedPreferences("login",MODE_PRIVATE);
        user=Shared.getString("logggin",null);

        if(Lan!=null) {
            Locale locale = new Locale(Lan);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());

        }
        setContentView(R.layout.activity_shoping);
        viewPager = findViewById(R.id.viewpager);
        counter_presenter=new Get_Counter_Presenter(getApplicationContext(),this);
        counter_presenter.GetCounter(user,"en");
        share=getSharedPreferences("count",MODE_PRIVATE);
        setupViewPager(viewPager);
        view3 = getLayoutInflater().inflate(R.layout.iconmycart, null);
        textcounter=view3.findViewById(R.id.cartt);

//        String count=share.getString("count",null);
//        if(count!=null){
//            T_Cartshop.setText(count);
//            T_Cartshop.setBackgroundResource(R.drawable.circlecart);
//        }

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(
                ColorStateList.valueOf(getResources().getColor(R.color.White)));
//        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#fffc00"));
              tabLayout.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#fffc00"));

        tabLayout.setSelectedTabIndicatorHeight((int) (5 * getResources().getDisplayMetrics().density));
        tabLayout.setRotationX(180);
        tabLayout.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#fffc00"));
        tabLayout.setSelectedTabIndicatorHeight((int) (5 * getResources().getDisplayMetrics().density));
        setupTabIcons();

        if(isRTL()) {
            tabLayout.getTabAt(4).select();
        }else {
            tabLayout.getTabAt(0).select();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            tabLayout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }
      onSelectedTab();

    }

   public void onSelectedTab(){
       tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
           @Override
           public void onTabSelected(TabLayout.Tab tab) {

               FragmentManager fm = getSupportFragmentManager(); // or 'getSupportFragmentManager();'
               int count = fm.getBackStackEntryCount();
               if(count!=0) {
                   for (int i = 0; i < count; ++i) {
                       fm.popBackStack();
                   }
               }
//                switch(tab.getPosition()) {
               viewPager.setCurrentItem(tab.getPosition());
           }

           @Override
           public void onTabUnselected(TabLayout.Tab tab) {

           }

           @Override
           public void onTabReselected(TabLayout.Tab tab) {
               FragmentManager fm = getSupportFragmentManager(); // or 'getSupportFragmentManager();'
               int count = fm.getBackStackEntryCount();
               if(count!=0) {
                   for (int i = 0; i < count; ++i) {
                       fm.popBackStack();
                   }
               }
           }
       });
   }
    public static boolean isRTL(Locale locale) {
        final int directionality = Character.getDirectionality(locale.getDisplayName().charAt(0));
        return directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT ||
                directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC;
    }
//    @Override
//    public void onBackPressed() {
//        startActivity(new Intent(Shoping.this,Navigation.class));
//        finish();
//    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());


        if (isRTL()) {
            adapter.addFragment(new Setting(), getResources().getString(R.string.more));
            adapter.addFragment(new cartproducts(), getResources().getString(R.string.mycart));
            adapter.addFragment(new Offers(),getResources().getString(R.string.offers));
            adapter.addFragment(new Profile(),getResources().getString(R.string.profile));
            adapter.addFragment(new Home(),getResources().getString(R.string.home));

        } else {
            // The view has LTR layout
            adapter.addFragment(new Home(),getResources().getString(R.string.home));
            adapter.addFragment(new Profile(),getResources().getString(R.string.profile));
            adapter.addFragment(new Offers(),getResources().getString(R.string.offers));
            adapter.addFragment(new cartproducts(), getResources().getString(R.string.mycart));
            adapter.addFragment(new Setting(), getResources().getString(R.string.more));

        }

        //phones //Accessores // Sparts

//        viewPager.setCurrentItem(adapter.getCount() - 1);
        viewPager.setAdapter(adapter);
    }
    public static boolean isRTL() {
        return isRTL(Locale.getDefault());
    }

    private void setupTabIcons() {
        View view1 = getLayoutInflater().inflate(R.layout.iconhome, null);
        View view2 = getLayoutInflater().inflate(R.layout.iconprofile, null);
        View view3 = getLayoutInflater().inflate(R.layout.iconoffers, null);
        View view4 = getLayoutInflater().inflate(R.layout.iconmycart, null);
        View view5 = getLayoutInflater().inflate(R.layout.iconsetting, null);

        if(isRTL()){
            tabLayout.getTabAt(0).setCustomView(view5);
            tabLayout.getTabAt(1).setCustomView(view4);
            tabLayout.getTabAt(2).setCustomView(view3);
            tabLayout.getTabAt(3).setCustomView(view2);
            tabLayout.getTabAt(4).setCustomView(view1);
        }else {
            tabLayout.getTabAt(0).setCustomView(view1);
            tabLayout.getTabAt(1).setCustomView(view2);
            tabLayout.getTabAt(2).setCustomView(view3);
            tabLayout.getTabAt(3).setCustomView(view4);
            tabLayout.getTabAt(4).setCustomView(view5);
        }
    }

    @Override
    public void Counter(String counter) {
        if(Integer.parseInt(counter)!=0) {

            if (ikon.ikon.Language.isRTL()) {
                TabLayout.Tab tab = tabLayout.getTabAt(1); // fourth tab
                View tabView = tab.getCustomView();
                TextView textView = tabView.findViewById(R.id.cartt);
                textView.setVisibility(View.VISIBLE);
                textView.setText(counter);
            } else {
                TabLayout.Tab tab = tabLayout.getTabAt(3); // fourth tab
                View tabView = tab.getCustomView();
                TextView textView = tabView.findViewById(R.id.cartt);
                textView.setVisibility(View.VISIBLE);
                textView.setText(counter);
            }
        }
    }

    @Override
    public void Error() {

    }


    static  class Adapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }


        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // TODO Auto-generated method stub
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        List<Fragment> listOfFragments =getSupportFragmentManager().getFragments();

        if(listOfFragments.size()>=1){
            for (Fragment fragment : listOfFragments) {
                if(fragment instanceof OrderLocation){
                    fragment.onActivityResult(requestCode, resultCode, data);
                }
            }
        }


        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                fragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }
    }
    @Override
    public void onBackPressed() {

        //get current tab index.
        int index = tabLayout.getSelectedTabPosition();
        if(isRTL()){
            if(Visablty) {
                if (index != 4) {
                    tabLayout.getTabAt(4).select();
                } else {
                    super.onBackPressed();
                }
            }else {
                super.onBackPressed();
            }
        } else {
            if(Visablty) {
                if (index != 0) {
                    tabLayout.getTabAt(0).select();
                } else {
                    super.onBackPressed();
                }
            }else {
                super.onBackPressed();
            }
        }

                }




}