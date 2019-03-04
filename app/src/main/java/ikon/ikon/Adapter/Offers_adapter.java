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
import android.widget.Filter;
import android.widget.Filterable;
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

import ikon.ikon.Model.DetailsProducts;
import ikon.ikon.Model.Product_Detail;
import ikon.ikon.Model.Product_Details;
import jak.jaaak.R;

/**
 * Created by Ahmed on 31/12/2018.
 */

public class Offers_adapter extends RecyclerView.Adapter<Offers_adapter.MyViewHolder> {


    DetailsProducts detailsProducts;
    public List<Product_Detail> filteredList=new ArrayList<>();
    View itemView;
    Context con;
    public static List<Product_Detail> filtered = new ArrayList<>();
    private List<Product_Detail> mArrayList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView T_Name,T_Discrption,T_Model,T_Price,count;
        ImageView mobile;
        ProgressBar progressBar;
        TextView btncart;
        public ImageView plus,minus;

        public MyViewHolder(View view) {
            super(view);
            T_Name = view.findViewById(R.id.T_Name);
            T_Price = view.findViewById(R.id.T_Price);
            mobile=view.findViewById(R.id.Image_product);
            progressBar=view.findViewById(R.id.progrossimage);
            btncart=view.findViewById(R.id.btncard);
            progressBar=view.findViewById(R.id.progressBar);
        }

    }

    public Offers_adapter(List<Product_Detail> phon,Context context){
        filteredList=phon;
        this.con=context;
    }

    public void setOnClicklistner(DetailsProducts detailsProducts){
        this.detailsProducts=detailsProducts;
    }
    @Override
    public Offers_adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_offer, parent, false);
        return new Offers_adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final Offers_adapter.MyViewHolder holder, final int position) {


        holder.T_Name.setText(filteredList.get(position).getProductsName());
//        holder.T_Model.setText(filteredList.get(position).getProductsModel());
//        String a = filteredList.get(position).getProductsDescription();
//        holder.T_Discrption.setText(a.replace("<p>","").replace("</p>",""));



        holder.T_Price.setText(filteredList.get(position).getProductsPrice()+"SAR");
        String i = filteredList.get(position).getProductsImage();

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
                .into(holder.mobile);
        holder.btncart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product_Details details=new Product_Details();
                details.setProductId(filteredList.get(position).getProductsId());
                detailsProducts.CartDetails(details);

            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product_Details details=new Product_Details();
                details.setProductId(filteredList.get(position).getProductsId());
                details.setProductName(filteredList.get(position).getProductsName());
                details.setPrice(filteredList.get(position).getProductsPrice());
                details.setModelProduct(filteredList.get(position).getProductsModel());
                details.setProductImage(filteredList.get(position).getProductsImage());
                details.setDescrption(filteredList.get(position).getProductsDescription());
                detailsProducts.ProductsDetails(details);
//
            }
        });




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


