package com.prosperekwerike.gadsleaderboard.ui.adapters

import android.annotation.SuppressLint
import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@SuppressLint("ResourceType")
@BindingAdapter("displayBadge")
fun ImageView.displayBadge(url : String){
    Glide.with(this.context)
        .load(Uri.parse(url))
        .override(width, height)
        .placeholder(resources.getColor(android.R.color.darker_gray))
        .into(this)
}
