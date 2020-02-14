package dev.pinna.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import dev.pinna.news.datasource.database.Database
import dev.pinna.news.fragments.ArticleDetailsFragment
import dev.pinna.news.fragments.ArticlesFavoritesFragment
import dev.pinna.news.fragments.ArticlesFragment
import dev.pinna.news.models.Article
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Database.init(this)

        val fragment = ArticlesFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.article_fragment, fragment)
            addToBackStack(null)
        }.commit()

        my_favorites_button.setOnClickListener {
            val fragment = ArticlesFavoritesFragment()
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.article_fragment, fragment)
                addToBackStack(null)
            }.commit()
        }
        home_button.setOnClickListener {
            val fragment = ArticlesFragment()
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.article_fragment, fragment)
                addToBackStack(null)
            }.commit()
        }
    }
}
