package com.prosperekwerike.gadsleaderboardren.ui.adapters

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
        .into(this)
}
