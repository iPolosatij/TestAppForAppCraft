package space.digitallab.kotlinretrofitpicassospotsdialog.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_content.view.*
import kotlinx.android.synthetic.main.item_layout.view.*
import space.digitallab.testappforappcraft.R
import space.digitallab.testappforappcraft.data.dto.AlbumElements

class AlbumContentAdapter(private val context: Context,
                       private val albumContain: MutableList<AlbumElements>):RecyclerView.Adapter<AlbumContentAdapter.AlbumContentHolder>() {

    class AlbumContentHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.image
        val albumId: TextView = itemView.albumId
        val albumTitle: TextView = itemView.AlbumTitle
        val elementId: TextView = itemView.elementId
        val url: TextView = itemView.url

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumContentHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_content, parent, false)
        return AlbumContentHolder(itemView)
    }

    override fun getItemCount() = albumContain.size

    override fun onBindViewHolder(holder: AlbumContentHolder, position: Int) {


        Picasso.get().load(albumContain[position].thumbnailUrl).into(holder.image)
        holder.albumId.text = albumContain[position].albumId
        holder.albumTitle.text = albumContain[position].title
        holder.elementId.text = albumContain[position].id
        holder.url.text = albumContain[position].url
    }

}