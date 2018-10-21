@file:JvmName("ViewBindingAdapter")

package com.medisafe.task.view.common.binding

import android.graphics.drawable.PictureDrawable
import androidx.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import com.medisafe.task.view.common.glide.GlideApp
import com.medisafe.task.view.common.glide.SvgSoftwareLayerSetter

@BindingAdapter("visibleInvisible")
fun View.showHide(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.INVISIBLE
}


@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String) {
    GlideApp.with(context).`as`(PictureDrawable::class.java).listener(SvgSoftwareLayerSetter()).load(url.toUri()).into(this)
}
