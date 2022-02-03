package com.example.hsdemosoft.utils

import android.text.TextUtils
import android.widget.ImageView
import com.example.hsdemosoft.R
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso


fun ShapeableImageView.loadImg(imageUrl: String) {
    if (TextUtils.isEmpty(imageUrl)) {
        Picasso.with(context).load(R.mipmap.ic_launcher).into(this)
    } else {
        Picasso.with(context).load(imageUrl).into(this)
    }
}
