package com.example.younes_rcc010

import android.content.ClipData
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
 lateinit var refresh :SwipeRefreshLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadNews()
        val mAdView :AdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        refresh = findViewById(R.id.refresh)
        refresh.setOnRefreshListener {

        }
    }

    fun loadNews (){
        val retrofit = Retrofit
            .Builder()
            .baseUrl("https://newsapi.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val callable = retrofit.create(CallableInterface::class.java)
        val newsCall = callable.getEg()

        newsCall.enqueue(object :Callback<NewsModel>{
            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                val news =response.body()
                val articles =news?.articles
                 val adapter = CustomAdaptar(this@MainActivity,articles)
                val rv : RecyclerView = findViewById(R.id.rv)
                rv.adapter= adapter
                refresh.isRefreshing =false
            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                val parent:LinearLayout = findViewById(R.id.parent)

            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when(id) {
            R.id.eg ->  refresh.setOnRefreshListener {
                loadNews()
            }
            R.id.us ->refresh.setOnRefreshListener {
              usNews()
            }
            R.id.de -> refresh.setOnRefreshListener {
              deNews()
            }
            R.id.it -> refresh.setOnRefreshListener {
               itNews()
            }
        }

        when(id) {
            R.id.eg -> loadNews()
            R.id.us -> usNews()
            R.id.de -> deNews()
            R.id.it -> itNews()
        }
        return super.onOptionsItemSelected(item)
    }
    fun usNews (){
        val retrofit = Retrofit
            .Builder()
            .baseUrl("https://newsapi.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val callable = retrofit.create(CallableInterface::class.java)
        callable.getUs().enqueue(object : Callback<NewsModel> {
            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                val news = response.body()
                val articles = news?.articles
                val adapter = CustomAdaptar(this@MainActivity, articles)
                val rv: RecyclerView = findViewById(R.id.rv)
                rv.adapter = adapter
                refresh.isRefreshing = false
            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {

            }
        })
    }
    fun deNews(){
        val retrofit = Retrofit
            .Builder()
            .baseUrl("https://newsapi.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val callable = retrofit.create(CallableInterface::class.java)
        callable.getDe().enqueue(object : Callback<NewsModel> {
            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                val news = response.body()
                val articles = news?.articles
                val adapter = CustomAdaptar(this@MainActivity, articles)
                val rv: RecyclerView = findViewById(R.id.rv)
                rv.adapter = adapter
                refresh.isRefreshing = false
            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {

            }
        })
    }
    fun itNews(){
        val retrofit = Retrofit
            .Builder()
            .baseUrl("https://newsapi.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val callable = retrofit.create(CallableInterface::class.java)
        callable.getIt().enqueue(object : Callback<NewsModel> {
            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                val news = response.body()
                val articles = news?.articles
                val adapter = CustomAdaptar(this@MainActivity, articles)
                val rv: RecyclerView = findViewById(R.id.rv)
                rv.adapter = adapter
                refresh.isRefreshing = false
            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {

            }
        })
    }

    override fun onBackPressed() {
        val exit = ExitDialog()
        exit.isCancelable = true
        exit.show(supportFragmentManager, null)
    }
    }

















