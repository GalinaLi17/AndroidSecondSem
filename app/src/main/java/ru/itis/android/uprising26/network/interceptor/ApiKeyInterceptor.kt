package ru.itis.android.uprising26.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.Protocol
import okhttp3.ResponseBody.Companion.toResponseBody
import ru.itis.android.uprising26.BuildConfig
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiKeyInterceptor @Inject constructor() : Interceptor {

    private val errorCounter = AtomicInteger(0)

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val newRequest = request.newBuilder()
            .addHeader("Authorization", "Bearer ${BuildConfig.apiKey}")
            .build()

        if (errorCounter.incrementAndGet() % 3 == 0) {
            return Response.Builder()
                .request(newRequest)
                .protocol(Protocol.HTTP_1_1)
                .code(404)
                .message("Not Found")
                .body("{\"error\": \"Song not found\"}".toResponseBody(null))
                .build()
        }

        return chain.proceed(newRequest)
    }
}