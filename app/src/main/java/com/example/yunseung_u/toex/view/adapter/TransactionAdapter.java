package com.example.yunseung_u.toex.view.adapter;

import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import com.example.yunseung_u.toex.R;
import com.example.yunseung_u.toex.model.TransactionItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by seungwoo on 2017-10-02.
 */

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ItemViewHolder>{

    private static final String TAG = "StationAdapter";
    private List<TransactionItem> transaction_list;
    private Context context;



    public TransactionAdapter(Context context, List<TransactionItem> item_list) {
        this.transaction_list = item_list;
        this.context = context;
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.transaction_recyclerview_item,viewGroup,false);
        return new TransactionAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        TransactionItem ItemObject = transaction_list.get(position);
        Glide.with(context)
                .load(ItemObject.getUserProfileURL())
                .apply(new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background))
                .into(holder.profile_image);
        holder.item_currency.setText(ItemObject.getFrom());
        holder.item_distance.setText(ItemObject.getTo());
        holder.profile_name.setText(ItemObject.getValue());
    }

    @Override
    public int getItemCount() {
        return transaction_list.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.profile_image)
        ImageView profile_image;
        @BindView(R.id.own_value)
        TextView profile_name;
        @BindView(R.id.currency)
        TextView item_currency;
        @BindView(R.id.distance_value)
        TextView item_distance;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            profile_image.setBackground(new ShapeDrawable(new OvalShape()));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                profile_image.setClipToOutline(true);
            }

        }
    }
}
