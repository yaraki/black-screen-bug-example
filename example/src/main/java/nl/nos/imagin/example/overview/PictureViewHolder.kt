package nl.nos.imagin.example.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import nl.nos.imagin.example.R

class PictureViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_view_picture, parent, false)
) {
    val imageView: ImageView = itemView.findViewById(R.id.image_view)
}