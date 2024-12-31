package com.example.sodaw2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // ViewPager2와 TabLayout을 findViewById로 연결
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        val tabLayout: TabLayout = findViewById(R.id.tab_layout)

        // ViewPager 어댑터 설정
        val adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter

        // TabLayout과 ViewPager 연결

        val tabTitles = listOf("Contacts", "Gallery", "Game")

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }



    class ViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = 3 // 탭의 개수

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> MondayFragment()
                1 -> TuesdayFragment()
                2 -> WednesdayFragment()
                else -> throw IllegalStateException("Invalid position $position")
            }
        }
    }
}

