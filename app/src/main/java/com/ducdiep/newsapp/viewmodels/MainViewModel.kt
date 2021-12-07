package com.ducdiep.newsapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ducdiep.newsapp.models.Article
import com.ducdiep.newsapp.models.ResponseNews
import com.ducdiep.newsapp.repository.NewsRepository

class MainViewModel : ViewModel() {
    var listArticle = MutableLiveData<List<Article>?>()
    var listArticleSearch = MutableLiveData<List<Article>?>()
    var dataLoading = MutableLiveData<Boolean>()
    fun fetchListNews(country: String, apiKey: String) {
        dataLoading.value = true
        NewsRepository.getInstance()
            .getTopNews(country, apiKey) { isSuccess: Boolean, response: ResponseNews? ->
                dataLoading.value = false
                if (isSuccess) {
                    listArticle.value = response?.articles
                } else {
                    listArticle.value = null
                }
            }
    }

    fun getListSearch(keySearch: String) {
        if (listArticle.value != null) {
            dataLoading.value = true
            var key = keySearch.toLowerCase()
            if (key == "") {
                listArticleSearch.value = listArticle.value
                dataLoading.value = false
            } else {
                var listSearch = ArrayList<Article>()
                for (element in listArticle.value!!) {
                    if (element.title.toLowerCase()
                            .contains(key) || element.description.toLowerCase()
                            .contains(key) || element.source.name.toLowerCase().contains(key)
                    ) {
                        listSearch.add(element)
                    }
                }
                dataLoading.value = false
                listArticleSearch.value = listSearch
            }

        }

    }
}