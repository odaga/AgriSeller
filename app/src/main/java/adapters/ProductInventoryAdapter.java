package adapters;

import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.squareup.picasso.Picasso;
import com.ugtechie.agriseller.R;

import Models.ProductModel;

public class ProductInventoryAdapter extends FirestoreRecyclerAdapter<ProductModel, ProductInventoryAdapter.InventoryHolder> {
    private AdapterView.OnItemClickListener listener;


    public ProductInventoryAdapter(@NonNull FirestoreRecyclerOptions<ProductModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull InventoryHolder holder, int position, @NonNull ProductModel model) {
        //holder.textViewFarmTitle.setText(model.getFarmName());
        holder.inventoryProductName.setText(model.getProductName());
        holder.inventoryProductPrice.setText("UGX"+model.getProductPrice());
        if (model.getProductImageUrl() != null)
            Picasso.get().load(model.getProductImageUrl()).into(holder.inventoryProductImage);
        else
            Picasso.get().load(model.getProductImageUrl()).into(holder.inventoryProductImage);
    }

    @NonNull
    @Override
    public InventoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_inventory_card,parent, false);
        return new InventoryHolder(v);
    }

    class InventoryHolder extends RecyclerView.ViewHolder {
        ImageView inventoryProductImage;
        TextView inventoryProductName;
        TextView inventoryProductPrice;

        public InventoryHolder(@NonNull View itemView) {
            super(itemView);
            //textViewFarmTitle = itemView.findViewById(R.id.card_farm_title);
            inventoryProductImage = itemView.findViewById(R.id.single_inventory_product_image);
            inventoryProductName = itemView.findViewById(R.id.single_inventory_product_name);
            inventoryProductPrice = itemView.findViewById(R.id.single_inventory_product_price);

            //Setting click listener on the farm card
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION && listener != null) {
                       // listener.onItemClick(getSnapshots().getSnapshot(position), position);
                    }

                }
            });

        }
    }
/*
    public interface  OnItemClickListener {
        void  onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    */
}

