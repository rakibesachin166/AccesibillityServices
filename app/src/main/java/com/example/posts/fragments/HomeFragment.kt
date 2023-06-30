package com.example.posts.fragments

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import com.example.posts.R

class HomeFragment : Fragment() {

     private lateinit var seePostBtn : CardView
    private lateinit var accessibilityBtn :CardView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

         seePostBtn = view.findViewById(R.id.Post_button_id)
         accessibilityBtn = view.findViewById(R.id.accessibility_btn_id)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        seePostBtn.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_layout_id, PostListFragment())
                .addToBackStack(null)
                .commit()
        }

        accessibilityBtn.setOnClickListener(View.OnClickListener {
            val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
            startActivity(intent)
        })
    }
}