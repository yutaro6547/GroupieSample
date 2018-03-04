package com.example.zukkey.groupiesample

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val excitingSection: Section = Section()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val verticalFancyItems = generateFancyItems(4)
        val boringFancyItems = generateFancyItems(2)
        val carouselFancyItems = generateFancyItems(8)
        val excitingFancyItems = generateFancyItems(6)

        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            spanCount = 2
        }

        recycler_view.apply {
            layoutManager = GridLayoutManager(this@MainActivity, groupAdapter.spanCount).apply {
                spanSizeLookup = groupAdapter.spanSizeLookup
            }
            adapter = groupAdapter
        }


        Section(HeaderItem(R.string.app_name)).apply {
            add(ColumnGroup(verticalFancyItems))
            groupAdapter.add(this)
        }

        ExpandableGroup(ExpandableHeaderItem("Boring Group"), true).apply {
            add(Section(boringFancyItems))
            groupAdapter.add(this)
        }

        Section(HeaderItem(R.string.carousel_title, R.string.carousel_subtitle)).apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                add(makeCarouselItem(boringFancyItems))
                groupAdapter.add(this)
            }
        }

        ExpandableGroup(ExpandableHeaderItem("Exciting Group"), true).apply {
            excitingSection.addAll(excitingFancyItems)
            add(excitingSection)
            groupAdapter.add(this)
        }

        fab.setOnClickListener {
            excitingFancyItems.shuffle()
            excitingSection.update(excitingFancyItems)
        }
    }

    private fun makeCarouselItem(items: List<Item<*>>): CarouselItem {
        val carouselDecoration = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            CarouselItemDecoration(getColor(R.color.colorPrimaryDark), 50)
        } else {
            TODO("VERSION.SDK_INT < M")
        }
        val carouselAdapter = GroupAdapter<com.xwray.groupie.ViewHolder>()
        for (i in 0..29) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                carouselAdapter.add(CarouselCardItem(i.toString()))
            }
        }
        return CarouselItem(carouselDecoration, carouselAdapter)
    }

    private fun generateFancyItems(count: Int): MutableList<FancyItem> {
        val rnd = Random()
        return MutableList(count) {
            val color = Color.argb(255, rnd.nextInt(256),
                    rnd.nextInt(256), rnd.nextInt(256))
            FancyItem(color, rnd.nextInt(100))
        }
    }
}
