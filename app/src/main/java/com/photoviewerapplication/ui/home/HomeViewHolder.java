package com.photoviewerapplication.ui.home;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.photoviewerapplication.R;

public class HomeViewHolder extends RecyclerView.ViewHolder {
    ImageView mImage;
    CardView mCardView;

    HomeViewHolder(View itemView) {
        super(itemView);
        mImage = itemView.findViewById(R.id.ivImage);
        mCardView = itemView.findViewById(R.id.cardview);
    }
}