package dev.pinna.news.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import dev.pinna.news.MainActivity
import dev.pinna.news.R
import dev.pinna.news.models.Article

class ArticleAdapter(val handler: (article:Article) -> Unit) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    private val dataset: MutableList<Article> = mutableListOf()

    inner class ViewHolder(val root: View) : RecyclerView.ViewHolder(root) {
        fun bind(item: Article) {
            val material_icon_button = root.findViewById<Button>(R.id.material_icon_button)
            val txtTitle = root.findViewById<TextView>(R.id.title)
            val txtDesc = root.findViewById<TextView>(R.id.description)
            txtTitle.text = item.title
            txtDesc.text = item.description

            txtTitle.setOnClickListener {
                handler(item)
            }
            material_icon_button.setOnClickListener{
                handler(item)
            }
        }
    }

    fun updateData(list: List<Article>) {
        dataset.clear()
        dataset.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataset[position])
    }

    override fun getItemCount() = dataset.size
}