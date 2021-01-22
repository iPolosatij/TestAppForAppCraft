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
import kotlinx.android.synthetic.main.item_layout.view.Id
import kotlinx.android.synthetic.main.item_layout.view.title
import space.digitallab.testappforappcraft.R
import space.digitallab.testappforappcraft.data.dto.AlbumElements

class AlbumContentAdapter(private val context: Context,
                          private val albumContent: MutableList<AlbumElements>):RecyclerView.Adapter<AlbumContentAdapter.AlbumContentHolder>() {

    class AlbumContentHolder(itemView: View): RecyclerView.ViewHolder(itemView){
       val thumbnail: ImageView = itemView.image
        val id: TextView = itemView.Id
        val title: TextView = itemView.title
        val albumId: TextView = itemView.albumId

        fun bind(listItem: AlbumElements) {
            thumbnail.setOnClickListener {
                Toast.makeText(it.context, "нажал на ${itemView.image}", Toast.LENGTH_SHORT)
                    .show()
            }


            itemView.setOnClickListener {
                Toast.makeText(it.context, "нажал на ${itemView.title.text}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumContentHolder{
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_content, parent, false)
        return AlbumContentHolder(itemView)
    }

    override fun getItemCount() = albumContent.size

    override fun onBindViewHolder(holder: AlbumContentHolder, position: Int) {
        val listItem = albumContent[position]
        holder.bind(listItem)

        Picasso.get().load(albumContent[position].thumbnailUrl).into(holder.thumbnail)
        holder.id.text = albumContent[position].id
        holder.title.text = albumContent[position].title
        holder.albumId.text = albumContent[position].albumId
    }

}