package com.example.zukkey.groupiesample

import android.databinding.DataBindingUtil
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.example.zukkey.groupiesample.databinding.ActivityMainBinding
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder
import java.util.*

class MainActivity : AppCompatActivity() {

    private val excitingSection: Section = Section()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val boringFancyItems = generateFancyItems(6)
        val excitingFancyItems = generateFancyItems(12)

        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            spanCount = 3
        }

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(this@MainActivity, groupAdapter.spanCount).apply {
                spanSizeLookup = groupAdapter.spanSizeLookup
            }
            adapter = groupAdapter
        }

        ExpandableGroup(ExpandableHeaderItem("Boring Group"), true).apply {
            add(Section(boringFancyItems))
            groupAdapter.add(this)
        }
        ExpandableGroup(ExpandableHeaderItem("Exciting Group"), true).apply {
            excitingSection.addAll(excitingFancyItems)
            add(excitingSection)
            groupAdapter.add(this)
        }

        binding.floatingActionButton.setOnClickListener {
            excitingFancyItems.shuffle()
            excitingSection.update(excitingFancyItems)
        }
    }

    private fun generateFancyItems(count: Int): MutableList<FancyItem> {
        val rnd = Random()
        return MutableList(count) {
            val color = Color.argb(255, rnd.nextInt(256),
                    rnd.nextInt(256),  rnd.nextInt(256))
            FancyItem(color, rnd.nextInt(100))
        }
    }
}
