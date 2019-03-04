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

import de.hdodenhof.circleimageview.CircleImageView;
import ikon.ikon.Model.Categories;
import ikon.ikon.Viewes.Product_id;
import jak.jaaak.R;

/**
 * Created by Ahmed on 30/12/2018.
 */

public class Categories_Adapter extends RecyclerView.Adapter<Categories_Adapter.MyViewHolder>{

    private List<Categories> filteredList=new ArrayList<>();
    View itemView;
    Context con;
    Product_id product_id;
    private int postionn=0;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView Text_Name;
        private Button Callnow,Details;
        private CircleImageView img_Category;
        private ProgressBar ProgrossSpare;
        public MyViewHolder(View view) {
            super(view);
            img_Category=view.findViewById(R.id.image_Category);
            Text_Name=view.findViewById(R.id.T_Name);
            ProgrossSpare=view.findViewById(R.id.Progrossbarcategory);
        }

    }

    public Categories_Adapter(List<Categories> list, Context context){
        this.filteredList=list;
        this.con=context;
    }
    public Categories_Adapter(Context context){

        this.con=context;
    }
    public void setOnClicklistner(Product_id product_id){
        this.product_id=product_id;
    }

    @Override
    public Categories_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemcategory, parent, false);
        return new Categories_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final Categories_Adapter.MyViewHolder holder, final int position) {


        holder.Text_Name.setText(filteredList.get(position).getCategoriesName());
        String i = filteredList.get(position).getCategoriesImage();
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
                        holder.ProgrossSpare.setVisibility(View.GONE);

                        return false;
                    }
                })
                .into(holder.img_Category);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                product_id.id(filteredList.get(position).getCategoriesId(),filteredList.get(position).getCategoriesName());

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


