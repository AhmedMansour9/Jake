package ikon.ikon.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import ikon.ikon.Activites.Shoping;
import ikon.ikon.PreSenter.ProfilePresenter;
import jak.jaaak.R;
import okhttp3.internal.cache.DiskLruCache;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class Profile extends Fragment  {


    public Profile() {
        // Required empty public constructor
    }
    SharedPreferences.Editor Sha;
    String userToken;
    EditText userName,T_ModileNumber,userRemaining;
    SharedPreferences prefs;
    ProfilePresenter profilePresenter;
    View view;
    RelativeLayout myOrders;
    TextView T_Name,E_Email;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;
    Button Edit_Profile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_profile, container, false);
        Sha=getActivity().getSharedPreferences("login",MODE_PRIVATE).edit();
        prefs=this.getActivity().getSharedPreferences( "login", Context.MODE_PRIVATE );
        userToken=prefs.getString( "logggin",null);
        Edit_Profile=view.findViewById(R.id.Edit_Profile);
//        profilePresenter=new ProfilePresenter( getContext(),this );
//        profilePresenter.getProfileData(userToken);
        init();
        OpenMyOrders();
        EditProfile();

        return view;
    }
    public void EditProfile(){
        Edit_Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                

            }
        });

    }


    public void init(){
        userName=view.findViewById( R.id.T_Name );
        E_Email=view.findViewById( R.id.E_Email );
        T_ModileNumber=view.findViewById( R.id.T_ModileNumber );
        myOrders=view.findViewById(R.id.myOrders);
        String google=prefs.getString("fc",null);

        if(google!=null) {
            mAuth = FirebaseAuth.getInstance();
            if (mAuth.getCurrentUser() != null) {
                String Name = mAuth.getCurrentUser().getDisplayName();
                String Email = mAuth.getCurrentUser().getEmail();
                userName.setText(Name);
                E_Email.setText(Email);

            }
        }
        else {
            String email = prefs.getString("email", null);
            String name = prefs.getString("name", null);
            String phone = prefs.getString("phone", null);
            userName.setText(name);
            E_Email.setText(email);
            T_ModileNumber.setText(phone);

        }
        }
    public void OpenMyOrders(){
        myOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Users_Orders details_product=new Users_Orders();
                Bundle args = new Bundle();
                args.putString("name","profile");
                details_product.setArguments(args);
                getFragmentManager().beginTransaction()
                        .add(R.id.Profile_Frame, details_product )
                        .addToBackStack(null)
                        .commit();

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

}
