package space.digitallab.testappforappcraft

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import space.digitallab.kotlinretrofitpicassospotsdialog.Adapter.AlbumListAdapter
import space.digitallab.kotlinretrofitpicassospotsdialog.Common.Common
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

        getAlbumList()
    }

    private fun getAlbumList() {
        dialog.show()
        mService.getListElement().enqueue(object : Callback<MutableList<ListElement>> {
            override fun onFailure(call: Call<MutableList<ListElement>>, t: Throwable) {

            }

            override fun onResponse(call: Call<MutableList<ListElement>>, response: Response<MutableList<ListElement>>) {
                adapter = AlbumListAdapter(baseContext, response.body() as MutableList<ListElement>)
                adapter.notifyDataSetChanged()
                recyclerList.adapter = adapter

                dialog.dismiss()
            }
        })
    }
}