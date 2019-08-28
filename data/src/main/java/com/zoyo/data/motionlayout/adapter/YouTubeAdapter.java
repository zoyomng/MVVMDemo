package com.zoyo.data.motionlayout.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zoyo.data.R;

/**
 * @Description: java类作用描述
 * @Author: zoyomng
 * @CreateDate: 2019/8/23 14:44
 */
public class YouTubeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Integer[] mData;

    public YouTubeAdapter(@NonNull Integer[] mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(viewType, parent, false);

        if (viewType == R.layout.motion_22_recyclerview_expanded_text_header) {
            return new TextHeaderViewHolder(itemView);
        } else if (viewType == R.layout.motion_22_recyclerview_expanded_text_description) {
            return new TextDescriptionViewHolder(itemView);
        } else {
            return new CatRowViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CatRowViewHolder) {
            CatRowViewHolder catRowViewHolder = (CatRowViewHolder) holder;
            TextView textView = catRowViewHolder.textView;
            textView.setText(textView.getResources().getString(R.string.cat_n, position - 2));
            Glide.with(textView.getContext())
                    .load(mData[position - 2])
                    .into(catRowViewHolder.imageView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        int layoutResId;
        switch (position) {
            case 0:
                layoutResId = R.layout.motion_22_recyclerview_expanded_text_header;
                break;
            case 1:
                layoutResId = R.layout.motion_22_recyclerview_expanded_text_description;
                break;
            default:
                layoutResId = R.layout.motion_22_recyclerview_expanded_row;
                break;
        }
        return layoutResId;
    }

    @Override
    public int getItemCount() {
        return mData.length + 2; //头布局
    }
}

class CatRowViewHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;
    public TextView textView;

    public CatRowViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.image_row);
        textView = itemView.findViewById(R.id.text_row);
    }
}

class TextDescriptionViewHolder extends RecyclerView.ViewHolder {

    public TextDescriptionViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}

class TextHeaderViewHolder extends RecyclerView.ViewHolder {

    public TextHeaderViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}
