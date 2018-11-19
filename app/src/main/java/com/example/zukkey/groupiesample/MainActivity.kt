package com.example.zukkey.groupiesample

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.xwray.groupie.*
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val excitingSection: Section = Section()
    private val carouseSection: Section = Section()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val verticalFancyItems = generateFancyItems(4)
        val boringFancyItems = generateFancyItems(8)
        val carouselFancyItems = generateFancyItems(8)
        val excitingFancyItems = generateFancyItems(6)

        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            spanCount = 2
        }

        var items = mutableListOf<Group>()


        recycler_view.apply {
            layoutManager = GridLayoutManager(this@MainActivity, groupAdapter.spanCount).apply {
                spanSizeLookup = groupAdapter.spanSizeLookup
            }
            adapter = groupAdapter
        }

        Section(HeaderItem(R.string.carousel_title, R.string.carousel_subtitle)).apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                add(makeCarouselItemSection(boringFancyItems))
                groupAdapter.update(listOf(this))
            }
        }

        fab.setOnClickListener {
            val copyItem = boringFancyItems.mapIndexed { index, fancyItem ->
                if(index == 0) {
                    val color = Color.argb(255, 255,
                        0, 0)
                    FancyItem(color, 2)
                } else {
                    fancyItem
                }
            }
            val outerSection = groupAdapter.getItem(1) as Section
            val innerSection = outerSection.getItem(0) as Section
            innerSection.update(copyItem)
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

    private fun makeCarouselItemSection(items: List<Item<*>>): Section {
        return Section().apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                addAll(items)
            }
        }
    }


    private fun makeCarouselItem2(items: List<Item<*>>): CarouselItem {
        val carouselDecoration = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            CarouselItemDecoration(getColor(R.color.colorPrimaryDark), 50)
        } else {
            TODO("VERSION.SDK_INT < M")
        }
        val carouselAdapter = GroupAdapter<com.xwray.groupie.ViewHolder>()
        for (i in 0..29) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if(i == 1) {
                    carouselAdapter.add(CarouselCardItem("update"))
                } else {
                    carouselAdapter.add(CarouselCardItem(i.toString()))
                }
            }
        }
        return CarouselItem(carouselDecoration, carouselAdapter)
    }

    private fun generateFancyItems(count: Int): MutableList<FancyItem> {
        //val rnd = Random()
        return MutableList(count) {
            val color = Color.argb(255, 0,
                0, 255)
            FancyItem(color, 1)
        }
    }
}
