package adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.ugtechie.agriseller.R;

import java.text.DecimalFormat;
import java.util.List;

import Models.ProductModel;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.inventoryViewHolder> {
    //Adapter variables
    private List<ProductModel> inventoryList;
    private OnItemClickListener mListener;

    //Inventory constructor
    public InventoryAdapter(List<ProductModel> inventoryList) {
        this.inventoryList = inventoryList;
    }

    //onClick Interface
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public static class inventoryViewHolder extends RecyclerView.ViewHolder {
        ImageView inventoryProductImage;
        TextView inventoryProductName;
        TextView inventoryProductPrice;

        public inventoryViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            inventoryProductImage = itemView.findViewById(R.id.single_inventory_product_image);
            inventoryProductName = itemView.findViewById(R.id.single_inventory_product_name);
            inventoryProductPrice = itemView.findViewById(R.id.single_inventory_product_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public inventoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_inventory_card, parent, false);
        return new inventoryViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull inventoryViewHolder holder, int position) {
        ProductModel inventory = inventoryList.get(position);
        //DecimalFormat decimalFormat = new DecimalFormat("#, ###.##");

        holder.inventoryProductName.setText(inventory.getName());
        holder.inventoryProductPrice.setText("UGX" + inventory.getPrice());
        if (inventory.getProductImage() == null) {
            holder.inventoryProductImage.setImageResource(R.drawable.app_background);
        } else {
            Picasso.get().load(inventory.getProductImage()).into(holder.inventoryProductImage);
        }
        /*
        try {
            holder.inventoryProductName.setText(inventory.getProductName());
            holder.inventoryProductPrice.setText("UGX" +inventory.getProductPrice());
            if (inventory.getProductImageUrl() != null)
                Picasso.get().load(inventory.getProductImageUrl()).into(holder.inventoryProductImage);
            else
                Picasso.get().load(inventory.getProductImageUrl()).into(holder.inventoryProductImage);
        }
        catch (Exception e) {
            Log.d(TAG, "onBindViewHolder: " +e.getMessage());
            holder.inventoryProductImage.setImageResource(R.drawable.app_background);
        }

        */
    }

    @Override
    public int getItemCount() {
        return inventoryList.size();
    }


}
