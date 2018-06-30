package com.example.yunseung_u.toex.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import com.example.yunseung_u.toex.R;
import com.example.yunseung_u.toex.model.DealItem;
import com.example.yunseung_u.toex.view.activity.ShowListItemActivity;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by seungwoo on 2017-10-02.
 */

public class DealitemAdapter extends RecyclerView.Adapter<DealitemAdapter.ItemViewHolder>{

    private static final String TAG = "StationAdapter";
    private List<DealItem> dealitem_list;
    private Context context;



    public DealitemAdapter(Context context, List<DealItem> item_list) {
        this.dealitem_list = item_list;
        this.context = context;
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.transaction_recyclerview_item,viewGroup,false);
        return new DealitemAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {

        final DealItem ItemObject = dealitem_list.get(position);
        Glide.with(context)
                .load(ItemObject.getUserprofileURL())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_launcher_background))
                .into(holder.profile_image);
        holder.item_currency.setText("1020");
        holder.item_distance.setText(ItemObject.getDistance());
        holder.item_distance.append(" km");
        holder.valueText.setText(String.valueOf(ItemObject.getSellprice()));


        String buyUnit = ItemObject.getSellunit();
        if(buyUnit.equals("USD")){
            holder.valueText.append(Resources.getSystem().getString(R.string.USD));
        }else if(buyUnit.equals("KRW")){
            holder.valueText.append("￦");
        }else if(buyUnit.equals("JPY")){
            holder.valueText.append(" 엔");
        }else if(buyUnit.equals("EUR")) {
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();

                Intent intent = new Intent(v.getContext() , ShowListItemActivity.class);


                DealItem dealItem = new DealItem(
                        dealitem_list.get(position).getId(),
                        dealitem_list.get(position).getId(),
                        dealitem_list.get(position).getLatitude(),
                        dealitem_list.get(position).getLongitude(),
                        dealitem_list.get(position).getSellprice(),
                        dealitem_list.get(position).getSellunit(),
                        dealitem_list.get(position).getBuyprice(),
                        dealitem_list.get(position).getBuyunit(),
                        dealitem_list.get(position).getUserprofileURL(),
                        dealitem_list.get(position).getDistance()
                );

                /*
                intent.putExtra("longitude",dealitem_list.get(position).getLongitude());
                intent.putExtra("latitude",dealitem_list.get(position).getLongitude());
                intent.putExtra("sellunit",dealitem_list.get(position).getSellunit());
                intent.putExtra("buyunit",dealitem_list.get(position).getBuyunit());
                intent.putExtra("sellprice",dealitem_list.get(position).getSellprice());
                intent.putExtra("buyprice",dealitem_list.get(position).getBuyprice());
                intent.putExtra("userprofileURL",dealitem_list.get(position).getUserprofileURL());

*/
                /*
                intent.putExtra("item", (Serializable) dealitem_list.get(position));
*/
                intent.putExtra("item", dealItem);
                v.getContext().startActivity(intent);



            }
        });





    }

    @Override
    public int getItemCount() {
        return dealitem_list.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.profile_image)
        ImageView profile_image;
        @BindView(R.id.own_value)
        TextView valueText;
        @BindView(R.id.currency)
        TextView item_currency;
        @BindView(R.id.distance_value)
        TextView item_distance;

        public View mView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            ButterKnife.bind(this, itemView);
            profile_image.setBackground(new ShapeDrawable(new OvalShape()));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                profile_image.setClipToOutline(true);
            }

        }
    }
}
