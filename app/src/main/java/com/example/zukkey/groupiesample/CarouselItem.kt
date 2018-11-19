package com.example.zukkey.groupiesample

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_carousel.*

class CarouselItem(private val carouselDecoration: RecyclerView.ItemDecoration,
                   private val carouselAdapter: GroupAdapter<com.xwray.groupie.ViewHolder>,
                   private val itemId: Long
) : Item(itemId) {

    override fun getLayout(): Int {
        return R.layout.item_carousel
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.recycler_view.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = carouselAdapter
            removeItemDecoration(carouselDecoration)
            addItemDecoration(carouselDecoration)
        }
    }

//    override fun getId(): Long {
//        return super.getId()
//    }

    override fun isSameAs(other: com.xwray.groupie.Item<*>?): Boolean {
        if (other is CarouselItem) {
            return itemId == other.id
        }
        return false
    }

    override fun equals(other: Any?): Boolean {
        if (other is CarouselItem) {
            return itemId == other.id
        }
        return false
    }

    override fun hashCode(): Int {
        return carouselAdapter.hashCode()
    }

}
