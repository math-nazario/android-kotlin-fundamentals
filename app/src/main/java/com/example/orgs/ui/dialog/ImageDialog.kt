package com.example.orgs.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.example.orgs.databinding.ImageFormBinding
import com.example.orgs.extensions.tryToLoadImage

class ImageDialog(private val context: Context) {
    fun show(
        defaultURL: String? = null,
        whenImageLoaded: (imageView: String) -> Unit
    ) {
        ImageFormBinding.inflate(LayoutInflater.from(context)).apply {

            defaultURL?.let {
                imgProductImageUploaded.tryToLoadImage(it)
                tieURL.setText(it)
            }

            btnLoadImage.setOnClickListener {
                val url = tieURL.text.toString()
                imgProductImageUploaded.tryToLoadImage(url)
            }
            AlertDialog.Builder(context)
                .setView(root)
                .setPositiveButton("Confirm") { _, _ ->
                    val url = tieURL.text.toString()
                    whenImageLoaded(url)
                }
                .setNegativeButton("Cancel") { _, _ -> }
                .show()
        }
    }
}