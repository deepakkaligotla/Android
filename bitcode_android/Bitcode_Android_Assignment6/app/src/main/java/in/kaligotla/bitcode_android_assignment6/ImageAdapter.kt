package `in`.kaligotla.bitcode_android_assignment6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import `in`.kaligotla.bitcode_android_assignment6.databinding.ImageViewHolderBinding

class ImageAdapter(private var imageIds: ArrayList<Int>): RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
    inner class ImageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var imageViewHolderBinding = ImageViewHolderBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(ImageViewHolderBinding.inflate(LayoutInflater.from(parent.context)).root)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.imageViewHolderBinding.imgView.setImageResource(imageIds[position])
    }

    override fun getItemCount(): Int {
        return imageIds.size
    }
}