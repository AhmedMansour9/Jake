package ikon.ikon.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ikon.ikon.Activites.ShowProduct;
import ikon.ikon.Model.DetailsProducts;
import ikon.ikon.Model.Phones;
import ikon.ikon.Model.Product_Detail;
import ikon.ikon.Model.Product_Details;
import jak.jaaak.R;


/**
 * Created by ic on 9/7/2018.
 */

public class Phones_Adapter extends RecyclerView.Adapter<Phones_Adapter.MyViewHolder> implements Filterable {


    DetailsProducts detailsProducts;
    public List<Product_Detail> filteredList=new ArrayList<>();
    View itemView;
    Context con;
    public static List<Product_Detail> filtered = new ArrayList<>();
    private List<Product_Detail> mArrayList;
    int current;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView T_Name,T_Discrption,T_Model,T_Price,count,Add_ToCart;
        ImageView mobile;
        ProgressBar progressBar;
        TextView btncart;
        public ImageView plus,minus;

        public MyViewHolder(View view) {
            super(view);
            T_Name = view.findViewById(R.id.T_Name);
            T_Price = view.findViewById(R.id.T_Price);
            mobile=view.findViewById(R.id.Image_Phone);
            progressBar=view.findViewById(R.id.progrossimage);
            btncart=view.findViewById(R.id.btncard);
            Add_ToCart=view.findViewById(R.id.Add_ToCart);

        }

    }

    public Phones_Adapter(List<Product_Detail> phon,Context context){
        filteredList=phon;
        this.con=context;
        mArrayList=phon;
    }

    public void setOnClicklistner(DetailsProducts detailsProducts){
        this.detailsProducts=detailsProducts;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_phone, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


        holder.T_Name.setText(filteredList.get(position).getProductsName());
//        holder.T_Model.setText(filteredList.get(position).getProductsModel());
//        String a = filteredList.get(position).getProductsDescription();
//        holder.T_Discrption.setText(a.replace("<p>","").replace("</p>",""));



        holder.T_Price.setText(filteredList.get(position).getProductsPrice());
        String i = filteredList.get(position).getProductsImage();

        Uri u = Uri.parse(i);
       holder.progressBar.setVisibility(View.VISIBLE);
        Picasso.with(con)
                .load("https://jak-go.com/"+u)
                .resize(500,500)
                .into(holder.mobile, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        holder.progressBar.setVisibility(View.GONE);
                    }
                });

        Typeface typeface = Typeface.createFromAsset(con.getAssets(), "fonts/no.otf");
        holder.T_Name.setTypeface(typeface);
//        holder.T_Model.setTypeface(typeface);
//        holder.T_Discrption.setTypeface(typeface);
        holder.T_Price.setTypeface(typeface);

        holder.Add_ToCart.setOnClickListener(new View.OnClickListener() {
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
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                filtered.clear();
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filteredList = mArrayList;
                } else {
                    for (Product_Detail androidVersion : mArrayList) {
                        if (androidVersion.getProductsName().toLowerCase().contains(charString)) {
                            filtered.add(androidVersion);}}
                    filteredList = filtered;}
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredList = (List<Product_Detail>) filterResults.values;

                notifyDataSetChanged();
            }
        };

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


