package pokhilko.aleksandr.ru.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Aleksandr Pokhilko on 30.10.2018
 */
object RetrofitServiceCreator {

    private val retrofit = Retrofit.Builder()
            .baseUrl(ApiCredentials.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(createClientWithInterceptors())
            .build()

    private fun createClientWithInterceptors(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor { chain ->
                    val request = chain
                            .request()
                            .newBuilder()
                            .addHeader("Authorization", "Client-ID ${ApiCredentials.CLIENT_ID}")
                            .build()
                    chain.proceed(request)
                }
                .build()
    }

    fun createService(): ImgurApiService {
        return retrofit.create(ImgurApiService::class.java)
    }

}