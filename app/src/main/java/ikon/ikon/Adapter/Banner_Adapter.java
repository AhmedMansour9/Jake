package ikon.ikon.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import ikon.ikon.Model.Banner;
import ikon.ikon.Viewes.CountView;
import jak.jaaak.R;

/**
 * Created by ic on 9/23/2018.
 */

public class Banner_Adapter extends RecyclerView.Adapter<Banner_Adapter.MyViewHolder>{

    public static List<Banner> filteredList=new ArrayList<>();
    SharedPreferences.Editor share;
    CountView count;
    public static String TotalPrice;
    View itemView;
    Context con;
    String prrice;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView T_Name,T_Discrption,T_Model,T_Price,count;
        ImageView mobile;
        ProgressBar progressBar;
        ImageView btncart;
        public ImageView plus,minus,delete;
        ImageView imggg;
        public MyViewHolder(View view) {
            super(view);
//            T_Name = view.findViewById(R.id.T_Name);
            imggg=view.findViewById(R.id.viewPagerItem_image1);

        }


    }

    public Banner_Adapter(List<Banner> list,Context context){
        this.filteredList=list;
        this.con=context;
    }


    @Override
    public Banner_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.banner, parent, false);
        return new Banner_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final Banner_Adapter.MyViewHolder holder, final int position) {


        String i = filteredList.get(position).getImage();
        Uri u = Uri.parse(i);
//        holder.progressBar.setVisibility(View.VISIBLE);



        Glide.with(con)
                .load("https://jak-go.com/public/images/mobile_banner/"+u)
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

                .into(holder.imggg);



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
