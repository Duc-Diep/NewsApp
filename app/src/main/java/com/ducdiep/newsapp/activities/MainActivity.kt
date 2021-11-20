package com.ducdiep.newsapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ducdiep.newsapp.API_KEY
import com.ducdiep.newsapp.R
import com.ducdiep.newsapp.adapters.NewsAdapter
import com.ducdiep.newsapp.models.Article
import com.ducdiep.newsapp.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(),SwipeRefreshLayout.OnRefreshListener {
    lateinit var viewModel:MainViewModel
    lateinit var newsAdapter:NewsAdapter
    lateinit var job:Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        job = CoroutineScope(Dispatchers.Main).launch {
            delay(500)
            viewModel.getListSearch(edt_search.text.toString())
        }
        refresh_layout.setOnRefreshListener(this)
        edt_search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                    job.cancel()
                    job = CoroutineScope(Dispatchers.Main).launch {
                        delay(500)
                        viewModel.getListSearch(edt_search.text.toString())
                    }
                    job.start()
            }

        })
    }



    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.listArticle.observe(this){
            setupAdapter(it!!)
        }
        viewModel.listArticleSearch.observe(this){
            setupAdapter(it!!)
        }
        viewModel.dataLoading.observe(this){
            if (it==true){
                refresh_layout.isRefreshing = true
            }else{
                refresh_layout.isRefreshing = false
            }
        }
        viewModel.fetchListNews("us", API_KEY)

    }

    private fun setupAdapter(list:List<Article>) {
        newsAdapter = NewsAdapter(this,list)
        newsAdapter.setonClickItem {
            var intent = Intent(this,WebViewActivity::class.java)
            intent.putExtra("url",it.url)
            startActivity(intent)
            Toast.makeText(this, "${it.title}", Toast.LENGTH_SHORT).show()
        }
        rcv_news.adapter = newsAdapter
    }

    override fun onRefresh() {
        viewModel.fetchListNews("us", API_KEY)
        refresh_layout.isRefreshing = false
    }
}