package com.example.posts

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.posts.database.AppDatabase
import com.example.posts.fragments.HomeFragment
import com.example.posts.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout_id, HomeFragment()).commit()

      fetchPosts()

    }
    private fun fetchPosts() {
        val apiService = ApiClient.apiService
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val posts = apiService.getPosts()
                Log.d("Online_posts: ", posts.toString())
                insertPostsToDatabase(posts)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun insertPostsToDatabase(posts: List<Post>) {
        val dao = AppDatabase.getInstance(applicationContext).postDao()
        GlobalScope.launch(Dispatchers.IO) {
            dao.insertAll(posts)
        }
    }



}