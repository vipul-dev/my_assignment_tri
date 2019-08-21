package com.example.myassignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myassignment.R;
import com.example.myassignment.databinding.RowOfGridLayoutBinding;
import com.example.myassignment.interf.OnItemClickListener;
import com.example.myassignment.model.HomeModel;

public class GridLayoutAdapter extends RecyclerView.Adapter<GridLayoutAdapter.MyViewHolder> {

    private Context context;
    private ObservableArrayList<HomeModel> mDataList;
    private OnItemClickListener itemClickListener;

    public GridLayoutAdapter(Context context, ObservableArrayList<HomeModel> mDataList,OnItemClickListener itemClickListener) {
        this.context = context;
        this.mDataList = mDataList;
        this.itemClickListener=itemClickListener;
    }

    public void updateList(ObservableArrayList<HomeModel> list) {
        mDataList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_of_grid_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        if (mDataList.size() > 0) {
            HomeModel model = mDataList.get(position);
            String imageUrl = model.getImage();
            if (imageUrl != null && !imageUrl.equalsIgnoreCase("")) {
                Glide.with(context).load(imageUrl).into(holder.binding.ivImage);
            } else {

                holder.binding.ivImage.setImageResource(R.mipmap.ic_launcher);
            }
            holder.binding.tvImageName.setText(model.getImageName());


            holder.binding.ivdownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(v,position);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private RowOfGridLayoutBinding binding;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = DataBindingUtil.bind(itemView);
        }
    }
}
