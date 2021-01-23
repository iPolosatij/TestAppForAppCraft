package space.digitallab.kotlinretrofitpicassospotsdialog.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_content.view.*
import kotlinx.android.synthetic.main.item_layout.view.*
import space.digitallab.testappforappcraft.R
import space.digitallab.testappforappcraft.data.dto.AlbumElements

class AlbumContentAdapter(private val context: Context,
                       private val albumContains: MutableList<AlbumElements>):RecyclerView.Adapter<AlbumContentAdapter.AlbumContentHolder>() {

    class AlbumContentHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.image
        val id: TextView = itemView.Id
        val title: TextView = itemView.title
        val albumId: TextView = itemView.albumId

        fun bind(listItem: AlbumElements) {
            image.setOnClickListener {
                 Toast.makeText(it.context, "нажал на ${itemView.image}", Toast.LENGTH_SHORT)
                     .show()
             }

            itemView.setOnClickListener {
                Toast.makeText(it.context, "нажал на ${itemView.title.text}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumContentHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.album_contains, parent, false)
        return AlbumContentHolder(itemView)
    }

    override fun getItemCount() = albumContains.size

    override fun onBindViewHolder(holder: AlbumContentHolder, position: Int) {
        val listItem = albumContains[position]
        holder.bind(listItem)

        Picasso.get().load(albumContains[position].thumbnailUrl).into(holder.image)
        holder.id.text = albumContains[position].id
        holder.title.text = albumContains[position].title
        holder.albumId.text = albumContains[position].albumId
    }

}