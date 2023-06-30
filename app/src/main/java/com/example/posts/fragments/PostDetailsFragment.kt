package com.example.posts.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.posts.R
import com.example.posts.model.Post

class PostDetailsFragment(var post: Post) : Fragment() {
    private lateinit var userIdTextView: TextView
    private lateinit var idTextView: TextView
    private lateinit var titleTextView: TextView
    private lateinit var bodyTextView: TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_post_detail, container, false)
        userIdTextView = view.findViewById(R.id.user_idTextView)
        idTextView = view.findViewById(R.id.idTextView)
        titleTextView = view.findViewById(R.id.titleTextView)
        bodyTextView = view.findViewById(R.id.bodyTextView)

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userIdTextView.text = post.userId.toString()
        idTextView.text = post.id.toString()
        titleTextView.text = post.title
        bodyTextView.text = post.body

    }



}

