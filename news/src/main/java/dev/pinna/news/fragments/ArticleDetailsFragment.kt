package dev.pinna.news.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import dev.pinna.news.R
import dev.pinna.news.viewmodels.ArticlesViewModel
import kotlinx.android.synthetic.main.article_details_fragment.*
import android.content.Intent
import android.net.Uri
import android.text.Html
import android.widget.TextView
import androidx.core.content.ContextCompat
import dev.pinna.news.models.Article


class ArticleDetailsFragment : Fragment() {
    lateinit var viewModel: ArticlesViewModel

    companion object
    {
        const val ARG_ARTICLE = "ARG_ARTICLE"
        fun newInstance(string: String): ArticleDetailsFragment {
            return ArticleDetailsFragment().apply{
                arguments = bundleOf(ARG_ARTICLE to string)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.article_details_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(activity!!).get(ArticlesViewModel::class.java)
        viewModel.loadArticleFromTitle(arguments?.getString(ARG_ARTICLE)!!)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        val context = this.context ?: return
        super.onActivityCreated(savedInstanceState)

        favorite_button.setOnClickListener { _->
            viewModel.toggleFavoriteOnActual()
            Log.i("FAVORITE", "Favorite")
        }

        viewModel.actualArticle.observe(viewLifecycleOwner, Observer {
            Log.i("FAVORITE", "Update")
            title_article.html(it.title.coronavirus())
          //  title_article.text = it.title
            Picasso.get().load(it.urlToImage).into(image)
            description_article.html(it.description.coronavirus())

            material_icon_button.setOnClickListener{_ ->
                val path = Uri.parse(it.url)
                val intent = Intent(Intent.ACTION_VIEW, path)
                context.startActivity(intent)
            }

            favorite_button.icon = when( it.favorite ) {
                true -> ContextCompat.getDrawable(context, R.drawable.ic_star_black_24dp)
                false -> ContextCompat.getDrawable(context, R.drawable.ic_star_border_black_24dp)
            }


            button_share.setOnClickListener {_->
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, it.title)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
        })
    }
}

fun TextView.html(text:String) {
    this.text = Html.fromHtml(text)
}

fun String.coronavirus() : String {
    return this.replace("coronavirus", "<font color='#EE0000'>Coronavirus</font>", true)
}