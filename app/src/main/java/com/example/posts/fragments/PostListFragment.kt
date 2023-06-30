package com.example.posts.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.posts.R
import com.example.posts.adapter.PostAdapter
import com.example.posts.database.AppDatabase
import com.example.posts.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PostListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PostAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_post_list, container, false)
        recyclerView = view.findViewById(R.id.recycler_view_posts)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = PostAdapter { post ->
            showPostDetails(post)
        }
        recyclerView.adapter = adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            val dao = AppDatabase.getInstance(requireContext()).postDao()
            GlobalScope.launch(Dispatchers.IO) {
                val posts = dao.getAllPosts()
                withContext(Dispatchers.Main) {
                    adapter.setPosts(posts)
                }
            }


    }






    private fun showPostDetails(post: Post) {

        parentFragmentManager.beginTransaction()
            .replace(R.id.frame_layout_id, PostDetailsFragment(post))
            .addToBackStack(null)
            .commit()
    }
}