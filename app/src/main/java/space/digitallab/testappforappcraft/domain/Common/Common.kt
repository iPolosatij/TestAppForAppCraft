package space.digitallab.kotlinretrofitpicassospotsdialog.Common

import space.digitallab.testappforappcraft.domain.networck.ApiClient
import space.digitallab.testappforappcraft.domain.networck.ApiInterface

object Common {
    private val BASE_URL = "https://jsonplaceholder.typicode.com/"
    val apiInterface: ApiInterface
        get() = ApiClient.getClient(BASE_URL).create(ApiInterface::class.java)
}