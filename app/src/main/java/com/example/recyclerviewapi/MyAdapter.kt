import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewapi.NewsResponse
import com.example.recyclerviewapi.R
import com.squareup.picasso.Picasso

class MyAdapter(private val newsItems: List<NewsResponse>) :
    RecyclerView.Adapter<MyAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.newshead)
        val descriptionTextView: TextView = itemView.findViewById(R.id.newsdesc)
        val imageView: ImageView = itemView.findViewById(R.id.ivimg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view, parent, false)
        return NewsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = newsItems[position]

        holder.titleTextView.text = currentItem.title
        holder.descriptionTextView.text = currentItem.description

        // You can use a library like Picasso or Glide to load images from URLs
        // Example using Picasso:
        Picasso.get().load(currentItem.imageUrl).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return newsItems.size
    }
}
