package adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.ugtechie.agriseller.R;

import java.util.List;

import Models.OrderModel;
import Models.ProductModel;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.orderViewHolder> {
    //Adapter variables
    private List<OrderModel> orderList;
    private OnItemClickListener mListener;

    //Constructors
    public OrderAdapter(List<OrderModel> orderList) {
        this.orderList = orderList;
    }

    //onClick Interface
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public static class orderViewHolder  extends RecyclerView.ViewHolder {
        ImageView orderProductImage;
        TextView orderProductName;
        TextView orderProductPrice;
        TextView orderProductQuantity;

        public orderViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            orderProductImage = itemView.findViewById(R.id.single_order_product_image);
            orderProductName = itemView.findViewById(R.id.single_order_name);
            orderProductPrice = itemView.findViewById(R.id.single_order_product_price);
            orderProductQuantity = itemView.findViewById(R.id.single_product_order_quantity);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
    public orderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_order_card, parent, false);
       return new orderViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull orderViewHolder holder, int position) {
        OrderModel order = orderList.get(position);
        Float total = Float.parseFloat(order.getPrice())  * Float.parseFloat(order.getQuantity());
        holder.orderProductName.setText(order.getName());
        //holder.orderProductPrice.setText(order.getPrice());
        holder.orderProductPrice.setText(String.valueOf(String.valueOf(total)));
        holder.orderProductQuantity.setText(String.valueOf(order.getQuantity()));

        if (order.getProductImage() == null) {
            holder.orderProductImage.setImageResource(R.drawable.app_background);
        } else {
            Picasso.get().load(order.getProductImage()).into(holder.orderProductImage);
        }

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

}

