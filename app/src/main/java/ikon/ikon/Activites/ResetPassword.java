package ikon.ikon.Activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fourhcode.forhutils.FUtilsValidation;

import ikon.ikon.Model.ResetPassword_Response;
import ikon.ikon.Model.UserRegister;
import ikon.ikon.PreSenter.Reset_Password_Presenter;
import ikon.ikon.Viewes.LoginView;
import jak.jaaak.R;

public class ResetPassword extends AppCompatActivity implements LoginView {

    ProgressBar progrossReset;
    Reset_Password_Presenter resetPassword_response;
    EditText E_Emai;
    TextView reset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_reset_password);
        progrossReset=findViewById(R.id.progrossReset);
        resetPassword_response=new Reset_Password_Presenter(this,this);
        E_Emai=findViewById(R.id.E_Emai);
        reset=findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (E_Emai.getText().toString().equals("")) {
                    E_Emai.setError("");
                    E_Emai.setFocusable(true);
                }
                  if(ValidateEmail())

                if (!E_Emai.getText().toString().equals("") &&ValidateEmail()) {
                   progrossReset.setVisibility(View.VISIBLE);
                   String emil=E_Emai.getText().toString();
                    resetPassword_response.Reset(emil);
                }

            }
        });


    }
    private Boolean ValidateEmail(){
        String EMAIL=E_Emai.getText().toString().trim();
        if (EMAIL.isEmpty()||!isValidEmail(EMAIL)){
            Toast.makeText(this, ""+getResources().getString(R.string.insertemail), Toast.LENGTH_SHORT).show();

            return false;
        }else if(!E_Emai.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
            Toast.makeText(this, ""+getResources().getString(R.string.insertemail), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    public void openMain(String a) {
        Toast.makeText(this, ""+getResources().getString(R.string.passwordsend), Toast.LENGTH_SHORT).show();
        finish();
        progrossReset.setVisibility(View.GONE);
    }

    @Override
    public void OpenRole(String UserName, String Email, String usertoken, String phone) {

    }

    @Override
    public void showError(String error) {
        progrossReset.setVisibility(View.GONE);
    }

    @Override
    public void invalideemail(String error) {

    }
}
