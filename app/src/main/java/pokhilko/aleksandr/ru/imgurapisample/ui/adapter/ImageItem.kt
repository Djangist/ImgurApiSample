package pokhilko.aleksandr.ru.imgurapisample.ui.adapter

import com.bumptech.glide.Glide
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_image.view.*
import pokhilko.aleksandr.ru.domain.model.Image
import pokhilko.aleksandr.ru.imgurapisample.R

/**
 * Created by Aleksandr Pokhilko on 30.10.2018
 */
class ImageItem constructor(private val image: Image) : Item() {

    override fun getLayout() = R.layout.item_image

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.tvTitle.text = image.description
        Glide.with(viewHolder.itemView).load(image.link).into(viewHolder.itemView.ivImage)
    }
}