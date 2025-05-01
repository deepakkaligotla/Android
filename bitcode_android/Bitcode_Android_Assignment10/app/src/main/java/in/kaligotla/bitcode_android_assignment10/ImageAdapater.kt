package `in`.kaligotla.bitcode_android_assignment10

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import `in`.kaligotla.bitcode_android_assignment10.databinding.ViewHolderImageBinding

class ImageAdapater(var images: IntArray): RecyclerView.Adapter<ImageAdapater.ImageViewHolder>() {

    inner class ImageViewHolder(imageView: View): RecyclerView.ViewHolder(imageView) {
        var viewHolderImageBinding  = ViewHolderImageBinding.bind(imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(ViewHolderImageBinding.inflate(LayoutInflater.from(parent.context)).root)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.viewHolderImageBinding.insideImage.setImageResource(images[position])
        holder.viewHolderImageBinding.insideImage.setOnClickListener {
            val intent = Intent()
            intent.putExtra("selectedImage", images[position])
            (holder.itemView.context as ImageActivity).setResult(1, intent)
            (holder.itemView.context as ImageActivity).finish()
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }
}