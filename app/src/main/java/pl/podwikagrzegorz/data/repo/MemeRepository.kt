package pl.podwikagrzegorz.data.repo

import pl.podwikagrzegorz.data.remote.response.MemeResponse
import pl.podwikagrzegorz.util.Resource

interface MemeRepository {
    suspend fun getMemeList() : Resource<MemeResponse>
}