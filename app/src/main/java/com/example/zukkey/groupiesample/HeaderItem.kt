package com.example.zukkey.groupiesample

import android.support.annotation.StringRes
import android.view.View
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_header.*

open class HeaderItem(
        @StringRes private val titleStringResId: Int,
        @StringRes private val subtitleResId: Int? = null,
        private val onIconClickListener: View.OnClickListener? = null) : Item() {

    override fun getLayout(): Int {
        return R.layout.item_header
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.title.setText(titleStringResId)

        viewHolder.subtitle.apply {
            visibility = View.GONE
            subtitleResId?.let {
                visibility = View.VISIBLE
                setText(it)
            }
        }
    }
}