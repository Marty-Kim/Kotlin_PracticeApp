package marty.ration.com.flitto_kimeunchan.common

import android.app.Application
import marty.ration.com.flitto_kimeunchan.http.ApiService
import marty.ration.com.flitto_kimeunchan.http.ServerRequest
import retrofit2.Retrofit


class MAPP : Application(){


    private var retrofit: Retrofit? = null
    private var RAPP: MAPP? = null
    private var listApi : ApiService? = null;


    fun getRAPP(): MAPP? {
        return RAPP
    }

    fun getRetrofit(): Retrofit? {
        return retrofit
    }

    fun getApiService() : ApiService?{
        return listApi
    }

    override fun onCreate() {
        super.onCreate()
        RAPP = this
        retrofit = ServerRequest.getService()
        listApi = retrofit?.create(ApiService::class.java)

    }
}