package com.example.zukkey.groupiesample

import android.support.annotation.ColorInt
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_fancy.*

class FancyItem(@ColorInt private val color: Int,
                private val number: Int,
                private val itemId: Long) : Item(itemId) {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.item_fancy_cardView.setCardBackgroundColor(color)
        viewHolder.item_fancy_number.text = number.toString()
    }

    override fun getLayout(): Int = R.layout.item_fancy

    override fun getSpanSize(spanCount: Int, position: Int): Int = spanCount / 2

    override fun getId(): Long {
        return itemId
    }

    override fun isSameAs(other: com.xwray.groupie.Item<*>?): Boolean {
        if (other is FancyItem) {
            return itemId == other.itemId
        }
        return false
    }

    override fun equals(other: Any?): Boolean {
        if (other is FancyItem) {
            return itemId == other.itemId
        }
        return false
    }

    override fun hashCode(): Int {
        return itemId.hashCode()
    }

}
