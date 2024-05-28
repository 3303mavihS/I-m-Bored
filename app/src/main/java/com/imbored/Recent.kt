package com.imbored

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.imbored.databinding.ActivityRecentBinding

class Recent : AppCompatActivity() {

    private lateinit var binding: ActivityRecentBinding
    private lateinit var viewPager: ViewPager2
    private lateinit var viewPageAdapter: RecentViewPagerAdapter
    private lateinit var tabLayout : TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRecentBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //fetching the name from the sharedPreferences
        val readSharedPreferences = getSharedPreferences("activity-name", MODE_PRIVATE)
        val readName = readSharedPreferences.getString("name","")
        val avatar = readSharedPreferences.getInt("avatar",0)

        binding.greet.text = "Hi, $readName"
        binding.avatar.setImageResource(avatar)

        viewPager = binding.viewPagerSec
        viewPageAdapter = RecentViewPagerAdapter(this)
        viewPager.adapter = viewPageAdapter
        tabLayout = binding.tabBarLayout

        TabLayoutMediator(tabLayout,viewPager){tabItem:TabLayout.Tab,position:Int ->
            when(position){
                0->tabItem.text = "Recent Activity"
                else->tabItem.text = "Setting"
            }
        }.attach()

    }

    override fun onResume() {
        super.onResume()
        //fetching the name from the sharedPreferences
        val readSharedPreferences = getSharedPreferences("activity-name", MODE_PRIVATE)
        val readName = readSharedPreferences.getString("name","")
        val avatar = readSharedPreferences.getInt("avatar",0)

        binding.greet.text = "Hi, $readName"
        binding.avatar.setImageResource(avatar)
    }
}