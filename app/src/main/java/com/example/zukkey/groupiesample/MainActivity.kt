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
            addAll(generateCarouselItems(8))
            add(innerSection)
            addAll(generateCarouselItems(10))
            groupAdapter.update(listOf(this))
        }

        fab.setOnClickListener {
            val newItem = boringFancyItems.mapIndexed { index, fancyItem ->
                if (index == 0) {
                    val color = Color.argb(255, 0,
                        255, 0)
                    FancyItem(color, 1, index.toLong() * 2)
                } else {
                    fancyItem
                }
            }
            group = makeCarouselGroup(newItem, 2)
            innerSection.update(listOf(group))
        }
    }

//    private fun makeCarouselItem(items: List<Item<*>>): CarouselItem {
//        val carouselDecoration = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            CarouselItemDecoration(getColor(R.color.colorPrimaryDark), 50)
//        } else {
//            TODO("VERSION.SDK_INT < M")
//        }
//        val carouselAdapter = GroupAdapter<com.xwray.groupie.ViewHolder>()
//        carouselAdapter.addAll(items)
//        return CarouselItem(carouselDecoration, carouselAdapter)
//    }

    private fun makeCarouselItemSection(items: List<Item<*>>): Section {
        group = makeCarouselGroup(items, 1)
        return Section().apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                add(group)
            }
        }
    }

    private fun makeCarouselGroup(items: List<Item<*>>, itemId: Long): Group {
        val carouselDecoration = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            CarouselItemDecoration(getColor(R.color.colorPrimaryDark), 50)
        } else {
            TODO("VERSION.SDK_INT < M")
        }
        val carouselAdapter = GroupAdapter<com.xwray.groupie.ViewHolder>()
        carouselAdapter.addAll(items)
        return CarouselGroup(carouselAdapter, true, CarouselItem(carouselDecoration, carouselAdapter, itemId))
    }


//    private fun makeCarouselItem2(items: List<Item<*>>): CarouselItem {
//        val carouselDecoration = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            CarouselItemDecoration(getColor(R.color.colorPrimaryDark), 50)
//        } else {
//            TODO("VERSION.SDK_INT < M")
//        }
//        val carouselAdapter = GroupAdapter<com.xwray.groupie.ViewHolder>()
//        for (i in 0..29) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                if(i == 1) {
//                    carouselAdapter.add(CarouselCardItem("update"))
//                } else {
//                    carouselAdapter.add(CarouselCardItem(i.toString()))
//                }
//            }
//        }
//        return CarouselItem(carouselDecoration, carouselAdapter)
//    }

    private fun generateFancyItems(count: Int): MutableList<FancyItem> {
        //val rnd = Random()
        return MutableList(count) { index ->
            val color = Color.argb(255, 0,
                0, 255)
            FancyItem(color, 1, index.toLong())
        }
    }

    private fun generateCarouselItems(count: Int): MutableList<CarouselCardItem> {
        //val rnd = Random()
        return MutableList(count) {
            val color = Color.argb(255, 0,
                0, 255)
            CarouselCardItem(color.toString())
        }
    }

//    private fun generateFancyItems2(count: Int): MutableList<FancyItem> {
//        //val rnd = Random()
//        return MutableList(count) { index ->
//            if (index == 0) {
//                val color = Color.argb(255, 0,
//                    255, 0)
//                FancyItem(color, 1)
//            } else {
//                val color = Color.argb(255, 0,
//                    0, 255)
//                FancyItem(color, 1)
//            }
//        }
//    }
}
