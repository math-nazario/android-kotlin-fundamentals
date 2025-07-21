package com.example.orgs.extensions

import android.content.Context
import android.os.Build.VERSION.SDK_INT
import android.widget.ImageView
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.load
import com.example.orgs.R

fun createImageLoader(context: Context): ImageLoader {
    return ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
}

fun ImageView.tryToLoadImage(url: String? = null) {
    val imageLoader = createImageLoader(context)
    load(url, imageLoader) {
        fallback(R.drawable.error)
        error(R.drawable.error)
        placeholder(R.drawable.loading)
        crossfade(true)
    }
}