package pl.podwikagrzegorz.data.repo

import dagger.hilt.android.scopes.ActivityScoped
import pl.podwikagrzegorz.data.remote.response.MemeResponse
import pl.podwikagrzegorz.util.Resource
import javax.inject.Inject

@ActivityScoped
class DefaultMemeRepository @Inject constructor(
    private val api: MemeApi
) : MemeRepository {
    override suspend fun getMemeList(): Resource<MemeResponse> {
        val response = try {
            api.getMemeList()
        } catch (e: Exception) {
            return Resource.Error(e.message?: "Unknown error has occurred")
        }

        return Resource.Success(response)
    }
}