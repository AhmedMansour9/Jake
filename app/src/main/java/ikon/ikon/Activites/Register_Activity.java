package ikon.ikon.Activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.fourhcode.forhutils.FUtilsValidation;

import ikon.ikon.NetworikConntection;
import ikon.ikon.Model.UserRegister;
import ikon.ikon.PreSenter.Register;
import ikon.ikon.Viewes.RegisterView;
import jak.jaaak.R;

public class Register_Activity extends AppCompatActivity implements RegisterView {
    String gender;
    Button register;
    EditText E_FirstName,E_LastName,E_Emai,E_Phone,E_Password,E_ConFirmpassword;
    Register Register_Presenter;
    ProgressBar Progrossregister;
    NetworikConntection checkgbsAndNetwork;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        E_FirstName=findViewById(R.id.E_FirstName);
        E_LastName=findViewById(R.id.E_LastName);
        Progrossregister=findViewById(R.id.progressBarRegister);
        E_Emai=findViewById(R.id.E_Emai);
        E_Phone=findViewById(R.id.E_Phone);
        E_Password=findViewById(R.id.E_Password);
        E_ConFirmpassword=findViewById(R.id.E_ConFirmPassword);
        register=findViewById(R.id.Register);
        Register_Presenter=new Register(this,this);
       checkgbsAndNetwork=new NetworikConntection(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkgbsAndNetwork.isNetworkAvailable(getBaseContext())) {


                    if (E_Emai.getText().toString().equals("")) {
                        E_Emai.setError("");
                        E_Emai.setFocusable(true);
                    }

                    if (E_FirstName.getText().toString().equals("")) {
                        E_FirstName.setError("");
                        E_FirstName.setFocusable(true);
                    }

                    if (E_LastName.getText().toString().equals("")) {
                        E_LastName.setError("");
                        E_LastName.setFocusable(true);
                    }

                    if (E_Phone.getText().toString().equals("")) {
                        E_Phone.setError("");
                        E_Phone.setFocusable(true);
                    }
                    if (E_Password.getText().toString().equals("")) {
                        E_Password.setError("");
                        E_Password.setFocusable(true);
                    }
                    else if (E_ConFirmpassword.getText().toString().equals("")) {
                        E_ConFirmpassword.setError("");
                        E_ConFirmpassword.setFocusable(true);
                    }
                    else if (!FUtilsValidation.isLengthCorrect(E_Password.getText().toString(), 8, 16)) {
                        Toast.makeText(Register_Activity.this, getResources()
                                .getString(R.string.minpas), Toast.LENGTH_SHORT).show();
                        E_Password.setError("");
                    }
                     else if (!FUtilsValidation.isLengthCorrect(E_ConFirmpassword.getText().toString(), 8, 16)){
                        Toast.makeText(Register_Activity.this, getResources()
                                .getString(R.string.minpas), Toast.LENGTH_SHORT).show();
                    E_ConFirmpassword.setError("");
                }
                else if (!E_Password.getText().toString().equals(E_ConFirmpassword.getText().toString())) {
                    Toast.makeText(Register_Activity.this, getResources().getString(R.string.notmatch),
                            Toast.LENGTH_SHORT).show();
                }
                    else if(ValidateEmail()){
                          if (!E_Emai.getText().toString().equals("") && !E_FirstName.getText().toString().equals("") && !E_LastName.getText().toString().equals("") && !E_Phone.getText().toString().equals("") &&
                                  (FUtilsValidation.isLengthCorrect(E_Password.getText().toString(), 8, 16))
                                  && ValidateEmail()) {
                              UserRegister user = new UserRegister();
                              user.setEmail(E_Emai.getText().toString());
                              user.setFirstName(E_FirstName.getText().toString());
                              user.setLastName(E_LastName.getText().toString());
                              user.setPhone(E_Phone.getText().toString());
                              user.setPassword(E_Password.getText().toString());

                              Progrossregister.setVisibility(View.VISIBLE);
                              Register_Presenter.register(user);
                          }

                    }
                }else {
                    Toast.makeText(Register_Activity.this,getResources().getString(R.string.internet), Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    @Override
    public void openMain() {
        Toast.makeText(this, "Successfull", Toast.LENGTH_SHORT).show();
        Progrossregister.setVisibility(View.GONE);
        startActivity(new Intent(Register_Activity.this,Login.class));
        finish();
    }

    @Override
    public void showError(String error) {
        Progrossregister.setVisibility(View.GONE);
    }

    @Override
    public void validemail(String error) {
        Toast.makeText(this, getResources().getString(R.string.alreadyexistemail), Toast.LENGTH_SHORT).show();
        Progrossregister.setVisibility(View.GONE);

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

    private Boolean ValidatePassword(){
        if(E_Password.getText().toString().trim().isEmpty()&&E_Password.getText().toString().trim().length()>3){
//            E_Password.setError(getResources().getString(R.string.enterpas));
            return false;
        }else {
            return true;
        }

    }
}
