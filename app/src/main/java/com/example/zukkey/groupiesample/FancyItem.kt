package com.example.zukkey.groupiesample

import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.item_card.view.*

class FancyItem(private val color: Int,
                private val number: Int): Item<ViewHolder>() {
    override fun getLayout(): Int = R.layout.item_card

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.item_fancy_cardView.setCardBackgroundColor(color)
       viewHolder.itemView.item_fancy_number.text = number.toString()
    }

    override fun getSpanSize(spanCount: Int, position: Int): Int = spanCount / 3

}