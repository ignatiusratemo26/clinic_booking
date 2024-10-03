package com.example.mydoc.network
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import com.example.mydoc.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("Authorization", "Bearer ${getFirebaseToken()}")
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .build()
    }
    private fun getFirebaseToken(): String {
        // Replace with your method to get the Firebase token
        return "your_firebase_token"
    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}
