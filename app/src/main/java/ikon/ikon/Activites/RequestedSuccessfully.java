package ikon.ikon.Activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ikon.ikon.Adapter.adorder_Succesfull_Adapter;
import ikon.ikon.Bussiness.ListItemCart;
import ikon.ikon.GPSTracker;
import jak.jaaak.R;

public class RequestedSuccessfully extends AppCompatActivity {

    View view;
    RecyclerView recyclerView;
    adorder_Succesfull_Adapter adapter;
    ListItemCart list=new ListItemCart();
    Button btnorder;
    GPSTracker gbs;
    double total_price;
    double res = 0;
    ListItemCart listItemCar;
    TextView TextDone;
    SharedPreferences.Editor sharesss;
    ListItemCart listItemCart;
    TextView txtorder,T_Price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requested_successfully);
        txtorder=findViewById(R.id.T_Orderss);
        T_Price=findViewById(R.id.T_Price);
        sharesss=getSharedPreferences("count",MODE_PRIVATE).edit();
        TextDone=findViewById(R.id.done);
        txtorder.setText(getIntent().getStringExtra("id"));
        T_Price.setText(getIntent().getStringExtra("price"));
        TextDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent inty=new Intent(RequestedSuccessfully.this,Shoping.class);
//                inty.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                inty.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(inty);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent inty=new Intent(RequestedSuccessfully.this,Shoping.class);
        startActivity(inty);
        finish();

    }
}
