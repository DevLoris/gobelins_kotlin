package dev.pinna.news.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dev.pinna.news.MainActivity
import dev.pinna.news.R
import dev.pinna.news.adapters.ArticleAdapter
import dev.pinna.news.viewmodels.ArticlesViewModel
import kotlinx.android.synthetic.main.articles_list_fragment.*

class ArticlesFavoritesFragment: Fragment() {
    lateinit var viewModel: ArticlesViewModel
    lateinit var adapterRecycler: ArticleAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        adapterRecycler = ArticleAdapter {
            val fragment = ArticleDetailsFragment.newInstance(it.title)
            this.activity!!.supportFragmentManager.beginTransaction().apply {
                replace(R.id.article_fragment, fragment)
                addToBackStack(null)
            }.commit()
        }
        return inflater.inflate(R.layout.articles_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list.layoutManager = LinearLayoutManager(context)
        list.adapter = adapterRecycler
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(activity!!).get(ArticlesViewModel::class.java)
        viewModel.loadFavorites()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.favoriteArticles.observe(viewLifecycleOwner, Observer {
            adapterRecycler.updateData(it)
        })
    }
}