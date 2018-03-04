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
}