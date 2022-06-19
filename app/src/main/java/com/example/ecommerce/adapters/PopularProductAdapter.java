package com.example.ecommerce.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecommerce.R;
import com.example.ecommerce.models.PopularProductsModel;

import java.util.List;

public class PopularProductAdapter  extends RecyclerView.Adapter<PopularProductAdapter.ViewHolder> {
    private Context context;
    private List<PopularProductsModel> popularProductsModelsList;

    public PopularProductAdapter(Context context, List<PopularProductsModel> popularProductsModelsList) {
        this.context = context;
        this.popularProductsModelsList = popularProductsModelsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(popularProductsModelsList.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(popularProductsModelsList.get(position).getName());
        holder.price.setText(String.valueOf(popularProductsModelsList.get(position).getPrice()));

    }

    @Override
    public int getItemCount() {

        return popularProductsModelsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name,price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.all_img);
            name=itemView.findViewById(R.id.all_product_name);
            price=itemView.findViewById(R.id.all_price);

        }
    }
}
