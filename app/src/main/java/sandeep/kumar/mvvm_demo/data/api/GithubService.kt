package sandeep.kumar.mvvm_demo.data.api

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import sandeep.kumar.mvvm_demo.data.model.SearchResponse
import sandeep.kumar.mvvm_demo.data.model.User

interface GithubService {

    @GET("users")
    suspend fun getUser(): Response<List<User>>

    @GET("search/users")
    suspend fun searchUser(@Query("q") name: String): Response<SearchResponse>
}