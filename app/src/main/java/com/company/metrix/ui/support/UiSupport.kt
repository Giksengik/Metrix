package com.company.metrix.ui.support

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestListener

fun loadImage(
    context: Context,
    url: Uri?,
    view: ImageView,
) {
    Glide.with(context)
        .load(
            url ?: "https://avatars.githubusercontent.com/u/55493845?v=4"
        )
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(view)
}
