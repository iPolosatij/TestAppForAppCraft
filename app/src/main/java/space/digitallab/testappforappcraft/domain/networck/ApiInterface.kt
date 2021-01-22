package space.digitallab.testappforappcraft.domain.networck


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import space.digitallab.testappforappcraft.data.dto.AlbumElements
import space.digitallab.testappforappcraft.data.dto.ListElement

interface ApiInterface{

    @GET("albums")
    fun getListElement(): Call<MutableList<ListElement>>

    @GET("photos?albumId={id}")
    fun getAlbumContent(@Path("id") id: Int): Call<MutableList<AlbumElements>>
}