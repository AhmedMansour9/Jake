package ikon.ikon.Fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.fourhcode.forhutils.FUtilsValidation;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ikon.ikon.Activites.RequestedSuccessfully;
import ikon.ikon.Activites.Shoping;
import ikon.ikon.Model.Filter_mandob;
import ikon.ikon.Model.SalesMan;
import ikon.ikon.PreSenter.Order_Presenter;
import ikon.ikon.PreSenter.SalesMan_Presenter;
import ikon.ikon.Viewes.OrderService_View;
import ikon.ikon.Viewes.Sales_View;
import jak.jaaak.R;

import static android.content.Context.MODE_PRIVATE;


public class OrderLocation extends Fragment implements Sales_View,OrderService_View,OnMapReadyCallback,
        com.google.android.gms.location.LocationListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

//    String T_FName,T_LName,T_Address,T_Latitude,T_Longetude;

    TextView FName,LName;
    final int REQUEST_LOCATION_CODE = 99;
    public static double latitude,longitude;
    GoogleApiClient mGoogleApiClient;
    LocationRequest locationReques;
    private GoogleMap googleMap;
    LatLng latyy;
    LatLng laty;
    Button RequestOrder;
    EditText T_Name,T_Phone,T_Address,T_City,T_Email,T_Notes;
    String Name,Phone,Address,City,Email,Notes;
    Order_Presenter order_service;
    ProgressBar progressBarorder;
    View view;
    SharedPreferences Shared;
    SharedPreferences prefs;
    String user;
    TextView T_Price;
    String price;
    SalesMan_Presenter salesMan_presenter;
    List<Filter_mandob> filterPlaces=new ArrayList<>();
    String mandobid;
    String userToken;
    private FirebaseAuth mAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.activity_ordershopping, container, false);
        order_service=new Order_Presenter(getActivity(),this);
        salesMan_presenter=new SalesMan_Presenter(getActivity(),this);
        prefs=this.getActivity().getSharedPreferences( "login", Context.MODE_PRIVATE );
        userToken=prefs.getString( "logggin",null);

        T_Price=view.findViewById(R.id.T_Price);
        Shared=getActivity().getSharedPreferences("login",MODE_PRIVATE);
        user=Shared.getString("logggin",null);
        salesMan_presenter.ShowSales(user);
        Bundle bundle=getArguments();
        if(bundle!=null){
             price=bundle.getString("price");
            T_Price.setText(price);
        }
        init();
          Reservee();


        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.maps);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
        checkLocationPermission();

    return view;
    }
    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_LOCATION_CODE);



            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_LOCATION_CODE);
            }
            return false;
        } else {
            return true;
        }
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

    public void init(){
        T_Name=view.findViewById(R.id.T_Name);
        T_Phone=view.findViewById(R.id.T_Phone);
        T_Address=view.findViewById(R.id.T_Address);
        T_City=view.findViewById(R.id.T_City);
        T_Email=view.findViewById(R.id.T_Email);
        T_Notes=view.findViewById(R.id.T_Notes);
        RequestOrder=view.findViewById(R.id.RequestOrder);
        progressBarorder=view.findViewById(R.id.progressBarorder);
        String google=prefs.getString("fc",null);
        if(google!=null) {
            mAuth = FirebaseAuth.getInstance();
            if (mAuth.getCurrentUser() != null) {
                String Name = mAuth.getCurrentUser().getDisplayName();
                String Email = mAuth.getCurrentUser().getEmail();
                T_Name.setText(Name);
                T_Email.setText(Email);

        }

        }else {
            String email = prefs.getString("email", null);
            String name = prefs.getString("name", null);
            String phone = prefs.getString("phone", null);
            T_Email.setText(email);
            T_Name.setText(name);
            T_Phone.setText(phone);

        }
    }
    public void Reservee(){
        RequestOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(latitude!=0) {

                    Name = T_Name.getText().toString();
                    Phone = T_Phone.getText().toString();
                    Address = T_Address.getText().toString();
                    City = T_City.getText().toString();
                    Email = T_Email.getText().toString();
                    Notes = T_Notes.getText().toString();


                    FUtilsValidation.isEmpty(T_Name, "");
                    FUtilsValidation.isEmpty(T_Phone, "");
                    FUtilsValidation.isEmpty(T_Address, "");
                    FUtilsValidation.isEmpty(T_City, "");
                    FUtilsValidation.isEmpty(T_Email, "");
                      ValidateEmail();

                    if (!T_Name.getText().toString().equals("") &&
                            !T_Phone.getText().toString().equals("") && !T_Address.getText().toString().equals("")
                            && !T_City.getText().toString().equals("") &&
                            (!T_Email.getText().toString().equals(""))&&ValidateEmail()) {

                        progressBarorder.setVisibility(View.VISIBLE);
                        order_service.Order_Service(user, "en", Phone, City, Address, Name, Notes,mandobid
                        ,String.valueOf(latitude),String.valueOf(longitude));
                    }

                }else {
                    buildGoogleapiclint();
                      checkLocationPermission();
                }
            }
        });

    }
    private Boolean ValidateEmail(){
        String EMAIL=T_Email.getText().toString().trim();
        if (EMAIL.isEmpty()||!isValidEmail(EMAIL)){
            Toast.makeText(getActivity(), ""+getResources().getString(R.string.insertemail), Toast.LENGTH_SHORT).show();;


            return false;
        }else if(!T_Email.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
            Toast.makeText(getActivity(), ""+getResources().getString(R.string.insertemail), Toast.LENGTH_SHORT).show();;
            return false;
        }
        return true;
    }
    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationReques = new LocationRequest();
        locationReques.setFastestInterval(10000);

        locationReques.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        int permissionCheck = ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION);

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, locationReques, this);
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                    .addLocationRequest(locationReques);


            SettingsClient client = LocationServices.getSettingsClient(getActivity());
            Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());

            task.addOnFailureListener(getActivity(), new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    if (e instanceof ResolvableApiException) {
                        try {
                            ResolvableApiException resolvable = (ResolvableApiException) e;
                            resolvable.startResolutionForResult(getActivity(),
                                    REQUEST_LOCATION_CODE);
                        } catch (IntentSender.SendIntentException sendEx) {
                        }
                    }
                }
            });
        }
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

        latitude = location.getLatitude();
        longitude = location.getLongitude();



    }
    @Override
    public void onStop() {
        super.onStop();
//        if (mGoogleApiClient.isConnected()) {
//            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
//            mGoogleApiClient.disconnect();
//        }

    }

    @Override
    public void onMapReady(GoogleMap googleMas) {
        googleMap = googleMas;
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        buildGoogleapiclint();



    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        final LocationSettingsStates states = LocationSettingsStates.fromIntent(data);
        switch (requestCode) {
            case REQUEST_LOCATION_CODE:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        buildGoogleapiclint();
                        salesMan_presenter.ShowSales(user);

                        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity()
                                , Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }

                        break;
                    case Activity.RESULT_CANCELED:

                        break;
                    default:
                        break;
                }
                break;
        }

    }
    private synchronized void buildGoogleapiclint() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }


    @Override
    public void Orders(String orderid) {
        Intent inty=new Intent(getActivity(), RequestedSuccessfully.class);
        inty.putExtra("id",orderid);
        inty.putExtra("price",price);
        startActivity(inty);
        getActivity().finish();
        progressBarorder.setVisibility(View.GONE);
    }

    @Override
    public void Error() {

        progressBarorder.setVisibility(View.GONE);
    }

    @Override
    public void GetSalesMan(List<SalesMan> a) {

        if (this.latitude != 0) {

            for (int i = 0; i < a.size(); i++) {
                Location selected_location = new Location("locationA");
                selected_location.setLatitude(this.latitude);
                selected_location.setLongitude(this.longitude);
                Location near_locations = new Location("locationB");
                near_locations.setLatitude(Double.parseDouble(a.get(i).getLat()));
                near_locations.setLongitude(Double.parseDouble(a.get(i).getLag()));
                double distance = selected_location.distanceTo(near_locations);
                double distan=distance/1000;
                int distanceKm=(int)distan;
                Filter_mandob filter_places = new Filter_mandob();

                filter_places.setId(String.valueOf(a.get(i).getMyid()));
                filter_places.setDestance(distanceKm);

                filterPlaces.add(filter_places);

            }
            Collections.sort(filterPlaces, new Comparator<Filter_mandob>() {

                @Override
                public int compare(Filter_mandob o1, Filter_mandob o2) {
                    return Double.compare(o1.getDestance(), o2.getDestance());
                }
            });
            Integer distancee=filterPlaces.get(0).getDestance();
            if(distancee<50){
                mandobid=filterPlaces.get(0).getId();
            }

        }
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 99: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do
                    // .
                    if (ContextCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        buildGoogleapiclint();
                        salesMan_presenter.ShowSales(user);
                    }

                } else {
                }
                return;
            }
        }
    }
}
