package pl.podwikagrzegorz.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.podwikagrzegorz.data.repo.DefaultMemeRepository
import pl.podwikagrzegorz.data.repo.MemeApi
import pl.podwikagrzegorz.data.repo.MemeRepository
import pl.podwikagrzegorz.util.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMemeApi() : MemeApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MemeApi::class.java)
    }

    @Singleton
    @Provides
    fun provideDefaultRepo(api: MemeApi) : MemeRepository {
        return DefaultMemeRepository(api)
    }
}