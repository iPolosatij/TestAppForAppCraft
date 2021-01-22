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
import kotlinx.android.synthetic.main.item_layout.view.*
import space.digitallab.testappforappcraft.R
import space.digitallab.testappforappcraft.data.dto.ListElement

class AlbumListAdapter(private val context: Context,
                       private val albumList: MutableList<ListElement>):RecyclerView.Adapter<AlbumListAdapter.AlbumListHolder>() {

    class AlbumListHolder(itemView: View): RecyclerView.ViewHolder(itemView){
       // val image: ImageView = itemView.image_movie
        val id: TextView = itemView.Id
        val title: TextView = itemView.title
        val userId: TextView = itemView.userId

        fun bind(listItem: ListElement) {
           /* image.setOnClickListener {
                Toast.makeText(it.context, "нажал на ${itemView.image_movie}", Toast.LENGTH_SHORT)
                    .show()
            }

            */
            itemView.setOnClickListener {
                Toast.makeText(it.context, "нажал на ${itemView.title.text}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumListHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return AlbumListHolder(itemView)
    }

    override fun getItemCount() = albumList.size

    override fun onBindViewHolder(holder: AlbumListHolder, position: Int) {
        val listItem = albumList[position]
        holder.bind(listItem)

       // Picasso.get().load(albumList[position].imageurl).into(holder.image)
        holder.id.text = albumList[position].id
        holder.title.text = albumList[position].title
        holder.userId.text = albumList[position].userId
    }

}