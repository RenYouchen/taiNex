package com.kot.tainex.MainPage.UI

import android.graphics.Typeface
import android.graphics.pdf.PdfDocument.Page
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.gson.Gson
import com.kot.tainex.R
import com.kot.tainex.data.activitys
import com.kot.tainex.databinding.FragmentMainPageBinding
import com.kot.tainex.util.ListAdapter
import java.io.File

class MainPage : Fragment() {
    private lateinit var binding : FragmentMainPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainPageBinding.inflate(layoutInflater)
        val imagePager = binding.viewPager
        val tabLayout = binding.tabLayout
        val images = listOf(R.drawable.image_1, R.drawable.image_3)

        val gson = Gson()
        val json = context?.resources?.openRawResource(R.raw.activitys)?.bufferedReader().use { it?.readText() }
        val datas = gson.fromJson(json,Array<activitys>::class.java).toList()

        val list = binding.listItem
        list.layoutManager = LinearLayoutManager(context)
        list.adapter = ListAdapter(datas)

        val adapter = object : PagerAdapter() {
            override fun getCount(): Int {
                return images.size
            }

            override fun isViewFromObject(view: View, `object`: Any): Boolean {
                return view == `object`
            }

            override fun instantiateItem(container: ViewGroup, position: Int): Any {
                val imageView = ImageView(requireContext())
                imageView.setImageResource(images[position])
                container.addView(imageView)
                return imageView
            }
        }

        imagePager.adapter = adapter
        tabLayout.setupWithViewPager(imagePager)

        return binding.root
    }
}