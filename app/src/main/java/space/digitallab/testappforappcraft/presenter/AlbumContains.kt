package space.digitallab.testappforappcraft.presenter

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.recyclerview.widget.LinearLayoutManager
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.album_contains.*
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

class MainActivity : AppCompatActivity() {

    lateinit var mService: ApiInterface
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: AlbumContentAdapter
    lateinit var dialog: AlertDialog
    var pathId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.album_contains)

        mService = Common.apiInterface
        recyclerAlbumContains.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerAlbumContains.layoutManager = layoutManager
        dialog = SpotsDialog.Builder().setCancelable(true).setContext(this).build()

        getAlbumContains()
    }


    private fun getAlbumContains() {

        dialog.show()
        mService.getAlbumContent(pathId).enqueue(object : Callback<MutableList<AlbumElements>> {
            override fun onFailure(call: Call<MutableList<AlbumElements>>, t: Throwable) {

            }

            override fun onResponse(call: Call<MutableList<AlbumElements>>, response: Response<MutableList<AlbumElements>>) {
                adapter = AlbumContentAdapter(baseContext, response.body() as MutableList<AlbumElements>)
                adapter.notifyDataSetChanged()
                recyclerList.adapter = adapter

                dialog.dismiss()
            }
        })
    }
}