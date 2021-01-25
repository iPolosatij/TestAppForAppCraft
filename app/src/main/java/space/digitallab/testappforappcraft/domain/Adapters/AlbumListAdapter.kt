package space.digitallab.kotlinretrofitpicassospotsdialog.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_layout.view.*
import space.digitallab.testappforappcraft.R
import space.digitallab.testappforappcraft.data.db.DbHolder
import space.digitallab.testappforappcraft.data.db.ListElementDao
import space.digitallab.testappforappcraft.data.db.ListElementDb
import space.digitallab.testappforappcraft.data.dto.ListElement


class AlbumListAdapter(
    private val context: Context,
    private val albumList: MutableList<ListElement>

):RecyclerView.Adapter<AlbumListAdapter.AlbumListHolder>() {



    class AlbumListHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val id: TextView = itemView.Id
        val title: TextView = itemView.title
        val userId: TextView = itemView.userId

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumListHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_layout,
            parent,
            false
        )
        return AlbumListHolder(itemView)
    }

    override fun getItemCount() = albumList.size

    override fun onBindViewHolder(holder: AlbumListHolder, position: Int) {



        holder.id.text = albumList[position].id
        holder.title.text = albumList[position].title
        holder.userId.text = albumList[position].userId
    }
}

