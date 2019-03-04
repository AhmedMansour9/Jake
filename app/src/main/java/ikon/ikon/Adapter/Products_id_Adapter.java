package ikon.ikon.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

import ikon.ikon.Model.Product_Orders;
import ikon.ikon.Model.ShowOrdersyid;
import ikon.ikon.Viewes.CountView;
import jak.jaaak.R;

/**
 * Created by ic on 9/17/2018.
 */

public class Products_id_Adapter extends RecyclerView.Adapter<Products_id_Adapter.MyViewHolder> {



    private List<Product_Orders> filteredList=new ArrayList<>();

    CountView count;

    View itemView;
    Context con;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView T_Name,T_Address,T_MonileNum,T_Price,T_Order,T_EMail;
        ProgressBar progressBar;
        Button getorders;
        ImageView imagespare;
        public MyViewHolder(View view) {
            super(view);
            T_Name = view.findViewById(R.id.T_Name);
            progressBar=view.findViewById(R.id.progrossimage);
            T_Price=view.findViewById(R.id.T_Price);
            imagespare=view.findViewById(R.id.imgproductid);
        }


    }

    public Products_id_Adapter(List<Product_Orders> phon, Context context){
        filteredList=phon;
        this.con=context;
    }


    @Override
    public Products_id_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemhowroductid, parent, false);
        return new Products_id_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final Products_id_Adapter.MyViewHolder holder, final int position) {


        holder.T_Name.setText(filteredList.get(position).getProductsName());


        holder.T_Price.setText(filteredList.get(position).getFinalPrice()+"SAR");
//        holder.T_Discrption.setText(a.replace("<p>","").replace("</p>",""));

        String i = filteredList.get(position).getImage();

        Uri u = Uri.parse(i);
        holder.progressBar.setVisibility(View.VISIBLE);


        Glide.with(con)
                .load("http://jak-go.com/"+u)
                .apply(new RequestOptions().override(500,500))

                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        // log exception
                        return false; // important to return false so the error placeholder can be placed
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(holder.imagespare);

        Typeface typeface = Typeface.createFromAsset(con.getAssets(), "fonts/no.otf");
        holder.T_Name.setTypeface(typeface);
        holder.T_Price.setTypeface(typeface);

    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

}


