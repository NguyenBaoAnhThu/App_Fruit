package com.example.project162.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project162.Activity.ListFoodsActivity;
import com.example.project162.Domain.Category;
import com.example.project162.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewholder> {

    private ArrayList<Category> items;
    private Context context;

    // Cập nhật constructor để chấp nhận context
    public CategoryAdapter(Context context, ArrayList<Category> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.viewholder_category, parent, false);
        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.titleTxt.setText(items.get(position).getName());

        // Cài đặt background dựa trên vị trí
        switch (position) {
            case 0: holder.pic.setBackgroundResource(R.drawable.cat_0_background); break;
            case 1: holder.pic.setBackgroundResource(R.drawable.cat_1_background); break;
            case 2: holder.pic.setBackgroundResource(R.drawable.cat_2_background); break;
            case 3: holder.pic.setBackgroundResource(R.drawable.cat_3_background); break;
            case 4: holder.pic.setBackgroundResource(R.drawable.cat_4_background); break;
            case 5: holder.pic.setBackgroundResource(R.drawable.cat_5_background); break;
            case 6: holder.pic.setBackgroundResource(R.drawable.cat_6_background); break;
            case 7: holder.pic.setBackgroundResource(R.drawable.cat_7_background); break;
        }

        // Tải ảnh danh mục bằng Glide
        int drawableResourceId = context.getResources().getIdentifier(items.get(position).getImagePath(),
                "drawable", context.getPackageName());
        Glide.with(context)
                .load(drawableResourceId)
                .into(holder.pic);

        // Cài đặt onClickListener để chuyển đến ListFoodsActivity
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ListFoodsActivity.class);
            intent.putExtra("CategoryId", items.get(position).getId());
            intent.putExtra("CategoryName", items.get(position).getName());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView titleTxt;
        ImageView pic;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.catNameTxt);
            pic = itemView.findViewById(R.id.imgCat);
        }
    }
}
