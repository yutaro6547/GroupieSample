package com.example.zukkey.groupiesample

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.xwray.groupie.*
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import com.xwray.groupie.GroupAdapter


class MainActivity : AppCompatActivity() {

    private val excitingSection: Section = Section()
    private val carouseSection: Section = Section()

    private lateinit var group: Group

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
        val innerSection = makeCarouselItemSection(boringFancyItems)


        recycler_view.apply {
            layoutManager = GridLayoutManager(this@MainActivity, groupAdapter.spanCount).apply {
                spanSizeLookup = groupAdapter.spanSizeLookup
            }
            adapter = groupAdapter
        }

        Section(HeaderItem(R.string.carousel_title, R.string.carousel_subtitle)).apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                addAll(generateFancyItems(4))
                add(innerSection)
                addAll(generateFancyItems(10))
                groupAdapter.update(listOf(this))
            }
        }

        fab.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val newItem = boringFancyItems.mapIndexed { index, fancyItem ->
                    if (index == 0) {
                        val color = Color.argb(255, 0,
                            255, 0)
                        FancyItem(color, 1)
                    } else {
                        fancyItem
                    }
                }
                group = makeCarouselGroup(newItem)
                innerSection.update(listOf(group))
            }
        }
    }

    private fun makeCarouselItem(items: List<Item<*>>): CarouselItem {
        val carouselDecoration = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            CarouselItemDecoration(getColor(R.color.colorPrimaryDark), 50)
        } else {
            TODO("VERSION.SDK_INT < M")
        }
        val carouselAdapter = GroupAdapter<com.xwray.groupie.ViewHolder>()
        carouselAdapter.addAll(items)
        return CarouselItem(carouselDecoration, carouselAdapter)
    }

    private fun makeCarouselItemSection(items: List<Item<*>>): Section {
        group = makeCarouselGroup(items)
        return Section().apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                add(group)
            }
        }
    }

    private fun makeCarouselGroup(items: List<Item<*>>): Group {
        val carouselDecoration = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            CarouselItemDecoration(getColor(R.color.colorPrimaryDark), 50)
        } else {
            TODO("VERSION.SDK_INT < M")
        }
        val carouselAdapter = GroupAdapter<com.xwray.groupie.ViewHolder>()
        carouselAdapter.addAll(items)
        return CarouselGroup(carouselAdapter, true, CarouselItem(carouselDecoration, carouselAdapter))
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

    private fun generateFancyItems2(count: Int): MutableList<FancyItem> {
        //val rnd = Random()
        return MutableList(count) { index ->
            if (index == 0) {
                val color = Color.argb(255, 0,
                    255, 0)
                FancyItem(color, 1)
            } else {
                val color = Color.argb(255, 0,
                    0, 255)
                FancyItem(color, 1)
            }
        }
    }
}
