package com.example.recyclerviewapi

import MyAdapter
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//65af8791dbfa43e08e4c72f52455e186
class MainActivity : AppCompatActivity() {

    private lateinit var rvcategories: RecyclerView
    private lateinit var topStoriesrv: RecyclerView
    private lateinit var toolbarr: Toolbar
    private lateinit var progressBar: ProgressBar
    private lateinit var toolbartext:TextView
    lateinit var adapter: MyAdapter



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        topStoriesrv=findViewById(R.id.topStoriesrv)
//        rvcategories=findViewById(R.id.rvcategories)
        toolbarr=findViewById(R.id.toolbarr)
        progressBar=findViewById(R.id.progress)
        toolbartext=findViewById(R.id.toolbartext)

        rvcategories.layoutManager = LinearLayoutManager(this)
        topStoriesrv.layoutManager = LinearLayoutManager(this)


        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RetrofitAPI::class.java)

        val call = service.getTopHeadlines("in", "65af8791dbfa43e08e4c72f52455e186")



        call.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    val newsResponse = response.body()
                    val newsItems = newsResponse?.articles?.map { article ->
                        NewsResponse(
                            article.title,
                            article.description,
                            article.urlToImage ?: "URL_TO_DEFAULT_IMAGE"
                        )
                    }
                    adapter = MyAdapter(newsItems ?: emptyList())
                    topStoriesrv.adapter = adapter
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("NewsApp", "Error: ${t.message}")
            }
        })



    }
}


