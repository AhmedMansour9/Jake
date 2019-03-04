package ikon.ikon.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fourhcode.forhutils.FUtilsValidation;
import com.google.firebase.auth.FirebaseAuth;

import ikon.ikon.Activites.Login;
import ikon.ikon.Activites.Shoping;
import ikon.ikon.Model.UserRegister;
import ikon.ikon.PreSenter.Change_Password_Presenter;
import ikon.ikon.PreSenter.ProfilePresenter;
import ikon.ikon.Viewes.Edit_Profile_View;
import jak.jaaak.R;
import okhttp3.internal.cache.DiskLruCache;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class Profile extends Fragment  implements Edit_Profile_View {


    public Profile() {
        // Required empty public constructor
    }
    SharedPreferences.Editor Shared;
    SharedPreferences.Editor Sha;
    String userToken;
    EditText T_ModileNumber,userRemaining;
    SharedPreferences prefs;
    TextView E_Email;
    ProfilePresenter profilePresenter;
    View view;
    RelativeLayout myOrders;
    EditText T_Name,userName;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;
    Button Edit_Profile;
    Change_Password_Presenter change_password;
    ProgressBar progrossprofileedit;
    String email,name,phone;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_profile, container, false);
        Sha=getActivity().getSharedPreferences("login",MODE_PRIVATE).edit();
        prefs=this.getActivity().getSharedPreferences( "login", Context.MODE_PRIVATE );
        Shared=getActivity().getSharedPreferences("login",MODE_PRIVATE).edit();
        userToken=prefs.getString( "logggin",null);
        Edit_Profile=view.findViewById(R.id.Edit_Profile);
        progrossprofileedit=view.findViewById(R.id.progrossprofileedit);
        change_password=new Change_Password_Presenter(getContext(),this);
        init();
        OpenMyOrders();
        EditProfile();
        NameCahnges();
        PhoneChanges();


        return view;
    }
    public void PhoneChanges (){
        T_ModileNumber.addTextChangedListener(new TextWatcher() {
                                                  @Override
                                                  public void beforeTextChanged (CharSequence s,int start, int count,
                                                                                 int after){
                                                  }
                                                  @Override
                                                  public void onTextChanged ( final CharSequence s, int start, int before,
                                                                              int count){

                                                  }
                                                  @Override
                                                  public void afterTextChanged ( final Editable s){
                                                      phone = prefs.getString("phone", null);
                                                      if(phone!=null) {
                                                          if (!phone.equals(s.toString())) {
                                                              Edit_Profile.setEnabled(true);
                                                          }else {
                                                              Edit_Profile.setEnabled(false);
                                                          }
                                                      }
                                                  }
                                              }

        );
    }
    public void NameCahnges(){
        userName.addTextChangedListener(new TextWatcher() {
                                            @Override
                                            public void beforeTextChanged (CharSequence s,int start, int count,
                                                                           int after){
                                            }
                                            @Override
                                            public void onTextChanged ( final CharSequence s, int start, int before,
                                                                        int count){

                                            }
                                            @Override
                                            public void afterTextChanged ( final Editable s){
                                                String google=prefs.getString("fc",null);
                                                if(google!=null) {
                                                    mAuth = FirebaseAuth.getInstance();
                                                    if (mAuth.getCurrentUser() != null) {
                                                        name = mAuth.getCurrentUser().getDisplayName();
                                                    }
                                                }
                                                else {
                                                    name = prefs.getString("name", null);
                                                }
                                                if(name!=null) {
                                                    if (!name.equals(s.toString())) {
                                                        Edit_Profile.setEnabled(true);
                                                    }else {
                                                        Edit_Profile.setEnabled(false);
                                                    }
                                                }

                                            }
                                        }

        );
    }
    public void EditProfile(){
        Edit_Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FUtilsValidation.isEmpty(userName,"");
                FUtilsValidation.isEmpty(T_ModileNumber,"");

              if(!userName.getText().equals("")&&!T_ModileNumber.getText().equals("")) {
                  UserRegister user = new UserRegister();
                  user.setFirstName(userName.getText().toString());
                  user.setEmail(E_Email.getText().toString());
                  user.setPhone(T_ModileNumber.getText().toString());
                  user.setUsertoken(userToken);
                  progrossprofileedit.setVisibility(View.VISIBLE);
                  change_password.changeuser(user);
              }


            }
        });

    }


    public void init(){
        userName=view.findViewById( R.id.T_Name );
        E_Email=view.findViewById( R.id.E_Email );
        T_ModileNumber=view.findViewById( R.id.T_ModileNumber );
        myOrders=view.findViewById(R.id.myOrde);
        String google=prefs.getString("fc",null);
        Edit_Profile.setEnabled(false);
        if(google!=null) {
            mAuth = FirebaseAuth.getInstance();
            if (mAuth.getCurrentUser() != null) {
                name = mAuth.getCurrentUser().getDisplayName();
                String Email = mAuth.getCurrentUser().getEmail();
                userName.setText(name);
                E_Email.setText(Email);

            }
        }
        else {
             email = prefs.getString("email", null);
             name = prefs.getString("name", null);
             phone = prefs.getString("phone", null);
            userName.setText(name);
            E_Email.setText(email);
            T_ModileNumber.setText(phone);

        }
        }
    public void OpenMyOrders(){
        myOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(userToken!=null) {
                    Users_Orders details_product=new Users_Orders();
                    Bundle args = new Bundle();
                    args.putString("name","profile");
                    details_product.setArguments(args);
                    getFragmentManager().beginTransaction()
                            .add(R.id.Profile_Frame, details_product )
                            .addToBackStack(null)
                            .commit();
                }else {
                    Toast.makeText(getActivity(), ""+getResources().getString(R.string.usernotlogin)
                            , Toast.LENGTH_SHORT).show();
                    Shared.putString("logggin",null);
                    Shared.apply();
                    startActivity(new Intent(getActivity(), Login.class));
                    getActivity().finish();

                }

            }
        });
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
    public void ProfileUpdted() {
        Snackbar.make(view,getResources().getText(R.string.updatedusersuccess),1500).show();
        Shared.putString("phone",T_ModileNumber.getText().toString());
        Shared.putString("name",userName.getText().toString());
        Shared.apply();
        Edit_Profile.setEnabled(false);
        progrossprofileedit.setVisibility(View.GONE);
    }

    @Override
    public void showError() {

        progrossprofileedit.setVisibility(View.GONE);
    }
}
