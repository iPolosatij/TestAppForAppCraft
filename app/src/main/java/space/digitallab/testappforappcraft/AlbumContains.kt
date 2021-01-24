package space.digitallab.testappforappcraft

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.album_contains.*
import kotlinx.android.synthetic.main.item_content.view.*
import kotlinx.android.synthetic.main.item_layout.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import space.digitallab.kotlinretrofitpicassospotsdialog.Adapter.AlbumContentAdapter
import space.digitallab.kotlinretrofitpicassospotsdialog.Adapter.AlbumListAdapter
import space.digitallab.kotlinretrofitpicassospotsdialog.Common.Common
import space.digitallab.testappforappcraft.R
import space.digitallab.testappforappcraft.data.dto.AlbumElements
import space.digitallab.testappforappcraft.data.dto.ListElement
import space.digitallab.testappforappcraft.domain.networck.ApiInterface

class AlbumContains : AppCompatActivity() {

    lateinit var mService: ApiInterface
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: AlbumContentAdapter
    lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.album_contains)

        val pathId = intent.getStringExtra("id")
        mService = Common.apiInterface
        albumRecyclerList.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        albumRecyclerList.layoutManager = layoutManager
        dialog = SpotsDialog.Builder().setCancelable(true).setContext(this).build()


            getList(pathId)
    }

    private fun getList(albumId: String?) {
        dialog.show()
        mService.getAlbumContent(albumId?.toInt()).enqueue(object : Callback<MutableList<AlbumElements>> {
            override fun onFailure(call: Call<MutableList<AlbumElements>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<MutableList<AlbumElements>>,
                response: Response<MutableList<AlbumElements>>
            ) {
                adapter = AlbumContentAdapter(baseContext, response.body() as MutableList<AlbumElements>)
                adapter.notifyDataSetChanged()
                albumRecyclerList.adapter = adapter

                dialog.dismiss()
            }
        })
    }

    fun viewImage(v: View) {
        val intent = Intent(this, ImageViewLoad::class.java)
        intent.putExtra("url", v.url.text)
        startActivity(intent)
    }
}