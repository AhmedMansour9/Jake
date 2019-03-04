package ikon.ikon.Activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

//import com.facebook.AccessToken;
//import com.facebook.CallbackManager;
//import com.facebook.FacebookCallback;
//import com.facebook.FacebookException;
//import com.facebook.GraphRequest;
//import com.facebook.GraphResponse;
//import com.facebook.login.LoginManager;
//import com.facebook.login.LoginResult;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Locale;

import ikon.ikon.NetworikConntection;
import ikon.ikon.Model.UserRegister;
import ikon.ikon.PreSenter.RegisterFace_Presenter;
import ikon.ikon.PreSenter.LoginPresenter;
import ikon.ikon.PreSenter.Registergoogle;
import ikon.ikon.Viewes.RegisterFaceView;
import ikon.ikon.Viewes.LoginView;
import ikon.ikon.Viewes.RegistergoogleView;
import jak.jaaak.R;

public class Login extends AppCompatActivity implements LoginView,RegisterFaceView,RegistergoogleView{
    ImageView loginfac,google;
//    CallbackManager mCallbackManager;
    private ProgressBar progressBar;
    public FirebaseAuth mAuth;
    public GoogleApiClient googleApiClient;
    public static final int RequestSignInCode = 7;
    GoogleSignInOptions googleSignInOptions;
    EditText E_Email,E_Password;
    Button signin;
    LoginPresenter logiin;
    RegisterFace_Presenter regist;
    UserRegister userface;
//    LoginResult loginResu;
    Registergoogle Registergoogl;
    SharedPreferences.Editor Shared;
    TextView Register,Resetpass;
    SharedPreferences.Editor shareRole;
    NetworikConntection checknetwork;
    RelativeLayout Relativelogin;
    String useer;
    String email;
    String useergoogle;
    SharedPreferences sha;
    SharedPreferences shared;
    TextView guest;
    LoginResult loginResu;
    CallbackManager mCallbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sha=getSharedPreferences("login",MODE_PRIVATE);
        String logi=sha.getString("logggin",null);
        if(logi!=null){
            startActivity(new Intent(Login.this,Shoping.class));
            finish();
        }
        shared=getSharedPreferences("Language",MODE_PRIVATE);
        String Lan=shared.getString("Lann",null);
        if(Lan!=null) {
            Locale locale = new Locale(Lan);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
        }
        setContentView(R.layout.activity_login);
        init();
        Reset();
//        GoogleSignOpition();
//        LoginGoogle();
        LoginFac();
        Loginhome();
        Register();
        openguest();

    }
    public void init(){
        E_Email=findViewById(R.id.Log_Email);
        E_Password=findViewById(R.id.Log_Password);
        signin=findViewById(R.id.signin);
        loginfac=findViewById(R.id.loginfac);
        Register=findViewById(R.id.Register);
        Relativelogin=findViewById(R.id.Relativelogin);
        guest=findViewById(R.id.guest);
        Resetpass=findViewById(R.id.Resetpass);
        Shared=getSharedPreferences("login",MODE_PRIVATE).edit();
        shareRole=getSharedPreferences("Role",MODE_PRIVATE).edit();
//        google=findViewById(R.id.google);
        progressBar=findViewById(R.id.progressBarlogin);
        regist=new RegisterFace_Presenter(this,this);
        Registergoogl=new Registergoogle(this,this);
        mCallbackManager = CallbackManager.Factory.create();
        mAuth = FirebaseAuth.getInstance();
        checknetwork=new NetworikConntection(this);
        logiin=new LoginPresenter(this,this);

    }
    public void Reset(){
        Resetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,ResetPassword.class));
            }
        });
    }
    public void openguest(){
        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Shared.putString("logggin",null);
                Shared.putString("name",null);
                Shared.putString("email",null);
                Shared.putString("fc",null);
                Shared.apply();
                progressBar.setVisibility(View.GONE);
                startActivity(new Intent(Login.this, Shoping.class));

            }
        });
    }
    public void Register()
    {
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,Register_Activity.class));
            }
        });
    }
    private void Loginhome() {
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if(!ValidateEmail()){
//                    return;
//                }
                if(checknetwork.isNetworkAvailable(getBaseContext())) {


//                    FUtilsValidation.isEmpty(E_Email,"");
//                    FUtilsValidation.isEmpty(E_Password, "please insert Password");
                    if (E_Email.getText().toString().equals("")) {
                        E_Email.setError("");
//                        Toast.makeText(Login.this, ""+getResources().getString(R.string.insertemail)
//                                , Toast.LENGTH_SHORT).show();
                        E_Email.setFocusable(true);
                    }
                    if (E_Password.getText().toString().equals("")) {
                        E_Password.setError("");
                        E_Password.setFocusable(true);
                    }

                    if (!E_Email.getText().toString().equals("") && !E_Password.getText().toString().equals("")) {
                        UserRegister user = new UserRegister();
                        user.setEmail(E_Email.getText().toString());
                        user.setPassword(E_Password.getText().toString());
                        progressBar.setVisibility(View.VISIBLE);
                        email=E_Email.getText().toString();
                        logiin.Login(user);
                    }
                }else {
                    Toast.makeText(Login.this,getResources().getString(R.string.internet), Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private Boolean ValidateEmail(){
        String EMAIL=E_Email.getText().toString().trim();
        if (EMAIL.isEmpty()||!isValidEmail(EMAIL)){
            E_Email.setError(getResources().getString(R.string.invalidemail));

            return false;
        }else if(!E_Email.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z-Z0-9._-]+\\.+[a-z]+")){
            E_Email.setError(getResources().getString(R.string.invalidemail));
            return false;
        }
        return true;
    }
    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private void LoginGoogle() {

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checknetwork.isNetworkAvailable(getBaseContext())) {
                    Intent AuthIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                    startActivityForResult(AuthIntent, RequestSignInCode);
                }else {
                    Toast.makeText(Login.this,getResources().getString(R.string.internet), Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RequestSignInCode) {
            GoogleSignInResult googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (googleSignInResult.isSuccess()) {
                GoogleSignInAccount googleSignInAccount = googleSignInResult.getSignInAccount();
                FirebaseUserAuth(googleSignInAccount);
            }
        }
        }



//    public void GoogleSignOpition(){
//        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
//        googleApiClient = new GoogleApiClient.Builder(Login.this)
//                .enableAutoManage(Login.this, new GoogleApiClient.OnConnectionFailedListener() {
//                    @Override
//                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//
//                    }
//                } /* OnConnectionFailedListener */)
//                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
//                .build();
//
//    }


    public void FirebaseUserAuth(final GoogleSignInAccount googleSignInAccount) {
        AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
        mAuth.signInWithCredential(authCredential)
                .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task AuthResultTask) {
                        if (AuthResultTask.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();

                             useergoogle=user.getDisplayName();
                            final String emaail=user.getEmail();
                            UserRegister use=new UserRegister();
                            String y =googleSignInAccount.getId();

                            use.setFirstName(useergoogle);
                            use.setEmail(emaail);
                            use.setId(y);

                            progressBar.setVisibility(View.VISIBLE);
                            Registergoogl.Registergoogle(use);




                        } else {
//                            Toast.makeText(LoginPresenter.this, "Turn on Internet", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
    public void LoginFac(){
        loginfac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checknetwork.isNetworkAvailable(getBaseContext())) {

                    LoginManager.getInstance().logInWithReadPermissions(Login.this, Arrays.asList("email", "public_profile"));

                    LoginManager.getInstance().registerCallback(mCallbackManager,
                            new FacebookCallback<LoginResult>() {
                                @Override
                                public void onSuccess(LoginResult loginResult) {

                                    loginResu = loginResult;
                                    handleFacebookAccessToken(loginResult.getAccessToken());
                                }

                                @Override
                                public void onCancel() {
//                                Toast.makeText(loginmain.this, "LoginPresenter Cancel", Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onError(FacebookException exception) {
                                    Toast.makeText(Login.this, getResources().getString(R.string.internet), Toast.LENGTH_LONG).show();
                                }
                            });
                }else {
                    Toast.makeText(Login.this,getResources().getString(R.string.internet), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void handleFacebookAccessToken(AccessToken token) {

        progressBar.setVisibility(View.VISIBLE);
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            GraphRequest data_request = GraphRequest.newMeRequest(
                                    loginResu.getAccessToken(),
                                    new GraphRequest.GraphJSONObjectCallback() {
                                        @Override
                                        public void onCompleted(
                                                JSONObject object,
                                                GraphResponse response) {
                                            try {
                                                String facebook_id = object.getString("id");
                                                progressBar.setVisibility(View.GONE);
                                                FirebaseUser user = mAuth.getCurrentUser();
                                                useer=object.getString("name");
                                                final String emaail=object.getString("email");

                                                final String id=user.getUid();

                                                userface = new UserRegister();
                                                userface.setFirstName(useer);
                                                userface.setEmail(emaail);
                                                userface.setId(facebook_id);
                                                progressBar.setVisibility(View.VISIBLE);
                                                regist.RegisterFace(userface);

                                            } catch (JSONException e) {
                                                // TODO Auto-generated catch block
                                            }
                                        }
                                    });
                           Bundle permission_param = new Bundle();
                            permission_param.putString("fields", "id,name,email");
                            data_request.setParameters(permission_param);
                            data_request.executeAsync();
                            data_request.executeAsync();


                        }
                    }
                });
    }

    @Override
    public void openMain(String a) {
        Shared.putString("logggin",a);
        Shared.apply();
        progressBar.setVisibility(View.GONE);
        Intent inty=new Intent(Login.this,Shoping.class);
        inty.putExtra("username",email);
        startActivity(inty);
        finish();

    }


    @Override
    public void OpenRole(String role,String a,String user,String phone) {
        Shared.putString("logggin",user);
        Shared.putString("name",role);
        Shared.putString("email",a);
        Shared.putString("phone",phone);
        Shared.putString("fc",null);
        Shared.apply();
        progressBar.setVisibility(View.GONE);
        startActivity(new Intent(Login.this, Shoping.class));
        finish();
//
    }

    @Override
    public void showError(String error) {

        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void invalideemail(String error) {
        Toast.makeText(this, ""+getResources().getString(R.string.invalidemail), Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void openMainFace(String a) {
        Shared.putString("logggin",a);
        Shared.putString("fc","a");
        Shared.apply();
        progressBar.setVisibility(View.GONE);
        Intent inty=new Intent(Login.this,Shoping.class);
        startActivity(inty);
        finish();
    }

    @Override
    public void showErrorFace(String error) {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void openMainGoogle(String a) {
        Shared.putString("logggin",a);
        Shared.putString("fc","a");
        Shared.apply();
        progressBar.setVisibility(View.GONE);
        Intent inty=new Intent(Login.this,Shoping.class);
        inty.putExtra("username",useergoogle);
        startActivity(inty);
        finish();
    }

    @Override
    public void showErrorGoogle(String error) {
        progressBar.setVisibility(View.GONE);
    }
}
