package ikon.ikon.Adapter;

import android.content.Context;
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

import ikon.ikon.Model.DetailsProducts;
import ikon.ikon.Model.Product_Detail;
import ikon.ikon.Model.Product_Details;
import jak.jaaak.R;

/**
 * Created by Ahmed on 01/01/2019.
 */

public class flash_Adapter  extends RecyclerView.Adapter<flash_Adapter.MyViewHolder>{


    public static List<Product_Detail> filteredList=new ArrayList<>();
    View itemView;
    Context con;
    DetailsProducts detailsProducts;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView Text_Spare,Text_Price,Text_Details;
        private Button Callnow,Details;
        private ImageView img_Spare;
        private ProgressBar ProgrossSpare;
        public MyViewHolder(View view) {
            super(view);
            img_Spare=view.findViewById(R.id.img_Spare);
            Text_Spare=view.findViewById(R.id.Text_Spare);
            Text_Price=view.findViewById(R.id.Text_Price);
            ProgrossSpare=view.findViewById(R.id.ProgrossSpare);
        }

    }

    public flash_Adapter(List<Product_Detail> list, Context context){
        this.filteredList=list;
        this.con=context;
    }
    public flash_Adapter(Context context){
        this.con=context;
    }
    public void setOnClicklistner(DetailsProducts detailsProducts){
        this.detailsProducts=detailsProducts;
    }

    @Override
    public flash_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemsparts, parent, false);
        return new flash_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final flash_Adapter.MyViewHolder holder, final int position) {
        holder.Text_Spare.setText(filteredList.get(position).getProductsName());
        holder.Text_Price.setText(String.valueOf(filteredList.get(position).getProductsPrice())+"SAR");

        String i = filteredList.get(position).getProductsImage();
        Uri u = Uri.parse(i);
        holder.ProgrossSpare.setVisibility(View.VISIBLE);


        Glide.with(con)
                .load("http://jak-go.com/"+u)
                .apply(new RequestOptions().override(500,500))

                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.ProgrossSpare.setVisibility(View.GONE);

                        return false; // important to return false so the error placeholder can be placed
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(holder.img_Spare);


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

