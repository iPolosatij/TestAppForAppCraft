package space.digitallab.testappforappcraft

import android.app.AlertDialog
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_layout.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import space.digitallab.kotlinretrofitpicassospotsdialog.Adapter.AlbumListAdapter
import space.digitallab.kotlinretrofitpicassospotsdialog.Common.Common
import space.digitallab.testappforappcraft.data.db.DbHolder
import space.digitallab.testappforappcraft.data.db.ListElementDao
import space.digitallab.testappforappcraft.data.db.ListElementDb
import space.digitallab.testappforappcraft.data.dto.ListElement
import space.digitallab.testappforappcraft.domain.networck.ApiInterface

class MainActivity : AppCompatActivity() {

    lateinit var mService: ApiInterface
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: AlbumListAdapter
    lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mService = Common.apiInterface
        recyclerList.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerList.layoutManager = layoutManager
        dialog = SpotsDialog.Builder().setCancelable(true).setContext(this).build()

        if(isOnline()) getAlbumList()
        else getAlbumListNotOnline()
    }

    private fun getAlbumList() {
        dialog.show()
        mService.getListElement().enqueue(object : Callback<MutableList<ListElement>> {
            override fun onFailure(call: Call<MutableList<ListElement>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<MutableList<ListElement>>,
                response: Response<MutableList<ListElement>>
            ) {
                adapter = AlbumListAdapter(baseContext, response.body() as MutableList<ListElement>)
                adapter.notifyDataSetChanged()
                recyclerList.adapter = adapter

                dialog.dismiss()
            }
        })
    }

    fun getAlbumListNotOnline(){

        dialog.show()

        val db: ListElementDb? = DbHolder.instance?.getDatabase()
        val listElementDao: ListElementDao? = db?.listElementDao()

        adapter = AlbumListAdapter(baseContext, listElementDao?.all as MutableList<ListElement>)
        adapter.notifyDataSetChanged()
        recyclerList.adapter = adapter

        dialog.dismiss()
    }

    fun albumContains(v: View) {
        val intent = Intent(this, AlbumContains::class.java)
        intent.putExtra("id", v.Id.text)
        startActivity(intent)
    }

    fun isOnline(): Boolean {
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }
}