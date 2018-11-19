package com.example.zukkey.groupiesample

import android.support.v7.widget.RecyclerView
import com.xwray.groupie.*


/**
 * A group that contains a single carousel item and is empty when the carousel is empty
 */
class CarouselGroup(
    val adapter: GroupAdapter<ViewHolder>,
    var isEmpty: Boolean = true,
    val carouselItem: CarouselItem
) : Group {

    private var groupDataObserver: GroupDataObserver? = null

    private val adapterDataObserver = object : RecyclerView.AdapterDataObserver() {

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            val empty = adapter.itemCount == 0
            if (empty && !isEmpty) {
                isEmpty = empty
                groupDataObserver!!.onItemRemoved(carouselItem, 0)
            }
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            val empty = adapter.itemCount == 0
            if (isEmpty && !empty) {
                isEmpty = empty
                groupDataObserver!!.onItemInserted(carouselItem, 0)
            }
        }
    }

    override fun getItemCount(): Int {
        return if (isEmpty) 0 else 1
    }

    init {
        isEmpty = adapter.itemCount == 0
        adapter.registerAdapterDataObserver(adapterDataObserver)
    }

    override fun getItem(position: Int): Item<*> {
        return if (position == 0 && !isEmpty)
            carouselItem
        else
            throw IndexOutOfBoundsException()
    }

    override fun getPosition(item: Item<*>): Int {
        return if (item === carouselItem && !isEmpty) 0 else -1
    }

    override fun registerGroupDataObserver(groupDataObserver: GroupDataObserver) {
        this.groupDataObserver = groupDataObserver
    }

    override fun unregisterGroupDataObserver(groupDataObserver: GroupDataObserver) {
        this.groupDataObserver = null
    }
}
