package com.example.ecommerce.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce.R;
import com.example.ecommerce.models.AddressModel;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {
    Context context;
    List<AddressModel> addressModelList;
    SelectedAddres selectedAddres;
    private RadioButton selectedRadiobtn;

    public AddressAdapter(Context context, List<AddressModel> addressModelList, SelectedAddres selectedAddres) {
        this.context = context;
        this.addressModelList = addressModelList;
        this.selectedAddres = selectedAddres;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.address_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.address.setText(addressModelList.get(position).getUserAddress());
        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(AddressModel address:addressModelList){
                    address.setSeleceted(false);
                }
                addressModelList.get(position).setSeleceted(true);
                if(selectedRadiobtn!=null){
                    selectedRadiobtn.setChecked(false);
                }
                selectedRadiobtn=(RadioButton) v;
                selectedRadiobtn.setChecked(true);
                selectedAddres.setAddress(addressModelList.get(position).getUserAddress());
            }
        });

    }

    @Override
    public int getItemCount() {
        return addressModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView address;
        RadioButton radioButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            address=itemView.findViewById(R.id.address_add);
            radioButton =itemView.findViewById(R.id.select_address);
        }
    }
    public interface SelectedAddres{
        void setAddress(String address);


    }
}
