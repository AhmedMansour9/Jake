package ikon.ikon.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import ikon.ikon.Activites.Login;
import ikon.ikon.Activites.Shoping;
import ikon.ikon.Language;
import jak.jaaak.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class Setting extends Fragment {

    SharedPreferences.Editor Shared;

    public Setting() {
        // Required empty public constructor
    }
    RelativeLayout log_out;
    View view;
    TextView T_Language;
    SharedPreferences.Editor share;
    SharedPreferences shared;
    RelativeLayout relative_language,myOrders;
    SharedPreferences sharess;
    String user_Token;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_setting, container, false);
        log_out=view.findViewById(R.id.logout);
        T_Language=view.findViewById(R.id.T_Language);
        relative_language=view.findViewById(R.id.relative_language);
        myOrders=view.findViewById(R.id.myOrder);
        setLanguage();
        Shared=getActivity().getSharedPreferences("login",MODE_PRIVATE).edit();
        share=getActivity().getSharedPreferences("Language",MODE_PRIVATE).edit();
        sharess =getActivity().getSharedPreferences("login", MODE_PRIVATE);
        user_Token = sharess.getString("logggin", null);


        Log_Out();
        ChangeLanguage();

        myOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(user_Token!=null) {
                   Users_Orders details_product=new Users_Orders();
                   Bundle args = new Bundle();
                   args.putString("name","setting");
                   details_product.setArguments(args);
                   getFragmentManager().beginTransaction()
                           .add(R.id.setting_Frame, details_product )
                           .addToBackStack(null)
                           .commit();
               }else {
                   Toast.makeText(getActivity(), ""+getResources().getString(R.string.usernotlogin)
                           , Toast.LENGTH_SHORT).show();
                   Shared.putString("logggin",null);
                   Shared.apply();
                   startActivity(new Intent(getActivity(),Login.class));
                   getActivity().finish();

               }
            }
        });
        return view;
    }

    public void setLanguage(){
        if(Language.isRTL()){
            T_Language.setText("English");
        }else {
            T_Language.setText("عربي");
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

    public void ChangeLanguage(){
        relative_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(T_Language.getText().toString().equals("عربي")){
                    share.putString("Lann","ar");
                    share.commit();
                    startActivity(new Intent(getContext(),Shoping.class));

                    getActivity().finish();
                }
                else if(T_Language.getText().toString().equals("English")){
                    share.putString("Lann","en");
                    share.commit();
                    startActivity(new Intent(getContext(),Shoping.class));
                    getActivity().finish();

                }

            }
        });

    }
    public void Log_Out(){
        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Shared.putString("logggin",null);
                Shared.putString("fc",null);
                Shared.putString("name",null);
                Shared.putString("email",null);
                Shared.putString("phone",null);

                Shared.apply();
                startActivity(new Intent(getActivity(),Login.class));
                getActivity().finish();


            }
        });

    }
}
