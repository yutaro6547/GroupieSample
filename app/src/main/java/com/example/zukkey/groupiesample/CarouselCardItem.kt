package com.example.zukkey.groupiesample

import android.support.annotation.ColorInt
import android.support.annotation.StringRes
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_square_card.*

class CarouselCardItem(@StringRes private val colorRes: String) : Item() {

    override fun getLayout() = R.layout.item_square_card

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.carousel_text.text = colorRes
    }

    fun setText(viewHolder: ViewHolder, updateText: String) {
        viewHolder.carousel_text.text = updateText
    }

    override fun isSameAs(other: com.xwray.groupie.Item<*>?): Boolean {
        if (other is CarouselCardItem) {
            return colorRes == other.colorRes
        }
        return false
    }

    override fun equals(other: Any?): Boolean {
        if (other is CarouselCardItem) {
            return colorRes == other.colorRes
        }
        return false
    }

    override fun hashCode(): Int {
        return colorRes.hashCode()
    }

}
