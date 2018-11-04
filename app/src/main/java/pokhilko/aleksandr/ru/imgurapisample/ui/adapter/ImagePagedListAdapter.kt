package pokhilko.aleksandr.ru.imgurapisample.ui.adapter

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import pokhilko.aleksandr.ru.domain.model.Image
import pokhilko.aleksandr.ru.imgurapisample.R

/**
 * Created by Aleksandr Pokhilko on 31.10.2018
 */
class ImagePagedListAdapter: PagedListAdapter<Image, ImagePagedListAdapter.ImageViewHolder>(
        object : DiffUtil.ItemCallback<Image>() {
            override fun areItemsTheSame(oldImage: Image,
                                         newImage: Image): Boolean =
                    oldImage.id == newImage.id

            override fun areContentsTheSame(oldImage: Image,
                                            newImage: Image): Boolean =
                    oldImage == newImage
        }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, null)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = getItem(position)
        if( image != null ){
            holder.bind(image)
        }
    }

    class ImageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(image: Image){
            val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
            tvTitle.text = image.description

            val ivImage = itemView.findViewById<ImageView>(R.id.ivImage)
            Glide.with(itemView).load(image.link).into(ivImage)
        }
    }
}