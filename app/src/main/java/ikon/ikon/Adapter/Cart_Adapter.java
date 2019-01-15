package ikon.ikon.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ikon.ikon.Model.Cart_Details;
import ikon.ikon.Viewes.CountView;
import ikon.ikon.Viewes.Count_View;
import jak.jaaak.R;

/**
 * Created by ic on 9/10/2018.
 */

public class Cart_Adapter  extends RecyclerView.Adapter<Cart_Adapter.MyViewHolder> {



    public static List<Cart_Details> filteredList=new ArrayList<>();
    SharedPreferences.Editor share;
    CountView count;
    public static String TotalPrice;
    View itemView;
    Context con;
    Count_View count_view;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView T_Name,T_Discrption,T_Model,T_Price,counter,plus,minus;
        ImageView Image_product;
        ProgressBar progressBar;
        ImageView btncart;
        public ImageView delete;

        public MyViewHolder(View view) {
            super(view);
            T_Name = view.findViewById(R.id.T_Name);
            Image_product = view.findViewById(R.id.Image_product);
            plus = view.findViewById(R.id.plus);
            T_Price = view.findViewById(R.id.T_Price);
            minus=view.findViewById(R.id.minus);
            progressBar=view.findViewById(R.id.progressBar);
            counter=view.findViewById(R.id.counter);
            delete=view.findViewById(R.id.delete);


        }


    }

    public Cart_Adapter(List<Cart_Details> phon, Context context){
        filteredList=phon;
        this.con=context;
    }

    public void count(Count_View count_view){
        this.count_view=count_view;
    }
    @Override
    public Cart_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemscart, parent, false);
        return new Cart_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final Cart_Adapter.MyViewHolder holder, final int position) {

        holder.T_Name.setText(filteredList.get(position).getProductsName());
        holder.T_Price.setText(filteredList.get(position).getFinalPrice());
        holder.counter.setText(filteredList.get(position).getCustomersBasketQuantity());

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count=Integer.parseInt(holder.counter.getText().toString());
                count++;
                holder.counter.setText(count+"");
                count_view.count_plus(String.valueOf(filteredList.get(position).getCustomersBasketId()));
            }
        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int count=Integer.parseInt(holder.counter.getText().toString());
                if(count>1) {
                    count--;
                    holder.counter.setText(count + "");
                    count_view.count_minus(String.valueOf(filteredList.get(position).getCustomersBasketId()));
                }
            }
        });

        String i = filteredList.get(position).getImage();
        Uri u = Uri.parse(i);
        holder.progressBar.setVisibility(View.VISIBLE);
        Picasso.with(con)
                .load("http://jak-go.com/"+u)
                .fit()
                .centerCrop()
                .into(holder.Image_product, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        holder.progressBar.setVisibility(View.GONE);
                    }
                });


//        Typeface typeface = Typeface.createFromAsset(con.getAssets(), "fonts/no.otf");
//        holder.T_Name.setTypeface(typeface);
//        holder.T_Model.setTypeface(typeface);
//        holder.T_Discrption.setTypeface(typeface);
//        holder.T_Price.setTypeface(typeface);


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(filteredList.get(position).getCustomersBasketId()!=null) {
                    count_view.delete(String.valueOf(filteredList.get(position).getCustomersBasketId()),
                            String.valueOf(position));
                    filteredList.remove(position);

                }
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

