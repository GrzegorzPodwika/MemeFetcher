package pl.podwikagrzegorz.data.repo

import pl.podwikagrzegorz.data.remote.response.MemeResponse
import retrofit2.http.GET

interface MemeApi {

    @GET("get_memes")
    suspend fun getMemeList() : MemeResponse
}